package com.griddynamics.workshop.imdg.runner;

import com.griddynamics.workshop.imdg.domain.stackoverflow.load.PostLoader;
import com.griddynamics.workshop.imdg.domain.stackoverflow.load.UserLoader;
import com.griddynamics.workshop.imdg.domain.stackoverflow.model.Post;
import com.griddynamics.workshop.imdg.domain.stackoverflow.model.User;
import com.griddynamics.workshop.imdg.preloader.PreloadWorker;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

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

        usersCache.addIndex(User.EXTRACTOR_AGE, true, null);

        postsCache.addIndex(Post.EXTRACTOR_OWNER_USER_ID, false, null);
        postsCache.addIndex(Post.EXTRACTOR_CREATION_DATE, true, null);

        preloadAll(usersCache, postsCache);
    }

    private void preloadAll(NamedCache usersCache, NamedCache postsCache) {
        try {
            String path = System.getProperty("imdg.dataPath", "e:\\Downloads\\Stack Exchange Data Dump - Jun 2013\\Content\\stackoverflow.com\\");
            File pathFile = new File(path);
            new PreloadWorker<User>().preload(
                    new File(pathFile, "Users.xml.gz"),
                    usersCache,
                    new UserLoader(),
                    1.0);

            new PreloadWorker<Post>().preload(
                    new File(pathFile, "Posts.xml.gz"),
                    postsCache,
                    new PostLoader(),
                    1.0);
        } catch (Exception e) {
            log.error("Error while preloading", e);
        }
    }
}
