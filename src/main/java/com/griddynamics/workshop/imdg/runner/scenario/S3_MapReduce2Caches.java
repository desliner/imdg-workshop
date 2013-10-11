package com.griddynamics.workshop.imdg.runner.scenario;

import com.griddynamics.workshop.imdg.domain.stackoverflow.model.Post;
import com.griddynamics.workshop.imdg.domain.stackoverflow.model.User;
import com.griddynamics.workshop.imdg.runner.Client;
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
public class S3_MapReduce2Caches extends Client {

    @Override
    protected void run() {
        NamedCache postsCache = CacheFactory.getCache("posts");
        NamedCache usersCache = CacheFactory.getCache("users");

        Set userIds = usersCache.keySet(new EqualsFilter(User.EXTRACTOR_AGE, 21));
        log.info("Users of age 21: {}", userIds.size());

        Double viewCountAverage = (Double) postsCache.aggregate(new InFilter(Post.EXTRACTOR_OWNER_USER_ID, userIds), new DoubleAverage(Post.EXTRACTOR_VIEW_COUNT));
        log.info("Average views on post of users of age 21: {}", viewCountAverage);
    }
}
