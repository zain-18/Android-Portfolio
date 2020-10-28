package com.example.whatsapp_status_downloader.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.whatsapp_status_downloader.R;

public class ViewImage extends AppCompatActivity {
ImageView imageView;
ImageButton backBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        imageView=findViewById(R.id.viewImage);
        backBackBtn=findViewById(R.id.BackBtn);
        Intent intent=getIntent();
        Glide.with(this).load("file://"+intent.getStringExtra("Image"))
                .skipMemoryCache(false)
                .into(imageView);
        backBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewImage.this,MainActivity.class));
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
