package com.example.sagi.imagesearch.ui.base;

import android.support.annotation.CallSuper;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class BasePresenter<V extends MvpView> implements Presenter<V> {

    private V mMvpView;

    @Override
    @CallSuper
    public void attachView(V mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    @CallSuper
    public void detachView() {
        this.mMvpView = null;
    }

    public boolean isViewAttached() {
        return this.mMvpView != null;
    }

    public V getMvpView() {
        return this.mMvpView;
    }

}
