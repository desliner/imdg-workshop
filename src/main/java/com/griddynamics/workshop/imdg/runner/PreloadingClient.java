package com.griddynamics.workshop.imdg.runner;

import com.griddynamics.workshop.imdg.domain.stackoverflow.load.PostLoader;
import com.griddynamics.workshop.imdg.domain.stackoverflow.load.UserLoader;
import com.griddynamics.workshop.imdg.domain.stackoverflow.model.Post;
import com.griddynamics.workshop.imdg.domain.stackoverflow.model.User;
import com.griddynamics.workshop.imdg.preloader.PreloadWorker;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.extractor.ReflectionExtractor;

import java.io.File;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/1/13
 */
public class PreloadingClient extends Client {

    @Override
    protected void run() {
        NamedCache usersCache = CacheFactory.getCache("users");
        NamedCache postsCache = CacheFactory.getCache("posts");

        usersCache.addIndex(new ReflectionExtractor("getAge"), true, null);

        postsCache.addIndex(new ReflectionExtractor("getOwnerUserId"), false, null);
        postsCache.addIndex(new ReflectionExtractor("getCreationDate"), true, null);

        preloadAll(usersCache, postsCache);
    }

    private void preloadAll(NamedCache usersCache, NamedCache postsCache) {
        try {
            new PreloadWorker<User>().preload(
                    new File("e:\\Downloads\\Stack Exchange Data Dump - Jun 2013\\Content\\stackoverflow.com\\Users.xml"),
                    usersCache,
                    new UserLoader(),
                    1.0);

            new PreloadWorker<Post>().preload(
                    new File("e:\\Downloads\\Stack Exchange Data Dump - Jun 2013\\Content\\stackoverflow.com\\Posts.xml"),
                    postsCache,
                    new PostLoader(),
                    0.5);
        } catch (Exception e) {
            log.error("Error while preloading", e);
        }
    }

    public static void main(String[] args) {
        new PreloadingClient().main();
    }
}
