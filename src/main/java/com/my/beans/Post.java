package com.my.beans;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/15.
 */
@Entity
public class Post {
    private long pid;
    private String content;
    private Timestamp sendtime;
    private String picture;
    private Long forwardingcount = (long)0;
    private Long state = (long)0;
    private Set<Comments> comments;
    private PostUser user;

    @Id
    @GeneratedValue
    @Column(name = "PID")
    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
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
    @Column(name = "SENDTIME")
    public Timestamp getSendtime() {
        return sendtime;
    }

    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
    }

    @Basic
    @Column(name = "PICTURE")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "FORWARDINGCOUNT")
    public Long getForwardingcount() {
        return forwardingcount;
    }

    public void setForwardingcount(Long forwardingcount) {
        this.forwardingcount = forwardingcount;
    }

    @Basic
    @Column(name = "STATE")
    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post weibo = (Post) o;

        if (pid != weibo.pid) return false;
        if (content != null ? !content.equals(weibo.content) : weibo.content != null) return false;
        if (sendtime != null ? !sendtime.equals(weibo.sendtime) : weibo.sendtime != null) return false;
        if (picture != null ? !picture.equals(weibo.picture) : weibo.picture != null) return false;
        if (forwardingcount != null ? !forwardingcount.equals(weibo.forwardingcount) : weibo.forwardingcount != null)
            return false;
        if (state != null ? !state.equals(weibo.state) : weibo.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (pid ^ (pid >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (sendtime != null ? sendtime.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (forwardingcount != null ? forwardingcount.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Weibo{" +
                "wid=" + pid +
                ", content='" + content + '\'' +
                ", sendtime=" + sendtime +
                ", picture='" + picture + '\'' +
                ", forwardingcount=" + forwardingcount +
                ", state=" + state +
                ", comments=" + comments +
                ", user=" + user +
                '}';
    }

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    public Set<Comments> getComments() {
        return comments;
    }

    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PostUser", referencedColumnName = "USERID")
    public PostUser getUser() {
        return user;
    }

    public void setUser(PostUser user) {
        this.user = user;
    }
}
