package com.griddynamics.workshop.imdg.runner.scenario;

import com.griddynamics.workshop.imdg.runner.ScenarioClient;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.aggregator.DoubleAverage;
import com.tangosol.util.filter.EqualsFilter;
import com.tangosol.util.filter.InFilter;

import java.util.Set;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/3/13
 */
public class MapReduce2Caches extends ScenarioClient {

    @Override
    protected void run() {
        NamedCache postsCache = CacheFactory.getCache("posts");
        NamedCache usersCache = CacheFactory.getCache("users");

        Set userIds = usersCache.keySet(new EqualsFilter("getAge", 21));
        log.info("Users of age 21: {}", userIds.size());

        Double viewCountAverage = (Double) postsCache.aggregate(new InFilter("getOwnerUserId", userIds), new DoubleAverage("getViewCount"));
        log.info("Average views on post of users of age 21: {}", viewCountAverage);
    }
}
