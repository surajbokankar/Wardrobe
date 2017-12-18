package com.ecomm.suraj.clothpicker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.LoginFilter;

import com.ecomm.suraj.clothpicker.addcloth.AddCloths;
import com.ecomm.suraj.clothpicker.login.LoginActivity;
import com.ecomm.suraj.clothpicker.login.SocialLoginActivity;

/**
 * Created by surajbokankar on 02/01/17.
 */

public class SplashActivity  extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        initView();
    }

    private void initView() {



       Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    Intent intent=new Intent(SplashActivity.this,AddCloths.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();




    }
}
