package com.griddynamics.workshop.imdg.domain.stackoverflow.model;

import com.griddynamics.workshop.imdg.domain.common.model.Entity;
import com.griddynamics.workshop.imdg.util.CompressingStringCodec;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.PofExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/30/13
 */
public class Post implements Entity<Integer>, PortableObject {
    public static final int POF_INDEX_ID;
    public static final int POF_INDEX_POST_TYPE_ID;
    public static final int POF_INDEX_ACCEPTED_ANSWER_ID;
    public static final int POF_INDEX_PARENT_ID;
    public static final int POF_INDEX_CREATION_DATE;
    public static final int POF_INDEX_SCORE;
    public static final int POF_INDEX_VIEW_COUNT;
    public static final int POF_INDEX_BODY;
    public static final int POF_INDEX_OWNER_USER_ID;
    public static final int POF_INDEX_OWNER_DISPLAY_NAME;
    public static final int POF_INDEX_LAST_EDITOR_USER_ID;
    public static final int POF_INDEX_LAST_EDITOR_DISPLAY_NAME;
    public static final int POF_INDEX_LAST_EDIT_DATE;
    public static final int POF_INDEX_LAST_ACTIVITY_DATE;
    public static final int POF_INDEX_TITLE;
    public static final int POF_INDEX_TAGS;
    public static final int POF_INDEX_ANSWER_COUNT;
    public static final int POF_INDEX_COMMENT_COUNT;
    public static final int POF_INDEX_FAVORITE_COUNT;
    public static final int POF_INDEX_CLOSED_DATE;
    public static final int POF_INDEX_COMMUNITY_OWNED_DATE;

    public static final ValueExtractor EXTRACTOR_VIEW_COUNT;
    public static final ValueExtractor EXTRACTOR_OWNER_USER_ID;
    public static final ValueExtractor EXTRACTOR_CREATION_DATE;
    public static final ValueExtractor EXTRACTOR_SCORE;
    
    static {
        int i = 0;
        POF_INDEX_ID = i++;
        POF_INDEX_POST_TYPE_ID = i++;
        POF_INDEX_ACCEPTED_ANSWER_ID = i++;
        POF_INDEX_PARENT_ID = i++;
        POF_INDEX_CREATION_DATE = i++;
        POF_INDEX_SCORE = i++;
        POF_INDEX_VIEW_COUNT = i++;
        POF_INDEX_BODY = i++;
        POF_INDEX_OWNER_USER_ID = i++;
        POF_INDEX_OWNER_DISPLAY_NAME = i++;
        POF_INDEX_LAST_EDITOR_USER_ID = i++;
        POF_INDEX_LAST_EDITOR_DISPLAY_NAME = i++;
        POF_INDEX_LAST_EDIT_DATE = i++;
        POF_INDEX_LAST_ACTIVITY_DATE = i++;
        POF_INDEX_TITLE = i++;
        POF_INDEX_TAGS = i++;
        POF_INDEX_ANSWER_COUNT = i++;
        POF_INDEX_COMMENT_COUNT = i++;
        POF_INDEX_FAVORITE_COUNT = i++;
        POF_INDEX_CLOSED_DATE = i++;
        POF_INDEX_COMMUNITY_OWNED_DATE = i++;

        EXTRACTOR_VIEW_COUNT = new PofExtractor(int.class, POF_INDEX_VIEW_COUNT);
        EXTRACTOR_OWNER_USER_ID = new PofExtractor(int.class, POF_INDEX_OWNER_USER_ID);
        EXTRACTOR_CREATION_DATE = new PofExtractor(Date.class, POF_INDEX_CREATION_DATE);
        EXTRACTOR_SCORE = new PofExtractor(int.class, POF_INDEX_SCORE);
    }
    
    
    private int id;
    private int postTypeId; // TODO: enum
    private int acceptedAnswerId;
    private int parentId;
    private Date creationDate;
    private int score;
    private int viewCount;
    private String body;
    private int ownerUserId;
    private String ownerDisplayName;
    private int lastEditorUserId;
    private String lastEditorDisplayName;
    private Date lastEditDate;
    private Date lastActivityDate;
    private String title;
    private List<String> tags;
    private int answerCount;
    private int commentCount;
    private int favoriteCount;
    private Date closedDate;
    private Date communityOwnedDate;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(int postTypeId) {
        this.postTypeId = postTypeId;
    }

