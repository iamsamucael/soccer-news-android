package com.samucael.soccernews.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.samucael.soccernews.data.SoccerNewsRepository;
import com.samucael.soccernews.domain.News;

import java.util.List;

    public class FavoritesViewModel extends ViewModel {

        public FavoritesViewModel() {

        }

        public LiveData<List<News>> loadFavoriteNews() {
            return SoccerNewsRepository.getInstance().getLocalDb().newsDao().loadFavoritesNews();
        }

        public void saveNews(News news) {
            AsyncTask.execute(() -> SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news));
        }

    }