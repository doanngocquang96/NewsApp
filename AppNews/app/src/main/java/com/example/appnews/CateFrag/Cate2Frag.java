package com.example.appnews.CateFrag;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class Cate2Frag extends Fragment {
    RecyclerView recyclerView2;
    Thegioi_Adapter thegioi_adapter2;
    ArrayList<News> newsArrayList2;
    View view;

    public Cate2Frag() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cate2, container, false);


        anhxa();

        new ReadRSS().execute("https://vnexpress.net/rss/thoi-su.rss");

        thegioi_adapter2 = new Thegioi_Adapter(getActivity(), newsArrayList2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        if (linearLayoutManager != null)
            recyclerView2.setLayoutManager(linearLayoutManager);


        return view;
    }


    private void anhxa() {
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView_thoisu);
    }

    private class ReadRSS extends AsyncTask<String, Void, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            newsArrayList2 = new ArrayList<>();
            Document document = null;
            try {
                document = Jsoup.connect(strings[0]).get();
                Elements elements = document.select("item");
                News news = null;

                AppUtil.myReadRSS(newsArrayList2, news, elements);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return newsArrayList2;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);

            thegioi_adapter2.setData(news);

            recyclerView2.setAdapter(thegioi_adapter2);

        }
    }

}