package com.ecomm.suraj.clothpicker;

import android.Manifest;
import android.app.AlarmManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.Image;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.ecomm.suraj.clothpicker.utils.CommonUtility;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.resultImage)
    ImageView resultPair;
    @BindView(R.id.image1)
    ImageView likeImageView;
    @BindView(R.id.image2)
     ImageView bookmark;
    ArrayList<Bitmap> bitmapArrayList;
    @BindView(R.id.clickPhoto)
    AppCompatButton clickPhoto;
    @BindView(R.id.imagePickerLayout)
    RelativeLayout imagePickerRelativeLayout;
    final int CAMERA_REQUEST=100;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socila_login_activity);

        ButterKnife.bind(this);

        clickPhoto.setOnClickListener(this);

    }




    private void initViews() {

       /* likeImageView.buildDrawingCache();
        bookmark.buildDrawingCache();

        Bitmap likeBitmap =Bitmap.createBitmap(likeImageView.getDrawingCache(true)); //i is imageview whch u want to convert in bitmap

        Bitmap heartBitmap=Bitmap.createBitmap(bookmark.getDrawingCache());
        bitmapArrayList=new ArrayList<>();
        bitmapArrayList.add(likeBitmap);
        bitmapArrayList.add(heartBitmap);

      //  Bitmap pairBitmap=PairOfCloths.appendImages(bitmapArrayList);

        resultPair.setImageBitmap(pairBitmap);
*/
    }

    private Bitmap getImageBitmap(ImageView imageView) {
        Bitmap bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.RGB_565);

        return bitmap;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case  R.id.clickPhoto:
                if (CommonUtility.getInstance().checkPermission(MainActivity.this)) {
                    Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent,CAMERA_REQUEST);
                }else{
                    Snackbar.make(findViewById(R.id.imagePickerLayout),"Please Grant Permission",Snackbar.LENGTH_LONG).show();
                }
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST && resultCode==RESULT_OK){

            Bitmap imageBitmap= (Bitmap) data.getExtras().get("Data");

            Log.i(TAG, "onActivityResult: In Result Code="+data.getExtras()+"\n"+data.getData().getPathSegments());

            resultPair.setImageBitmap(imageBitmap);


        }
    }





}
