package com.max.jacentsao.banjia.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.max.jacentsao.banjia.R;

/**
 * 我的：界面自定义线性布局
 * Created by JacenTsao on 2016/1/11.
 */
public class CustomLinearLayout extends LinearLayout {

    private TextView textViewLeft;
    private ImageView imageView;
    private TextView textViewRight;
    private ImageView imageViewArrow;

    public CustomLinearLayout(Context context) {
        super(context);
        init(context,null);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_mine_custom_linearlayout, this);
        textViewLeft = (TextView) view.findViewById(R.id.tv_custom_text_left);
        imageView = (ImageView) view.findViewById(R.id.iv_mine_custom);
        textViewRight = (TextView) view.findViewById(R.id.tv_custom_text_right);
        imageViewArrow = (ImageView) view.findViewById(R.id.iv_custom_ll_arrow_right);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.CustomLinearLayout);
        String tv_id = array.getString(R.styleable.CustomLinearLayout_tv_custom);
        Drawable iv_id = array.getDrawable(R.styleable.CustomLinearLayout_iv_custom);
        textViewLeft.setText(tv_id);
        imageView.setImageDrawable(iv_id);
        array.recycle();
    }

    public void setContent(String text){
        String versionCode = "v"+text;
        textViewRight.setVisibility(VISIBLE);
        textViewRight.setText(versionCode);
    }

    public void setIamgeviewArrow(boolean visible){
        if(!visible){
            imageViewArrow.setVisibility(GONE);
        }
    }
}
