package com.skyblue.machinetask.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.skyblue.machinetask.MainActivity;
import com.skyblue.machinetask.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    Handler mHandler;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mHandler=new Handler();
        mHandler.postDelayed(() -> {
            Intent intent=new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        },1900); // 1500
    }
}