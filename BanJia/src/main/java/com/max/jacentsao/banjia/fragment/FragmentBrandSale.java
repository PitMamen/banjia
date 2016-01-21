package com.max.jacentsao.banjia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.adapter.BrandSalePtrLvAdapter;
import com.max.jacentsao.banjia.module.BrandSale;
import com.max.jacentsao.banjia.utils.BanJiaNetworkUtil;
import com.max.jacentsao.banjia.utils.RequestDataCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gan on 2016/1/13.
 */
public class FragmentBrandSale extends BaseFragment
{
    @ViewInject(R.id.hsv_brand_sale)
    private HorizontalScrollView mHsvBrand;

    private BrandSalePtrLvAdapter mAdapter;

    private List<BrandSale.RowsEntity> mListBrand = new ArrayList<>();;

    @ViewInject(R.id.ptrLv_brand_sale)
    private PullToRefreshListView mPtrLvBrandSale;

    private int mCurrentPageNO = 1;
    private int bClass = 99;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_brand_sale, null);

        ViewUtils.inject(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initViews();

        initDatas();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


    }


    private void initViews()
    {
//        mListBrand = new ArrayList<>();

        mAdapter = new BrandSalePtrLvAdapter(getActivity(), mListBrand);
        mPtrLvBrandSale.setAdapter(mAdapter);

        mPtrLvBrandSale.getRefreshableView().setDivider(null);
        mPtrLvBrandSale.getRefreshableView().setSelector(android.R.color.transparent);

        // 同时支持上下拉刷新
        mPtrLvBrandSale.setMode(PullToRefreshBase.Mode.BOTH);

        mPtrLvBrandSale.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
        {
            // 处理下拉刷新事件
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
            {
                mListBrand.clear();
                mCurrentPageNO = 1;
                initDatas();

            }

            // 处理上拉刷新的事件
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
            {
                // 加载下一页
                mCurrentPageNO++;

                initDatas();
            }
        });

    }

    @OnRadioGroupCheckedChange(R.id.rg_brand_head)
    private void changeSelectType(RadioGroup radioGroup, int checkedId)
    {
        mCurrentPageNO = 1;
        mListBrand.clear();

        mPtrLvBrandSale.getRefreshableView().setSelection(0);
        switch (checkedId)
        {
            case R.id.rb_brand_head_new:
                bClass = 99;
                break;
            case R.id.rb_brand_head_yesterday:
                bClass = 98;
                break;
            case R.id.rb_brand_head_last:
                bClass = 97;
                break;
            case R.id.rb_brand_head_gril:
                bClass = 1;
                break;
            case R.id.rb_brand_head_shoes:
                bClass = 5;
                break;
            case R.id.rb_brand_head_life:
                bClass = 3;
                break;
            case R.id.rb_brand_head_beauty:
                bClass = 9;
                break;
            case R.id.rb_brand_head_food:
                bClass = 7;
                break;
            case R.id.rb_brand_head_baby:
                bClass = 4;
                break;
            case R.id.rb_brand_head_appliances:
                bClass = 8;
                break;
            case R.id.rb_brand_head_man:
                bClass = 2;
                break;
            case R.id.rb_brand_head_sports:
                bClass = 10;
                break;
        }

        initDatas();

    }

    private Map<String, Object> params = new HashMap<>();

    private void initDatas()
    {
        params.clear();
        // 设置参数
        params.put("act","brandlist");
        params.put("cpage", mCurrentPageNO);
        params.put("pagesize", 20);
        params.put("bclass", bClass);
        params.put("v",31);

        BanJiaNetworkUtil.getInstance().getBrandSale(params, new RequestDataCallback()
        {
            @Override
            public void onSuccess(String response)
            {
                Gson gson = new Gson();
                BrandSale brandSale = gson.fromJson(response, BrandSale.class);

                if (brandSale != null && brandSale.getRows() != null)
                {
                    mListBrand.addAll(brandSale.getRows());

                    mAdapter.notifyDataSetChanged();
                }

                mPtrLvBrandSale.onRefreshComplete();

            }

            @Override
            public void onFailure(HttpException error)
            {
                mPtrLvBrandSale.onRefreshComplete();
            }
        });

    }

}
