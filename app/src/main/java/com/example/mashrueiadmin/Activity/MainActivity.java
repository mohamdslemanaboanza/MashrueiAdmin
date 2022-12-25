package com.example.mashrueiadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mashrueiadmin.Adapter.AdapterSectionTypes;
import com.example.mashrueiadmin.Model.DesignItemHome;
import com.example.mashrueiadmin.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.mRecyclerView);


        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        AdapterSectionTypes itemHome = new AdapterSectionTypes(MainActivity.this, preparyArray());
        mRecyclerView.setAdapter(itemHome);

    }


    private ArrayList<DesignItemHome> preparyArray() {

        ArrayList<DesignItemHome> list = new ArrayList<>();

        DesignItemHome designItem = new DesignItemHome();


        designItem = new DesignItemHome();
        designItem.setImage(R.drawable.shop);
        designItem.setTitle("المتاجر ");
        list.add(designItem);


        designItem = new DesignItemHome();
        designItem.setImage(R.drawable.center);
        designItem.setTitle("اضافة مركز ");
        list.add(designItem);

        designItem = new DesignItemHome();
        designItem.setImage(R.drawable.post);
        designItem.setTitle("نشر منشور");
        list.add(designItem);

        designItem = new DesignItemHome();
        designItem.setImage(R.drawable.bell);
        designItem.setTitle("ارسال اشعارات");
        list.add(designItem);


        designItem = new DesignItemHome();
        designItem.setImage(R.drawable.ic_baseline_email_24);
        designItem.setTitle("رسائل");
        list.add(designItem);

        designItem = new DesignItemHome();
        designItem.setImage(R.drawable.ic_baseline_power_settings_new_24);
        designItem.setTitle("تسجيل خروج");
        list.add(designItem);

//        designItem = new DesignItemHome();
//        designItem.setImage(R.drawable.donatefurniture);
//        designItem.setTitle("أثاث منزلي");
//        list.add(designItem);

//
//        designItem = new DesignItemHome();
//        designItem.setImage(R.drawable.c);
//        designItem.setTitle("أخرى");
//        list.add(designItem);

        return list;

    }
}