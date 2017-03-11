package com.tenten.gameofthegeneralsarbiter.fragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tenten.gameofthegeneralsarbiter.R;
import com.tenten.gameofthegeneralsarbiter.dialogs.ColorPickerDialog;
import com.tenten.gameofthegeneralsarbiter.helpers.CustomTextView;
import com.tenten.gameofthegeneralsarbiter.models.MaterialColor;
import com.tenten.gameofthegeneralsarbiter.models.Player;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Makeup for the app
 * Created by Tenten Ponce on 10/03/2017.
 */

public class SettingsFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.coor_settings)
    CoordinatorLayout coor_settings;

    @BindView(R.id.linear_player_1_name)
    LinearLayout linear_player_1_name;

    @BindView(R.id.tv_player_1_name)
    CustomTextView tv_player_1_name;

    @BindView(R.id.linear_player_2_name)
    LinearLayout linear_player_2_name;

    @BindView(R.id.tv_player_2_name)
    CustomTextView tv_player_2_name;

    @BindView(R.id.linear_player_1_color)
    LinearLayout linear_player_1_color;

    @BindView(R.id.linear_player_2_color)
    LinearLayout linear_player_2_color;

    @BindView(R.id.circle_iv_player_1_color)
    CircleImageView circle_iv_player_1_color;

    @BindView(R.id.circle_iv_player_2_color)
    CircleImageView circle_iv_player_2_color;

    private AlertDialog changeNameDialog;
    private AppCompatEditText edit_player_name;
    private boolean isPlayer1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        initViews();
        initDialogs();
    }

    private void initDialogs() {
        LinearLayout linear_parent = new LinearLayout(getContext());
        edit_player_name = new AppCompatEditText(getContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        linear_parent.setPadding(20, 10, 20, 10);

        edit_player_name.setLayoutParams(layoutParams);
        linear_parent.addView(edit_player_name);

        changeNameDialog = new AlertDialog.Builder(getContext())
                .setTitle("Edit Player Name")
                .setView(linear_parent)
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isPlayer1) { //check if the dialog is for player 1's name
                            Player.setPlayer1Name(getContext(), edit_player_name.getText().toString().trim());
                        } else { //otherwise it's for player 2
                            Player.setPlayer2Name(getContext(), edit_player_name.getText().toString().trim());
                        }

                        initDatas();
                    }
                })
                .setNeutralButton("Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isPlayer1) { //check if the dialog is for player 1's name
                            Player.setPlayer1Name(getContext(), Player.DEFAULT_PLAYER_1_NAME);
                        } else { //otherwise it's for player 2
                            Player.setPlayer2Name(getContext(), Player.DEFAULT_PLAYER_2_NAME);
                        }

                        initDatas();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();

    }

    private void initViews() {
        linear_player_1_name.setOnClickListener(this);
        linear_player_2_name.setOnClickListener(this);

        linear_player_1_color.setOnClickListener(this);
        linear_player_2_color.setOnClickListener(this);
        circle_iv_player_1_color.setOnClickListener(this);
        circle_iv_player_2_color.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.linear_player_1_color ||
                view.getId() == R.id.circle_iv_player_1_color) {
            new ColorPickerDialog(getContext(), new ColorPickerDialog.OnColorPickListener() {
                @Override
                public void onColorPick(MaterialColor materialColor) {
                    if (!Player.getPlayer2Color(getContext()).equals(
                            materialColor.getHexColor()
                    )) {
                        Player.setPlayer1Color(getContext(), materialColor.getHexColor());
                    } else {
                        Snackbar.make(coor_settings, "Nope. Same color makes you crazy.", Snackbar.LENGTH_LONG).show();
                    }
                }
            }).show();
        } else if (view.getId() == R.id.linear_player_2_color ||
                view.getId() == R.id.circle_iv_player_2_color) {
            new ColorPickerDialog(getContext(), new ColorPickerDialog.OnColorPickListener() {
                @Override
                public void onColorPick(MaterialColor materialColor) {
                    if (!Player.getPlayer1Color(getContext()).equals(
                            materialColor.getHexColor()
                    )) {
                        Player.setPlayer2Color(getContext(), materialColor.getHexColor());
                    } else {
                        Snackbar.make(coor_settings, "Nope. Same color makes you crazy.", Snackbar.LENGTH_LONG).show();
                    }
                }
            }).show();
        } else if (view.getId() == R.id.linear_player_1_name) {
            isPlayer1 = true;

            edit_player_name.setText(Player.getPlayer1Name(getContext()));
            changeNameDialog.show();
        } else if (view.getId() == R.id.linear_player_2_name) {
            isPlayer1 = false;

            edit_player_name.setText(Player.getPlayer2Name(getContext()));
            changeNameDialog.show();
        }

        initDatas();
    }

    @Override
    public void onResume() {
        super.onResume();

        initDatas();
    }

    private void initDatas() {
        /* get the set colors */
        circle_iv_player_1_color.setBackgroundColor(
                Color.parseColor(Player.getPlayer1Color(getContext()))
        );

        circle_iv_player_2_color.setBackgroundColor(
                Color.parseColor(Player.getPlayer2Color(getContext()))
        );

        /* get the set names */
        tv_player_1_name.setText(Player.getPlayer1Name(getContext()));
        tv_player_2_name.setText(Player.getPlayer2Name(getContext()));
    }
}
