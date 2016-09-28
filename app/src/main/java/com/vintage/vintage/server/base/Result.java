package com.vintage.vintage.server.base;

/**
 * Created by Antony Lulciuc on 9/20/2016.
 */

public class Result<T> {
    public T result = null;
    public boolean isSet = false;

    /**
     * Reinterprets cast for result
     * @param _result
     */
    @SuppressWarnings("all")
    public void setResult(Object _result){
        if (_result != null)
            result = (T)_result;
    }
}
