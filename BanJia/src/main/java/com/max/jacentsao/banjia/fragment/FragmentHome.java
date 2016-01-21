package com.max.jacentsao.banjia.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.activity.CategoryListShowActivity;
import com.max.jacentsao.banjia.activity.ProductDetailActivity;
import com.max.jacentsao.banjia.activity.SignInActivity;
import com.max.jacentsao.banjia.adapter.BannerListAdapter;
import com.max.jacentsao.banjia.adapter.HomeProductListAdapter;
import com.max.jacentsao.banjia.application.GlobalApplication;
import com.max.jacentsao.banjia.module.Banner;
import com.max.jacentsao.banjia.module.CategorySearchEvent;
import com.max.jacentsao.banjia.module.HomeListProduct;
import com.max.jacentsao.banjia.module.HomeTodayNew;
import com.max.jacentsao.banjia.utils.BanJiaNetworkUtil;
import com.max.jacentsao.banjia.utils.ListViewHeightCalculateUtil;
import com.max.jacentsao.banjia.utils.RequestDataCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import de.greenrobot.event.EventBus;

/**
 * Created by JacenTsao on 2016/1/11.
 * 加载首页数据
 */
public class FragmentHome extends BaseFragment {
    //当前页码
    private int currentPage = 1;

    //下方ListView
    @ViewInject(R.id.home_pull_refresh_list)
    private ListView lvHomePoductList;

    //存放加载ListView的参数
    private Map<String, Object> paramsHomeList = new HashMap<>();

    //存放今日更新的参数
    private Map<String, Object> paramsTodayNew = new HashMap<>();

    //存放ListView数据
    private List<HomeListProduct.RowsEntity> list = new ArrayList<>();

    //存放今日更新数据
    private List<HomeTodayNew.URLEntity> listTodayNew = new ArrayList<>();

    //存放首页Banner数据
    private List<Banner.AdListEntity> listBanner = new ArrayList<>();

    //ListView适配器
    private HomeProductListAdapter homeProductListAdapter;

    //头部广告的适配器
    private BannerListAdapter bannerListAdapter;

    //用于存放今日更新的四个图片
    private List<ImageView> listImageView = new ArrayList<>();

    @ViewInject(R.id.rg_select_type)
    private RadioGroup radioGroup;

    //中间今日更新四个图片
    @ViewInject(R.id.iv_home_worth_discount)
    private ImageView ivDiscount;

    @ViewInject(R.id.iv_home_delicious_food)
    private ImageView ivDeliciousFood;

    @ViewInject(R.id.iv_home_fancy_female_cloth)
    private ImageView ivFancyCloth;

    @ViewInject(R.id.iv_home_today_new)
    private ImageView ivTodayNew;

    //头部广告Banner
    @ViewInject(R.id.vp_home)
    private AutoScrollViewPager autoScrollViewPager;

    //中间区域的上下拉刷新ScrollView
    @ViewInject(R.id.pull_refresh_scrollview)
    private PullToRefreshScrollView pullToRefreshScrollView;

    //签到
    @ViewInject(R.id.image_qiandao)
    private ImageView ivHomeSignIn;

    //搜索
    @ViewInject(R.id.image_search)
    private ImageView ivSearch;

