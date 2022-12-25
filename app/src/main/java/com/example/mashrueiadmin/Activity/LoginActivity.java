package com.example.mashrueiadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mashrueiadmin.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {


    String password="123456";
    String userName="123456";
    EditText userNameEditText,passEditText;
    private TextInputLayout textInputLayoutPass,textInputLayoutPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textInputLayoutPass=findViewById(R.id.textInputLayoutPass);
        textInputLayoutPhoneNumber=findViewById(R.id.textInputLayoutPhoneNumber);
        userNameEditText=findViewById(R.id.userName);
        passEditText=findViewById(R.id.pass);

        SharedPreferences preferences=getSharedPreferences("SuccessLogIn", Context.MODE_PRIVATE);

        boolean LogIn = preferences.getBoolean("login",false);

        if (LogIn){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();

        }else {

        }



    }

    public void login(View view) {
        if (TextUtils.isEmpty(userNameEditText.getText().toString())) {
            textInputLayoutPhoneNumber.setBoxStrokeColor(Color.RED);
            userNameEditText.requestFocus();
            return;
        }  else if (TextUtils.isEmpty(passEditText.getText())) {
            textInputLayoutPass.setBoxStrokeColor(Color.RED);
            passEditText.requestFocus();
            return;
        } else if (!userNameEditText.getText().toString().equals(userName) || !passEditText.getText().toString().equals(password)){
            Toast.makeText(this, "البيانات المدخلة خاطئة", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences=getSharedPreferences("SuccessLogIn",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("login",true);
        editor.apply();

        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();




    }
}