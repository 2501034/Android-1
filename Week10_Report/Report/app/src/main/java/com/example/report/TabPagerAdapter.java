package com.example.report;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return TabButtonFragment.newInstance("첫번째 TAB", "#8BC34A");
            case 1:
                return TabButtonFragment.newInstance("두번째 TAB", "#FFCC99");
            case 2:
                return TabButtonFragment.newInstance("세번째 TAB", "#2196F3");
            case 3:
            default:
                return new SettingListFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
