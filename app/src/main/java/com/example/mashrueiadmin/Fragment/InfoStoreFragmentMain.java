package com.example.mashrueiadmin.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.mashrueiadmin.Model.Modelnotice;
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

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class InfoStoreFragmentMain extends Fragment {




    private String nameStore,descriptionStore,phoneStore,locationStore,workTimeStore,imgUriStore,cityStore,tokenStore;
    private LinearLayout linearLayout4;
    private double latitude;
    private double longitude;
    private TextView nameStoreTextView,description_storeTextView,PhoneNumberStoreTextView,LocationStoreTextView,WorkTimeTextView,cityStoreTextView;
    private ImageView imgStore;
    public DatabaseReference databaseStore,referenceNotifications;
    private Button accept,reject,delete;


    public InfoStoreFragmentMain() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_store_main, container, false);




        nameStoreTextView=view.findViewById(R.id.nameStore);
        linearLayout4=view.findViewById(R.id.linearLayout4);
        accept=view.findViewById(R.id.accept);
        reject=view.findViewById(R.id.reject);
        delete=view.findViewById(R.id.delete);
        description_storeTextView=view.findViewById(R.id.description_store);
        imgStore=view.findViewById(R.id.imgStore);
        PhoneNumberStoreTextView=view.findViewById(R.id.PhoneNumberStore);
        LocationStoreTextView=view.findViewById(R.id.LocationStore);
        WorkTimeTextView=view.findViewById(R.id.WorkTime);
        cityStoreTextView=view.findViewById(R.id.cityStore);



        SharedPreferences preferences = getActivity().getSharedPreferences("saveIdStore", Context.MODE_PRIVATE);
        String StoreType=preferences.getString("typeStore","");




        SharedPreferences  saveIdStore = getActivity().getSharedPreferences("saveIdStore",Context.MODE_PRIVATE);
        Log.d("saveIdStore",saveIdStore.getString("saveIdStore",""));



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
        databaseStore = FirebaseDatabase.getInstance().getReference("Stores").child(StoreType).child(saveIdStore.getString("saveIdStore",""));
        referenceNotifications=FirebaseDatabase.getInstance().getReference("UserNotification").child(saveIdStore.getString("saveIdStore",""));



        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senPushdNotification("تم رفض انشاء المتجر الخاص بك","تنبيه",tokenStore);
                addNotification("تنبيه","تم رفض انشاء المتجر الخاص بك");
                databaseStore.child("isActiveStore").setValue(2).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                     if (task.isSuccessful()){
                         Toast.makeText(getActivity(), "تم رفض المتجر", Toast.LENGTH_SHORT).show();
                     }
                    }
                });
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senPushdNotification("تم انشاء المتجر الخاص بك قم باضافة المنتجات المنزلية اليه","مبروك",tokenStore);
                addNotification("مبروك","تم انشاء المتجر الخاص بك قم باضافة المنتجات المنزلية اليه");
                databaseStore.child("isActiveStore").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "تم نشر المتجر", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle(" حذف المتجر ");
                builder.setMessage(" هل أنت متأكد من حذف المتجر ");
                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        databaseStore.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getActivity(), "تم حذف المتجر", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                });

                builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });


                Dialog dialog=builder.create();
                dialog.show();

            }
        });




        databaseStore.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    nameStore = dataSnapshot.child("nameStore").getValue(String.class);
                    descriptionStore = dataSnapshot.child("descriptionStore").getValue(String.class);
                    phoneStore = dataSnapshot.child("phoneStore").getValue(String.class);
                    cityStore = dataSnapshot.child("cityStore").getValue(String.class);
                    locationStore = dataSnapshot.child("locationStore").getValue(String.class);
                    imgUriStore = dataSnapshot.child("imgUriStore").getValue(String.class);
                    workTimeStore = dataSnapshot.child("workTimeStore").getValue(String.class);
                    tokenStore = dataSnapshot.child("tokenStore").getValue(String.class);
                    latitude = Double.parseDouble(String.valueOf(dataSnapshot.child("latitude").getValue(Double.class)));
                    longitude = Double.parseDouble(String.valueOf(dataSnapshot.child("longitude").getValue(Double.class)));


                    Picasso.get().load(imgUriStore).placeholder(R.drawable.logo).into(imgStore);
                    nameStoreTextView.setText(nameStore);
                    description_storeTextView.setText(descriptionStore);
                    PhoneNumberStoreTextView.setText(phoneStore);
                    WorkTimeTextView.setText(workTimeStore);
                    LocationStoreTextView.setText(locationStore);
                    cityStoreTextView.setText(cityStore);
                }catch (Exception e){
                    e.printStackTrace();
                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        PhoneNumberStoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions((Activity) getActivity(),new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else
                {
                    String dial="tel:"+phoneStore;
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
            }
        });

        LocationStoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String geoUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude;
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    getActivity().startActivity(mapIntent);


                }
            }
        });



        return view;
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
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
                    Request request = new Request.Builder()
                            .header("Authorization", "key=AAAA4P5nnkM:APA91bHCkwZrd9-216twryr7_O5KHhaCklG8hmxHcwyucDOaa653uhj79Sa0HF968os1R1jlZBdFvSU9vGAWLTlGbX9bXoFCnXp6TQH7vFEfgnJBVQjZfF7_whGJRdegKhqEpQ_XMY_F")
                            .url("https://fcm.googleapis.com/fcm/send")
                            .post(requestBody)
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
        referenceNotifications.child(id).setValue(notice);

    }
}