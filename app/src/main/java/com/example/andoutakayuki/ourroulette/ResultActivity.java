package com.example.andoutakayuki.ourroulette;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andoutakayuki on 15/07/24.
 */
public class ResultActivity extends Activity {
    final static String DB_NAME = "userDB.db";
    final static int DB_VERSION = 1;
    final static String DB_TABLE = "userDB";
    ArrayList<String> list;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        Log.d("MyDebug", intent.getStringExtra("result"));
        TextView textView = (TextView)findViewById(R.id.resultText);
        textView.setText(getString(R.string.result,intent.getStringExtra("result")));
        //staticなのにnew????
        DBhelper dbhelper = new DBhelper(this);
        db = dbhelper.getWritableDatabase();
        list = intent.getStringArrayListExtra("list");
    }


    public void saveButton(View v){
        try{
            for(String name:list) {
                writeDB("name",name);
            }
        }catch (Exception e){
        }
    }


    private void writeDB(String name,String GroupName)throws Exception{
        ContentValues values = new ContentValues();
        int count = 0;
        values.put("id","0");
        values.put("name",name);
        values.put("GroupName",GroupName);
        int colNum = db.update(DB_TABLE,values,null,null);
        if(colNum == 0){
            db.insert(DB_TABLE,"",values);
        }
    }



    //classの中にclass？？？
    private static class DBhelper extends SQLiteOpenHelper{
        public DBhelper(Context context){
            super(context,DB_NAME,null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table if not exits " + DB_TABLE + "(Id text primary key,Name text,GroupName text)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + DB_TABLE);
            onCreate(db);
        }
    }
}
