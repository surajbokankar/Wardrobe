package com.ecomm.suraj.clothpicker.addcloth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ecomm.suraj.clothpicker.Model.UserSelectedCloths;
import com.ecomm.suraj.clothpicker.Presenter.MyStylePresenter;
import com.ecomm.suraj.clothpicker.Presenter.StyleSelfInterface;
import com.ecomm.suraj.clothpicker.R;
import com.ecomm.suraj.clothpicker.addcloth.adapter.AllPantsAdapter;
import com.ecomm.suraj.clothpicker.addcloth.adapter.AllShirstAdapter;
import com.ecomm.suraj.clothpicker.addcloth.listener.OnItemSelectedListner;
import com.ecomm.suraj.clothpicker.addcloth.model.ClothModel;
import com.ecomm.suraj.clothpicker.dbhlper.DataBaseHelper;
import com.ecomm.suraj.clothpicker.utils.CommonUtility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by surajbokankar on 04/01/17.
 */

public class StyleYourSelf extends Fragment implements StyleSelfInterface, View.OnClickListener,OnItemSelectedListner {

    @BindView(R.id.pair_cloth_image_pants)
    AppCompatImageView suggestedPantImage;
    @BindView(R.id.pair_cloth_image_shirts)
    AppCompatImageView suggestedShirtImage;
    RecyclerView pantRecycler;
    RecyclerView shirtRecycler;


    AppCompatImageView addPants, addShirts,likePair;

    AppCompatImageView shufflePairOfCloths;
    public static final String TAG = "StyleYourSelf";
    DataBaseHelper dataBaseHelper;
    MyStylePresenter presenter;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private int REQUEST_TYPE;
    AllPantsAdapter pantsAdapter=null;
    AllShirstAdapter shirtsAdapter=null;

    ArrayList<ClothModel> pantList =null;
    ArrayList<ClothModel> shirtList =null;

    static StyleYourSelf fragment = null;

    public static StyleYourSelf getInstance() {
        if (fragment == null) {
            fragment = new StyleYourSelf();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.today_wear_cloth_layout, container, false);
       // suggestedPantImage = (AppCompatImageView) view.findViewById(R.id.pair_cloth_image_shirts);
       // suggestedShirtImage = (AppCompatImageView) view.findViewById(R.id.pair_cloth_image_pants);

        addPants = (AppCompatImageView) view.findViewById(R.id.add_pant_button);
        shufflePairOfCloths = (AppCompatImageView) view.findViewById(R.id.pair_shuffle_button);
        addShirts = (AppCompatImageView) view.findViewById(R.id.add_shirts_button);
        likePair= (AppCompatImageView) view.findViewById(R.id.like_button);


        pantRecycler= (RecyclerView) view.findViewById(R.id.pants_recycler_view);
        shirtRecycler= (RecyclerView) view.findViewById(R.id.shirts_recycler_view);

        addPants.setOnClickListener(this);
        shufflePairOfCloths.setOnClickListener(this);
        addShirts.setOnClickListener(this);
        likePair.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(getActivity());
        initViews();
    }


    private void initViews() {

        pantList =new ArrayList<>();
        shirtList=new ArrayList<>();

        dataBaseHelper = new DataBaseHelper(getContext(), DataBaseHelper.DB_Name, null, DataBaseHelper.DB_Version);

        presenter = new MyStylePresenter(getActivity(), this);

        presenter.getPairToWear();
        presenter.getAllCloths();



    }


    @Override
    public void onStylePairedSuccess(Bitmap shirt, Bitmap pant) {

        Bitmap comboBitmap;

        int width, height;

        width = shirt.getWidth() + pant.getWidth();
        height = shirt.getHeight() > pant.getHeight() ? shirt.getHeight() : pant.getHeight();
        comboBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(comboBitmap);


        comboImage.drawBitmap(shirt, 0f, 0f, null);
        comboImage.drawBitmap(pant, shirt.getWidth(), 0f, null);

//        suggestedPantImage.setImageBitmap(comboBitmap);
  //      suggestedShirtImage.setVisibility(View.GONE);

    }

    @Override
    public void onStylePairedFailure(String error) {

    }

    @Override
    public void onDislikePairSuccess(Bitmap shirt, Bitmap pant) {
        Bitmap comboBitmap;

        int width, height;

        width = shirt.getWidth() + pant.getWidth();
        height = shirt.getHeight() > pant.getHeight() ? shirt.getHeight() : pant.getHeight();
        comboBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(comboBitmap);


        comboImage.drawBitmap(shirt, 0f, 0f, null);
        comboImage.drawBitmap(pant, shirt.getWidth(), 0f, null);

        suggestedPantImage.setImageBitmap(comboBitmap);
    }

