package com.appface.akhil.screencompanion.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.appface.akhil.screencompanion.R;
import com.appface.akhil.screencompanion.model.bean.movieSearch.MovieResultItem;
import com.bumptech.glide.Glide;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieSearchRecyclerViewAdapter extends RecyclerView.Adapter<MovieSearchRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<MovieResultItem> contactList;
    private MovieSearchAdapterListener listener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.moviesearch_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MovieResultItem searchEntry = contactList.get(position);
        holder.name.setText(searchEntry.getTitle());

        Glide.with(context)
                .load(context.getResources().getString(R.string.mImageBaseUrl)+ searchEntry.getPoster_path())
                .placeholder(R.drawable.ic_placeholder_foreground)
                .dontAnimate().into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, phone;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactList.get(getAdapterPosition()));

                }
            });
        }
    }

    public MovieSearchRecyclerViewAdapter(Context context, List<MovieResultItem> contactList, MovieSearchAdapterListener listener) {
        this.context = context;
        this.contactList = contactList;
        this.listener = listener;
    }

    public interface MovieSearchAdapterListener {
        void onContactSelected(MovieResultItem contact);
    }
}
