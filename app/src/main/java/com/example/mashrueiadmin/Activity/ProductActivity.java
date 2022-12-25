package com.example.mashrueiadmin.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mashrueiadmin.Adapter.MyAdapterStore;
import com.example.mashrueiadmin.Adapter.custam_adpter_text_location_ser;
import com.example.mashrueiadmin.Model.custm_item_text;
import com.example.mashrueiadmin.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    TextView header;

    Spinner GovernessesSpinner;
    ArrayList<custm_item_text> Governesses;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);



        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        header =  findViewById(R.id.header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        GovernessesSpinner=findViewById(R.id.GovernessesSpinner);
        Governesses = GetGovernorate();
        custam_adpter_text_location_ser ustamAdabterCustam_adpter_text=new custam_adpter_text_location_ser(this, Governesses);
        if(GovernessesSpinner != null)
        {
            GovernessesSpinner.setAdapter(ustamAdabterCustam_adpter_text);
        }


        tabLayout.addTab(tabLayout.newTab().setText("منتجات المتجر"));
        tabLayout.addTab(tabLayout.newTab().setText("معلومات المتجر"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapterStore adapter = new MyAdapterStore(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



    }




    //بضيف المدن
    private ArrayList<custm_item_text> GetGovernorate()
    {
        Governesses =new ArrayList<>();
        Governesses.add(new custm_item_text("عمان"));
        Governesses.add(new custm_item_text("الزرقاء"));
        Governesses.add(new custm_item_text("الطفيلة"));
        Governesses.add(new custm_item_text("الكرك"));
        Governesses.add(new custm_item_text("اربد"));
        Governesses.add(new custm_item_text("معان"));
        Governesses.add(new custm_item_text("العقبة"));
        Governesses.add(new custm_item_text("السلط"));
        Governesses.add(new custm_item_text("مادبا"));
        Governesses.add(new custm_item_text("جرش"));
        Governesses.add(new custm_item_text("عجلون"));
        Governesses.add(new custm_item_text("المفرق"));

        return Governesses;


    }
}
