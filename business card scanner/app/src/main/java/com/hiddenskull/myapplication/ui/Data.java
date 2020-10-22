package com.hiddenskull.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.hiddenskull.myapplication.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    EditText phoneEditText,emailEditText,weblinkEditText,nameEditText;
    Button addtoContactBtn,Nav;
    ImageView imageView;
public String receiveData;
    Pattern emailPattern,webLinkPattern;
    Matcher emailMatcher,webLinkMatcher;
    String mobileNumber,Name,Email,Website,Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        phoneEditText=findViewById(R.id.textviewPhone);
        emailEditText=findViewById(R.id.textviewEmail);
        weblinkEditText=findViewById(R.id.textviewWebLink);
        nameEditText=findViewById(R.id.textviewName);
        addtoContactBtn=findViewById(R.id.btnAddcontacts);
        Nav=findViewById(R.id.navdetails);
        imageView=findViewById(R.id.imageView);
        final Intent intent=getIntent();
        receiveData=intent.getStringExtra("Data");

        Intent intent1 = getIntent();
        Bitmap bitmap = (Bitmap) intent1.getParcelableExtra("Image");
        imageView.setImageBitmap(bitmap);
        Nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(Data.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.aboutus,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(Data.this);
                popupMenu.show();
            }
        });


        emailPattern=Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
        emailMatcher=emailPattern.matcher(receiveData);
        while (emailMatcher.find()){
            emailEditText.setText(emailMatcher.group());
        }
        webLinkPattern=Pattern.compile("\\(?\\b(https?://|www[.]|ftp://)[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]");
        webLinkMatcher=webLinkPattern.matcher(receiveData);
        while (webLinkMatcher.find()){
            weblinkEditText.setText(webLinkMatcher.group());
        }

        mobileNumber=PhoneNumberUtils.stripSeparators(receiveData);
        phoneEditText.setText(mobileNumber);
        Name=nameEditText.getText().toString();
        Phone=phoneEditText.getText().toString();
        Email=emailEditText.getText().toString();
        Website=weblinkEditText.getText().toString();
          if(TextUtils.isEmpty(Phone)){
              Dialog dialog=new Dialog(this);
              dialog.setContentView(R.layout.scanagain);
              dialog.show();
              Button scanagainBtn=dialog.findViewById(R.id.scanagainbtn);
              scanagainBtn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent intent2=new Intent(Data.this,MainActivity.class);
                      startActivity(intent2);
                  }
              });
          }
          if(TextUtils.isEmpty(receiveData)){
              Dialog dialog=new Dialog(this);
              dialog.setContentView(R.layout.scanagain);
              dialog.show();
              Button scanagainBtn=dialog.findViewById(R.id.scanagainbtn);
              scanagainBtn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent intent2=new Intent(Data.this,MainActivity.class);
                      startActivity(intent2);
                  }
              });
          }

        addtoContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME,nameEditText.getText().toString());
                intent.putExtra(ContactsContract.Intents.Insert.PHONE,phoneEditText.getText() );
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL,emailEditText.getText().toString());
                intent.putExtra(ContactsContract.Intents.Insert.COMPANY,weblinkEditText.getText().toString());
                startActivity(intent);

            }

        });





    }
    public void onBackPressed(){
        Intent intent=new Intent(Data.this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.about_us:
                Intent intent=new Intent(Data.this,Details.class);
                startActivity(intent);
                return true;

        }
        return false;
    }
}
