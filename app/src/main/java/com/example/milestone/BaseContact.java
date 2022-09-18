package com.example.milestone;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Comparator;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({

        @JsonSubTypes.Type(value = BusinessContact.class, name = "businesscontact"),
        @JsonSubTypes.Type(value = PersonalContact.class, name = "personalcontact")
})
public abstract class BaseContact {

    //I think most of this is self-explanatory
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private int zipCode;
    private String phoneNumber;
    private String email;
    private Boolean isBusinessContact;

    public BaseContact() {

    }

    public BaseContact(String name, String address, String city, String state, String country, int zipCode, String phoneNumber, String email, Boolean isBusinessContact) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isBusinessContact = isBusinessContact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsBusinessContact() {
        return isBusinessContact;
    }

    public void setIsBusinessContact(Boolean isBusinessContact) {
        this.isBusinessContact = isBusinessContact;
    }

    public void navigateTo() {
        System.out.println("Currently navigating to " + getName());
    }

    public void textTo() {
        System.out.println("Currently texting " + getName());
    }

    public void callTo() {
        System.out.println("Currently calling " + getName());
    }

    public void emailTo() {
        System.out.println("Currently emailing " + getName());
    }

    public void openURLinBrowser() {
    }

    public static Comparator<BaseContact> sortContactsAlphabetically = new Comparator<BaseContact>() {
        @Override
        public int compare(BaseContact o1, BaseContact o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public String addressToString() {
        return getAddress() + ", " + getCity() + ", " + getState() + ", " + getCountry() + ", " + getZipCode();
    }

    public String getStringBusinessHours() {
        return "";
    }

    public String getBusinessURL() {
        return "";
    }

    public String getPersonalDescription() {
        return "";
    }
}



