package com.zgmsy.wzy_demos21;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cn.weipass.pos.sdk.IPrint;
import cn.weipass.pos.sdk.LatticePrinter;
import cn.weipass.pos.sdk.Printer;
import cn.weipass.pos.sdk.Weipos;
import cn.weipass.pos.sdk.impl.WeiposImpl;

public class MainActivity extends AppCompatActivity {
    private LatticePrinter latticePrinter;// 点阵打印
    private Printer printer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * WeiposImpl的初始化（init函数）和销毁（destroy函数），
         * 最好分别放在一级页面的onCreate和onDestroy中执行。 其他子页面不用再调用，可以直接获取能力对象并使用。
         */
        WeiposImpl.as().init(MainActivity.this, new Weipos.OnInitListener() {

            @Override
            public void onInitOk() {


                try {
                    // 设备可能没有打印机，open会抛异常
                    printer = WeiposImpl.as().openPrinter();
                } catch (Exception e) {
                    // TODO: handle exception
                }
                // 回调函数中不能做UI操作，所以可以使用runOnUiThread函数来包装一下代码块
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this, "微POS 设备初始化完成",
                                Toast.LENGTH_SHORT).show();
                    }
                });



                try {
                    latticePrinter = WeiposImpl.as().openLatticePrinter();
                } catch (Exception e) {
                    // TODO: handle exception
                }



            }

            @Override
            public void onError(String message) {
                // TODO Auto-generated method stub
                final String msg = message;
                // 回调函数中不能做UI操作，所以可以使用runOnUiThread函数来包装一下代码块
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this, msg,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
    /*
	 * (non-Javadoc)
	 *
	 * @see android.app.Activity#onDestroy()
	 */
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        // 注意：destroy函数在一级根页面的onDestroy调用，以防止在二级页面或者返回到一级页面中
        // 使用weipos能力对象（例如：Printer）抛出服务未初始化的异常.
        WeiposImpl.as().destroy();
    }

    /**
     * 点阵打印
     */
    public void print(View view)
    {
        if (latticePrinter == null) {
            Toast.makeText(MainActivity.this, "尚未初始化点阵打印sdk，请稍后再试",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        // 打印内容赋值
        latticePrinter.setOnEventListener(new IPrint.OnEventListener() {

            @Override
            public void onEvent(final int what, String in) {
                // TODO Auto-generated method stub
                final String info = in;
                // 回调函数中不能做UI操作，所以可以使用runOnUiThread函数来包装一下代码块
                runOnUiThread(new Runnable() {
                    public void run() {
                      /*  if (pd != null) {
                            pd.hide();
                        }*/
                        final String message = ToolsUtil.getPrintErrorInfo(
                                what, info);
                        if (message == null || message.length() < 1) {
                            return;
                        }
                        showResultInfo("打印", "打印结果信息", message);
                    }
                });
            }
        });
        ToolsUtil.printLattice(MainActivity.this, latticePrinter);
    }
    private void showResultInfo(String operInfo, String titleHeader, String info) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(titleHeader + ":" + info);
        builder.setTitle(operInfo);
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
    public void print2(View view)
    {
        if (printer == null) {
            Toast.makeText(MainActivity.this, "尚未初始化打印sdk，请稍后再试",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        printer.setOnEventListener(new IPrint.OnEventListener() {

            @Override
            public void onEvent(final int what, String in) {
                // TODO Auto-generated method stub
                final String info = in;
                // 回调函数中不能做UI操作，所以可以使用runOnUiThread函数来包装一下代码块
                runOnUiThread(new Runnable() {
                    public void run() {
                       /* if (pd != null) {
                            pd.hide();
                        }*/
                        final String message = ToolsUtil
                                .getPrintErrorInfo(what, info);
                        if (message == null || message.length() < 1) {
                            return;
                        }
                        showResultInfo("打印", "打印结果信息", message);
                    }
                });
            }
        });
        ToolsUtil.printNormal(MainActivity.this, printer);
    }

}
