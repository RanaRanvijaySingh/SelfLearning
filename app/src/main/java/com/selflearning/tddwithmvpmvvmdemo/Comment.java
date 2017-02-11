package com.selflearning.tddwithmvpmvvmdemo;

public class Comment {
    private String name;
    private String email;
    private String body;

    public Comment(String name, String email, String body) {
        this.name = name;
        this.body = body;
        this.email = email;
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
}
