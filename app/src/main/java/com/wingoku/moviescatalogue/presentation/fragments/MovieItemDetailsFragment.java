package com.wingoku.moviescatalogue.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.squareup.picasso.Picasso;
import com.wingoku.moviescatalogue.R;
import com.wingoku.moviescatalogue.domain.models.MovieItemDetails;
import com.wingoku.moviescatalogue.presentation.activities.MainActivity;

public class MovieItemDetailsFragment extends Fragment {
    private static final String TAG = "ItemDetailsFragment";
    private MovieItemDetails movieItemDetails;
    private TextView movieNameTV;
    private TextView movieDescTV;
    private TextView moviePriceTV;
    private TextView movieAvailabilityTV;
    private ImageView movieImageView;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_fragment_item_details, container, false);
        initUI(view);

        //adding collapsing toolbar in navigation ui for showing back button in toolbar and let nav controller handle back button interaction
        NavigationUI.setupWithNavController(toolbar,  NavHostFragment.findNavController(this));

        movieItemDetails = (MovieItemDetails) MovieItemDetailsFragmentArgs.fromBundle(requireArguments()).getMovieItemDetails();
        populateUI(movieItemDetails);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)requireActivity()).hideToolbar();
    }


    private void initUI(View view) {
        movieNameTV = view.findViewById(R.id.tv_title_value);
        movieDescTV = view.findViewById(R.id.tv_description_value);
        moviePriceTV = view.findViewById(R.id.tv_description2_value);
        movieAvailabilityTV = view.findViewById(R.id.tv_description3_value);
        movieImageView = view.findViewById(R.id.imageView_collapsingToolbar);
        toolbar = view.findViewById(R.id.toolbar);
    }

    private void populateUI(MovieItemDetails movieItemDetails) {
        toolbar.setTitle(movieItemDetails.getMovieName());
        movieNameTV.setText(movieItemDetails.getMovieName());
        movieDescTV.setText(movieItemDetails.getMovieDescription());
        moviePriceTV.setText(movieItemDetails.getMoviePrice());
        movieAvailabilityTV.setText(String.valueOf(movieItemDetails.isAvailable()));

        Picasso.get().load(movieItemDetails.getImageUrl()).placeholder(R.drawable.place_holder).into(movieImageView);
    }
}