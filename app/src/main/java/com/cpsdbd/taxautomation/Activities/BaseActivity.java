package com.cpsdbd.taxautomation.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.TextView;

import com.cpsdbd.taxautomation.NavigationDrawer;
import com.cpsdbd.taxautomation.R;


/**
 * Created by Sohel on 1/31/2018.
 */

public class BaseActivity extends AppCompatActivity {
    private TextView tvTitle;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private FragmentManager manager;
    private NavigationDrawer drawerFragment;

    public ProgressDialog mProgressDialog;




    public void setupToolbar() {
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }


    public void setUpNavigationDrawer(){
        setupToolbar();
        manager = getSupportFragmentManager();
        mDrawerLayout =  findViewById(R.id.drawer_layout);
        drawerFragment =
                (NavigationDrawer) manager.findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, mDrawerLayout, toolbar);
        //getSupportActionBar().setTitle(Constant.HOME);
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }




    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading, Please Wait...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }



}
