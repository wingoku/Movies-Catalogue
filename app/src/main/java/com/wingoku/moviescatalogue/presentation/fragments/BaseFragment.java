package com.wingoku.moviescatalogue.presentation.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.wingoku.moviescatalogue.R;

public class BaseFragment extends Fragment {
    private Snackbar snackbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initSnackbar(requireActivity().getApplicationContext(), view);
        return view;
    }

    /**
     * This method will return a Snackbar object
     *
     * @param con Activity/Application context
     * @param view View with which {@link Snackbar} will be attached
     */
    protected void initSnackbar(Context con, View view) {
        snackbar = Snackbar.make(requireActivity().findViewById(android.R.id.content), "", Snackbar.LENGTH_LONG);
        snackbar.setAction(con.getString(R.string.ok_string), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.setTextColor(Color.WHITE);
        snackbar.setActionTextColor(Color.GREEN);
    }

    protected void showSnackBar(String message) {
        snackbar.setText(message);
        snackbar.show();
    }
}