package com.ecomm.suraj.clothpicker.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.ecomm.suraj.clothpicker.Model.StyleselfInteractor;
import com.ecomm.suraj.clothpicker.Model.UserSelectedCloths;

import java.util.ArrayList;

/**
 * Created by surajbokankar on 07/01/17.
 */

public class MyStylePresenter implements StylePresenterInterface {

    Context context;
    StyleSelfInterface styleSelfInterface;
    StyleselfInteractor styleselfInteractor;
    private static final String TAG = "MyStylePresenter";

    public MyStylePresenter(Context context,StyleSelfInterface selfInterface){
    this.context=context;
        this.styleSelfInterface=selfInterface;
        styleselfInteractor=new StyleselfInteractor(context,this);
    }


    @Override
    public void getPairToWear() {
     styleselfInteractor.getRecommendedPair();
    }

    @Override
    public void savePairAsBookMarked(Bitmap[] imageArray) {
styleselfInteractor.saveBookMarkedPair(imageArray);
    }

    @Override
    public void getNewPairOnDiscard() {
       styleselfInteractor.getNewPair();
    }

    @Override
    public void getAllCloths() {
        styleselfInteractor.getAllClothList();
    }

    @Override
    public void getBookMarkedList() {
        styleselfInteractor.getAllBookMarkedPair();
    }

    @Override
    public void onStylePairedSuccess(Bitmap shirt, Bitmap pant) {
        Log.i(TAG, "onStylePairedSuccess: onIntractorSucces="+shirt);
       styleSelfInterface.onStylePairedSuccess(shirt,pant);
    }

    @Override
    public void onStylePairedFailure(String error) {
     styleSelfInterface.onStylePairedFailure(error);
    }

    @Override
    public void onDislikePairSuccess(Bitmap shirt,Bitmap pant) {
     styleSelfInterface.onDislikePairSuccess(shirt,pant);
    }

    @Override
    public void onBookMarkedClick(String message) {
      styleSelfInterface.onBookMarkedClick(message);
    }

    @Override
    public void getAllClothsList(UserSelectedCloths shirts) {
      styleSelfInterface.getAllClothsList(shirts);
    }

    @Override
    public void onBookMarkListSuccess(UserSelectedCloths cloths) {
        styleSelfInterface.onBookMarkListSuccess(cloths);
    }


}
