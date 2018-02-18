package com.cpsdbd.taxautomation.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.cpsdbd.myslider.banners.Banner;
import com.cpsdbd.myslider.banners.RemoteBanner;
import com.cpsdbd.myslider.views.BannerSlider;
import com.cpsdbd.taxautomation.Model.Entity;
import com.cpsdbd.taxautomation.R;
import com.cpsdbd.taxautomation.Utility.MyDatabaseRef;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity {

    private Entity entity;

    private BannerSlider bannerSlider;

    private List<Banner> bannerList;

    MyDatabaseRef myDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        myDatabaseRef = new MyDatabaseRef();

        if(getIntent().getExtras()!=null){
            entity = (Entity) getIntent().getSerializableExtra("entity");
        }

        setupToolbar();

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle(entity.getName());

        bannerSlider = findViewById(R.id.banner_slider);

        bannerList = new ArrayList<>();

        myDatabaseRef.getEntityImageRef().child(entity.getId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot x: dataSnapshot.getChildren()){
                            bannerList.add(new RemoteBanner(x.getValue(String.class)));
                        }

                        bannerSlider.setBanners(bannerList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if(id==android.R.id.home){
            onBackPressed();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
