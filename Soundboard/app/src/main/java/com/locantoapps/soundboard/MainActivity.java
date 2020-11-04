package com.locantoapps.soundboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.Button;

/*import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinSdk;*/
import com.flurry.android.FlurryAgent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,mute,next;
    MediaPlayer player1;
   /* private static final int PERMISSION_REQUEST_CODE = 1;
    private String fNmae = "bruhsoundeffect.mp3";
    private String fPAth = "android.resource://com.hiddenskull.soundboard/raw/bruhsoundeffect";*/

    //private AppLovinIncentivizedInterstitial myIncent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "XXJKBC8X2KRRZ7D45JV9");
        /*--------------------*/




       /* buzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (Build.VERSION.SDK_INT >= 23) {
                    if (checkPermission()) {
                        if (Settings.System.canWrite(MainActivity.this))
                        {
                            setRingtone();
                        }
                        else
                        {
                            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS)
                                    .setData(Uri.parse("package:" + getPackageName()))
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }


                        Log.e("value", "Permission already Granted, Now you can save image.");
                    } else {
                        requestPermission();
                    }
                } else {
                    setRingtone();
                    Log.e("value", "Not required for requesting runtime permission");
                }
                player = MediaPlayer.create(MainActivity.this, R.raw.bruhsoundeffect);
                player.start();
            }
        });
*/
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        /*--------------------*/
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        /*--------------------*/
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        /*--------------------*/
        mute = findViewById(R.id.mute);
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();

            }
        });

        /*--------nextActivity+Adds-----------*/
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (myIncent.isAdReadyToDisplay()) {
                    // A rewarded video is available.  Call the show method with the listeners you want to use.
                    // We will use the display listener to preload the next rewarded video when this one finishes.

                    myIncent.show(MainActivity.this, null, null, new AppLovinAdDisplayListener() {
                        @Override
                        public void adDisplayed(AppLovinAd appLovinAd) {


                        }

                        @Override
                        public void adHidden(AppLovinAd appLovinAd) {
                            // A rewarded video was closed.  Preload the next video now.  We won't use a load listener.
                            myIncent.preload(null);
                        }
                    });
                } else {
                    Intent  intent=new Intent(MainActivity.this,Page2.class);
                    startActivity(intent);

                    // No ad is currently available.  Perform failover logic...
                }*/
                Intent  intent=new Intent(MainActivity.this,Page2.class);
                startActivity(intent);

            }
        });
        /*---------end of next Activity----------*/
        /*----------Initailization Applovin---------*/
        /*AppLovinSdk.initializeSdk(this);
        myIncent = AppLovinIncentivizedInterstitial.create(this);
        myIncent.preload(null);*/
        /*--------- End of Initailization Applovin----------*/


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }
                player1 = MediaPlayer.create(MainActivity.this, R.raw.adults);
                player1.start();

                break;
            case R.id.button2:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(MainActivity.this, R.raw.afregar);
                player1.start();

                break;
            case R.id.button3:

                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(MainActivity.this, R.raw.airhorn);
                player1.start();

                break;
            case R.id.button4:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(MainActivity.this, R.raw.akko);
                player1.start();

                break;
            case R.id.button5:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(MainActivity.this, R.raw.aku_aku);
                player1.start();

                break;
            case R.id.button6:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(MainActivity.this, R.raw.alarmacoche);
                player1.start();
                break;
            case R.id.button7:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(MainActivity.this, R.raw.alertasubnormal);
                player1.start();
                break;
            case R.id.button8:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(MainActivity.this, R.raw.android_remix);
                player1.start();
                break;
            case R.id.button9:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }
                player1 = MediaPlayer.create(MainActivity.this, R.raw.anenemy);
                player1.start();
                break;


        }
    }

    private void stopPlaying() {
        if (player1 != null && player1.isPlaying()) {
            player1.stop();
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onPause() {
        if(player1!=null){
            player1.stop();
            player1.release();
        }
        super.onPause();
    }


    /*private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }*/

    /*@Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can save image .");
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (Settings.System.canWrite(MainActivity.this)) {
                            setRingtone();
                        } else {
                            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS)
                                    .setData(Uri.parse("package:" + getPackageName()))
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                } else {
                    Log.e("value", "Permission Denied, You cannot save image.");
                }
                break;
        }
    }*/


   /* private void setRingtone() {
        AssetFileDescriptor openAssetFileDescriptor;
        ((AudioManager) getSystemService(AUDIO_SERVICE)).setRingerMode(2);
        File file = new File(Environment.getExternalStorageDirectory() + "/Ringtones", this.fNmae);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri parse = Uri.parse(this.fPAth);
        ContentResolver contentResolver = getContentResolver();
        try {
            openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(parse, "r");
        } catch (FileNotFoundException e2) {
            openAssetFileDescriptor = null;
        }
        try {
            byte[] bArr = new byte[1024];
            FileInputStream createInputStream = openAssetFileDescriptor.createInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (int read = createInputStream.read(bArr); read != -1; read = createInputStream.read(bArr)) {
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_data", file.getAbsolutePath());
        contentValues.put("title", "nkDroid ringtone");
        contentValues.put("mime_type", "audio/mp3");
        contentValues.put("_size", Long.valueOf(file.length()));
        contentValues.put("artist", Integer.valueOf(R.string.app_name));
        contentValues.put("is_ringtone", Boolean.valueOf(true));
        contentValues.put("is_notification", Boolean.valueOf(false));
        contentValues.put("is_alarm", Boolean.valueOf(false));
        contentValues.put("is_music", Boolean.valueOf(false));
        try {
            Toast.makeText(this, new StringBuilder().append("Ringtone set successfully"), Toast.LENGTH_LONG).show();
            RingtoneManager.setActualDefaultRingtoneUri(getBaseContext(), 1, contentResolver.insert(MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath()), contentValues));
        } catch (Throwable th) {
            Toast.makeText(this, new StringBuilder().append("Ringtone feature is not working"), Toast.LENGTH_LONG).show();
        }
    }*/

}

