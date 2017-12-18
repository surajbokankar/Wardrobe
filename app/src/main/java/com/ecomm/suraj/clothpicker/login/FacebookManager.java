package com.ecomm.suraj.clothpicker.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.sql.Array;
import java.util.Arrays;

/**
 * Created by surajbokankar on 03/01/17.
 */

public class FacebookManager {

    private static final String TAG = "FacebookManager";
    private static FacebookManager facebookManager;
    private CallbackManager callbackManager;
    private OnFacebookLoginSuccessListener  onFacebookLoginSuccessListener;
    static Context mContext;
    private static final String EMAIL_PERMISSION = "email";




    private  FacebookManager(){
        FacebookSdk.sdkInitialize(mContext);
        callbackManager=CallbackManager.Factory.create();
        initiateFacebooCallback();
    }

    private void initiateFacebooCallback() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                if(loginResult!=null&&loginResult.getAccessToken()!=null){
                    if(!TextUtils.isEmpty(loginResult.getAccessToken().getToken())){
                        onFacebookLoginSuccessListener.onFacebookLoginSuccess();
                    }
                }
            }

            @Override
            public void onCancel() {
                onFacebookLoginSuccessListener.onFacebookLoginFailure();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "onError: =", error);
            }
        });
    }

    public static FacebookManager getInstance(Context context){
        mContext=context;
        if(facebookManager==null){
            facebookManager=new FacebookManager();
        }
        return  facebookManager;
    }


    public void login(Activity aactivity,OnFacebookLoginSuccessListener onFacebookLoginSuccessListener){
        this.onFacebookLoginSuccessListener=onFacebookLoginSuccessListener;
        LoginManager.getInstance().logInWithReadPermissions(aactivity, Arrays.asList(EMAIL_PERMISSION));


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
