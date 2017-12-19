package com.ecomm.suraj.clothpicker.addcloth.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ecomm.suraj.clothpicker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by suraj.bokankar on 18/12/17.
 */

public class PerfectMatchedAdapter extends RecyclerView.Adapter<PerfectMatchedAdapter.CustomHolder> {


    ArrayList<byte[]>  bitmapList=null;
    Context context=null;
    public PerfectMatchedAdapter(ArrayList<byte[]> list){
        this.bitmapList=list;
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.layout_content,parent,false);
        CustomHolder holder=new CustomHolder(view);
        context=parent.getContext();
        return  holder;
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        if(bitmapList!=null&&bitmapList.size()>0){
            Bitmap bitmap= BitmapFactory.decodeByteArray(bitmapList.get(position),0,bitmapList.get(position).length);
            //Picasso.with(context).load(bitmap.toString()).into(holder.imageView);
            holder.imageView.setImageBitmap(bitmap);
        }

    }

    @Override
    public int getItemCount() {
        return bitmapList.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imageView;
        public CustomHolder(View itemView) {
            super(itemView);
            imageView= (AppCompatImageView) itemView.findViewById(R.id.pair_cloth_image_shirts);
        }
    }
}
