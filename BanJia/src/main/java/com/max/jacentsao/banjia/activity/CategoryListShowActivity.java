package com.max.jacentsao.banjia.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
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

    @ViewInject(R.id.btn_category_show_category)
    private Button btnCategoery;

    @ViewInject(R.id.btn_category_show_filtrate)
    private Button btnCategoeryFilter;

    private CategoryShowPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] allCategory;
    private String[] subFemaleCloth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
//        requestAlertWindowPermission();
        initViews();
    }

    private void initViews() {
        allCategory = getResources().getStringArray(R.array.all_category);
        subFemaleCloth = getResources().getStringArray(R.array.sub_female_cloth);
        Intent intent = getIntent();
        String bc = intent.getStringExtra("bc");
        fragments.add(new FragmentCategory());
        fragments.add(new FragmentCategory());
        fragments.add(new FragmentCategory());
        fragments.add(new FragmentCategory());
        adapter = new CategoryShowPagerAdapter(getSupportFragmentManager(), fragments, bc);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }

//    /**
//     * 点击选择类别
//     * @param view
//     */
//    @OnClick(R.id.tv_category_show_category)
//    public void selectCategory(View view) {
//
//    }

    /**
     * 点击选择过滤器
     * @param view
     */
    @OnClick(R.id.btn_category_show_filtrate)
    public void filter(View view){

    }

    @OnRadioGroupCheckedChange(R.id.rg_category_show)
    public void choseOrderCategory(RadioGroup radioGroup, int checkedID) {
        switch (checkedID) {
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

    private static final int REQUEST_CODE = 1;

    private void requestAlertWindowPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_CODE);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (Settings.canDrawOverlays(this)) {
//                }
//            }
//        }
//    }
}
