package com.example.sSqlapplication;

public class PassengerModel {

    private int imageResource;
    private String Name;
    private String Surname;
    private String MiddleName;
    private String UserId;

    public PassengerModel(String Name, String MiddleName, String Surname, String UserId){
        this.Name = Name;
        this.MiddleName = MiddleName;
        this.Surname = Surname;
        this.UserId = UserId;

    }

    public String getName() {
        return this.Name;
    }
    public String getMiddleName() { return this.MiddleName; }
    public String getSurname() { return this.Surname; }
    public String getId() { return this.UserId; }

    public void setId(String Id) {  this.UserId = Id; }
    public void setName(String Name) {  this.Name = Name; }
    public void setMiddleName(String MiddleName) {  this.MiddleName = MiddleName; }
    public void setSurname(String surname) {  this.Surname= surname; }

    public String toString() {

        if(getMiddleName() == null || getMiddleName().isEmpty() )
            return String.format("%s %s",getName(),getSurname());
        return String.format("%s %s %s",getName(),getMiddleName(),getSurname());
    }


}
