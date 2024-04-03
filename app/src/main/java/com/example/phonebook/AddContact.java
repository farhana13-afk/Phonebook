package com.example.phonebook;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    EditText name;
    EditText description;
    EditText universe;
    EditText favs;
    EditText email;
    EditText phone;
    CharSequence n;
    CharSequence d;
    CharSequence u;
    CharSequence f;
    CharSequence e;
    CharSequence p;
    Button add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        name = findViewById(R.id.editName);
        description = findViewById(R.id.editDescription);
        universe = findViewById(R.id.editUniverse);
        favs = findViewById(R.id.editSaying);
        email = findViewById(R.id.editEmail);
        phone = findViewById(R.id.editPhone);
        add = findViewById(R.id.addButtonLol);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //n = (String)s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                n = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // d = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                d = s;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        universe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               // u = (String) s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                u = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        favs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               // sa = (String) s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                f = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //e = (String) s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e =  s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               // p = (String) s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                p = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void OnClickAdd(View view)
    {
        if(n == null || d == null || u == null || f == null || e == null || p == null)
        {
            Toast loser = Toast.makeText(this, "Finish Filling in Contact Info", Toast.LENGTH_SHORT);
            loser.show();
        }
        else
        {
            Contact lmao = new Contact(R.drawable.unknown, n.toString(), d.toString(), u.toString(), f.toString(), e.toString(), p.toString());
            Toast doneAdding = Toast.makeText(this, "New Contact Added! Return to Main", Toast.LENGTH_SHORT);
            doneAdding.show();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("data", lmao);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }

}