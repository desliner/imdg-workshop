/*
 * Copyright (c) 2013 Grid Dynamics International, Inc. All Rights Reserved
 * http://www.griddynamics.com
 *
 * IMDG Workshop is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $Id: $
 * @Project:     IMDG Workshop
 * @Description: Demo application for IMDG based on Oracle Coherence
 */

package com.griddynamics.workshop.imdg.domain.common.load;

class ProgressTracker {

    private final long totalCount;
    private final long startTime;

    private long lastUpdateTime;
    private long remainingCount;
    private long completedCount;
    private double completedPercentage;
    private double averageSpeed;
    private long remainingTime;

    private long completedCountRecords;
    private double averageSpeedRecords;

    public ProgressTracker(long totalCount) {
        this.totalCount = totalCount;
        this.startTime = System.currentTimeMillis();
        this.lastUpdateTime = this.startTime;
    }

    public void update(long completedCount, long completedCountRecords) {
        long difference = completedCount - this.completedCount;
        long differenceRecords = completedCountRecords - this.completedCountRecords;

        this.completedCountRecords = completedCountRecords;
        this.completedCount = completedCount;
        this.remainingCount = totalCount - completedCount;
        this.completedPercentage = (double) completedCount / totalCount * 100;

        long currentTime = System.currentTimeMillis();
        averageSpeed = ((double) difference) / ((double) (currentTime - lastUpdateTime)) * 1000;
        averageSpeedRecords = ((double) differenceRecords) / ((double) (currentTime - lastUpdateTime)) * 1000;
        if (Math.abs(averageSpeed) > 1e-6) {
            remainingTime = (long) (remainingCount / averageSpeed);
        } else {
            remainingTime = Integer.MAX_VALUE;
        }
        lastUpdateTime = currentTime;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public long getCompletedCount() {
        return completedCount;
    }

    public double getCompletedPercentage() {
        return completedPercentage;
    }

    public long getRemainingCount() {
        return remainingTime;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getCompletedCountRecords() {
        return completedCountRecords;
    }

    public double getAverageSpeedRecords() {
        return averageSpeedRecords;
    }
}
