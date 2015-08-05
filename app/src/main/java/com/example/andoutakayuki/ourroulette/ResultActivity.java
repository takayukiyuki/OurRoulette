package com.example.andoutakayuki.ourroulette;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * Created by andoutakayuki on 15/07/24.
 */
public class ResultActivity extends AppCompatActivity {
    public static String GroupName;
    private static Intent intent;
    private static Cursor c;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        intent = getIntent();
        TextView textView1 = (TextView) findViewById(R.id.resultText);
        textView1.setText(getString(R.string.result, intent.getStringExtra("result")));
        TextView textView2 = (TextView) findViewById(R.id.groupText);
        textView2.setText(getString(R.string.group_name, intent.getStringExtra("GroupName")));
        ListView listView = (ListView)findViewById(R.id.resultList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        for(String s:intent.getStringArrayListExtra("resultList")){
            adapter.add(s);
        }
        listView.setAdapter(adapter);
    }

    public void saveButton(View v) {
        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem menuItem = menu.add(0, MainActivity.MENU_ITEM, 0, "HOME");
        menuItem.setIcon(R.drawable.notepad);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }


}
