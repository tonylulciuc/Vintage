package com.vintage.vintage;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VintageSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vintage_splash);
        Intent intent = new Intent(VintageSplashActivity.this, VintageCollectionActivity.class);
        startActivity(intent);
        finish();
    }
}
