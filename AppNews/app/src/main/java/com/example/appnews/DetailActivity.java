package com.example.appnews;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appnews.RoomDatabase.NewsDatabase;


public class DetailActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        webView = (WebView) findViewById(R.id.webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_webview);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        webView.getSettings().setJavaScriptEnabled(true);   // ngon hon, nhung lag hon
        webView.loadUrl(link);

        webView.setWebViewClient(new WebViewClient());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_luu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.luuTinTuc:
                luuTinTucDB();
                return true;

            case android.R.id.home: // press back
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void luuTinTucDB() {
//        int dbSize = NewsDatabase.getInstance(DetailActivity.this).newsDAO().getCount();    // lay ra id cuoi cung cua database (=size)
//        News news = NewsDatabase.getInstance(DetailActivity.this).newsDAO().getNewsById(dbSize);    // lay ra tin tuc cuoi cung (moi nhat)
//        => bi loi khi xoa toan bo xong luu tin

        News news = NewsDatabase.getInstance(DetailActivity.this).newsDAO().getLastNews();  // lay ra new cuoi cung

        news.setSave(1);    // save = 1 => da luu
        NewsDatabase.getInstance(DetailActivity.this).newsDAO().updateNewsDatabase(news);

        Toast.makeText(this, "Đã lưu", Toast.LENGTH_SHORT).show();
    }

}