package com.vintage.vintage.adapter;

import android.graphics.Bitmap;

import com.vintage.vintage.bean.item;

/**
 * Created by Antony Lulciuc on 9/26/2016.
 */

public class VintageItem {
    private item m_Item;
    private Bitmap bmp;

    public VintageItem(){}
    public VintageItem(item _item, Bitmap _bmp){
        m_Item = _item;
        bmp = _bmp;
    }
    public void setItem(item _item){
        m_Item = _item;
    }
    public void setBitmap(Bitmap _bmp){
        bmp = _bmp;
    }
    public item getItem(){
        return (this.m_Item);
    }
    public Bitmap getBitmap(){ return (this.bmp); }
}
