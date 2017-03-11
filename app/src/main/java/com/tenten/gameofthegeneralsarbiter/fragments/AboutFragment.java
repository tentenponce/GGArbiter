package com.tenten.gameofthegeneralsarbiter.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tenten.gameofthegeneralsarbiter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * About the application
 * Created by Tenten Ponce on 11/03/2017.
 */

public class AboutFragment extends Fragment {

    private static final String TCORNER = "https://www.facebook.com/tencorner/";

    @BindView(R.id.coor_about)
    CoordinatorLayout coor_about;

    @BindView(R.id.linear_developer)
    LinearLayout linear_developer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        linear_developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(TCORNER));
                    startActivity(browserIntent);
                } catch (ActivityNotFoundException e) {
                    Snackbar.make(coor_about, "Please try again later.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}
