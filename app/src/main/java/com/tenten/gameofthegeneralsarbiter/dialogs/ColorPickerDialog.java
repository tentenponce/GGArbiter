package com.tenten.gameofthegeneralsarbiter.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tenten.gameofthegeneralsarbiter.R;
import com.tenten.gameofthegeneralsarbiter.models.MaterialColor;

import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Displays list of colors to choose from
 * Created by Tenten Ponce on 10/03/2017.
 */

public class ColorPickerDialog extends AlertDialog {

    private static final ArrayList<MaterialColor> MATERIAL_COLORS = new ArrayList<>(
            Arrays.asList(
                    new MaterialColor("Red", "#F44336"),
                    new MaterialColor("Pink", "#E91E63"),
                    new MaterialColor("Purple", "#9C27B0"),
                    new MaterialColor("Indigo", "#3F51B5"),
                    new MaterialColor("Blue", "#2196F3"),
                    new MaterialColor("Green", "#4CAF50"),
                    new MaterialColor("Amber", "#FFC107"),
                    new MaterialColor("Orange", "#FF9800"),
                    new MaterialColor("Brown", "#795548"),
                    new MaterialColor("Grey", "#9E9E9E"),
                    new MaterialColor("Blue Grey", "#607D8B")
            ));

    public ColorPickerDialog(@NonNull Context context, final OnColorPickListener onColorPickListener) {
        super(context);

        setTitle("Pick Color");

        LinearLayout linear_parent = new LinearLayout(context);
        linear_parent.setOrientation(LinearLayout.VERTICAL);
        linear_parent.setPadding(20, 10, 20, 10);

        LinearLayout linear_color_parent = new LinearLayout(context); //initially setup first row
        linear_color_parent.setGravity(Gravity.CENTER_HORIZONTAL);
        for (final MaterialColor materialColor : MATERIAL_COLORS) {
            /* Create the color to choose from */
            LinearLayout linear_color = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_color, linear_color_parent, false);
            CircleImageView circle_color = (CircleImageView) linear_color.findViewById(R.id.circle_color);

            circle_color.setBackgroundColor(Color.parseColor(
                    materialColor.getHexColor()
            ));

            linear_color.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onColorPickListener.onColorPick(materialColor);

                    dismiss();
                }
            });

            linear_color_parent.addView(linear_color); //add it to the row

            int i = MATERIAL_COLORS.indexOf(materialColor); //get the index of this instance of material color

            if ((i != 0 && (i + 1) % 4 == 0) || //check if it is divisible by 4 and not the first index
                    i == MATERIAL_COLORS.size() - 1) { //or if this was the last index

                linear_parent.addView(linear_color_parent); //add to the main view
                linear_color_parent = new LinearLayout(context); //create new row
                linear_color_parent.setGravity(Gravity.CENTER_HORIZONTAL);
            }
        }

        setView(linear_parent);
    }

    public interface OnColorPickListener {
        void onColorPick(MaterialColor materialColor);
    }
}
