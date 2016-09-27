package com.vintage.vintage;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

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
    protected ListView     m_ContentListLeft;
    protected ListView     m_ContentListRight;
    protected List<VintageItem> m_lVintageItemLeft;
    protected List<VintageItem> m_lVintageItemRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vintage_collection);

        m_ContentListLeft   = (ListView)findViewById(R.id.content_list_left);
        m_ContentListRight  = (ListView)findViewById(R.id.content_list_right);
        m_DrawerLayoutLeft  = (DrawerLayout)findViewById(R.id.search_drawer_layout);
        m_ResultSet         = new Result<>();
        VintageServer       = new Server(this);
        m_lVintageItemLeft  = new ArrayList<>();
        m_lVintageItemRight = new ArrayList<>();

        // Login into server to access database
        VintageServer.login("tuf77259@temple.edu", "dh5yfhf4jakdj");

        // If device dimensions were not calculated
        if (DeviceSize == null)
            getDeviceDimensions();

        m_ContentListLeft.setVerticalScrollBarEnabled(false);
        m_ContentListRight.setVerticalScrollBarEnabled(false);

        // TEMPORARY
        AdapterView.OnItemClickListener ocl = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VintageCollectionActivity.this, VintageItemSpecActivity.class);
                startActivity(intent);
            }
        };

        m_ContentListLeft.setOnItemClickListener(ocl);
        m_ContentListRight.setOnItemClickListener(ocl);

        adjustTitle();
    }

    /**
     * Adjust to orientation change
     */
    private void adjustTitle(){
        View scrollSearch = findViewById(R.id.title_search_scroll);
        View buttonSearch = findViewById(R.id.title_search_button);
        ViewGroup.LayoutParams lpScrollSearch = scrollSearch.getLayoutParams();
        ViewGroup.LayoutParams lpButtonSearch = buttonSearch.getLayoutParams();
        ViewGroup.LayoutParams lpContentListLeft  = m_ContentListLeft.getLayoutParams();
        ViewGroup.LayoutParams lpContentListRight = m_ContentListRight.getLayoutParams();

        if (this.getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            lpScrollSearch.width     = DeviceSize.x - (int)((double)DeviceSize.x * 0.25 + (double)(2 * lpButtonSearch.width));
            lpContentListRight.width = lpContentListLeft.width = DeviceSize.x / 2;
        }else{
            lpScrollSearch.width     = DeviceSize.y - (int)((double)DeviceSize.y * 0.25 + (double)(2 * lpButtonSearch.width));
            lpContentListRight.width = lpContentListLeft.width = DeviceSize.y / 2;
        }

        scrollSearch.setLayoutParams(lpScrollSearch);
        m_ContentListLeft.setLayoutParams(lpContentListLeft);
        m_ContentListRight.setLayoutParams(lpContentListRight);
    }

    protected void resizeContentLists(){
        ViewGroup.LayoutParams lpContentListLeft = m_ContentListLeft.getLayoutParams();
        ViewGroup.LayoutParams lpContentListRight = m_ContentListRight.getLayoutParams();
        int density = getResources().getDisplayMetrics().densityDpi;
        int listLeftSize = m_lVintageItemLeft.size();
        int listRightSize = m_lVintageItemRight.size();

        lpContentListRight.height = density * listRightSize;
        lpContentListLeft.height  = density * listLeftSize;
        m_ContentListLeft.setLayoutParams(lpContentListLeft);
        m_ContentListRight.setLayoutParams(lpContentListRight);
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
    public void preformQuery(String _name, String _query){
        Thread population = new Thread(new Runnable() {
            @Override
            @SuppressWarnings("all")
            public void run() {
                Handler handle = new Handler(Looper.getMainLooper());

                while (m_ResultSet.result == null);

                populateContentLists();
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

    /**
     * Populate selection based on query
     */
    protected void populateContentLists(){
        VintageAdapter vintageAdapter;

        m_lVintageItemRight.add(new VintageItem());
        m_lVintageItemRight.add(new VintageItem());
        m_lVintageItemRight.add(new VintageItem());
        m_lVintageItemRight.add(new VintageItem());
        m_lVintageItemLeft.add(new VintageItem());
        m_lVintageItemLeft.add(new VintageItem());

        vintageAdapter = new VintageAdapter(this, R.layout.layout_vintage_item_list, m_lVintageItemRight);

        m_ContentListLeft.setAdapter(vintageAdapter);
        m_ContentListRight.setAdapter(vintageAdapter);
    }
}
