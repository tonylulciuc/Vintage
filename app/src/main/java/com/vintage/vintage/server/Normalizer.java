package com.vintage.vintage.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Antony Lulciuc on 9/28/2016.
 */
public interface Normalizer {
    /**
     * Normalize query for apecified colume
     * @param _query [in] query to normalize
     * @param _for [in] from specified column
     * @return Normalizes query
     */
    String normalize(String _query, String _for);
}
