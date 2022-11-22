package com.example.madprojectbrownies;

public class Reservation {

    String IDv;
    String Namev;
    String ContactNov;
    String Tablesv;
    String Datev;
    String Timev;

    public Reservation() {
    }

    public Reservation(String IDv, String Namev, String ContactNov, String Tablesv, String Datev, String Timev) {
        this.IDv = IDv;
        this.Namev = Namev;
        this.ContactNov = ContactNov;
        this.Tablesv = Tablesv;
        this.Datev= Datev;
        this.Timev=Timev;
    }

    public String getIDv() {
        return IDv;
    }

    public void setIDv(String IDv) {
        this.IDv = IDv;
    }

    public String getNamev() {
        return Namev;
    }

    public void setNamev(String namev) {
        Namev = namev;
    }

    public String getContactNov() {
        return ContactNov;
    }

    public void setContactNov(String contactNov) {
        ContactNov = contactNov;
    }

    public String getTablesv() {
        return Tablesv;
    }

    public void setTablesv(String tablesv) {
        Tablesv = tablesv;
    }

    public String getDatev() {
        return Datev;
    }

    public void setDatev(String datev) {
        Datev = datev;
    }

    public String getTimev() {
        return Timev;
    }

    public void setTimev(String timev) {
        Timev = timev;
    }
}
