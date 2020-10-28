package com.example.whatsapp_status_downloader.Utilis;

import android.os.Environment;

import java.io.File;

public class MyConstants {

    public static final File STATUS_DIRECTORY =
            new File(Environment.getExternalStorageDirectory() + File.separator +"WhatsApp/Media/.Statuses");

    public static final String APP_DIR_IMAGE=Environment.getExternalStorageDirectory()+ File.separator +"WhatsAppStatusImages";
    public static final String APP_DIR_VIDEO=Environment.getExternalStorageDirectory()+ File.separator +"WhatsAppStatusVideo";

    public static final int THUMBSIZE =1000;
    public static final int THUMBSIZES =100;

    public static final File Saved_Status_Directory= new File(Environment.getExternalStorageDirectory() + File.separator +"WhatsAppStatusProDir");


}
