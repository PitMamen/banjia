package com.max.jacentsao.banjia.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.adapter.WelcomePagerAdapter;

/**
 * 欢迎页
 */
@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends AppCompatActivity {

    @ViewInject(R.id.iv_welcome)
    private ImageView mIvWelcome;

    @ViewInject(R.id.vp_welcome)
    private ViewPager mVpWelcome;

    private boolean isFirst = true;

    // viewpeger适配器
    private WelcomePagerAdapter mAdapter;

    // 数据源图片id数组
    private int[] mImageIds = {R.mipmap.z1, R.mipmap.z2, R.mipmap.z3, R.mipmap.z4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewUtils.inject(this);

        initViews();

        initDatas();

    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        SharedPreferences sp = getSharedPreferences("banjia", MODE_PRIVATE);
        isFirst = sp.getBoolean("isFirst", true);
    }

    /**
     * 初始化视图
     */
    private void initViews() {

        mHandler.sendEmptyMessageDelayed(1, 2000);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (isFirst) {
                mIvWelcome.setVisibility(View.GONE);
                mVpWelcome.setVisibility(ViewPager.VISIBLE);
                mAdapter = new WelcomePagerAdapter(mImageIds,WelcomeActivity.this);
                mVpWelcome.setAdapter(mAdapter);
            } else {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(1);
    }
}
