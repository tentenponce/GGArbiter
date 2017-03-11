package com.tenten.gameofthegeneralsarbiter.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.tenten.gameofthegeneralsarbiter.utilities.TUtilities;

/**
 * Player model, 1 piece and a name
 * Created by Tenten Ponce on 26/02/2017.
 */

public class Player {
    public static final String DEFAULT_PLAYER_COLOR = "#607D8B";

    private static final String PLAYER_1_NAME_KEY = "player_1_name";
    private static final String PLAYER_2_NAME_KEY = "player_2_name";

    private static final String PLAYER_1_COLOR_KEY = "player_1_color";
    private static final String PLAYER_2_COLOR_KEY = "player_2_color";

    private static final String DEFAULT_PLAYER_1_NAME = "Player 1";
    private static final String DEFAULT_PLAYER_2_NAME = "Player 2";

    private String name;
    private Piece piece;

    public Player (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public static String getPlayer1Name(Context context) {
        return context.getSharedPreferences(TUtilities.GG_PREF, Context.MODE_PRIVATE).getString(PLAYER_1_NAME_KEY, DEFAULT_PLAYER_1_NAME);
    }

    public static void setPlayer1Name(Context context, String name) {
        SharedPreferences.Editor editor = context.getSharedPreferences(TUtilities.GG_PREF, Context.MODE_PRIVATE).edit();

        editor.putString(PLAYER_1_NAME_KEY, name);

        editor.apply();
    }

    public static String getPlayer2Name(Context context) {
        return context.getSharedPreferences(TUtilities.GG_PREF, Context.MODE_PRIVATE).getString(PLAYER_2_NAME_KEY, DEFAULT_PLAYER_2_NAME);
    }

    public static void setPlayer2Name(Context context, String name) {
        SharedPreferences.Editor editor = context.getSharedPreferences(TUtilities.GG_PREF, Context.MODE_PRIVATE).edit();

        editor.putString(PLAYER_2_NAME_KEY, name);

        editor.apply();
    }

    public static String getPlayer1Color(Context context) {
        return context.getSharedPreferences(TUtilities.GG_PREF, Context.MODE_PRIVATE).getString(PLAYER_1_COLOR_KEY, DEFAULT_PLAYER_COLOR);
    }

    public static void setPlayer1Color(Context context, String color) {
        SharedPreferences.Editor editor = context.getSharedPreferences(TUtilities.GG_PREF, Context.MODE_PRIVATE).edit();

        editor.putString(PLAYER_1_COLOR_KEY, color);

        editor.apply();
    }

    public static String getPlayer2Color(Context context) {
        return context.getSharedPreferences(TUtilities.GG_PREF, Context.MODE_PRIVATE).getString(PLAYER_2_COLOR_KEY, DEFAULT_PLAYER_COLOR);
    }

    public static void setPlayer2Color(Context context, String color) {
        SharedPreferences.Editor editor = context.getSharedPreferences(TUtilities.GG_PREF, Context.MODE_PRIVATE).edit();

        editor.putString(PLAYER_2_COLOR_KEY, color);

        editor.apply();
    }
}
