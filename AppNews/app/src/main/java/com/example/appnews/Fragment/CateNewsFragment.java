package com.example.appnews.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appnews.CateFrag.CateViewPagerAdapter;
import com.example.appnews.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CateNewsFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 cateviewPager2;
    private  CateNewsFragment cateNewsFragment;
    private View view;

    public CateNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_cate_news, container, false);
        tabLayout = view.findViewById(R.id.catetab_layout);
        cateviewPager2 = view.findViewById(R.id.CateViewPager2);

        cateviewPager2.setAdapter(new CateViewPagerAdapter(getActivity()));


        new TabLayoutMediator(tabLayout, cateviewPager2, true, false, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            switch (position){
                case 0:
                    tab.setText("Thế giới");
                    break;
                case 1:
                    tab.setText("Thời sự");
                    break;
                case 2:
                    tab.setText("Covid 19");
                    break;
                case 3:
                    tab.setText("Thể thao");
                    break;
                case 4:
                    tab.setText("Sức khỏe");
                    break;

            }
            }
        }).attach();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        return view;
    }
}