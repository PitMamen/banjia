package com.max.jacentsao.banjia.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.utils.FormatVerifyUtil;
import com.max.jacentsao.banjia.utils.SecureCodeUtils;

import cn.smssdk.SMSSDK;

@ContentView(R.layout.activity_find_password)
public class FindPasswordActivity extends AppCompatActivity {
    //返回上一个界面
    @ViewInject(R.id.ib_find_password_back)
    private ImageButton ibBack;

    @ViewInject(R.id.et_find_password_username)
    private EditText etUsername;

    @ViewInject(R.id.et_find_password_code)
    private EditText etCode;

    @ViewInject(R.id.et_find_password_set_password)
    private EditText etPassword;

    @ViewInject(R.id.btn_find_password_submit)
    private Button btnSubmit;

    @ViewInject(R.id.btn_find_password_get_code)
    private Button btnGetCode;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
    }

    /**
     * 结束当前页面，返回上一个界面
     *
     * @param view
     */
    @OnClick(R.id.ib_find_password_back)
    public void goBack(View view) {
        this.finish();
    }

    /**
     * 提交新的密码
     *
     * @param view
     */
    @OnClick(R.id.btn_find_password_submit)
    public void commit(View view) {
        phoneNumber = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String authCode = etCode.getText().toString().trim();

        if (TextUtils.isEmpty(authCode)) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
        }else {
            SMSSDK.submitVerificationCode("86", phoneNumber, authCode);
            if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
            }else {
                //TODO 将找回成功的数据添加到本地数据库
            }
        }


    }

    /**
     * 找回密码--获取验证码
     *
     * @param view
     */
    @OnClick(R.id.btn_find_password_get_code)
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
