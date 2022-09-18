package com.example.milestone;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("personalcontact")
public class PersonalContact extends BaseContact {

    //this whole class basically just adds a personal description
    private String personalDescription;

    public PersonalContact() {

    }

    public PersonalContact(String name, String address, String city, String state, String country, int zipCode, String phoneNumber, String email, Boolean isBusinessContact, String personalDescription) {
        setName(name);
        setAddress(address);
        setCity(city);
        setState(state);
        setCountry(country);
        setZipCode(zipCode);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setIsBusinessContact(isBusinessContact);
        this.personalDescription = personalDescription;
    }

    public String getPersonalDescription() {
        return personalDescription;
    }

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    public String toString() {
        return "Name: " + getName() + "\nAddress: " + getAddress() + ", " + getCity() + ", " + getState() + ", " + getCountry() + ", " + getZipCode() + "\nPhoneNumber: " + getPhoneNumber() + "\nEmail: " + getEmail() + "\nDescription: " + getPersonalDescription();
    }

    public String addressToString() {
        return getAddress() + ", " + getCity() + ", " + getState() + ", " + getCountry() + ", " + getZipCode();
    }

}

