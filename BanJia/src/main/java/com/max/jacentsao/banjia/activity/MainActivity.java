package com.max.jacentsao.banjia.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.fragment.FragmentBrandSale;
import com.max.jacentsao.banjia.fragment.FragmentHome;
import com.max.jacentsao.banjia.fragment.FragmentMine;
import com.max.jacentsao.banjia.fragment.FragmentSearch;
import com.max.jacentsao.banjia.module.CategorySearchEvent;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 主Activity
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @ViewInject(R.id.rg_activity)
    private RadioGroup radioGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        ViewUtils.inject(this);
        initViews();
    }

    private void initViews() {
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.getChildAt(0).performClick();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                fragmentManager(R.id.fl_fragment, FragmentHome.class);
                break;
            case R.id.rb_brand:
                fragmentManager(R.id.fl_fragment, FragmentBrandSale.class);
                break;
            case R.id.rb_category:
                fragmentManager(R.id.fl_fragment, FragmentSearch.class);
                break;
            case R.id.rb_mine:
                fragmentManager(R.id.fl_fragment, FragmentMine.class);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onEventMainThread(CategorySearchEvent event) {
        radioGroup.getChildAt(2).performClick();
    }
}
