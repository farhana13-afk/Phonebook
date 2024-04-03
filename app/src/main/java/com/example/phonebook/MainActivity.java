package com.example.phonebook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> contacts;
    ListView listview;
    Button goToAdd;
    int currentClicked;
    ImageView image;
    TextView name;
    TextView description;
    TextView universe;
    TextView saying;
    TextView email;
    TextView phonenumber;
    Contact addedContact;
    ActivityResultLauncher<Intent> resultContract;
    public static final String STRING_KEY = "saveStuff";
    public static final String STRING_2 = "saveStuffPart2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listView);
        goToAdd = findViewById(R.id.goToAddButton);
        image = findViewById(R.id.image_land);
        name = findViewById(R.id.name2);
        description = findViewById(R.id.description2);
        universe = findViewById(R.id.universe);
        saying = findViewById(R.id.saying);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phoneNumber);
        Activity main = this;

        resultContract =  registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result != null && result.getResultCode() == Activity.RESULT_OK) {
                            addedContact = result.getData().getParcelableExtra("data");
                            if(addedContact != null)
                            {
                                contacts.add(addedContact);
                                listview.setAdapter(new CustomAdapter(main, R.layout.adapter_layout, contacts));
                            }
                        }
                    }
                }
        );

        if(savedInstanceState == null)
        {
            contacts = new ArrayList<Contact>();
            contacts.add(new Contact(R.drawable.spongebob, "SpongeBob", "Energetic Yellow Sea Sponge", "Bikini Bottom", "Jellyfishing", "spongebob_squarepants@gmail.com", "7324439284"));
            contacts.add(new Contact(R.drawable.patrick, "Patrick", "Friendly Pink Starfish", "Bikini Bottom",  "Art of Doing Nothing", "patrick_starr@yahoo.com", "9384596023"));
            contacts.add(new Contact(R.drawable.ferb, "Ferb", "Green and Rectangle", "Danville", "Summer Vacation", "ferb_fletcher@gmail.com", "8374921348"));
            contacts.add(new Contact(R.drawable.jerry, "Jerry", "Brown House Mouse", "Worldwide", "Being Better Than Tom", "jerry_mouse@gmail.com", "3429834566"));
            contacts.add(new Contact(R.drawable.mickey, "Mickey", "Mascot Mouse", "Disney", "Hot Dogs", "mickey_mouse@yahoo.com", "3219656770"));
            contacts.add(new Contact(R.drawable.mrbean, "Mr.Bean", "Immature Human", "London", "Watching Tv","mr_bean@gmail.com", "6099686770"));
            contacts.add(new Contact(R.drawable.phineas, "Phineas", "Orange and Triangle", "Danville","Adventure", "phineas_flynn@gmail.com", "6095294486"));
            contacts.add(new Contact(R.drawable.pinkpanther, "Pink Panther", "Pink and a Panther", "Worldwide", "pranks and skateboarding", "pink_panther@gmail.com", "4589383402"));
            contacts.add(new Contact(R.drawable.tom, "Tom", "Grey British Cat", "Worldwide", "Chasing Jerry", "tom_cat@gmail.com", "8234596870"));
        }
        else if(savedInstanceState != null)
        {
            currentClicked = savedInstanceState.getInt(STRING_KEY);
            contacts = savedInstanceState.getParcelableArrayList(STRING_2);

            if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && contacts.size() != 0)
            {

                Contact temp = contacts.get(currentClicked);
                image.setImageResource(temp.getImage());
                name.setText( temp.getName());
                description.setText(temp.getDescription());
                universe.setText("universe: "+temp.getUniverse());
                saying.setText("saying: "+temp.getSaying());
                email.setText("email: "+ temp.getEmail());
                phonenumber.setText("phone number: "+ temp.getPhonenumber());
            }
            else if(contacts.size() == 0)
            {
                Toast out = Toast.makeText(this, "No Contacts Currently", Toast.LENGTH_SHORT);
                out.show();
            }
        }

        listview.setAdapter(new CustomAdapter(this, R.layout.adapter_layout, contacts));


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentClicked = position;

                if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Contact temp = contacts.get(currentClicked);
                    image.setImageResource(temp.getImage());
                    name.setText(temp.getName());
                    description.setText (temp.getDescription());
                    universe.setText("universe: "+temp.getUniverse());
                    saying.setText("saying: "+temp.getSaying());
                    email.setText("email: "+ temp.getEmail());
                    phonenumber.setText("phone number: "+ temp.getPhonenumber());
                }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                {
                    Toast toast = Toast.makeText(main, contacts.get(currentClicked).getName()+" Clicked", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STRING_KEY, currentClicked);
        outState.putParcelableArrayList(STRING_2, contacts);
    }

    public void OnClickAdd(View view)
    {
        openAddContact();
    }
    public void openAddContact()
    {
        Intent intent = new Intent(this, AddContact.class);
        resultContract.launch(intent);
    }
    public void OnClickRemove(View view)
    {
        if(contacts.size() != 0) {
            contacts.remove(currentClicked);
            listview.setAdapter(new CustomAdapter(this, R.layout.adapter_layout, contacts));
            if (currentClicked >= contacts.size()) {
                currentClicked--;
            }
        }
    }

}