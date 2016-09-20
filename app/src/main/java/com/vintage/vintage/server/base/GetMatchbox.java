package com.vintage.vintage.server.base;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.persistence.BackendlessDataQuery;
import com.vintage.vintage.bean.item;
import com.vintage.vintage.bean.item_info;
import com.vintage.vintage.bean.item_link;

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
                BackendlessDataQuery bdq = new BackendlessDataQuery();
                Result result            = (Result) _data[0];
                String query             = (String) _data[1];

                // If result is requesting a collection
                if (result.result instanceof BackendlessCollection) {
                    bdq.setWhereClause(query);
                    result.setResult(Backendless.Persistence.of(item.class).find(bdq));
                } else {
                    // If a single item is desired
                    if (result.result instanceof SingleItem) {
                        SingleItem singleItem = (SingleItem)result.result;

                        // set item_id
                        bdq.setWhereClause("objectId=" + singleItem.header.getObjectId());

                        // Get matchbox description
                        singleItem.description = Backendless.Persistence.of(item_info.class).findById(singleItem.header.getItem_info_id());

                        // Get sources for matchbox
                        singleItem.sources = Backendless.Persistence.of(item_link.class).find(bdq);
                    }
                }
            }
        }catch (Exception err){
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new RunnableInstruction(new ToastMsg(), new Object[]{err.getMessage(), _data[2], Toast.LENGTH_SHORT}));
        }
    }
}
