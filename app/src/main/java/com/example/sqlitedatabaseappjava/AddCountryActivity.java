package com.example.sqlitedatabaseappjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddCountryActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btAdd;
    private EditText edSub, edDesc;

    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.activity_add_record);

        edSub = findViewById(R.id.edSubject);
        edDesc = findViewById(R.id.edDescription);
        btAdd = findViewById(R.id.btAdd);

        dbManager = new DBManager(this);
        dbManager.open();
        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btAdd:
                final String name = edSub.getText().toString();
                final String desc = edDesc.getText().toString();

                dbManager.insert(name, desc);

                Intent addIntent = new Intent(AddCountryActivity.this,
                        CountryListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(addIntent);
                break;
        }
    }
}
