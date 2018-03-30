package com.example.sagi.imagesearch.ui.image;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.sagi.imagesearch.ui.base.BackPressedHandler;
import com.example.sagi.imagesearch.ui.image.manager.ImageManagerFragment;

public class ImageSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, ImageManagerFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        boolean backPressHandled = false;
        // Let added fragment handle back navigation, if not handled call super
        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        if (fragment != null && fragment instanceof BackPressedHandler) {
            backPressHandled = ((BackPressedHandler) fragment).onBackPressed();
        }
        if (!backPressHandled) {
            super.onBackPressed();
        }
    }
}
