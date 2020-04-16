package com.kepler.androidsamplemynewsapi.injection.module;

import com.kepler.androidsamplemynewsapi.api.repo.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {


    @Provides
    @Singleton
    Repository provideRepo() {
        return new Repository();
    }
}