
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Pieces.*;

import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Board extends JFrame implements MouseListener {

    int c;
    ArrayList<Piece> Piece_List;
    ArrayList<ArrayList<JLabel>> squares;
    ArrayList<JLabel> squares2;
    ArrayList<JLabel> top;
    ArrayList<JLabel> bottom;
    ArrayList<JLabel> right;
    ArrayList<JLabel> left;
    JLabel fill1;
    JLabel fill2;
    JLabel fill3;
    JLabel fill4;
    JPanel board_middle;
    JPanel board_top;
    JPanel board_bottom;
    JPanel board_right;
    JPanel board_left;

    JLayeredPane board;

    Component selected_Piece;

    Board(){

        String[] letters = {"A","B","C","D","E","F","G","H"};
        String[] numbers = {"8","7","6","5","4","3","2","1"};

        //Declares the center of the board

        Piece_List = new ArrayList<>();
        squares = new ArrayList<>();
        squares2 = new ArrayList<>();
        board_middle = new JPanel();
        c = 0;
        board_middle.setLayout(new GridLayout(8,8));
        board_middle.setSize(825, 825);


        for(int i=0;i<8;i++){
            ArrayList<JLabel> temp_ArrayList = new ArrayList<>();
            for(int j=0;j<8;j++){
                JLabel templabel = new JLabel();
                squares2.add(templabel);
                temp_ArrayList.add(templabel);
            }
            squares.add(temp_ArrayList);
        }

        for(ArrayList<JLabel> i:squares){
            for(JLabel j:i) {
                j.setSize(50, 50);

                if (c % 2 == 0) {
                    j.setBackground(new Color(231, 221, 219));
                } else {
                    j.setBackground(new Color(59, 46, 42));
                }
                c++;

                j.addMouseListener(this);
                j.setVisible(true);
                j.setOpaque(true);
                board_middle.add(j);
                }
                c++;
            }




        //Declares the top of the Board

        board_top = new JPanel(new GridLayout(1,8));
        fill1 = new JLabel();
        fill2 = new JLabel();
        top = new ArrayList<>();


        for(int i = 0; i<8;i++){
            top.add(new JLabel());
        }

        for(JLabel i:top){
            i.setSize(50, 20);
            i.setText(letters[top.indexOf(i)]);
            i.setHorizontalAlignment(SwingConstants.CENTER);
            i.setVerticalAlignment(SwingConstants.CENTER);
            i.setFont(new Font("Serif", Font.PLAIN, 14));
            //i.setBackground(new Color(71, 42, 32));
        }

        //fill1.setSize(20,20);
        //top.add(0,fill1);
        //fill2.setSize(10,20);
        //top.add(fill2);

        for(JLabel i:top){
            board_top.add(i);
        }




        //Declares the bottom of the Board

        board_bottom = new JPanel(new GridLayout(1,8));
        fill3 = new JLabel();
        fill4 = new JLabel();
        bottom = new ArrayList<>();

        for(int i = 0; i<8;i++){
            bottom.add(new JLabel());
        }

        for(JLabel i:bottom){
            i.setSize(50, 20);
            i.setText(letters[bottom.indexOf(i)]);
            i.setHorizontalAlignment(SwingConstants.CENTER);
            i.setVerticalAlignment(SwingConstants.CENTER);
            i.setFont(new Font("Serif", Font.PLAIN, 14));
            i.setOpaque(true);
            //i.setBackground(new Color(82, 46, 34, 255));
        }

        for(JLabel i:bottom){
            board_bottom.add(i);
        }


        //board_bottom.add(fill4);

        //Declares the right side of the Board

        board_right = new JPanel(new GridLayout(8,1));

        right = new ArrayList<>();


        for(int i = 0; i<8;i++){
            right.add(new JLabel());
        }

        for(JLabel i:right){
            i.setSize(20, 50);
            i.setText(numbers[right.indexOf(i)]);
            i.setFont(new Font("Serif", Font.PLAIN, 14));
            //i.setBackground(new Color(71, 42, 32));
            board_right.add(i);
        }




        //Declares the left side of the Board

        board_left = new JPanel(new GridLayout(8,1));

        left = new ArrayList<>();


        for(int i = 0; i<8;i++){
            left.add(new JLabel());
        }

        for(JLabel i:left){
            i.setSize(20, 50);
            i.setText(numbers[left.indexOf(i)]);
            i.setFont(new Font("Serif", Font.PLAIN, 14));
            //i.setBackground(new Color(82, 46, 34));
            //i.setOpaque(true);
            board_left.add(i);
        }

        //Fuses everything

        board = new JLayeredPane();
        board.add(board_middle);

        this.setSize(870,900);
        this.setLayout(new BorderLayout());
        this.add(board,BorderLayout.CENTER);
        this.add(board_top,BorderLayout.PAGE_START);
        this.add(board_bottom,BorderLayout.PAGE_END);
        this.add(board_right,BorderLayout.LINE_END);
        this.add(board_left,BorderLayout.LINE_START);

        this.setTitle("Chess Board");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);



        //Declares and puts the white pieces (and pawns (who technically are not called pieces (yes I'm a chess nerd, how did you recognize that?))) in the Piece List

        King king_white = new King(true,squares.get(7).get(4).getLocation());
        Piece_List.add(king_white);
        Queen queen_white = new Queen(true,squares.get(7).get(3).getLocation());
        Piece_List.add(queen_white);
        Rook rook_white_1 = new Rook(true,squares.get(7).get(0).getLocation());
        Piece_List.add(rook_white_1);
        Rook rook_white_2 = new Rook(true,squares.get(7).get(7).getLocation());
        Piece_List.add(rook_white_2);
        Bishop dsqBishop_white = new Bishop(true,squares.get(7).get(2).getLocation());
        Piece_List.add(dsqBishop_white);
        Bishop lsqBishop_white = new Bishop(true,squares.get(7).get(5).getLocation());
        Piece_List.add(lsqBishop_white);
        Knight knight_white_1 = new Knight(true,squares.get(7).get(1).getLocation());
        Piece_List.add(knight_white_1);
        Knight knight_white_2 = new Knight(true,squares.get(7).get(6).getLocation());
        Piece_List.add(knight_white_2);

        //Declares and puts the white Pawns in the Piece List

        Pawn a_pawn_white = new Pawn(true,squares.get(6).get(0).getLocation());
        Piece_List.add(a_pawn_white);
        Pawn b_pawn_white = new Pawn(true,squares.get(6).get(1).getLocation());
        Piece_List.add(b_pawn_white);
        Pawn c_pawn_white = new Pawn(true,squares.get(6).get(2).getLocation());
        Piece_List.add(c_pawn_white);
        Pawn d_pawn_white = new Pawn(true,squares.get(6).get(3).getLocation());
        Piece_List.add(d_pawn_white);
        Pawn e_pawn_white = new Pawn(true,squares.get(6).get(4).getLocation());
        Piece_List.add(e_pawn_white);
        Pawn f_pawn_white = new Pawn(true,squares.get(6).get(5).getLocation());
        Piece_List.add(f_pawn_white);
        Pawn g_pawn_white = new Pawn(true,squares.get(6).get(6).getLocation());
        Piece_List.add(g_pawn_white);
        Pawn h_pawn_white = new Pawn(true,squares.get(6).get(7).getLocation());
        Piece_List.add(h_pawn_white);

        //Declares and puts the black pieces in the Piece List

        King king_black = new King(false,squares.get(0).get(4).getLocation());
        Piece_List.add(king_black);
        Queen queen_black = new Queen(false,squares.get(0).get(3).getLocation());
        Piece_List.add(queen_black);
        Rook rook_black_1 = new Rook(false,squares.get(0).get(0).getLocation());
        Piece_List.add(rook_black_1);
        Rook rook_black_2 = new Rook(false,squares.get(0).get(7).getLocation());
        Piece_List.add(rook_black_2);
        Bishop dsqBishop_black = new Bishop(false,squares.get(0).get(5).getLocation());
        Piece_List.add(dsqBishop_black);
        Bishop lsqBishop_black = new Bishop(false,squares.get(0).get(2).getLocation());
        Piece_List.add(lsqBishop_black);
        Knight knight_black_1 = new Knight(false,squares.get(0).get(1).getLocation());
        Piece_List.add(knight_black_1);
        Knight knight_black_2 = new Knight(false,squares.get(0).get(6).getLocation());
        Piece_List.add(knight_black_2);

        //Declares and puts the black Pawns in the Piece List

        Pawn a_pawn_black = new Pawn(false,squares.get(1).get(0).getLocation());
        Piece_List.add(a_pawn_black);
        Pawn b_pawn_black = new Pawn(false,squares.get(1).get(1).getLocation());
        Piece_List.add(b_pawn_black);
        Pawn c_pawn_black = new Pawn(false,squares.get(1).get(2).getLocation());
        Piece_List.add(c_pawn_black);
        Pawn d_pawn_black = new Pawn(false,squares.get(1).get(3).getLocation());
        Piece_List.add(d_pawn_black);
        Pawn e_pawn_black = new Pawn(false,squares.get(1).get(4).getLocation());
        Piece_List.add(e_pawn_black);
        Pawn f_pawn_black = new Pawn(false,squares.get(1).get(5).getLocation());
        Piece_List.add(f_pawn_black);
        Pawn g_pawn_black = new Pawn(false,squares.get(1).get(6).getLocation());
        Piece_List.add(g_pawn_black);
        Pawn h_pawn_black = new Pawn(false,squares.get(1).get(7).getLocation());
        Piece_List.add(h_pawn_black);

        //Actually puts the Pieces on the board and makes then functional

        for(JLabel i:Piece_List){
            board.add(i,JLayeredPane.POPUP_LAYER);
            i.addMouseListener(this);
        }



        
    }
//!squares.contains(e.getComponent())
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!squares2.contains(e.getComponent())){
            if(selected_Piece != e.getComponent()){
                selected_Piece = e.getComponent();
            }
            else{
                selected_Piece = null;
            }
        }
        else{
            selected_Piece.setLocation(e.getComponent().getLocation());


        }
        System.out.println(selected_Piece);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}