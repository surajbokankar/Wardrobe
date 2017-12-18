package com.ecomm.suraj.clothpicker.addcloth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ecomm.suraj.clothpicker.Model.AddShirtsPantsInterface;
import com.ecomm.suraj.clothpicker.Model.CameraActivity;
import com.ecomm.suraj.clothpicker.R;
import com.ecomm.suraj.clothpicker.utils.CommonUtility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;

/**
 * Created by surajbokankar on 04/01/17.
 */

public class AddShirtPants  extends Fragment implements View.OnClickListener{


    AppCompatButton  addShirtsView;

    AppCompatButton addPantsView;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private  int REQUEST_TYPE;

    public static final String TAG = "AddShirtPants";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.add_new_cloths_layout,container,false);
        addShirtsView= (AppCompatButton) view.findViewById(R.id.addShirts);
        addPantsView= (AppCompatButton) view.findViewById(R.id.addPants);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: Called=True");
        initview();
    }

    private void initview() {
        addShirtsView.setOnClickListener(this);
        addPantsView.setOnClickListener(this);
    }

    public static Fragment getInstance() {
        AddShirtPants addShirts=new AddShirtPants();

        return addShirts;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addPants:
                String titleShirts=getContext().getResources().getString(R.string.addshirt);
                REQUEST_TYPE=0;
                pickImageForUser(titleShirts);
                break;

            case R.id.addShirts:
                String  titlePants=getContext().getResources().getString(R.string.addPants);
                REQUEST_TYPE=1;
                pickImageForUser(titlePants);
                break;

        }
    }

    public void  pickImageForUser(String title){
        Dialog dialog=new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.select_option_dialog_layout);

        AppCompatTextView  addShirtsPantsTitle= (AppCompatTextView) dialog.findViewById(R.id.option_select_dialog_title);
        AppCompatButton    clickPhoto= (AppCompatButton) dialog.findViewById(R.id.click_photo);
        
        AppCompatButton   uploadPhoto= (AppCompatButton) dialog.findViewById(R.id.upload_photo);
        
        addShirtsPantsTitle.setText(title);
        
        clickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if( CommonUtility.getInstance().checkPermission(getContext())){
                   cameraIntent();
               }


            }
        });
        
        
        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( CommonUtility.getInstance().checkPermission(getContext())){
                    openGalleryIntent();
                }

            }
        });
        

        dialog.show();


    }

    private void openGalleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                new UserOptionSelection(getActivity()).onSelectFromGalleryResult(data,REQUEST_TYPE);
            else if (requestCode == REQUEST_CAMERA)
                new UserOptionSelection(getActivity()).onCaptureImageResult(data,REQUEST_TYPE);
        }
    }


}
