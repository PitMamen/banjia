package com.max.jacentsao.banjia.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by JacenTsao on 2016/1/18.
 */
public class CategoryShowPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String bc;
    private int i = 0;

    public CategoryShowPagerAdapter(FragmentManager fm, List<Fragment> fragments, String bc) {
        super(fm);
        this.fragments = fragments;
        this.bc = bc;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        String sorts = null;
        bundle.putString("bc", bc);
        switch (position) {
            case 0:
                break;
            case 1:
                sorts = "sale2l";
                break;
            case 2:
                if (i / 2 == 0) {
                    sorts = "pl2h";
                } else {
                    sorts = "ph2l";
                }
                break;
            case 3:
                sorts = "new";
                break;

        }
        bundle.putString("sorts", sorts);
        fragments.get(position).setArguments(bundle);
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
