package com.max.jacentsao.banjia.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.adapter.SearchRecyclerViewAdapter;

/**
 * Created by Administrator on 2016/1/13.
 */
public class FragmentSearch extends Fragment {

    @ViewInject(R.id.recycler_view_search)
    private RecyclerView recyclerView;
    private String[] texts;
    private int[] images;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ViewUtils.inject(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //准备文件图片资源
        texts = getResources().getStringArray(R.array.category_search);
        images = new int[]{R.mipmap.fushi, R.mipmap.nanzhuang, R.mipmap.jujia,
                R.mipmap.muying, R.mipmap.xiebao, R.mipmap.nieyi,
                R.mipmap.meishi, R.mipmap.shuma, R.mipmap.peishi,
                R.mipmap.huazhuangpin, R.mipmap.wenti, R.mipmap.oldman};

        //设置为GridView视图
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.offsetChildrenHorizontal(100);
        recyclerView.setLayoutManager(gridLayoutManager);
        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(getActivity(), images, texts);
        recyclerView.setAdapter(adapter);
    }

}
