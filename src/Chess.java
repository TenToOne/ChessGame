package src;

import src.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Chess extends JFrame implements ActionListener {
    static String[] board = {"b1","n1","k","r1","*","*","p1","*","*","?","*","*","*","P1","*","*","R1","K","N1","B1"};
    static char turn = 'w';
    boolean item=false;
    boolean active = false;
    boolean  skill=true;
    static int[] exp = {0,0,0,0};
    static int[] level = {1,1,1,1};
    static int stage;
    Move m = new Move();

    // GUI STUFF ================================================================================
    JLabel lblLevel = new JLabel();
    JPanel pnlFlow = new JPanel(new FlowLayout());
    JLabel lblStatus = new JLabel();
    JLabel lblTurn = new JLabel("You're turn : selcet your fmaily");
    JLabel lblWin = new JLabel();
    JLabel lblMoves = new JLabel();
    JButton btnSquare[] = new JButton[20];
    JButton btnSkill = new JButton("SKILL : ONE MORE");

    Font f = new Font("DIALOG", Font.PLAIN, 20);

    JPanel pnlGrid =  new backG();

    ImageIcon back = new ImageIcon("./image/Back.jpg");
    Image backI = back.getImage();

    class backG extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backI, 0, 0, getWidth(), getHeight(), this);
        }
    }


    public void clearBoard(){
        for (int i=0; i<20; i++) {
            if (i % 8 == 0||i % 8 == 2||i % 8 == 5||i % 8 == 7) {
                btnSquare[i].setBackground(Color.orange);
                btnSquare[i].setContentAreaFilled(false);
                btnSquare[i].setBorderPainted(false);
            }
            else {
                btnSquare[i].setBackground(Color.WHITE);
                btnSquare[i].setContentAreaFilled(false);
                btnSquare[i].setBorderPainted(false);
            }
        }
        if(active){
            int add = 0;
            if(turn=='w'&&skill) add=8;
            btnSquare[4+add].setBackground(Color.BLUE);
            btnSquare[4+add].setContentAreaFilled(true);
            btnSquare[4+add].setBorderPainted(true);
            btnSquare[5+add].setBackground(Color.BLUE);
            btnSquare[5+add].setContentAreaFilled(true);
            btnSquare[5+add].setBorderPainted(true);
            btnSquare[6+add].setBackground(Color.BLUE);
            btnSquare[6+add].setContentAreaFilled(true);
            btnSquare[6+add].setBorderPainted(true);
            active=false;
        }
    }

    public Chess(int stage) throws IOException {
        pnlGrid.setLayout(new GridLayout(5,4));
       this.stage=stage;
       board = new SetBoard().setboard(stage,level);
       for(int i=0;i<20;i++){
           if(board[i].equals("?")){
               item=true;
               break;
           }
       }
        for (int i=0; i<20; i++) {
            btnSquare[i] = new JButton();
            btnSquare[i].setFont(f);

            if (i % 8 == 0||i % 8 == 2||i % 8 == 5||i % 8 == 7) {
                btnSquare[i].setBackground(Color.ORANGE);
                btnSquare[i].setContentAreaFilled(false);
                btnSquare[i].setBorderPainted(false);
            }
            else {
                btnSquare[i].setBackground(Color.WHITE);
                btnSquare[i].setContentAreaFilled(false);
                btnSquare[i].setBorderPainted(false);
            }
            btnSquare[i].addActionListener(this);
            pnlGrid.add(btnSquare[i]);
        }

        Container ctp = getContentPane();
        ctp.setLayout(null);

        lblLevel.setText("Level"+stage);
        pnlFlow.add(lblLevel);
        pnlFlow.add(pnlGrid);
        pnlFlow.add(btnSkill);
        pnlFlow.add(lblTurn);
        pnlFlow.add(lblStatus);

        btnSkill.addActionListener(this);

        lblStatus.setForeground(Color.RED);
        lblStatus.setFont(f);
        lblWin.setForeground(Color.BLUE);
        lblWin.setFont(f);
        lblLevel.setForeground(Color.WHITE);
        lblLevel.setFont(f);
        lblTurn.setForeground(Color.WHITE);
        lblTurn.setFont(f);
        lblMoves.setForeground(Color.WHITE);
        lblMoves.setFont(f);
        btnSkill.setBackground(Color.ORANGE);
        try {
            display();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle("ChessGame");
        setResizable(false);
        setSize(540, 960);
        pnlGrid.setPreferredSize(new Dimension(540,800));
        btnSkill.setPreferredSize(new Dimension(540,50));
        btnSkill.setFont(f);
        pnlFlow.setBackground(new Color(0x662500));



        setContentPane(pnlFlow);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Display legal moves if they exist
    }

    int a=-1,b=-1;
    boolean more = false;
    public void actionPerformed(ActionEvent e) {
        if(turn=='b'){
            if (!isGameOver()) {
                try {
                    doAI();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (!isItem()) lblMoves.setText("");
                clearBoard();
                try {
                    display();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (isGameOver()) {
                    lblStatus.setText("");
                }
                lblStatus.setText("");
            }
        }
        else {
            if (e.getSource() == btnSkill) {
                if(skill) {
                    more = true;
                    lblMoves.setText("Use Skill : One More");
                    JOptionPane.showMessageDialog(null, "Used Skill : One More \n 한 턴 더 행동합니다.");
                    skill = false;
                }
                else{
                    JOptionPane.showMessageDialog(null, "이미 스킬을 사용하셨습니다.");
                }
            } else {
                for (int i = 0; i < 20; i++) {
                    if (e.getSource() == btnSquare[i]) {
                        if (a == -1) a = i + 1;
                        else b = i + 1;
                        try {
                            display();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                if (a != -1 && b == -1) {
                    int count = 0;
                    for (int j = 0; j < 20; j++) {
                        // Destination and source squares must be different
                        if (a - 1 != j) {
                            // Add to the list of valid moves for the current player
                            if (m.validateMove(a - 1, j, turn,board)) {
                                btnSquare[j].setBackground(Color.RED);
                                btnSquare[j].setContentAreaFilled(true);
                                btnSquare[j].setBorderPainted(true);
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
                        if (more) {
                            swapTurn();
                            more = false;
                        }
                        clearBoard();
                        try {
                            display();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    // Display message if game is over
                    if (isGameOver()) {
                        lblStatus.setText("");
                        dispose();
                        try {
                            new Chess(stage+1).display();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void doAI() throws IOException {
        ArrayList<Integer> aPlace = new ArrayList<Integer>();
        ArrayList<Integer> bPlace = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (m.validateMove(i, j, turn,board)) {
                    aPlace.add(i);
                    bPlace.add(j);
                }
            }
        }
        int choice = new AI().doAI(board,aPlace,bPlace,stage);
        doMove(aPlace.get(choice),bPlace.get(choice), turn);

    }

    public void display() throws IOException {
        // Display Unicode chess characters
        for (int i=0; i<20; i++) {
            if(board[i].equals("*")){
                btnSquare[i].setIcon(new ImageIcon()); continue;
            }
            String name = "./image/";
            switch (board[i].charAt(0)) {
                case 'b': name+=("m4");break;
                case 'p': name+=("m2");break;
                case 'n': name+=("m3");break;
                case 'r': name+=("m1");break;
                case 'k': name+=("BB");break;
                case 'B': name+=("gm");break;
                case 'P': name+=("gp");break;
                case 'N': name+=("m");break;
                case 'R': name+=("f");break;
                case 'K': name+=("WB");break;
                case '?': name+=("item");break;
            }
            if(board[i].length()>1){
                int l = (int)board[i].charAt(1)-48;
                if(l>1){
                    name+="_"+l;
                }
            }
            btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File(name+".png"))));
        }
    }

    public void doMove(int a, int b, char colour) {
        // Perform move if it is a valid move and the king is not moving into check
        if (m.validateMove(a, b, colour,board)) {
            if(colour=='w'){
                if(board[b].charAt(0)!='*'){
                    switch(board[a]){
                        case "P1" :
                            exp[0]++;
                            if(exp[0]==2){
                                level[0]++;
                                board[a] = "P2";
                            }
                            break;
                        case "R1" :
                            exp[1]++;
                            if(exp[1]==2){
                                level[1]++;
                                board[a] = "R2";
                            }
                            break;
                        case "B1" :
                            exp[2]++;
                            if(exp[2]==2){
                                level[2]++;
                                board[a] = "B2";
                            }
                            break;
                        case "N1" :
                            exp[3]++;
                            if(exp[3]==2){
                                level[3]++;
                                board[a] = "N2";
                            }
                            break;
                    }
                }
            }
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
            lblTurn.setText("Enemy's turn : press to any button");
        }
        else {
            turn = 'w';
            lblTurn.setText("You're turn : selcet your fmaily");
        }
    }

    public boolean isGameOver() {
        if (isWin()){
            if(turn=='b')   JOptionPane.showMessageDialog(null,"YOU WIN!!");
            else  JOptionPane.showMessageDialog(null,"YOU LOSE!!");
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
        JOptionPane.showMessageDialog(null,"Get Item : Full Swing \n 전방의 3칸의 말을 처리합니다.");
        if(turn=='b') {
            board[4] = "*";
            board[5] = "*";
            board[6] = "*";
        }
        else {
            board[4+8] = "*";
            board[5+8] = "*";
            board[6+8] = "*";
        }
        item=false;
        if(!item){
            active =true;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Chess c = new Chess(1);
        try {
            c.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
