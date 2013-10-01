package com.griddynamics.workshop.imdg.domain.stackoverflow.model;

import com.griddynamics.workshop.imdg.common.model.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/30/13
 */
public class Post implements Entity<Integer>, Serializable {
    private int id;
    private int postTypeId;
    private Integer acceptedAnswerId;
    private Integer parentId;
    private Date creationDate;
    private int score;
    private int viewCount;
    private String body;
    private Integer ownerUserId;
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
}
