package com.locantoapps.stupidquiz;


import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.flurry.android.FlurryAgent;

public class MainActivity extends AppCompatActivity {
    Button level1, level2, level3, level4, level5, level6;
    static int count = 0;
    static int reopen=1;
    public String flag;
    public AppLovinAd loadedAd;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "7GQ587HPXZBVGZ4XTQZP");
                    /* interstitial Ads  */
        AppLovinSdk.initializeSdk(this);
        final AppLovinInterstitialAdDialog interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( this ), this );
        AppLovinSdk.getInstance( this ).getAdService().loadNextAd( AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener()
        {
            @Override
            public void adReceived(AppLovinAd ad)
            {
                loadedAd =ad;
            }

            @Override
            public void failedToReceiveAd(int errorCode)
            {

            }
        } );
                         /*  End OF interstitial Ads  */

       /* if(reopen==2){
            level2.setEnabled(true);
            level2.setBackground(getResources().getDrawable(R.drawable.btn2));
            level2.setTextColor(getResources().getColor(R.color.white1));
        }*/


        level1 = findViewById(R.id.Level1);
        level2 = findViewById(R.id.Level2);
        level3 = findViewById(R.id.Level3);
        level4 = findViewById(R.id.Level4);
        level5 = findViewById(R.id.Level5);
        level6 = findViewById(R.id.Level6);
        /* when Level Completed Successfully and check value that comes fron completed level or enable next level  */
        if (count == 1) {
            Intent intent = getIntent();
            flag= intent.getStringExtra("flag");
            if (flag.equals("ok")) {
                level2.setEnabled(true);
                level2.setBackground(getResources().getDrawable(R.drawable.btn2));
                level2.setTextColor(getResources().getColor(R.color.white1));
            }
             if(flag.equals("ok2")) {
                Level2();
            }
            if (flag.equals("ok3")) {
                Level3();

            }
            if (flag.equals("ok4")) {
                Level4();

            }
            if (flag.equals("ok5")) {
                Level5();

            }

        }
        /* End Of Condition  */
                        /* Button Click Listner   */

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Questionlist.class);
                count++;
                startActivity(intent);


            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                interstitialAd.showAndRender( loadedAd );
                count = 1;
                startActivity(intent);
                reopen=2;
                SharedPreferences settings = getSharedPreferences(MyPREFERENCES, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("reopen",reopen);
                editor.commit();


            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                interstitialAd.showAndRender( loadedAd );
                count = 1;
                startActivity(intent);
                reopen=3;
                SharedPreferences settings = getSharedPreferences(MyPREFERENCES, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("reopen",reopen);
                editor.commit();

            }
        });
        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                interstitialAd.showAndRender( loadedAd );
                count = 1;
                startActivity(intent);
                reopen=4;
                SharedPreferences settings = getSharedPreferences(MyPREFERENCES, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("reopen",reopen);
                editor.commit();


            }
        });
        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main5Activity.class);
                interstitialAd.showAndRender( loadedAd );
                count = 1;
                startActivity(intent);
                reopen=5;
                SharedPreferences settings = getSharedPreferences(MyPREFERENCES, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("reopen",reopen);
                editor.commit();
            }
        });
        level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main6Activity.class);
                interstitialAd.showAndRender( loadedAd );
                count = 1;
                startActivity(intent);
                reopen=6;
                SharedPreferences settings = getSharedPreferences(MyPREFERENCES, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("reopen",reopen);
                editor.commit();
            }
        });
        /*  End  Button Click Listner   */
                                 /* Code To Maintain History   */
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences settings = getSharedPreferences(MyPREFERENCES, 0);
        reopen=settings.getInt("reopen",reopen);


            if (reopen == 2) {
                level2.setEnabled(true);
                level2.setBackground(getResources().getDrawable(R.drawable.btn2));
                level2.setTextColor(getResources().getColor(R.color.white1));
                return;
            }
            if(reopen==3){
                Level2();
                return;
            }
            if(reopen==4){
                Level3();
                return;
            }
            if(reopen==5){
                Level4();
                return;
            }
            if(reopen==6){
                Level5();
                return;
            }
        /*  End Code To Maintain History   */


    }
    /* functions that tell which level call or which not  */
    public void Level2() {
        level2.setEnabled(true);
        level3.setEnabled(true);
        level2.setBackground(getResources().getDrawable(R.drawable.btn2));
        level2.setTextColor(getResources().getColor(R.color.white1));
        level3.setBackground(getResources().getDrawable(R.drawable.btn3));
        level3.setTextColor(getResources().getColor(R.color.white1));
    }

    public void Level3() {
        level2.setEnabled(true);
        level3.setEnabled(true);
        level4.setEnabled(true);
        level4.setBackground(getResources().getDrawable(R.drawable.btn4));
        level4.setTextColor(getResources().getColor(R.color.white1));
        level2.setBackground(getResources().getDrawable(R.drawable.btn2));
        level2.setTextColor(getResources().getColor(R.color.white1));
        level3.setBackground(getResources().getDrawable(R.drawable.btn3));
        level3.setTextColor(getResources().getColor(R.color.white1));

    }

    public void Level4() {
        level2.setEnabled(true);
        level3.setEnabled(true);
        level4.setEnabled(true);
        level5.setEnabled(true);
        level5.setBackground(getResources().getDrawable(R.drawable.btn5));
        level5.setTextColor(getResources().getColor(R.color.white1));
        level4.setBackground(getResources().getDrawable(R.drawable.btn4));
        level4.setTextColor(getResources().getColor(R.color.white1));
        level2.setBackground(getResources().getDrawable(R.drawable.btn2));
        level2.setTextColor(getResources().getColor(R.color.white1));
        level3.setBackground(getResources().getDrawable(R.drawable.btn3));
        level3.setTextColor(getResources().getColor(R.color.white1));

    }

    public void Level5() {
        level2.setEnabled(true);
        level3.setEnabled(true);
        level4.setEnabled(true);
        level5.setEnabled(true);
        level6.setEnabled(true);
        level6.setBackground(getResources().getDrawable(R.drawable.btn6));
        level6.setTextColor(getResources().getColor(R.color.white1));
        level5.setBackground(getResources().getDrawable(R.drawable.btn5));
        level5.setTextColor(getResources().getColor(R.color.white1));
        level4.setBackground(getResources().getDrawable(R.drawable.btn4));
        level4.setTextColor(getResources().getColor(R.color.white1));
        level2.setBackground(getResources().getDrawable(R.drawable.btn2));
        level2.setTextColor(getResources().getColor(R.color.white1));
        level3.setBackground(getResources().getDrawable(R.drawable.btn3));
        level3.setTextColor(getResources().getColor(R.color.white1));

    }
                 /* End of Functions */

    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }


}
