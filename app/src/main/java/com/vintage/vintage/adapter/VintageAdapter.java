package com.vintage.vintage.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.vintage.vintage.R;

import java.util.List;

/**
 * Created by Antony Lulciuc on 9/26/2016.
 */

public class VintageAdapter extends ArrayAdapter<VintageItem> {

    // declaring our ArrayList of items
    private List<VintageItem> objects;
    private int resourceId;
    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */

    public VintageAdapter(Context context, int textViewResourceId, List<VintageItem> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
        resourceId = textViewResourceId;
    }

    /*
     * we are overriding the getView method here - this is what defines how each
     * list item will look.
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resourceId, null);
        }

        VintageItem i = objects.get(position);
        ImageView imageView = (ImageView)v.findViewById(R.id.vintage_item_low_res_test);

        if (i != null) {
            imageView.setImageBitmap(i.getBitmap());
        }

        return (v);
    }

    public VintageItem getContent(int _position){
        return (objects.get(_position));
    }
}
