public class SetBoard {
    public String[] setboard(int stage,int[] level){
        if(stage==1)
            return new String[]{"p1","p1","k","p1","*","*","p1","*","*","?","*","*","*","P1","*","*","R1","K","N1","B1"};
        String[] board = {"b1","n1","k","r1","*","*","p1","*","*","?","*","*","*","P1","*","*","R1","K","N1","B1"};
        String enermy = Heredity.set();
        System.out.println(enermy);
        return board;
    }
}
