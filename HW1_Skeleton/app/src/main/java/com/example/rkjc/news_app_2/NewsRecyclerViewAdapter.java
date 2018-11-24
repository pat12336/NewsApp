package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder> {

    final private ListItemClickListener listener;
    ArrayList<NewsItem> mRepos;

    public NewsRecyclerViewAdapter(ListItemClickListener listener, ArrayList<NewsItem> Repos) {
        this.listener = listener;
        this.mRepos = Repos;
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedIndex);
    }
    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return this.mRepos.size();
    }
    public class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView description;
        TextView date;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById((R.id.date));
            itemView.setOnClickListener(this);
        }

        void bind(final int listIndex) {
            NewsItem item = mRepos.get(listIndex);
            System.out.println(item.getTitle());
            title.setText(item.getTitle());
            description.setText(item.getDescription());
            date.setText(item.getPublishedAt());

        }

        @Override
        public void onClick(View view) {
            int clickPosition = getAdapterPosition();
            listener.onListItemClick(clickPosition);
        }
    }
}
