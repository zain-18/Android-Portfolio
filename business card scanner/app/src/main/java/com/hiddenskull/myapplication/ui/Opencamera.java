package com.hiddenskull.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.hiddenskull.myapplication.R;

public class Opencamera extends AppCompatActivity implements SurfaceHolder.Callback {
    Button btn_capture;
    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Camera.PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    Camera.PictureCallback jpegCallback;
    Camera.PictureCallback pictureCallback;
    Bitmap bmp,cbmp,bOutput;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opencamera);

        btn_capture=findViewById(R.id.capture);
        surfaceView=findViewById(R.id.surfaceview);
        surfaceView.getHolder().setFormat(PixelFormat.RGBA_8888);
        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        btn_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.takePicture(null,null,pictureCallback);


            }
        });

        pictureCallback=new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                 bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                recognizationText();
            }
        };
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        try {
            // open the camera
            camera = Camera.open();
        } catch (RuntimeException e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
        Camera.Parameters param;
        param = camera.getParameters();
        // modify parameter
        param.setJpegQuality(100);
        param.setPreviewSize(352, 288);
        param=camera.getParameters();
        param.setAutoWhiteBalanceLock(true);
        param.setAutoExposureLock(true);
        param.setPreviewFpsRange(300,500);
        param.setPreviewFrameRate(100);
        camera.setDisplayOrientation(90);
       // camera.setParameters(param);
        try {
            // The Surface has been created, now tell the camera where to draw
            // the preview.
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        camera.stopPreview();
        camera.release();
        camera = null;
    }



    public void recognizationText(){
        TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!txtRecognizer.isOperational())
        {
            // Shows if your Google Play services is not up to date or OCR is not supported for the device
            Toast.makeText(this, "Detector dependencies are not yet available", Toast.LENGTH_SHORT).show();

        }
        else
        {
            // Set the bitmap taken to the frame to perform OCR Operations.
            Frame frame = new Frame.Builder().setBitmap(bmp).build();
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
            Intent dataIntent=new Intent(Opencamera.this,Data.class);
            dataIntent.putExtra("Data",strBuilder.toString());
            dataIntent.putExtra("Image",bmp);
            startActivity(dataIntent);

        }
    }
}
