package com.my.beans;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/2/15.
 */
@Entity
public class Comments {
    private long cid;
    private String content;
    private Timestamp commenttime;
    private Post post;
    private PostUser user;

    @Id
    @Column(name = "CID")
    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "COMMENTTIME")
    public Timestamp getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Timestamp commenttime) {
        this.commenttime = commenttime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comments comments = (Comments) o;

        if (cid != comments.cid) return false;
        if (content != null ? !content.equals(comments.content) : comments.content != null) return false;
        if (commenttime != null ? !commenttime.equals(comments.commenttime) : comments.commenttime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (cid ^ (cid >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (commenttime != null ? commenttime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "PID", referencedColumnName = "PID")
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @ManyToOne
    @JoinColumn(name = "COMMENTUSER", referencedColumnName = "USERID")
    public PostUser getUser() {
        return user;
    }

    public void setUser(PostUser user) {
        this.user = user;
    }
}
