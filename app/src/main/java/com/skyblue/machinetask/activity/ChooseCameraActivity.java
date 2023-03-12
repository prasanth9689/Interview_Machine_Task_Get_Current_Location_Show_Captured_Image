package com.skyblue.machinetask.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.skyblue.machinetask.databinding.ActivityChooseCameraBinding;

import java.io.File;

public class ChooseCameraActivity extends AppCompatActivity {
    private ActivityChooseCameraBinding binding;
    Uri selectedImageUri;
    int SELECT_PICTURE = 200;
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 100;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseCameraBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Check permisson
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            storagePermission();
        }

        loadLastCapturedImage();

        binding.pickImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });

        binding.startCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CameraActivity.class);
                startActivity(intent);
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadLastCapturedImage() {
        File file = new File(getExternalFilesDir(null), "pic.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

        if (bitmap != null){
            binding.cpImage.setVisibility(View.VISIBLE);
            binding.cpImgText.setVisibility(View.VISIBLE);

            binding.cpImage.setImageBitmap(bitmap);

        }else {
            binding.cpImage.setVisibility(View.INVISIBLE);
            binding.cpImgText.setVisibility(View.INVISIBLE);
        }
    }

    private void storagePermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            //Storage Permission already granted
        }
    }

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    binding.imageView.setVisibility(View.VISIBLE);
                    binding.imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE_REQUEST_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //  TODO
                } else {
                    Toast.makeText(context, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        loadLastCapturedImage();
    }
}