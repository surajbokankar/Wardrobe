package com.ecomm.suraj.clothpicker.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ecomm.suraj.clothpicker.R;
import com.ecomm.suraj.clothpicker.utils.Constants;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

/**
 * Created by surajbokankar on 03/01/17.
 */

public class GoogleSignInManager implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "GoogleSignInManager";

    private static GoogleSignInManager mGoogleInstance;
    private GoogleApiClient mGoogleApiClient;
    Context mContext;
    OnGoogleSignInListener listener;


    private GoogleSignInManager(Context ctx) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestProfile()
                .requestScopes(new Scope("https://www.googleapis.com/auth/userinfo.profile"))
                .requestServerAuthCode(ctx.getString(R.string.google_server_client_id), false)
                .requestIdToken(ctx.getString(R.string.google_server_client_id))
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(ctx)
                .enableAutoManage((FragmentActivity) ctx, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        this.mContext=ctx;
    }

    public static GoogleSignInManager getGoogleSignInInstance(Context context) {
        if (mGoogleInstance == null) {
            mGoogleInstance = new GoogleSignInManager(context);
        }
        return mGoogleInstance;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectionFailed: " + connectionResult.getErrorMessage());

    }

    public void signIn(AppCompatActivity context) {

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        context.startActivityForResult(intent, Constants.SIGN_IN);
    }



    public void onActivityResult(Intent data,OnGoogleSignInListener listener){

        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        Log.i(TAG, "onActivityResult: Result="+result.isSuccess());
        if (result != null && result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            String token = acct.getIdToken();
            listener.googleSignInSuccess();

        } else {
            listener.googleSignInFailed("Login Failed");
        }

    }


}
