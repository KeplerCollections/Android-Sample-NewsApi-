package com.kepler.androidsamplemynewsapi.injection.component;

import com.kepler.androidsamplemynewsapi.injection.module.AppModule;
import com.kepler.androidsamplemynewsapi.injection.module.RepoModule;
import com.kepler.androidsamplemynewsapi.presenters.MainPresnter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RepoModule.class})
public interface RepoComponent {
    void inject(MainPresnter mainPresnter);
}