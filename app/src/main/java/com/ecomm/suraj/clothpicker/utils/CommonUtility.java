package com.ecomm.suraj.clothpicker.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by surajbokankar on 01/01/17.
 */

public class CommonUtility {
    private static final String TAG = "CommonUtility";

    private static final int REQUEST_PERMISSIONS = 0;


    static CommonUtility commonUtility=null;

    public static CommonUtility getInstance(){
        if(commonUtility==null){
            commonUtility=new CommonUtility();
        }
        return commonUtility;
    }

    /*public static boolean checkRuntimePermission(Context context) {

        final Activity parentActivity = (Activity) context;

        boolean permissionEnabled = false;

        if (ContextCompat.checkSelfPermission(parentActivity,
                Manifest.permission.CAMERA) + ContextCompat
                .checkSelfPermission(parentActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (parentActivity, Manifest.permission.CAMERA)){
                if( ActivityCompat.shouldShowRequestPermissionRationale
                        (parentActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                {
                    Log.i(TAG, "checkRuntimePermission: Permission First Block=true");
                    ActivityCompat.requestPermissions(parentActivity,
                            new String[]{Manifest.permission
                                    .CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_PERMISSIONS);


                } else{
                    Log.i(TAG, "checkRuntimePermission: no External Storage");
                }
            }
              else {
                Log.i(TAG, "checkRuntimePermission: Permission Second Block=true ");
                ActivityCompat.requestPermissions(parentActivity,
                        new String[]{Manifest.permission
                                .WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        REQUEST_PERMISSIONS);
            }
        } else {

            permissionEnabled = true;
        }

        return permissionEnabled;
    }*/

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public  boolean checkPermission(final Context context)
    {
        boolean isPermitted=false;
        try{
            int PERMISSION_ALL = 1;
            String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS, Manifest.permission.CAMERA};
            Activity activity= (Activity) context;
            if(!hasPermissions(activity, PERMISSIONS)){
                ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_ALL);
                isPermitted=true;
            }else {
                isPermitted=false;
            }
        }catch (Exception e){
            Log.i(TAG, "checkPermission: Error="+e.getMessage());
        }
        return isPermitted;

    }

    public boolean hasPermissions(Context context, String... permissions) {
        try{
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
                for (String permission : permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                        return false;
                    }
                }
            }
        }catch (Exception e){
            Log.i(TAG, "hasPermissions: Error="+e.getMessage());
        }

        return true;
    }


    public LinearLayoutManager getHorizontalLayoutManager(Context context){
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        return  horizontalLayoutManagaer;
    }


    public  LinearLayoutManager getVerticalLayoutManager(Context context){
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        return  horizontalLayoutManagaer;
    }

    public void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
