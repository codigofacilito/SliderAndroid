package com.codigofacilito.mg.slider;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdaoter extends FragmentPagerAdapter {
    List<Fragment>list=new ArrayList<>();
    public MyViewPagerAdaoter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void addFragment(Fragment fragment){
        list.add(fragment);
    }
}
