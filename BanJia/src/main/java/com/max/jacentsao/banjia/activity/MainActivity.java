package com.max.jacentsao.banjia.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.fragment.FragmentBrandSale;
import com.max.jacentsao.banjia.fragment.FragmentHome;
import com.max.jacentsao.banjia.fragment.FragmentMine;
import com.max.jacentsao.banjia.fragment.FragmentSearch;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

//    @ViewInject(R.id.vp_main)
//    private ViewPager viewPager;

    @ViewInject(R.id.rg_activity)
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        initViews();
    }

    private void initViews() {
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.getChildAt(0).performClick();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment,new FragmentHome()).commit();
                break;
            case R.id.rb_brand:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment,new FragmentBrandSale()).commit();
                break;
            case R.id.rb_category:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment,new FragmentSearch()).commit();
                break;
            case R.id.rb_mine:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment,new FragmentMine()).commit();
                break;
        }
    }
}
