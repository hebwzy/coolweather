package com.zgmsy.opencamerademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.weipass.pos.sdk.Photograph;
import cn.weipass.pos.sdk.Weipos;
import cn.weipass.pos.sdk.impl.WeiposImpl;

public class MainActivity extends AppCompatActivity {
    private Photograph mPhotograph;
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
// 回调函数中不能做UI操作，所以可以使用runOnUiThread函数来包装一下代码块
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this, "微POS 设备初始化完成",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                try {
                    // 初始化相机
                    mPhotograph = WeiposImpl.as().openPhotograph();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            @Override
            public void onError(String message) {
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
    private String photoSaveName;
    public String fileFullName;//照相后的照片的全整路径
    /**
     * 调用相机拍照
     * @param view
     */
    public void TakePhoto(View view)
    {
        if(mPhotograph!=null) {

            mPhotograph.setResultListener(new Photograph.OnResultListener() {
                @Override
                public void onResult(int result, byte[] data, String err) {
//                    Log.e("wwwwww","result--->"+result+"---data--->"+data.toString()+"---err--->"+err);


                    if(data!=null) {

                        photoSaveName="goods_sale_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
                        String targetDir ="sdcard/" + "Goods_Sale/uploadimages";

                        File file = new File(targetDir);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        fileFullName = targetDir + "/" + photoSaveName;
                        Log.e("wwwwww", "图片位置" + fileFullName);
                        getFile(data, targetDir,photoSaveName);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "获取图片失败" + err,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
            // 是否需要剪切
            boolean isCrop = true;
            mPhotograph.takePicture(isCrop);

        } else {
            Toast.makeText(MainActivity.this, "初始化拍照sdk失败", Toast.LENGTH_LONG)
                    .show();
        }

    }
    /**
     * 根据byte数组，生成文件
     */
    public static void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            /*if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }*/
            file = new File(filePath+ "/"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);

            //这里调用图片压缩的方法
            //压缩后调用上传图片的方法

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
