package com.appface.akhil.screencompanion.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.appface.akhil.screencompanion.R;
import com.appface.akhil.screencompanion.model.bean.movieDiscover.MovieDiscoverResponseResults;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MoviePagedListAdapter extends PagedListAdapter<MovieDiscoverResponseResults, MoviePagedListAdapter.ItemViewHolder> {

    private static final String TAG = "myPagedListAdapter";
    private final Context mCtx;

    public MoviePagedListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mCtx = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerviewcardview, parent, false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        MovieDiscoverResponseResults item = getItem(position);
        if (item != null) {
            holder.tvmoviename.setText(item.getOriginal_title());
            holder.tvreleasedate.setText(item.getRelease_date());

            Glide.with(mCtx)
                    .load(mCtx.getResources().getString(R.string.mImageBaseUrl)+ item.getPoster_path())
                    .placeholder(R.drawable.ic_placeholder_foreground)
                    .dontAnimate().into(holder.iv_poster);

        } else {
            Toast.makeText(mCtx, "Item null", Toast.LENGTH_SHORT).show();
        }
    }

    private static DiffUtil.ItemCallback<MovieDiscoverResponseResults> DIFF_CALLBACK = new DiffUtil.ItemCallback<MovieDiscoverResponseResults>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieDiscoverResponseResults oldItem, @NonNull MovieDiscoverResponseResults newItem) {
            return false;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MovieDiscoverResponseResults oldItem, @NonNull MovieDiscoverResponseResults newItem) {
            return oldItem.equals(newItem);
        }
    };

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvmoviename, tvreleasedate;
        ImageView iv_poster;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvmoviename = itemView.findViewById(R.id.tvmoviename);
            tvreleasedate = itemView.findViewById(R.id.tvreleasedate);
            iv_poster = itemView.findViewById(R.id.im_poster);
        }
    }
}
