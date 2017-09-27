package com.my.beans;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/2/15.
 */
@Entity
public class Relations {
    private long rid;
    private Long state = (long)0;
    private PostUser by_user;
    private PostUser user;

    @Id
    @GeneratedValue
    @Column(name = "RID")
    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
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

        Relations relations = (Relations) o;

        if (rid != relations.rid) return false;
        if (state != null ? !state.equals(relations.state) : relations.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rid ^ (rid >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = PostUser.class)
    @JoinColumn(name = "BY_PARTICIPANT", referencedColumnName = "USERID")
    public PostUser getBy_user() {
        return by_user;
    }

    public void setBy_user(PostUser by_user) {
        this.by_user = by_user;
    }

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = PostUser.class)
    @JoinColumn(name = "PARTICIPANT", referencedColumnName = "USERID")
    public PostUser getUser() {
        return user;
    }

    public void setUser(PostUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Relations{" +
                "rid=" + rid +
                ", state=" + state +
                ", by_user=" + by_user.toString() +
                ", user=" + user.toString() +
                '}';
    }
}
