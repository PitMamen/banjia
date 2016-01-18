package com.max.jacentsao.banjia.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.max.jacentsao.banjia.activity.BannerShowActivity;
import com.max.jacentsao.banjia.application.GlobalApplication;
import com.max.jacentsao.banjia.module.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/13.
 */
public class BannerListAdapter extends PagerAdapter {

    private Context mcontext;

    private List<Banner.AdListEntity> listBanner = new ArrayList<>();


    public BannerListAdapter(Context mcontext, List<Banner.AdListEntity> listBanner) {
        this.mcontext = mcontext;
        this.listBanner = listBanner;
    }


    @Override
    public int getCount() {
        return listBanner.size();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mcontext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setClickable(true);
        GlobalApplication.getApp().getImageLoader().displayImage(listBanner.get(position).getImgUrl(), imageView, GlobalApplication.getApp().getImageOptions());
        container.addView(imageView);
        //为每一个ImageView添加点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, BannerShowActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("bannerImageUrl", listBanner.get(position).getImgUrl());
                mcontext.startActivity(intent);
            }
        });
        return imageView;
    }


}
