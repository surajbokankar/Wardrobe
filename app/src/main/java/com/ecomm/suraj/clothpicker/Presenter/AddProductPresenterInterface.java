package com.ecomm.suraj.clothpicker.Presenter;

import com.ecomm.suraj.clothpicker.Model.AddShirtsPantsInterface;

/**
 * Created by surajbokankar on 05/01/17.
 */

public interface AddProductPresenterInterface extends AddShirtsPantsInterface {

    void callCameraApiToTakePicture();

    void  openGalleryToUploadPhotoFrom();
}
