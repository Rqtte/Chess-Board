package Pieces;

import javax.swing.ImageIcon;
import java.awt.*;

public class Rook extends Piece {

    ImageIcon rook_white;
    ImageIcon rook_black;
    boolean whit;

    public Rook(boolean white,Point point){

        whit = white;

        rook_white = new ImageIcon("Pieces/ImageIcons/Rook_White.png");
        Image rw = rook_white.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        rook_white = new ImageIcon(rw);

        rook_black = new ImageIcon("Pieces/ImageIcons/Rook_Black.png");
        Image rb = rook_black.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        rook_black = new ImageIcon(rb);

        if(white){
            this.setIcon(rook_white);
        }
        else{
            this.setIcon(rook_black);
        }

        this.setVisible(true);
        this.setSize(103,103);
        this.setOpaque(false);
        this.setLocation(point);

    }
    public boolean getcolour(){
        return(whit);
    }
}
