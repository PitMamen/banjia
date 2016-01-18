package com.max.jacentsao.banjia.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.customview.CustomLinearLayout;
import com.max.jacentsao.banjia.utils.ClearCacheUtil;

@ContentView(R.layout.activity_system_setting)
public class SystemSettingActivty extends AppCompatActivity {
    //返回上一个界面
    @ViewInject(R.id.ib_system_setting_back)
    private ImageButton ibBack;

    //清除缓存
    @ViewInject(R.id.custom_clear_cache)
    private CustomLinearLayout clearCache;

    //检查更新
    @ViewInject(R.id.custom_check_update)
    private CustomLinearLayout checkUpdate;


    //关于半价特卖
    @ViewInject(R.id.custom_about_banjia)
    private CustomLinearLayout about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        getVersionCode();
    }

    /**
     * 获取当前APP的版本号
     */
    private void getVersionCode() {
        PackageManager manager;

        PackageInfo info = null;

        manager = this.getPackageManager();

        try {

            info = manager.getPackageInfo(this.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();

        }

//        int versionCode = info.versionCode;

        String versionName = info.versionName;

//        String packageName = info.packageName;

//        Signature[] signatures = info.signatures;

        checkUpdate.setContent(versionName);
    }

    /**
     * 结束当前页面，返回上一个界面
     * @param view
     */
    @OnClick(R.id.ib_system_setting_back)
    public void goBack(View view){
        this.finish();
    }

    /**
     * 清除缓存
     * @param view
     */
    @OnClick(R.id.custom_clear_cache)
    public void clearCache(View view){
        ClearCacheUtil.cleanApplicationData(this);
    }

    /**
     * 检查更新
     * @param view
     */
    @OnClick(R.id.custom_check_update)
    public void checkUpdate(View view){

    }

    /**
     * 关于半价特卖
     * @param view
     */
    @OnClick(R.id.custom_about_banjia)
    public void about(View view){
        Intent intent = new Intent();
        intent.setClass(this,AboutBanJiaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
