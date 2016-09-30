package com.selflearning.databaseupgradedemo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.selflearning.R;

import static com.selflearning.databaseupgradedemo.DatabaseHelper.isDBUpgraded;

public class DatabaseUpgradeDemoActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView tvData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_upgrade_demo);
        tvData = (TextView) findViewById(R.id.tvData);
        databaseHelper = new DatabaseHelper(this);
        insertDummyData();
        displayTableData();
    }

    private void insertDummyData() {
        for (int i = 0; i < 3; i++) {
            if (isDBUpgraded) {
                databaseHelper.insertUser("User " + (i + 1), "" + (i + 1), "" + (i + 1));
            } else {
                databaseHelper.insertUser("User " + (i + 1), "" + (i + 1));
            }
        }
    }

    public void clearDbEntries(View view) {
        databaseHelper.deleteAllRows();
        displayTableData();
    }

    private void displayTableData() {
        Cursor cursor = databaseHelper.getData();
        cursor.moveToFirst();
        StringBuilder stringBuilder = new StringBuilder();
        while (!cursor.isAfterLast()) {
            stringBuilder
                    .append("\n")
                    .append(cursor.getString(cursor.getColumnIndex("name")))
                    .append("         ")
                    .append(cursor.getString(cursor.getColumnIndex("number")))
                    .append("         ");
            if (isDBUpgraded) {
                stringBuilder.append(cursor.getString(cursor.getColumnIndex("phone")));
            }
            cursor.moveToNext();
        }
        tvData.setText(stringBuilder.toString());
    }

    public void showDbEntries(View view) {
        displayTableData();
    }
}