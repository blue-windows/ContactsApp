package com.example.milestone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;

import com.fasterxml.jackson.databind.ser.Serializers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_addContact, btn_saveContacts, btn_loadContacts;
    RecyclerView rv_contactsList;
    BusinessService bs = new BusinessService();
    File file = new File("C:\\Users\\dwtho\\workspaceCST-135\\milestone1\\src\\milestone1\\save.txt");
    Menu menu;
    AddressBook addressBook = (AddressBook) this.getApplication();
    List<BaseContact> contactList;

    private RecyclerView recyclerView;
    private RecycleViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = bs.getAddressBook().getContactList();
        btn_addContact = findViewById(R.id.btn_addContact);
        btn_loadContacts = findViewById(R.id.btn_loadContacts);
        btn_saveContacts = findViewById(R.id.btn_saveContacts);
        recyclerView = findViewById(R.id.rv_contactsList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecycleViewAdapter(contactList, this);
        recyclerView.setAdapter(mAdapter);

        Collections.sort(contactList, BaseContact.sortContactsAlphabetically);
        mAdapter.notifyDataSetChanged();

        btn_addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , pick_contact_type.class);
                startActivity(intent);
                //next id will remain default value of -1
            }
        });

        btn_saveContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileAccessService fas = new FileAccessService(v.getContext());
                try {
                    fas.saveAll(bs, "cars.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_loadContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileAccessService fas = new FileAccessService(v.getContext());
                try {
                    fas.readAll(bs, "cars.txt");
                    mAdapter.notifyDataSetChanged();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView(); //this is null
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}