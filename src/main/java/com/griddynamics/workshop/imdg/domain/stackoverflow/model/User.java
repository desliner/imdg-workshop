package com.griddynamics.workshop.imdg.domain.stackoverflow.model;

import com.griddynamics.workshop.imdg.domain.common.model.Entity;

import java.util.Date;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/30/13
 */
public class User implements Entity<Integer> {
    private int id;
    private int reputation;
    private Date creationDate;
    private String displayName;
    private Date lastAccessDate;
    private String websiteUrl;
    private String location;
    private String aboutMe;
    private int views;
    private int upVotes;
    private int downVotes;
    private String emailHash;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", reputation=" + reputation +
                ", creationDate=" + creationDate +
                ", lastAccessDate=" + lastAccessDate +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", location='" + location + '\'' +
                ", views=" + views +
                ", upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                ", emailHash='" + emailHash + '\'' +
                ", age=" + age +
                '}';
    }
}
