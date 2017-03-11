package com.tenten.gameofthegeneralsarbiter.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tenten.gameofthegeneralsarbiter.R;
import com.tenten.gameofthegeneralsarbiter.helpers.CustomTextView;
import com.tenten.gameofthegeneralsarbiter.models.Piece;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.tenten.gameofthegeneralsarbiter.models.Gameplay.PIECES;

/**
 * displays the data of each piece
 * Created by Tenten Ponce on 25/02/2017.
 */

public class PieceAdapter extends RecyclerView.Adapter<PieceAdapter.PieceHolder> {

    private OnPieceClickListener onPieceClickListener;
    private String color;

    public interface OnPieceClickListener {
        void onPieceClick(Piece piece);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public PieceAdapter(OnPieceClickListener onPieceClickListener) {
        this.onPieceClickListener = onPieceClickListener;
    }

    @Override
    public PieceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_piece, parent, false);

        return new PieceHolder(v);
    }

    @Override
    public void onBindViewHolder(PieceHolder holder, int position) {
        final Piece piece = PIECES.get(position);

        holder.iv_piece.setImageResource(piece.getIcon());
        holder.iv_piece.setBorderColor(Color.parseColor(color));
        holder.tv_piece.setText(piece.getName());
        holder.linear_piece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPieceClickListener.onPieceClick(piece);
            }
        });
    }

    @Override
    public int getItemCount() {
        return PIECES.size();
    }

    class PieceHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.linear_piece)
        LinearLayout linear_piece;

        @BindView(R.id.iv_piece)
        CircleImageView iv_piece;

        @BindView(R.id.tv_piece)
        CustomTextView tv_piece;

        PieceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}