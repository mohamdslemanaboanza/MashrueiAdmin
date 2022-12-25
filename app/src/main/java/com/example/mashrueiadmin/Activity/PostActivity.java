package com.example.mashrueiadmin.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mashrueiadmin.Model.ModelPost;
import com.example.mashrueiadmin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    private TextView header;
    int PLACE_PICKER_REQUEST=2;
    private ProgressBar ProgressBar;
    private Uri pickedImgUri=null;
    static int PReqCode = 1, REQUESCODE = 1;
    private LinearLayout linearLayout;
    private ImageView imagePost;
    private TextView hint;
    private DatabaseReference databaseReferencePosts;
    private EditText namePost,deckPost;
    private StorageTask mUploadTask;
    private StorageReference ImagesPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        header=findViewById(R.id.header);
        imagePost=findViewById(R.id.imagePost);
        linearLayout=findViewById(R.id.linearLayout);
        hint=findViewById(R.id.hint);
        namePost=findViewById(R.id.namePost);
        deckPost=findViewById(R.id.deckPost);
        databaseReferencePosts= FirebaseDatabase.getInstance().getReference("Posts");
        ImagesPosts = FirebaseStorage.getInstance().getReference().child("ImagesPosts");
        ProgressBar=findViewById(R.id.ProgressBar);
        ProgressBar.setVisibility(View.GONE);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();


            }
        });

        imagePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndRequestForPermission();

            }
        });
    }

    public void Post(View view) {

        if (TextUtils.isEmpty(namePost.getText()) || TextUtils.isEmpty(deckPost.getText())){
            Toast.makeText(this, "يجب ملئ كل الحقول", Toast.LENGTH_SHORT).show();
            return;
        }else {

            ProgressBar.setVisibility(View.VISIBLE);
        final String id=databaseReferencePosts.push().getKey();


            final StorageReference fileReference = ImagesPosts.child(System.currentTimeMillis()
                    + "." + getFileExtension(pickedImgUri));


            mUploadTask = fileReference.putFile(pickedImgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    ModelPost post = new ModelPost(namePost.getText().toString(),deckPost.getText().toString(),uri.toString(),id);
                                    databaseReferencePosts.child(id).setValue(post);
                                    Toast.makeText(PostActivity.this, "تم نشر المنشور", Toast.LENGTH_SHORT).show();
                                    ProgressBar.setVisibility(View.GONE);

                                    namePost.setText("");
                                    deckPost.setText("");
                                    pickedImgUri=null;
                                    imagePost.setImageResource(R.drawable.ic_baseline_add_a_photo_24);
                                }


                            });
                        }

                    });
        }
    }

    private void openGallery() {
        Intent intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
        intentGallery.setType("image/*");
        intentGallery.setAction(Intent.ACTION_PICK);
        startActivityForResult(intentGallery, REQUESCODE);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR =getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {

            pickedImgUri = data.getData();
            imagePost.setImageURI(pickedImgUri);

            imagePost.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            imagePost.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            hint.setVisibility(View.GONE);

        }


    }
    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(PostActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(PostActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
                Toast.makeText(PostActivity.this,"Please accept the required permission", Toast.LENGTH_SHORT).show();

            else
                ActivityCompat.requestPermissions(PostActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
        } else
            openGallery();

    }
}