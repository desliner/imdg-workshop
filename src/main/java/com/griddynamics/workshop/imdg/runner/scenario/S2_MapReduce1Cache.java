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

package com.griddynamics.workshop.imdg.runner.scenario;

import com.griddynamics.workshop.imdg.domain.stackoverflow.model.Post;
import com.griddynamics.workshop.imdg.runner.Client;
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
public class S2_MapReduce1Cache extends Client {

    @Override
    protected void run() {
        NamedCache postsCache = CacheFactory.getCache("posts");

        log.info("Posts: {}", postsCache.size());


        Date minDate = (Date) postsCache.aggregate(AlwaysFilter.INSTANCE, new ComparableMin(Post.EXTRACTOR_CREATION_DATE));
        log.info("Minimum post creation date: {}", minDate);

        Date maxDate = (Date) postsCache.aggregate(AlwaysFilter.INSTANCE, new ComparableMax(Post.EXTRACTOR_CREATION_DATE));
        log.info("Maximum post creation date: {}", maxDate);

        Date start = new LocalDateTime(2010, 1, 1, 0, 0).toDate();
        Date end = new LocalDateTime(2011, 1, 1, 0, 0).toDate();

        Long maxScore = (Long) postsCache.aggregate(
                new AndFilter(
                        new GreaterFilter(Post.EXTRACTOR_CREATION_DATE, start),
                        new LessFilter(Post.EXTRACTOR_CREATION_DATE, end)
                ),
                new LongMax(Post.EXTRACTOR_SCORE));

        log.info("Max score of posts created between {} and {}: {}", start, end, maxScore);
    }
}
