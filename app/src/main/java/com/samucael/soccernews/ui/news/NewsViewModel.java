package com.samucael.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.samucael.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        List<News> news = new ArrayList<>();
        news.add(new News("Seleção brasileira feminina vence", "Neque porro quisquam est qui dolorem ipsum."));
        news.add(new News("Sampaio vence contra Sport", "is simply dummy text of the printing and typesetting industry."));
        news.add(new News("Inter goleia o Grêmio no Brasileirão", "There is no one who loves pain itself."));

        this.news.setValue(news);

    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}