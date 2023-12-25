import Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Square extends JLabel {
    boolean white;
    Piece Occupied;
    Square(){

        this.setSize(50,50);
        this.setVisible(true);
        this.setOpaque(true);;

    }
    public void setOccupation(Piece piece){
        this.Occupied = piece;
    }

    public void setColor(boolean white){
        if(white){
            this.setBackground(new Color(220, 220, 220));
        }
        else {
            this.setBackground(new Color(59, 46, 42));
        }
    }



}


