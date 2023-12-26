package Pieces;

import javax.swing.ImageIcon;
import java.awt.*;

public class Pawn extends Piece {

    ImageIcon pawn_white;
    ImageIcon pawn_black;
    boolean whit;
    boolean EnPassantable;

    public Pawn(boolean white, Point point) {

        whit = white;
        EnPassantable = false;

        pawn_white = new ImageIcon("Pieces/ImageIcons/Pawn_White.png");
        Image pw = pawn_white.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        pawn_white = new ImageIcon(pw);

        pawn_black = new ImageIcon("Pieces/ImageIcons/Pawn_Black.png");
        Image pb = pawn_black.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        pawn_black = new ImageIcon(pb);

        if (white) {
            this.setIcon(pawn_white);
        } else {
            this.setIcon(pawn_black);//
        }

        this.setVisible(true);
        this.setSize(103, 103);
        this.setOpaque(false);
        this.setLocation(point);

    }

    public boolean getcolour() {
        return (whit);
    }

    public void setEnpassantable(boolean bool) {
        EnPassantable = bool;
    }
    public boolean getEnpassantable(){
        return EnPassantable;
    }
}

