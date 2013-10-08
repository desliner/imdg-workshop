package com.griddynamics.workshop.imdg.runner.scenario;

import com.griddynamics.workshop.imdg.runner.ScenarioClient;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.aggregator.ComparableMax;
import com.tangosol.util.aggregator.ComparableMin;
import com.tangosol.util.aggregator.LongMax;
import com.tangosol.util.filter.AlwaysFilter;
import com.tangosol.util.filter.AndFilter;
import com.tangosol.util.filter.GreaterFilter;
import com.tangosol.util.filter.LessFilter;
import org.joda.time.LocalDateTime;

import java.util.Date;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/3/13
 */
public class MapReduce1Cache extends ScenarioClient {

    @Override
    protected void run() {
        NamedCache postsCache = CacheFactory.getCache("posts");

        Date minDate = (Date) postsCache.aggregate(AlwaysFilter.INSTANCE, new ComparableMin("getCreationDate"));
        log.info("Minimum post creation date: {}", minDate);

        Date maxDate = (Date) postsCache.aggregate(AlwaysFilter.INSTANCE, new ComparableMax("getCreationDate"));
        log.info("Maximum post creation date: {}", maxDate);

        Date start = new LocalDateTime(2010, 1, 1, 0, 0).toDate();
        Date end = new LocalDateTime(2010, 12, 31, 23, 59).toDate();

        Long maxScore = (Long) postsCache.aggregate(
                new AndFilter(
                        new GreaterFilter("getCreationDate", start),
                        new LessFilter("getCreationDate", end)
                ),
                new LongMax("getScore"));

        log.info("Max score of posts created between {} and {}: {}", start, end, maxScore);
    }
}
