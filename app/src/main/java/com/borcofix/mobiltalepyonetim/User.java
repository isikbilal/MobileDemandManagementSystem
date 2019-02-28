package com.borcofix.mobiltalepyonetim;

/**
 * Created by ABRA-A5-V52 on 21.12.2017.
 */

public class User {

    String MemberID;
    String UserName;
    String UserPassword;
    String MemberName;  //kullanıcının gerçek adı
    String MemberSurname;  //kullanıcının gerçek soyadı
    String MemberEmail;

    public User() {

    }

    public String getMemberID() {return MemberID;}

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getMemberSurname() {
        return MemberSurname;
    }

    public void setMemberSurname(String memberSurname) {
        MemberSurname = memberSurname;
    }

    public String getMemberEmail() {
        return MemberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        MemberEmail = memberEmail;
    }


}
