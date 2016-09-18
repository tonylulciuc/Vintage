package com.vintage.vintage;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VintageCollectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vintage_collection);
    }

    @Override
    public void onConfigurationChanged(Configuration _configuration){
        super.onConfigurationChanged(_configuration);

        switch (_configuration.orientation){
            case Configuration.ORIENTATION_PORTRAIT:
                changeToPortrait(_configuration);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                changeToLandscape(_configuration);
                break;
        }
    }

    /**
     * Change app view based on Portrait viewing
     * @param _configuration [in] portrait config
     */
    protected void changeToPortrait(Configuration _configuration){

    }

    /**
     * Change app view based on Landscape viewing
     * @param _configuration [in] landscape config
     */
    protected void changeToLandscape(Configuration _configuration){

    }

}
