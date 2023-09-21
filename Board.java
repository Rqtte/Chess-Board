import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;



public class Board extends JFrame{
    
    int c;
    ArrayList<JLabel> squares;
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

    Board(){

        String[] letters = {"A","B","C","D","E","F","G","H"};
        String[] numbers = {"8","7","6","5","4","3","2","1"};



        //Declares the center of the board
        
        squares = new ArrayList<>();
        board_middle = new JPanel();
        c = 0;
        board_middle.setLayout(new GridLayout(8,8));
        board_middle.setSize(800, 800);

        for(int i = 0;i<64;i++){
            squares.add(new JLabel());
        }
        for(JLabel i:squares){
            i.setSize(50, 50);
            
            if(c==8 || c== 17 || c== 26 || c== 35 || c== 44 || c==53 || c==62 || c == 71){c++;}

            if(c % 2 == 0){
                i.setBackground(Color.white);
            }
            else{
                i.setBackground(Color.black);
            }
            c++;

            i.setVisible(true);
            i.setOpaque(true);
            board_middle.add(i);

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
            i.setBackground(new Color(71, 42, 32));
        }

        //fill1.setSize(20,20);
        //top.add(0,fill1);
        //fill2.setSize(20,20);
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
            i.setBackground(new Color(71, 42, 32));
        }

        //fill3.setSize(20,20);
        //bottom.add(0,fill3);
        //fill4.setSize(20,20);
        //bottom.add(fill4);

        for(JLabel i:bottom){
            board_bottom.add(i);
        }




        //Declares the right of the Board

        board_right = new JPanel(new GridLayout(8,1));

        right = new ArrayList<>();


        for(int i = 0; i<8;i++){
            right.add(new JLabel());
        }

        for(JLabel i:right){
            i.setSize(20, 50);
            i.setText(numbers[right.indexOf(i)]);
            i.setFont(new Font("Serif", Font.PLAIN, 14));
            i.setBackground(new Color(71, 42, 32));
            board_right.add(i);
        }




        //Declares the left of the Board

        board_left = new JPanel(new GridLayout(8,1));

        left = new ArrayList<>();


        for(int i = 0; i<8;i++){
            left.add(new JLabel());
        }

        for(JLabel i:left){
            i.setSize(20, 50);
            i.setText(numbers[left.indexOf(i)]);
            i.setFont(new Font("Serif", Font.PLAIN, 14));
            i.setBackground(new Color(71, 42, 32));
            board_left.add(i);
        }




        this.setTitle("Chess Board");
        this.setLayout(new BorderLayout());
        this.add(board_middle, BorderLayout.CENTER);
        this.add(board_top,BorderLayout.PAGE_START);
        this.add(board_bottom,BorderLayout.PAGE_END);
        this.add(board_right,BorderLayout.LINE_END);
        this.add(board_left,BorderLayout.LINE_START);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900,900);
        this.setResizable(false);
        this.setVisible(true);
    }
}