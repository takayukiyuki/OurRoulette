package com.example.andoutakayuki.ourroulette;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by andoutakayuki on 15/07/22.
 */
public class RouletteActivity extends Activity {
    EditText ed1;
    EditText ed2;
    EditText ed3;
    EditText ed4;
    EditText ed5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //増やせたなおよし　listviewをつかう
        setContentView(R.layout.activity_edit_name);
        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        ed5 = (EditText) findViewById(R.id.editText5);
    }

    public void rouletteButton(View v) {
        ArrayList<String> list = new ArrayList<>();
        //editTexのままだとなぜかnulチェックに引っかからない
        if (!ed1.getText().toString().equals("")) {
            list.add(ed1.getText().toString());
            Log.d("MyDebug", "1");
        }
        if (!ed2.getText().toString().equals("")) {
            list.add(ed2.getText().toString());
            Log.d("MyDebug", "2");
            }
        if (!ed3.getText().toString().equals("")) {
            list.add(ed3.getText().toString());
            Log.d("MyDebug", "3");
            }
        if (!ed4.getText().toString().equals("")) {
            list.add(ed4.getText().toString());
            Log.d("MyDebug", "4");
            }
        if (!ed5.getText().toString().equals("")) {
            list.add(ed5.getText().toString());
            Log.d("MyDebug", "5");
        }
        if(list.size()==0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("一人以上の名前を入力してください。");
            alert.show();
            return;
        }
        //randomの作り方　シード
        String result = list.get(new Random().nextInt(list.size()));
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("result", result);
        intent.putExtra("list",list);
        startActivity(intent);
    }
}
