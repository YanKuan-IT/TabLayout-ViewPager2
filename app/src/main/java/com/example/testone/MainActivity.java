package com.example.testone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;

import com.example.testone.myfragment.TabFragment;
import com.example.testone.myviewpager.MyPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Fragment> fragmentList = new ArrayList<>();

    ViewPager2 viewPager;

    MyPageAdapter myPageAdapter;

    TabLayout tab_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabFragment tabFragment1 = new TabFragment();
        TabFragment tabFragment2 = new TabFragment();
        TabFragment tabFragment3 = new TabFragment();

        fragmentList.add(tabFragment1);
        fragmentList.add(tabFragment2);
        fragmentList.add(tabFragment3);

        viewPager = findViewById(R.id.view_pager);
        myPageAdapter = new MyPageAdapter(this, fragmentList);
        viewPager.setAdapter(myPageAdapter);
        tab_layout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tab_layout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("TAB-"+position);
            }
        }).attach();
    }
}
