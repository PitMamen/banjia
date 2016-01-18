package com.max.jacentsao.banjia.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.application.GlobalApplication;
import com.max.jacentsao.banjia.db.Banjia;
import com.max.jacentsao.banjia.utils.FormatVerifyUtil;
import com.max.jacentsao.banjia.utils.SecureCodeUtils;

import cn.smssdk.SMSSDK;

/**
 * 注册页面
 */
@ContentView(R.layout.activity_register_user)
public class RegisterUserActivity extends AppCompatActivity {

    //返回上一个界面
    @ViewInject(R.id.ib_register_user_back)
    private ImageButton ibBack;

    @ViewInject(R.id.et_register_user_username)
    private EditText etUsername;

    @ViewInject(R.id.et_register_user_code)
    private EditText etCode;

    @ViewInject(R.id.et_register_user_set_password)
    private EditText etPassword;

    @ViewInject(R.id.et_register_recommend_user)
    private EditText etRecommendUser;

    @ViewInject(R.id.btn_register_user_submit)
    private Button btnSubmit;

    @ViewInject(R.id.btn_register_user_get_code)
    private Button btnGetCode;

    private String phoneNumber;

    private DbUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        dbUtils = GlobalApplication.getApp().getDbUtils();
    }

    /**
     * 立即注册
     *
     * @param view
     */
    @OnClick(R.id.btn_register_user_submit)
    public void commit(View view) {
        phoneNumber = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String authCode = etCode.getText().toString().trim();

        if (TextUtils.isEmpty(authCode)) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            SMSSDK.submitVerificationCode("86", phoneNumber, authCode);
            if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
            } else {
                Banjia banjia;
                try {
                    banjia = dbUtils.findFirst(Selector.from(Banjia.class).where("username", "=", phoneNumber));
                    if (banjia != null) {
                        Toast.makeText(this, phoneNumber + "已经存在，请换一个用户名", Toast.LENGTH_SHORT).show();
                    } else {
                        banjia = new Banjia();
                        banjia.username = phoneNumber;
                        banjia.password = password;
                        banjia.bonusPoint = 100;
                        dbUtils.save(banjia);
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                        this.finish();
                    }

                } catch (DbException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    @OnClick(R.id.ib_register_user_back)
    public void goBack(View view) {
        this.finish();
    }


    /**
     * 注册--获取验证码
     *
     * @param view
     */
    @OnClick(R.id.btn_register_user_get_code)
    public void getAuthCode(View view) {

        SecureCodeUtils secureCodeUtils = new SecureCodeUtils();
        secureCodeUtils.getCode(this);
        phoneNumber = etUsername.getText().toString().trim();
        if (!FormatVerifyUtil.isMobileNO(phoneNumber)) {
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
        } else {
            //通过电话号码获得验证码
            SMSSDK.getVerificationCode("86", phoneNumber);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
