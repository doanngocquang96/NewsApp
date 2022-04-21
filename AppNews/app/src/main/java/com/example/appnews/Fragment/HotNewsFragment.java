package com.example.appnews.Fragment;

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


public class HotNewsFragment extends Fragment {
    public static RecyclerView recyclerView4;
    public static Thegioi_Adapter thegioi_adapter4;
    public static ArrayList<News> newsArrayList4;
    View view;

    public HotNewsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot_news, container, false);

        anhxa();
        thegioi_adapter4 = new Thegioi_Adapter(getActivity(), newsArrayList4);

        new ReadRSS().execute("https://vnexpress.net/rss/tin-noi-bat.rss");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        if (linearLayoutManager != null)
            recyclerView4.setLayoutManager(linearLayoutManager);


        return view;
    }


    private void anhxa() {
        recyclerView4 = (RecyclerView) view.findViewById(R.id.recyclerView_hot);
    }

    public static class ReadRSS extends AsyncTask<String, Void, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            newsArrayList4 = new ArrayList<>();
            Document document = null;
            try {
                document = Jsoup.connect(strings[0]).get();
                Elements elements = document.select("item");
                News news = null;
                AppUtil.myReadRSS(newsArrayList4, news, elements);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return newsArrayList4;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);

            thegioi_adapter4.setData(news);

            recyclerView4.setAdapter(thegioi_adapter4);

        }
    }
}