package com.max.jacentsao.banjia.module;

/**
 * Created by JacenTsao on 2016/1/20.
 */
public class CategorySearchEvent {
    private String mMsg;
    public CategorySearchEvent(String msg) {
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
