package com.example.appnews.CateFrag;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.appnews.AppUtil;
import com.example.appnews.News;
import com.example.appnews.R;
import com.example.appnews.adapter.Thegioi_Adapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Cate1Frag extends Fragment {
    public static RecyclerView recyclerView;
    public static Thegioi_Adapter thegioi_adapter;
    public static ArrayList<News> newsArrayList;
    View view;

    public Cate1Frag() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cate1, container, false);

        anhxa();
        thegioi_adapter = new Thegioi_Adapter(getActivity(), newsArrayList);

        new ReadRSS().execute("https://vnexpress.net/rss/the-gioi.rss");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        if (linearLayoutManager != null)
            recyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }


    private void anhxa() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_thegioi);
    }

    public static class ReadRSS extends AsyncTask<String, Void, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            newsArrayList = new ArrayList<>();
            Document document = null;
            try {
                document = Jsoup.connect(strings[0]).get();
                Elements elements = document.select("item");
                News news = null;

                AppUtil.myReadRSS(newsArrayList, news, elements);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return newsArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);

            thegioi_adapter.setData(news);

            recyclerView.setAdapter(thegioi_adapter);

        }
    }
}