    @Override
    public void onBookMarkedClick(String message) {
        Snackbar.make(getActivity().findViewById(R.id.pair_cloth_alyout), "Had a snack at Snackbar", Snackbar.LENGTH_LONG)
                .setActionTextColor(getActivity().getResources().getColor(R.color.white))
                .show();
    }

    @Override
    public void getAllClothsList(UserSelectedCloths shirts) {
          if(shirts!=null){
               createShirRecyclerView(shirts);
               createPantRecyclerView(shirts);
          }
    }

    @Override
    public void onBookMarkListSuccess(UserSelectedCloths cloths) {

    }

    private void createPantRecyclerView(UserSelectedCloths userSelectedCloths) {
        shirtRecycler.setLayoutManager(CommonUtility.getInstance().getHorizontalLayoutManager(getContext()));
        shirtList=getPantList(userSelectedCloths.addedShirtAndTShirt);
        Log.i(TAG, "createPantRecyclerView: shirtList="+shirtList.get(0).imageArray);
        shirtsAdapter=new AllShirstAdapter(shirtList,this);
        shirtRecycler.setAdapter(shirtsAdapter);
    }

    private ArrayList<ClothModel> getPantList(ArrayList<byte[]> addedPants) {
    ArrayList<ClothModel> clothModels=new ArrayList<>();
    for(int i=0;i<addedPants.size();i++){
        ClothModel clothModel=new ClothModel();
        clothModel.imageArray=addedPants.get(i);
        clothModels.add(clothModel);
    }
    return clothModels;
    }


    private void createShirRecyclerView(UserSelectedCloths userSelectedCloths) {
        pantRecycler.setLayoutManager(CommonUtility.getInstance().getHorizontalLayoutManager(getContext()));
        pantList=getPantList(userSelectedCloths.addedPants);
        pantsAdapter=new AllPantsAdapter(pantList,this);
        pantRecycler.setAdapter(pantsAdapter);
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick: ");
        switch (view.getId()) {

            case R.id.pair_shuffle_button:
                presenter.getNewPairOnDiscard();
                break;

            case R.id.add_pant_button:
                REQUEST_TYPE = 0;
                pickImageForUser("Add Shirts");
                break;

            case R.id.add_shirts_button:
                REQUEST_TYPE = 1;
                Log.i(TAG, "onClick: Discard");
                pickImageForUser("Add Pants");
                break;

            case R.id.like_button:

              //  likePair.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.ic_like_after));
                saveLikedPair();

                break;

        }
    }

    private void saveLikedPair() {
        Bitmap[] likePairedBitmap=null;
        Bitmap shirts=getBitmap(shirtList);
        Bitmap pants=getBitmap(pantList);
        if(shirts!=null&&pants!=null){
            likePairedBitmap=new Bitmap[]{shirts,pants};
            presenter.savePairAsBookMarked(likePairedBitmap);
            CommonUtility.getInstance().showToast(getContext(),"Perfect Matched");
        }
    }


    public void pickImageForUser(String title) {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.select_option_dialog_layout);

        AppCompatTextView addShirtsPantsTitle = (AppCompatTextView) dialog.findViewById(R.id.option_select_dialog_title);
        AppCompatButton clickPhoto = (AppCompatButton) dialog.findViewById(R.id.click_photo);

        AppCompatButton uploadPhoto = (AppCompatButton) dialog.findViewById(R.id.upload_photo);

        addShirtsPantsTitle.setText(title);

        clickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonUtility.getInstance().checkPermission(getContext())) {
                    cameraIntent();
                }


            }
        });


        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonUtility.getInstance().checkPermission(getContext())) {
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
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
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
                new UserOptionSelection(getActivity()).onSelectFromGalleryResult(data, REQUEST_TYPE);
            else if (requestCode == REQUEST_CAMERA)
                new UserOptionSelection(getActivity()).onCaptureImageResult(data, REQUEST_TYPE);
        }
    }


    private Bitmap getBitmap(ArrayList<ClothModel> list) {
        Bitmap bitmap = null;
        for(ClothModel model:list){
            if(model.isSelected){
                 bitmap= BitmapFactory.decodeByteArray(model.imageArray,0,model.imageArray.length);
            }
        }
        return bitmap;
    }

    @Override
    public void onItemSelectedListener(ArrayList<ClothModel> imageArray,int pos) {
        pantList=imageArray;
        pantsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShirtItemSelectedListener(ArrayList<ClothModel> imageArray,int pos) {
      shirtList=imageArray;
      shirtsAdapter.notifyDataSetChanged();
    }
}
