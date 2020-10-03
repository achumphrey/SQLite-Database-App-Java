package com.example.sqlitedatabaseappjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CountryListActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    //Array of column titles in the cursor
    // which was retrieved from the database
    final String[] from = new String[]{
            DatabaseHelper._ID,
            DatabaseHelper.SUBJECT,
            DatabaseHelper.DESC
    };

    //Array of view components to bind the data
    // from the cursor in order to display them
    final int[] to = new int[]{
            R.id.tvId,
            R.id.tvTitle,
            R.id.tvDesc
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.activity_view_record,
                cursor,
                from,
                to,
                0);

        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        //OnclickListener for list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id) {

                TextView idTextView = view.findViewById(R.id.tvId);
                TextView titleTextView = view.findViewById(R.id.tvTitle);
                TextView descTextView = view.findViewById(R.id.tvDesc);

                String itemId = idTextView.getText().toString();
                String itemTitle = titleTextView.getText().toString();
                String itemDesc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(),
                        ModifyCountryActivity.class);
                modify_intent.putExtra("id", itemId);
                modify_intent.putExtra("title", itemTitle);
                modify_intent.putExtra("desc", itemDesc);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record){

            Intent add_mem = new Intent(CountryListActivity.this,
                    AddCountryActivity.class);
            startActivity(add_mem);
        }

        return super.onOptionsItemSelected(item);
    }
}
