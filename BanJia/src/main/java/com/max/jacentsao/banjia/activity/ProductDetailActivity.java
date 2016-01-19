package com.max.jacentsao.banjia.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.max.jacentsao.banjia.R;

/**
 * 用于展示淘宝商品的商品详情页，页面直接拉取淘宝界面
 * @author JacenTsao
 */
@ContentView(R.layout.activity_product_detail)
public class ProductDetailActivity extends AppCompatActivity {

    @ViewInject(R.id.web_view)
    private WebView webView;

    @ViewInject(R.id.ib_product_detail_back)
    private ImageButton ibBack;

    @ViewInject(R.id.ib_product_detail_click)
    private ImageButton ibClick;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        initView();
    }

    private void initView() {
        builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_share_favor);

        String productUrl = getIntent().getStringExtra("productUrl");
//        webView.loadUrl(productUrl);
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
        //WebView设置
        WebSettings webSettings = webView.getSettings();
        //可JS交互
        webSettings.setJavaScriptEnabled(true);
        //可缩放
        webSettings.setSupportZoom(true);
        //请求焦点
        webView.requestFocus();

        webView.loadUrl(productUrl);

        WebViewClient client = new WebViewClient() {

            @Override

            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);

            }

        };

        webView.setWebViewClient(client);
    }

    @OnClick(R.id.ib_product_detail_click)
    public void shareOrFavor(View view){
        builder.show();
    }
}
