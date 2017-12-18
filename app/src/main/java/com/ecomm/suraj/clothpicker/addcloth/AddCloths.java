package com.ecomm.suraj.clothpicker.addcloth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ecomm.suraj.clothpicker.R;
import com.ecomm.suraj.clothpicker.addcloth.fragments.BookmarkedPairFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by surajbokankar on 04/01/17.
 */

public class AddCloths extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.style_today_layout)
    LinearLayout styleYourSelfLayout;
    @BindView(R.id.add_cloths_layout)
    LinearLayout addClothsLayout;
    @BindView(R.id.style_today)
    AppCompatImageView styleIconImageView;
    @BindView(R.id.style_yourself_TextView)
    AppCompatTextView styleTextView;
    @BindView(R.id.add_cloths_new)
    AppCompatImageView addClothImageView;
    @BindView(R.id.add_new_cloth_TextView)
    AppCompatTextView addNewClothsTextView;
    @BindView(R.id.toolbar_title)
    AppCompatTextView toolBarTitle;
    @BindView(R.id.bookmark_icon_view)
    AppCompatImageView bookMarkImageView;
    @BindView(R.id.bookmark_TextView)
    AppCompatTextView bookMarkTextView;
    @BindView(R.id.loadcontainer)
    FrameLayout homeContainer;
    @BindView(R.id.bookmark_pair_layout)
    LinearLayout bookmarkLayout;
    private static final String TAG = "AddCloths";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_cloths_layout);

        ButterKnife.bind(this);
        initViews();


    }

    private void initViews() {

       changeViewOnTap(2);
        //bookmarkedPairFragment();
        styleYourSelfFragment();

        styleYourSelfLayout.setOnClickListener(this);
        addClothsLayout.setOnClickListener(this);
        bookmarkLayout.setOnClickListener(this);

    }

    private void setDefaultView() {


        addClothImageView.setImageResource(R.drawable.ic_before_add_cloths);
        addNewClothsTextView.setTextColor(ContextCompat.getColor(AddCloths.this, R.color.color_000000));

        styleIconImageView.setImageResource(R.drawable.ic_before_wear_select);
        styleTextView.setTextColor(ContextCompat.getColor(AddCloths.this, R.color.color_000000));

        bookMarkImageView.setImageResource(R.drawable.ic_before_favorite);
        bookMarkTextView.setTextColor(ContextCompat.getColor(AddCloths.this, R.color.color_000000));

    }

    public void changeViewOnTap(int state){

        if(state==1){

            addClothImageView.setImageResource(R.drawable.ic_after_cloth_add);
            addNewClothsTextView.setTextColor(ContextCompat.getColor(AddCloths.this, R.color.color_D80027));
            toolBarTitle.setText(getResources().getString(R.string.AddCloths));
            toolBarTitle.setTextColor(ContextCompat.getColor(AddCloths.this, R.color.color_D80027));

        }
        if(state==2){
            styleIconImageView.setImageResource(R.drawable.ic_after_wear_select);
            styleTextView.setTextColor(ContextCompat.getColor(AddCloths.this, R.color.color_D80027));
            toolBarTitle.setText(getResources().getString(R.string.Today));
            toolBarTitle.setTextColor(ContextCompat.getColor(AddCloths.this, R.color.color_D80027));

        }
        if(state==3){
            bookMarkImageView.setImageResource(R.drawable.ic_after_favorite);
            bookMarkTextView.setTextColor(ContextCompat.getColor(AddCloths.this, R.color.color_D80027));
            toolBarTitle.setText(getResources().getString(R.string.Bookmark));
            toolBarTitle.setTextColor(ContextCompat.getColor(AddCloths.this, R.color.color_D80027));

        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.add_cloths_layout:
                setDefaultView();
                addShirtsandPantsFragment();
                changeViewOnTap(1);
                break;

            case R.id.style_today_layout:
                setDefaultView();
                styleYourSelfFragment();
                changeViewOnTap(2);
                break;
            case R.id.bookmark_pair_layout:
                setDefaultView();
                bookmarkedPairFragment();
                changeViewOnTap(3);
                break;
        }

    }

    private void addShirtsandPantsFragment() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            replaceFragmentInContainer(fragmentManager, AddShirtPants.TAG, AddShirtPants.getInstance());
        }catch (IllegalStateException e) {
            Log.e(TAG, e.toString());
        }
    }

    private void styleYourSelfFragment() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            replaceFragmentInContainer(fragmentManager, StyleYourSelf.TAG, StyleYourSelf.getInstance());
        }catch (IllegalStateException e) {
            Log.e(TAG, e.toString());
        }
    }

    private void bookmarkedPairFragment() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            replaceFragmentInContainer(fragmentManager, BookmarkedPairFragment.TAG, BookmarkedPairFragment.getInstance());
        }
        catch (IllegalStateException e) {
            Log.e(TAG, e.toString());
        }
    }


    private void replaceFragmentInContainer(FragmentManager fragmentManager, String tag, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.loadcontainer, fragment, tag)
                .commit();



    }
}
