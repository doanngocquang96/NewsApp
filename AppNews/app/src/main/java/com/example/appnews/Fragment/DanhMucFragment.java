package com.example.appnews.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.appnews.DanhMuc.TinDaLuu_Activity;
import com.example.appnews.DanhMuc.TinDaXem_Activity;
import com.example.appnews.R;
import com.example.appnews.RoomDatabase.NewsDatabase;


public class DanhMucFragment extends Fragment {
    View view;
    LinearLayout tinDaXem, tinDaLuu, thongTinApp;


    public DanhMucFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_danh_muc, container, false);

        anhxa();

        tinDaXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TinDaXem_Activity.class);
                startActivity(intent);
            }
        });

        tinDaLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TinDaLuu_Activity.class);
                startActivity(intent);
            }
        });

        thongTinApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAboutApp();
            }
        });




        return view;
    }

    private void anhxa() {
        tinDaXem = (LinearLayout) view.findViewById(R.id.tinDaXem);
        tinDaLuu = (LinearLayout) view.findViewById(R.id.tinDaluu);
        thongTinApp = (LinearLayout) view.findViewById(R.id.aboutApp);
    }

    private void showAboutApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông tin ứng dụng");
        builder.setMessage("+ Ứng dụng: AppNews\n+ Lưu ý: không dùng với mục đích thương mại");

        // Negative option
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }

}