    private ProgressDialog progressDialog;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewUtils.inject(this, view);
//        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        loadHomeBannerDatas();
        loadHomeTodayNewDatas();
        loadHomeListDatas();
    }

    private void initViews(View view) {
        //跳转到按类别展示商品列表界面
        intent = new Intent(getActivity(),CategoryListShowActivity.class);

        //设置加载ProgressDialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("正在加载，请稍后~~");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        //首页中间今日更新
        listImageView.add(ivTodayNew);
        listImageView.add(ivFancyCloth);
        listImageView.add(ivDiscount);
        listImageView.add(ivDeliciousFood);

        //首页Banner轮播控件
        bannerListAdapter = new BannerListAdapter(getActivity(), listBanner);
        autoScrollViewPager.setAdapter(bannerListAdapter);
        autoScrollViewPager.startAutoScroll(5000);
        autoScrollViewPager.setInterval(5000);
        autoScrollViewPager.setStopScrollWhenTouch(true);
        autoScrollViewPager.setCycle(true);
        autoScrollViewPager.setScrollDurationFactor(5);

        //首恶ListView
        homeProductListAdapter = new HomeProductListAdapter(getActivity());
        lvHomePoductList.setAdapter(homeProductListAdapter);
        lvHomePoductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String productUrl = list.get(position).getProductUrl();
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productUrl", productUrl);
                startActivity(intent);

            }
        });

        pullToRefreshScrollView.scrollTo(0, 0);
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                currentPage = 1;
                list.clear();
                loadHomeListDatas();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                currentPage++;
                loadHomeListDatas();
            }

        });
    }

    /**
     * 首页搜索菜单，点击跳转到搜索界面
     * @param view
     */
    @OnClick(R.id.image_search)
    public void search(View view) {
        EventBus.getDefault().post(new CategorySearchEvent("hello"));
    }

    /**
     * 首页签到菜单，点击跳转到签到界面
     *
     * @param view
     */
    @OnClick(R.id.image_qiandao)
    public void signIn(View view) {
        startActivity(new Intent(getActivity(), SignInActivity.class));
    }


    /**
     * 首页鞋包，居家，数码家电，更多跳转界面
     *
     * @param radioGroup
     * @param checkedId
     */
    @OnRadioGroupCheckedChange(R.id.rg_select_type)
    public void choiceProductType(RadioGroup radioGroup, int checkedId) {
        Intent intent = new Intent();
        String bc;
        switch (checkedId) {
            case R.id.rb_home_shoes_bags:
                bc = 5 + "";
                intent.setClass(getActivity(), CategoryListShowActivity.class);
                intent.putExtra("bc", bc);
                startActivity(intent);
                break;
            case R.id.rb_home_home:
                bc = 3 + "";
                intent.setClass(getActivity(), CategoryListShowActivity.class);
                intent.putExtra("bc", bc);
                startActivity(intent);
                break;
            case R.id.rb_home_electrics:
                bc = 9 + "";
                intent.setClass(getActivity(), CategoryListShowActivity.class);
                intent.putExtra("bc", bc);
                startActivity(intent);
                break;
            default:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, new FragmentSearch()).commit();
                break;
        }
    }

    /**
     * 中间今日更新四张图片的点击事件
     *
     * @param view
     */
    @OnClick({R.id.iv_home_worth_discount, R.id.iv_home_delicious_food, R.id.iv_home_fancy_female_cloth, R.id.iv_home_today_new})
    public void onImageClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_home_worth_discount:
                break;
            case R.id.iv_home_delicious_food:
                break;
            case R.id.iv_home_fancy_female_cloth:
                break;
            case R.id.iv_home_today_new:
                break;
        }
    }

    /**
     * 首页超值折扣
     *
     * @param view
     */
    @OnClick(R.id.iv_home_worth_discount)
    public void worthDiscount(View view) {
        intent.putExtra("bc",1);
        startActivity(intent);
    }

    /**
     * 首页美味零食
     *
     * @param view
     */
    @OnClick(R.id.iv_home_delicious_food)
    public void deliciousFood(View view) {
        intent.putExtra("bc",1);
        startActivity(intent);
    }

    /**
     * 首页精品女装
     *
     * @param view
     */
    @OnClick(R.id.iv_home_fancy_female_cloth)
    public void fancyFemaleCloth(View view) {
        intent.putExtra("bc",1);
        startActivity(intent);
    }

    /**
     * 首页今日更新
     *
     * @param view
     */
    @OnClick(R.id.iv_home_today_new)
    public void todayNew(View view) {
        intent.putExtra("bc",1);
        startActivity(intent);
    }

    /**
     * 首页ViewPager广告栏
     */
    private void loadHomeBannerDatas() {

        BanJiaNetworkUtil.getInstance().getBanner(new RequestDataCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                Banner banner = gson.fromJson(response, Banner.class);
                if (banner != null && banner.getAdList() != null) {
                    List<Banner.AdListEntity> bannerList = banner.getAdList();
                    listBanner.addAll(bannerList);
                    bannerListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(HttpException error) {

            }
        });


    }

    /**
     * 加载中间四个跳转图片
     * act=getproductlist&pages=1&bc=0&sc=0&sorts=&channel=0&ckey=&daynews=&lprice=0&hprice=0&tbclass=0&actid=0&brandid=0&predate=&v=31
     */
    private void loadHomeTodayNewDatas() {
        paramsTodayNew.put("act", "getproductlist");
        paramsTodayNew.put("pages", 1);
        paramsTodayNew.put("bc", 0);
        paramsTodayNew.put("sc", 0);
        paramsTodayNew.put("channel", 0);
        paramsTodayNew.put("lprice", 0);
        paramsTodayNew.put("hprice", 0);
        paramsTodayNew.put("tbclass", 0);
        paramsTodayNew.put("actid", 0);
        paramsTodayNew.put("brandid", 0);
        paramsTodayNew.put("v", 31);
        BanJiaNetworkUtil.getInstance().getHomeTodayNew(paramsTodayNew, new RequestDataCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.opt(i);
                        String urlAddress = jsonObject.get("imgUrl").toString();
                        GlobalApplication.getApp().getImageLoader().displayImage(urlAddress,
                                listImageView.get(i), GlobalApplication.getApp().getImageOptions());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException error) {

            }
        });
    }

    /**
     * 加载首页ListView数据
     */
    private void loadHomeListDatas() {
        progressDialog.show();
        paramsHomeList.put("pages", currentPage);
        paramsHomeList.put("act", "getproductlist");
        paramsHomeList.put("bc", 0);
        paramsHomeList.put("sc", 0);
        paramsHomeList.put("channel", 0);
        paramsHomeList.put("lprice", 0);
        paramsHomeList.put("hprice", 0);
        paramsHomeList.put("tbclass", 0);
        paramsHomeList.put("actid", 0);
        paramsHomeList.put("brandid", 0);
        paramsHomeList.put("v", 31);
        BanJiaNetworkUtil.getInstance().getHomeProductList(paramsHomeList, new RequestDataCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                HomeListProduct homeListProduct = gson.fromJson(response, HomeListProduct.class);
                if (homeListProduct != null && homeListProduct.getRows() != null) {
                    List<HomeListProduct.RowsEntity> rows = homeListProduct.getRows();
                    list.addAll(rows);
                    homeProductListAdapter.addDatas(rows);
                    hidenRresh();
                    ListViewHeightCalculateUtil.setListViewHeightBasedOnChildren(lvHomePoductList);
                }
            }

            @Override
            public void onFailure(HttpException error) {
                hidenRresh();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        autoScrollViewPager.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        autoScrollViewPager.startAutoScroll(5000);
    }

    //用于影藏刷新提示
    private void hidenRresh() {
        if (pullToRefreshScrollView.isRefreshing()) {
            //设置延迟隐藏刷新提示
            pullToRefreshScrollView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pullToRefreshScrollView.onRefreshComplete();
                }
            }, 1000);
        }
        if (progressDialog.isShowing()) {
            pullToRefreshScrollView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 1000);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
