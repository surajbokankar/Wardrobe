package com.ecomm.suraj.clothpicker.addcloth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import com.ecomm.suraj.clothpicker.dbhlper.DataBaseHelper;
import com.ecomm.suraj.clothpicker.utils.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.security.AccessController.getContext;

/**
 * Created by surajbokankar on 05/01/17.
 */

public class UserOptionSelection {


    DataBaseHelper baseHelper=null;

    private Context context;
    public UserOptionSelection(Context context){
        this.context=context;
        baseHelper=new DataBaseHelper(context,DataBaseHelper.DB_Name,null,DataBaseHelper.DB_Version);
    }




    public void onCaptureImageResult(Intent data ,int request_type) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(request_type==0){
            baseHelper.addShirtAndPantsIntoDB(bytes.toByteArray(), Constants.PANT);
        } else if(request_type==1){
            baseHelper.addShirtAndPantsIntoDB(bytes.toByteArray(), Constants.SHIRT);

        }

    }

    @SuppressWarnings("deprecation")
    public void onSelectFromGalleryResult(Intent data,int request_type) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                if(request_type==0){
                    baseHelper.addShirtAndPantsIntoDB(bytes.toByteArray(), Constants.PANT);
                } else if(request_type==1){
                    baseHelper.addShirtAndPantsIntoDB(bytes.toByteArray(), Constants.SHIRT);

                }            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
