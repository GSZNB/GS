package com.example.shoppingcar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.shoppingcar.fragment.AAFragment;
import com.example.shoppingcar.fragment.BBFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager Pager_View;
    private RadioButton rd;
    private RadioButton rdb;
    private RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Pager_View = (ViewPager) findViewById(R.id.Pager_View);
        rd = (RadioButton) findViewById(R.id.rd);
        rdb = (RadioButton) findViewById(R.id.rdb);
        radio = (RadioGroup) findViewById(R.id.radio);
        //创建Fragment
        AAFragment aFragment=new AAFragment();
        BBFragment bFragment=new BBFragment();
        //放入集合里
        final ArrayList<Fragment> list=new ArrayList<>();
        list.add(aFragment);
        list.add(bFragment);
        //ViewPager适配器
        Pager_View.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        Pager_View.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radio.check(radio.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rd:
                        Pager_View.setCurrentItem(0);
                        break;
                    case R.id.rdb:
                        Pager_View.setCurrentItem(1);
                        break;
                }
            }
        });
    }

}
