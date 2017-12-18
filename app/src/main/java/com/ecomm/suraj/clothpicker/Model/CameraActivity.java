package com.ecomm.suraj.clothpicker.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Created by surajbokankar on 05/01/17.
 */

public class CameraActivity {

    public   static Context mContext;
    private static CameraActivity cameraActivity=null;
   private static AddShirtsPantsInterface listenerInterface;
    public CameraActivity(){

    }


    public  static CameraActivity getInstance(Context context,AddShirtsPantsInterface shirtsPantsInterface){
        context=context;
        listenerInterface=shirtsPantsInterface;
        if(cameraActivity==null){
            cameraActivity=new CameraActivity();
        }
        return cameraActivity;
    }




}
