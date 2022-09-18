package com.example.milestone;

public class BusinessService {

    //a nice address book
    AddressBook addressBook = this.getAddressBook();
    PersonalContact personalContact1;

    public BusinessService() {

    }

    //doesn't do anything yet
    public void saveContacts(AddressBook addressBook) {
        System.out.println("The contacts have been saved");
    }

    //also doesn't really do anything
    public AddressBook loadContacts() {
        System.out.println("The contacts have been loaded");
        return new AddressBook();
    }

    //gets the address book
    public AddressBook getAddressBook() {
        return addressBook;
    }
}

