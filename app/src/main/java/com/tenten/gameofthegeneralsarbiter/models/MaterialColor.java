package com.tenten.gameofthegeneralsarbiter.models;

/**
 * color hex code with the name
 * Created by Tenten Ponce on 10/03/2017.
 */

public class MaterialColor {
    private String colorName;
    private String hexColor;

    public MaterialColor(String colorName, String hexColor) {
        this.colorName = colorName;
        this.hexColor = hexColor;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }
}
