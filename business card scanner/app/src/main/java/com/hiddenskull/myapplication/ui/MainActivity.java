package com.hiddenskull.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.hiddenskull.myapplication.R;

public class MainActivity extends AppCompatActivity {
Button button1,button2;
TextView textView;
Bitmap bitmap;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dispatchTakePictureIntent();

      /*  button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // To get bitmap from resource folder of the application.
               // bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.sample);
// Starting Text Recognizer
                TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                if (!txtRecognizer.isOperational())
                {
                    // Shows if your Google Play services is not up to date or OCR is not supported for the device
                    textView.setText("Detector dependencies are not yet available");
                }
                else
                {
                    // Set the bitmap taken to the frame to perform OCR Operations.
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray items = txtRecognizer.detect(frame);
                    StringBuilder strBuilder = new StringBuilder();
                    for (int i = 0; i < items.size(); i++)
                    {
                        TextBlock item = (TextBlock)items.valueAt(i);
                        strBuilder.append(item.getValue());
                        strBuilder.append("\n");
                        // The following Process is used to show how to use lines & elements as well
                        for (i = 0; i < items.size(); i++) {
                            item = (TextBlock) items.valueAt(i);
                            strBuilder.append(item.getValue());
                            strBuilder.append("\n");
                            for (Text line : item.getComponents()) {
                                //extract scanned text lines here
                                Log.v("lines", line.getValue());
                                for (Text element : line.getComponents()) {
                                    //extract scanned text words here
                                    Log.v("element", element.getValue());
                                }
                            }
                        }
                    }
                   // textView.setText(strBuilder.toString());
                    Intent intent=new Intent(MainActivity.this,Data.class);
                    intent.putExtra("Data",strBuilder.toString());
                    startActivity(intent);
                }
            }
        });*/
       /* button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        })*/;

    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");
            recognizationText();

        }
    }
    public void recognizationText(){
        TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!txtRecognizer.isOperational())
        {
            // Shows if your Google Play services is not up to date or OCR is not supported for the device
            Toast.makeText(this, "Detector dependencies are not yet available", Toast.LENGTH_SHORT).show();
           // textView.setText("Detector dependencies are not yet available");
        }
        else
        {
            // Set the bitmap taken to the frame to perform OCR Operations.
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray items = txtRecognizer.detect(frame);
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < items.size(); i++)
            {
                TextBlock item = (TextBlock)items.valueAt(i);
                strBuilder.append(item.getValue());
                strBuilder.append("\n");
                // The following Process is used to show how to use lines & elements as well
                for (i = 0; i < items.size(); i++) {
                    item = (TextBlock) items.valueAt(i);
                    strBuilder.append(item.getValue());
                    strBuilder.append("\n");
                    for (Text line : item.getComponents()) {
                        //extract scanned text lines here
                        Log.v("lines", line.getValue());
                        for (Text element : line.getComponents()) {
                            //extract scanned text words here
                            Log.v("element", element.getValue());
                        }
                    }
                }
            }
            // textView.setText(strBuilder.toString());
            Intent dataIntent=new Intent(MainActivity.this,Data.class);
            dataIntent.putExtra("Data",strBuilder.toString());
            dataIntent.putExtra("Image",bitmap);
            startActivity(dataIntent);

        }
    }
    public void onBackPressed(){
        finishAffinity();
    }

}
