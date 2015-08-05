package com.example.andoutakayuki.ourroulette;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by andoutakayuki on 15/07/22.
 */
public class RouletteActivity extends AppCompatActivity {
    private EditText group;
    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private EditText ed4;
    private EditText ed5;

    private static String GroupName;
    private static Intent intent;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //増やせたなおよし　listviewをつかう
        setContentView(R.layout.activity_edit_name);
        group = (EditText) findViewById(R.id.groupText);
        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        ed5 = (EditText) findViewById(R.id.editText5);

        DBhelper dbhelper = new DBhelper(getApplicationContext());
        db = dbhelper.getWritableDatabase();
    }

    /**
     * ランダム結果を渡す
     *
     * @param v
     */
    public void rouletteButton(View v) {
        ArrayList<String> list = new ArrayList<>();
        //editTexのままだとなぜかnulチェックに引っかからない
        if (!group.getText().toString().equals("")) {
            GroupName = group.getText().toString();
        }
        if (!ed1.getText().toString().equals("")) {
            list.add(ed1.getText().toString());
        }
        if (!ed2.getText().toString().equals("")) {
            list.add(ed2.getText().toString());
        }
        if (!ed3.getText().toString().equals("")) {
            list.add(ed3.getText().toString());
        }
        if (!ed4.getText().toString().equals("")) {
            list.add(ed4.getText().toString());
        }
        if (!ed5.getText().toString().equals("")) {
            list.add(ed5.getText().toString());
        }
        if (list.size() == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("一人以上の名前を入力してください。");
            alert.show();
            return;
        }
        //randomの作り方　シード
        String result = list.get(new Random().nextInt(list.size()));

        intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("GroupName", GroupName);
        intent.putExtra("result", result);
        intent.putStringArrayListExtra("resultList",list);
        startActivity(intent);
        finish();
    }

    /**
     * データベースにグループを保存したのちにランダムな結果を渡す
     *
     * @param v
     */
    public void saveButton(View v) {
        ArrayList<String> list = new ArrayList<>();
        //editTexのままだとなぜかnulチェックに引っかからない
        if (!group.getText().toString().equals("")) {
            GroupName = group.getText().toString();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("グループ名を入力してください。");
            alert.show();
            return;
        }

        if (!ed1.getText().toString().equals("")) {
            list.add(ed1.getText().toString());
        }
        if (!ed2.getText().toString().equals("")) {
            list.add(ed2.getText().toString());
        }
        if (!ed3.getText().toString().equals("")) {
            list.add(ed3.getText().toString());
        }
        if (!ed4.getText().toString().equals("")) {
            list.add(ed4.getText().toString());
        }
        if (!ed5.getText().toString().equals("")) {
            list.add(ed5.getText().toString());
        }
        if (list.size() == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("一人以上の名前を入力してください。");
            alert.show();
            return;
        }

        //ランダムな結果
        String result = list.get(new Random().nextInt(list.size()));
        String sql = "select * from " + DBhelper.DB_TABLE + " where GroupName = " + "'" + GroupName + "'";
        if (db.rawQuery(sql, null).getCount() == 0) {
            //データベースに入れる処理
            for (String name : list) {
                try {
                    writeDB(name, GroupName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("そのグループ名はすでに存在します。");
            alert.show();
            return;
        }
        intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("GroupName", GroupName);
        intent.putExtra("result", result);
        intent.putStringArrayListExtra("resultList",list);
        startActivity(intent);
        finish();
    }


    private void writeDB(String name, String GroupName) throws Exception {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("GroupName", GroupName);
        db.insert(DBhelper.DB_TABLE, "", values);
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

//    @Override
//    protected void onPause() {
//        super.onPause();
//        db.close();
//    }
}
