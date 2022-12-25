package com.example.mashrueiadmin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mashrueiadmin.Model.ModelCenter;
import com.example.mashrueiadmin.Model.ModelPost;
import com.example.mashrueiadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.shivtechs.maplocationpicker.LocationPickerActivity;
import com.shivtechs.maplocationpicker.MapUtility;

public class AddCenterActivity extends AppCompatActivity {


    private String addressStr;
    private ImageView imagePost;
    private TextView hint;
    private DatabaseReference databaseReferenceCenters;
    private Button add;
    private EditText name,deck,location,PhoneNumber;
    private boolean flag=true;
    double latitude=0.0;
    double longitude=0.0;
    private StorageTask mUploadTask;
    private StorageReference VideosCenters;
    private LinearLayout choose_location;
    private Uri pickedImgUri=null;
    static int PReqCode = 1, REQUESCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_center);

        hint=findViewById(R.id.hint);
        add=findViewById(R.id.add);
        PhoneNumber=findViewById(R.id.PhoneNumber);
        imagePost=findViewById(R.id.imagePost);
        name=findViewById(R.id.name);
        choose_location=findViewById(R.id.choose_location);
        deck=findViewById(R.id.deck);
        location=findViewById(R.id.location);
        MapUtility.apiKey = getResources().getString(R.string.google_api_key);
        choose_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddCenterActivity.this, LocationPickerActivity.class);
                startActivityForResult(i, 2);
            }
        });

        imagePost=findViewById(R.id.imagePost);
        imagePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndRequestForPermission();
            }
        });



        databaseReferenceCenters= FirebaseDatabase.getInstance().getReference("Centers");
        VideosCenters = FirebaseStorage.getInstance().getReference().child("VideosCenters");




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(deck.getText()) || TextUtils.isEmpty(location.getText()) || TextUtils.isEmpty(PhoneNumber.getText())){
                    Toast.makeText(AddCenterActivity.this, "يجب ملئ كل الحقول", Toast.LENGTH_SHORT).show();
                    return;
                } else if (flag){
                    Toast.makeText(AddCenterActivity.this, "يجب أختيار فيديو للمركز", Toast.LENGTH_SHORT).show();
                    return;
                }

                final StorageReference fileReference = VideosCenters.child(System.currentTimeMillis()
                        + "." + getFileExtension(pickedImgUri));


                mUploadTask = fileReference.putFile(pickedImgUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        String id=databaseReferenceCenters.push().getKey();
                                        ModelCenter center = new ModelCenter(uri.toString(),name.getText().toString(),latitude,longitude,PhoneNumber.getText().toString(),location.getText().toString(),deck.getText().toString());
                                        databaseReferenceCenters.child(id).setValue(center).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(AddCenterActivity.this, "تم نشر المركز", Toast.LENGTH_SHORT).show();
                                                    onBackPressed();
                                                }else {
                                                    Toast.makeText(AddCenterActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }


                                });
                            }
                        });


            }
        });
    }

    //this function returns null when using IO file manager
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        getContentResolver();
        Cursor cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {





                pickedImgUri=data.getData();
                imagePost.setImageURI(pickedImgUri);

                imagePost.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                imagePost.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                hint.setVisibility(View.GONE);

                flag=false;




            }
            else  if (requestCode == 2 ) {
                try {
                    if (data != null && data.getStringExtra(MapUtility.ADDRESS) != null) {
                        String address = data.getStringExtra(MapUtility.ADDRESS);
                        double selectedLatitude = data.getDoubleExtra(MapUtility.LATITUDE, 0.0);
                        double selectedLongitude = data.getDoubleExtra(MapUtility.LONGITUDE, 0.0);

                        latitude=selectedLatitude;
                        longitude=selectedLongitude;

                        addressStr=address;
                        location.setText(address);

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    private String getFileExtension(Uri uri) {
        ContentResolver cR =getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(AddCenterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(AddCenterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
                Toast.makeText(AddCenterActivity.this,"Please accept the required permission", Toast.LENGTH_SHORT).show();

            else
                ActivityCompat.requestPermissions(AddCenterActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
        } else
            openGallery();

    }
    private void openGallery() {
        Intent intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
        intentGallery.setType("image/*");
        intentGallery.setAction(Intent.ACTION_PICK);
        startActivityForResult(intentGallery, REQUESCODE);
    }

}