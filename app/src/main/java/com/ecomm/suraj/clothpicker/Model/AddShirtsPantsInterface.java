package com.ecomm.suraj.clothpicker.Model;

/**
 * Created by surajbokankar on 04/01/17.
 */

 public interface AddShirtsPantsInterface {

    void  onCameraPhotoCaptureSuccess(String capturedSuccessFull);

    void onCameraPhotoCaptureFailure(String error);


    void onImageUploadSuccess(String success);

    void onImageUploadFailure(String error);




}
