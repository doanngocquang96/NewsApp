package com.example.appnews;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class AppUtil {

    public static boolean isNetworkAvailable (Context context){
        if (context==null)
            return false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager==null)
            return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            Network network = connectivityManager.getActiveNetwork();
            if  (network==null)
                return false;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            return networkCapabilities != null && (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                    || (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));


        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }

    }


    public static ArrayList<News> myReadRSS(ArrayList<News> newsArrayList2, News news, Elements elements) {
          for (Element element : elements) {      // duyet het phan tu elements
   //     for (int i = 0; i < 20; i++) {                   // chi duyet 10 phan tu => 10 news

            news = new News("","","","","");

            news.setTitle(element.select("title").text());

            if (element.select("description").text().contains("</br>")) {
                String[] info = element.select("description").text().split("</br>");
                news.setInfo(info[1]);
            } else
                news.setInfo(element.select("description").text());

//                    news.setInfo("1");
//                    Log.e("AAAAAAAAAAAAAAAAAAAA",news.getThumbnail());
            news.setThumbnail(Jsoup.parse(element.select("description").text()).select("img").attr("src"));
            news.setLink(element.select("link").text());
            news.setDate(element.select("pubDate").text().substring(0, 25)); // substring cat bot chu thua dang sau cung

            newsArrayList2.add(news);
        }
        return newsArrayList2;
    }

}
