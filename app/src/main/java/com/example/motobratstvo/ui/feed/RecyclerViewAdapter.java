package com.example.motobratstvo.ui.feed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import com.example.motobratstvo.MainActivity;
import com.example.motobratstvo.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<News> news;

    RecyclerViewAdapter(Context context, List<News> news) {
        this.news = news;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        @SuppressLint("ResourceType") View view = inflater.inflate(R.layout.recycler_view_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        News news_ = news.get(position);
        //holder.imageView.setImageResource(news_.getImage());
        holder.textView.setText(news_.getText());
        holder.titleView.setText(news_.getTitle());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //final ImageView imageView;
        final TextView titleView, textView;
        final ViewPager2 viewPager2;


        ViewHolder(View view) {
            super(view);
            //imageView = (ImageView)view.findViewById(R.id.newsImage);
            textView = (TextView) view.findViewById(R.id.textNews);
            titleView = (TextView) view.findViewById(R.id.titleNews);
            viewPager2 = (ViewPager2) view.findViewById(R.id.viewPagerImages);
        }
    }
}