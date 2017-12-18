package com.ecomm.suraj.clothpicker.addcloth.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ecomm.suraj.clothpicker.R;
import com.ecomm.suraj.clothpicker.addcloth.listener.OnItemSelectedListner;
import com.ecomm.suraj.clothpicker.addcloth.model.ClothModel;
import com.ecomm.suraj.clothpicker.utils.CustomDrawable;

import java.util.ArrayList;

/**
 * Created by suraj.bokankar on 17/12/17.
 */

public class AllPantsAdapter extends RecyclerView.Adapter<AllPantsAdapter.CutomHolder> {

    public ArrayList<ClothModel> pantList=null;
    Context context=null;
    OnItemSelectedListner callBack=null;
    private static final String TAG = "AllPantsAdapter";
    public AllPantsAdapter(ArrayList<ClothModel> imageList,OnItemSelectedListner listner){
        this.pantList=imageList;
        callBack=listner;
    }

    @Override
    public CutomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.layout_content,parent,false);
        CutomHolder cutomHolder=new CutomHolder(view);
        context=parent.getContext();
        return cutomHolder;
    }

    @Override
    public void onBindViewHolder(CutomHolder holder, final int position) {
       if(pantList!=null&&pantList.size()>0){
           final ClothModel clothModel=pantList.get(position);
           Bitmap bitmap= BitmapFactory.decodeByteArray(clothModel.imageArray,0,clothModel.imageArray.length);
           holder.imageView.setImageBitmap(bitmap);
           Log.i(TAG, "onBindViewHolder: Values="+clothModel.imageArray);
           GradientDrawable drawable= CustomDrawable.getInstance().setDrawableShape(GradientDrawable.RECTANGLE).setDrawableStrokeColor(context.getResources().getColor(R.color.white)).getDrawable();

           if(clothModel.isSelected){
               holder.layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.boarder_rounded_button));
           }else{
               holder.layout.setBackgroundDrawable(drawable);
           }

           holder.imageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(clothModel.isSelected){
                       clothModel.isSelected=false;
                   }else{
                       clothModel.isSelected=true;
                   }
                   clearAllAndChangeCurrent(clothModel,pantList,position);
                   callBack.onItemSelectedListener(pantList,position);
               }

           });
       }
    }

    private void clearAllAndChangeCurrent(ClothModel clothModel, ArrayList<ClothModel> pantList,int position) {

        for(int i=0;i<pantList.size();i++){
            if(i!=position){
                pantList.get(i).isSelected=false;
            }
        }
    }

    @Override
    public int getItemCount() {
        return pantList.size();
    }

    public class CutomHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imageView;
        LinearLayout layout;
        public CutomHolder(View itemView) {
            super(itemView);
            imageView= (AppCompatImageView) itemView.findViewById(R.id.pair_cloth_image_shirts);
            layout= (LinearLayout) itemView.findViewById(R.id.layout_background);
        }
    }
}
