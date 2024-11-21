package org.example.htmlfx.user;

public class Users {
    private String email;
    private String phone;
    private int userID;
    private String username;
    private String sex;
    private String birthday;

    public Users() {

    }

    public Users(String email, String phone, int userID, String username) {
        this.email = email;
        this.phone = phone;
        this.userID = userID;
        this.username = username;
    }

    public Users(String email, String phone, int userID,  String username, String sex, String birthday) {
        this.email = email;
        this.phone = phone;
        this.userID = userID;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
