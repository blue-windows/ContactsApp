package com.example.milestone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class add_edit_business_contact extends AppCompatActivity {

    BusinessService bs = new BusinessService();
    Button btn_addBusinessContact, btn_cancelBusinessContact, btn_deleteBusinessContact;
    EditText et_businessContactName, et_businessContactAddress, et_businessContactCity, et_businessContactState, et_businessContactCountry, et_businessContactZipCode,
            et_businessContactPhoneNumber, et_businessContactEmail, et_businessContactBusinessHours, et_businessContactBusinessDays, et_businessContactBusinessURl;
    List<BaseContact> contactList;
    private AddressBook addressBook = (AddressBook) this.getApplication();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_business_contact);
        //declaring all the buttons and edittexts
        btn_addBusinessContact = findViewById(R.id.btn_addBusinessContact);
        btn_cancelBusinessContact = findViewById(R.id.btn_cancelBusinessContact);
        btn_deleteBusinessContact = findViewById(R.id.btn_businessContactDelete);
        et_businessContactName = findViewById(R.id.et_businessContactName);
        et_businessContactAddress = findViewById(R.id.et_businessContactAddress);
        et_businessContactCity = findViewById(R.id.et_businessContactCity);
        et_businessContactState = findViewById(R.id.et_businessContactState);
        et_businessContactCountry = findViewById(R.id.et_businessContactCountry);
        et_businessContactZipCode = findViewById(R.id.et_businessContactZipCode);
        et_businessContactPhoneNumber = findViewById(R.id.et_businessContactPhoneNumber);
        et_businessContactEmail = findViewById(R.id.et_businessContactEmail);
        et_businessContactBusinessHours = findViewById(R.id.et_businessContactBusinessHours);
        et_businessContactBusinessDays = findViewById(R.id.et_businessContactBusinessDays);
        et_businessContactBusinessURl = findViewById(R.id.et_businessContactBusinessURL);

        addressBook = (AddressBook) this.getApplication();
        contactList = addressBook.getContactList();

        //this gets the id number from the extras in the intent
        Intent intent = getIntent();
        id = intent.getIntExtra("id1", -1);
        BusinessContact contact = null;

        if (id >= 0) {
            for (BaseContact b : contactList) {
                if (addressBook.getPosition(b) == id) {
                    contact = (BusinessContact) b;

                }
            }


            et_businessContactName.setText(contact.getName());
            et_businessContactAddress.setText(contact.getAddress());
            et_businessContactCity.setText(contact.getCity());
            et_businessContactState.setText(contact.getState());
            et_businessContactCountry.setText(contact.getCountry());
            et_businessContactZipCode.setText(String.valueOf(contact.getZipCode()));
            et_businessContactPhoneNumber.setText(contact.getPhoneNumber());
            et_businessContactEmail.setText(contact.getEmail());
            et_businessContactBusinessHours.setText(contact.getBusinessHours());
            et_businessContactBusinessDays.setText(contact.getBusinessDays());
            et_businessContactBusinessURl.setText(contact.getBusinessURL());
        }

        //set onclicks
        BusinessContact finalContact = contact;
        btn_addBusinessContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id < 0) {
                    //create contact object
                    int nextId = AddressBook.getNextId();
                    BusinessContact contact = new BusinessContact(et_businessContactName.getText().toString(), et_businessContactAddress.getText().toString(),
                            et_businessContactCity.getText().toString(), et_businessContactState.getText().toString(), et_businessContactCountry.getText().toString(),
                            Integer.parseInt(et_businessContactZipCode.getText().toString()), et_businessContactPhoneNumber.getText().toString(),
                            et_businessContactEmail.getText().toString(), true, et_businessContactBusinessHours.getText().toString(),
                            et_businessContactBusinessDays.getText().toString(), et_businessContactBusinessURl.getText().toString());
                    //add it to the list
                    contactList.add(contact);
                } else {
                    finalContact.setName(et_businessContactName.getText().toString());
                    finalContact.setAddress(et_businessContactAddress.getText().toString());
                    finalContact.setCity(et_businessContactCity.getText().toString());
                    finalContact.setState(et_businessContactState.getText().toString());
                    finalContact.setCountry(et_businessContactCountry.getText().toString());
                    finalContact.setZipCode(Integer.parseInt(et_businessContactZipCode.getText().toString()));
                    finalContact.setPhoneNumber(et_businessContactPhoneNumber.getText().toString());
                    finalContact.setEmail(et_businessContactEmail.getText().toString());
                    finalContact.setBusinessDays(et_businessContactBusinessDays.getText().toString());
                    finalContact.setBusinessHours(et_businessContactBusinessHours.getText().toString());
                    finalContact.setBusinessURL(et_businessContactBusinessURl.getText().toString());
                }
                //go back to main activity
                Intent intent = new Intent(add_edit_business_contact.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_cancelBusinessContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_edit_business_contact.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_deleteBusinessContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactList.remove(id);
                Intent intent = new Intent(add_edit_business_contact.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
