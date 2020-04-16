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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kepler.androidsamplemynewsapi.boilers.BaseFragment;
import com.kepler.androidsamplemynewsapi.pojo.Article;
import com.kepler.androidsamplemynewsapi.support.Constants;
import com.kepler.androidsamplemynewsapi.support.interfaces.SetOnRecyclerViewItemClickListener;

import java.util.List;

import butterknife.BindView;

import static com.kepler.androidsamplemynewsapi.support.Constants.PARAM_SOURCE;

public class NewsFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private String source;
    private NewsAdapter mAdapter;

    public static NewsFragment getInstance(Bundle bundle){
        NewsFragment newsFragment=new NewsFragment();
        newsFragment.setArguments(bundle);
        return newsFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        source = getArguments().getString(PARAM_SOURCE);
        mAdapter = new NewsAdapter(new SetOnRecyclerViewItemClickListener<String>() {
            @Override
            public void onItemClick(String url) {
                communicator.openUrl(url);
            }

        });
        communicator.loadArticle(source);
    }



    @Override
    protected int getContentView() {
        return R.layout.fragment_news;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mArticleReceiver, new IntentFilter(Constants.ACTION.ACTION_ARTICLE_ARRIVED));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
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
                mAdapter.add(intent.getParcelableArrayListExtra(Constants.PARAM_DATA));
            }
        }
    };
}
