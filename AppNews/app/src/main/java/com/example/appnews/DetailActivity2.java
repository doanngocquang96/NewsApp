package com.example.appnews;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class DetailActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        loadData();
    }

    private void loadData() {
        if (AppUtil.isNetworkAvailable(this)) {
            // co mang
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(DetailActivity2.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },2500);

        } else {
            // ko co mang
            //Toast.makeText(this, "No Internet!", Toast.LENGTH_LONG).show();

            dialogAppear();

        }
    }

    private void dialogAppear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lỗi mạng");
        builder.setMessage("Không có kết nối mạng");

        // Negative option
        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();  // thoat app
            }
        });

        builder.setPositiveButton("Thử lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                loadData();
            }
        });

        builder.show();
    }
}