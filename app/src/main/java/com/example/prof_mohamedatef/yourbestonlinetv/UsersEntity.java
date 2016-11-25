package com.example.prof_mohamedatef.yourbestonlinetv;

import java.io.Serializable;

/**
 * Created by Prof-Mohamed Atef on 10/5/2016.
 */
public class UsersEntity implements Serializable {
    public UsersEntity(String firstName, String selectedRadio_UserType) {
        this.FirstName = firstName;
        this.selectedRadio_UserType = selectedRadio_UserType;
    }

    String FirstName;
    String LastName;
    String selectedRadio_UserType;
    String SELECTEDGender;
    String ISADMIN;

    public String getISADMIN() {
        return ISADMIN;
    }

    public void setISADMIN(String ISADMIN) {
        this.ISADMIN = ISADMIN;
    }

    public String getSELECTEDGender() {
        return SELECTEDGender;
    }

    public void setSELECTEDGender(String SELECTEDGender) {
        this.SELECTEDGender = SELECTEDGender;
    }

    String Email;
    String DATEOFBIRTH;

    public String getDATEOFBIRTH() {
        return DATEOFBIRTH;
    }

    public void setDATEOFBIRTH(String DATEOFBIRTH) {
        this.DATEOFBIRTH = DATEOFBIRTH;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }



    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public UsersEntity() {

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getSelectedRadio_UserType() {
        return selectedRadio_UserType;
    }

    public void setSelectedRadio_UserType(String selectedRadio_UserType) {
        this.selectedRadio_UserType = selectedRadio_UserType;
    }

}