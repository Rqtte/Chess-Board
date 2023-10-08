package Pieces;

import javax.swing.ImageIcon;
import java.awt.*;

public class King extends Piece {

    ImageIcon king_white;
    ImageIcon king_black;

    public King(boolean white,Point point){

        king_white = new ImageIcon("Pieces/ImageIcons/King_White.png");
        Image kw = king_white.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        king_white = new ImageIcon(kw);

        king_black = new ImageIcon("Pieces/ImageIcons/King_Black.png");
        Image kb = king_black.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        king_black = new ImageIcon(kb);

        if(white){
            this.setIcon(king_white);
        }
        else{
            this.setIcon(king_black);//
        }

        this.setVisible(true);
        this.setSize(103,103);
        this.setOpaque(false);
        this.setLocation(point);

    }


}
