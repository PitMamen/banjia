package com.max.jacentsao.banjia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.jacentsao.banjia.R;

/**
 * Created by JacenTsao on 2016/1/14.
 */
public class FragmentBonusMallMyExchange extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bonus_mall_myexchange, container, false);
        return view;
    }
}
