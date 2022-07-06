package com.samucael.soccernews.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samucael.soccernews.R;
import com.samucael.soccernews.databinding.NewsItemBinding;
import com.samucael.soccernews.domain.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final FavoriteListener favoriteListener;

    public NewsAdapter(List<News> news, FavoriteListener favoriteListener){
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        News news = this.news.get(position);
        holder.binding.txtTittle.setText(news.tittle);
        holder.binding.txtBody.setText(news.body);
        Picasso.get().load(news.image)
                .fit()
                .into(holder.binding.imgThumb);
        // Implementação da função "abrir link"
        holder.binding.btnLink.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(news.link));
           context.startActivity(intent);
        });
        // Implementação da função "compartilhar"
        holder.binding.imgShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, news.link);
            holder.itemView.getContext().startActivity(Intent.createChooser(intent, "Compartilhar"));
        });
        // Implementação da função "favoritar"
        holder.binding.imgFavorite.setOnClickListener(v -> {
            news.favorite = !news.favorite;;
            this.favoriteListener.onFavorite(news);
            notifyItemChanged(position);
            Animation scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up);
            Animation scaleDown = AnimationUtils.loadAnimation(context, R.anim.scale_down);
            holder.binding.imgFavorite.startAnimation(scaleUp);
            holder.binding.imgFavorite.startAnimation(scaleDown);
        });
        if(news.favorite){
            holder.binding.imgFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_yellow, context.getTheme()));
            holder.binding.imgFavorite.setColorFilter(context.getResources().getColor(R.color.yellow));
        } else {
            holder.binding.imgFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_primary, context.getTheme()));
            holder.binding.imgFavorite.setColorFilter(context.getResources().getColor(R.color.icon));
        }

    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface FavoriteListener {
        void onFavorite(News news);
    }
}
