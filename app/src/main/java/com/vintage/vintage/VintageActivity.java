package com.vintage.vintage;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

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

        if (VintageItemsLeft.size() == 0 && VintageItemsRight.size() == 0)
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
            int count        = 0;

            VintageItemsLeft.clear();
            VintageItemsRight.clear();

            for (item i : items) {
                if (count > 0) {
                    if ((count % 2) == 0)
                        VintageItemsLeft.add(new VintageItem(i, (Bitmap)m_lItemImage.get(count)));
                    else
                        VintageItemsRight.add(new VintageItem(i, (Bitmap)m_lItemImage.get(count)));
                } else {
                    VintageItemsLeft.add(new VintageItem(i, (Bitmap)m_lItemImage.get(count)));
                }

                ++count;
            }

            m_ContentListLeft.setAdapter(new VintageAdapter(this, R.layout.layout_vintage_item_list, VintageItemsLeft));
            m_ContentListRight.setAdapter(new VintageAdapter(this, R.layout.layout_vintage_item_list, VintageItemsRight));
        }
    }

    public  void onSearchClick(View _view){
        EditText searchEditText = (EditText)findViewById(R.id.title_search_edit_text);
        String query = searchEditText.getText().toString();

        preformQuery("matchbox_car", query, m_lItemImage);
    }

    /**
     * Handle on click event for hamburger press
     * @param _view
     */
    public void onHamburgerClick(View _view){
        try {
            if (m_DrawerLayoutLeft.isDrawerOpen(Gravity.LEFT))
                m_DrawerLayoutLeft.closeDrawer(Gravity.LEFT);
            else
                m_DrawerLayoutLeft.openDrawer(Gravity.LEFT);
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    /**
     * Handle refresh click event for drawer
     * @param _view
     */
    public void onRefreshClick(View _view){
        preformQuery("matchbox_car", m_OldQuery, m_lItemImage);
        onHamburgerClick(_view);
    }

    /**
     * Handle matchbox click event for drawer
     * @param _view
     */
    public void onMatchboxClick(View _view){
        preformQuery("matchbox_car", "matchbox car", m_lItemImage);
        onHamburgerClick(_view);
    }
}
