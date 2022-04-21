package com.example.appnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnews.DetailActivity;
import com.example.appnews.R;
import com.example.appnews.News;
import com.example.appnews.RoomDatabase.NewsDatabase;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Thegioi_Adapter extends RecyclerView.Adapter<Thegioi_Adapter.ThegioiViewHolder> {
    Context context;
    ArrayList<News> thegioiArrayList;


    public Thegioi_Adapter(Context context, ArrayList<News> thegioiArrayList) {
        this.context = context;
        this.thegioiArrayList = thegioiArrayList;
    }



    public void setData (ArrayList<News> list){
        this.thegioiArrayList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThegioiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thegioi_item, parent, false);
        return new ThegioiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThegioiViewHolder holder, int position) {
        News news = thegioiArrayList.get(position);
        if (news == null)
            return;

        holder.tit.setText(news.getTitle());
        holder.info.setText(news.getInfo());
        holder.date.setText(news.getDate());
        if (news.getThumbnail().equals(""))
            Picasso.get().load(R.drawable.vnexpress).into(holder.image);
        else
            Picasso.get().load(news.getThumbnail()).into(holder.image);
      //  Picasso.get().load(R.drawable.vnexpress).into(holder.image);

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetail(news);
            }
        });

    }

    private void goToDetail(News news){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("link", news.getLink());

        News dbNews = new News(news.getTitle(), news.getLink(), news.getThumbnail(), news.getInfo(), news.getDate());
        NewsDatabase.getInstance(context).newsDAO().insertNews(dbNews);
       // Toast.makeText(context, "Added DB", Toast.LENGTH_SHORT).show();

        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (thegioiArrayList != null)
            return thegioiArrayList.size();
        return 0;
    }


    public class ThegioiViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView tit;
        private TextView info;
        private TextView date;
        private CardView itemLayout;

        public ThegioiViewHolder(@NonNull View itemView) {
            super(itemView);
            itemLayout = (CardView) itemView.findViewById(R.id.item_layout);
            image = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
            tit = (TextView) itemView.findViewById(R.id.tv_tittle);
            info = (TextView) itemView.findViewById(R.id.tv_info);
            date = (TextView) itemView.findViewById(R.id.tv_date);

        }

    }

//    public static String fmt(double d)
//    {
//        if(d == (long) d)
//            return String.format("%d",(long)d);
//        else
//            return String.format("%s",d);
//    }

}
