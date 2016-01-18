package com.max.jacentsao.banjia.utils;
import com.lidroid.xutils.exception.HttpException;

/**
 * Created by jerry on 15/11/2.
 */
public interface RequestDataCallback
{
    void onSuccess(String response);
    void onFailure(HttpException error);
}
