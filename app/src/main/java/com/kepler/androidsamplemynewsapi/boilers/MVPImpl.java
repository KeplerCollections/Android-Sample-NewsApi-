package com.kepler.androidsamplemynewsapi.boilers;

import static androidx.core.util.Preconditions.checkNotNull;

public abstract class MVPImpl<T extends MVP.BaseView> implements MVP.BasePresenter<T> {

    /**
     * The View linked to this Presenter
     */
    protected T view;

    @Override
    public void attachView(T view) {
        this.view = checkNotNull(view, "View cannot be null!");
    }


    @Override
    public void detachView() {
        this.view = null;
    }
}