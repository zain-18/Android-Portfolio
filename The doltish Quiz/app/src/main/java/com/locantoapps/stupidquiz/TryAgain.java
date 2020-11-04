package com.locantoapps.stupidquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinSdk;

public class TryAgain extends AppCompatActivity {
TextView tv;
Button Watch, Try;
public String unlock;
    private AppLovinIncentivizedInterstitial myIncent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_again);
        AppLovinSdk.initializeSdk(this);
        myIncent = AppLovinIncentivizedInterstitial.create(this);
        myIncent.preload(null);
        Intent intent = getIntent();
        int wrong = intent.getIntExtra("Wrong",0);
        unlock=intent.getStringExtra("unlock");
        Try=findViewById(R.id.tryagain);
        tv=findViewById(R.id.wrong);
        tv.setText("Wrong :" +wrong);
        Watch=findViewById(R.id.watch);
        Watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myIncent.isAdReadyToDisplay()) {
                    // A rewarded video is available.  Call the show method with the listeners you want to use.
                    // We will use the display listener to preload the next rewarded video when this one finishes.

                    myIncent.show(TryAgain.this, null, null, new AppLovinAdDisplayListener() {
                        @Override
                        public void adDisplayed(AppLovinAd appLovinAd) {

                        }

                        @Override
                        public void adHidden(AppLovinAd appLovinAd) {
                            // A rewarded video was closed.  Preload the next video now.  We won't use a load listener.
                            myIncent.preload(null);
                        }
                    });
                    /*new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(15*1000);
                                unlockToWatch();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();*/
                    unlockToWatch();


                } else {
                    Toast.makeText(TryAgain.this, "Slow Network", Toast.LENGTH_SHORT).show();
                    // No ad is currently available.  Perform failover logic...
                }



            }
        });
        Try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tryagain();
            }
        });

    }
    public  void unlockToWatch(){
        if(unlock.equals("unlock2")){
            Intent intent=new Intent(TryAgain.this,MainActivity.class);
            intent.putExtra("flag","ok");
            startActivity(intent);
            finish();
        }
        if(unlock.equals("unlock3")){
            Intent intent=new Intent(TryAgain.this,MainActivity.class);
            intent.putExtra("flag","ok2");
            startActivity(intent);
            finish();
        }
        if(unlock.equals("unlock4")){
            Intent intent=new Intent(TryAgain.this,MainActivity.class);
            intent.putExtra("flag","ok3");
            startActivity(intent);
            finish();
        }
        if(unlock.equals("unlock5")){
            Intent intent=new Intent(TryAgain.this,MainActivity.class);
            intent.putExtra("flag","ok4");
            startActivity(intent);
        }
        if(unlock.equals("unlock6")){
            Intent intent=new Intent(TryAgain.this,MainActivity.class);
            intent.putExtra("flag","ok5");
            startActivity(intent);
            finish();
        }
    }
    public void  Tryagain(){
        if(unlock.equals("unlock2")){
            Intent intent=new Intent(TryAgain.this,Questionlist.class);
            startActivity(intent);
            finish();
        }
        if(unlock.equals("unlock3")){
            Intent intent=new Intent(TryAgain.this,Main2Activity.class);
            startActivity(intent);
            finish();
        }
        if(unlock.equals("unlock4")){
            Intent intent=new Intent(TryAgain.this,Main3Activity.class);
            startActivity(intent);
            finish();
        }
        if(unlock.equals("unlock5")){
            Intent intent=new Intent(TryAgain.this,Main4Activity.class);
            startActivity(intent);
            finish();
        }
        if(unlock.equals("unlock6")){
            Intent intent=new Intent(TryAgain.this,Main5Activity.class);
            startActivity(intent);
        }
        if(unlock.equals("unlock7")){
            Intent intent=new Intent(TryAgain.this,Main6Activity.class);
            startActivity(intent);
            finish();
        }
    }
    public  void onBackPressed()
    {
        finish();
        super.onBackPressed();
    }

}
