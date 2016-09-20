package com.vintage.vintage.server.base;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Antony Lulciuc on 9/20/2016.
 */

public class ToastMsg implements Instruction {
    /**
     * Execute procedure on data provided
     * @param _data [in] what to process
     */
    public void execute(Object[] _data){
        if (_data.length == 3){
            Toast.makeText((Context)_data[0], (String)_data[1], (int)_data[2]).show();
        }
    }
}
