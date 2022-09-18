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

public class view_business_contact extends AppCompatActivity {

    final static int PERMISSION_TO_CALL = 1;
    TextView tv_businessContactName, tv_businessContactPhoneNumber, tv_businessContactAddress, tv_businessContactEmail, tv_businessContactBusinessHours,
            tv_businessContactBusinessURL;
    Button btn_businessContactCall, btn_businessContactText, btn_businessContactEmail, btn_businessContactNavigateTo, btn_openBusinessContactURL,
    btn_editBusinessContact;
    List<BaseContact> contactList;
    AddressBook addressBook = (AddressBook) this.getApplication();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_business_contact);
        tv_businessContactName = findViewById(R.id.tv_businessContactName);
        tv_businessContactPhoneNumber = findViewById(R.id.tv_businessContactPhoneNumber);
        tv_businessContactAddress = findViewById(R.id.tv_businessContactAddress);
        tv_businessContactEmail = findViewById(R.id.tv_businessContactEmail);
        tv_businessContactBusinessHours = findViewById(R.id.tv_businessContactBusinessHours);
        tv_businessContactBusinessURL = findViewById(R.id.tv_businessContactBusinessURL);
        btn_businessContactCall = findViewById(R.id.btn_callBusinessContact);
        btn_businessContactText = findViewById(R.id.btn_textBusinessContact);
        btn_businessContactEmail = findViewById(R.id.btn_emailBusinessContact);
        btn_businessContactNavigateTo = findViewById(R.id.btn_navigateToBusinessContact);
        btn_openBusinessContactURL = findViewById(R.id.btn_openBusinessContactURL);
        btn_editBusinessContact = findViewById(R.id.btn_editBusinessContact);

        contactList = addressBook.getContactList();

        Intent intent1 = getIntent();
        id = intent1.getIntExtra("id" , -1);
        tv_businessContactName.setText(contactList.get(id).getName());
        tv_businessContactPhoneNumber.setText(contactList.get(id).getPhoneNumber());
        tv_businessContactAddress.setText(contactList.get(id).addressToString());
        tv_businessContactEmail.setText(contactList.get(id).getEmail());
        tv_businessContactBusinessHours.setText(contactList.get(id).getStringBusinessHours());
        tv_businessContactBusinessURL.setText(contactList.get(id).getBusinessURL());


        btn_editBusinessContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_business_contact.this, add_edit_business_contact.class);
                intent.putExtra("id1", id);
                startActivity(intent);
            }
        });

        btn_businessContactCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneNumber(contactList.get(id).getPhoneNumber());
            }
        });

        btn_businessContactText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeMmsMessage(contactList.get(id).getPhoneNumber(), "");
            }
        });

        btn_businessContactEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] addresses = new String[1];
                addresses[0] = contactList.get(id).getEmail();

                composeEmail(addresses, "");
            }
        });

        btn_businessContactNavigateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapsQuery = "geo:0,0?q=" + contactList.get(id).getAddress() + contactList.get(id).getCity() + contactList.get(id).getState() +
                        contactList.get(id).getCountry() + contactList.get(id).getZipCode();
                Uri mapsURI = Uri.parse(mapsQuery);
                showMap(mapsURI);
            }
        });

        btn_openBusinessContactURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage(contactList.get(id).getBusinessURL());
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
            ActivityCompat.requestPermissions(view_business_contact.this , new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_TO_CALL);
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