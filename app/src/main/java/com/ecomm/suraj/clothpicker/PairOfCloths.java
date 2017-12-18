package com.ecomm.suraj.clothpicker;

import android.app.Activity;
import android.app.AlarmManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by surajbokankar on 01/01/17.
 */

public class PairOfCloths extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);
    }

    private static final String TAG = "PairOfCloths";
    /*public static Bitmap appendImages(ArrayList<Bitmap> bitmaplist) {

        //assume bitmaplist has atleast 1 bitmap
       *//* Log.i(TAG, "appendImages: Values="+bitmaplist.size());
        Log.i(TAG, "appendImages: Values="+bitmaplist.get(0));
        Bitmap tmpbitmap = bitmaplist.get(0);

        int w = tmpbitmap.getWidth();
        int h = tmpbitmap.getHeight();

        Bitmap new_image = Bitmap.createBitmap(w, h * bitmaplist.size(),
                tmpbitmap.getConfig());
        int[] pixels = new int[w * (h * bitmaplist.size())];

        int j = 0;

        for (Bitmap uri : bitmaplist) {

            Bitmap mBitmap = uri;

            mBitmap.getPixels(pixels, j * h * w, w, 0, 0, w, h);
            j++;

        }
        new_image.setPixels(pixels, 0, w, 0, 0, w, h * bitmaplist.size());

        String filepath = "/sdcard.jpg";
        File imagefile = new File(filepath);
        try {
            FileOutputStream fos = new FileOutputStream(imagefile);
            new_image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {

            Log.e("Error in File append", e.getMessage());
        }



        return new_image;*//*

    }*/



}
