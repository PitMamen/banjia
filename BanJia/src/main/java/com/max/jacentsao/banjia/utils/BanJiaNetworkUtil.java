package com.max.jacentsao.banjia.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.max.jacentsao.banjia.application.GlobalApplication;
import com.max.jacentsao.banjia.constant.Constants;

import java.util.Map;
import java.util.Set;

/**
 * Created by JacenTsao on 2016/1/11.
 */
public class BanJiaNetworkUtil {
    private static BanJiaNetworkUtil util = new BanJiaNetworkUtil();
    private HttpUtils httpUtils;

    private BanJiaNetworkUtil() {
        httpUtils = GlobalApplication.getApp().getHttpUtils();
    }

    public static BanJiaNetworkUtil getInstance() {
        return util;
    }

    /**
     * 获取品牌特卖集合
     *
     * @param params
     * @param callback
     */
    public void getBrandSale(Map<String, Object> params, RequestDataCallback callback) {
        doPost(Constants.BRAND_SELL, params, callback);
    }

    /**
     * 获取广告信息
     */
    public void getBanner(final RequestDataCallback callback) {

        doPost(Constants.HOME_BANNER, null, callback);

    }


    /**
     * get请求
     *
     * @param requestUrl
     * @param params
     */
    private void doGet(String requestUrl, Map<String, Object> params, final RequestDataCallback callback) {
        RequestParams requestParams = new RequestParams();

        if (params != null) {
            Set<Map.Entry<String, Object>> set = params.entrySet();

            for (Map.Entry<String, Object> entry : set) {
                requestParams.addQueryStringParameter(
                        entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        httpUtils.send(HttpRequest.HttpMethod.GET,
                requestUrl, requestParams,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        callback.onSuccess(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        callback.onFailure(error);
                    }
                });
    }

    /**
     * post请求
     *
     * @param requestUrl
     * @param params
     * @param callback
     */
    private void doPost(String requestUrl, Map<String, Object> params, final RequestDataCallback callback) {
        RequestParams requestParams = new RequestParams();

        if (params != null) {
            Set<Map.Entry<String, Object>> set = params.entrySet();

            for (Map.Entry<String, Object> entry : set) {
                requestParams.addBodyParameter(
                        entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        httpUtils.send(HttpRequest.HttpMethod.POST,
                requestUrl, requestParams,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        callback.onSuccess(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        callback.onFailure(error);
                    }
                });
    }

    /**
     * 获取奖品信息列表
     *
     * @param params
     * @param callback
     */
    public void getBonusProductList(Map<String, Object> params, final RequestDataCallback callback) {
        doPost(Constants.BONUS_PRODUCT_LIST, params, callback);
    }

    /**
     * 首页ListView产品
     *
     * @param params
     * @param callback
     */
    public void getHomeProductList(Map<String, Object> params, final RequestDataCallback callback) {
        doPost(Constants.PRODUCT_LIST, params, callback);
    }

    /**
     * 加载首页今日更新数据
     * @param params
     * @param callback
     */
    public void getHomeTodayNew(Map<String, Object> params, final RequestDataCallback callback) {
        doPost(Constants.TODAY_NEW, params, callback);
    }

    /**
     * 首页Banner跳转页面
     * @param params
     * @param callback
     */
    public void getBannerDetailList(Map<String, Object> params, final RequestDataCallback callback){
        doPost(Constants.PRODUCT_LIST, params, callback);
    }

    /**
     * 分类展示页面的ListView产品
     *
     * @param params
     * @param callback
     */
    public void getCategoryShowList(Map<String, Object> params, final RequestDataCallback callback) {
        doPost(Constants.PRODUCT_LIST, params, callback);
    }


}
