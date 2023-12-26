package Pieces;

import javax.swing.ImageIcon;
import java.awt.*;

public class King extends Piece {

    ImageIcon king_white;
    ImageIcon king_black;
    boolean whit;
    boolean moved;

     public King(boolean white,Point point){
         whit = white;
         moved = false;

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
    //@Override
    //public void setPoint(Point point) {
    //    this.point = point;
    public boolean getcolour(){
        return(whit);
    }
    public void Moved(){
        moved = true;
    }
    public boolean getMoved(){
        return moved;
    }

}
