package com.example.mashrueiadmin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mashrueiadmin.Adapter.AdapterMessage;
import com.example.mashrueiadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessagesActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private DatabaseReference massege;
    private ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        mRecyclerView=findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        massege= FirebaseDatabase.getInstance().getReference("Messages");
        arrayList=new ArrayList<>();
        massege.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String s = snapshot.getValue(String.class);
                    Log.d("messages",s);
                    arrayList.add(0,s);
                }


                AdapterMessage message =new AdapterMessage(MessagesActivity.this,arrayList);
                mRecyclerView.setAdapter(message);
                Log.d("numberOfItem",message.getItemCount()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void back(View view) {
        onBackPressed();
    }
}