    public int getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public void setAcceptedAnswerId(int acceptedAnswerId) {
        this.acceptedAnswerId = acceptedAnswerId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getOwnerDisplayName() {
        return ownerDisplayName;
    }

    public void setOwnerDisplayName(String ownerDisplayName) {
        this.ownerDisplayName = ownerDisplayName;
    }

    public int getLastEditorUserId() {
        return lastEditorUserId;
    }

    public void setLastEditorUserId(int lastEditorUserId) {
        this.lastEditorUserId = lastEditorUserId;
    }

    public String getLastEditorDisplayName() {
        return lastEditorDisplayName;
    }

    public void setLastEditorDisplayName(String lastEditorDisplayName) {
        this.lastEditorDisplayName = lastEditorDisplayName;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getCommunityOwnedDate() {
        return communityOwnedDate;
    }

    public void setCommunityOwnedDate(Date communityOwnedDate) {
        this.communityOwnedDate = communityOwnedDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postTypeId=" + postTypeId +
                ", acceptedAnswerId=" + acceptedAnswerId +
                ", parentId=" + parentId +
                ", creationDate=" + creationDate +
                ", score=" + score +
                ", viewCount=" + viewCount +
                ", ownerUserId=" + ownerUserId +
                ", ownerDisplayName='" + ownerDisplayName + '\'' +
                ", lastEditorUserId=" + lastEditorUserId +
                ", lastEditorDisplayName='" + lastEditorDisplayName + '\'' +
                ", lastEditDate=" + lastEditDate +
                ", lastActivityDate=" + lastActivityDate +
                ", tags=" + tags +
                ", answerCount=" + answerCount +
                ", commentCount=" + commentCount +
                ", favoriteCount=" + favoriteCount +
                ", closedDate=" + closedDate +
                ", communityOwnedDate=" + communityOwnedDate +
                '}';
    }

    @Override
    public void readExternal(PofReader pofreader) throws IOException {
        id = pofreader.readInt(POF_INDEX_ID);
        postTypeId = pofreader.readInt(POF_INDEX_POST_TYPE_ID);
        acceptedAnswerId = pofreader.readInt(POF_INDEX_ACCEPTED_ANSWER_ID);
        parentId = pofreader.readInt(POF_INDEX_PARENT_ID);
        creationDate = pofreader.readDate(POF_INDEX_CREATION_DATE);
        score = pofreader.readInt(POF_INDEX_SCORE);
        viewCount = pofreader.readInt(POF_INDEX_VIEW_COUNT);
        body = CompressingStringCodec.decompress(pofreader.readByteArray(POF_INDEX_BODY));
        ownerUserId = pofreader.readInt(POF_INDEX_OWNER_USER_ID);
        ownerDisplayName = pofreader.readString(POF_INDEX_OWNER_DISPLAY_NAME);
        lastEditorUserId = pofreader.readInt(POF_INDEX_LAST_EDITOR_USER_ID);
        lastEditorDisplayName = pofreader.readString(POF_INDEX_LAST_EDITOR_DISPLAY_NAME);
        lastEditDate = pofreader.readDate(POF_INDEX_LAST_EDIT_DATE);
        lastActivityDate = pofreader.readDate(POF_INDEX_LAST_ACTIVITY_DATE);
        title = pofreader.readString(POF_INDEX_TITLE);
        tags = (List<String>) pofreader.readCollection(POF_INDEX_TAGS, new ArrayList());
        answerCount = pofreader.readInt(POF_INDEX_ANSWER_COUNT);
        commentCount = pofreader.readInt(POF_INDEX_COMMENT_COUNT);
        favoriteCount = pofreader.readInt(POF_INDEX_FAVORITE_COUNT);
        closedDate = pofreader.readDate(POF_INDEX_CLOSED_DATE);
        communityOwnedDate = pofreader.readDate(POF_INDEX_COMMUNITY_OWNED_DATE);
    }

    @Override
    public void writeExternal(PofWriter pofwriter) throws IOException {
        pofwriter.writeInt(POF_INDEX_ID, id);
        pofwriter.writeInt(POF_INDEX_POST_TYPE_ID, postTypeId);
        pofwriter.writeInt(POF_INDEX_ACCEPTED_ANSWER_ID, acceptedAnswerId);
        pofwriter.writeInt(POF_INDEX_PARENT_ID, parentId);
        pofwriter.writeDate(POF_INDEX_CREATION_DATE, creationDate);
        pofwriter.writeInt(POF_INDEX_SCORE, score);
        pofwriter.writeInt(POF_INDEX_VIEW_COUNT, viewCount);
        pofwriter.writeByteArray(POF_INDEX_BODY, CompressingStringCodec.compress(body));
        pofwriter.writeInt(POF_INDEX_OWNER_USER_ID, ownerUserId);
        pofwriter.writeString(POF_INDEX_OWNER_DISPLAY_NAME, ownerDisplayName);
        pofwriter.writeInt(POF_INDEX_LAST_EDITOR_USER_ID, lastEditorUserId);
        pofwriter.writeString(POF_INDEX_LAST_EDITOR_DISPLAY_NAME, lastEditorDisplayName);
        pofwriter.writeDate(POF_INDEX_LAST_EDIT_DATE, lastEditDate);
        pofwriter.writeDate(POF_INDEX_LAST_ACTIVITY_DATE, lastActivityDate);
        pofwriter.writeString(POF_INDEX_TITLE, title);
        pofwriter.writeCollection(POF_INDEX_TAGS, tags);
        pofwriter.writeInt(POF_INDEX_ANSWER_COUNT, answerCount);
        pofwriter.writeInt(POF_INDEX_COMMENT_COUNT, commentCount);
        pofwriter.writeInt(POF_INDEX_FAVORITE_COUNT, favoriteCount);
        pofwriter.writeDate(POF_INDEX_CLOSED_DATE, closedDate);
        pofwriter.writeDate(POF_INDEX_COMMUNITY_OWNED_DATE, communityOwnedDate);
    }
}
