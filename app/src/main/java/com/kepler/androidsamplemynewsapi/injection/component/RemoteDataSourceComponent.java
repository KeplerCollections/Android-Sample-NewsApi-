package com.kepler.androidsamplemynewsapi.injection.component;

import com.kepler.androidsamplemynewsapi.api.repo.Repository;
import com.kepler.androidsamplemynewsapi.injection.module.RemoteDataSourceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RemoteDataSourceModule.class})
public interface RemoteDataSourceComponent {
    void inject(Repository repository);
}