package com.example.sagi.imagesearch;

import com.example.sagi.imagesearch.ui.ImageListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sagiyemini on 28/03/2018.
 */

@Singleton
@Component(modules = ImageSearchModule.class)
public interface ImageSearchComponent {

    void inject(ImageListFragment imageListFragment);

}
