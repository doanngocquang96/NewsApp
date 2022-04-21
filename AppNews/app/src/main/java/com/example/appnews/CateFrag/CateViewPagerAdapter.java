package com.example.appnews.CateFrag;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.appnews.Fragment.CateNewsFragment;
import com.example.appnews.Fragment.HotNewsFragment;

public class CateViewPagerAdapter extends FragmentStateAdapter {
    public CateViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Cate1Frag();
            case 1:
                return new Cate2Frag();
            case 2:
                return new Cate3Fragment();
            case 3:
                return new Cate4Fragment();
            case 4:
                return new Cate5Fragment();
            default:
                return new Cate1Frag();
        }
    }

    @Override
    public int getItemCount() {

        return 5;
    }
}
