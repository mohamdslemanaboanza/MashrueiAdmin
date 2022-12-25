package com.example.mashrueiadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mashrueiadmin.Activity.DetailsActivity;
import com.example.mashrueiadmin.Model.ProductModel;
import com.example.mashrueiadmin.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterStoreProduct extends RecyclerView.Adapter<AdapterStoreProduct.viewItem> {

    private ArrayList<ProductModel> items;
    private Context context;



    public AdapterStoreProduct(Context context, ArrayList<ProductModel> items) {

        this.items = items;
        this.context = context;

        Log.d("context",context.toString());
    }

    class viewItem extends RecyclerView.ViewHolder{

        public ImageView img_product;
        public TextView nameProduct,priceProduct;
        public com.omega_r.libs.OmegaCenterIconButton   add_to_card;


        public viewItem(@NonNull View itemView) {
            super(itemView);

            img_product=itemView.findViewById(R.id.img_product);
            nameProduct=itemView.findViewById(R.id.nameProduct);
            priceProduct=itemView.findViewById(R.id.priceProduct);
            add_to_card=itemView.findViewById(R.id.add_to_card);

        }
    }

    @NonNull
    @Override
    public viewItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.design_store, viewGroup, false);
        return new viewItem(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull viewItem holder, final int position) {

        final ProductModel product = items.get(position);

        Picasso.get().load(product.getImageScreen()).placeholder(R.drawable.logo).into(holder.img_product);
        holder.nameProduct.setText(product.getNameProduct());
        holder.priceProduct.setText(product.getPriceProduct()+" د.أ ");





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("nameProduct",product.getNameProduct());
                intent.putExtra("detailsProduct",product.getDeskProduct());
                intent.putExtra("priceProduct",product.getPriceProduct());
                intent.putExtra("idProduct",product.getIdProduct());
                intent.putExtra("idStore",product.getIdStore());
                intent.putExtra("img",product.getImageScreen());



                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
