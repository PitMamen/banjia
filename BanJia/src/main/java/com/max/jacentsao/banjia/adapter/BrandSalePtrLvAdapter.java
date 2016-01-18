package com.max.jacentsao.banjia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.application.GlobalApplication;
import com.max.jacentsao.banjia.constant.Constants;
import com.max.jacentsao.banjia.module.BrandSale;
import com.max.jacentsao.banjia.module.BrandSaleProductInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gan on 2016/1/13.
 */
public class BrandSalePtrLvAdapter extends BaseAdapter
{

    private List<BrandSale.RowsEntity> mListBrand = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    private ImageLoader mImageLoader;
    private DisplayImageOptions mImageOptions;

    public BrandSalePtrLvAdapter(Context context, List<BrandSale.RowsEntity> mListBrand)
    {
        this.context = context;
        this.mListBrand = mListBrand;
        this.inflater = LayoutInflater.from(context);

        mImageLoader = GlobalApplication.getApp().getImageLoader();
        mImageOptions = GlobalApplication.getApp().getImageOptions();
    }

    @Override
    public int getCount()
    {
        return mListBrand.size();
    }

    @Override
    public BrandSale.RowsEntity getItem(int position)
    {
        return mListBrand.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.brand_sale_adapter_item, null);
            holder = new ViewHolder();

            ViewUtils.inject(holder, convertView);

            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        // 品牌名
        holder.mTvBrandItemLogo.setText(mListBrand.get(position).getName());

        // 折扣
        holder.mTvBrandItemDiscount.setText(numberFormat(mListBrand.get(position).getDisCount()));

        // 剩余天数
        holder.mTvBrandItemRemaining.setText(dateFormat(mListBrand.get(position).getEndDateStr()));


        // logo图片
        String logoImage_URL = mListBrand.get(position).getImgUrlSml();
        mImageLoader.displayImage(Constants.BRAND_SELL_IMAGEPLUS + logoImage_URL, holder.mIvBrandItemLogo, mImageOptions);

        // 获取ProductInfojson字符串
         final String productInfo = mListBrand.get(position).getProductInfo();

        // item红左边的折扣
        double discountLeft = getDateFromProductInfo(productInfo)[0].getDisCount();
        holder.mTvBrandItemDiscountLeft.setText(numberFormat(discountLeft));

        // item中左边的价格
        double newPriceLeft = getDateFromProductInfo(productInfo)[0].getNewPrice();
        holder.mTvBrandItemCostLeft.setText( numberFormat(newPriceLeft));

        // item中左边的图片
        String image_Left = getDateFromProductInfo(productInfo)[0].getProductImg();
        mImageLoader.displayImage(Constants.BRAND_SELL_IMAGEPLUS + image_Left, holder.mIvBrandItemLeft, mImageOptions);


        // item红右边的折扣
        double discountRight = getDateFromProductInfo(productInfo)[1].getDisCount();
        holder.mTvBrandItemDiscountRight.setText(numberFormat(discountRight));

        // item中右边的价格
        double newPriceRight = getDateFromProductInfo(productInfo)[1].getNewPrice();
        holder.mTvBrandItemCostRight.setText(numberFormat(newPriceRight));

        // item中右边的图片
        String image_Right = getDateFromProductInfo(productInfo)[1].getProductImg();
        mImageLoader.displayImage(Constants.BRAND_SELL_IMAGEPLUS + image_Right,holder.mIvBrandItemRight,mImageOptions);


        return convertView;
    }

    private BrandSaleProductInfo[] getDateFromProductInfo( String productInfo)
    {
        Gson gson = new Gson();
        BrandSaleProductInfo[] brandSaleProductInfo = gson.fromJson(productInfo,BrandSaleProductInfo[].class);
        return brandSaleProductInfo;
    }

    /**
     * 剩余天数计算
     *
     * @param date
     * @return
     */
    private String dateFormat(String date)
    {
        long currentDate = System.currentTimeMillis();

        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

        int day = 0;
        try
        {
            Date deadline = format.parse(date);

            day = (int) ((deadline.getTime() - currentDate) / (24 * 60 * 60 * 1000));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return day + "";
    }

    /**
     * 四舍五入保留一位小数
     *
     * @param num
     * @return
     */
    private String numberFormat(Double num)
    {
        DecimalFormat format = new DecimalFormat("0.0");
        String discount = format.format(num);
        return discount;
    }

    class ViewHolder
    {
        @ViewInject(R.id.iv_brand_sale_adapter_item_logo)
        private ImageView mIvBrandItemLogo;

        @ViewInject(R.id.iv_brand_sale_adapter_item_left)
        private ImageView mIvBrandItemLeft;

        @ViewInject(R.id.iv_brand_sale_adapter_item_right)
        private ImageView mIvBrandItemRight;

        @ViewInject(R.id.tv_brand_sale_adapter_item_cost_left)
        private TextView mTvBrandItemCostLeft;

        @ViewInject(R.id.tv_brand_sale_adapter_item_cost_right)
        private TextView mTvBrandItemCostRight;

        @ViewInject(R.id.tv_brand_sale_adapter_item_discount_left)
        private TextView mTvBrandItemDiscountLeft;

        @ViewInject(R.id.tv_brand_sale_adapter_item_discount_right)
        private TextView mTvBrandItemDiscountRight;

        @ViewInject(R.id.tv_brand_sale_adapter_item_logo)
        private TextView mTvBrandItemLogo;

        @ViewInject(R.id.tv_brand_sale_adapter_item_discount)
        private TextView mTvBrandItemDiscount;

        @ViewInject(R.id.tv_brand_sale_adapter_item_remaining_time)
        private TextView mTvBrandItemRemaining;


    }
}
