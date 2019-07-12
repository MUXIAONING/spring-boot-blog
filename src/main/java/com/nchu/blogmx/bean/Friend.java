package com.nchu.blogmx.bean;

import javax.persistence.*;

@Entity
@Table(name = "t_friend")
public class Friend {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;//显示的名字

    private String occupation; //目前的职位

    private String description;   //关于站长的描述

    @Basic(fetch = FetchType.LAZY)  //懒加载
    @Lob
    private String avatar;   //头像

    private String flag;//大佬，朋友，网站

    private String website; //网站地址

    public Friend() {
    }

    public Friend(String nickname, String occupation, String description, String avatar, String flag, String website) {
        this.nickname = nickname;
        this.occupation = occupation;
        this.description = description;
        this.avatar = avatar;
        this.flag = flag;
        this.website = website;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", occupation='" + occupation + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", flag='" + flag + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
