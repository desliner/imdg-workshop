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

package com.griddynamics.workshop.imdg.domain.stackoverflow.load;

import com.griddynamics.workshop.imdg.domain.stackoverflow.load.setter.DateValueSetter;
import com.griddynamics.workshop.imdg.domain.stackoverflow.load.setter.IntValueSetter;
import com.griddynamics.workshop.imdg.domain.stackoverflow.load.setter.ValueSetter;
import com.griddynamics.workshop.imdg.domain.stackoverflow.model.User;

import java.util.Date;
import java.util.Map;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/2/13
 */
public class UserLoader extends AbstractLoader<User> {

    @Override
    protected User newInstance() {
        return new User();
    }

    @Override
    protected void initSetters(Map<String, ValueSetter<User>> setters) {
        setters.put("Id", new IntValueSetter<User>() {
            @Override
            public void set(User target, int value) {
                target.setId(value);
            }
        });
        setters.put("CreationDate", new DateValueSetter<User>() {
            @Override
            public void set(User target, Date value) {
                target.setCreationDate(value);
            }
        });
        setters.put("Reputation", new IntValueSetter<User>() {
            @Override
            public void set(User target, int value) {
                target.setReputation(value);
            }
        });
        setters.put("DisplayName", new ValueSetter<User>() {
            @Override
            public void set(User target, String value) {
                target.setDisplayName(value);
            }
        });
        setters.put("LastAccessDate", new DateValueSetter<User>() {
            @Override
            public void set(User target, Date value) {
                target.setLastAccessDate(value);
            }
        });
        setters.put("WebsiteUrl", new ValueSetter<User>() {
            @Override
            public void set(User target, String value) {
                target.setWebsiteUrl(value);
            }
        });
        setters.put("Location", new ValueSetter<User>() {
            @Override
            public void set(User target, String value) {
                target.setLocation(value);
            }
        });
        setters.put("AboutMe", new ValueSetter<User>() {
            @Override
            public void set(User target, String value) {
                target.setAboutMe(value);
            }
        });
        setters.put("Views", new IntValueSetter<User>() {
            @Override
            public void set(User target, int value) {
                target.setViews(value);
            }
        });
        setters.put("UpVotes", new IntValueSetter<User>() {
            @Override
            public void set(User target, int value) {
                target.setUpVotes(value);
            }
        });
        setters.put("DownVotes", new IntValueSetter<User>() {
            @Override
            public void set(User target, int value) {
                target.setDownVotes(value);
            }
        });
        setters.put("EmailHash", new ValueSetter<User>() {
            @Override
            public void set(User target, String value) {
                target.setEmailHash(value);
            }
        });
        setters.put("Age", new IntValueSetter<User>() {
            @Override
            public void set(User target, int value) {
                target.setAge(value);
            }
        });
    }
}
