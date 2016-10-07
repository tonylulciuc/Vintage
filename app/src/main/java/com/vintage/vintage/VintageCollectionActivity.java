package com.vintage.vintage;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.vintage.vintage.adapter.VintageAdapter;
import com.vintage.vintage.adapter.VintageItem;
import com.vintage.vintage.adapter.VintageOnItemClickListener;
import com.vintage.vintage.bean.item;
import com.vintage.vintage.server.Normalizer;
import com.vintage.vintage.server.Server;
import com.vintage.vintage.server.VintageNormalizer;
import com.vintage.vintage.server.base.Result;

import java.util.ArrayList;
import java.util.List;

import static com.vintage.vintage.VintageApplication.DeviceSize;
import static com.vintage.vintage.VintageApplication.SingleItemRequest;
import static com.vintage.vintage.VintageApplication.VintageItemsLeft;
import static com.vintage.vintage.VintageApplication.VintageItemsRight;

public class VintageCollectionActivity extends AppCompatActivity {
    public static Server VintageServer = null;
    protected static Result<List<item>> m_CollectionResultSet;
    protected DrawerLayout    m_DrawerLayoutLeft;
    protected Layout          m_DrawerLayoutContent;
    protected ListView        m_ContentListLeft;
    protected ListView        m_ContentListRight;
    private static Normalizer m_VariationNormalizer    = null;
    private static Normalizer m_YearNormalizer         = null;
    private static Normalizer m_SeriesNumberNormalizer = null;
    protected static String   m_OldQuery               = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vintage_collection);
        initServer();

        m_ContentListLeft   = (ListView)findViewById(R.id.content_list_left);
        m_ContentListRight  = (ListView)findViewById(R.id.content_list_right);
        m_DrawerLayoutLeft  = (DrawerLayout)findViewById(R.id.search_drawer_layout);


        // If device dimensions were not calculated
        if (DeviceSize == null)
            getDeviceDimensions();

        m_ContentListLeft.setVerticalScrollBarEnabled(false);
        m_ContentListRight.setVerticalScrollBarEnabled(false);
        m_ContentListLeft.setOnItemClickListener(new VintageOnItemClickListener(this, VintageItemsLeft));
        m_ContentListRight.setOnItemClickListener(new VintageOnItemClickListener(this, VintageItemsRight));

        initNormalizer();
        adjustTitle();
    }

    /**
     * Adjust to orientation change
     */
    private void adjustTitle(){
        View scrollSearch = findViewById(R.id.title_search_scroll);
        View buttonSearch = findViewById(R.id.title_search_button);
        ViewGroup.LayoutParams lpScrollSearch     = scrollSearch.getLayoutParams();
        ViewGroup.LayoutParams lpButtonSearch     = buttonSearch.getLayoutParams();
        ViewGroup.LayoutParams lpContentListLeft  = m_ContentListLeft.getLayoutParams();
        ViewGroup.LayoutParams lpContentListRight = m_ContentListRight.getLayoutParams();

        // If Orientation is portrait
        if (this.getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            lpScrollSearch.width     = DeviceSize.x - (int)((double)DeviceSize.x * 0.25 + (double)(2 * lpButtonSearch.width));
            lpContentListRight.width = lpContentListLeft.width = DeviceSize.x / 2;
        }else{
            lpScrollSearch.width     = DeviceSize.y - (int)((double)DeviceSize.y * 0.25 + (double)(2 * lpButtonSearch.width));
            lpContentListRight.width = lpContentListLeft.width = DeviceSize.y / 2;
        }

        // Set values
        scrollSearch.setLayoutParams(lpScrollSearch);
        m_ContentListLeft.setLayoutParams(lpContentListLeft);
        m_ContentListRight.setLayoutParams(lpContentListRight);
    }

    /**
     * Resize content list to fit query results
     */
    protected void resizeContentLists(){
        ViewGroup.LayoutParams lpContentListLeft  = m_ContentListLeft.getLayoutParams();
        ViewGroup.LayoutParams lpContentListRight = m_ContentListRight.getLayoutParams();
        int density       = getResources().getDisplayMetrics().densityDpi;
        int listLeftSize  = VintageItemsLeft.size();
        int listRightSize = VintageItemsRight.size();

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
        Display display       = manager.getDefaultDisplay();

        display.getSize(DeviceSize = new Point());
    }

    /**
     * Creates list of items pulled from database
     * @param _name [in] type of item to select
     * @param _query [in] query
     */
    public void preformQuery(String _name, String _query, List<Object> _extra){
        Thread population = new Thread(new Runnable() {
            @Override
            @SuppressWarnings("all")
            public void run() {
                Handler handle = new Handler(Looper.getMainLooper());

                while (m_CollectionResultSet.isSet == false);

                handle.post(new Runnable() {
                    @Override
                    public void run() {
                        populateContentLists();
                        resizeContentLists();
                    }
                });
            }
        });

        // If not initialized
        if (m_CollectionResultSet.result == null){
            m_CollectionResultSet.result = new ArrayList<>();
        }

        // Remove previous elements contained in list
        _extra.clear();
        m_CollectionResultSet.isSet = false;
        m_CollectionResultSet.result.clear();
        VintageServer.get(m_CollectionResultSet, nomalizeQuery(m_OldQuery = _query), Server.DATA_MATCHBOX, _extra);
        population.start();
    }

    /**
     * Populate selection based on query
     */
    protected void populateContentLists(){
        VintageAdapter vintageAdapter;

        VintageItemsRight.add(new VintageItem());
        VintageItemsRight.add(new VintageItem());
        VintageItemsRight.add(new VintageItem());
        VintageItemsRight.add(new VintageItem());
        VintageItemsLeft.add(new VintageItem());
        VintageItemsLeft.add(new VintageItem());

        vintageAdapter = new VintageAdapter(this, R.layout.layout_vintage_item_list, VintageItemsRight);

        m_ContentListLeft.setAdapter(vintageAdapter);
        m_ContentListRight.setAdapter(vintageAdapter);
    }

    /**
     * Normalize user query
     * @param _query [in] user query
     * @return normalized query
     */
    protected String nomalizeQuery(String _query){
        String normalizedQuery;
        String query = new String();

        if (_query.toLowerCase().contains("matchbox car") || _query.toLowerCase().contains("matchbox_car")){
            query = "item_name = \'matchbox_car\'";
        }else{

            // Attempt to find query pertaining to variation information
            normalizedQuery = m_VariationNormalizer.normalize(_query.toLowerCase(), "variation");

            // If found
            if (normalizedQuery.length() > 0)
                query = normalizedQuery;

            // Attempt to find query pertaining to series number
            normalizedQuery = m_SeriesNumberNormalizer.normalize(_query, "series_number");

            // if something was found
            if (normalizedQuery.length() > 0) {
                // Does there exist a 'norm' query statement
                if (query.length() > 0)
                    query += " OR " + normalizedQuery;
                else
                    query = normalizedQuery;
            }

            // Attempt to find query pertaining to the year it was made
            normalizedQuery = m_YearNormalizer.normalize(_query, "year");

            // if something was found
            if (normalizedQuery.length() > 0) {
                // Does there exist a 'norm' query statement
                if (query.length() > 0)
                    query += " OR " + normalizedQuery;
                else
                    query = normalizedQuery;
            }
        }

        return (query);
    }
    /**
     * Initialize database connection
     */
    private void initServer(){
        // Initialize
        if (VintageServer == null){
            VintageServer         = new Server(this);
            SingleItemRequest     = new Result<>();
            VintageItemsLeft = new ArrayList<>();
            VintageItemsRight = new ArrayList<>();
            m_CollectionResultSet = new Result<>();

            // Login into server to access database
            VintageServer.login("tuf77259@temple.edu", "dh5yfhf4jakdj");
        }
    }

    /**
     * Initialize normalizing objects
     */
    private void initNormalizer(){
        if (m_VariationNormalizer == null){
            initVariationNormalizer();
            initSeriesNormalizer();
            initYearNormalizer();
        }
    }

    /**
     * Initialize variation normalizer
     */
    private void initVariationNormalizer(){
        List<String> keys = new ArrayList<>();
        keys.add("red");
        keys.add("two");
        keys.add("level");
        keys.add("trolley");
        keys.add("bus");
        keys.add("olive");
        keys.add("green");
        keys.add("yellow");
        keys.add("military");
        keys.add("caravan");
        keys.add("tan");
        keys.add("dump");
        keys.add("bed");
        keys.add("brown");
        keys.add("gray");
        keys.add("tractor");
        keys.add("car");
        keys.add("blue");
        keys.add("truck");
        keys.add("trailer");
        keys.add("orange");
        keys.add("bucket");
        keys.add("fire");
        keys.add("hook");
        keys.add("metal");
        keys.add("bucket");
        keys.add("roller");
        keys.add("tampos");
        keys.add("wheel");
        keys.add("white");
        m_VariationNormalizer = new VintageNormalizer(keys);
    }

    /**
     * Initialize series number normalizer
     */
    private void initSeriesNormalizer(){
        List<String> keys = new ArrayList<>();
        keys.add("56-A");
        keys.add("49-A");
        keys.add("23-B");
        keys.add("40-A");
        keys.add("30-A");
        keys.add("4-B");
        keys.add("27-A");
        keys.add("24-A");
        keys.add("22-A");
        keys.add("18-A");
        keys.add("13-A");
        keys.add("9-A");
        keys.add("2-A");
        keys.add("1-A");
        m_SeriesNumberNormalizer = new VintageNormalizer(keys);
    }

    /**
     * Initialize year normalizer
     */
    private void initYearNormalizer(){
        List<String> keys = new ArrayList<>();
        keys.add("1953");
        keys.add("1955");
        keys.add("1956");
        keys.add("1957");
        keys.add("1958");
        m_YearNormalizer = new VintageNormalizer(keys);
    }

}
