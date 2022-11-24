package com.example.wechat.model;

public class Users {
    String userName,email,profilepic, password, lastmessage, userID;

    public Users(String userName, String email, String profilepic, String password, String lastmessage, String userID) {
        this.userName = userName;
        this.email = email;
        this.profilepic = profilepic;
        this.password = password;
        this.lastmessage = lastmessage;
        this.userID = userID;
    }

    public Users(){}

    public Users(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
