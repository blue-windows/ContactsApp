package com.example.milestone;

import android.content.Context;
import android.provider.Telephony;

import java.io.*;
import java.util.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FileAccessService {
    Context context;
    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;


    //this method writes all of the basecontacts to a file using a json mapper and a loop
    public void saveAll(BusinessService bs, String fileName) throws IOException {
        File path = context.getExternalFilesDir(null);
        File file = new File(path, fileName);

        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < AddressBook.getContactList().size(); i++) {
            String text = mapper.writeValueAsString(AddressBook.getContactList().get(i));
            pw.println(text);
        }
        pw.close();

    }

    //this methods reads all of the basecontacts that have been saved by the json mapper by using a loop
    public void readAll(BusinessService bs, String fileName) throws JsonParseException, JsonMappingException, IOException {
        File path = context.getExternalFilesDir(null);
        File file = new File(path, fileName);
        Scanner scanner = new Scanner(file);
        String text = "";
        AddressBook.getContactList().clear();

        while(scanner.hasNextLine()) {
            text = scanner.nextLine();
            AddressBook.addContact(mapper.readValue(text, BaseContact.class));
        }
    }

    //this just instantiates a FileAccessService
    public FileAccessService(Context context) {
     this.context = context;
    }

}

