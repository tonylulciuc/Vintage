package com.vintage.vintage.server.base;

/**
 * Created by Antony Lulciuc on 9/20/2016.
 */

public interface Instruction {
    /**
     * Execute procedure on data provided
     * @param _data [in] what to process
     */
    void execute(Object[] _data);
}
