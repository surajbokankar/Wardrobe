package com.ecomm.suraj.clothpicker.addcloth.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecomm.suraj.clothpicker.R;

/**
 * Created by surajbokankar on 04/01/17.
 */

public class BookmarkedPairFragment extends Fragment {

    public static final String TAG = "BookmarkedPairFragment";
    public static Fragment getInstance() {
        BookmarkedPairFragment bookmark=new BookmarkedPairFragment();
        return bookmark;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.bookmark_pair_layout,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: Called=true");
    }
}
