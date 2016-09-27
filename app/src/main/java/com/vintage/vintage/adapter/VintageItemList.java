package com.vintage.vintage.adapter;

import java.util.List;

/**
 * Created by Antony Lulciuc on 9/26/2016.
 */

public class VintageItemList {
    List<VintageItem> m_VintageItems;

    public VintageItemList(){
    }

    public void setVintageItems(List<VintageItem> _vintageeItems){
        m_VintageItems = _vintageeItems;
    }

    public List<VintageItem> getVintageItems(){
        return (this.m_VintageItems);
    }
}
