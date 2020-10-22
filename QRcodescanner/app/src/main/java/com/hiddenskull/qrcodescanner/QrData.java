package com.hiddenskull.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class QrData extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    TextView textView;
Button copy,share,search,speak;
public static String receiveData,escapedQuery;
ImageView imageView;
Button navbutton;
TextView scanAgain;
 public  static Bitmap bitmap,bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_data);
        cameraPermission();
        textView=findViewById(R.id.data);
        copy=findViewById(R.id.copy);
        share=findViewById(R.id.share);
        scanAgain=findViewById(R.id.ScanAgain);
        search=findViewById(R.id.search);
        imageView=findViewById(R.id.Qrimage);
        navbutton=findViewById(R.id.navbtn);
        navbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(QrData.this,view);
                popupMenu.setOnMenuItemClickListener(QrData.this);
                popupMenu.inflate(R.menu.abouts);
                popupMenu.show();
            }
        });

        final Intent intent=getIntent();
        receiveData=intent.getStringExtra("Data");
        textView.setText(receiveData);


        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText("label",receiveData);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(QrData.this, "Added to Clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        scanAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QrData.this,MainActivity.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    escapedQuery=URLEncoder.encode(receiveData,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Intent intent2=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/#q=" +escapedQuery));
                startActivity(intent2);
            }
        });
        try {
            bitmap = encodeAsBitmap(receiveData);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        if(bitmap!=null) {
            imageView.setImageBitmap(bitmap);
        }else {

        }


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent1=new Intent(Intent.ACTION_SEND);
                intent1.setType("text/plain");
                intent1.putExtra(Intent.EXTRA_TEXT,receiveData);
                startActivity(Intent.createChooser(intent1,"Choose an App"));

            }
        });

    }
    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, 250, 250, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 250, 0, 0, w, h);
        return bitmap;
    }




    private void cameraPermission() {
        String permission = Manifest.permission.CAMERA;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }



    public void onBackPressed(){
        super.onBackPressed();
        moveTaskToBack(true);
        finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.about:
                Intent intent=new Intent(QrData.this,Aboutus.class);
                startActivity(intent);
                finish();
                return true;
        }
        return false;
    }
}
