package com.tarun.assignment.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.tarun.assignment.model.Comment;

import java.io.Serializable;

@Entity (tableName = "comments")
public class DbComment implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "postedId")
    private int postId;

    @ColumnInfo(name = "name")
    private String name;

    public DbComment() {}

    public DbComment(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPostId();
        this.name = comment.getName();
        this.email = comment.getEmail();
        this.body = comment.getBody();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "body")
    private String body;


}
