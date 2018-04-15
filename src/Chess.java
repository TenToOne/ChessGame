import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Scanner;

public class Chess extends JFrame implements ActionListener {
    static String[] board = {"b1","n1","k","b1","*","*","p1","*","*","*","*","*","*","P1","*","*","B1","K","N1","B1"};
    static char turn = 'w';
    static String moves = "";

    public static void main(String[] args) {
        // Create chess board object and displays it
        Chess c = new Chess();
        while(!isGameOver()){
                for (int i = 0; i < 20; i++) {
                    System.out.print(board[i] + "\t\t|");
                    if ((i + 1) % 4 == 0) System.out.println();
                }
                doPlay();
        }
        for (int i = 0; i < 20; i++) {
            System.out.print(board[i] + "\t\t|");
            if ((i + 1) % 4 == 0) System.out.println();
        }
        System.out.println("Game Over");
    }

    public static void doPlay() {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        doMove(a - 1, b - 1, turn);
    }

    public static void doMove(int a, int b, char colour) {
        // Perform move if it is a valid move and the king is not moving into check
        if (validateMove(a, b, colour)) {
            board[b] = board[a];
            board[a]="*";
            swapTurn();
        }
        else {
            System.out.println("Invalid move");
        }
    }

    public static void swapTurn() {
        // Swap turns depending on the current player
        if (turn == 'w') {
            turn = 'b';
            System.out.println("Black's turn.");
        }
        else {
            turn = 'w';
            System.out.println("White's turn.");
        }
    }

    public static boolean validateMove(int a, int b, char colour) {
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
 //                   System.out.println(4);
                    return false;
                }
            case "N1":
            case "n1":
                // Bishops can move diagonally 1 or 2 spaces in any direction
                // Check if moving diagonally
                if (Math.abs(dr)+Math.abs(dc)==3&&(Math.abs(dr)==1|Math.abs(dr)==2)) {
                    return true;
                }
                else {
                    //                   System.out.println(4);
                    return false;
                }
            default:
                return false;
        }
    }

    public static boolean isGameOver() {
        // Check if the game is over (tie, stalemate, or either side wins)
        if (isWin()){
            if(turn=='w')  System.out.println("Black Wins!");
            else System.out.println("White Wins!");
            return true;
        }
        else return false;
    }

    public static boolean isWin() {
        int count =0;
        for(int i=0;i<20;i++){
            if(board[i].equals("K")||board[i].equals("k")) count++;
        }
        return count!=2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
