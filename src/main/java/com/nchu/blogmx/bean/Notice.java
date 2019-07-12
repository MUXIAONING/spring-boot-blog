package com.nchu.blogmx.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_notice")
public class Notice {

    @Id
    @GeneratedValue
    private long id;

    private String completed;

    private String uncompleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getUncompleted() {
        return uncompleted;
    }

    public void setUncompleted(String uncompleted) {
        this.uncompleted = uncompleted;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", completed='" + completed + '\'' +
                ", uncompleted='" + uncompleted + '\'' +
                '}';
    }

    public Notice() {
        
    }

    public Notice(String completed, String uncompleted) {
        this.completed = completed;
        this.uncompleted = uncompleted;
    }
}
