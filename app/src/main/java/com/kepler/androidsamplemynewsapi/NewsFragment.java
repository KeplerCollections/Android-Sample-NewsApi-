package com.kepler.androidsamplemynewsapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.kepler.androidsamplemynewsapi.boilers.BaseFragment;
import com.kepler.androidsamplemynewsapi.pojo.Article;
import com.kepler.androidsamplemynewsapi.support.Constants;

import java.util.List;

import static com.kepler.androidsamplemynewsapi.support.Constants.PARAM_SOURCE;

public class NewsFragment extends BaseFragment {

    private String source;

    public static NewsFragment getInstance(Bundle bundle){
        NewsFragment newsFragment=new NewsFragment();
        newsFragment.setArguments(bundle);
        return newsFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        source = getArguments().getString(PARAM_SOURCE);
    }



    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mArticleReceiver, new IntentFilter(Constants.ACTION.ACTION_ARTICLE_ARRIVED));
        communicator.loadArticle(source);
    }

    @Override
    public void onDestroyView() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mArticleReceiver);
        super.onDestroyView();
    }

    private BroadcastReceiver mArticleReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(source.equals(intent.getStringExtra(Constants.PARAM_SOURCE))){
                List<Article> articles= intent.getParcelableArrayListExtra(Constants.PARAM_DATA);
            }
        }
    };
}
