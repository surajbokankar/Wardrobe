package com.ecomm.suraj.clothpicker.addcloth.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecomm.suraj.clothpicker.Model.UserSelectedCloths;
import com.ecomm.suraj.clothpicker.Presenter.MyStylePresenter;
import com.ecomm.suraj.clothpicker.Presenter.StyleSelfInterface;
import com.ecomm.suraj.clothpicker.R;
import com.ecomm.suraj.clothpicker.addcloth.adapter.PerfectMatchedAdapter;
import com.ecomm.suraj.clothpicker.addcloth.listener.OnItemSelectedListner;
import com.ecomm.suraj.clothpicker.addcloth.model.ClothModel;
import com.ecomm.suraj.clothpicker.utils.CommonUtility;

import java.util.ArrayList;

/**
 * Created by surajbokankar on 04/01/17.
 */

public class BookmarkedPairFragment extends Fragment implements StyleSelfInterface,OnItemSelectedListner {

    public static final String TAG = "BookmarkedPairFragment";
    MyStylePresenter presenter;
    RecyclerView recyclerView=null;
    PerfectMatchedAdapter adapter=null;
    ArrayList<byte[]> bitmapList=null;
    public static Fragment getInstance() {
        BookmarkedPairFragment bookmark=new BookmarkedPairFragment();
        return bookmark;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.bookmark_pair_layout,container,false);
        initViews(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initViews(View view) {
        bitmapList=new ArrayList<>();
        recyclerView= (RecyclerView) view.findViewById(R.id.bookmark_pair_recycler_view);
        adapter=new PerfectMatchedAdapter(bitmapList);
        recyclerView.setLayoutManager(CommonUtility.getInstance().getVerticalLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        presenter=new MyStylePresenter(getContext(),this);
        presenter.getBookMarkedList();

    }

    @Override
    public void onItemSelectedListener(ArrayList<ClothModel> imageArray, int position) {

    }

    @Override
    public void onShirtItemSelectedListener(ArrayList<ClothModel> imageArray, int position) {

    }

    @Override
    public void onStylePairedSuccess(Bitmap shirt, Bitmap pant) {

    }

    @Override
    public void onStylePairedFailure(String error) {

    }

    @Override
    public void onDislikePairSuccess(Bitmap shirt, Bitmap pant) {

    }

    @Override
    public void onBookMarkedClick(String message) {

    }

    @Override
    public void getAllClothsList(UserSelectedCloths shirts) {

    }

    @Override
    public void onBookMarkListSuccess(UserSelectedCloths cloths) {
        bitmapList.addAll(cloths.bookMarkedPair);
        adapter.notifyDataSetChanged();
    }
}
