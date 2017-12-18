package com.ecomm.suraj.clothpicker.Presenter;

import android.content.ContentValues;
import android.content.Context;

import com.ecomm.suraj.clothpicker.Model.AddProductInteractor;
import com.ecomm.suraj.clothpicker.Model.AddShirtsPantsInterface;

/**
 * Created by surajbokankar on 05/01/17.
 */

public class AddProductPresenter  implements AddProductPresenterInterface{

   Context context;
    AddShirtsPantsInterface listener;
    AddProductInteractor interactor;

   public  AddProductPresenter(Context context,AddShirtsPantsInterface shirtsPantsInterface){

       this.context=context;
       this.listener=shirtsPantsInterface;
       interactor=new AddProductInteractor(context,this);
   }



    @Override
    public void callCameraApiToTakePicture() {

    }

    @Override
    public void openGalleryToUploadPhotoFrom() {

    }

    @Override
    public void onCameraPhotoCaptureSuccess(String capturedSuccessFull) {

    }

    @Override
    public void onCameraPhotoCaptureFailure(String error) {

    }

    @Override
    public void onImageUploadSuccess(String success) {

    }

    @Override
    public void onImageUploadFailure(String error) {

    }
}
