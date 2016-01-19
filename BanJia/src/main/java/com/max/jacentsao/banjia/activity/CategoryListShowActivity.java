package com.max.jacentsao.banjia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.adapter.CategoryShowPagerAdapter;
import com.max.jacentsao.banjia.fragment.FragmentCategory;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_category_list_show)
public class CategoryListShowActivity extends BaseActivity {
    @ViewInject(R.id.rg_category_show)
    private RadioGroup radioGroup;

    @ViewInject(R.id.vp_category_show)
    private ViewPager viewPager;
    private CategoryShowPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        initViews();
    }

    private void initViews() {
        Intent intent = getIntent();
        String bc = intent.getStringExtra("bc");
        fragments.add(new FragmentCategory());
        fragments.add(new FragmentCategory());
        fragments.add(new FragmentCategory());
        fragments.add(new FragmentCategory());
        adapter = new CategoryShowPagerAdapter(getSupportFragmentManager(),fragments,bc);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }

    @OnRadioGroupCheckedChange(R.id.rg_category_show)
    public void choseOrderCategory(RadioGroup radioGroup,int checkedID){
        switch (checkedID){
            case R.id.rb_category_show_default:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb_category_show_sales_volume:
                viewPager.setCurrentItem(1);
                break;
            case R.id.rb_category_show_price:
                viewPager.setCurrentItem(2);
                break;
            case R.id.rb_category_show_latest:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
