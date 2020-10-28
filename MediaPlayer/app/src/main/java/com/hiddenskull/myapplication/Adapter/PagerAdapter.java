package com.hiddenskull.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hiddenskull.myapplication.Fragements.DownloadedStatus;
import com.hiddenskull.myapplication.Fragements.WhatsAppStatusFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    //private PhoneVideoFragment phoneVideoFragment;
    private WhatsAppStatusFragment whatsAppStatusFragment;
    private DownloadedStatus downloadedStatus;

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        //phoneVideoFragment=new PhoneVideoFragment();
        whatsAppStatusFragment=new WhatsAppStatusFragment();
        downloadedStatus=new DownloadedStatus();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       /* if(position==0){
            return  phoneVideoFragment;
        }*/
         if(position==0){
            return whatsAppStatusFragment;
        }
        else{
            return downloadedStatus;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Recent";
        }

        else {
            return "Saved";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
