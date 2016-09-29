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
        TextView textView;

        // ITEM INFORMATION

        // IMAGE STORED
        imageView.setImageBitmap(SingleItemRequest.result.itemImage);

        // ITEM MODEL
        textView = (TextView)findViewById(R.id.vintage_item_model_text);
        textView.setText(SingleItemRequest.result.header.getModel());

        // YEAR
        textView = (TextView)findViewById(R.id.vintage_item_year_text);
        textView.setText(SingleItemRequest.result.header.getYear().toString());

        // SCARCITY
        textView = (TextView)findViewById(R.id.vintage_item_scarcity_text);


        // ORIGINAL PRICE
        textView = (TextView)findViewById(R.id.vintage_item_original_price_text);

        // CURRENT PRICE
        textView = (TextView)findViewById(R.id.vintage_item_current_price_text);

        // SERIES NUMBER
        textView = (TextView)findViewById(R.id.vintage_item_series_number_text);
        textView.setText(SingleItemRequest.result.header.getSeries_number());

        // VARIATION
        textView = (TextView)findViewById(R.id.vintage_item_variation_text);
        textView.setText(SingleItemRequest.result.header.getVariation());

        // DESCRIPTION
        textView = (TextView)findViewById(R.id.vintage_item_description_text);
        textView.setText(SingleItemRequest.result.description.getDescription());
        // SOURCES
        textView = (TextView)findViewById(R.id.vintage_item_sources_text);


    }
}
