package com.example.sagi.imagesearch.ui.image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

}
