package com.soft.bootdemo1.dto;

import com.sun.xml.internal.ws.developer.Serialization;

/**
 * Created by Dell on 6/2/2020.
 */
@Serialization
public class OutsideUser {
    private int userId;
    private int id;
    private String title;
    private String body;

    public OutsideUser() {}

    public OutsideUser(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
