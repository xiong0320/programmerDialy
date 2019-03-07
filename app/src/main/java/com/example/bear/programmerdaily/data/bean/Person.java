package com.example.bear.programmerdaily.data.bean;

/**
 * Created by bear on 2018/9/15.
 */

public class Person {
    private boolean isTeacher;
    private String userName;
    private String password;
    private String profileUri;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileUri() {
        return profileUri;
    }

    public void setProfileUri(String profileUri) {
        this.profileUri = profileUri;
    }

    public Person(boolean isTeacher, String userName, String password) {
        this.isTeacher = isTeacher;
        this.userName = userName;
        this.password = password;
        this.email = "12345678@163.com";
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
