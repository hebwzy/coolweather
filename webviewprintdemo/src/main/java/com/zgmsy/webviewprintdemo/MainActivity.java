package com.zgmsy.webviewprintdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//    private BaseBottomDialog bottomdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void print2(View view)
    {
        BottomSheetDialog dialog=new BottomSheetDialog(this);
        dialog.setContentView(R.layout.bottomdialog_layout_showdate);
        dialog.setCancelable(true);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.e("wwwwww","弹窗消失了");
                dialog.dismiss();
            }
        });
        dialog.show();
    }


   /* private OnTouchOutsideListener ontouchoutsidelistener;

    public void setOntouchoutsidelistener(OnTouchOutsideListener ontouchoutsidelistener) {
        this.ontouchoutsidelistener = ontouchoutsidelistener;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case  MotionEvent.ACTION_DOWN:
                if(v.isShown()) {
                    this.ontouchoutsidelistener.onTouchOutSide();
                    this.dismiss();
                }
                break;
        }
        return false;
    }*/
}
