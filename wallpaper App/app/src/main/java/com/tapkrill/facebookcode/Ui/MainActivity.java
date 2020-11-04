package com.tapkrill.facebookcode.Ui;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import com.google.android.material.tabs.TabLayout;
import com.tapkrill.facebookcode.Adapter.PagerAdapter;
import com.tapkrill.facebookcode.R;


public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    RelativeLayout relativelayout_chkConnection;
    TabLayout tabLayout;
    ViewPager viewPager;
     Button aboutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize widgets
        init();

        //calling method to check internet availability
        checkInternet();

        /// assign Pager Adapter in viewpager
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        /// assign viewpager to tablayout
        tabLayout.setupWithViewPager(viewPager);

        //Change Tab color
        tabLayout.setTabTextColors(Color.parseColor("#a5fafafa"), Color.parseColor("#ffffff"));

        // about Button oonclickLister for popup Menu
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(MainActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.popupmenu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(MainActivity.this);
                popupMenu.show();
            }
        });




    }

    public void init(){
        tabLayout=findViewById(R.id.Tablayout);
        viewPager=findViewById(R.id.Viewpager);
        relativelayout_chkConnection=findViewById(R.id.relativelayout_chkConnection);
        aboutbtn=findViewById(R.id.aboutbtn);
    }

    public void onBackPressed(){
        super.onBackPressed();
        Intent intent =new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void checkInternet(){
        ConnectivityManager mgr = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                relativelayout_chkConnection.setVisibility(View.GONE);

            }else {
                relativelayout_chkConnection.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);


            }
        } else {
            relativelayout_chkConnection.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.abouts:
                startActivity(new Intent(MainActivity.this,Aboutus.class));
                return true;
        }
        return true;
    }
}
