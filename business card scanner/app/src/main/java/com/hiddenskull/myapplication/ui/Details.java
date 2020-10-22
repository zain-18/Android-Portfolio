package com.hiddenskull.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hiddenskull.myapplication.R;

public class Details extends AppCompatActivity {
Button backBtn;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        backBtn=findViewById(R.id.back);
        textView=findViewById(R.id.privacylink);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/iconyz-privacy-policy/privacy-policy"));
                startActivity(intent);
            }
        });
    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent=new Intent(Details.this,MainActivity.class);
        startActivity(intent);
        this.finish();

    }
}
