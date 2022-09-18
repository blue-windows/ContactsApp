package com.example.milestone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class add_edit_personal_contact extends AppCompatActivity {

    BusinessService bs = new BusinessService();
    Button btn_addPersonalContact, btn_cancelPersonalContact, btn_deletePersonalContact;
    EditText et_personalContactName, et_personalContactAddress, et_personalContactCity, et_personalContactState, et_personalContactCountry, et_personalContactZipCode,
            et_personalContactPhoneNumber, et_personalContactEmail, et_personalContactPersonalDescription;
    List<BaseContact> contactList;
    AddressBook addressBook = (AddressBook) this.getApplication();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_personal_contact);
        btn_addPersonalContact = findViewById(R.id.btn_personalContactAdd);
        btn_cancelPersonalContact = findViewById(R.id.btn_personalContactCancel);
        btn_deletePersonalContact = findViewById(R.id.btn_personalContactDelete);
        et_personalContactName = findViewById(R.id.et_personalContactName);
        et_personalContactAddress = findViewById(R.id.et_personalContactAddress);
        et_personalContactCity = findViewById(R.id.et_personalContactCity);
        et_personalContactState = findViewById(R.id.et_personalContactState);
        et_personalContactCountry = findViewById(R.id.et_personalContactCountry);
        et_personalContactZipCode = findViewById(R.id.et_personalContactZipCode);
        et_personalContactPhoneNumber = findViewById(R.id.et_personalContactPhoneNumber);
        et_personalContactEmail = findViewById(R.id.et_personalContactEmail);
        et_personalContactPersonalDescription = findViewById(R.id.et_personalContactPersonalDescription);

        addressBook = (AddressBook) this.getApplication();
        contactList = addressBook.getContactList();

        //this gets the id number from the extras in the intent
        Intent intent = getIntent();
        id = intent.getIntExtra("id1" , -1);
        PersonalContact contact = null;

        if (id >= 0) {
            for (BaseContact b : contactList) {
                if (addressBook.getPosition(b) == id) {
                    contact = (PersonalContact) b;

                }
            }


            et_personalContactName.setText(contact.getName());
            et_personalContactAddress.setText(contact.getAddress());
            et_personalContactCity.setText(contact.getCity());
            et_personalContactState.setText(contact.getState());
            et_personalContactCountry.setText(contact.getCountry());
            et_personalContactZipCode.setText(String.valueOf(contact.getZipCode()));
            et_personalContactPhoneNumber.setText(contact.getPhoneNumber());
            et_personalContactEmail.setText(contact.getEmail());
            et_personalContactPersonalDescription.setText(contact.getPersonalDescription());
        }

        //set click listeners
        PersonalContact finalContact = contact;
        btn_addPersonalContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id <0) {
                    //create new contact
                    PersonalContact contact = new PersonalContact(et_personalContactName.getText().toString(), et_personalContactAddress.getText().toString(),
                            et_personalContactCity.getText().toString(), et_personalContactState.getText().toString(), et_personalContactCountry.getText().toString(),
                            Integer.parseInt(et_personalContactZipCode.getText().toString()), et_personalContactPhoneNumber.getText().toString(),
                            et_personalContactEmail.getText().toString(), false, et_personalContactPersonalDescription.getText().toString());
                    //add it to the list
                    contactList.add(contact);
                }
                else {
                    finalContact.setName(et_personalContactName.getText().toString());
                    finalContact.setAddress(et_personalContactAddress.getText().toString());
                    finalContact.setCity(et_personalContactCity.getText().toString());
                    finalContact.setState(et_personalContactState.getText().toString());
                    finalContact.setCountry(et_personalContactCountry.getText().toString());
                    finalContact.setZipCode(Integer.parseInt(et_personalContactZipCode.getText().toString()));
                    finalContact.setPhoneNumber(et_personalContactPhoneNumber.getText().toString());
                    finalContact.setEmail(et_personalContactEmail.getText().toString());
                    finalContact.setPersonalDescription(et_personalContactPersonalDescription.getText().toString());
                }
                //go back to main
                Intent intent = new Intent(add_edit_personal_contact.this , MainActivity.class);
                startActivity(intent);
            }
        });

        btn_cancelPersonalContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_edit_personal_contact.this , MainActivity.class);
                startActivity(intent);
            }
        });

        btn_deletePersonalContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactList.remove(id);
                Intent intent = new Intent(add_edit_personal_contact.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}