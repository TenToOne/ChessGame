package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Chess extends JFrame implements ActionListener {
    static String[] board = new String[20];
    static char turn = 'w';
    boolean item=false;
    boolean active = false;
    boolean  skill=false;
    boolean canSkill = true;
    static int[] exp = {0,0,0,0};
    static int[] startexp;
    static int stage;
    int itemnum =1;
    int t=0;
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

    ImageIcon back;

    Image backI;

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
            switch (itemnum) {
                case 1 :
                    int add = 0;
                    if (turn =='w' && skill) add = 8;
                    btnSquare[4 + add].setBackground(Color.BLUE);
                    btnSquare[4 + add].setContentAreaFilled(true);
                    btnSquare[4 + add].setBorderPainted(true);
                    btnSquare[5 + add].setBackground(Color.BLUE);
                    btnSquare[5 + add].setContentAreaFilled(true);
                    btnSquare[5 + add].setBorderPainted(true);
                    btnSquare[6 + add].setBackground(Color.BLUE);
                    btnSquare[6 + add].setContentAreaFilled(true);
                    btnSquare[6 + add].setBorderPainted(true);
                break;
                case 3 :
                    btnSquare[t].setBackground(Color.BLUE);
                    btnSquare[t].setContentAreaFilled(true);
                    btnSquare[t].setBorderPainted(true);
                break;
                case 4 :
                    btnSquare[9].setBackground(Color.BLUE);
                    btnSquare[9].setContentAreaFilled(true);
                    btnSquare[9].setBorderPainted(true);
                break;
            }
            active=false;
        }
    }

    public Chess(int stage,int []exp) throws IOException {
        startexp = exp.clone();
        turn = 'w';
        if(stage>=4){
            back = new ImageIcon("./image/back2.jpg");
        }
        else{
            back = new ImageIcon("./image/Back.jpg");
        }
        backI= back.getImage();
        pnlGrid.setLayout(new GridLayout(5,4));
       this.stage=stage;
       switch(stage) {
           case 1:
           case 4:
           case 5:
           case 7:
           case 8:
               skill = true;
               canSkill = false;
           break;
           case 2:
           case 10:
               canSkill = false;
               item = false;
           break;
       }
       if(stage>5){
           btnSkill = new JButton("SKILL : LEVEL UP");
       }
       if(stage>7) {
            btnSkill = new JButton("SKILL : NEW FAMILY");
        }
       board = new SetBoard().setboard(stage,exp);
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
        if(skill) btnSkill.setBackground(Color.ORANGE);
        else btnSkill.setBackground(Color.GRAY);
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
                isUsedSkill();
                clearBoard();
                try {
                    display();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (isGameOver()) {
                    lblStatus.setText("");
                    try {
                        new Chess(stage,startexp).display();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    dispose();
                }
                lblStatus.setText("");
            }
        }
        else {
            if (e.getSource() == btnSkill) {
                if(skill) {
                    if(stage<=5) {
                        more = true;
                        lblMoves.setText("Use Skill : One More");
                        JOptionPane.showMessageDialog(null, "Used Skill : One More \n 한 턴 더 행동합니다.");
                    }
                    else if(stage<8){
                        lblMoves.setText("Use Skill : Level Up");
                        JOptionPane.showMessageDialog(null, "Used Skill : Level Up \n 말의 레벨이 올라갑니다.");
                        int up = (int)Math.random()*2+2;
                        for(int i=0;i<4;i++){
                            exp[i]+=up;
                        }
                        for(int i=0;i<20;i++){
                            switch(board[i].charAt(0)) {
                                case 'P':
                                        board[i] = "P" + (exp[0] / 2 + 1);
                                    break;
                                case 'R':
                                        board[i] = "R" + (exp[1] / 2 + 1);
                                    break;
                                case 'B':
                                        board[i] = "B" + (exp[2] / 2 + 1);
                                    break;
                                case 'N':
                                    board[i] = "N" + (exp[3] / 2 + 1);
                                    break;
                            }
                        }
                        try {
                            display();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else{
                        lblMoves.setText("Use Skill : New Family");
                        JOptionPane.showMessageDialog(null, "Used Skill : New Family \n 새로운 말이 들어옵니다.");
                        ArrayList<Integer> random = new ArrayList<>();
                        for(int i=0;i<20;i++){
                            if(board[i].equals("*")) random.add(i);
                        }
                        board[random.get((int)(Math.random()*random.size()))] = "D";
                        try {
                            display();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    skill = false;
                    btnSkill.setBackground(Color.GRAY);
                }
                else{
                    JOptionPane.showMessageDialog(null, "스킬을 사용 할 수 없습니다.");
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
                        isUsedSkill();
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
                        try {
                            new Chess(stage+1,exp).display();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        dispose();
                    }
                }
            }
        }
    }

    private void isUsedSkill() {
        if(!canSkill) return;
        for (int i = 0; i < 20; i++) {
            if (board[i].equals("+")) return;
        }
        if(turn=='b') {
            canSkill = false;
            skill = true;
            btnSkill.setBackground(Color.ORANGE);
            JOptionPane.showMessageDialog(null, "스킬을 획득 했습니다.");
        }
        else{
            canSkill = false;
            skill = true;
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
                case 'k':
                    name+=("BB");
                    if(stage>=4) name+="_2";
                    break;
                case 'B': name+=("gm");break;
                case 'P': name+=("gp");break;
                case 'N': name+=("m");break;
                case 'R': name+=("f");break;
                case 'K': name+=("WB");break;
                case 'D': name+=("doggy");break;
                case '?': name+=("item");break;
                case '+': name+=("skill");break;
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
                    switch(board[a].charAt(0)){
                        case 'P' :
                            exp[0]++;
                            if(exp[0]%2==0){
                                board[a] = "P"+(exp[0]/2+1);
                            }
                            break;
                        case 'R' :
                            exp[1]++;
                            if(exp[1]%2==0){
                                board[a] = "R"+(exp[1]/2+1);
                            }
                            break;
                        case 'B' :
                            exp[2]++;
                            if(exp[2]%2==0){
                                board[a] = "B"+(exp[2]/2+1);
                            }
                            break;
                        case 'N' :
                            exp[3]++;
                            if(exp[3]%2==0){
                                board[a] = "N"+(exp[3]/2+1);
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
        if(stage>=7) itemnum = (int)(Math.random()*3)+1;
        else if(stage>1) itemnum = (int)(Math.random()*4)+1;
        switch (itemnum) {
            case 1 :
                lblMoves.setText("Use Item : Full Swing");
                JOptionPane.showMessageDialog(null, "Get Item : Full Swing \n 전방의 3칸의 말을 처리합니다.");
                if (turn == 'b') {
                    board[4] = "*";
                    board[5] = "*";
                    board[6] = "*";
                } else {
                    board[4 + 8] = "*";
                    board[5 + 8] = "*";
                    board[6 + 8] = "*";
                }
            break;
            case 2 :
                lblMoves.setText("Use Item : Training");
                JOptionPane.showMessageDialog(null, "Get Item : Training \n 일정 확률로 말이 레벨업 합니다.");
                if(true){
                    if(!board[9].equals("K")&&!board[9].equals("K")) {
                        String upgrade = "";
                        upgrade+=board[9].charAt(0);
                        upgrade+=((int)board[9].charAt(1)-48+1);
                        board[9] = upgrade;
                    }
                }
                for(String s:board){
                    System.out.print(s+" ");
                }
            break;
            case 3 :
                lblMoves.setText("Use Item : One Shot");
                JOptionPane.showMessageDialog(null, "Get Item : One Shot \n 상대말 하나를 무작위로 제거 합니다.");
                ArrayList<Integer> tagets = new ArrayList<Integer>();
                switch (turn){
                    case 'w':
                        for(int i=0;i<20;i++){
                            if(Character.isUpperCase(board[i].charAt(0))&&!board[i].equals("K")){
                                tagets.add(i);
                            }
                        }
                    break;
                    case 'b':
                        for(int i=0;i<20;i++){
                            if(Character.isLowerCase(board[i].charAt(0))&&!board[i].equals("k")){
                                tagets.add(i);
                            }
                        }
                    break;
                }
                if(!tagets.isEmpty()) {
                    t = tagets.get((int) (Math.random() * tagets.size()));
                    board[t]="*";
                }
            break;
            case 4 :
                lblMoves.setText("Use Item : Bomb");
                JOptionPane.showMessageDialog(null, "Get Item : Bomb \n 자폭합니다.");
                board[9]="*";
                break;
        }
        item = false;
        if (!item) {
            active = true;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Chess c = new Chess(8,exp);
        try {
            c.display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
