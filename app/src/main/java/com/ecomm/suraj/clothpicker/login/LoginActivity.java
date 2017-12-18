package com.ecomm.suraj.clothpicker.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.ecomm.suraj.clothpicker.R;
import com.ecomm.suraj.clothpicker.addcloth.AddCloths;
import com.ecomm.suraj.clothpicker.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by surajbokankar on 02/01/17.
 */

public class LoginActivity  extends AppCompatActivity  implements View.OnClickListener,OnFacebookLoginSuccessListener,OnGoogleSignInListener{

    @BindView(R.id.mButtonFacebook)
    AppCompatButton faceBookButton;
    @BindView(R.id.mButtonGoogle)
    AppCompatButton googleButton;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.socila_login_activity);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
         faceBookButton.setOnClickListener(this);
         googleButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mButtonFacebook:
                FacebookManager.getInstance(LoginActivity.this).login(LoginActivity.this,this);
                break;
            case R.id.mButtonGoogle:
               GoogleSignInManager.getGoogleSignInInstance(LoginActivity.this).signIn(LoginActivity.this);

                break;
        }
    }

    @Override
    public void onFacebookLoginSuccess() {
        Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_LONG).show();
        afterSuccessRedirectToMainPage();
    }

    @Override
    public void onFacebookLoginFailure() {
      Toast.makeText(LoginActivity.this,"Login Failure",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== Constants.SIGN_IN){
           GoogleSignInManager.getGoogleSignInInstance(LoginActivity.this).onActivityResult(data,this);
        }else{
            FacebookManager.getInstance(LoginActivity.this).onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void googleSignInSuccess() {
      Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
        afterSuccessRedirectToMainPage();
    }

    @Override
    public void googleSignInFailed(String error) {
     Toast.makeText(LoginActivity.this,error,Toast.LENGTH_LONG).show();
    }


    public void  afterSuccessRedirectToMainPage(){

        Intent intent=new Intent(LoginActivity.this, AddCloths.class);
            startActivity(intent);


    }
}
