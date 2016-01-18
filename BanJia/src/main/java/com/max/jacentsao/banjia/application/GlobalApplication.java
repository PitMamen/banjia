package com.max.jacentsao.banjia.application;

import android.app.Application;
import android.os.Environment;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.constant.Constants;
import com.max.jacentsao.banjia.db.Banjia;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

import cn.smssdk.SMSSDK;

/**
 * 用户初始化
 * Created by JacenTsao on 2016/1/9.
 */
public class GlobalApplication extends Application {
    private HttpUtils httpUtils;
    private static GlobalApplication application;
    private ImageLoader imageLoader;
    private DisplayImageOptions imageOptions;
    private DbUtils dbUtils;
    private File cacheFileDir;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        createCacheFile();
        initHttpUtils();
        initDBUtils();
        initUniversalImageLoader();
        SMSSDK.initSDK(this, Constants.APPKEY,Constants.APPSECRET);
    }

    private void createCacheFile() {
        String cachePath;
        if(Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()){
            cachePath = Environment.getDownloadCacheDirectory().getPath();
        }else{
            cachePath = getApplicationContext().getCacheDir().getPath();
        }

        cacheFileDir = new File(cachePath,"/banjia/images");
        cacheFileDir.mkdirs();
    }


    private void initHttpUtils() {
        //XUtils--HttpUtils初始化
        httpUtils = new HttpUtils(10 * 1000);
        httpUtils.configRequestRetryCount(10);
        httpUtils.configRequestThreadPoolSize(5);
        httpUtils.configSoTimeout(30 * 1000);
        httpUtils.configResponseTextCharset("utf-8");
        httpUtils.configDefaultHttpCacheExpiry(30 * 60 * 1000);
        httpUtils.configHttpCacheSize(20 * 1024);
    }

    /**
     * 初始化DBUtils
     */
    private void initDBUtils(){
        DbUtils.DaoConfig daoConfig = new DbUtils.DaoConfig(this);
        daoConfig.setDbName("banjia.db");
        daoConfig.setDbDir(cacheFileDir.getPath());
        daoConfig.setDbVersion(1);
        dbUtils = DbUtils.create(daoConfig);
        try {
            //创建表单
            dbUtils.createTableIfNotExist(Banjia.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    //将 Application对象传到外界
    public static GlobalApplication getApp() {
        return application;
    }

    /**
     * 初始化Universal ImageLoader
     */
    private void initUniversalImageLoader(){
        imageLoader = ImageLoader.getInstance();


        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .memoryCacheSize((int)Runtime.getRuntime().maxMemory()/8)
                .diskCache(new UnlimitedDiskCache(cacheFileDir))
                .diskCacheFileCount(300)
                .diskCacheSize(200 * 1024 * 1024)
                .build();

        imageLoader.init(configuration);

        imageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.jky_jzsbdzdg)
                .showImageOnFail(R.mipmap.jky_jzsbdzdg)
                .showImageForEmptyUri(R.mipmap.jky_jzsbdzdg)
                .build();
    }

    public HttpUtils getHttpUtils() {
        return this.httpUtils;
    }
    public ImageLoader getImageLoader(){
        return  this.imageLoader;
    }
    public DisplayImageOptions getImageOptions(){
        return this.imageOptions;
    }

    public DbUtils getDbUtils(){
        return this.dbUtils;
    }


}
