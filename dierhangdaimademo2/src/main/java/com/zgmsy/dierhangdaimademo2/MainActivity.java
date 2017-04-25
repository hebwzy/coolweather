package com.zgmsy.dierhangdaimademo2;

import android.app.AlarmManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("wwwwww","Debug调试前");
        myMethod();

        Log.e("wwwwww","Debug调试完");

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
//        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,);



    }

    private void myMethod() {
        Log.e("wwwwww","Debug调试中");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }
}
