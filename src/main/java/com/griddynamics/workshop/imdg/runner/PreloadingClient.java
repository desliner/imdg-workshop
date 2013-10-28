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
