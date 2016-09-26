package com.vintage.vintage;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.BackendlessCollection;
import com.vintage.vintage.adapter.VintageAdapter;
import com.vintage.vintage.adapter.VintageItem;
import com.vintage.vintage.bean.item;
import com.vintage.vintage.server.Server;
import com.vintage.vintage.server.base.Result;

import java.util.ArrayList;
import java.util.List;

public class VintageCollectionActivity extends AppCompatActivity {
    public static Point DeviceSize = null;
    public static Server VintageServer;
    protected Result<BackendlessCollection<item>> m_ResultSet;
    protected DrawerLayout m_DrawerLayoutLeft;
    protected Layout       m_DrawerLayoutContent;
    protected ListView     m_ContentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vintage_collection);

        m_ContentList = (ListView)findViewById(R.id.content_list);
        m_DrawerLayoutLeft = (DrawerLayout)findViewById(R.id.search_drawer_layout);
        m_ResultSet = new Result<>();
        VintageServer = new Server(this);
        VintageServer.login("tuf77259@temple.edu", "dh5yfhf4jakdj");

        if (DeviceSize == null)
            getDeviceDimensions();

        // TEMPORARY
        List<VintageItem> items = new ArrayList<>();
        items.add(new VintageItem());
        items.add(new VintageItem());
        items.add(new VintageItem());


        VintageAdapter vintageAdapter = new VintageAdapter(this, R.layout.layout_vintage_item, items);
        m_ContentList.setAdapter(vintageAdapter);
        // TEMPORARY

        //populateList("matchbox_car", "0-10");
        adjustTitle();
    }

    /**
     * Adjust to orientation change
     */
    private void adjustTitle(){
        View scrollSearch = findViewById(R.id.title_search_scroll);
        View buttonSearch = findViewById(R.id.title_search_button);
        View titleSearch  = findViewById(R.id.title_search_bar);
        ViewGroup.LayoutParams lpScrollSearch = scrollSearch.getLayoutParams();
        ViewGroup.LayoutParams lpButtonSearch = buttonSearch.getLayoutParams();
        ViewGroup.LayoutParams lpResultScroll = m_ContentList.getLayoutParams();
        ViewGroup.LayoutParams lpTitleSearch  = titleSearch.getLayoutParams();

        if (this.getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            lpScrollSearch.width = DeviceSize.x - (int)((double)DeviceSize.x * 0.25 + (double)(2 * lpButtonSearch.width));
            lpResultScroll.height = DeviceSize.y - lpTitleSearch.height;
        }else{
            lpScrollSearch.width = DeviceSize.y - (int)((double)DeviceSize.y * 0.25 + (double)(2 * lpButtonSearch.width));
            lpResultScroll.height = DeviceSize.x - lpTitleSearch.height;
        }


        scrollSearch.setLayoutParams(lpScrollSearch);
        m_ContentList.setLayoutParams(lpResultScroll);
    }

    /**
     * Acquire device size
     */
    private void getDeviceDimensions(){
        WindowManager manager = getWindowManager();
        Display display = manager.getDefaultDisplay();
        display.getSize(DeviceSize = new Point());
    }

    /**
     * Creates list of items pulled from database
     * @param _name [in] type of item to select
     * @param _query [in] query
     */
    public void populateList(String _name, String _query){
        Thread population = new Thread(new Runnable() {
            @Override
            @SuppressWarnings("all")
            public void run() {
                Handler handle = new Handler(Looper.getMainLooper());

                while (m_ResultSet.result == null);

                populateList();
            }
        });

        VintageServer.get(m_ResultSet, _query, Server.DATA_MATCHBOX);
        population.start();
    }

    /**
     *
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

    private void populateList(){
        // TODO : populate List view with resultset
    }
}
