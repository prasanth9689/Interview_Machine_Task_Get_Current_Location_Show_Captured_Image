package com.skyblue.machinetask.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.skyblue.machinetask.Camera2BasicFragment;
import com.skyblue.machinetask.R;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        if (null == savedInstanceState){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }
}