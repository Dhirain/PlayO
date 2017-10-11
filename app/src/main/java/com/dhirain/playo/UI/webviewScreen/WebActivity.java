package com.dhirain.playo.UI.webviewScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dhirain.playo.R;
import com.dhirain.playo.model.News;

public class WebActivity extends AppCompatActivity {

    public static final String IMAGE_TRANSITION_NAME = "transitionText";
    public static final String KEY_NEWS = "key_news";
    private static final String TAG = "WebActivity";

    private News news;
    private TextView titleText;
    private WebView webView;
    private Toolbar toolbar;
    private ImageView imageView;
    //private ProgressDialog mProgressDialog;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        getIntentData();

        setUI();

        clickListner();

        showOnWeb();

        ViewCompat.setTransitionName(titleText , WebActivity.IMAGE_TRANSITION_NAME);
    }

    private void showOnWeb() {
        webView.setWebViewClient(new MyBrowser(progressBar,imageView,webView));
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(news.getUrl());
    }

    private void getIntentData() {
        news = getIntent().getParcelableExtra(KEY_NEWS);
    }


    private void clickListner() {

    }

    private void setUI() {
        webView = (WebView) findViewById(R.id.webView);
        titleText = (TextView) findViewById(R.id.titleName);
        titleText.setText(news.getTitle());
        imageView = (ImageView) findViewById(R.id.no_result_found);
        progressBar = (ProgressBar)findViewById(R.id.progess);
        progressBar.setVisibility(View.VISIBLE);
    }

    /*private void intProgressbar() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getResources().getString(R.string.loading));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }*/

    public static void show(Context context,News news) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WebActivity.KEY_NEWS, news);
        context.startActivity(intent);
    }

    private class MyBrowser extends WebViewClient {
        private ProgressBar progressBar;
        private ImageView imageView;
        private WebView webView;

        public MyBrowser(ProgressBar progressBar, ImageView imageView,WebView webView) {
            this.progressBar = progressBar;
            this.imageView = imageView;
            this.webView = webView;

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            Log.d(TAG, "onReceivedError: 404");
            progressBar.setVisibility(View.GONE);
            webView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }



        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        }
    }
}
