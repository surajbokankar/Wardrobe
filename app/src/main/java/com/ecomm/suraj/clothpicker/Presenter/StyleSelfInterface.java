package com.ecomm.suraj.clothpicker.Presenter;

import android.graphics.Bitmap;

import com.ecomm.suraj.clothpicker.Model.UserSelectedCloths;

import java.util.ArrayList;

/**
 * Created by surajbokankar on 05/01/17.
 */

public interface StyleSelfInterface {

    void onStylePairedSuccess(Bitmap shirt, Bitmap pant);
    void onStylePairedFailure(String error);

    void onDislikePairSuccess(Bitmap shirt,Bitmap pant);

    void onBookMarkedClick(String message);

    void getAllClothsList(UserSelectedCloths shirts);


}
