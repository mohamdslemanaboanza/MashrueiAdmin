package com.example.mashrueiadmin.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.mashrueiadmin.Adapter.ViewPagerAdapterImages;
import com.example.mashrueiadmin.Model.Modelnotice;
import com.example.mashrueiadmin.Model.ProductModel;
import com.example.mashrueiadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DetailsActivity extends AppCompatActivity {






    private TextView name,nameProduct,deskProduct,priceProduct;
    private Button PublishTheProduct,deleteTheProduct,hideTheProduct;
    private DatabaseReference databaseStores,databaseMyOrder,referenceNotifications;
    private ProductModel productModel;
    private String tokenStore;
    private ViewPager viewPager;
    private int dotscount;
    private ImageView[] dots;
    private LinearLayout sliderDotspanel;
    private ViewPagerAdapterImages adpterRec;
    private ArrayList<String> images;
    private String idStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        images=new ArrayList<String>();
        viewPager=findViewById(R.id.viewPager);




        name=findViewById(R.id.name);
        deleteTheProduct=findViewById(R.id.deleteTheProduct);
        nameProduct=findViewById(R.id.nameProduct);
        hideTheProduct=findViewById(R.id.hideTheProduct);
        sliderDotspanel=findViewById(R.id.SliderDots);
        PublishTheProduct=findViewById(R.id.PublishTheProduct);
        deskProduct=findViewById(R.id.deskProduct);
        priceProduct=findViewById(R.id.priceProduct);



        SharedPreferences preferencesInfo = getSharedPreferences("InfoUser", Context.MODE_PRIVATE);
        final String userId = preferencesInfo.getString("UserId", "");

        databaseMyOrder = FirebaseDatabase.getInstance().getReference("UserOrder").child(userId);
        referenceNotifications=FirebaseDatabase.getInstance().getReference("UserNotification").child(userId);

        SharedPreferences preferences = getSharedPreferences("saveIdStore", Context.MODE_PRIVATE);
        String StoreType=preferences.getString("typeStore","");
        tokenStore=preferences.getString("tokenStore","");

        Log.d("token",tokenStore);


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

        Log.d("StoreType",StoreType);



        final String idProduct = getIntent().getExtras().getString("idProduct");
        databaseStores = FirebaseDatabase.getInstance().getReference("Stores").child(StoreType);

        databaseStores.keepSynced(true);
        databaseStores.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for (final DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                    databaseStores.child(dataSnapshot1.getKey()).child("Products").child(idProduct).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            productModel = dataSnapshot.getValue(ProductModel.class);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    databaseStores.child(dataSnapshot1.getKey()).child("Products").child(idProduct).child("ProductImages").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                images.add(dataSnapshot1.getValue(String.class));

                                Log.d("UrlImages",images.size()+"");
                                Log.d("Images",dataSnapshot1.getValue(String.class));



                            }

                            adpterRec=new ViewPagerAdapterImages(DetailsActivity.this,images);
                            viewPager.setAdapter(adpterRec);



                            dotscount = images.size();
                            dots = new ImageView[dotscount];


//                            dots[0].setImageDrawable(ContextCompat.getDrawable(DetailsActivity.this, R.drawable.active_dot));

                            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {

                                    for(int i = 0; i< dotscount; i++){
                                        dots[i].setImageDrawable(ContextCompat.getDrawable(DetailsActivity.this, R.drawable.nonactive_dot));
                                    }

                                    dots[position].setImageDrawable(ContextCompat.getDrawable(DetailsActivity.this, R.drawable.active_dot));

                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });



                            for(int i = 0; i < dotscount; i++){

                                dots[i] = new ImageView(DetailsActivity.this);
                                dots[i].setImageDrawable(ContextCompat.getDrawable(DetailsActivity.this, R.drawable.nonactive_dot));

                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                params.setMargins(8, 0, 8, 0);

                                sliderDotspanel.addView(dots[i], params);

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });







                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });








        final String nameProductStr = getIntent().getExtras().getString("nameProduct");
        nameProduct.setText(nameProductStr);


        String detailsProductStr = getIntent().getExtras().getString("detailsProduct");
        deskProduct.setText(detailsProductStr);

        String priceProductStr = getIntent().getExtras().getString("priceProduct");
        priceProduct.setText(priceProductStr+" د.أ ");

        String img = getIntent().getExtras().getString("img");
        idStore = getIntent().getExtras().getString("idStore");

        PublishTheProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseStores.child(idStore).child("Products").child(idProduct).child("isActive").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            senPushdNotification("مبروك تم الموافقة على المنتج : "+nameProductStr,"مبروك",tokenStore);
                            addNotification("مبروك","مبروك تم الموافقة على المنتج : "+nameProductStr);
                            Toast.makeText(DetailsActivity.this, "تم الموافقة على المنتج", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(DetailsActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        deleteTheProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senPushdNotification(" تم حذف  المنتج : "+nameProductStr,"تنبيه",tokenStore);
                addNotification("تنبيه"," تم حذف  المنتج : "+nameProductStr);
                databaseStores.child(idStore).child("Products").child(idProduct).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DetailsActivity.this, "تم حذف المنتج", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(DetailsActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

       hideTheProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseStores.child(idStore).child("Products").child(idProduct).child("isActive").setValue(2).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DetailsActivity.this, "تم اخفاء المنتج", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(DetailsActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });










        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();



    }


    public static void senPushdNotification(final String body, final String title, final String fcmToken) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    OkHttpClient client = new OkHttpClient();
                    JSONObject json = new JSONObject();
                    JSONObject notificationJson = new JSONObject();
                    JSONObject dataJson = new JSONObject();
                    notificationJson.put("text", body);
                    notificationJson.put("title", title);
                    notificationJson.put("priority", "high");
                    dataJson.put("customId", "02");
                    dataJson.put("badge", 1);
                    dataJson.put("alert", "Alert");
                    json.put("notification", notificationJson);
                    json.put("data", dataJson);
                    json.put("to", fcmToken);
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
                    Request request = new Request.Builder()
                            .header("Authorization", "key=AAAA4P5nnkM:APA91bHCkwZrd9-216twryr7_O5KHhaCklG8hmxHcwyucDOaa653uhj79Sa0HF968os1R1jlZBdFvSU9vGAWLTlGbX9bXoFCnXp6TQH7vFEfgnJBVQjZfF7_whGJRdegKhqEpQ_XMY_F")
                            .url("https://fcm.googleapis.com/fcm/send")
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    String finalResponse = response.body().string();
                    Log.i("TAG", finalResponse);
                } catch (Exception e) {

                    Log.i("TAG", e.getMessage());
                }
                return null;
            }
        }.execute();
    }
    public  void addNotification(String title, String body){

        String id = referenceNotifications.push().getKey();
        Modelnotice notice = new Modelnotice(id,title,body);
        referenceNotifications.child(idStore).child(id).setValue(notice);

    }
}