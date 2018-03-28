package com.example.sagi.imagesearch.ui.base;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

}
