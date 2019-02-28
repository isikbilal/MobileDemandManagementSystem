package com.borcofix.mobiltalepyonetim;

/**
 * Created by ABRA-A5-V52 on 30.11.2017.
 */

public class Entry {

    String MemberID;
    String UserName;
    String UserPassword;
    String MemberName;  //kullanıcının gerçek adı
    String MemberSurname;  //kullanıcının gerçek soyadı
    String MemberEmail;  //kullanıcının gerçek e posta adresi

    String RecordID;
    String RecordType;
    String RecordTitle;
    String RecordContent;
    String RecordPicture;
    String RecordAdress;
    String RecordDate;

    public Entry(){

    }

    public Entry(String RecordID, String MemberID, String MemberName, String MemberSurname, String MemberEmail, String RecordType, String RecordTitle, String RecordContent,
                 String RecordPicture, String RecordAdress, String RecordDate){


        this.MemberID = MemberID;
        this.MemberName = MemberName;
        this.MemberSurname = MemberSurname;
        this.MemberEmail = MemberEmail;
        this.RecordID = RecordID;
        this.RecordType = RecordType;
        this.RecordTitle = RecordTitle;
        this.RecordContent = RecordContent;
        this.RecordPicture = RecordPicture;
        this.RecordAdress = RecordAdress;
        this.RecordDate = RecordDate;
    }

}
