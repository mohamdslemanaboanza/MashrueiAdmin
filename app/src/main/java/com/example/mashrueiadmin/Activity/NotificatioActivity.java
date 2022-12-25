package com.example.mashrueiadmin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mashrueiadmin.Model.Modelnotice;
import com.example.mashrueiadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NotificatioActivity extends AppCompatActivity {


    private EditText name,deck;
    private DatabaseReference referenceNotifications;
    private ProgressBar ProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificatio);

        referenceNotifications= FirebaseDatabase.getInstance().getReference("AppNotifications");
        name=findViewById(R.id.name);
        deck=findViewById(R.id.deck);
        ProgressBar=findViewById(R.id.ProgressBar);
    }

    public void Post(View view) {

        if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(deck.getText())){
            Toast.makeText(this, "كل الحقول مطلوبة", Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressBar.setVisibility(View.VISIBLE);
        String id = referenceNotifications.push().getKey();
        Modelnotice notice = new Modelnotice(id,name.getText().toString(),deck.getText().toString());

        referenceNotifications.child(id).setValue(notice).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    sendNotification(name.getText().toString(),deck.getText().toString());
                    ProgressBar.setVisibility(View.GONE);
                    Toast.makeText(NotificatioActivity.this, "تم ارسال الاشعار", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    deck.setText("");
                }else {
                    ProgressBar.setVisibility(View.GONE);
                    Toast.makeText(NotificatioActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void back(View view) {
        onBackPressed();
    }

    private void sendNotification(String title,String body){
        RequestQueue mRequestQue = Volley.newRequestQueue(this);

        JSONObject json = new JSONObject();
        try {
            json.put("to", "/topics/" + "AppNotifications");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title", title);
            notificationObj.put("body", body);
            //replace notification with data when went send data
            json.put("notification", notificationObj);

            String URL = "https://fcm.googleapis.com/fcm/send";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("MUR", "onResponse: "+response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("MUR", "onError: " + error.networkResponse);
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=AAAA4P5nnkM:APA91bHCkwZrd9-216twryr7_O5KHhaCklG8hmxHcwyucDOaa653uhj79Sa0HF968os1R1jlZBdFvSU9vGAWLTlGbX9bXoFCnXp6TQH7vFEfgnJBVQjZfF7_whGJRdegKhqEpQ_XMY_F");
                    return header;
                }
            };


            mRequestQue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}