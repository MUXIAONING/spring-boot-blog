package com.nchu.blogmx.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_message")
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    private String touristName;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Message() {

    }

    public Message(String touristName, String content, Date createTime) {
        this.touristName = touristName;
        this.content = content;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", touristName='" + touristName + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
