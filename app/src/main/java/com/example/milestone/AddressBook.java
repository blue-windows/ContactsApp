package com.example.milestone;
import android.app.Application;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class AddressBook extends Application {

    //this is a nice arrayList of BaseContacts
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
    private static List<BaseContact> contactList = new ArrayList<>();
    private static int nextId = -1;

    public AddressBook() {
        contactList.add(new PersonalContact("Jeff Hall", "293 Clawfield Place", "Newtown", "MO",
                "U.S.A.", 63333, "333-543-6754", "jhall@gmail.com", false,
                "Pretty cool, very good at soccer"));
    }

    public AddressBook(List<BaseContact> contactList) {
        this.contactList = new ArrayList<>(contactList);
    }

    public static List<BaseContact> getContactList() {
        return contactList;
    }

    public static void setContactList(List<BaseContact> contactlist) {
        contactList = contactlist;
    }

    public static void addContact(BaseContact contact) {
        contactList.add(contact);
    }

    public static BaseContact getContact(int place) {
        return contactList.get(place);
    }

    public static void deleteContact(BaseContact contact) {
        contactList.remove(contact);
        System.out.println(contact.getName() + " has been removed from address book");
    }

    //deletes contact based on position in list
    public static void deleteContactID(int id) {
        deleteContact(contactList.get(id));
    }

    //searches for contacts based on name and address
    public static BaseContact findContact(String search) {
        for (BaseContact c : contactList) {
            if(c.getName().toLowerCase().contains(search.toLowerCase()) || c.getAddress().toLowerCase().contains(search.toLowerCase())) return c;
        }
        return null;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        AddressBook.nextId = nextId;
    }

    public int getPosition(BaseContact b) {
        int index = contactList.indexOf(b);
        return index;
    }

    public void displayAll() {
        for (BaseContact c : contactList) {
            System.out.println("================================================================");
            System.out.println(c.toString());
        }
    }

    public void displayPersonal() {
        for (BaseContact c : contactList ) {
            if (c.getIsBusinessContact() == false) {
                System.out.println(c.toString());
            }
        }
    }

    public void displayBusiness() {
        for (BaseContact c : contactList) {
            if (c.getIsBusinessContact()) {
                System.out.println(c.toString());
            }
        }
    }
}

