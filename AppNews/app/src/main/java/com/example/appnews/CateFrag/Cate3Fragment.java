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

public class Cate3Fragment extends Fragment {
    RecyclerView recyclerView3;
    Thegioi_Adapter thegioi_adapter3;
    ArrayList<News> newsArrayList3;
    View view;

    public Cate3Fragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cate3, container, false);


        anhxa();

        new ReadRSS().execute("https://vnexpress.net/rss/covid-19.rss");

        thegioi_adapter3 = new Thegioi_Adapter(getActivity(), newsArrayList3);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        if (linearLayoutManager != null)
            recyclerView3.setLayoutManager(linearLayoutManager);


        return view;
    }


    private void anhxa() {
        recyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerView_covid);
    }

    private class ReadRSS extends AsyncTask<String, Void, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            newsArrayList3 = new ArrayList<>();
            Document document = null;
            try {
                document = Jsoup.connect(strings[0]).get();
                Elements elements = document.select("item");
                News news = null;

                AppUtil.myReadRSS(newsArrayList3, news, elements);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return newsArrayList3;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);

            thegioi_adapter3.setData(news);

            recyclerView3.setAdapter(thegioi_adapter3);

        }
    }



}