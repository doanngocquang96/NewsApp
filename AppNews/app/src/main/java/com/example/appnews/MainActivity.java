package com.example.appnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appnews.Fragment.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private long pressedTime;
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.btm_navi);

        ViewPagerAdapter view = new ViewPagerAdapter(this);
        viewPager2.setAdapter(view);

        viewPager2.setPageTransformer(new DepthPageTransformer());

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.HotNews).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.NewNews).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.CateNews).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.DanhMuc).setChecked(true);
                        break;
                }
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.HotNews:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.NewNews:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.CateNews:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.DanhMuc:
                        viewPager2.setCurrentItem(3);
                        break;


                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Bấm quay về một lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}