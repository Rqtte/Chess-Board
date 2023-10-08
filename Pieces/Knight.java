package Pieces;

import javax.swing.ImageIcon;
import java.awt.*;

public class Knight extends Piece {

    ImageIcon knight_white;
    ImageIcon knight_black;

    public Knight(boolean white,Point point){

        knight_white = new ImageIcon("Pieces/ImageIcons/Knight_White.png");
        Image kw = knight_white.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        knight_white = new ImageIcon(kw);


        knight_black = new ImageIcon("Pieces/ImageIcons/Knight_Black.png");
        Image kb = knight_black.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        knight_black = new ImageIcon(kb);

        if(white){
            this.setIcon(knight_white);
        }
        else{
            this.setIcon(knight_black);
        }

        this.setVisible(true);
        this.setSize(103,103);
        this.setOpaque(false);
        this.setLocation(point);

    }

}

