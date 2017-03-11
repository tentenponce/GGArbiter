package com.tenten.gameofthegeneralsarbiter.models;

/**
 * detail of pieces
 * Created by Tenten Ponce on 25/02/2017.
 */

public class Piece {

    public static final int SPY = 13;
    public static final int FIVE_STAR_GENERAL = 12;
    public static final int FOUR_STAR_GENERAL = 11;
    public static final int THREE_STAR_GENERAL = 10;
    public static final int TWO_STAR_GENERAL = 9;
    public static final int ONE_STAR_GENERAL = 8;
    public static final int COLONEL = 7;
    public static final int LIEUTENANT_COLONEL = 6;
    public static final int MAJOR = 5;
    public static final int CAPTAIN = 4;
    public static final int FIRST_LT = 3;
    public static final int SECOND_LT = 2;
    public static final int SERGEANT = 1;
    public static final int PRIVATE = 0;
    public static final int FLAG = -1;

    private int image;
    private String name;
    private int position;
    private int icon;

    public Piece(int image, String name, int position, int icon) {
        this.image = image;
        this.name = name;
        this.position = position;
        this.icon = icon;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
