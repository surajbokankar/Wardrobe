package com.ecomm.suraj.clothpicker.login;

/**
 * Created by surajbokankar on 03/01/17.
 */

public interface OnGoogleSignInListener {

    void googleSignInSuccess();
    void googleSignInFailed(String error);

}
