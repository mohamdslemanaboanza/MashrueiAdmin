package com.example.mashrueiadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mashrueiadmin.Activity.ProductActivity;
import com.example.mashrueiadmin.Model.StoreModel;
import com.example.mashrueiadmin.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterMessage extends RecyclerView.Adapter <AdapterMessage.ImageViewHolder>
{
    private Context context;
    private ArrayList<String> mUploads;


    public AdapterMessage(Context storesActivity, ArrayList<String> mUploads) {
        this.context = storesActivity;
        this.mUploads = mUploads;

    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_message, viewGroup, false);
        return new ImageViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int i) {


        String messges=mUploads.get(i);

        Log.d("messges",messges);

        holder.name.setText(messges);








    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {


     public TextView name;
     ImageView delete,call;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            delete=itemView.findViewById(R.id.delete);
            call=itemView.findViewById(R.id.call);





        }
    }






}
