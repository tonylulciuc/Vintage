package com.vintage.vintage.server.base;

import android.graphics.Bitmap;

import com.backendless.BackendlessCollection;
import com.vintage.vintage.bean.item;
import com.vintage.vintage.bean.item_info;
import com.vintage.vintage.bean.item_link;


/**
 * Created by Antony Lulciuc on 9/20/2016.
 * Used to store results from queries made
 */

public class SingleItem {
    public item      header = null;
    public item_info description = null;
    public BackendlessCollection<item_link> sources = null;
    public Bitmap itemImage = null;
}
