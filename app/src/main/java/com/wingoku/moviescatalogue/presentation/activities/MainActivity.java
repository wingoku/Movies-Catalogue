package com.wingoku.moviescatalogue.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.wingoku.moviescatalogue.R;
import com.wingoku.moviescatalogue.data.network.interfaces.MoviesApi;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initNavController();
    }

    private void initNavController() {
        //to make splash screen fragment and categories fragment as TOP LEVEL FRAGMENTS cuz we don't want to show the BACK button in toolbar
        //https://developer.android.com/guide/navigation/navigation-ui#appbarconfiguration
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.splashScreenFragment, R.id.moviesListFragment).build();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = NavHostFragment.findNavController(navHostFragment);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    public void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

}