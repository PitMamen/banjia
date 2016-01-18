package com.max.jacentsao.banjia.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.max.jacentsao.banjia.activity.MainActivity;
import com.max.jacentsao.banjia.activity.WelcomeActivity;

/**
 * Created by Gan on 2016/1/11.
 */
public class WelcomePagerAdapter extends PagerAdapter {
    private int[] mImageIds;
    private Context context;

    public WelcomePagerAdapter(int[] mImageIds, Context context) {
        this.mImageIds = mImageIds;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(mImageIds[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        if (position == mImageIds.length - 1) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = context.getSharedPreferences("banjia", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("isFirst", false);
                    editor.commit();

                    // 跳转到首页
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    ((WelcomeActivity)context).finish();
                }
            });
        }

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
