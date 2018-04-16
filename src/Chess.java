import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chess extends JFrame implements ActionListener {
    static String[] board = {"b1","n1","k","b1","*","*","p1","*","*","?","*","*","*","P1","*","*","B1","K","N1","B1"};
    static char turn = 'w';
    boolean item=true;
    boolean  skill=true;

    // GUI STUFF ================================================================================
    JPanel pnlNull = new JPanel();
    JPanel pnlGrid = new JPanel(new GridLayout(5,4));
    JPanel pnlFlow = new JPanel(new FlowLayout());
    JLabel lblStatus = new JLabel();
    JLabel lblTurn = new JLabel("White's turn.");
    JLabel lblCheck = new JLabel();
    JLabel lblWin = new JLabel();
    JLabel lblMoves = new JLabel();
    JButton btnSquare[] = new JButton[20];
    JButton btnSkill = new JButton("SKILL");
    Font f = new Font("DIALOG", Font.PLAIN, 100);

    public void clearBoard(){
        for (int i=0; i<20; i++) {
            if (i % 8 == 0||i % 8 == 2||i % 8 == 5||i % 8 == 7) {
                btnSquare[i].setBackground(Color.ORANGE);
            }
            else {
                btnSquare[i].setBackground(Color.WHITE);
            }
        }
    }

    public Chess() {
        for (int i=0; i<20; i++) {
            btnSquare[i] = new JButton();
            btnSquare[i].setFont(f);

            if (i % 8 == 0||i % 8 == 2||i % 8 == 5||i % 8 == 7) {
                btnSquare[i].setBackground(Color.ORANGE);
            }
            else {
                btnSquare[i].setBackground(Color.WHITE);
            }
            btnSquare[i].addActionListener(this);
            pnlGrid.add(btnSquare[i]);
        }

        pnlFlow.add(pnlNull);
        pnlFlow.add(pnlGrid);
        pnlFlow.add(lblTurn);
        pnlFlow.add(lblStatus);
        pnlFlow.add(lblCheck);
        pnlFlow.add(lblWin);
        pnlFlow.add(lblMoves);
        pnlFlow.add(btnSkill);


        btnSkill.addActionListener(this);

        lblStatus.setForeground(Color.RED);
        lblWin.setForeground(Color.BLUE);

        display();
        setTitle("ChessGame");
        setResizable(false);
        setSize(540, 960);
        pnlNull.setPreferredSize(new Dimension(540,100));
        pnlGrid.setPreferredSize(new Dimension(540,675));



        setContentPane(pnlFlow);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Display legal moves if they exist
    }

    int a=-1,b=-1;
    boolean more = false;
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSkill&skill) {
            more=true;
            lblMoves.setText("Use Skill : One More");
            skill=false;
        }
        else {
            for (int i = 0; i < 20; i++) {
                if (e.getSource() == btnSquare[i]) {
                    if (a == -1) a = i + 1;
                    else b = i + 1;
                    display();
                }
            }
            if (a != -1 && b == -1) {
                int count = 0;
                for (int j = 0; j < 20; j++) {
                    // Destination and source squares must be different
                    if (a - 1 != j) {
                        // Add to the list of valid moves for the current player
                        if (validateMove(a - 1, j, turn)) {
                            btnSquare[j].setBackground(Color.RED);
                            count++;
                        }
                    }
                }
                if (count == 0) {
                    a = -1;
                    lblStatus.setText("Invalid move");
                }
            }
            if (b != -1) {
                if (!isGameOver()) {
                    doMove(a - 1, b - 1, turn);        // Player's turn
                    if (!isItem()) lblMoves.setText("");
                    a = b = -1;
                    clearBoard();
                    display();
                    if(more){
                        swapTurn();
                        more=false;
                    }
                }

                // Display message if game is over
                if (isGameOver()) {
                    lblStatus.setText("");
                }
            }
        }
    }

    public void display() {
        // Display Unicode chess characters
        for (int i=0; i<20; i++) {
            switch (board[i]) {
                case "b1": btnSquare[i].setText("\u265d"); break;
                case "p1": btnSquare[i].setText("\u265f"); break;
                case "n1": btnSquare[i].setText("\u265e"); break;
                case "k": btnSquare[i].setText("\u265a"); break;
                case "B1": btnSquare[i].setText("\u2657"); break;
                case "P1": btnSquare[i].setText("\u2659"); break;
                case "N1": btnSquare[i].setText("\u2658"); break;
                case "K": btnSquare[i].setText("\u2654"); break;
                case "?": btnSquare[i].setText("?"); break;
                default: btnSquare[i].setText(""); break;
            }
        }
    }

    public static void main(String[] args) {
        // Create chess board object and displays it
        Chess c = new Chess();
        c.display();
    }

    public void doMove(int a, int b, char colour) {
        // Perform move if it is a valid move and the king is not moving into check
        if (validateMove(a, b, colour)) {
            board[b] = board[a];
            board[a]="*";
            swapTurn();
            lblStatus.setText("");
        }
        else {
            lblStatus.setText("Invalid move");
        }
    }

    public void swapTurn() {
        // Swap turns depending on the current player
        if (turn == 'w') {
            turn = 'b';
            lblTurn.setText("Black's turn.");
        }
        else {
            turn = 'w';
            lblTurn.setText("White's turn.");
        }
    }

    public boolean validateMove(int a, int b, char colour) {
        int r1, c1, r2, c2, dr, dc;

        // Convert start and end positions to rows and columns
        r1 = a / 4 + 1;		// Start row
        c1 = a % 4 + 1;		// Start column
        r2 = b / 4 + 1;		// End row
        c2 = b % 4 + 1;		// End column
        dr = r2 - r1;		// Change of squares between start and end rows
        dc = c2 - c1;		// Change of squares between start and end columns

//        System.out.println(r1+","+c1+"->"+r2+","+c2+"|"+dr+","+dc);
        // Invalid move if source location is a black piece or destination is a white piece
        if (colour == 'w') {
            if (Character.isLowerCase(board[a].charAt(0)) || Character.isUpperCase(board[b].charAt(0))) {
 //               System.out.println(1);
                return false;
            }
        }
        // Invalid move if source location is a white piece or destination is a black piece
        else if (colour == 'b') {
            if (Character.isUpperCase(board[a].charAt(0)) || Character.isLowerCase(board[b].charAt(0))) {
 //               System.out.println(1);
                return false;
            }
        }

        // Check source location to see if a chess piece exists
        switch (board[a]) {
            case "*":
                // Invalid move if source location is an empty piece
                return false;
            case "P1":
                // White pawns can move one square up on the same row if the square is unoccupied
                if ((c1 == c2) && (dr == -1)) {
                    return true;
                }
                else {
 //                   System.out.println(2);
                    return false;
                }
            case "p1":
                // Black pawns can move one square down on the same row if the square is unoccupied
                if ((c1 == c2) && (dr == 1)) {
                    return true;
                }
                else {
   //                 System.out.println(2);
                    return false;
                }
            case "K":
            case "k":
                // Kings can only move one square in any direction
                if ((dr >= -1) && (dr <= 1) && (dc >= -1) && (dc <= 1)) {
                    return true;
                }
                else {
   //                 System.out.println(3);
                    return false;
                }
            case "B1":
            case "b1":
                // Bishops can move diagonally 1 or 2 spaces in any direction
                // Check if moving diagonally
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)==1) {
                        return true;
                }
                else {
                    return false;
                }
            case "N1":
            case "n1":
                if (Math.abs(dr)+Math.abs(dc)==3&&(Math.abs(dr)==1|Math.abs(dr)==2)) {
                    if(Math.abs(dr)==2&&board[a+(dr*4)].equals("*")&&board[a+(dr*2)].equals("*")) return true;
                    else if(Math.abs(dc)==2&&board[a+(dc)].equals("*")&&board[a+(dc/2)].equals("*")) return true;
                    else return false;
                }
                else {
                    //                   System.out.println(4);
                    return false;
                }
            default:
                return false;
        }
    }

    public boolean isGameOver() {
        // Check if the game is over (tie, stalemate, or either side wins)
        if (isWin()){
            if(turn=='w')   lblWin.setText("Black Wins!");
            else  lblWin.setText("White Wins!");
            return true;
        }
        else return false;
    }

    public boolean isWin() {
        int count =0;
        for(int i=0;i<20;i++){
            if(board[i].equals("K")||board[i].equals("k")) count++;
        }
        return count!=2;
    }

    public boolean isItem() {
        if(!item) return false;
        for(int i=0;i<20;i++){
            if(board[i].equals("?")) return false;
        }
        lblMoves.setText("Use Item : Full Swing");
        board[4]="*";
        board[5]="*";
        board[6]="*";
        item=false;
        return true;
    }

}
