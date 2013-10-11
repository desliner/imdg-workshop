package com.griddynamics.workshop.imdg.domain.stackoverflow.model;

import com.griddynamics.workshop.imdg.domain.common.model.Entity;
import com.griddynamics.workshop.imdg.util.CompressingStringCodec;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.PofExtractor;

import java.io.IOException;
import java.util.Date;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/30/13
 */
public class User implements Entity<Integer>, PortableObject {
    public static final int POF_INDEX_ID;
    public static final int POF_INDEX_REPUTATION;
    public static final int POF_INDEX_CREATION_DATE;
    public static final int POF_INDEX_DISPLAY_NAME;
    public static final int POF_INDEX_LAST_ACCESS_DATE;
    public static final int POF_INDEX_WEBSITE_URL;
    public static final int POF_INDEX_LOCATION;
    public static final int POF_INDEX_ABOUT_ME;
    public static final int POF_INDEX_VIEWS;
    public static final int POF_INDEX_UP_VOTES;
    public static final int POF_INDEX_DOWN_VOTES;
    public static final int POF_INDEX_EMAIL_HASH;
    public static final int POF_INDEX_AGE;

    public static final ValueExtractor EXTRACTOR_AGE;

    static {
        int i = 0;
        POF_INDEX_ID = i++;
        POF_INDEX_REPUTATION = i++;
        POF_INDEX_CREATION_DATE = i++;
        POF_INDEX_DISPLAY_NAME = i++;
        POF_INDEX_LAST_ACCESS_DATE = i++;
        POF_INDEX_WEBSITE_URL = i++;
        POF_INDEX_LOCATION = i++;
        POF_INDEX_ABOUT_ME = i++;
        POF_INDEX_VIEWS = i++;
        POF_INDEX_UP_VOTES = i++;
        POF_INDEX_DOWN_VOTES = i++;
        POF_INDEX_EMAIL_HASH = i++;
        POF_INDEX_AGE = i++;

        EXTRACTOR_AGE = new PofExtractor(int.class, POF_INDEX_AGE);
    }
    
    
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
    private int age;

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

    @Override
    public void readExternal(PofReader pofreader) throws IOException {
        id = pofreader.readInt(POF_INDEX_ID);
        reputation = pofreader.readInt(POF_INDEX_REPUTATION);
        creationDate = pofreader.readDate(POF_INDEX_CREATION_DATE);
        displayName = pofreader.readString(POF_INDEX_DISPLAY_NAME);
        lastAccessDate = pofreader.readDate(POF_INDEX_LAST_ACCESS_DATE);
        websiteUrl = pofreader.readString(POF_INDEX_WEBSITE_URL);
        location = pofreader.readString(POF_INDEX_LOCATION);
        aboutMe = CompressingStringCodec.decompress(pofreader.readByteArray(POF_INDEX_ABOUT_ME));
        views = pofreader.readInt(POF_INDEX_VIEWS);
        upVotes = pofreader.readInt(POF_INDEX_UP_VOTES);
        downVotes = pofreader.readInt(POF_INDEX_DOWN_VOTES);
        emailHash = pofreader.readString(POF_INDEX_EMAIL_HASH);
        age = pofreader.readInt(POF_INDEX_AGE);
    }

    @Override
    public void writeExternal(PofWriter pofwriter) throws IOException {
        pofwriter.writeInt(POF_INDEX_ID, id);
        pofwriter.writeInt(POF_INDEX_REPUTATION, reputation);
        pofwriter.writeDate(POF_INDEX_CREATION_DATE, creationDate);
        pofwriter.writeString(POF_INDEX_DISPLAY_NAME, displayName);
        pofwriter.writeDate(POF_INDEX_LAST_ACCESS_DATE, lastAccessDate);
        pofwriter.writeString(POF_INDEX_WEBSITE_URL, websiteUrl);
        pofwriter.writeString(POF_INDEX_LOCATION, location);
        pofwriter.writeByteArray(POF_INDEX_ABOUT_ME, CompressingStringCodec.compress(aboutMe));
        pofwriter.writeInt(POF_INDEX_VIEWS, views);
        pofwriter.writeInt(POF_INDEX_UP_VOTES, upVotes);
        pofwriter.writeInt(POF_INDEX_DOWN_VOTES, downVotes);
        pofwriter.writeString(POF_INDEX_EMAIL_HASH, emailHash);
        pofwriter.writeInt(POF_INDEX_AGE, age);
    }
}
