package com.griddynamics.workshop.imdg.domain.common.load;

import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/1/13
 */
public class TrackingLoader<T> implements Loader<T> {

    private static final int STATISTICS_INTERVAL = 5000;

    private static final PeriodFormatter PERIOD_FORMATTER = new PeriodFormatterBuilder()
            .printZeroRarelyLast()
            .appendDays().appendSuffix(" day ", " days ")
            .appendHours().appendSuffix(" hour ", " hours ")
            .minimumPrintedDigits(2)
            .appendMinutes().appendSuffix(" minute ", " minutes ")
            .appendSeconds().appendSuffix(" second", " seconds")
            .toFormatter();

    private final Logger log;

    private final double needed;

    private final Loader<T> delegate;

    public TrackingLoader(double needed, Loader<T> delegate) {
        this.needed = needed;
        this.delegate = delegate;
        this.log = LoggerFactory.getLogger(delegate.getClass());
    }

    @Override
    public void load(final Source source, final Callback<T> callback) throws Exception {
        final TrackingInputStream trackingInputStream = new TrackingInputStream(source.getInputStream());
        source.setInputStream(trackingInputStream);
        long totalLength = (long) (source.getFile().length() * needed);
        final ProgressTracker progressTracker = new ProgressTracker(totalLength);
        long startTime = System.currentTimeMillis();
        final AtomicLong lastStatisticsTime = new AtomicLong(startTime);
        final AtomicLong counter = new AtomicLong();
        delegate.load(source, new Callback<T>() {
            @Override
            public boolean onLoad(T entity) throws Exception {
                long currentTime = System.currentTimeMillis();
                long recordsCount = counter.incrementAndGet();
                if (currentTime - lastStatisticsTime.get() >= STATISTICS_INTERVAL) {
                    progressTracker.update(trackingInputStream.getCounter(), recordsCount);
                    Period period = Duration.standardSeconds(progressTracker.getRemainingTime())
                            .toPeriod()
                            .normalizedStandard(PeriodType.dayTime());
                    log.info("Records: {} at {}/sec, Size: {}/{} at {}/sec ({}%), ETA: {}",
                            humanReadableByteCount(progressTracker.getCompletedCountRecords(), true),
                            humanReadableByteCount((long) progressTracker.getAverageSpeedRecords(), true),
                            humanReadableByteCount(progressTracker.getCompletedCount()),
                            humanReadableByteCount(progressTracker.getTotalCount()),
                            humanReadableByteCount((long) progressTracker.getAverageSpeed()),
                            String.format("%.2f", progressTracker.getCompletedPercentage()),
                            PERIOD_FORMATTER.print(period)
                    );
                    lastStatisticsTime.set(currentTime);
                }
                boolean result = callback.onLoad(entity);
                return progressTracker.getCompletedPercentage() <= 100 && result;
            }
        });
        Period period = Duration.standardSeconds((System.currentTimeMillis() - startTime) / 1000)
                .toPeriod()
                .normalizedStandard(PeriodType.dayTime());
        log.info("Loaded {} records ({}) in {}", humanReadableByteCount(counter.get(), true), humanReadableByteCount(trackingInputStream.getCounter()), PERIOD_FORMATTER.print(period));
    }

    public static String humanReadableByteCount(long bytes) {
        return humanReadableByteCount(bytes, false);
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return si ? String.valueOf(bytes) : bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "" : " ") + (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "iB");
        return String.format("%.1f%s", bytes / Math.pow(unit, exp), pre);
    }

    private static class TrackingInputStream extends InputStream {

        private InputStream delegate;

        private long counter;

        private TrackingInputStream(InputStream delegate) {
            this.delegate = delegate;
        }

        public long getCounter() {
            return counter;
        }

        @Override
        public int read() throws IOException {
            int result = delegate.read();
            if (result != -1) {
                counter++;
            }
            return result;
        }

        @Override
        public int read(byte[] b) throws IOException {
            int result = delegate.read(b);
            if (result != -1) {
                counter += result;
            }
            return result;
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            int result = delegate.read(b, off, len);
            if (result != -1) {
                counter += result;
            }
            return result;
        }

        @Override
        public long skip(long n) throws IOException {
            long result = delegate.skip(n);
            if (result != -1) {
                counter += result;
            }
            return result;
        }

        @Override
        public int available() throws IOException {
            return delegate.available();
        }

        @Override
        public synchronized void mark(int readlimit) {
            delegate.mark(readlimit);
        }

        @Override
        public void close() throws IOException {
            delegate.close();
        }

        @Override
        public synchronized void reset() throws IOException {
            delegate.reset();
            counter = 0;
        }

        @Override
        public boolean markSupported() {
            return delegate.markSupported();
        }
    }
}
