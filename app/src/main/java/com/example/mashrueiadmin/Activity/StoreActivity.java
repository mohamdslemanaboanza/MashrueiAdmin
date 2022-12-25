package com.example.mashrueiadmin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mashrueiadmin.Adapter.AdapterProducts;
import com.example.mashrueiadmin.Adapter.Adapter_choose_type_store;
import com.example.mashrueiadmin.Model.ProductModel;
import com.example.mashrueiadmin.Model.StoreModel;
import com.example.mashrueiadmin.Model.custm_item_text;
import com.example.mashrueiadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private Spinner typeStore;
    private ArrayList<custm_item_text> types;
    private String typeStoreStr;
    private ImageView serach;
    private EditText ser;
    private DatabaseReference databaseStores;
    private List<StoreModel> mUploads;
    private List<ProductModel> productModels;
    private ImageView imageView4;
    private TextView textView9;
    private RecyclerView myRecyclerView;
    private AdapterProducts adapterProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        typeStore=findViewById(R.id.typeStore);
        serach=findViewById(R.id.serach);
        imageView4=findViewById(R.id.imageView4);
        textView9=findViewById(R.id.textView9);
        ser=findViewById(R.id.ser);
        myRecyclerView=findViewById(R.id.myRecyclerView);
        databaseStores = FirebaseDatabase.getInstance().getReference("Stores");

        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                adapterProducts.notifyDataSetChanged();
//                if (adapterProducts.getItemCount() == 0){
//                    imageView4.setVisibility(View.VISIBLE);
//                    textView9.setVisibility(View.VISIBLE);
//                }else {
//                    imageView4.setVisibility(View.GONE);
//                    textView9.setVisibility(View.GONE);
//                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (adapterProducts != null) {
                    if (charSequence.toString().equals("")) {
                        imageView4.setVisibility(View.GONE);
                        textView9.setVisibility(View.GONE);
                        adapterProducts.getFilter().filter(charSequence.toString());
                        adapterProducts.notifyDataSetChanged();
                    } else {
                        adapterProducts.getFilter().filter(charSequence.toString());
                        adapterProducts.notifyDataSetChanged();
                        Log.d("NumberOfItems", adapterProducts.getItemCount() + "");
                        if (adapterProducts.getItemCount() == 0) {
                            imageView4.setVisibility(View.VISIBLE);
                            textView9.setVisibility(View.VISIBLE);
                        } else {
                            imageView4.setVisibility(View.GONE);
                            textView9.setVisibility(View.GONE);
                        }
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
//                adapterProducts.notifyDataSetChanged();
//                if (adapterProducts.getItemCount() == 0){
//                    imageView4.setVisibility(View.VISIBLE);
//                    textView9.setVisibility(View.VISIBLE);
//                }else {
//                    imageView4.setVisibility(View.GONE);
//                    textView9.setVisibility(View.GONE);
//                }

            }
        });
        serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ser.setVisibility(View.VISIBLE);
                ser.setFocusable(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        });


        types = getTypes();
        Adapter_choose_type_store ustamAdabterCustam_adpter_text=new Adapter_choose_type_store(this, types);
        if(typeStore != null)
        {
            typeStore.setAdapter(ustamAdabterCustam_adpter_text);
        }

        typeStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                custm_item_text itemText = (custm_item_text) adapterView.getSelectedItem();
                typeStoreStr = itemText.getSpinnertext();
                mUploads=new ArrayList<>();
                productModels=new ArrayList<>();

                if (typeStoreStr.equals("طعام")){
                    typeStoreStr = "Food";
                }else if (typeStoreStr.equals("حلويات")){
                    typeStoreStr = "Candy";
                }else if (typeStoreStr.equals("ملابس")){
                    typeStoreStr = "Clothes";
                }else if (typeStoreStr.equals("البان و اجبان")){
                    typeStoreStr = "Dairies";
                }else if (typeStoreStr.equals("حرف يدوية")){
                    typeStoreStr = "Handicraft";
                }else if (typeStoreStr.equals("اثاثا منزلي")){
                    typeStoreStr = "HomeFurnishings";
                }else if (typeStoreStr.equals("اخرى")){
                    typeStoreStr = "Other";
                }


                databaseStores.child(typeStoreStr).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            final StoreModel store = dataSnapshot1.getValue(StoreModel.class);
                                mUploads.add(0,store);



                            adapterProducts = new AdapterProducts(StoreActivity.this, mUploads);
                            myRecyclerView.setAdapter(adapterProducts);


                            if (adapterProducts != null) {
                                if (adapterProducts.getItemCount() == 0) {
                                    imageView4.setVisibility(View.VISIBLE);
                                    textView9.setVisibility(View.VISIBLE);
                                } else {
                                    imageView4.setVisibility(View.GONE);
                                    textView9.setVisibility(View.GONE);
                                }
                            }else {
                                imageView4.setVisibility(View.VISIBLE);
                                textView9.setVisibility(View.VISIBLE);
                            }

                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(StoreActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private ArrayList<custm_item_text> getTypes()
    {
        types =new ArrayList<>();

        types.add(new custm_item_text("طعام",R.drawable.icone_food));
        types.add(new custm_item_text("حلويات",R.drawable.cake));
        types.add(new custm_item_text("البان و اجبان",R.drawable.dairy));
        types.add(new custm_item_text("ملابس",R.drawable.donateclothes));
        types.add(new custm_item_text("حرف يدوية",R.drawable.handicrafts));
        types.add(new custm_item_text("اثاثا منزلي",R.drawable.donatefurniture));
        types.add(new custm_item_text("أخرى",R.drawable.c));

        return types;


    }
}