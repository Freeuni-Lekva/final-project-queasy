package com.queasy.model.user;

public class User {
    private String userName;
    private String mail;
    private String password;

    public User(String userName, String mail, String password) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;

        if(other.userName.equals(this.userName) || other.mail.equals(this.mail)) {
            return true;
        }
        return false;
    }
}
