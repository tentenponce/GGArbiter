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
                    new Piece("Five Star General", Piece.FIVE_STAR_GENERAL, R.drawable.five_star),
                    new Piece("Four Star General", Piece.FOUR_STAR_GENERAL, R.drawable.four_star),
                    new Piece("Three Star General", Piece.THREE_STAR_GENERAL, R.drawable.three_star),
                    new Piece("Two Star General", Piece.TWO_STAR_GENERAL, R.drawable.two_star),
                    new Piece("One Star General", Piece.ONE_STAR_GENERAL, R.drawable.one_star),
                    new Piece("Colonel", Piece.COLONEL,R.drawable.colonel),
                    new Piece("Lieutenant Colonel", Piece.LIEUTENANT_COLONEL, R.drawable.lt_colonel),
                    new Piece("Major", Piece.MAJOR, R.drawable.major),
                    new Piece("Captain", Piece.CAPTAIN, R.drawable.captain),
                    new Piece("1st Lieutenant", Piece.FIRST_LT, R.drawable.first_lt),
                    new Piece("2nd Lieutenant", Piece.SECOND_LT, R.drawable.second_lt),
                    new Piece("Sergeant", Piece.SERGEANT, R.drawable.sergeant),
                    new Piece("Private", Piece.PRIVATE, R.drawable.private_soldier),
                    new Piece("Spy", Piece.SPY, R.drawable.spy),
                    new Piece("Flag", Piece.FLAG, R.drawable.flag)
            )
    );

    /**
     * Makes the player fight with piece
     * @return the piece wins, if tie it returns null
     */
    public static Piece fightPiece(Piece piece1, Piece piece2) {
        if (piece1.getPosition() == Piece.FLAG && piece2.getPosition() == Piece.FLAG) { //if they are the same flag
            /* return any of the flag, the winner will be the aggressive one */
            return piece1;
            //return piece2;
        }

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
