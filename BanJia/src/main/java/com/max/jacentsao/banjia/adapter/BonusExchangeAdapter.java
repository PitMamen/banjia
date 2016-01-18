package com.max.jacentsao.banjia.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.application.GlobalApplication;
import com.max.jacentsao.banjia.module.BonusProductForExchange;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by JacenTsao on 2016/1/14.
 */
public class BonusExchangeAdapter extends AbsBaseAdapter2<BonusProductForExchange.RowsEntity> {
    public BonusExchangeAdapter(Context context, int... resid) {
        super(context, R.layout.item_bonus_mall_exchange);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, BonusProductForExchange.RowsEntity data) {
        ImageView viewProduct = (ImageView) viewHolder.getView(R.id.iv_item_product);
        ImageLoader.getInstance().displayImage(data.getProductImg(),viewProduct, GlobalApplication.getApp().getImageOptions());
        TextView viewProductName = (TextView) viewHolder.getView(R.id.tv_home_list_product_name);
        viewProductName.setText(data.getName());
        TextView viewBonusPointExchange = (TextView) viewHolder.getView(R.id.tv_item_bonus_point_exchange);
        viewBonusPointExchange.setText(data.getScore()+"积分");
        TextView viewOriginalPrice = (TextView) viewHolder.getView(R.id.tv_item_original_price);
        viewOriginalPrice.setText("原价￥"+data.getOldPrice()+".0");
        TextView viewGoodsLeft = (TextView) viewHolder.getView(R.id.tv_goods_exchange_left);
        viewGoodsLeft.setText("剩余"+data.getTotal()+"件");

    }
}
