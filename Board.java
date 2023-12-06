
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Pieces.Piece;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;


public class Board extends JFrame implements MouseListener {

    int c;
    ArrayList<Piece> Piece_List;
    ArrayList<Piece> Piece_List_W;
    ArrayList<Piece> Piece_List_B;
    ArrayList<ArrayList<Square>>legal_moves;
    ArrayList<ArrayList<Square>> squares;
    ArrayList<Square> squares2;
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
    Boolean Turn_w;

    Board(){

        String[] letters = {"A","B","C","D","E","F","G","H"};
        String[] numbers = {"8","7","6","5","4","3","2","1"};

        //Declares the center of the board

        Piece_List = new ArrayList<>();
        Piece_List_W = new ArrayList<>();
        Piece_List_B = new ArrayList<>();
        squares = new ArrayList<>();
        squares2 = new ArrayList<>();
        board_middle = new JPanel();
        c = 0;
        board_middle.setLayout(new GridLayout(8,8));
        board_middle.setSize(825, 825);
        Turn_w = true;


        for(int i=0;i<8;i++){
            ArrayList<Square> temp_ArrayList = new ArrayList<>();
            for(int j=0;j<8;j++){
                Square tempsquare = new Square();
                squares2.add(tempsquare);
                temp_ArrayList.add(tempsquare);
            }
            squares.add(temp_ArrayList);
        }

        for(ArrayList<Square> i:squares){
            for(Square j:i) {

                j.setColor(c % 2 == 0);
                c++;

                j.addMouseListener(this);
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

        for(Piece i:Piece_List){
            if(i.getcolour()){
                Piece_List_W.add(i);
            }
            else{
                Piece_List_B.add(i);
            }
        }

        legal_moves = analyze_legal_moves();
    }

    public int getArray(Piece piece) {
        int array = -1;
        for(ArrayList<Square> i: squares){
            for (Square j : i) {
                if (Objects.equals(j.getLocation().toString(), piece.getLocation().toString())) {

                    array = squares.indexOf(i);
                }
        }



        }
        return(array);
    }

    public int getIndex(Piece piece){

            int index = -1;
            for(ArrayList<Square> i: squares){
                for (Square j : i) {
                    if (Objects.equals(j.getLocation().toString(), piece.getLocation().toString())) {

                        index = i.indexOf(j);
                    }
                }



            }
            return(index);
        }


        public ArrayList<Square> analyze_straight_moves(Piece piece){
            ArrayList<Square> legal_moves = new ArrayList<>();
        //Downwards
            for (int i = getArray(piece); i < 8; i++) {
                legal_moves.add(squares.get(i).get(getIndex(piece)));
                for (Piece j : Piece_List){
                    if (Objects.equals(j.getLocation().toString(), squares.get(i).get(getIndex(piece)).getLocation().toString())&& j != piece) {
                        legal_moves.remove(legal_moves.size()-1);
                        if (j.getcolour() != piece.getcolour()) {
                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        break;
                    }
                }
            }
            //Upwards
            for (int i = getArray(piece); i > -1; i--) {
                boolean brk = false;
                legal_moves.add(squares.get(i).get(getIndex(piece)));
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(i).get(getIndex(piece)).getLocation().toString())&& j != piece) {
                        legal_moves.remove(legal_moves.size() - 1);
                        if (j.getcolour() != piece.getcolour()) {
                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;

                    }
                }
                if(brk){
                    break;
                }
            }

            //Right
            for (int i = getIndex(piece); i < 8; i++) {
                if(i != -1) {
                    legal_moves.add(squares.get(getArray(piece)).get(i));
                    for (Piece j : Piece_List) {
                        if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece)).get(i).getLocation().toString())&& j != piece) {
                            legal_moves.remove(legal_moves.size() - 1);
                            if (j.getcolour() != piece.getcolour()) {
                                legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                            }
                            break;
                        }
                    }
                }
            }

            //Left
            for (int i = getIndex(piece); i > -1; i--) {
                //if(i != -1) {
                legal_moves.add(squares.get(getArray(piece)).get(i));
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece)).get(i).getLocation().toString())&& j != piece) {
                        legal_moves.remove(legal_moves.size() - 1);
                        if (j.getcolour() != piece.getcolour()) {
                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        break;
                    }
                }
                //}
            }
            return legal_moves;
        }

    public ArrayList<Square> analyze_diagonal_moves(Piece piece) {
        ArrayList<Square> legal_moves = new ArrayList<>();

        // down left
        for (int i = 1; i < 8; i++) {
            boolean brk = false;

            try {
                legal_moves.add(squares.get(getArray(piece) - i).get(getIndex(piece) - i));
                for (Piece j : Piece_List) {

                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece)).get(getIndex(piece)).getLocation().toString())&& j != piece) {
                        System.out.println(j.getcolour());
                        legal_moves.remove(legal_moves.size()-1);

                        if (j.getcolour() != piece.getcolour()) {

                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;


                    }

                }
            } catch (Exception e) {
                break;
            }
            if(brk) {
                break;
            }
        }

        // up right
        for (int i = 1; i < 8; i++) {
            boolean brk = false;

            try {
                legal_moves.add(squares.get(getArray(piece) + i).get(getIndex(piece) + i));
                for (Piece j : Piece_List) {

                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece)).get(getIndex(piece)).getLocation().toString())&& j != piece) {
                        System.out.println(j.getcolour());
                        legal_moves.remove(legal_moves.size()-1);

                        if (j.getcolour() != piece.getcolour()) {

                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;


                    }

                }
            } catch (Exception e) {
                break;
            }
            if(brk) {
                break;
            }
        }

        //down right
        for (int i = 1; i < 8; i++) {
            boolean brk = false;

            try {
                legal_moves.add(squares.get(getArray(piece) + i).get(getIndex(piece) - i));
                for (Piece j : Piece_List) {

                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece)).get(getIndex(piece)).getLocation().toString())&& j != piece) {
                        System.out.println(j.getcolour());
                        legal_moves.remove(legal_moves.size()-1);

                        if (j.getcolour() != piece.getcolour()) {

                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;


                    }

                }
            } catch (Exception e) {
                break;
            }
            if(brk) {
                break;
            }
        }

        //down left
        for (int i = 1; i < 8; i++) {
            boolean brk = false;

            try {
                legal_moves.add(squares.get(getArray(piece) - i).get(getIndex(piece) + i));
                for (Piece j : Piece_List) {

                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece)).get(getIndex(piece)).getLocation().toString())&& j != piece) {
                        System.out.println(j.getcolour());
                        legal_moves.remove(legal_moves.size()-1);

                        if (j.getcolour() != piece.getcolour()) {

                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;


                    }

                }
            } catch (Exception e) {
                break;
            }
            if(brk) {
                break;
            }
        }
        System.out.println(legal_moves);
        return legal_moves;

    }


        public ArrayList<Square> analyze_rook_moves(Rook rook) {
        ArrayList<Square> legal_moves = new ArrayList<>();

        legal_moves = analyze_straight_moves(rook);

        return legal_moves;
    }


    public ArrayList<Square> analyze_pawn_moves(Pawn pawn){
        ArrayList<Square> legal_moves = new ArrayList<>();

        //If pawn is white
        if(pawn.getcolour()) {

            //one square up
            legal_moves.add(squares.get(getArray(pawn)-1).get(getIndex(pawn)));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn)-1).get(getIndex(pawn)).getLocation().toString())) {
                    legal_moves.remove(legal_moves.size()-1);
                    System.out.println(j.getLocation().toString() + "   " + squares.get(getArray(pawn) + 1).get(getIndex(pawn)).getLocation().toString());
                    System.out.println(j);
                }
            }

            //two squares up
            if (getArray(pawn) == 6 && legal_moves.size() == 1) {
                legal_moves.add(squares.get(getArray(pawn) - 2).get(getIndex(pawn)));
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) - 2).get(getIndex(pawn)).getLocation().toString())) {
                        legal_moves.remove(legal_moves.size()-1);
                    }
                }
            }
        }
        //black pawn
        else {

            //one swuare down
            legal_moves.add(squares.get(getArray(pawn) + 1).get(getIndex(pawn)));
            for(Piece j : Piece_List) {

                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) + 1).get(getIndex(pawn)).getLocation().toString())) {
                    legal_moves.remove(legal_moves.size()-1);
                }
                break;
            }

            //two squares down
            if (getArray(pawn) == 1 && legal_moves.size() == 1) {
                legal_moves.add(squares.get(getArray(pawn) + 2).get(getIndex(pawn)));
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) + 2).get(getIndex(pawn)).getLocation().toString())) {
                        legal_moves.remove(legal_moves.size()-1);
                    }
                }
            }
        }
        //diagonal captures is pawn is white
        if(pawn.getcolour()) {
            if(getIndex(pawn)-1 != -1 && getIndex(pawn)-1 != -1) {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) - 1).get(getIndex(pawn) - 1).getLocation().toString())) {
                        if (j.getcolour() != pawn.getcolour()) {
                            legal_moves.add(squares.get(getArray(pawn) - 1).get(getIndex(pawn) - 1));
                        }
                    }
                }
            }
            if(getIndex(pawn)-1 != -1 && getIndex(pawn)+1 != 8) {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) - 1).get(getIndex(pawn) + 1).getLocation().toString())) {
                        if (j.getcolour() != pawn.getcolour()) {
                            legal_moves.add(squares.get(getArray(pawn) - 1).get(getIndex(pawn) + 1));
                        }
                    }
                }
            }
        }
        //diagonal captures is pawn is black
        else {
            if(getIndex(pawn)-1 != +1 && getIndex(pawn)-1 != -1) {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) + 1).get(getIndex(pawn) - 1).getLocation().toString())) {
                        if (j.getcolour() != pawn.getcolour()) {
                            legal_moves.add(squares.get(getArray(pawn) + 1).get(getIndex(pawn) - 1));
                        }
                    }
                }
            }
            if(getIndex(pawn)-1 != +1 && getIndex(pawn)+1 != 8) {
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) + 1).get(getIndex(pawn) + 1).getLocation().toString())) {
                    if (j.getcolour() != pawn.getcolour()) {
                        legal_moves.add(squares.get(getArray(pawn) + 1).get(getIndex(pawn) + 1));
                        }
                    }
                }
            }
        }

        return legal_moves;
    }

    public ArrayList<Square> analyze_bishop_moves(Bishop bishop) {
        ArrayList<Square> legal_moves = new ArrayList<>();

        legal_moves = analyze_diagonal_moves(bishop);

        return legal_moves;
    }

    public ArrayList<Square> analyze_queen_moves(Queen queen){
        ArrayList<Square> legal_moves = new ArrayList<>();


        ArrayList<Square> temp = analyze_diagonal_moves(queen);
        for(Square i : temp){
            legal_moves.add(i);
        }

        temp.clear();
        temp = analyze_straight_moves(queen);
        for(Square i : temp){
            legal_moves.add(i);
        }

        return legal_moves;
    }

    public ArrayList<Square> analyze_knight_moves(Knight knight){
        ArrayList<Square> legal_moves = new ArrayList<>();

        if(getArray(knight)+2 < 8 && getIndex(knight)-1>-1) {
            legal_moves.add(squares.get(getArray(knight) + 2).get(getIndex(knight) - 1));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) + 2).get(getIndex(knight) - 1).getLocation().toString())) {

                    if (knight.getcolour() == j.getcolour()){

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }
            }
        }

        if(getArray(knight)+2 < 8 && getIndex(knight)+1< 8) {
            legal_moves.add(squares.get(getArray(knight) + 2).get(getIndex(knight) + 1));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) + 2).get(getIndex(knight) + 1).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }

            }
        }

        if(getArray(knight)+1 < 8 && getIndex(knight)-2>-1) {
            legal_moves.add(squares.get(getArray(knight) + 1).get(getIndex(knight) - 2));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) + 1).get(getIndex(knight) - 2).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }

            }
        }

        if (!(getArray(knight)+1 >= 8) && !(getIndex(knight)+2 >= 8)) {
            legal_moves.add(squares.get(getArray(knight) + 1).get(getIndex(knight) + 2));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) + 1).get(getIndex(knight) + 2).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }

            }
        }

        if(getArray(knight)-2 > -1 && getIndex(knight)+1<8) {
            legal_moves.add(squares.get(getArray(knight) - 2).get(getIndex(knight) + 1));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) - 2).get(getIndex(knight) + 1).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }
            }
        }

        if(getArray(knight)-2 > -1 && getIndex(knight)-1>-1) {
            legal_moves.add(squares.get(getArray(knight) - 2).get(getIndex(knight) - 1));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) - 2).get(getIndex(knight) - 1).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }

            }
        }

        if(getArray(knight)-1 > -1 && getIndex(knight)+2<8) {
            legal_moves.add(squares.get(getArray(knight) - 1).get(getIndex(knight) + 2));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) - 1).get(getIndex(knight) + 2).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }


            }
        }

        if(getArray(knight)-1 > -1 && getIndex(knight)- 2 > -1) {
            legal_moves.add(squares.get(getArray(knight) - 1).get(getIndex(knight) - 2));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) - 1).get(getIndex(knight) - 2).getLocation().toString()))) {
                    if (knight.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }
            }
        }

        return legal_moves;
    }

    public ArrayList<Square> analyze_king_moves(King king){
        ArrayList<Square> legal_moves = new ArrayList<>();

        if(getArray(king)-1!=-1 && getIndex(king)-1 != -1) {
            legal_moves.add(squares.get(getArray(king) - 1).get(getIndex(king) - 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) - 1).get(getIndex(king) - 1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if(getArray(king)+1 != 8 && getIndex(king) + 1 != 8){
            legal_moves.add(squares.get(getArray(king) + 1).get(getIndex(king) + 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) + 1).get(getIndex(king) +1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }


        if(getArray(king) +1 != 8 && getIndex(king)-1 != -1) {
            legal_moves.add(squares.get(getArray(king) + 1).get(getIndex(king) - 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) + 1).get(getIndex(king) - 1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if(getArray(king)-1 != -1 && getIndex(king) +1 != 8) {
            legal_moves.add(squares.get(getArray(king) - 1).get(getIndex(king) + 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) - 1).get(getIndex(king) + 1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if(getIndex(king) -1 != -1) {
            legal_moves.add(squares.get(getArray(king)).get(getIndex(king) - 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king)).get(getIndex(king) - 1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if(getIndex(king) +1 != 8) {
            legal_moves.add(squares.get(getArray(king)).get(getIndex(king) + 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king)).get(getIndex(king) +1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if(getArray(king)+1 != 8) {
            legal_moves.add(squares.get(getArray(king) + 1).get(getIndex(king)));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) +1).get(getIndex(king) ).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if(getArray(king)-1 != -1 ) {
            legal_moves.add(squares.get(getArray(king) - 1).get(getIndex(king)));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) - 1).get(getIndex(king)).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour()) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        return legal_moves;
    }
    public ArrayList<ArrayList<Square>> analyze_legal_moves(){
        ArrayList<ArrayList<Square>> legal_moves = new ArrayList<>();
        if(Turn_w){
            for(Piece i:Piece_List_W) {
                if (i instanceof Rook) {
                    legal_moves.add(analyze_rook_moves((Rook) i));
                }
                if (i instanceof Pawn) {
                    legal_moves.add(analyze_pawn_moves((Pawn) i));
                }
                if (i instanceof Bishop) {
                    legal_moves.add(analyze_bishop_moves((Bishop) i));
                }
                if (i instanceof Queen) {
                    legal_moves.add(analyze_queen_moves((Queen) i));
                }
                if (i instanceof Knight) {
                    legal_moves.add(analyze_knight_moves((Knight) i));
                }
                if (i instanceof King) {
                    legal_moves.add(analyze_king_moves((King) i));
                }
            }
        }

        else{
            for(Piece i:Piece_List_B){
                if (i instanceof Rook) {
                    legal_moves.add(analyze_rook_moves((Rook) i));
                }
                if (i instanceof Pawn) {
                    legal_moves.add(analyze_pawn_moves((Pawn) i));
                }
                if (i instanceof Bishop) {
                    legal_moves.add(analyze_bishop_moves((Bishop) i));
                }
                if (i instanceof Queen) {
                    legal_moves.add(analyze_queen_moves((Queen) i));
                }
                if (i instanceof Knight) {
                    legal_moves.add(analyze_knight_moves((Knight) i));
                }
                if (i instanceof King) {
                    legal_moves.add(analyze_king_moves((King) i));
                }
            }
        }
        return legal_moves;
    }
    public void select_piece(Piece piece) {
        if (piece.getcolour() == Turn_w) {

            selected_Piece = piece;
        }
    }

    public void capture_or_change_piece(Piece piece){

        if(((Piece) selected_Piece).getcolour() != ((piece).getcolour())){

            boolean p_got_taken = false;

            if(Turn_w && legal_moves.get(Piece_List_W.indexOf(selected_Piece)).contains(squares.get(getArray(piece)).get(getIndex(piece)))) {
                selected_Piece.setLocation(piece.getLocation());
                p_got_taken = true;
            }

            if(!Turn_w && legal_moves.get(Piece_List_B.indexOf(selected_Piece)).contains(squares.get(getArray(piece)).get(getIndex(piece)))) {
                selected_Piece.setLocation(piece.getLocation());
                p_got_taken = true;

            }

            if(p_got_taken) {
                piece.setVisible(false);
                selected_Piece = null;
                Turn_w = !Turn_w;
                legal_moves.clear();
                legal_moves = analyze_legal_moves();
            }
        }
        else{

            selected_Piece = piece;
            System.out.println(selected_Piece);
        }
    }

    public void move(Square square){
        boolean moved = false;
        if(Turn_w && selected_Piece!= null) {
            if (legal_moves.get(Piece_List_W.indexOf(selected_Piece)).contains(square) && Turn_w) {
                selected_Piece.setLocation(square.getLocation());
                selected_Piece = null;
                moved = true;

            }
        }


        if(!Turn_w && selected_Piece!= null) {
            if (legal_moves.get(Piece_List_B.indexOf(selected_Piece)).contains(square) && !Turn_w) {
                selected_Piece.setLocation(square.getLocation());
                selected_Piece = null;
                moved= true;

            }
        }
        if(moved) {
            Turn_w = !Turn_w;
            legal_moves.clear();
            legal_moves = analyze_legal_moves();
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        System.out.println(selected_Piece);
        if(e.getComponent() instanceof Piece){
            if(selected_Piece != e.getComponent()) {
                if (selected_Piece == null) {
                    select_piece((Piece)e.getComponent());
                }
                else {
                    capture_or_change_piece((Piece) e.getComponent());
                }
            }
            else{
                selected_Piece = null;
            }
        }
        else{
            move((Square)e.getComponent());


        }
        System.out.println(selected_Piece);


    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}