package com.griddynamics.workshop.imdg.runner.scenario;

import com.griddynamics.workshop.imdg.domain.stackoverflow.model.Post;
import com.griddynamics.workshop.imdg.domain.stackoverflow.model.User;
import com.griddynamics.workshop.imdg.runner.Client;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.filter.EqualsFilter;

import java.util.Collection;
import java.util.Map;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/11/13
 */
public class S1_GetQueryPut extends Client {

    @Override
    protected void run() {
        NamedCache postsCache = CacheFactory.getCache("posts");
        NamedCache usersCache = CacheFactory.getCache("users");

        Post post = (Post) postsCache.get(17);
        log.info("Post: {}", post);

        User owner = (User) usersCache.get(post.getOwnerUserId());
        log.info("Owner: {}", owner);

        Collection<Map.Entry<Integer, User>> users = usersCache.entrySet(new EqualsFilter(User.EXTRACTOR_AGE, owner.getAge()));
        log.info("Number of users with same age ({}): {}", owner.getAge(), users.size());

        int newUpVotes = owner.getUpVotes() + users.size();
        owner.setUpVotes(newUpVotes);
        usersCache.put(owner.getId(), owner);
        log.info("User {} now has {} up votes", owner.getId(), newUpVotes);
    }
}
