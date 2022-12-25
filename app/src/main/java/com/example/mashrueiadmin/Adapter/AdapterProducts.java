package com.example.mashrueiadmin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mashrueiadmin.Activity.ProductActivity;
import com.example.mashrueiadmin.Model.ProductModel;
import com.example.mashrueiadmin.Model.StoreModel;
import com.example.mashrueiadmin.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterProducts extends RecyclerView.Adapter <AdapterProducts.ImageViewHolder>implements Filterable
{
    private Context context;
    private List<StoreModel> mUploads;
    ArrayList<StoreModel> getDataAdapterFull;

    public AdapterProducts(Context storesActivity, List<StoreModel> mUploads) {
        this.context = storesActivity;
        this.mUploads = mUploads;
        getDataAdapterFull= new ArrayList<>(mUploads);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.design_item, viewGroup, false);
        return new ImageViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int i) {






        final StoreModel store = mUploads.get(i);


        Picasso.get().load(store.getImgUriStore()).placeholder(R.drawable.logo).into(holder.img_store);
        holder.location.setText(store.getLocationStore());
        holder.name.setText(store.getNameStore());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences  saveIdStore = context.getSharedPreferences("saveIdStore",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = saveIdStore.edit();
                editor.putString("saveIdStore",store.getKeyUser());
                editor.putString("typeStore",store.getTypeStore());
                editor.putString("tokenStore",store.getTokenStore());
                editor.apply();
                context.startActivity(new Intent(context, ProductActivity.class));




            }
        });




    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

     public ImageView img_store;
     public TextView location,name;
     public RecyclerView myRecyclerView;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            img_store=itemView.findViewById(R.id.img_user);
            location=itemView.findViewById(R.id.location);
            name=itemView.findViewById(R.id.name);
            myRecyclerView=itemView.findViewById(R.id.myRecyclerView);



        }
    }

    @Override
    public Filter getFilter() {
        return Exsablelist;
    }

    private Filter Exsablelist = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<StoreModel> filthredlsit = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filthredlsit.addAll(getDataAdapterFull);
            } else {
                String fp = constraint.toString().toLowerCase().trim();
                for (StoreModel item : getDataAdapterFull) {
                    if (item.getNameStore().toLowerCase().contains(fp)) {
                        filthredlsit.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filthredlsit;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            mUploads.clear();
            mUploads.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public int numberOfItems(){
        return mUploads.size();
    }


}
