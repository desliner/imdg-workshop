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
