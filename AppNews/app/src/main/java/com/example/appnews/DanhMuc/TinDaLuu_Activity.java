package com.example.appnews.DanhMuc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnews.News;
import com.example.appnews.R;
import com.example.appnews.RoomDatabase.NewsDatabase;
import com.example.appnews.adapter.Thegioi_Adapter;

import java.util.ArrayList;
import java.util.Collections;

public class TinDaLuu_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    Thegioi_Adapter thegioi_adapter;
    ArrayList<News> newsArrayList;
    TextView khong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_da_luu);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_tinDaLuu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_daluu);
        khong = (TextView) findViewById(R.id.khongco);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        newsArrayList = new ArrayList<>();
        thegioi_adapter = new Thegioi_Adapter(this, newsArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        if (linearLayoutManager != null)
            recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(thegioi_adapter);

        newsArrayList = (ArrayList<News>) NewsDatabase.getInstance(TinDaLuu_Activity.this).newsDAO().getSavedNews();

        Collections.reverse(newsArrayList); // dao nguoc arraylist lai de hien thi cho tien loi

        thegioi_adapter.setData(newsArrayList);

        if (NewsDatabase.getInstance(TinDaLuu_Activity.this).newsDAO().getListNews().isEmpty()) {
            khong.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else {
            khong.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xoa_da_luu, menu);
        return true;
    }

    // nut quay ve va nut xoa lich su xem tin tuc
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.xoaDaLuu:
                if (NewsDatabase.getInstance(TinDaLuu_Activity.this).newsDAO().getListNews().isEmpty())
                    Toast.makeText(TinDaLuu_Activity.this, "Không có gì để xóa", Toast.LENGTH_SHORT).show();
                else
                    deleteAll();

                return true;

            case android.R.id.home: // press back
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteAll() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa tin đã lưu");
        builder.setMessage("Bạn có chắc muốn xóa tất cả tin đã lưu? (sẽ xóa luôn cả lịch sử xem)");

        // Negative option
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NewsDatabase.getInstance(TinDaLuu_Activity.this).newsDAO().deleteAllDatabase();
                NewsDatabase.getInstance(TinDaLuu_Activity.this).newsDAO().clearPrimaryKey();
                Toast.makeText(TinDaLuu_Activity.this, "Đã xóa", Toast.LENGTH_SHORT).show();

//                // cap nhat lai RecyclerView (da bi xoa)
//                newsArrayList = (ArrayList<News>) NewsDatabase.getInstance(TinDaXem_Activity.this).newsDAO().getListNews();
//                thegioi_adapter.setData(newsArrayList);


                // refresh activity without "blink" effect
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        builder.show();
    }

}