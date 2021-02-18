package com.wingoku.moviescatalogue.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wingoku.moviescatalogue.R;
import com.wingoku.moviescatalogue.data.network.interfaces.MoviesApi;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}