package com.tenten.gameofthegeneralsarbiter.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.tenten.gameofthegeneralsarbiter.R;
import com.tenten.gameofthegeneralsarbiter.adapters.PieceAdapter;
import com.tenten.gameofthegeneralsarbiter.helpers.CustomTextView;
import com.tenten.gameofthegeneralsarbiter.models.Gameplay;
import com.tenten.gameofthegeneralsarbiter.models.Piece;
import com.tenten.gameofthegeneralsarbiter.models.Player;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main window to play the GGArbiter
 * Created by Tenten Ponce on 10/03/2017.
 */

public class HomeFragment extends Fragment implements PieceAdapter.OnPieceClickListener {

    private Player player1 = new Player("Player 1");
    private Player player2 = new Player("Player 2");

    private PieceAdapter pieceAdapter;
    private AlertDialog winnerDialog;

    private Toolbar toolbar;

    @BindView(R.id.coor_main)
    CoordinatorLayout coor_main;

    @BindView(R.id.rv_piece)
    RecyclerView rv_piece;

    @BindView(R.id.playerNameTV)
    CustomTextView playerNameTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pieceAdapter = new PieceAdapter(this);
        /*init winner dialog*/
        winnerDialog = new AlertDialog.Builder(getContext())
                .setTitle("Winner")
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        rv_piece.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));

        rv_piece.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view); // item position
                int spanCount = 4;
                int spacing = getResources().getDimensionPixelSize(R.dimen.piece_margin); //spacing between views in grid

                if (position >= 0) {
                    int column = position % spanCount; // item column

                    outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                    if (position < spanCount) { // top edge
                        outRect.top = spacing;
                    }
                    outRect.bottom = spacing; // item bottom
                } else {
                    outRect.left = 0;
                    outRect.right = 0;
                    outRect.top = 0;
                    outRect.bottom = 0;
                }
            }
        });

        rv_piece.setAdapter(pieceAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        updatePlayerTurn();
    }

    @Override
    public void onPieceClick(Piece piece) {
        togglePiecePlay(piece);
    }

    /**
     * Process the selected piece who will be the player
     * to hold it
     *
     * @param piece piece to be processed (Selected piece)
     */
    private void togglePiecePlay(Piece piece) {
        if (player1.getPiece() == null) { //check if player 1 has picked piece
            player1.setPiece(piece); //set the selected piece to player 1
        } else {
            player2.setPiece(piece); //otherwise set the selected piece to player 2

            Piece winPiece = Gameplay.fightPiece(player1.getPiece(), player2.getPiece()); //holds the piece that wins

            String fightMsg;
            if (winPiece == null) { //null means the piece is the same
                fightMsg = "Both Piece Dies";
            } else {
                boolean isFlag = false;
                if (winPiece.getPosition() == Piece.FLAG) { //if the winner is a flag
                    fightMsg = "The aggressive/attacker flag wins."; //message that the attacker wins because the system cannot detect neither the attacker nor the defender
                } else if (winPiece == player1.getPiece()) { //check if the win piece is equals to the player 1 piece
                    fightMsg = Player.getPlayer1Name(getContext()) + " wins.";

                    if (player2.getPiece().getPosition() == Piece.FLAG) {
                        isFlag = true;
                    }
                } else {
                    fightMsg = Player.getPlayer2Name(getContext()) + " wins.";

                    if (player1.getPiece().getPosition() == Piece.FLAG) {
                        isFlag = true;
                    }
                }

                if (isFlag) {
                    fightMsg = "Flag captured. " + fightMsg;
                }
            }

            winnerDialog.setMessage(fightMsg);
            winnerDialog.show();

            resetPlay();
        }

        updatePlayerTurn();
    }

    /**
     * Resets the player's piece they are holding
     */
    private void resetPlay() {
        player1.setPiece(null);
        player2.setPiece(null);
    }

    /**
     * Updates the players turn, who will play
     */
    @SuppressLint("SetTextI18n")
    private void updatePlayerTurn() {
        String color;
        if (player1.getPiece() == null) {
            playerNameTV.setText(Player.getPlayer1Name(getContext()) + " to play.");
            color = Player.getPlayer1Color(getContext());

        } else {
            playerNameTV.setText(Player.getPlayer2Name(getContext()) + " to play.");
            color = Player.getPlayer2Color(getContext());
        }

        toolbar.setBackgroundColor(Color.parseColor(color));
        pieceAdapter.setColor(color);
        pieceAdapter.notifyDataSetChanged();

        Animation flipAnim = new ScaleAnimation(
                1f, -1f, // Start and end values for the X axis scaling
                1f, 1f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, .5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, .5f); // Pivot point of Y scaling
        flipAnim.setDuration(500);
        flipAnim.setRepeatCount(1);
        flipAnim.setRepeatMode(Animation.REVERSE);

        playerNameTV.startAnimation(flipAnim);
    }

    @Override
    public void onPause() {
        super.onPause();

        toolbar.setBackgroundColor(Color.parseColor(
                Player.DEFAULT_PLAYER_COLOR
        ));
    }
}
