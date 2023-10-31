package Pieces;

import javax.swing.JLabel;
import java.awt.*;

abstract public class Piece extends JLabel{
    
    Point point;

    Piece(){

    }

    //public String getLocation() {
    //    return location;
    //}
    public void move(Point point){
        this.point = point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}