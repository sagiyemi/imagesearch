package com.example.sagi.imagesearch.ui.base;

import android.support.v4.app.Fragment;

import com.example.sagi.imagesearch.ImageSearchApplication;
import com.example.sagi.imagesearch.ImageSearchComponent;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class BaseFragment extends Fragment {

    protected final ImageSearchComponent activityComponent() {
        return ImageSearchApplication.getComponent(getContext());
    }

}
