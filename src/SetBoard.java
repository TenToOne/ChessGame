package src;

public class SetBoard {
    public String[] setboard(int stage,int[] exp){
        if(stage==1)
//              return new String[]{"k","*","*","*","*","*","*","*","B8","*","B8","*","*","*","*","*","*","*","K","*"};
                return new String[]{"p1","p1","k","p1","*","*","p1","*","*","?","*","*","*","P1","*","*","R1","K","N1","B1"};
        String[] board = {"*","*","k","*","*","*","*","*","*","*","*","*","*","*","*","*","*","K","*","*"};
        String enermy = Heredity.set(stage);
        System.out.println(enermy);
        for(int i=0;i<4;i++){
            String sub = (String) enermy.subSequence(i*5,(i+1)*5);
            int place = i;
            if(place==2) place++;
            else if(place==3) place=6;
//            System.out.println(sub);
            switch (sub){
                case "00000" : board[place]="p1"; break;
                case "00001" : board[place]="p2"; break;
                case "00010" : board[place]="p3"; break;
                case "00011" : board[place]="p4"; break;
                case "00100" : board[place]="p5"; break;
                case "00101" : board[place]="p6"; break;
                case "00110" : board[place]="p7"; break;
                case "00111" : board[place]="p8"; break;
                case "01000" : board[place]="b1"; break;
                case "01001" : board[place]="b2"; break;
                case "01010" : board[place]="b3"; break;
                case "01011" : board[place]="b4"; break;
                case "01100" : board[place]="b5"; break;
                case "01101" : board[place]="b6"; break;
                case "01110" : board[place]="b7"; break;
                case "01111" : board[place]="b8"; break;
                case "10000" : board[place]="r1"; break;
                case "10001" : board[place]="r2"; break;
                case "10010" : board[place]="r3"; break;
                case "10011" : board[place]="r4"; break;
                case "10100" : board[place]="r5"; break;
                case "10101" : board[place]="r6"; break;
                case "10110" : board[place]="r7"; break;
                case "10111" : board[place]="r8"; break;
                case "11000" : board[place]="n1"; break;
                case "11001" : board[place]="n2"; break;
                case "11010" : board[place]="n3"; break;
                case "11011" : board[place]="n4"; break;
                case "11100" : board[place]="n5"; break;
                case "11101" : board[place]="n6"; break;
                case "11110" : board[place]="n7"; break;
                case "11111" : board[place]="n8"; break;
            }
        }
        if(stage==3) board[10]="+";
        if(stage==3) board[9]="?";
        board[13]="P"+(exp[0]/2+1);
        board[16]="R"+(exp[1]/2+1);
        board[18]="N"+(exp[2]/2+1);
        board[19]="B"+(exp[3]/2+1);
        return board;
    }
}
