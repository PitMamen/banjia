package com.max.jacentsao.banjia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.customview.CustomLinearLayout;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

/**
 * 签到领积分界面
 * @author JacenTsao
 *
 */
@ContentView(R.layout.activity_sign_in)
public class SignInActivity extends AppCompatActivity {

    //返回上一个界面
    @ViewInject(R.id.ib_sign_in_back)
    private ImageButton ibBack;

    //积分换话费
    @ViewInject(R.id.btn_mashangdihuafei)
    private Button btnExchangeBonus;

    //签到日历
    @ViewInject(R.id.calendarView)
    private MaterialCalendarView calendarView;

    //签到状态表述
    @ViewInject(R.id.tv_sign_in_state_explain)
    private TextView textViewSignStateExplain;

    //点击签到
    @ViewInject(R.id.btn_sign_in)
    private Button btnSignIn;

    //积分说明
    @ViewInject(R.id.btn_bonus_point_explain)
    private Button btnBonusExplain;

    //邀请好友注册
    @ViewInject(R.id.ll_invite_friends)
    private CustomLinearLayout inviteFriends;

    //首次登录
    @ViewInject(R.id.ll_invite_friends)
    private CustomLinearLayout firstLogin;

    //周五前到双倍积分
    @ViewInject(R.id.ll_invite_friends)
    private CustomLinearLayout fridayBonus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
    }

    /**
     *
     * 结束并返回上一个界面
     * @param view
     */
    @OnClick(R.id.ib_sign_in_back)
    public void goBack(View view){
        this.finish();
    }

    /**
     * 积分换话费
     * @param view
     */
    @OnClick(R.id.btn_mashangdihuafei)
    public void exchangeTelephoneExpense(View view){
        startActivity(new Intent(this,BonusMallActivity.class));
    }

    /**
     * 积分说明
     * @param view
     */
    @OnClick(R.id.btn_bonus_point_explain)
    public void bonusExplain(View view){
        startActivity(new Intent(this,BonusExplainActivity.class));
    }

    /**
     * 邀请好友注册
     * @param view
     */
    @OnClick(R.id.ll_invite_friends)
    public void inviteFriends(View view){
//        startActivity(new Intent(this,RecommendFriendsBonus.class));
    }

}
