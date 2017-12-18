package com.ecomm.suraj.clothpicker.addcloth.listener;

import com.ecomm.suraj.clothpicker.addcloth.model.ClothModel;

import java.util.ArrayList;

/**
 * Created by suraj.bokankar on 17/12/17.
 */

public interface OnItemSelectedListner {
    void onItemSelectedListener(ArrayList<ClothModel> imageArray,int position);
    void onShirtItemSelectedListener(ArrayList<ClothModel> imageArray,int position);
}
