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
import com.griddynamics.workshop.imdg.domain.stackoverflow.model.Post;

import java.util.Date;
import java.util.Map;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/30/13
 */
public class PostLoader extends AbstractLoader<Post> {

    @Override
    protected Post newInstance() {
        return new Post();
    }

    protected void initSetters(Map<String, ValueSetter<Post>> setters) {
        setters.put("Id", new IntValueSetter<Post>() {
            @Override
            public void set(Post post, int value) {
                post.setId(value);
            }
        });
        setters.put("PostTypeId", new IntValueSetter<Post>() {
            @Override
            public void set(Post post, int value) {
                post.setPostTypeId(value);
            }
        });
        setters.put("AcceptedAnswerId", new IntValueSetter<Post>() {
            @Override
            public void set(Post target, int value) {
                target.setAcceptedAnswerId(value);
            }
        });
        setters.put("ParentId", new IntValueSetter<Post>() {
            @Override
            public void set(Post target, int value) {
                target.setParentId(value);
            }
        });
        setters.put("CreationDate", new DateValueSetter<Post>() {
            @Override
            public void set(Post post, Date value) {
                post.setCreationDate(value);
            }
        });
        setters.put("Score", new IntValueSetter<Post>() {
            @Override
            public void set(Post target, int value) {
                target.setScore(value);
            }
        });
        setters.put("ViewCount", new IntValueSetter<Post>() {
            @Override
            public void set(Post target, int value) {
                target.setViewCount(value);
            }
        });
        setters.put("Body", new ValueSetter<Post>() {
            @Override
            public void set(Post target, String value) {
                target.setBody(value);
            }
        });
        setters.put("OwnerUserId", new IntValueSetter<Post>() {
            @Override
            public void set(Post target, int value) {
                target.setOwnerUserId(value);
            }
        });
        setters.put("OwnerDisplayName", new ValueSetter<Post>() {
            @Override
            public void set(Post target, String value) {
                target.setOwnerDisplayName(value);
            }
        });
        setters.put("LastEditorUserId", new IntValueSetter<Post>() {
            @Override
            public void set(Post target, int value) {
                target.setLastEditorUserId(value);
            }
        });
        setters.put("LastEditorDisplayName", new ValueSetter<Post>() {
            @Override
            public void set(Post target, String value) {
                target.setLastEditorDisplayName(value);
            }
        });
        setters.put("LastEditDate", new DateValueSetter<Post>() {
            @Override
            public void set(Post target, Date value) {
                target.setLastEditDate(value);
            }
        });
        setters.put("LastActivityDate", new DateValueSetter<Post>() {
            @Override
            public void set(Post target, Date value) {
                target.setLastActivityDate(value);
            }
        });
        setters.put("Title", new ValueSetter<Post>() {
            @Override
            public void set(Post target, String value) {
                target.setTitle(value);
            }
        });
        setters.put("Tags", new ValueSetter<Post>() {
            @Override
            public void set(Post target, String value) {
                target.setTags(TagsDeserializer.deserialize(value));
            }
        });
        setters.put("AnswerCount", new IntValueSetter<Post>() {
            @Override
            public void set(Post target, int value) {
                target.setAnswerCount(value);
            }
        });
        setters.put("CommentCount", new IntValueSetter<Post>() {
            @Override
            public void set(Post target, int value) {
                target.setCommentCount(value);
            }
        });
        setters.put("FavoriteCount", new IntValueSetter<Post>() {
            @Override
            public void set(Post target, int value) {
                target.setFavoriteCount(value);
            }
        });
        setters.put("ClosedDate", new DateValueSetter<Post>() {
            @Override
            public void set(Post target, Date value) {
                target.setClosedDate(value);
            }
        });
        setters.put("CommunityOwnedDate", new DateValueSetter<Post>() {
            @Override
            public void set(Post target, Date value) {
                target.setCommunityOwnedDate(value);
            }
        });
    }

}
