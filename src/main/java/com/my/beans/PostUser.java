package com.my.beans;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/15.
 */
@Entity
@Table(name = "POSTUSER",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class PostUser {
    private int userid;
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 2,max = 30,message = "用户名不能长度必须在{min}-{max}之间")
    private String username;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 3,max = 30,message = "密码不能长度必须在{min}-{max}之间")
    private String password;
    /*@NotNull
    @Min(1)*/
    private int sex;
    private int age;
    @NotEmpty(message = "邮箱不能为空")
    private String email;
    private String face="/img/face.jpg";
    private String cdkey;
    private int cdkeystate;
    private Timestamp cdkeytime;
    private Set<Comments> comments;
    private Set<Relations> fans;
    private Set<Relations> participant;
    private Set<Post> posts;
    @Id
    @GeneratedValue
    @Column(name = "USERID")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name="USERNAME")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }




    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Basic
    @Column(name = "SEX")
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "AGE")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "FACE")
    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    @Basic
    @Column(name = "CDKEY")
    public String getCdkey() {
        return cdkey;
    }

    public void setCdkey(String cdkey) {
        this.cdkey = cdkey;
    }

    @Basic
    @Column(name = "CDKEYSTATE")
    public int getCdkeystate() {
        return cdkeystate;
    }

    public void setCdkeystate(int cdkeystate) {
        this.cdkeystate = cdkeystate;
    }

    @Basic
    @Column(name = "CDKEYTIME")
    public Timestamp getCdkeytime() {
        return cdkeytime;
    }

    public void setCdkeytime(Timestamp cdkeytime) {
        this.cdkeytime = cdkeytime;
    }





    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    public Set<Comments> getComments() {
        return comments;
    }

    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "by_user",cascade = CascadeType.ALL)
    @Where(clause = "state=0")
    public Set<Relations> getFans() {
        return fans;
    }

    public void setFans(Set<Relations> fans) {
        this.fans = fans;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Where(clause = "state=0")
    public Set<Relations> getParticipant() {
        return participant;
    }

    public void setParticipant(Set<Relations> participant) {
        this.participant = participant;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @OrderBy("pid desc")
    @Where(clause = "state=0")
     public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "PostUser{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", face='" + face + '\'' +
                ", cdkey='" + cdkey + '\'' +
                ", cdkeystate=" + cdkeystate +
                ", cdkeytime=" + cdkeytime +
                ", comments=" + comments +
                '}';
    }
}
