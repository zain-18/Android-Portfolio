package com.hiddenskull.myapplication.UI;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hiddenskull.myapplication.R;

public class Aboutus extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

    }

    public void backed(View view) {
        onBackPressed();
    }


    public void onBackPressed(){
        super.onBackPressed();


    }
}
