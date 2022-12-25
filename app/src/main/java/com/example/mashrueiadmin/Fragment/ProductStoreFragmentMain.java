package com.example.mashrueiadmin.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mashrueiadmin.Adapter.AdapterStoreProduct;
import com.example.mashrueiadmin.Model.ProductModel;
import com.example.mashrueiadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ProductStoreFragmentMain extends Fragment {




    RecyclerView myRecyclerView;
    private DatabaseReference StoresProduct,databaseStores;
    private ArrayList<ProductModel> mUploads;
    public ProductStoreFragmentMain() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_store_main, container, false);

        mUploads=new ArrayList<>();

        myRecyclerView=view.findViewById(R.id.myRecyclerView);
        myRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        SharedPreferences preferences = getActivity().getSharedPreferences("saveIdStore", Context.MODE_PRIVATE);
        String StoreType=preferences.getString("typeStore","");



        SharedPreferences  saveIdStore = getActivity().getSharedPreferences("saveIdStore",Context.MODE_PRIVATE);
        final String idStore = saveIdStore.getString("saveIdStore","");



        if (StoreType.equals("طعام")){
            StoreType = "Food";
        }else if (StoreType.equals("حلويات")){
            StoreType = "Candy";
        }else if (StoreType.equals("ملابس")){
            StoreType = "Clothes";
        }else if (StoreType.equals("البان و اجبان")){
            StoreType = "Dairies";
        }else if (StoreType.equals("حرف يدوية")){
            StoreType = "Handicraft";
        }else if (StoreType.equals("اثاثا منزلي")){
            StoreType = "HomeFurnishings";
        }else if (StoreType.equals("اخرى")){
            StoreType = "Other";
        }



        StoresProduct = FirebaseDatabase.getInstance().getReference("Stores").child(StoreType);

        StoresProduct.keepSynced(true);
        StoresProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                StoresProduct.child(idStore).child("Products").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            ProductModel dataSetFire = dataSnapshot1.getValue(ProductModel.class);

                            mUploads.add(0,dataSetFire);
                            AdapterStoreProduct adapterProducts = new AdapterStoreProduct(getActivity(), mUploads);



                            myRecyclerView.setAdapter(adapterProducts);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




        return view;
    }


}