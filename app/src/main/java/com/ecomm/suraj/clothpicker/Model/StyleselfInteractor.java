package com.ecomm.suraj.clothpicker.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.ecomm.suraj.clothpicker.Presenter.StyleSelfInterface;
import com.ecomm.suraj.clothpicker.dbhlper.DataBaseHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by surajbokankar on 07/01/17.
 */

public class StyleselfInteractor  implements StyleInteractorInterface {

    Context context;
    StyleSelfInterface presenterInterface;
    DataBaseHelper helper;
    private static final String TAG = "StyleselfInteractor";
    public StyleselfInteractor(Context context, StyleSelfInterface stylePresenterInterface){
      this.context=context;
        this.presenterInterface=stylePresenterInterface;
        helper=new DataBaseHelper(context,DataBaseHelper.DB_Name,null,DataBaseHelper.DB_Version);

    }


    @Override
    public void getRecommendedPair() {


        UserSelectedCloths cloths=helper.getAddedClothsFromDB();
        ArrayList<byte[]> pantList=cloths.addedPants;
        ArrayList<byte[]> shirtList=cloths.addedShirtAndTShirt;
        if((pantList!=null&&pantList.size()>0)&&(shirtList!=null&&shirtList.size()>0)){
            getRandomPair(pantList,shirtList,0);
        }else{
            presenterInterface.onStylePairedFailure("Please Enter Cloths");
        }

    }

    @Override
    public void saveBookMarkedPair(Bitmap[] imageArray) {

        Bitmap bitmap=mergeMultiple(imageArray);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        helper.addBookmarkedPair(byteArray);

        if(helper.getBookMarkedPairDetails().isBookMarked){
            presenterInterface.onBookMarkedClick("Pair Added To BookMark List");
        }



    }

    private Bitmap mergeMultiple(Bitmap[] parts){

        Bitmap result = Bitmap.createBitmap(parts[0].getWidth() * 2, parts[0].getHeight() * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        for (int i = 0; i < parts.length; i++) {
            canvas.drawBitmap(parts[i], parts[i].getWidth() * (i % 2), parts[i].getHeight() * (i / 2), paint);
        }
        return result;
    }

    @Override
    public void getNewPair() {
        Log.i(TAG, "getNewPair: Called");
        UserSelectedCloths userSelectedCloths=new UserSelectedCloths();
        ArrayList<byte[]> pantList=userSelectedCloths.addedPants;
        ArrayList<byte[]> shirtList=userSelectedCloths.addedShirtAndTShirt;
        if((pantList!=null&&pantList.size()>0)&&(shirtList!=null&&shirtList.size()>0)){
            getRandomPair(pantList,shirtList,1);
        }else{
            presenterInterface.onStylePairedFailure("Please Enter Cloths");
        }

    }

    @Override
    public void getAllClothList() {
        UserSelectedCloths cloths=helper.getAddedClothsFromDB();
        ArrayList<byte[]> pantList=cloths.addedPants;
        ArrayList<byte[]> shirtList=cloths.addedShirtAndTShirt;
        presenterInterface.getAllClothsList(cloths);
    }

    @Override
    public void getAllBookMarkedPair() {
        UserSelectedCloths cloths=helper.getBookMarkedPairDetails();
        presenterInterface.onBookMarkListSuccess(cloths);
    }


    private void getRandomPair(ArrayList<byte[]> pantList,ArrayList<byte[]> shirtList,int state) {

        Random rand = new Random();

        Collections.shuffle(pantList);
        Collections.shuffle(shirtList);

        byte[] pantValue = pantList.get(rand.nextInt(pantList.size()));

        byte[] shirtValue = shirtList.get(rand.nextInt(shirtList.size()));


      Bitmap pantBitmap = BitmapFactory.decodeByteArray(pantValue, 0, pantValue.length);

      Bitmap shirtBitmap= BitmapFactory.decodeByteArray(shirtValue,0,shirtValue.length);


         shirtBitmap= scaledBitmap(shirtBitmap);
         pantBitmap= scaledBitmap(pantBitmap);

        if(state==0){
            Log.i(TAG, "getRandomPair: StylePair Success");
            presenterInterface.onStylePairedSuccess(shirtBitmap,pantBitmap);

        }else if(state==1){
            Log.i(TAG, "getRandomPair: Discard Pair Success");
            presenterInterface.onDislikePairSuccess(shirtBitmap,pantBitmap);
        }


    }

    private Bitmap scaledBitmap(Bitmap bitmap) {
        final int maxSize = 900;
        int outWidth;
        int outHeight;
        int inWidth = bitmap.getWidth();
        int inHeight = bitmap.getHeight();



        if(inWidth > inHeight){
            outWidth = maxSize;
            outHeight = (inHeight * maxSize) / inWidth;
        } else {
            outHeight = maxSize;
            outWidth = (inWidth * maxSize) / inHeight;
        }

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, outWidth, outHeight, false);
        return  resizedBitmap;
    }



}
