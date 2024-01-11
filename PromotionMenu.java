import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PromotionMenu extends JLabel implements ActionListener {

    ImageIcon queen_white;
    ImageIcon queen_black;
    ImageIcon knight_white;
    ImageIcon knight_black;
    ImageIcon rook_white;
    ImageIcon rook_black;
    ImageIcon bishop_white;
    ImageIcon bishop_black;
    JPanel Buttons;
    JButton QueenButton;
    JButton RookButton;
    JButton KnightButton;
    JButton BishopButton;
    String Selection;
    PromotionMenu(boolean colour){

        Buttons = new JPanel();
        QueenButton = new JButton();
        RookButton = new JButton();
        KnightButton = new JButton();
        BishopButton = new JButton();
        Selection = "null";



        if(colour){
            bishop_white = new ImageIcon("Pieces/ImageIcons/Bishop_White.png");
            Image bw = bishop_white.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            bishop_white = new ImageIcon(bw);

            BishopButton.setIcon(bishop_white);
            BishopButton.setVisible(true);
            //BishopButton.setOpaque(true);

            knight_white = new ImageIcon("Pieces/ImageIcons/Knight_White.png");
            Image kw = knight_white.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            knight_white = new ImageIcon(kw);

            KnightButton.setIcon(knight_white);
            KnightButton.setVisible(true);
            //KnightButton.setOpaque(true);


            queen_white = new ImageIcon("Pieces/ImageIcons/Queen_White.png");
            Image qw = queen_white.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            queen_white = new ImageIcon(qw);

            QueenButton.setIcon(queen_white);
            QueenButton.setVisible(true);
            //QueenButton.setOpaque(true);


            rook_white = new ImageIcon("Pieces/ImageIcons/Rook_White.png");
            Image rw = rook_white.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            rook_white = new ImageIcon(rw);

            RookButton.setIcon(rook_white);
            RookButton.setVisible(true);
            //RookButton.setOpaque(true);

        }

        if(!colour){
            bishop_black = new ImageIcon("Pieces/ImageIcons/Bishop_Black.png");
            Image bb = bishop_black.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            bishop_black = new ImageIcon(bb);

            BishopButton.setIcon(bishop_black);
            BishopButton.setVisible(true);
            //BishopButton.setOpaque(true);

            knight_black = new ImageIcon("Pieces/ImageIcons/Knight_Black.png");
            Image kb = knight_black.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            knight_black = new ImageIcon(kb);

            KnightButton.setIcon(knight_black);
            KnightButton.setVisible(true);
            //KnightButton.setOpaque(true);

            queen_black = new ImageIcon("Pieces/ImageIcons/Queen_Black.png");
            Image qb = queen_black.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            queen_black = new ImageIcon(qb);

            QueenButton.setIcon(queen_black);
            QueenButton.setVisible(true);
            //QueenButton.setOpaque(true);

            rook_black = new ImageIcon("Pieces/ImageIcons/Rook_Black.png");
            Image rb = rook_black.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            rook_black = new ImageIcon(rb);

            RookButton.setIcon(rook_black);
            RookButton.setVisible(true);
            //RookButton.setOpaque(true);
        }

        this.add(QueenButton);
        this.add(RookButton);
        this.add(KnightButton);
        this.add(BishopButton);


        QueenButton.addActionListener(this);
        QueenButton.setVisible(true);
        RookButton.addActionListener(this);
        RookButton.setVisible(true);
        KnightButton.addActionListener(this);
        KnightButton.setVisible(true);
        BishopButton.addActionListener(this);
        BishopButton.setVisible(true);




        this.setLayout(new GridLayout(1, 4) );
        this.setSize(412, 103);
        this.setVisible(true);
        //this.setOpaque(true);



    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == QueenButton){
            Selection = "Queen";
            this.setVisible(false);
        }

        if(e.getSource() == RookButton){
            Selection = "Rook";
            this.setVisible(false);
        }

        if(e.getSource() == KnightButton){
            Selection = "Knight";
            this.setVisible(false);
        }

        if(e.getSource() == BishopButton){
            Selection = "Bishop";
            this.setVisible(false);
        }
    }

    public String getSelection() {
        return Selection;
    }
}
