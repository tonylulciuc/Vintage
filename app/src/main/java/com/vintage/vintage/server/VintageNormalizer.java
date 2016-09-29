package com.vintage.vintage.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antony Lulciuc on 9/28/2016.
 */

public class VintageNormalizer implements Normalizer {
    private static List<String> m_Keys = new ArrayList<>();

    /**
     * Constructor
     * @param _lKeys [in] keys used to normalize query
     */
    public VintageNormalizer(List<String> _lKeys){
        m_Keys.addAll(_lKeys);
    }

    /**
     * Normalize query for apecified colume
     * @param _query [in] query to normalize
     * @param _for [in] from specified column
     * @return Normalizes query
     */
    @Override
    public String normalize(String _query, String _for){
       return (assembleKeys(findKeys(_query), _for));
    }

    /**
     * Find keys contained in user query
     * @param _query [in] user query
     * @return List of keys found in user query
     */
    private  List<String> findKeys(String _query){
        List<String> contains = new ArrayList<>();

        for (String key : m_Keys){
            if (_query.contains(key)){
                contains.add(key);
            }
        }

        return (contains);
    }

    /**
     * Assembles query based on keys found and column defined
     * @param _keys [in] keys found in user query
     * @param _for [in] column query pertains to
     * @return normalized query
     */
    private String assembleKeys(List<String> _keys, String _for){
        String norm = _for;
        int totalKeyCount = _keys.size();
        int currentKey    = 0;

        for (String key : _keys){
            if ((currentKey + 1) < totalKeyCount){
                norm += " LIKE \'%" + key + "%\' OR " + _for;
            }else{
                norm += " LIKE \'%" + key + "%\'";
            }

            ++currentKey;
        }


        return (norm);
    }
}
