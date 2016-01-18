package com.max.jacentsao.banjia.activity;

import android.content.Intent;
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

/**
 * 登录界面
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    //返回上一个界面
    @ViewInject(R.id.ib_login_now_back)
    private ImageButton ibBack;

    @ViewInject(R.id.et_login_username)
    private EditText etUsername;

    @ViewInject(R.id.et_login_password)
    private EditText etPassword;

    @ViewInject(R.id.btn_login_now)
    private Button btnLogin;

    @ViewInject(R.id.btn_forget_password)
    private Button btnForgetPassword;

    @ViewInject(R.id.btn_to_register_activity)
    private Button btnRegister;

    private DbUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        dbUtils = GlobalApplication.getApp().getDbUtils();

    }


    /**
     * 结束当前页面，返回上一个界面
     *
     * @param view
     */
    @OnClick(R.id.ib_login_now_back)
    public void goBack(View view) {
        this.finish();
    }

    /**
     * 立即登录
     *
     * @param view
     */
    @OnClick(R.id.btn_login_now)
    public void loginNow(View view) {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Banjia banjia;
            try {
                banjia = dbUtils.findFirst(Selector.from(Banjia.class).where("username", "=", username));
                if (banjia != null) {
                    String passwordInTheDB = banjia.password;
                    if (passwordInTheDB == password) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Button button = (Button) findViewById(R.id.btn_login);
                        button.setText(username);
                        this.finish();
                    } else {
                        Toast.makeText(this, "登录失败，密码错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "登录失败，账户不存在", Toast.LENGTH_SHORT).show();
                }

            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 忘记密码，跳转到找回密码界面
     *
     * @param view
     */
    @OnClick(R.id.btn_forget_password)
    public void forgetPassword(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FindPasswordActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 注册账户
     *
     * @param view
     */
    @OnClick(R.id.btn_to_register_activity)
    public void toRegisterActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, RegisterUserActivity.class);
        startActivity(intent);
    }
}
