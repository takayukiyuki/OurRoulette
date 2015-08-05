//リストビューで例外がでるので一旦停止


package com.example.andoutakayuki.ourroulette;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by andoutakayuki on 15/08/01.
 */
public class GroupListActivity extends Activity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBhelper dBhelper = new DBhelper(getApplicationContext());
        db = dBhelper.getWritableDatabase();
        setContentView(R.layout.activity_group);
        ListView listView = (ListView) findViewById(R.id.groupList);
        try {
            listView.setAdapter(readDB());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    ListView listView = (ListView) parent;
                    String groupName = (String) listView.getItemAtPosition(position);
                    String sql = "select name from " + DBhelper.DB_TABLE + " where GroupName = " + "'" + groupName + "'";
                    ArrayList<String> list = new ArrayList<String>();
                    Cursor c = db.rawQuery(sql, null);
                    c.moveToFirst();
                    for (int i = 0; i < c.getCount(); i++) {
                        list.add(c.getString(0));
                        c.moveToNext();
                    }
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("GroupName", groupName);
                    intent.putExtra("result", list.get(new Random().nextInt(list.size())));
                    intent.putStringArrayListExtra("resultList", list);
                    startActivity(intent);
                    finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayAdapter<String> readDB() throws Exception {
        Cursor c = db.query(true, DBhelper.DB_TABLE, new String[]{"GroupName"}, null, null, null, null, null, null);
        ArrayAdapter<String> list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            list.add(c.getString(0));
            c.moveToNext();
        }
        return list;
    }

}
