package com.example.appnews.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HotNewsFragment();
            case 1:
                return new NewNewsFragment();
            case 2:
                return new CateNewsFragment();
            case 3:
                return new DanhMucFragment();
            default:
                return new HotNewsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
