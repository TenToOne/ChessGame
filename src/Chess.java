
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Chess extends JFrame implements ActionListener {
    static String[] board = {"p1","p1","k","p1","*","*","p1","*","*","?","*","*","*","P1","*","*","R1","K","N1","B1"};
    static char turn = 'w';
    boolean item=true;
    boolean active = false;
    boolean  skill=true;
    static int[] exp = {0,0,0,0};

    // GUI STUFF ================================================================================
    JLabel lblLevel = new JLabel("Level1");
    JPanel pnlGrid = new JPanel(new GridLayout(5,4));
    JPanel pnlFlow = new JPanel(new FlowLayout());
    JLabel lblStatus = new JLabel();
    JLabel lblTurn = new JLabel("You're turn : selcet your fmaily");
    JLabel lblWin = new JLabel();
    JLabel lblMoves = new JLabel();
    JButton btnSquare[] = new JButton[20];
    JButton btnSkill = new JButton("SKILL : ONE MORE");

    Font f = new Font("DIALOG", Font.PLAIN, 20);


    public void clearBoard(){
        for (int i=0; i<20; i++) {
            if (i % 8 == 0||i % 8 == 2||i % 8 == 5||i % 8 == 7) {
                btnSquare[i].setBackground(Color.ORANGE);
            }
            else {
                btnSquare[i].setBackground(Color.WHITE);
            }
        }
        if(active){
            int add = 0;
            if(turn=='w'&&skill) add=8;
            btnSquare[4+add].setBackground(Color.BLUE);
            btnSquare[5+add].setBackground(Color.BLUE);
            btnSquare[6+add].setBackground(Color.BLUE);
            active=false;
        }
    }

    public Chess() throws IOException {
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

        Container ctp = getContentPane();
        ctp.setLayout(null);

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
                doAI();
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
                    }
                }
            }
        }
    }

    public void doAI(){
        ArrayList<Integer> aPlace = new ArrayList<Integer>();
        ArrayList<Integer> bPlace = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (validateMove(i, j, turn)) {
                    aPlace.add(i);
                    bPlace.add(j);
                }
            }
        }
        int random = (int)(Math.random()*aPlace.size());
        doMove(aPlace.get(random),bPlace.get(random), turn);

    }

    public void display() throws IOException {
        // Display Unicode chess characters
        for (int i=0; i<20; i++) {
            switch (board[i]) {
                case "b1": btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("m1.png"))));break;
                case "p1": btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("m2.png"))));break;
                case "n1": btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("m3.png"))));break;
                case "r1": btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("m4.png"))));break;
                case "k": btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("BB.png"))));break;
                case "B1":
                case "B2":
                    btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("gm.png"))));break;
                case "P1":
                case "P2":
                    btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("gp.png"))));break;
                case "N1":
                case "N2":
                    btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("m.png"))));break;
                case "R1":
                case "R2":
                    btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("f.png"))));break;
                case "K":
                    btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("WB.png"))));break;
                case "?":
                    btnSquare[i].setIcon(new ImageIcon(ImageIO.read(new java.io.File("item.png"))));break;
                default:
                    btnSquare[i].setIcon(new ImageIcon());

            }
        }
    }

    public void doMove(int a, int b, char colour) {
        // Perform move if it is a valid move and the king is not moving into check
        if (validateMove(a, b, colour)) {
            if(colour=='w'){
                if(board[b].charAt(0)!='*'){
                    switch(board[a]){
                        case "P1" : exp[0]++; break;
                        case "R1" : exp[1]++; break;
                        case "B1" : exp[2]++; break;
                        case "N1" : exp[3]++; break;
                    }
                }
                for(int i=0;i<4;i++){
                    if(exp[i]==5) {
                        switch (i) {
                            case 0 : if(board[a]=="P1") board[a] = "P2"; break;
                            case 1 : if(board[a]=="R1") board[a] = "R2"; break;
                            case 2 : if(board[a]=="B1") board[a] = "B2"; break;
                            case 3 : if(board[a]=="N1") board[a] = "N2"; break;
                        }
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

    public boolean validateMove(int a, int b, char colour) {
        int r1, c1, r2, c2, dr, dc;

        // Convert start and end positions to rows and columns
        r1 = a / 4 + 1;		// Start row
        c1 = a % 4 + 1;		// Start column
        r2 = b / 4 + 1;		// End row
        c2 = b % 4 + 1;		// End column
        dr = r2 - r1;		// Change of squares between start and end rows
        dc = c2 - c1;		// Change of squares between start and end columns

        if (colour == 'w') {
            if (Character.isLowerCase(board[a].charAt(0)) || Character.isUpperCase(board[b].charAt(0))) {
                return false;
            }
        }
        else if (colour == 'b') {
            if (Character.isUpperCase(board[a].charAt(0)) || Character.isLowerCase(board[b].charAt(0))) {
                return false;
            }
        }

        switch (board[a]) {
            case "*":
                return false;
            case "P1":
                if ((c1 == c2) && (dr == -1)) {
                    return true;
                }
                else {
                    return false;
                }
            case "p1":
                if ((c1 == c2) && (dr == 1)) {
                    return true;
                }
                else {
                    return false;
                }
            case "P2" :
                if(Math.abs(dc)<=1&&dr==-1)
                    return true;
                else return false;
            case "K":
            case "k":
                if ((dr >= -1) && (dr <= 1) && (dc >= -1) && (dc <= 1)) {
                    return true;
                }
                else {
   //                 System.out.println(3);
                    return false;
                }
            case "B1":
            case "b1":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)==1) {
                        return true;
                }
                else {
                    return false;
                }
            case "B2":
                if (Math.abs(dr)==Math.abs(dc)&&Math.abs(dr)<=2) {
                    if(Math.abs(dr)==1) return  true;
                    else if(Math.abs(dr)==2&&board[a+(dr*2)+(dc/2)].equals("*")) return  true;
                    else return false;
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
                    return false;
                }
            case "N2":
                if (Math.abs(dr)+Math.abs(dc)==3&&(Math.abs(dr)==1|Math.abs(dr)==2)) {
                    return true;
                }
                else {
                    return false;
                }
            case "R1":
            case "r1":
                if (Math.abs(dr)+Math.abs(dc)==1) {
                    return true;
                }
                else {
                    return false;
                }
            case "R2":
                if ((dc==0&&Math.abs(dr)<=2)||(dr==0&&Math.abs(dc)<=2)) {
                    if(Math.abs(dr)+Math.abs(dc)==1) return true;
                    else if(Math.abs(dr)==2&&board[a+dr*2].equals("*")) return  true;
                    else if(Math.abs(dc)==2&&board[a+dc/2].equals("*")) return  true;
                    else return false;
                }
                else {
                    return false;
                }
            default:
                return false;
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
        Chess c = new Chess();
        try {
            c.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
