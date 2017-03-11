package com.tenten.gameofthegeneralsarbiter.models;

import com.tenten.gameofthegeneralsarbiter.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Holds the Gameplay logic of Game of the Generals
 * Created by Tenten Ponce on 26/02/2017.
 */

public class Gameplay {

    public static final ArrayList<Piece> PIECES = new ArrayList<>(
            Arrays.asList(
                    new Piece(0, "Five Star General", Piece.FIVE_STAR_GENERAL, R.drawable.five_star),
                    new Piece(0, "Four Star General", Piece.FOUR_STAR_GENERAL, R.drawable.four_star),
                    new Piece(0, "Three Star General", Piece.THREE_STAR_GENERAL, R.drawable.three_star),
                    new Piece(0, "Two Star General", Piece.TWO_STAR_GENERAL, R.drawable.two_star),
                    new Piece(0, "One Star General", Piece.ONE_STAR_GENERAL, R.drawable.one_star),
                    new Piece(0, "Colonel", Piece.COLONEL,R.drawable.colonel),
                    new Piece(0, "Lieutenant Colonel", Piece.LIEUTENANT_COLONEL, R.drawable.lt_colonel),
                    new Piece(0, "Major", Piece.MAJOR, R.drawable.major),
                    new Piece(0, "Captain", Piece.CAPTAIN, R.drawable.captain),
                    new Piece(0, "1st Lieutenant", Piece.FIRST_LT, R.drawable.first_lt),
                    new Piece(0, "2nd Lieutenant", Piece.SECOND_LT, R.drawable.second_lt),
                    new Piece(0, "Sergeant", Piece.SERGEANT, R.drawable.sergeant),
                    new Piece(0, "Private", Piece.PRIVATE, R.drawable.private_soldier),
                    new Piece(0, "Spy", Piece.SPY, R.drawable.spy),
                    new Piece(0, "Flag", Piece.FLAG, R.drawable.flag)
            )
    );

    /**
     * Makes the player fight with piece
     * @return the piece wins, if tie it returns null
     */
    public static Piece fightPiece(Piece piece1, Piece piece2) {
        if (piece1.getPosition() == piece2.getPosition()) { //check if their position is the same
            return null; //no one wins
        }

        if (piece1.getPosition() == Piece.SPY) { //check if the piece 1 is spy
            if (piece2.getPosition() <= Piece.FIVE_STAR_GENERAL && piece2.getPosition() != Piece.PRIVATE) { //if the 2nd piece is equal or less than 5 star general AND not a private
                return piece1; //piece 1 is a winner
            } else {
                return piece2; //otherwise, piece2 is a private
            }
        } else if (piece1.getPosition() == Piece.PRIVATE) { //check if the piece is private
            if (piece2.getPosition() == Piece.SPY || piece2.getPosition() == Piece.FLAG) { //if the 2nd piece is spy OR flag, then it is beaten
                return piece1; //piece1 wins because private vs spy
            } else {
                return piece2; //otherwise, private is defeated
            }
        }

        //now check the 2nd piece
        if (piece2.getPosition() == Piece.SPY) { //check if the piece 1 is spy
            if (piece1.getPosition() <= Piece.FIVE_STAR_GENERAL && piece1.getPosition() != Piece.PRIVATE) { //if the 2nd piece is equal or less than 5 star general AND not a private
                return piece2; //piece 2 is a winner
            } else {
                return piece1; //otherwise, piece2 is a private
            }
        } else if (piece2.getPosition() == Piece.PRIVATE) { //check if the piece is private
            if (piece1.getPosition() == Piece.SPY || piece1.getPosition() == Piece.FLAG) { //if the 2nd piece is spy OR flag, then it is beaten
                return piece2; //piece1 wins because private vs spy
            } else {
                return piece1; //otherwise, private is defeated
            }
        }

        //if it is not spy or private, compare easily who's the winner base on ranking
        if (piece1.getPosition() > piece2.getPosition()) {
            return piece1;
        } else {
            return piece2;
        }
    }
}
