package com.example.sagi.imagesearch;

import com.example.sagi.imagesearch.ui.image.details.ImageDetailsFragment;
import com.example.sagi.imagesearch.ui.image.list.ImageListFragment;
import com.example.sagi.imagesearch.ui.image.manager.ImageManagerFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sagiyemini on 28/03/2018.
 */

@Singleton
@Component(modules = ImageSearchModule.class)
public interface ImageSearchComponent {

    void inject(ImageManagerFragment imageManagerFragment);

    void inject(ImageListFragment imageListFragment);

    void inject(ImageDetailsFragment imageDetailsFragment);

}
