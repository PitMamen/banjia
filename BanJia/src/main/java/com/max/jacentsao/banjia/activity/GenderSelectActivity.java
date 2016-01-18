package com.max.jacentsao.banjia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.max.jacentsao.banjia.R;

/**
 * 性别选择界面
 */
@ContentView(R.layout.activity_gender_select)
public class GenderSelectActivity extends AppCompatActivity {
    public static final int RESULT_CODE = 1112;

    @ViewInject(R.id.rg_gender_select)
    private RadioGroup rgGender;

    private int gender = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        //性别选择监听
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_gender_select_boy:
                        gender = 1;
                        break;
                    case R.id.rb_gender_select_girl:
                        gender = 0;
                        break;
                }
            }
        });
    }

    /**
     * 确认性别，返回上一级
     * @param view
     */
    @OnClick(R.id.btn_gender_select_confirm)
    public void confirmGender(View view) {
        Intent intent = getIntent();
        intent.putExtra("gender", gender);
        setResult(RESULT_CODE, intent);
        this.finish();
    }
}
