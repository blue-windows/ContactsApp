package com.example.milestone;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("businesscontact")
public class BusinessContact extends BaseContact {

    //this class adds business hours and a website
    private String businessHours;
    private String businessDays;
    private String businessURL;

    public BusinessContact() {

    }

    public BusinessContact(String name, String address, String city, String state, String country, int zipCode, String phoneNumber, String email, Boolean isBusinessContact, String businessHours, String businessDays, String businessURL) {
        setName(name);
        setAddress(address);
        setCity(city);
        setState(state);
        setCountry(country);
        setZipCode(zipCode);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setIsBusinessContact(isBusinessContact);
        this.businessHours = businessHours;
        this.businessDays = businessDays;
        this.businessURL = businessURL;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getBusinessDays() {
        return businessDays;
    }

    public void setBusinessDays(String businessDays) {
        this.businessDays = businessDays;
    }

    public String getBusinessURL() {
        return businessURL;
    }

    public void setBusinessURL(String businessURL) {
        this.businessURL = businessURL;
    }

    public void openURLinBrowser() {
        System.out.println("The URL has been opened");
    }

    public String toString() {
        return "Name: " + getName() + "\nAddress: " + getAddress() + ", " + getCity() + ", " + getState() + ", " + getCountry() + ", " + getZipCode() + "\nPhoneNumber: " + getPhoneNumber() + "\nEmail: " + getEmail() + "\nBusiness hours: " + getBusinessHours() + ", " + getBusinessDays() + "\nWebsite: " + getBusinessURL();
    }

    public String addressToString() {
        return getAddress() + ", " + getCity() + ", " + getState() + ", " + getCountry() + ", " + getZipCode();
    }

    public String getStringBusinessHours() {
        return getBusinessHours() + ", " + getBusinessDays();
    }
}

