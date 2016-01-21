package com.max.jacentsao.banjia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.activity.BonusMallActivity;
import com.max.jacentsao.banjia.activity.GenderSelectActivity;
import com.max.jacentsao.banjia.activity.LoginActivity;
import com.max.jacentsao.banjia.activity.SignInActivity;
import com.max.jacentsao.banjia.activity.SystemSettingActivty;
import com.max.jacentsao.banjia.customview.CustomLinearLayout;

/**
 * 我的 界面
 * Created by JacenTsao on 2016/1/11.
 */
public class FragmentMine extends BaseFragment {
    private static final int REQUEST_CODE = 1111;
    //设置按钮
    @ViewInject(R.id.iv_setting)
    private ImageView imageViewSetting;

    //性别设置
    @ViewInject(R.id.btn_gender_select)
    private Button btnGenderSet;

    //立即登录
    @ViewInject(R.id.btn_login)
    private Button btnLogin;

    //淘宝订单
    @ViewInject(R.id.ll_taobao_dingdan)
    private CustomLinearLayout taobao;

    //淘宝物流信息
    @ViewInject(R.id.ll_taobao_wuliu)
    private CustomLinearLayout wuliu;

    //淘宝购物车
    @ViewInject(R.id.ll_taobao_gouwuche)
    private CustomLinearLayout gouwuche;

    //我的收藏
    @ViewInject(R.id.ll_favorite)
    private CustomLinearLayout favorite;

    //签到领积分
    @ViewInject(R.id.ll_qiandao)
    private CustomLinearLayout qiandao;

    //我的积分
    @ViewInject(R.id.ll_wode)
    private CustomLinearLayout wode;

    //积分商城
    @ViewInject(R.id.ll_jifen_mall)
    private CustomLinearLayout jifen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ViewUtils.inject(this, view);
        return view;
    }

    @OnClick(R.id.iv_setting)
    public void setting(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), SystemSettingActivty.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @OnClick(R.id.btn_login)
    public void login(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    /**
     * 设置性别参数
     *
     * @param view
     */
    @OnClick(R.id.btn_gender_select)
    public void setGender(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), GenderSelectActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode && GenderSelectActivity.RESULT_CODE == resultCode) {
            int gender = data.getIntExtra("gender", 0);
            if (gender == 0) {
                btnGenderSet.setText("美女");
            } else {
                btnGenderSet.setText("帅哥");
            }
        }
    }

    /**
     * 淘宝订单
     *
     * @param view
     */
    @OnClick(R.id.ll_taobao_dingdan)
    public void taobaoOrder(View view) {

    }

    /**
     * 淘宝物流信息
     *
     * @param view
     */
    @OnClick(R.id.ll_taobao_wuliu)
    public void taobaoLogistics(View view) {

    }

    /**
     * 淘宝购物车
     *
     * @param view
     */
    @OnClick(R.id.ll_taobao_gouwuche)
    public void taobaoCart(View view) {

    }

    /**
     * 我的收藏
     *
     * @param view
     */
    @OnClick(R.id.ll_favorite)
    public void myFavorite(View view) {

    }

    /**
     * 签到领积分
     *
     * @param view
     */
    @OnClick(R.id.ll_qiandao)
    public void signForIntegral(View view) {
        Intent intent = new Intent(getActivity(),SignInActivity.class);
        startActivity(intent);
    }

    /**
     * 我的积分
     *
     * @param view
     */
    @OnClick(R.id.ll_wode)
    public void myIntergral(View view) {

    }

    /**
     * 积分商城
     *
     * @param view
     */
    @OnClick(R.id.ll_jifen_mall)
    public void integralMall(View view) {
        startActivity(new Intent(getActivity(),BonusMallActivity.class));
    }
}
