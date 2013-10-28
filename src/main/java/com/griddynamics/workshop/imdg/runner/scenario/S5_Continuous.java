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
