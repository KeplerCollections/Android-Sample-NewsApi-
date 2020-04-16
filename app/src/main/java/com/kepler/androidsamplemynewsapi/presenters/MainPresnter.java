package com.kepler.androidsamplemynewsapi.presenters;

import com.kepler.androidsamplemynewsapi.api.remote.IRemoteDataSource;
import com.kepler.androidsamplemynewsapi.api.repo.Repository;
import com.kepler.androidsamplemynewsapi.boilers.MVPImpl;
import com.kepler.androidsamplemynewsapi.injection.component.DaggerRepoComponent;
import com.kepler.androidsamplemynewsapi.pojo.Article;
import com.kepler.androidsamplemynewsapi.pojo.Source;

import java.util.List;

import javax.inject.Inject;


public class MainPresnter extends MVPImpl<AppLogic.MainView> implements AppLogic.MainLogic {

    @Inject
    Repository repository;

    public MainPresnter() {
        DaggerRepoComponent.create().inject(this);
    }

    @Override
    public void loadSource() {
        loadSourcesFromRepository(view.isNetworkAvailable());
    }

    @Override
    public void loadArticle(String id) {
        loadArticleFromRepository(id, view.isNetworkAvailable());
    }

    private void loadSourcesFromRepository(boolean isNetworkAvailable) {
        view.startProgressing();
        repository.getSources(new IRemoteDataSource.LoadDataCallback<Source>() {
            @Override
            public void onDataLoaded(List<Source> list) {
                view.stopProgressing();
                if (list.isEmpty()) {
                    view.showNoSourcesData();
                } else {
                    view.showSources(list);
                }
            }

            @Override
            public void onDataNotAvailable() {
                view.stopProgressing();
                view.showLoadingSourcesError();
            }
        }, isNetworkAvailable);

    }

    private void loadArticleFromRepository(String source, boolean isNetworkAvailable) {
        view.startProgressing();
        repository.getArticles(source, new IRemoteDataSource.LoadDataCallback<Article>() {
            @Override
            public void onDataLoaded(List<Article> list) {
                view.stopProgressing();
                if (list.isEmpty()) {
//                    view.showNoSourcesData();
                } else {
                    view.showArticles(list, source);
                }
            }

            @Override
            public void onDataNotAvailable() {
                view.stopProgressing();
//                view.showLoadingSourcesError();
            }
        }, isNetworkAvailable);

    }
}
