package com.example.milestone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pick_contact_type extends AppCompatActivity {

    Button btn_selectBusinessContact, btn_selectPersonalContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_contact_type);
        btn_selectBusinessContact = findViewById(R.id.btn_selectBusinessContact);
        btn_selectPersonalContact = findViewById(R.id.btn_selectPersonalContact);

        btn_selectBusinessContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pick_contact_type.this , add_edit_business_contact.class);
                startActivity(intent);
            }
        });

        btn_selectPersonalContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pick_contact_type.this , add_edit_personal_contact.class);
                startActivity(intent);
            }
        });
    }

}