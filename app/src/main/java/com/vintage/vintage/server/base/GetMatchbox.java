package com.vintage.vintage.server.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.vintage.vintage.bean.item;
import com.vintage.vintage.bean.item_info;
import com.vintage.vintage.bean.item_link;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Antony Lulciuc on 9/20/2016.
 */

public class GetMatchbox implements Instruction {

    /**
     * Execute procedure on data provided
     * @param _data [in] what to process
     */
    @SuppressWarnings("all")
    public void execute(Object[] _data){
        try {
            if (_data.length == 3) {
                BackendlessCollection<item> page;
                BackendlessCollection<item> bci;
                BackendlessDataQuery bdq  = new BackendlessDataQuery();
                QueryOptions queryOptions = new QueryOptions();
                List<Bitmap> extra        = (List<Bitmap>)_data[2];
                Result result             = (Result) _data[0];

                // If result is requesting a collection
                if (result.result instanceof List) {
                    List<item> lData  = (List<item>)result.result;
                    String query      = (String) _data[1];
                    List<item> lPage;
                    bdq.setWhereClause(query);
                    bci = Backendless.Persistence.of(item.class).find(bdq);

                    for (item i : bci.getCurrentPage()) {
                        extra.add(downloadImage(i.getItem_img_low_res()));
                        lData.add(i);
                    }

                    while ((page = bci.nextPage()) != null){
                        lPage = page.getCurrentPage();

                        if (lPage == null || lPage.size() == 0){
                            break;
                        }

                        for (item i : lPage){
                            extra.add(downloadImage(i.getItem_img_low_res()));
                            lData.add(i);
                        }

                        bci = page;
                    }

                } else {
                    // If a single item is desired
                    if (result.result instanceof SingleItem) {
                        SingleItem singleItem = (SingleItem)result.result;

                        // set item_id
                        bdq.setWhereClause("objectId=\'" + singleItem.header.getObjectId()+"\'");

                        // Get matchbox description
                        singleItem.description = Backendless.Persistence.of(item_info.class).findById(singleItem.header.getItem_info_id());
                        singleItem.itemImage = downloadImage(singleItem.description.getItem_img_high_res());

                        // Get sources for matchbox
                        try {
                            singleItem.sources = Backendless.Persistence.of(item_link.class).find(bdq);
                        }catch (Exception err){
                            // DO NOTHING
                        }
                    }
                }

                result.isSet = true;
            }
        }catch (Exception err){
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new RunnableInstruction(new ToastMsg(), new Object[]{err.getMessage(), _data[2], Toast.LENGTH_SHORT}));
        }
    }

    private Bitmap downloadImage(String _url) {
        Bitmap bmp = null;

        try {
            URL url = new URL(_url);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bmp = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
        }

        return (bmp);
    }
}
