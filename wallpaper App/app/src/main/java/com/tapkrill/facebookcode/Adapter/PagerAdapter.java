package com.tapkrill.facebookcode.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tapkrill.facebookcode.Fragments.Categories;
import com.tapkrill.facebookcode.Fragments.New;
import com.tapkrill.facebookcode.Fragments.Popular;

public class PagerAdapter extends FragmentPagerAdapter {

    Categories categories;
    New news;
    Popular popular;

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

        categories=new Categories();
        news=new New();
        popular=new Popular();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if(position==0){
            return categories;
        }
        else if(position==1){
            return popular;
        }
        else {
            return news;
        }
    }

    @Override
    public CharSequence getPageTitle(int position){

        if(position==0){
            return "Categories";
        }
        else if(position==1){
            return "popular";
        }
        else {
            return "New";
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
