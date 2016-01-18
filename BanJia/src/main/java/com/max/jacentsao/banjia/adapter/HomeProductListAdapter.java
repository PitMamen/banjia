package com.max.jacentsao.banjia.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.application.GlobalApplication;
import com.max.jacentsao.banjia.module.HomeListProduct;

import java.math.BigDecimal;

/**
 * Created by JacenTsao on 2016/1/15.
 */
public class HomeProductListAdapter extends AbsBaseAdapter2<HomeListProduct.RowsEntity> {
    public HomeProductListAdapter(Context context, int... resid) {
        super(context, R.layout.item_fragment_home_list);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, HomeListProduct.RowsEntity data) {
        ImageView imageView = (ImageView) viewHolder.getView(R.id.iv_home_list_product_image);
        GlobalApplication.getApp().getImageLoader().displayImage(data.getProductImg1(), imageView, GlobalApplication.getApp().getImageOptions());
        TextView name = (TextView) viewHolder.getView(R.id.tv_home_list_product_name);
        name.setText(data.getName());
        TextView newPrice = (TextView) viewHolder.getView(R.id.tv_home_list_new_price);
        //新旧价格保留一位小数
        BigDecimal bNew = new BigDecimal(data.getNewPrice());
        double newPriceValue = bNew.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        newPrice.setText("￥" + newPriceValue);

        TextView originalPrice = (TextView) viewHolder.getView(R.id.tv_home_list_original_price);
        originalPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        BigDecimal bOld = new BigDecimal(data.getOldPrice());
        double oldPriceValue = bOld.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        originalPrice.setText("￥" + oldPriceValue);

        TextView isBaoyou = (TextView) viewHolder.getView(R.id.tv_home_list_is_baoyou);
        if (data.getIsBaoYou() == 1) {
            isBaoyou.setVisibility(View.VISIBLE);
        } else {
            isBaoyou.setVisibility(View.GONE);
        }
        TextView discount = (TextView) viewHolder.getView(R.id.tv_home_list_discount);

        String result = String.format("%.1f", data.getDiscount());
        discount.setText(result + "折");
        TextView totalSell = (TextView) viewHolder.getView(R.id.tv_home_list_sale_total);
        totalSell.setText("已售" + data.getSaleTotal() + "件");
        TextView platform = (TextView) viewHolder.getView(R.id.tv_home_list_PFrom);
        switch (data.getPFrom()) {
            case 0:
                platform.setText("天猫");
                break;
            case 1:
                platform.setText("淘宝");
                break;
        }
    }
}
