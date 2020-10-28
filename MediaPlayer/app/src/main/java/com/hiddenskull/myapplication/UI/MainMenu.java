package com.hiddenskull.myapplication.UI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hiddenskull.myapplication.Adapter.PagerAdapter;
import com.hiddenskull.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Button transferBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();

        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


    }

    public void init(){
        toolbar=findViewById(R.id.toolbar);
        tabLayout=findViewById(R.id.tabslayout);
        viewPager=findViewById(R.id.viewPager);
    }
    public int REQUEST_ID_MULTIPLE_PERMISSION = 1;

public boolean checkAndRequestPermission(){
        int readPermission=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
            List<String> premissionList=new ArrayList<>();
        if(readPermission!=PackageManager.PERMISSION_GRANTED){
            premissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(!premissionList.isEmpty()){
            ActivityCompat.requestPermissions(this,premissionList.toArray(new String[premissionList.size()]),REQUEST_ID_MULTIPLE_PERMISSION);
            return false;
        }
        return true;
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
       /* switch (item.getItemId()){
            case R.id.transfer:
                startActivity(new Intent(MainMenu.this,Wifi_Direct.class));
                return true;

        }*/
        return true;
    }

    public void back(View view) {

    startActivity(new Intent(MainMenu.this,MainActivity.class));
    }

    public void onBackPressed() {

        startActivity(new Intent(MainMenu.this,MainActivity.class));
        finish();
        return;
    }
}
