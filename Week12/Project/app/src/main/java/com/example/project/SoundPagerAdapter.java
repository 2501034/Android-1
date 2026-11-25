package com.example.project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SoundPagerAdapter extends FragmentStateAdapter {

    public SoundPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0 :
                fragment = new SoundPoolFragment();
                break;
            case 1 :
                fragment = new SoundPoolFragment();
                break;
            case 2 :
                fragment = new SoundPoolFragment();
                break;
            case 3 :
                fragment = new SoundPoolFragment();
                break;
            case 4 :
                fragment = new SoundPoolFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
