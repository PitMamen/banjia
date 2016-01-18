package com.max.jacentsao.banjia.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.max.jacentsao.banjia.R;
@ContentView(R.layout.activity_about_ban_jia)
public class AboutBanJiaActivity extends AppCompatActivity {

    //返回上一个界面
    @ViewInject(R.id.ib_about_banjia_back)
    private ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
    }

    @OnClick(R.id.ib_about_banjia_back)
    public void goBack(View view){
        this.finish();
    }
}
