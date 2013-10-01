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
