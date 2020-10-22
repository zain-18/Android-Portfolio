package com.hiddenskull.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;
import com.hiddenskull.myapplication.Adapter.InstructionAdapter;
import com.hiddenskull.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class onboarding extends AppCompatActivity {
ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        viewPager=findViewById(R.id.viewpager);
        InstructionAdapter instructionAdapter=new InstructionAdapter(getSupportFragmentManager());
        viewPager.setAdapter(instructionAdapter);

    }


}
