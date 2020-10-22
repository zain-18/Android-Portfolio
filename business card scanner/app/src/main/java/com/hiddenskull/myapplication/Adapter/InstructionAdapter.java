package com.hiddenskull.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hiddenskull.myapplication.Fragments.BlankFragment;
import com.hiddenskull.myapplication.Fragments.SecondFragment;
import com.hiddenskull.myapplication.Fragments.ThirdFragment;

public class InstructionAdapter extends FragmentPagerAdapter {
    public InstructionAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BlankFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
