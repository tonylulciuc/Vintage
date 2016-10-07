package com.vintage.vintage;

import android.app.Application;
import android.graphics.Point;

import com.vintage.vintage.adapter.VintageItem;
import com.vintage.vintage.server.Server;
import com.vintage.vintage.server.base.Result;
import com.vintage.vintage.server.base.SingleItem;

import java.util.List;

/**
 * Created by Antony Lulciuc on 10/6/2016.
 */

public class VintageApplication extends Application {
    public static Point DeviceSize     = null;
    public static Result<SingleItem> SingleItemRequest;
    public static List<VintageItem> VintageItemsLeft;
    public static List<VintageItem> VintageItemsRight;

}
