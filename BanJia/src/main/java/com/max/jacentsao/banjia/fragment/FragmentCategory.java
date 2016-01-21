package com.max.jacentsao.banjia.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.activity.ProductDetailActivity;
import com.max.jacentsao.banjia.adapter.HomeProductListAdapter;
import com.max.jacentsao.banjia.module.HomeListProduct;
import com.max.jacentsao.banjia.utils.BanJiaNetworkUtil;
import com.max.jacentsao.banjia.utils.RequestDataCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JacenTsao on 2016/1/18.
 */
public class FragmentCategory extends BaseFragment {

    @ViewInject(R.id.pull_refresh_listview_category_show)
    private PullToRefreshListView pullToRefreshListView;

    private HomeProductListAdapter adapter;

    private Map<String, Object> params = new HashMap<>();

    private ProgressDialog progressDialog;
    private List<HomeListProduct.RowsEntity> list = new ArrayList<>();
    int currentPage = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_show, container, false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        loadDatas();
    }

    public void init(View view) {
//        设置加载ProgressDialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("正在加载，请稍候~~");

        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new HomeProductListAdapter(getActivity(), R.layout.item_fragment_home_list);
        pullToRefreshListView.setAdapter(adapter);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage = 1;
                list.clear();
                loadDatas();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage++;
                loadDatas();
            }
        });
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String productUrl = list.get(position).getProductUrl();
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("productUrl", productUrl);
                startActivity(intent);
            }
        });
    }


    public void loadDatas() {
//        progressDialog.show();
        Bundle arguments = getArguments();
        params.put("act", "getproductlist");
        params.put("pages", currentPage);
        params.put("bc", arguments.getString("bc"));
        params.put("sc", 0);
        params.put("channel", 0);
        params.put("lprice", 0);
        params.put("hprice", 0);
        params.put("tbclass", 0);
        params.put("actid", 0);
        params.put("brandid", 0);
        params.put("v", 31);
        params.put("sorts", arguments.getString("sorts"));
        BanJiaNetworkUtil.getInstance().getCategoryShowList(params, new RequestDataCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                HomeListProduct homeListProduct = gson.fromJson(response, HomeListProduct.class);
                if (homeListProduct != null && homeListProduct.getRows() != null) {
                    List<HomeListProduct.RowsEntity> rows = homeListProduct.getRows();
                    list.addAll(rows);
                    adapter.addDatas(rows);
                }
                hidenRresh();
            }

            @Override
            public void onFailure(HttpException error) {
                hidenRresh();
                Toast.makeText(getActivity(), "网络异常，请稍候重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //用于影藏刷新提示
    private void hidenRresh() {
        if (pullToRefreshListView.isRefreshing()) {
            //设置延迟隐藏刷新提示
            pullToRefreshListView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pullToRefreshListView.onRefreshComplete();
                }
            }, 1000);
        }
        if (progressDialog.isShowing()) {
            pullToRefreshListView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 1000);

        }
    }

}
