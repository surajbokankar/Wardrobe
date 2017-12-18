package com.ecomm.suraj.clothpicker.Presenter;

import android.graphics.Bitmap;

import com.ecomm.suraj.clothpicker.Model.UserSelectedCloths;

/**
 * Created by surajbokankar on 05/01/17.
 */

public interface StylePresenterInterface extends StyleSelfInterface {

    void getPairToWear();

    void savePairAsBookMarked(Bitmap[] imageArray);

    void getNewPairOnDiscard();

    void getAllCloths();

}
