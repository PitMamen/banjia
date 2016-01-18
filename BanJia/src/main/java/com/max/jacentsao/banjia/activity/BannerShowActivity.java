package com.max.jacentsao.banjia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.adapter.HomeProductListAdapter;
import com.max.jacentsao.banjia.application.GlobalApplication;
import com.max.jacentsao.banjia.module.HomeListProduct;
import com.max.jacentsao.banjia.utils.BanJiaNetworkUtil;
import com.max.jacentsao.banjia.utils.ListViewHeightCalculateUtil;
import com.max.jacentsao.banjia.utils.RequestDataCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_banner_show)
public class BannerShowActivity extends AppCompatActivity {

    @ViewInject(R.id.lv_banner_list)
    private ListView bannerListView;

    @ViewInject(R.id.iv_banner_list_head)
    private ImageView ivBannerListHead;

    @ViewInject(R.id.pull_refresh_scrollview_banner)
    private PullToRefreshScrollView pullRefreshScrollView;

    private Map<String,Object> params = new HashMap<>();
    private int currentPage = 1;
    private int position;
    private HomeProductListAdapter homeProductListAdapter;
    //存放ListView数据
    private List<HomeListProduct.RowsEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        initViews();
        loadDatas();
    }

    private void initViews() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        String bannerImageUrl = intent.getStringExtra("bannerImageUrl");

        GlobalApplication.getApp().getImageLoader().displayImage(bannerImageUrl,ivBannerListHead,GlobalApplication.getApp().getImageOptions());
        homeProductListAdapter = new HomeProductListAdapter(this);
        bannerListView.setAdapter(homeProductListAdapter);

        pullRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        pullRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                currentPage = 1;
                list.clear();
                loadDatas();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                currentPage++;
                loadDatas();

            }
        });
    }


    /**
     *加载Banner跳转数据
     */
    private void loadDatas() {
        params.put("act", "getproductlist");
        params.put("pages", currentPage);
        params.put("bc", 0);
        params.put("sc", 0);
        params.put("channel", 0);
        params.put("lprice", 0);
        params.put("hprice", 0);
        params.put("tbclass", 0);
        params.put("brandid", 0);
        params.put("v", 31);
        switch (position){
            case 0:
                params.put("actid", 21);
                break;
            case 1:
                params.put("actid", 26);
                break;
            case 2:
                params.put("actid", 28);
                break;
        }
        BanJiaNetworkUtil.getInstance().getBannerDetailList(params, new RequestDataCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                HomeListProduct homeListProduct = gson.fromJson(response, HomeListProduct.class);
                if (homeListProduct != null && homeListProduct.getRows() != null) {
                    List<HomeListProduct.RowsEntity> rows = homeListProduct.getRows();
                    list.addAll(rows);
                    homeProductListAdapter.addDatas(list);
                    hidenRresh();
                    ListViewHeightCalculateUtil.setListViewHeightBasedOnChildren(bannerListView);
                }
            }

            @Override
            public void onFailure(HttpException error) {
                hidenRresh();
            }
        });
    }
    private void hidenRresh() {
        if (pullRefreshScrollView.isRefreshing()) {
            //设置延迟隐藏刷新提示
            pullRefreshScrollView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pullRefreshScrollView.onRefreshComplete();
                }
            }, 1000);
        }
    }
}
