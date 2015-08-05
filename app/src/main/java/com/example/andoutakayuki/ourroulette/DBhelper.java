package com.example.andoutakayuki.ourroulette;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andoutakayuki on 15/07/30.
 */
class DBhelper extends SQLiteOpenHelper {
    final static String DB_NAME = "userDB.db";
    final static int DB_VERSION = 1;
    final static String DB_TABLE = "userDB";
    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + DB_TABLE + "(Id integer primary key autoincrement, Name text, GroupName text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DB_TABLE);
        onCreate(db);
    }
}
