package com.ecomm.suraj.clothpicker.addcloth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecomm.suraj.clothpicker.R;

/**
 * Created by surajbokankar on 04/01/17.
 */

public class BookmarkedPairFragment extends Fragment {

    public static final String TAG = "BookmarkedPairFragment";
    RecyclerView bookMarkedView=null;
    public static Fragment getInstance() {
        BookmarkedPairFragment bookmark=new BookmarkedPairFragment();
        return bookmark;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        if(container!=null){
            container.removeAllViews();
           view=inflater.inflate(R.layout.bookmark_pair_layout,container);
        }
        initViews(view);
        return  view;
    }

    private void initViews(View view) {
      bookMarkedView= (RecyclerView) view.findViewById(R.id.bookmark_pair_recycler_view);
    }




}
