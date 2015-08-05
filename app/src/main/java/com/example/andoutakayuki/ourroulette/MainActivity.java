package com.example.andoutakayuki.ourroulette;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//ActionBarActivityはsupport libraryの22.1で非推奨になったのでAppCompatActivityを使う

/**
 *
 */
public class MainActivity extends Activity {
    public static final int MENU_ITEM = 0;
    public static final String MyDebug = "MyDebug";

    private static String DB_TEST;
    private static Intent intent;

    private SQLiteDatabase db;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.startButton);
        Button button2 = (Button) findViewById(R.id.groupButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), RouletteActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), GroupListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
