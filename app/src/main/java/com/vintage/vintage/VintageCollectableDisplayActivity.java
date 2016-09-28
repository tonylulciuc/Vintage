package com.vintage.vintage;


import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static com.vintage.vintage.VintageCollectionActivity.SingleItemRequest;

public class VintageCollectableDisplayActivity extends AppCompatActivity {

    @Override
    @SuppressWarnings("all")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vintage_collectable_display);
        ActionBar actionBar = this.getSupportActionBar();
        Thread loadThread;
        actionBar.setTitle(SingleItemRequest.result.header.getModel());

        loadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                // Wait until information is ready before display
                while (!SingleItemRequest.isSet);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setItemInformation();
                    }
                });
            }
        });

        loadThread.start();
    }

    protected void setItemInformation(){
        ImageView imageView = (ImageView)findViewById(R.id.vintage_item_high_res_image);
        TextView textView = (TextView)findViewById(R.id.vintage_item_name_text);

        // Set main display
        imageView.setImageBitmap(SingleItemRequest.result.itemImage);



    }
}
