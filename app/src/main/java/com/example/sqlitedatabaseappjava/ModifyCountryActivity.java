package com.example.sqlitedatabaseappjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ModifyCountryActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_Title, ed_desc;
    private Button bt_update, bt_Delete;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();

        ed_Title = findViewById(R.id.subEdit);
        ed_desc = findViewById(R.id.descEdit);

        bt_update = findViewById(R.id.btUpdate);
        bt_Delete = findViewById(R.id.btDelete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        //cast string to long
        _id = Long.parseLong(id);

        ed_Title.setText(name);
        ed_desc.setText(desc);

        bt_update.setOnClickListener(this);
        bt_Delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btUpdate:
                String title = ed_Title.getText().toString();
                String desc = ed_desc.getText().toString();
                dbManager.update(_id,title, desc);
                this.returnHome();
                break;

            case R.id.btDelete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }

    }

    public void returnHome(){

        Intent home_intent = new Intent(getApplicationContext(), 
                CountryListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
