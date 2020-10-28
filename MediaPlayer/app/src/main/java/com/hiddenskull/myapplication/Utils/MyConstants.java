package com.hiddenskull.myapplication.Utils;

import android.os.Environment;

import java.io.File;

public class MyConstants {

    public static final File STATUS_DIRECTORY =
            new File(Environment.getExternalStorageDirectory() + File.separator +"WhatsApp/Media/.Statuses");

    public static final String APP_DIR=Environment.getExternalStorageDirectory()+ File.separator +"WhatsAppStatusProDir";

    public static final int THUMBSIZE =100;
    public static final int THUMBSIZES =100;

    public static final File Saved_Status_Directory= new File(Environment.getExternalStorageDirectory() + File.separator +"WhatsAppStatusProDir");


}
