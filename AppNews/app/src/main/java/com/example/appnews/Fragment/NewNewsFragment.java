package com.example.appnews.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnews.AppUtil;
import com.example.appnews.News;
import com.example.appnews.R;
import com.example.appnews.adapter.Thegioi_Adapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class NewNewsFragment extends Fragment {
    public static RecyclerView recyclerView6;
    public static Thegioi_Adapter thegioi_adapter6;
    public static ArrayList<News> newsArrayList6;
    View view;

    public NewNewsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_news, container, false);

        anhxa();
        thegioi_adapter6 = new Thegioi_Adapter(getActivity(), newsArrayList6);

        new ReadRSS().execute("https://vnexpress.net/rss/tin-moi-nhat.rss");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        if (linearLayoutManager != null)
            recyclerView6.setLayoutManager(linearLayoutManager);


        return view;
    }


    private void anhxa() {
        recyclerView6 = (RecyclerView) view.findViewById(R.id.recyclerView_hot);
    }

    public static class ReadRSS extends AsyncTask<String, Void, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            newsArrayList6 = new ArrayList<>();
            Document document = null;
            try {
                document = Jsoup.connect(strings[0]).get();
                Elements elements = document.select("item");
                News news = null;

                AppUtil.myReadRSS(newsArrayList6, news, elements);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return newsArrayList6;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);

            thegioi_adapter6.setData(news);

            recyclerView6.setAdapter(thegioi_adapter6);

        }
    }
}