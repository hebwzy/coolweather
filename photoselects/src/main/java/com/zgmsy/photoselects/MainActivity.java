package com.zgmsy.photoselects;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;
import com.werb.pickphotoview.PickPhotoView;
import com.werb.pickphotoview.util.PickConfig;
import com.yanzhenjie.album.Album;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
      *打开相册
      *
      *@author wzy
      *create at 2017-04-24
      */
    public void oPenGallery(View view)
    {
//        Album.startAlbum(GoodsManageActivity.this, OPENGALLERY_requestCode, 3-photos.size());

        Album.album(this)
                .requestCode(999) // Request code.
//                .toolBarColor(getResources().getColor(R.color.toolbarcolor)) // Toolbar color.
//                .statusBarColor( getResources().getColor(R.color.statusBarColor)) // StatusBar color.
                .navigationBarColor(getResources().getColor(R.color.navigationBarColor)) // NavigationBar color.
                .title("图库") // Title.

                .selectCount(4) // Choose up to a few pictures.
                .columnCount(2) // Number of albums.
                .camera(false) // Have a camera function.
                .checkedList(mImageList) // Has selected the picture, automatically select.
                .start();
    }
    private ArrayList<String> mImageList=new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0) {
            return;
        }
        if(data==null) {
            return;
        }

        if(requestCode == 999) {
            if (resultCode == RESULT_OK) { // Successfully.
                // Parse select result.
                ArrayList<String> imageList = Album.parseResult(data);
                for (int i = 0; i <imageList.size() ; i++) {
                    Log.e("wwwwww","相册选择结果---"+imageList.get(i));
                }

            } else if (resultCode == RESULT_CANCELED) {
                // User canceled.
            }
        }
       else if(requestCode == 1212) {

            List<String> photos = (List<String>) data.getSerializableExtra(GalleryActivity.PHOTOS);

            for (int i = 0; i <photos.size() ; i++) {
                 Log.e("wwwwww","选择的结果"+photos.get(i));
            }
        }
        else  if (requestCode == PickConfig.PICK_PHOTO_DATA) {

            List<String> selectPaths = (List<String>) data.getSerializableExtra(PickConfig.INTENT_IMG_LIST_SELECT);
            // do something u want
            for (int i = 0; i <selectPaths.size() ; i++) {
                Log.e("wwwwww","选择的结果"+selectPaths.get(i));
            }
        }
    }

    public void oPenGallery2(View view)
    {
        GalleryConfig config = new GalleryConfig.Build()
                .limitPickPhoto(3)
                .singlePhoto(false)
                .hintOfPick("this is pick hint")
                .filterMimeTypes(new String[]{"image/jpeg"})
                .build();
        GalleryActivity.openActivity(MainActivity.this, 1212, config);
    }
    public void oPenGallery3(View view)
    {
        new PickPhotoView.Builder(this)
                
                .setPickPhotoSize(4)   //select max size
                .setShowCamera(false)   //is show camera
                .setSpanCount(2)       //SpanCount
                .setLightStatusBar(true)  // custom theme
                .setStatusBarColor("#239ee4")   // custom statusBar
                .setToolbarColor("#239ee4")   // custom toolbar
                .setToolbarIconColor("#ffffff")   // custom toolbar icon
                .start();
    }
}
