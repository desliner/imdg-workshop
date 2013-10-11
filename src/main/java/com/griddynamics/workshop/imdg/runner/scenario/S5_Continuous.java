package com.griddynamics.workshop.imdg.runner.scenario;

import com.griddynamics.workshop.imdg.domain.stackoverflow.model.User;
import com.griddynamics.workshop.imdg.runner.Client;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.net.cache.ContinuousQueryCache;
import com.tangosol.util.filter.EqualsFilter;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/11/13
 */
public class S5_Continuous extends Client {

    @Override
    protected void run() {
        NamedCache usersCache = CacheFactory.getCache("users");

        log.info("Setting up Continuous cache over users with age of 20");
        NamedCache continuousUsersCache = new ContinuousQueryCache(usersCache, new EqualsFilter(User.EXTRACTOR_AGE, 20));
        log.info("Users in Continuous cache: {}", continuousUsersCache.size());

        User newUser = new User();
        newUser.setId(-1);
        newUser.setAge(20);
        usersCache.put(newUser.getId(), newUser);
        log.info("Added one more user with age 20");

        log.info("Now users in Continuous cache: {}", continuousUsersCache.size());

        long time = System.currentTimeMillis();
        User user = (User) continuousUsersCache.get(newUser.getId());
        time = System.currentTimeMillis() - time;
        log.info("Get from Continuous completed in {} ms", time);

        usersCache.remove(newUser.getId());
    }
}
