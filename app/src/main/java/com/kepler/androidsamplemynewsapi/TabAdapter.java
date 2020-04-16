package com.kepler.androidsamplemynewsapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kepler.androidsamplemynewsapi.injection.module.ApiModule_ProvideApiServiceFactory;
import com.kepler.androidsamplemynewsapi.pojo.Article;
import com.kepler.androidsamplemynewsapi.pojo.Source;
import com.kepler.androidsamplemynewsapi.support.Constants;

import java.util.List;

import okhttp3.internal.Util;

import static com.kepler.androidsamplemynewsapi.support.Constants.PARAM_SOURCE;

public class TabAdapter extends FragmentStatePagerAdapter {


    private final List<Source> sourceList;
    private Bundle bundle;


    public TabAdapter(@NonNull FragmentManager fm,List<Source> sourceList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.sourceList=sourceList;

    }


    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    private Fragment getFragment(int position) {
        Log.e("position","="+position);
        bundle=new Bundle();
        bundle.putString(PARAM_SOURCE,sourceList.get(position).getId());
        return NewsFragment.getInstance(bundle);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return sourceList.get(position).getName();
    }

    @Override
    public int getCount() {
        return sourceList.size();
    }

}