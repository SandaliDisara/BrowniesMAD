package com.example.madprojectbrownies;


public class UsersItem {

    String userID;
    String userName;
    String userPhone;
    String userAddress;
    String userNote;

    public UsersItem() {
    }

    public UsersItem(String userID, String userName, String userPhone, String userAddress, String userNote) {
        this.userID = userID;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userNote = userNote;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}
