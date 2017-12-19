package com.ecomm.suraj.clothpicker.dbhlper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.provider.SyncStateContract;
import android.util.Log;

import com.ecomm.suraj.clothpicker.Model.UserSelectedCloths;
import com.ecomm.suraj.clothpicker.utils.Constants;

import java.util.ArrayList;

/**
 * Created by surajbokankar on 04/01/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String DB_Name = "demo.db";
    public static int DB_Version = 1;
    private String TABLE_NAME = "USER";
    private String IMAGE_BITMAP = "image";
    private String TYPE = "type";
    private String TABLE_NAME_BOOKMARK = "Bookmark";

    private static final String TAG = "DataBaseHelper";


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_Name, factory, DB_Version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + IMAGE_BITMAP + " BLOB," + TYPE + " TEXT)";
        Log.i(TAG, "onCreate: Query="+query);
        String bookmarkQuery = "CREATE TABLE " + TABLE_NAME_BOOKMARK + " (" + IMAGE_BITMAP + " BLOB)";
        Log.i(TAG, "onCreate: Query bookMark="+bookmarkQuery);
        sqLiteDatabase.execSQL(query);

        sqLiteDatabase.execSQL(bookmarkQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE" + TABLE_NAME + " IF EXISTS";
        String bookmarkQuery = "DROP TABLE" + TABLE_NAME_BOOKMARK + " IF EXISTS";
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(bookmarkQuery);

    }


    public void addShirtAndPantsIntoDB(byte[] imageBitmap, String type) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IMAGE_BITMAP, imageBitmap);

        cv.put(TYPE, type);

        db.insert(TABLE_NAME, null, cv);

        Log.i(TAG, "addShirtAndPantsIntoDB: CV=" + cv);


    }


    public void addBookmarkedPair(byte[] imageBitmap) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IMAGE_BITMAP, imageBitmap);

        db.insert(TABLE_NAME_BOOKMARK, null, cv);

        Log.i(TAG, "addBookMarkedPAir: CV=" + cv);

    }


    public UserSelectedCloths getAddedClothsFromDB() {


        UserSelectedCloths userSelectedCloths=new UserSelectedCloths();
        ArrayList<byte[]> Shirts = new ArrayList<>();

        ArrayList<byte[]> Pants = new ArrayList<>();

        Cursor cursor = null;

        SQLiteDatabase db = getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                byte[] values = cursor.getBlob(0);

                String type = cursor.getString(1);
                if (type.equalsIgnoreCase(Constants.PANT)) {
                    Pants.add(values);
                }
                if (type.equalsIgnoreCase(Constants.SHIRT)) {
                    Shirts.add(values);
                }

            } while (cursor.moveToNext());
        }

        userSelectedCloths.addedShirtAndTShirt = Shirts;
        userSelectedCloths.addedPants = Pants;

        return userSelectedCloths;

    }


    public UserSelectedCloths getBookMarkedPairDetails() {


        boolean isBookMarkedPair=false;
        ArrayList<byte[]> bookMarkedList = new ArrayList<>();

        Cursor cursor = null;

        SQLiteDatabase db = getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_BOOKMARK, null);

        if (cursor.moveToFirst()) {
            do {
                byte[] values = cursor.getBlob(0);
                bookMarkedList.add(values);

            } while (cursor.moveToNext());
        }
        UserSelectedCloths cloths=new UserSelectedCloths();
        cloths.bookMarkedPair = bookMarkedList;

        if(bookMarkedList!=null&&bookMarkedList.size()>0){
            isBookMarkedPair=true;
        }
        cloths.isBookMarked=isBookMarkedPair;
        return  cloths;

    }


}
