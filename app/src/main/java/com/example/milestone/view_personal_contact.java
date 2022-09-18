package com.example.milestone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class view_personal_contact extends AppCompatActivity {

    final static int PERMISSION_TO_CALL = 1;
    TextView tv_personalContactName, tv_personalContactPhoneNumber, tv_personalContactAddress, tv_personalContactEmail, tv_personalContactPersonalDescription;
    Button btn_personalContactCall, btn_personalContactText, btn_personalContactEmail, btn_personalContactNavigateTo,
    btn_editPersonalContact;
    List<BaseContact> contactList;
    AddressBook addressBook = (AddressBook) this.getApplication();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_personal_contact);
        tv_personalContactName = findViewById(R.id.tv_personalContactName);
        tv_personalContactPhoneNumber = findViewById(R.id.tv_personalContactPhoneNumber);
        tv_personalContactAddress = findViewById(R.id.tv_personalContactAddress);
        tv_personalContactEmail = findViewById(R.id.tv_personalContactEmail);
        tv_personalContactPersonalDescription = findViewById(R.id.tv_personalContactPersonalDescription);
        btn_personalContactCall = findViewById(R.id.btn_callPersonalContact);
        btn_personalContactText = findViewById(R.id.btn_textPersonalContact);
        btn_personalContactEmail = findViewById(R.id.btn_emailPersonalContact);
        btn_personalContactNavigateTo = findViewById(R.id.btn_navigateToPersonalContact);
        btn_editPersonalContact = findViewById(R.id.btn_editPersonalContact);

        contactList = addressBook.getContactList();

        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id" , -1);
        tv_personalContactName.setText(contactList.get(id).getName());
        tv_personalContactPhoneNumber.setText(contactList.get(id).getPhoneNumber());
        tv_personalContactAddress.setText(contactList.get(id).addressToString());
        tv_personalContactEmail.setText(contactList.get(id).getEmail());
        tv_personalContactPersonalDescription.setText(contactList.get(id).getPersonalDescription());

        btn_editPersonalContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_personal_contact.this, add_edit_personal_contact.class);
                intent.putExtra("id1" , id);
                startActivity(intent);
            }
        });

        btn_personalContactCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneNumber(contactList.get(id).getPhoneNumber());
            }
        });

        btn_personalContactText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeMmsMessage(contactList.get(id).getPhoneNumber(), "");
            }
        });

        btn_personalContactEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addresses = new String[1];
                addresses[0] = contactList.get(id).getEmail();

                composeEmail(addresses, "");
            }
        });

        btn_personalContactNavigateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapsQuery = "geo:0,0?q=" + contactList.get(id).getAddress() + contactList.get(id).getCity() + contactList.get(id).getState() +
                        contactList.get(id).getCountry() + contactList.get(id).getZipCode();
                Uri mapsURI = Uri.parse(mapsQuery);
                showMap(mapsURI);
            }
        });
    }

    public void openWebPage(String url) {
        if (!url.startsWith("http://") || !url.startsWith("https://")) {
            url = "http://" + url;
        }

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        //       if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
        //       }
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //       if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
        //       }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
//        if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
//        }
    }

    public void callPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(view_personal_contact.this , new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_TO_CALL);
        }
        else {


//        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
//        }
        }
    }

    public void composeMmsMessage(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        //   if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
        //  }
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        //    if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
        //   }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_TO_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhoneNumber(contactList.get(id).getPhoneNumber());
                }
                else {
                    Toast.makeText(this, "Didn't get permission" , Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}