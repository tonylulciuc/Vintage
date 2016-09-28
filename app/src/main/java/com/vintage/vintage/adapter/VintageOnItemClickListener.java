package com.vintage.vintage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.vintage.vintage.VintageCollectableDisplayActivity;
import com.vintage.vintage.VintageCollectionActivity;
import com.vintage.vintage.server.Server;
import com.vintage.vintage.server.base.SingleItem;

import java.util.List;

import static com.vintage.vintage.VintageCollectionActivity.SingleItemRequest;
import static com.vintage.vintage.VintageCollectionActivity.VintageServer;

/**
 * Created by Antony Lulciuc on 9/28/2016.
 */

public class VintageOnItemClickListener implements AdapterView.OnItemClickListener {
    private List<VintageItem> m_lVintageItems;
    private Context m_Context;

    public VintageOnItemClickListener(Context _context, List<VintageItem> _vintageItems){
        m_lVintageItems = _vintageItems;
        m_Context = _context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(m_Context, VintageCollectableDisplayActivity.class);
        SingleItemRequest.result = new SingleItem();
        SingleItemRequest.isSet = false;
        SingleItemRequest.result.header = m_lVintageItems.get(position).getItem();
        VintageServer.get(SingleItemRequest, null, Server.DATA_MATCHBOX, null);
        m_Context.startActivity(intent);
    }
}
