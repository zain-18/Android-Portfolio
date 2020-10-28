package com.example.whatsapp_status_downloader.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.whatsapp_status_downloader.R;

public class AboutActivity extends AppCompatActivity {
    ImageButton backimageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        backimageButton=findViewById(R.id.BackBtn);
        backimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutActivity.this,MainActivity.class));
                finish();

            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onResume() {

        super.onResume();
    }
}
