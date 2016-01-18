package com.max.jacentsao.banjia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.adapter.BonusExchangeAdapter;
import com.max.jacentsao.banjia.module.BonusProductForExchange;
import com.max.jacentsao.banjia.utils.BanJiaNetworkUtil;
import com.max.jacentsao.banjia.utils.RequestDataCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JacenTsao on 2016/1/14.
 */
public class FragmentBonusMallExchange extends Fragment {

    private BonusExchangeAdapter adapter;

    @ViewInject(R.id.pull_refresh_list)
    private PullToRefreshListView pullToRefreshListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bonus_mall_exchange, container, false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        loadDatas();
    }


    private void initViews(View view) {
        adapter = new BonusExchangeAdapter(getActivity());
        pullToRefreshListView.setAdapter(adapter);
    }

    private Map<String, Object> params = new HashMap<>();

    private void loadDatas() {
        params.put("act", "getproductlist");
        params.put("pages", 1);
        params.put("cls", 0);
        params.put("v", 31);
        BanJiaNetworkUtil.getInstance().getBonusProductList(params, new RequestDataCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                BonusProductForExchange bonusProductForExchange = gson.fromJson(response, BonusProductForExchange.class);
                if (bonusProductForExchange != null && bonusProductForExchange.getTotal() != 0) {
                    List<BonusProductForExchange.RowsEntity> rows = bonusProductForExchange.getRows();
                    adapter.addDatas(rows);
                }
            }

            @Override
            public void onFailure(HttpException error) {
                Toast.makeText(getActivity(), "Sorry，网络异常，请稍后再试", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
