package com.vintage.vintage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vintage.vintage.adapter.VintageAdapter;
import com.vintage.vintage.adapter.VintageItem;
import com.vintage.vintage.bean.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antony Lulciuc on 9/27/2016.
 */

public class VintageActivity extends VintageCollectionActivity {
    protected static List<Object> m_lItemImage = new ArrayList<>();

    @Override
    public void onCreate(Bundle _savedInstance){
        super.onCreate(_savedInstance);

        if (m_lVintageItemLeft.size() == 0 && m_lVintageItemRight.size() == 0)
            preformQuery("matchbox_car", "matchbox car", m_lItemImage);
        else{
            this.populateContentLists();
            this.resizeContentLists();
        }
    }


    @Override
    protected void populateContentLists() {
        if (m_CollectionResultSet.result != null) {
            List<item> items = m_CollectionResultSet.result;
            int count = 0;

            m_lVintageItemLeft.clear();
            m_lVintageItemRight.clear();

            for (item i : items) {
                if (count > 0) {
                    if ((count % 2) == 0)
                        m_lVintageItemLeft.add(new VintageItem(i, (Bitmap)m_lItemImage.get(count)));

                    else
                        m_lVintageItemRight.add(new VintageItem(i, (Bitmap)m_lItemImage.get(count)));
                } else {
                    m_lVintageItemLeft.add(new VintageItem(i, (Bitmap)m_lItemImage.get(count)));
                }

                ++count;
            }

            m_ContentListLeft.setAdapter(new VintageAdapter(this, R.layout.layout_vintage_item_list, m_lVintageItemLeft));
            m_ContentListRight.setAdapter(new VintageAdapter(this, R.layout.layout_vintage_item_list, m_lVintageItemRight));
        }
    }

    public  void onSearchClick(View _view){
        EditText searchEditText = (EditText)findViewById(R.id.title_search_edit_text);
        String query = searchEditText.getText().toString();

        m_lItemImage.clear();
        preformQuery("matchbox_car", query, m_lItemImage);
    }
}
