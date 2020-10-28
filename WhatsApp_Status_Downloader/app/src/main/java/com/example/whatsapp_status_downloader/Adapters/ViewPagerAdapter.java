package com.example.whatsapp_status_downloader.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatsapp_status_downloader.Fragments.WhatsAppImages;
import com.example.whatsapp_status_downloader.Fragments.WhatsAppVideos;

public class ViewPagerAdapter extends FragmentPagerAdapter {
   private WhatsAppImages whatsAppImages;
   private WhatsAppVideos whatsAppVideos;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        whatsAppImages=new WhatsAppImages();
        whatsAppVideos=new WhatsAppVideos();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return whatsAppImages;
        }
        else {
            return whatsAppVideos;
        }
    }
    @Override
    public CharSequence getPageTitle(int position){
        if (position==0){
            return "Images";
        }
        else{
            return "Video's";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
