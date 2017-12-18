package com.ecomm.suraj.clothpicker.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

import com.ecomm.suraj.clothpicker.Presenter.AddProductPresenterInterface;

/**
 * Created by surajbokankar on 05/01/17.
 */

public class AddProductInteractor implements AddProductIntractorInterface {

    Context context;
    AddShirtsPantsInterface presenterInterface;
    public AddProductInteractor(Context context, AddShirtsPantsInterface listener){
        this.context=context;
        this.presenterInterface=listener;
    }


    @Override
    public void callCameraApi() {

        String success="Image Captured Success";

        presenterInterface.onCameraPhotoCaptureSuccess(success);
    }

    @Override
    public void openGallery() {
        String success="Image Upload Success";

        presenterInterface.onImageUploadSuccess(success);
    }


}
