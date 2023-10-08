package Pieces;

import javax.swing.ImageIcon;
import java.awt.*;

public class Queen extends Piece {

    ImageIcon queen_white;
    ImageIcon queen_black;

    public Queen(boolean white, Point point){

        queen_white = new ImageIcon("Pieces/ImageIcons/Queen_White.png");
        Image qw = queen_white.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        queen_white = new ImageIcon(qw);

        queen_black = new ImageIcon("Pieces/ImageIcons/Queen_Black.png");
        Image qb = queen_black.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        queen_black = new ImageIcon(qb);

        if(white){
            this.setIcon(queen_white);
        }
        else{
            this.setIcon(queen_black);//
        }

        this.setVisible(true);
        this.setSize(103,103);
        this.setOpaque(false);
        this.setLocation(point);

    }
}

