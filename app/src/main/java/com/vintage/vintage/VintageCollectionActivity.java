package com.vintage.vintage;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Handler;
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
import android.widget.Toast;

import com.backendless.BackendlessCollection;
import com.vintage.vintage.bean.item;
import com.vintage.vintage.server.Server;
import com.vintage.vintage.server.base.Result;

public class VintageCollectionActivity extends AppCompatActivity {
    public static Point DeviceSize = null;
    public static Server VintageServer;
    protected Result<BackendlessCollection<item>> m_ResultSet;
    protected DrawerLayout m_DrawerLayoutLeft;
    protected Layout       m_DrawerLayoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vintage_collection);

        m_DrawerLayoutLeft = (DrawerLayout)findViewById(R.id.search_drawer_layout);
        m_ResultSet = new Result<>();
        VintageServer = new Server(this);
        VintageServer.login("tuf77259@temple.edu", "dh5yfhf4jakdj");

        if (DeviceSize == null)
            getDeviceDimensions();


        populateList("matchbox_car", "0-10");
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

        if (this.getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            lpScrollSearch.width = DeviceSize.x - (int)((double)DeviceSize.x * 0.25 + (double)(2 * lpButtonSearch.width));
        }else{
            lpScrollSearch.width = DeviceSize.y - (int)((double)DeviceSize.y * 0.25 + (double)(2 * lpButtonSearch.width));
        }

        scrollSearch.setLayoutParams(lpScrollSearch);
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

}
