
import javax.swing.*;

import Pieces.Piece;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;


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
    ArrayList<ArrayList<Square>> legal_moves;
    ArrayList<ArrayList<Square>> Unsafe_Squares_for_King;
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
    Boolean Check;
    Boolean CastlingPossible;
    ArrayList<Pawn> EnPassantables;
    ArrayList<Pawn> NoLongerEnPassantable;
    int FiftyMoveDrawCount;
    boolean ColourWithWhichFiftyMoveCountReset;
    ArrayList<String> ReachedPositions;
    ArrayList<Integer> NumberOfTimesPositionWasReached;
    PromotionMenu PromotionMenu;
    Piece PromotedPiece;

    Board() {

        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] numbers = {"8", "7", "6", "5", "4", "3", "2", "1"};

        //Declares the center of the board

        ReachedPositions = new ArrayList<>();
        NumberOfTimesPositionWasReached = new ArrayList<>();
        ColourWithWhichFiftyMoveCountReset = true;
        EnPassantables = new ArrayList<>();
        NoLongerEnPassantable = new ArrayList<>();
        CastlingPossible = false;
        Piece_List = new ArrayList<>();
        Piece_List_W = new ArrayList<>();
        Piece_List_B = new ArrayList<>();
        squares = new ArrayList<>();
        squares2 = new ArrayList<>();
        board_middle = new JPanel();
        c = 0;
        board_middle.setLayout(new GridLayout(8, 8));
        board_middle.setSize(825, 825);
        Turn_w = true;
        Check = false;
        FiftyMoveDrawCount = 0;


        for (int i = 0; i < 8; i++) {
            ArrayList<Square> temp_ArrayList = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                Square tempsquare = new Square();
                squares2.add(tempsquare);
                temp_ArrayList.add(tempsquare);
            }
            squares.add(temp_ArrayList);
        }

        for (ArrayList<Square> i : squares) {
            for (Square j : i) {

                j.setColor(c % 2 == 0);
                c++;

                j.addMouseListener(this);
                board_middle.add(j);
            }
            c++;
        }


        //Declares the top of the Board

        board_top = new JPanel(new GridLayout(1, 8));
        fill1 = new JLabel();
        fill2 = new JLabel();
        top = new ArrayList<>();


        for (int i = 0; i < 8; i++) {
            top.add(new JLabel());
        }

        for (JLabel i : top) {
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

        for (JLabel i : top) {
            board_top.add(i);
        }


        //Declares the bottom of the Board

        board_bottom = new JPanel(new GridLayout(1, 8));
        fill3 = new JLabel();
        fill4 = new JLabel();
        bottom = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            bottom.add(new JLabel());
        }

        for (JLabel i : bottom) {
            i.setSize(50, 20);
            i.setText(letters[bottom.indexOf(i)]);
            i.setHorizontalAlignment(SwingConstants.CENTER);
            i.setVerticalAlignment(SwingConstants.CENTER);
            i.setFont(new Font("Serif", Font.PLAIN, 14));
            i.setOpaque(true);
            //i.setBackground(new Color(82, 46, 34, 255));
        }

        for (JLabel i : bottom) {
            board_bottom.add(i);
        }


        //board_bottom.add(fill4);

        //Declares the right side of the Board

        board_right = new JPanel(new GridLayout(8, 1));

        right = new ArrayList<>();


        for (int i = 0; i < 8; i++) {
            right.add(new JLabel());
        }

        for (JLabel i : right) {
            i.setSize(20, 50);
            i.setText(numbers[right.indexOf(i)]);
            i.setFont(new Font("Serif", Font.PLAIN, 14));
            //i.setBackground(new Color(71, 42, 32));
            board_right.add(i);
        }


        //Declares the left side of the Board

        board_left = new JPanel(new GridLayout(8, 1));

        left = new ArrayList<>();


        for (int i = 0; i < 8; i++) {
            left.add(new JLabel());
        }

        for (JLabel i : left) {
            i.setSize(20, 50);
            i.setText(numbers[left.indexOf(i)]);
            i.setFont(new Font("Serif", Font.PLAIN, 14));
            board_left.add(i);
        }

        //Fuses everything

        board = new JLayeredPane();
        board.add(board_middle);

        this.setSize(870, 900);
        this.setLayout(new BorderLayout());
        this.add(board, BorderLayout.CENTER);
        this.add(board_top, BorderLayout.PAGE_START);
        this.add(board_bottom, BorderLayout.PAGE_END);
        this.add(board_right, BorderLayout.LINE_END);
        this.add(board_left, BorderLayout.LINE_START);

        this.setTitle("Chess Board");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


        //Declares and puts the white pieces (and pawns (who technically are not called pieces (yes I'm a chess nerd, how did you recognize that?))) in the Piece List

        King king_white = new King(true, squares.get(7).get(4).getLocation());
        Piece_List.add(king_white);
        Queen queen_white = new Queen(true, squares.get(7).get(3).getLocation());
        Piece_List.add(queen_white);
        Rook rook_white_1 = new Rook(true, squares.get(7).get(0).getLocation());
        Piece_List.add(rook_white_1);
        Rook rook_white_2 = new Rook(true, squares.get(7).get(7).getLocation());
        Piece_List.add(rook_white_2);
        Bishop dsqBishop_white = new Bishop(true, squares.get(7).get(2).getLocation());
        Piece_List.add(dsqBishop_white);
        Bishop lsqBishop_white = new Bishop(true, squares.get(7).get(5).getLocation());
        Piece_List.add(lsqBishop_white);
        Knight knight_white_1 = new Knight(true, squares.get(7).get(1).getLocation());
        Piece_List.add(knight_white_1);
        Knight knight_white_2 = new Knight(true, squares.get(7).get(6).getLocation());
        Piece_List.add(knight_white_2);

        //Declares and puts the white Pawns in the Piece List

        Pawn a_pawn_white = new Pawn(true, squares.get(6).get(0).getLocation());
        Piece_List.add(a_pawn_white);
        Pawn b_pawn_white = new Pawn(true, squares.get(6).get(1).getLocation());
        Piece_List.add(b_pawn_white);
        Pawn c_pawn_white = new Pawn(true, squares.get(6).get(2).getLocation());
        Piece_List.add(c_pawn_white);
        Pawn d_pawn_white = new Pawn(true, squares.get(6).get(3).getLocation());
        Piece_List.add(d_pawn_white);
        Pawn e_pawn_white = new Pawn(true, squares.get(6).get(4).getLocation());
        Piece_List.add(e_pawn_white);
        Pawn f_pawn_white = new Pawn(true, squares.get(6).get(5).getLocation());
        Piece_List.add(f_pawn_white);
        Pawn g_pawn_white = new Pawn(true, squares.get(6).get(6).getLocation());
        Piece_List.add(g_pawn_white);
        Pawn h_pawn_white = new Pawn(true, squares.get(6).get(7).getLocation());
        Piece_List.add(h_pawn_white);

        //Declares and puts the black pieces in the Piece List

        King king_black = new King(false, squares.get(0).get(4).getLocation());
        Piece_List.add(king_black);
        Queen queen_black = new Queen(false, squares.get(0).get(3).getLocation());
        Piece_List.add(queen_black);
        Rook rook_black_1 = new Rook(false, squares.get(0).get(0).getLocation());
        Piece_List.add(rook_black_1);
        Rook rook_black_2 = new Rook(false, squares.get(0).get(7).getLocation());
        Piece_List.add(rook_black_2);
        Bishop dsqBishop_black = new Bishop(false, squares.get(0).get(5).getLocation());
        Piece_List.add(dsqBishop_black);
        Bishop lsqBishop_black = new Bishop(false, squares.get(0).get(2).getLocation());
        Piece_List.add(lsqBishop_black);
        Knight knight_black_1 = new Knight(false, squares.get(0).get(1).getLocation());
        Piece_List.add(knight_black_1);
        Knight knight_black_2 = new Knight(false, squares.get(0).get(6).getLocation());
        Piece_List.add(knight_black_2);

        //Declares and puts the black Pawns in the Piece List

        Pawn a_pawn_black = new Pawn(false, squares.get(1).get(0).getLocation());
        Piece_List.add(a_pawn_black);
        Pawn b_pawn_black = new Pawn(false, squares.get(1).get(1).getLocation());
        Piece_List.add(b_pawn_black);
        Pawn c_pawn_black = new Pawn(false, squares.get(1).get(2).getLocation());
        Piece_List.add(c_pawn_black);
        Pawn d_pawn_black = new Pawn(false, squares.get(1).get(3).getLocation());
        Piece_List.add(d_pawn_black);
        Pawn e_pawn_black = new Pawn(false, squares.get(1).get(4).getLocation());
        Piece_List.add(e_pawn_black);
        Pawn f_pawn_black = new Pawn(false, squares.get(1).get(5).getLocation());
        Piece_List.add(f_pawn_black);
        Pawn g_pawn_black = new Pawn(false, squares.get(1).get(6).getLocation());
        Piece_List.add(g_pawn_black);
        Pawn h_pawn_black = new Pawn(false, squares.get(1).get(7).getLocation());
        Piece_List.add(h_pawn_black);

        //Actually puts the Pieces on the board and makes then functional

        for (JLabel i : Piece_List) {
            board.add(i, JLayeredPane.POPUP_LAYER);
            i.addMouseListener(this);
        }

        for (Piece i : Piece_List) {
            if (i.getcolour()) {
                Piece_List_W.add(i);
            } else {
                Piece_List_B.add(i);
            }
        }

        legal_moves = analyze_legal_moves(false);
        ProcessFEN();
    }

    public int getArray(Piece piece) {
        int array = -1;
        for (ArrayList<Square> i : squares) {
            for (Square j : i) {
                if (Objects.equals(j.getLocation().toString(), piece.getLocation().toString())) {

                    array = squares.indexOf(i);
                }
            }


        }
        return (array);
    }

    public int getArray(Square square) {
        int array = -1;
        for (ArrayList<Square> i : squares) {
            if (i.contains(square)) {
                array = squares.indexOf(i);
            }

        }
        return (array);
    }

    public int getIndex(Piece piece) {

        int index = -1;
        for (ArrayList<Square> i : squares) {
            for (Square j : i) {
                if (Objects.equals(j.getLocation().toString(), piece.getLocation().toString())) {

                    index = i.indexOf(j);
                }
            }


        }
        return (index);
    }

    public int getIndex(Square square) {
        int index = -1;
        for (ArrayList<Square> i : squares) {
            if (i.contains(square)) {
                index = i.indexOf(square);
            }

        }
        return (index);
    }

    public Piece getPieceOnSquare(int Array, int Index){
        for(Piece i:Piece_List){
            if(i.isVisible()) {
                if (getArray(i) == Array && getIndex(i) == Index){
                    return i;
                }
            }
        }
        return null;
    }

    public void Promotion(){

        PromotedPiece = (Piece)selected_Piece;
        PromotionMenu = new PromotionMenu(Turn_w);
        PromotionMenu.setLocation(board.getWidth()/4-4,board.getHeight()/2-50);
        board.add(PromotionMenu, JLayeredPane.DRAG_LAYER);
        PromotionMenu.setVisible(true);
        board.repaint();
        PromotionMenu.addMouseListener(this);
        PromotionMenu.QueenButton.addMouseListener(this);
        PromotionMenu.RookButton.addMouseListener(this);
        PromotionMenu.KnightButton.addMouseListener(this);
        PromotionMenu.BishopButton.addMouseListener(this);


        //PromotionMenu  = new PromotionMenu(Turn_w);
        //board.add(PromotionMenu, JLayeredPane.DRAG_LAYER);
        //PromotionMenu.setOpaque(true);
        //PromotionMenu.setLocation(board.getWidth()/4-4,board.getHeight()/2-50);
        //PromotionMenu.setVisible(true);

    }


    public String getFEN(){
        String FEN = "";
        int BlankSquares = 0;

        for(int i=0;i<squares.size();i++){
            if(i!=0){
                FEN = FEN+"/";
            }
            for(int j=0;j<squares.get(i).size();j++){
                if(getPieceOnSquare(i,j) == null){
                    BlankSquares++;
                }
                else{
                    if(BlankSquares !=0) {
                        FEN = FEN + BlankSquares;
                        BlankSquares = 0;
                    }


                    if(getPieceOnSquare(i,j) instanceof Pawn){
                        if(getPieceOnSquare(i,j).getcolour()){
                            FEN=FEN+"P";
                        }
                        else{
                            FEN=FEN+"p";
                        }
                    }

                    if(getPieceOnSquare(i,j) instanceof King){
                        if(getPieceOnSquare(i,j).getcolour()){
                            FEN=FEN+"K";
                        }
                        else{
                            FEN=FEN+"k";
                        }
                    }

                    if(getPieceOnSquare(i,j) instanceof Queen){
                        if(getPieceOnSquare(i,j).getcolour()){
                            FEN=FEN+"Q";
                        }
                        else{
                            FEN=FEN+"q";
                        }
                    }
                    if(getPieceOnSquare(i,j) instanceof Rook){
                        if(getPieceOnSquare(i,j).getcolour()){
                            FEN=FEN+"R";
                        }
                        else{
                            FEN=FEN+"r";
                        }
                    }

                    if(getPieceOnSquare(i,j) instanceof Knight){
                        if(getPieceOnSquare(i,j).getcolour()){
                            FEN=FEN+"N";
                        }
                        else{
                            FEN=FEN+"n";
                        }
                    }

                    if(getPieceOnSquare(i,j) instanceof Bishop){
                        if(getPieceOnSquare(i,j).getcolour()){
                            FEN=FEN+"B";
                        }
                        else{
                            FEN=FEN+"b";
                        }
                    }



                }

            }
            if(BlankSquares != 0){
                FEN = FEN + BlankSquares;
                BlankSquares = 0;
            }



        }

        if(Turn_w){
            FEN = FEN + " w";

        }

        else{
            FEN = FEN + " b";
        }

        return FEN;
    }

    public void ProcessFEN(){
        String FEN = getFEN();
        if(ReachedPositions.contains(FEN)){
            NumberOfTimesPositionWasReached.set(ReachedPositions.indexOf(FEN),NumberOfTimesPositionWasReached.get(ReachedPositions.indexOf(FEN)) + 1);
            if(NumberOfTimesPositionWasReached.get(ReachedPositions.indexOf(FEN)) == 3){
                ThreefoldRepetition();
            }
        }
        else {
            ReachedPositions.add(FEN);
            NumberOfTimesPositionWasReached.add(1);
        }
    }
    public void CheckPinsBlocks() {
        if (Turn_w) {
            for (Piece i : Piece_List_W) {
                if (!(i instanceof King)) {
                    try {
                        for (int j = 0; j < legal_moves.get(Piece_List_W.indexOf(i)).size(); j++) {
                            if (!analyze_future_moves(i, getIndex(i), getArray(i), getIndex(legal_moves.get(Piece_List_W.indexOf(i)).get(j)), getArray(legal_moves.get(Piece_List_W.indexOf(i)).get(j)))) {
                                legal_moves.get(Piece_List_W.indexOf(i)).remove(j);
                                j--;
                            }

                        }
                    } catch (IndexOutOfBoundsException ignored) {

                    }
                }
            }
        }


        if (!Turn_w) {
            for (Piece i : Piece_List_B) {
                if (!(i instanceof King)) {
                    try {
                        for (int j = 0; j < legal_moves.get(Piece_List_B.indexOf(i)).size(); j++) {
                            if (!analyze_future_moves(i, getIndex(i), getArray(i), getIndex(legal_moves.get(Piece_List_B.indexOf(i)).get(j)), getArray(legal_moves.get(Piece_List_B.indexOf(i)).get(j)))) {
                                legal_moves.get(Piece_List_B.indexOf(i)).remove(j);
                                j--;
                            }
                        }
                    } catch (IndexOutOfBoundsException ignored) {

                    }
                }
            }
        }
    }

    public void Check_for_Checks() {
        boolean brk = false;
        King king = null;
        Unsafe_Squares_for_King = analyze_legal_moves(true);
        ArrayList<Square> Unsafe_Squares_for_King_1d = convert_to_1d(Unsafe_Squares_for_King);

        if (Turn_w) {
            king = (King) Piece_List_W.get(0);
        } else {
            king = (King) Piece_List_B.get(0);
        }

        for (Square square : Unsafe_Squares_for_King_1d) {
            for (int j = 0; j < legal_moves.get(0).size(); j++) {
                if (getArray(square) == getArray(legal_moves.get(0).get(j)) && getIndex(square) == getIndex(legal_moves.get(0).get(j))) {
                    legal_moves.get(0).remove(j);
                    j--;
                }
            }
        }

        for (Square i : Unsafe_Squares_for_King_1d) {
            if (Objects.equals(i.getLocation().toString(), king.getLocation().toString())) {
                Check = true;
                System.out.println("CHECK!");
                Check_Checkmate();
                brk = true;
                break;

            }
            if (brk) {
                break;
            }
        }
        if (!Check) {
            if (KingSideCastlingPossible(king.getcolour())) {
                if (king.getcolour()) {
                    legal_moves.get(0).add(squares.get(7).get(6));
                } else {
                    legal_moves.get(0).add(squares.get(0).get(6));
                }
                CastlingPossible = true;
            }

            if (QueenSideCastlingPossible(king.getcolour())) {
                if (king.getcolour()) {
                    legal_moves.get(0).add(squares.get(7).get(2));
                } else {
                    legal_moves.get(0).add(squares.get(0).get(2));
                }
                CastlingPossible = true;
            }
        }
    }


    public void Check_Checkmate() {
        System.out.println(legal_moves.get(0));
        for (ArrayList<Square> i : legal_moves) {
            if (!i.isEmpty()) {
                return;
            }
        }
        if (Check) {
            Checkmate();
        } else {
            Stalemate();
        }
    }

    public void Checkmate() {
        System.out.println("Checkmate");
    }

    public void Stalemate() {
        System.out.println("Stalemate");
    }
    public void FiftyMoveDraw(){
        System.out.println("50 Move Draw");
    }
    public void ThreefoldRepetition(){
        System.out.println("Threefold repetition");
    }

    public ArrayList<Square> analyze_straight_moves(Piece piece, Boolean prot) {
        ArrayList<Square> legal_moves = new ArrayList<>();
        //Downwards
        for (int i = getArray(piece)+1 ; i < 8; i++) {

            boolean brk = false;
            legal_moves.add(squares.get(i).get(getIndex(piece)));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(i).get(getIndex(piece)).getLocation().toString()) && j != piece) {
                    legal_moves.remove(legal_moves.size() - 1);
                    if (j.getcolour() != piece.getcolour() || prot) {
                        legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                    }
                    brk = true;
                    break;
                }

            }
            if (brk) {
                break;
            }
        }
        //Upwards
        for (int i = getArray(piece)-1; i > -1; i--) {
            boolean brk = false;
            legal_moves.add(squares.get(i).get(getIndex(piece)));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(i).get(getIndex(piece)).getLocation().toString()) && j != piece) {
                    legal_moves.remove(legal_moves.size() - 1);
                    if (j.getcolour() != piece.getcolour() || prot) {
                        legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                    }
                    brk = true;
                    break;

                }
            }
            if (brk) {
                break;
            }
        }

        //Right
        for (int i = getIndex(piece)+1; i < 8; i++) {
            if (i != -1) {
                boolean brk = false;
                legal_moves.add(squares.get(getArray(piece)).get(i));
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece)).get(i).getLocation().toString()) && j != piece) {
                        legal_moves.remove(legal_moves.size() - 1);
                        if (j.getcolour() != piece.getcolour() || prot) {
                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;
                    }
                }

                if (brk) {
                    break;
                }
            }
        }

        //Left
        for (int i = getIndex(piece)-1; i > -1; i--) {
            if (i != -1) {
                boolean brk = false;
                legal_moves.add(squares.get(getArray(piece)).get(i));
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece)).get(i).getLocation().toString()) && j != piece) {
                        legal_moves.remove(legal_moves.size() - 1);
                        if (j.getcolour() != piece.getcolour() || prot) {
                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;
                    }
                }
                if (brk) {
                    break;
                }
            }
        }
        return legal_moves;
    }

    public ArrayList<Square> analyze_diagonal_moves(Piece piece, Boolean prot) {
        ArrayList<Square> legal_moves = new ArrayList<>();

        // down left
        for (int i = 1; i < 8; i++) {
            boolean brk = false;

            try {
                legal_moves.add(squares.get(getArray(piece) - i).get(getIndex(piece) - i));
                for (Piece j : Piece_List) {

                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) - i).get(getIndex(piece) - i).getLocation().toString()) && j != piece) {
                        legal_moves.remove(legal_moves.size() - 1);

                        if (j.getcolour() != piece.getcolour() || prot) {

                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;


                    }

                }
            } catch (Exception e) {
                break;
            }
            if (brk) {
                break;
            }
        }

        // up right
        for (int i = 1; i < 8; i++) {
            boolean brk = false;

            try {
                legal_moves.add(squares.get(getArray(piece) + i).get(getIndex(piece) + i));
                for (Piece j : Piece_List) {

                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) + i).get(getIndex(piece) + i).getLocation().toString()) && j != piece) {
                        legal_moves.remove(legal_moves.size() - 1);

                        if (j.getcolour() != piece.getcolour() || prot) {

                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;


                    }

                }
            } catch (Exception e) {
                break;
            }
            if (brk) {
                break;
            }
        }

        //down right
        for (int i = 1; i < 8; i++) {
            boolean brk = false;

            try {
                legal_moves.add(squares.get(getArray(piece) + i).get(getIndex(piece) - i));
                for (Piece j : Piece_List) {

                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) + i).get(getIndex(piece) - i).getLocation().toString()) && j != piece) {
                        legal_moves.remove(legal_moves.size() - 1);

                        if (j.getcolour() != piece.getcolour() || prot) {

                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;


                    }

                }
            } catch (Exception e) {
                break;
            }
            if (brk) {
                break;
            }
        }

        //down left
        for (int i = 1; i < 8; i++) {
            boolean brk = false;

            try {
                legal_moves.add(squares.get(getArray(piece) - i).get(getIndex(piece) + i));
                for (Piece j : Piece_List) {

                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) - i).get(getIndex(piece) + i).getLocation().toString()) && j != piece) {
                        legal_moves.remove(legal_moves.size() - 1);

                        if (j.getcolour() != piece.getcolour() || prot) {

                            legal_moves.add(squares.get(getArray(j)).get(getIndex(j)));

                        }
                        brk = true;
                        break;


                    }

                }
            } catch (Exception e) {
                break;
            }
            if (brk) {
                break;
            }
        }
        //System.out.println(legal_moves);
        return legal_moves;

    }


    public ArrayList<Square> analyze_rook_moves(Rook rook, boolean prot) {
        ArrayList<Square> legal_moves = new ArrayList<>();

        legal_moves = analyze_straight_moves(rook, prot);

        return legal_moves;
    }


    public ArrayList<Square> analyze_pawn_moves(Pawn pawn, boolean prot) {
        ArrayList<Square> legal_moves = new ArrayList<>();
        //If pawn is white
        if (pawn.getcolour()) {

            //one square up
            if(getArray(pawn) - 1 != -1 && !prot) {
                legal_moves.add(squares.get(getArray(pawn) - 1).get(getIndex(pawn)));
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) - 1).get(getIndex(pawn)).getLocation().toString())) {
                        legal_moves.remove(legal_moves.size() - 1);
                    }

                }
            }

            //two squares up
            if (getArray(pawn) == 6 && legal_moves.size() == 1) {
                legal_moves.add(squares.get(getArray(pawn) - 2).get(getIndex(pawn)));
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) - 2).get(getIndex(pawn)).getLocation().toString())) {
                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }
        //black pawn
        else {
            //one swuare down
            if(getArray(pawn) + 1 != 8 && !prot) {
                legal_moves.add(squares.get(getArray(pawn) + 1).get(getIndex(pawn)));
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) + 1).get(getIndex(pawn)).getLocation().toString())) {
                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }

            //two squares down
            if (getArray(pawn) == 1 && legal_moves.size() == 1) {
                legal_moves.add(squares.get(getArray(pawn) + 2).get(getIndex(pawn)));
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) + 2).get(getIndex(pawn)).getLocation().toString())) {
                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }
        //diagonal captures is pawn is white
        if (pawn.getcolour()) {
            if(getArray(pawn) - 1 != -1) {
                if (getIndex(pawn) - 1 != -1 && getIndex(pawn) - 1 != -1) {
                    for (Piece j : Piece_List) {
                        if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) - 1).get(getIndex(pawn) - 1).getLocation().toString()) || prot) {
                            if (j.getcolour() != pawn.getcolour() || prot) {
                                legal_moves.add(squares.get(getArray(pawn) - 1).get(getIndex(pawn) - 1));
                            }
                        }
                    }
                }
                if (getIndex(pawn) - 1 != -1 && getIndex(pawn) + 1 != 8) {
                    for (Piece j : Piece_List) {
                        if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) - 1).get(getIndex(pawn) + 1).getLocation().toString()) || prot) {
                            if (j.getcolour() != pawn.getcolour() || prot) {
                                legal_moves.add(squares.get(getArray(pawn) - 1).get(getIndex(pawn) + 1));
                            }
                        }
                    }
                }
            }
        }
        //diagonal captures is pawn is black
        else {
            if(getArray(pawn) + 1 != 8) {
                if (getIndex(pawn) - 1 != -1 && getIndex(pawn) - 1 != -1) {
                    for (Piece j : Piece_List) {
                        if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) + 1).get(getIndex(pawn) - 1).getLocation().toString()) || prot) {
                            if (j.getcolour() != pawn.getcolour() || prot) {
                                legal_moves.add(squares.get(getArray(pawn) + 1).get(getIndex(pawn) - 1));
                            }
                        }
                    }
                }
                if (getIndex(pawn) - 1 != -1 && getIndex(pawn) + 1 != 8) {
                    for (Piece j : Piece_List) {
                        if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn) + 1).get(getIndex(pawn) + 1).getLocation().toString()) || prot) {
                            if (j.getcolour() != pawn.getcolour() || prot) {
                                legal_moves.add(squares.get(getArray(pawn) + 1).get(getIndex(pawn) + 1));
                            }
                        }
                    }
                }
            }
        }

        if (!prot) {
            if (getIndex(pawn) - 1 != -1) {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn)).get(getIndex(pawn) - 1).getLocation().toString())) {
                        if (j instanceof Pawn && j.getcolour() != pawn.getcolour() && (((Pawn) j).getEnpassantable())) {
                            if (pawn.getcolour()) {
                                legal_moves.add(squares.get(getArray(pawn) - 1).get(getIndex(pawn) - 1));
                            } else {
                                legal_moves.add(squares.get(getArray(pawn) + 1).get(getIndex(pawn) - 1));
                            }
                        }
                    }
                }
            }

            if (getIndex(pawn) + 1 != 8) {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(pawn)).get(getIndex(pawn) + 1).getLocation().toString())) {
                        if (j instanceof Pawn && j.getcolour() != pawn.getcolour() && (((Pawn) j).getEnpassantable())) {
                            if (pawn.getcolour()) {
                                legal_moves.add(squares.get(getArray(pawn) - 1).get(getIndex(pawn) + 1));
                            } else {
                                legal_moves.add(squares.get(getArray(pawn) + 1).get(getIndex(pawn) + 1));
                            }
                        }
                    }
                }
            }
        }


        return legal_moves;
    }

    public ArrayList<Square> analyze_bishop_moves(Bishop bishop, boolean prot) {
        ArrayList<Square> legal_moves = new ArrayList<>();

        legal_moves = analyze_diagonal_moves(bishop, prot);

        return legal_moves;
    }

    public ArrayList<Square> analyze_queen_moves(Queen queen, boolean prot) {


        ArrayList<Square> temp = analyze_diagonal_moves(queen, prot);
        ArrayList<Square> legal_moves = new ArrayList<>(temp);

        temp.clear();
        temp = analyze_straight_moves(queen, prot);
        legal_moves.addAll(temp);

        return legal_moves;
    }

    public ArrayList<Square> analyze_knight_moves(Knight knight, boolean prot) {
        ArrayList<Square> legal_moves = new ArrayList<>();

        if (getArray(knight) + 2 < 8 && getIndex(knight) - 1 > -1) {
            legal_moves.add(squares.get(getArray(knight) + 2).get(getIndex(knight) - 1));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) + 2).get(getIndex(knight) - 1).getLocation().toString())) {

                    if (knight.getcolour() == j.getcolour() && !prot) {
                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }
            }
        }

        if (getArray(knight) + 2 < 8 && getIndex(knight) + 1 < 8) {
            legal_moves.add(squares.get(getArray(knight) + 2).get(getIndex(knight) + 1));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) + 2).get(getIndex(knight) + 1).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }

            }
        }

        if (getArray(knight) + 1 < 8 && getIndex(knight) - 2 > -1) {
            legal_moves.add(squares.get(getArray(knight) + 1).get(getIndex(knight) - 2));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) + 1).get(getIndex(knight) - 2).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }

            }
        }

        if (!(getArray(knight) + 1 >= 8) && !(getIndex(knight) + 2 >= 8)) {
            legal_moves.add(squares.get(getArray(knight) + 1).get(getIndex(knight) + 2));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) + 1).get(getIndex(knight) + 2).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }

            }
        }

        if (getArray(knight) - 2 > -1 && getIndex(knight) + 1 < 8) {
            legal_moves.add(squares.get(getArray(knight) - 2).get(getIndex(knight) + 1));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) - 2).get(getIndex(knight) + 1).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }
            }
        }

        if (getArray(knight) - 2 > -1 && getIndex(knight) - 1 > -1) {
            legal_moves.add(squares.get(getArray(knight) - 2).get(getIndex(knight) - 1));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) - 2).get(getIndex(knight) - 1).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }

            }
        }

        if (getArray(knight) - 1 > -1 && getIndex(knight) + 2 < 8) {
            legal_moves.add(squares.get(getArray(knight) - 1).get(getIndex(knight) + 2));
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) - 1).get(getIndex(knight) + 2).getLocation().toString())) {
                    if (knight.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }


            }
        }

        if (getArray(knight) - 1 > -1 && getIndex(knight) - 2 > -1) {
            legal_moves.add(squares.get(getArray(knight) - 1).get(getIndex(knight) - 2));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(knight) - 1).get(getIndex(knight) - 2).getLocation().toString()))) {
                    if (knight.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                    break;
                }
            }
        }

        return legal_moves;
    }

    public boolean KingSideCastlingPossible(boolean colour) {
        boolean CastlingPossible = true;
        ArrayList<Square> Unsafe_Squares_for_King_1d = convert_to_1d(Unsafe_Squares_for_King);


        if (colour) {
            if (((Rook) Piece_List_W.get(3)).getMoved() || ((King) Piece_List_W.get(0)).getMoved()) {
                CastlingPossible = false;
                return CastlingPossible;
            }

            for (Piece i : Piece_List) {
                if (getArray(i) == 7 && (getIndex(i) == 6 || getIndex(i) == 5)) {
                    CastlingPossible = false;
                    return CastlingPossible;
                }
            }
            for (Square square : Unsafe_Squares_for_King_1d) {
                for (int i = 5; i < 6; i++) {
                    if (getArray(square) == getArray(squares.get(7).get(i)) && getIndex(square) == getIndex(squares.get(7).get(i))) {
                        CastlingPossible = false;
                        return CastlingPossible;
                    }
                }
            }
        }

        if (!colour) {
            if (((Rook) Piece_List_B.get(3)).getMoved() || ((King) Piece_List_B.get(0)).getMoved()) {
                CastlingPossible = false;
                return CastlingPossible;
            }

            for (Piece i : Piece_List) {
                if (getArray(i) == 0 && (getIndex(i) == 6 || getIndex(i) == 5)) {
                    CastlingPossible = false;
                    return CastlingPossible;
                }
            }

            for (Square square : Unsafe_Squares_for_King_1d) {
                for (int i = 5; i < 6; i++) {
                    if (getArray(square) == getArray(squares.get(0).get(i)) && getIndex(square) == getIndex(squares.get(0).get(i))) {
                        CastlingPossible = false;
                        return CastlingPossible;
                    }
                }
            }
        }

        return CastlingPossible;
    }

    public boolean QueenSideCastlingPossible(boolean colour) {
        boolean CastlingPossible = true;
        ArrayList<Square> Unsafe_Squares_for_King_1d = convert_to_1d(Unsafe_Squares_for_King);


        if (colour) {
            if (((Rook) Piece_List_W.get(2)).getMoved() || ((King) Piece_List_W.get(0)).getMoved()) {
                CastlingPossible = false;
                return CastlingPossible;
            }

            for (Piece i : Piece_List) {
                if (getArray(i) == 7 && (getIndex(i) == 1 || getIndex(i) == 2 || getIndex(i) == 3)) {
                    CastlingPossible = false;
                    return CastlingPossible;
                }
            }
            for (Square square : Unsafe_Squares_for_King_1d) {
                for (int i = 2; i < 4; i++) {
                    if (getArray(square) == getArray(squares.get(7).get(i)) && getIndex(square) == getIndex(squares.get(7).get(i))) {
                        CastlingPossible = false;
                        return CastlingPossible;
                    }
                }
            }
        }

        if (!colour) {
            if (((Rook) Piece_List_B.get(2)).getMoved() && ((King) Piece_List_B.get(0)).getMoved()) {
                CastlingPossible = false;
                return CastlingPossible;
            }

            for (Piece i : Piece_List) {
                if (getArray(i) == 0 && (getIndex(i) == 1 || getIndex(i) == 2 || getIndex(i) == 3)) {
                    CastlingPossible = false;
                    return CastlingPossible;
                }
            }
            for (Square square : Unsafe_Squares_for_King_1d) {
                for (int i = 2; i < 4; i++) {
                    if (getArray(square) == getArray(squares.get(0).get(i)) && getIndex(square) == getIndex(squares.get(0).get(i))) {
                        CastlingPossible = false;
                        return CastlingPossible;
                    }
                }
            }
        }

        return CastlingPossible;
    }

    public ArrayList<Square> analyze_king_moves(King king, boolean prot) {
        ArrayList<Square> legal_moves = new ArrayList<>();


        if (getArray(king) - 1 != -1 && getIndex(king) - 1 != -1) {
            legal_moves.add(squares.get(getArray(king) - 1).get(getIndex(king) - 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) - 1).get(getIndex(king) - 1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour() && !prot) {
                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if (getArray(king) + 1 != 8 && getIndex(king) + 1 != 8) {
            legal_moves.add(squares.get(getArray(king) + 1).get(getIndex(king) + 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) + 1).get(getIndex(king) + 1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }


        if (getArray(king) + 1 != 8 && getIndex(king) - 1 != -1) {
            legal_moves.add(squares.get(getArray(king) + 1).get(getIndex(king) - 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) + 1).get(getIndex(king) - 1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if (getArray(king) - 1 != -1 && getIndex(king) + 1 != 8) {
            legal_moves.add(squares.get(getArray(king) - 1).get(getIndex(king) + 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) - 1).get(getIndex(king) + 1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if (getIndex(king) - 1 != -1) {
            legal_moves.add(squares.get(getArray(king)).get(getIndex(king) - 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king)).get(getIndex(king) - 1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour() && !prot) {
                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if (getIndex(king) + 1 != 8) {
            legal_moves.add(squares.get(getArray(king)).get(getIndex(king) + 1));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king)).get(getIndex(king) + 1).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if (getArray(king) + 1 != 8) {
            legal_moves.add(squares.get(getArray(king) + 1).get(getIndex(king)));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) + 1).get(getIndex(king)).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        if (getArray(king) - 1 != -1) {
            legal_moves.add(squares.get(getArray(king) - 1).get(getIndex(king)));
            for (Piece j : Piece_List) {
                if ((Objects.equals(j.getLocation().toString(), squares.get(getArray(king) - 1).get(getIndex(king)).getLocation().toString()))) {
                    if (king.getcolour() == j.getcolour() && !prot) {

                        legal_moves.remove(legal_moves.size() - 1);
                    }
                }
            }
        }

        return legal_moves;
    }

    public ArrayList<ArrayList<Square>> analyze_legal_moves(boolean prot) {
        ArrayList<ArrayList<Square>> legal_moves = new ArrayList<>();
        if (Turn_w && !prot || !Turn_w && prot) {
            for (Piece i : Piece_List_W) {
                if (i.isVisible()) {
                    if (i instanceof Rook) {
                        legal_moves.add(analyze_rook_moves((Rook) i, prot));
                    }
                    if (i instanceof Pawn) {
                        legal_moves.add(analyze_pawn_moves((Pawn) i, prot));
                    }
                    if (i instanceof Bishop) {
                        legal_moves.add(analyze_bishop_moves((Bishop) i, prot));
                    }
                    if (i instanceof Queen) {
                        legal_moves.add(analyze_queen_moves((Queen) i, prot));
                    }
                    if (i instanceof Knight) {
                        legal_moves.add(analyze_knight_moves((Knight) i, prot));
                    }
                    if (i instanceof King) {
                        legal_moves.add(analyze_king_moves((King) i, prot));
                    }
                } else {
                    legal_moves.add(new ArrayList<>());
                }
            }
        } else if (!Turn_w && !prot || Turn_w && prot) {
            for (Piece i : Piece_List_B) {
                if (i.isVisible()) {
                    if (i instanceof Rook) {
                        legal_moves.add(analyze_rook_moves((Rook) i, prot));
                    }
                    if (i instanceof Pawn) {
                        legal_moves.add(analyze_pawn_moves((Pawn) i, prot));
                    }
                    if (i instanceof Bishop) {
                        legal_moves.add(analyze_bishop_moves((Bishop) i, prot));
                    }
                    if (i instanceof Queen) {
                        legal_moves.add(analyze_queen_moves((Queen) i, prot));
                    }
                    if (i instanceof Knight) {
                        legal_moves.add(analyze_knight_moves((Knight) i, prot));
                    }
                    if (i instanceof King) {
                        legal_moves.add(analyze_king_moves((King) i, prot));
                    }
                } else {
                    legal_moves.add(new ArrayList<>());
                }
            }
        }

        if (prot) {
            ArrayList<Square> legal_moves_temp = new ArrayList<>();
            for (ArrayList<Square> i : legal_moves) {
                legal_moves_temp.addAll(i);
            }

        }
        return legal_moves;
    }

    public ArrayList<Square> convert_to_1d(ArrayList<ArrayList<Square>> array) {
        ArrayList<Square> legal_moves = new ArrayList<>();
        if (array != null) {
            for (ArrayList<Square> i : array) {
                legal_moves.addAll(i);
            }
        }
        return legal_moves;
    }

    public boolean analyze_straight_future_moves(Piece piece, Piece piece_to_be_moved, int destination_index, int destination_array) {
        boolean safe = true;

        if (getIndex(piece) == destination_index && getArray(piece) == destination_array) {
            return safe;
        }

        for (int i = getArray(piece); i < 8; i++) {
            boolean brk = false;
            if (i == destination_array && getIndex(piece) == destination_index) {
                break;
            }
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(i).get(getIndex(piece)).getLocation().toString()) && j != piece && j != piece_to_be_moved && j.isVisible()) {

                    if (j instanceof King && j.getcolour() != piece.getcolour()) {
                        safe = false;
                        return safe;

                    }
                    brk = true;
                    break;

                }

            }

            if (brk) {
                break;
            }

        }
        //Upwards
        for (int i = getArray(piece); i > -1; i--) {
            boolean brk = false;
            if (i == destination_array && getIndex(piece) == destination_index) {
                break;
            }
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(i).get(getIndex(piece)).getLocation().toString()) && j != piece && j != piece_to_be_moved && j.isVisible()) {
                    if (j instanceof King && j.getcolour() != piece.getcolour()) {
                        safe = false;
                        return safe;

                    }
                    brk = true;
                    break;

                }

            }

            if (brk) {
                break;
            }

        }

        //Right
        for (int i = getIndex(piece); i < 8; i++) {
            boolean brk = false;
            if (i == destination_index && getArray(piece) == destination_array) {
                break;
            }
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece)).get(i).getLocation().toString()) && j != piece && j != piece_to_be_moved && j.isVisible()) {
                    if (j instanceof King && j.getcolour() != piece.getcolour()) {
                        safe = false;
                        return safe;
                    }
                    brk = true;
                    break;
                }
            }
            if (brk) {
                break;
            }
        }


        //Left
        for (int i = getIndex(piece); i > -1; i--) {
            boolean brk = false;
            if (i == destination_index && getArray(piece) == destination_array) {
                break;
            }
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece)).get(i).getLocation().toString()) && j != piece && j != piece_to_be_moved && j.isVisible()) {

                    if (j instanceof King && j.getcolour() != piece.getcolour()) {
                        safe = false;
                        return safe;
                    }
                    brk = true;
                    break;
                }
            }
            if (brk) {
                break;
            }
        }
        return safe;
    }

    public boolean analyze_diagonal_future_moves(Piece piece, Piece piece_to_be_moved, int destination_index, int destination_array) {
        ArrayList<Square> legal_moves = new ArrayList<>();
        boolean safe = true;

        if (getIndex(piece) == destination_index && getArray(piece) == destination_array) {
            return safe;
        }

        // down left
        for (int i = 1; i < 8; i++) {
            if (getArray(piece) - i == destination_array && getIndex(piece) - i == destination_index) {
                break;
            }
            boolean brk = false;
            try {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) - i).get(getIndex(piece) - i).getLocation().toString()) && j != piece_to_be_moved && j.isVisible()) {
                        if (j instanceof King && j.getcolour() != piece.getcolour()) {
                            safe = false;
                            return safe;
                        }
                        brk = true;
                        break;

                    }
                }
            } catch (Exception e) {
                break;
            }
            if (brk) {
                break;
            }
        }

        // up right
        for (int i = 1; i < 8; i++) {
            if (getArray(piece) + i == destination_array && getIndex(piece) + i == destination_index) {
                break;
            }
            boolean brk = false;
            try {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) + i).get(getIndex(piece) + i).getLocation().toString()) && j != piece_to_be_moved && j.isVisible()) {
                        if (j instanceof King && j.getcolour() != piece.getcolour()) {
                            safe = false;
                            return safe;
                        }
                        brk = true;
                        break;

                    }
                }
            } catch (Exception e) {
                break;
            }
            if (brk) {
                break;
            }
        }

        //down right
        for (int i = 1; i < 8; i++) {
            if (getArray(piece) + i == destination_array && getIndex(piece) - i == destination_index) {
                break;
            }
            boolean brk = false;
            try {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) + i).get(getIndex(piece) - i).getLocation().toString()) && j != piece_to_be_moved && j.isVisible()) {
                        if (j instanceof King && j.getcolour() != piece.getcolour()) {
                            safe = false;
                            return safe;
                        }
                        brk = true;
                        break;

                    }
                }
            } catch (Exception e) {
                break;
            }
            if (brk) {
                break;
            }
        }

        //down left
        for (int i = 1; i < 8; i++) {
            if (getArray(piece) - i == destination_array && getIndex(piece) + i == destination_index) {
                break;
            }
            boolean brk = false;
            try {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) - i).get(getIndex(piece) + i).getLocation().toString()) && j != piece_to_be_moved && j.isVisible()) {
                        if (j instanceof King && j.getcolour() != piece.getcolour()) {
                            safe = false;
                            return safe;
                        }
                        brk = true;
                        break;

                    }
                }
            } catch (Exception e) {
                break;
            }
            if (brk) {
                break;
            }
        }
        return safe;

    }


    public boolean analyzeFutureKnightMoves(Piece piece, Piece piece_to_be_moved, int destination_index, int destination_array){
        boolean safe = true;

        if (getIndex(piece) == destination_index && getArray(piece) == destination_array) {
            return safe;
        }

        if (getArray(piece) + 2 < 8 && getIndex(piece) - 1 > -1) {
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) + 2).get(getIndex(piece) - 1).getLocation().toString())) {
                    if (piece.getcolour() != j.getcolour() && j instanceof King ) {
                        safe = false;
                        return safe;
                    }
                    break;
                }
            }
        }
        if (getArray(piece) + 2 < 8 && getIndex(piece) + 1 < 8) {
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) + 2).get(getIndex(piece) + 1).getLocation().toString())) {
                    if (piece.getcolour() != j.getcolour() && j instanceof King ) {
                        safe = false;
                        return safe;
                    }
                    break;
                }
            }
        }

        if (getArray(piece) + 1 < 8 && getIndex(piece) + 2 < 8) {
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) + 1).get(getIndex(piece) + 2).getLocation().toString())) {
                    if (piece.getcolour() != j.getcolour() && j instanceof King ) {
                        safe = false;
                        return safe;
                    }
                    break;
                }
            }
        }

        if (getArray(piece) + 1 < 8 && getIndex(piece) - 2 > -1) {
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) + 1).get(getIndex(piece) - 2).getLocation().toString())) {
                    if (piece.getcolour() != j.getcolour() && j instanceof King ) {
                        safe = false;
                        return safe;
                    }
                    break;
                }
            }
        }
        if (getArray(piece) - 2 > -1 && getIndex(piece) - 1 > -1) {
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) - 2).get(getIndex(piece) - 1).getLocation().toString())) {
                    if (piece.getcolour() != j.getcolour() && j instanceof King ) {
                        safe = false;
                        return safe;
                    }
                    break;
                }
            }
        }
        if (getArray(piece) - 2 > -1 && getIndex(piece) + 1 < 8) {
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) - 2).get(getIndex(piece) + 1).getLocation().toString())) {
                    if (piece.getcolour() != j.getcolour() && j instanceof King ) {
                        safe = false;
                        return safe;
                    }
                    break;
                }
            }
        }

        if (getArray(piece) - 1 > -1 && getIndex(piece) + 2 < 8) {
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) - 1).get(getIndex(piece) + 2).getLocation().toString())) {
                    if (piece.getcolour() != j.getcolour() && j instanceof King ) {
                        safe = false;
                        return safe;
                    }
                    break;
                }
            }
        }

        if (getArray(piece) - 1 > -1 && getIndex(piece) - 2 > -1) {
            for (Piece j : Piece_List) {
                if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) - 1).get(getIndex(piece) - 2).getLocation().toString())) {
                    if (piece.getcolour() != j.getcolour() && j instanceof King ) {
                        safe = false;
                        return safe;
                    }
                    break;
                }
            }
        }


        return safe;
    }
    public boolean analyzeFuturePawnMoves(Piece piece, Piece piece_to_be_moved, int destination_index, int destination_array) {
        boolean safe = true;

        if (getIndex(piece) == destination_index && getArray(piece) == destination_array) {
            return safe;
        }

        if (piece.getcolour()) {
            if (getArray(piece) - 1 != -1 && getIndex(piece) - 1 != -1) {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) - 1).get(getIndex(piece) - 1).getLocation().toString())) {
                        if (j.getcolour() != piece.getcolour() && j instanceof King) {
                            safe = false;
                            return safe;
                        }
                    }
                }
            }
            if (getArray(piece) - 1 != -1 && getIndex(piece) + 1 != 8) {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) - 1).get(getIndex(piece) + 1).getLocation().toString())) {
                        if (j.getcolour() != piece.getcolour() && j instanceof King) {
                            safe = false;
                            return safe;
                        }
                    }
                }
            }
        }

        //diagonal captures is pawn is black

        if (!piece.getcolour()) {
            if (getArray(piece) + 1 != 8 && getIndex(piece) - 1 != -1) {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) + 1).get(getIndex(piece) - 1).getLocation().toString())) {
                        if (j.getcolour() != piece.getcolour() && j instanceof King) {
                            safe = false;
                            return safe;
                        }
                    }
                }
            }
            if (getArray(piece) + 1 != 8 && getIndex(piece) + 1 != 8) {
                for (Piece j : Piece_List) {
                    if (Objects.equals(j.getLocation().toString(), squares.get(getArray(piece) + 1).get(getIndex(piece) + 1).getLocation().toString())) {
                        if (j.getcolour() != piece.getcolour() && j instanceof King) {
                            safe = false;
                            return safe;
                        }
                    }
                }
            }
        }

        return safe;
    }


    public boolean analyze_future_moves(Piece piece, int origin_index, int origin_array, int destination_index, int destination_array) {
        boolean safe = true;
        if (Turn_w) {
            for (Piece i : Piece_List_B) {
                if (i.isVisible()) {
                    if (i instanceof Rook) {
                        if (!analyze_straight_future_moves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                    }
                    if (i instanceof Bishop) {
                        if (!analyze_diagonal_future_moves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                    }
                    if (i instanceof Queen) {
                        if (!analyze_straight_future_moves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                        if (!analyze_diagonal_future_moves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                    }

                    if (i instanceof Knight) {
                        if (!analyzeFutureKnightMoves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                    }

                    if(i instanceof  Pawn){
                        if (!analyzeFuturePawnMoves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                    }

                }


            }
        } else if (!Turn_w) {
            for (Piece i : Piece_List_W) {
                if (i.isVisible()) {
                    if (i instanceof Rook) {
                        if (!analyze_straight_future_moves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                    }
                    if (i instanceof Bishop) {
                        if (!analyze_diagonal_future_moves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                    }
                    if (i instanceof Queen) {
                        if (!analyze_straight_future_moves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                        if (!analyze_diagonal_future_moves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                    }
                    if (i instanceof Knight) {
                        if (!analyzeFutureKnightMoves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                    }

                    if(i instanceof  Pawn){
                        if (!analyzeFuturePawnMoves(i, piece, destination_index, destination_array)) {
                            safe = false;
                            return safe;
                        }
                    }

                }


            }
        }
        return safe;
    }

    public void select_piece(Piece piece) {
        if (piece.getcolour() == Turn_w) {

            selected_Piece = piece;
        }
    }

    public void capture_or_change_piece(Piece piece) {

        if (((Piece) selected_Piece).getcolour() != ((piece).getcolour())) {

            boolean p_got_taken = false;

            if (Turn_w && legal_moves.get(Piece_List_W.indexOf(selected_Piece)).contains(squares.get(getArray(piece)).get(getIndex(piece)))) {
                selected_Piece.setLocation(piece.getLocation());
                p_got_taken = true;
            }

            if (!Turn_w && legal_moves.get(Piece_List_B.indexOf(selected_Piece)).contains(squares.get(getArray(piece)).get(getIndex(piece)))) {
                selected_Piece.setLocation(piece.getLocation());
                p_got_taken = true;

            }


            if (p_got_taken) {
                piece.setVisible(false);
                Piece_List.remove(piece);

                FiftyMoveDrawCount = 0;
                ColourWithWhichFiftyMoveCountReset = Turn_w;

                if (selected_Piece instanceof King) {
                    ((King) selected_Piece).Moved();
                }
                if (selected_Piece instanceof Rook) {
                    ((Rook) selected_Piece).Moved();
                }

                if (!EnPassantables.isEmpty()) {
                    NoLongerEnPassantable.addAll(EnPassantables);
                    EnPassantables.clear();

                    for (Pawn i : EnPassantables) {
                        i.setEnpassantable(true);
                    }

                    for (Pawn i : NoLongerEnPassantable) {
                        i.setEnpassantable(false);
                    }
                }

                    if(selected_Piece instanceof Pawn && (getArray((Piece) selected_Piece) == 7 || selected_Piece instanceof Pawn && getArray((Piece) selected_Piece) == 0)){
                        Promotion();
                    }

                    selected_Piece = null;
                    Turn_w = !Turn_w;
                if(PromotedPiece == null) {
                    legal_moves = analyze_legal_moves(false);
                    CheckPinsBlocks();
                    Check_for_Checks();
                }

            }
        }
        else {

            selected_Piece = piece;

        }
    }
        public void move(Square square){
            boolean moved = false;
            if (Turn_w && selected_Piece != null) {
                if (legal_moves.get(Piece_List_W.indexOf(selected_Piece)).contains(square) && Turn_w) {
                    selected_Piece.setLocation(square.getLocation());
                    moved = true;

                }
            }


            if (!Turn_w && selected_Piece != null) {
                if (legal_moves.get(Piece_List_B.indexOf(selected_Piece)).contains(square) && !Turn_w) {
                    selected_Piece.setLocation(square.getLocation());
                    moved = true;

                }
            }
            if (moved) {
                if (selected_Piece instanceof King) {
                    ((King) selected_Piece).Moved();
                }
                if (selected_Piece instanceof Rook) {
                    ((Rook) selected_Piece).Moved();
                }

                if (CastlingPossible && selected_Piece instanceof King) {
                    if (getArray((Piece) selected_Piece) == 0 && getIndex((Piece) selected_Piece) == 6 || getArray((Piece) selected_Piece) == 7 && getIndex((Piece) selected_Piece) == 6) {
                        if (Turn_w) {
                            Piece_List_W.get(3).setLocation(squares.get(7).get(5).getLocation());
                        }
                        if (!Turn_w) {
                            Piece_List_B.get(3).setLocation(squares.get(0).get(5).getLocation());
                        }
                    }
                    if (getArray((Piece) selected_Piece) == 0 && getIndex((Piece) selected_Piece) == 2 || getArray((Piece) selected_Piece) == 7 && getIndex((Piece) selected_Piece) == 2) {
                        if (Turn_w) {
                            Piece_List_W.get(2).setLocation(squares.get(7).get(3).getLocation());
                        }
                        if (!Turn_w) {
                            Piece_List_B.get(2).setLocation(squares.get(0).get(3).getLocation());
                        }
                    }
                }
                if(!EnPassantables.isEmpty()) {
                    if (selected_Piece instanceof Pawn) {
                        if (getIndex(EnPassantables.get(0)) == getIndex((Piece) selected_Piece)) {
                            if (EnPassantables.get(0).getcolour() && getArray((Piece) selected_Piece) == getArray(EnPassantables.get(0)) + 1) {
                                EnPassantables.get(0).setVisible(false);
                                EnPassantables.clear();
                            }
                            if (!EnPassantables.get(0).getcolour() && getArray((Piece) selected_Piece) == getArray(EnPassantables.get(0)) - 1) {
                                EnPassantables.get(0).setVisible(false);
                                EnPassantables.clear();
                            }
                        }
                    }
                }

                if(selected_Piece instanceof Pawn){
                    FiftyMoveDrawCount = 0;
                    ColourWithWhichFiftyMoveCountReset = Turn_w;
                }
                else{
                    if(ColourWithWhichFiftyMoveCountReset == Turn_w){
                        FiftyMoveDrawCount++;
                    }

                }

                if (!EnPassantables.isEmpty()) {
                    NoLongerEnPassantable.addAll(EnPassantables);
                    EnPassantables.clear();
                }

                if (selected_Piece instanceof Pawn && (getArray((Piece) selected_Piece) == 2 || getArray((Piece) selected_Piece) == 5)){
                    NoLongerEnPassantable.add((Pawn)selected_Piece);
                }

                if (selected_Piece instanceof Pawn && !(NoLongerEnPassantable.contains(selected_Piece)) && (getArray((Piece) selected_Piece) == 3 || getArray((Piece) selected_Piece) == 4)) {
                    EnPassantables.add(((Pawn) selected_Piece));
                }

                for (Pawn i : EnPassantables) {
                    i.setEnpassantable(true);
                }

                for (Pawn i : NoLongerEnPassantable) {
                    i.setEnpassantable(false);
                }

                if(selected_Piece instanceof Pawn && (getArray((Piece) selected_Piece) == 7 || selected_Piece instanceof Pawn && getArray((Piece) selected_Piece) == 0)){
                    Promotion();
                }

                selected_Piece = null;
                CastlingPossible = false;
                Turn_w = !Turn_w;
                ProcessFEN();
                if(PromotedPiece == null) {
                    legal_moves = analyze_legal_moves(false);
                    CheckPinsBlocks();
                    Check_for_Checks();
                }
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
        if(PromotionMenu != null) {
            if (Objects.equals(PromotionMenu.getSelection(), "null")) {
                return;
            }
            if (Objects.equals(PromotionMenu.getSelection(), "Queen")) {
            PromotedPiece.setVisible(false);
            Queen newQueen = new Queen(!Turn_w,PromotedPiece.getLocation());
            Piece_List.set(Piece_List.indexOf(PromotedPiece),newQueen);
            board.add(newQueen, JLayeredPane.POPUP_LAYER);
            newQueen.addMouseListener(this);

            if(!Turn_w){
                Piece_List_W.set(Piece_List_W.indexOf(PromotedPiece),newQueen);
            }

            if(Turn_w){
                Piece_List_B.set(Piece_List_B.indexOf(PromotedPiece),newQueen);
            }


            }
            if (Objects.equals(PromotionMenu.getSelection(), "Rook")) {
                PromotedPiece.setVisible(false);
                Rook newRook = new Rook(!Turn_w,PromotedPiece.getLocation());
                Piece_List.set(Piece_List.indexOf(PromotedPiece),newRook);
                board.add(newRook, JLayeredPane.POPUP_LAYER);
                newRook.addMouseListener(this);

                if(!Turn_w){
                    Piece_List_W.set(Piece_List_W.indexOf(PromotedPiece),newRook);
                }

                if(Turn_w){
                    Piece_List_B.set(Piece_List_B.indexOf(PromotedPiece),newRook);
                }


            }
            if (Objects.equals(PromotionMenu.getSelection(), "Bishop")) {
                PromotedPiece.setVisible(false);
                Bishop newBishop = new Bishop(!Turn_w,PromotedPiece.getLocation());
                Piece_List.set(Piece_List.indexOf(PromotedPiece),newBishop);
                board.add(newBishop, JLayeredPane.POPUP_LAYER);
                newBishop.addMouseListener(this);

                if(!Turn_w){
                    Piece_List_W.set(Piece_List_W.indexOf(PromotedPiece),newBishop);
                }

                if(Turn_w){
                    Piece_List_B.set(Piece_List_B.indexOf(PromotedPiece),newBishop);
                }


            }
            if (Objects.equals(PromotionMenu.getSelection(), "Knight")) {
                PromotedPiece.setVisible(false);
                Knight newKnight = new Knight(!Turn_w,PromotedPiece.getLocation());
                Piece_List.set(Piece_List.indexOf(PromotedPiece),newKnight);
                board.add(newKnight, JLayeredPane.POPUP_LAYER);
                newKnight.addMouseListener(this);

                if(!Turn_w){
                    Piece_List_W.set(Piece_List_W.indexOf(PromotedPiece),newKnight);
                }

                if(Turn_w){
                    Piece_List_B.set(Piece_List_B.indexOf(PromotedPiece),newKnight);
                }



            }
            PromotedPiece = null;
            PromotionMenu = null;

            legal_moves = analyze_legal_moves(false);
            CheckPinsBlocks();
            Check_for_Checks();
        }

        else{
            Check = false;
            System.out.println(selected_Piece);
            if (e.getComponent() instanceof Piece) {
                if (selected_Piece != e.getComponent()) {
                    if (selected_Piece == null) {
                        select_piece((Piece) e.getComponent());
                    } else {
                        capture_or_change_piece((Piece) e.getComponent());


                    }
                } else {
                    selected_Piece = null;
                }
            } else {
                move((Square) e.getComponent());


            }
            if (FiftyMoveDrawCount == 50) {
                FiftyMoveDraw();
            }
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