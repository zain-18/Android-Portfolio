package com.hiddenskull.zain_.youtubeplayer.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.hiddenskull.zain_.youtubeplayer.R;

public class Details extends AppCompatActivity {
Button back1;
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        back1=findViewById(R.id.backbtn);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
        WebViewClient WebClient = new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        };
        webView=findViewById(R.id.web);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.setWebViewClient(WebClient);
        webView.loadUrl("https://sites.google.com/view/hidden-skull/privacy-policy");

    }
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }
}
