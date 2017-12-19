package com.ecomm.suraj.clothpicker.Model;

import android.graphics.Bitmap;

/**
 * Created by surajbokankar on 07/01/17.
 */

public interface StyleInteractorInterface {

    void getRecommendedPair();

    void saveBookMarkedPair(Bitmap[] imageArray);

    void getNewPair();

    void getAllClothList();

    void getAllBookMarkedPair();


}
