package com.nchu.blogmx.bean;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_account")
public class Account {

    @Id
    @GeneratedValue
    private long id;

    private String accountname;

    private String username;

    private String password;

    private String eamil;

    private String type;

    private String avator;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "account")
    private List<Blog> blogs = new ArrayList<>();

    public Account() {
    }

    public Account(String accountname, String username, String password, String eamil, String type, String avator,
                   Date createTime, Date updateTime, List<Blog> blogs) {
        this.accountname = accountname;
        this.username = username;
        this.password = password;
        this.eamil = eamil;
        this.type = type;
        this.avator = avator;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountname='" + accountname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", eamil='" + eamil + '\'' +
                ", type='" + type + '\'' +
                ", avator='" + avator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", blogs=" + blogs +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
