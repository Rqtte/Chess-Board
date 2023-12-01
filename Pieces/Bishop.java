package Pieces;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;


public class Bishop extends Piece {


    ImageIcon bishop_white;
    ImageIcon bishop_black;
    boolean whit;


    public Bishop(boolean white, Point point){

        whit = white;

        bishop_white = new ImageIcon("Pieces/ImageIcons/Bishop_White.png");
        Image bw = bishop_white.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        bishop_white = new ImageIcon(bw);

        bishop_black = new ImageIcon("Pieces/ImageIcons/Bishop_Black.png");
        Image bb = bishop_black.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        bishop_black = new ImageIcon(bb);

        if(white){
            this.setIcon(bishop_white);
        }
        else{
            this.setIcon(bishop_black);//
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