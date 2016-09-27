package com.vintage.vintage;

import android.os.Bundle;

/**
 * Created by Antony Lulciuc on 9/27/2016.
 */

public class VintageActivity extends VintageCollectionActivity {


    @Override
    public void onCreate(Bundle _savedInstance){
        super.onCreate(_savedInstance);


        preformQuery("matchbox_car", "name=matchbox_car");
    }




}
