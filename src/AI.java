import java.io.IOException;
import java.util.ArrayList;

public class AI{

    public int doAI(String[] board, ArrayList<Integer> aPlace, ArrayList<Integer> bPlace, int stage){
        int maxprice=-999;
        int choice=0;
        int[] price = new int[aPlace.size()];
        ArrayList<Integer> choices = new ArrayList<Integer>();
        if(stage==1) choice=(int)(Math.random()*aPlace.size());
        if(stage>=2){
            for(int i=0;i<aPlace.size();i++){
                int j = bPlace.get(i);
                for(int k=0;k<board.length;k++){
                    if(new Move().validateMove(k,j,'w',board)){
                        price[i]=-15;
                    }
                }
                switch (board[j]){
                    case "K" : price[i]=999999; break;
                    case "?" : price[i]=10; break;
                    case "R2" : price[i]=8; break;
                    case "B2" : price[i]=8; break;
                    case "N2" : price[i]=10; break;
                    case "P2" : price[i]=3; break;
                    case "R1" : price[i]=4; break;
                    case "B1" : price[i]=4; break;
                    case "N1" : price[i]=5; break;
                    case "P1" : price[i]=1; break;
                }
            }
        }
        for(int i=0;i<price.length;i++){
            if(price[i]>maxprice) {
                maxprice=price[i];
                choices.clear();
                choices.add(i);
            }
            else if(price[i]==maxprice){
                choices.add(i);
            }
            System.out.println(aPlace.get(i)+"->"+bPlace.get(i)+":"+price[i]);
        }
        choice=choices.get((int)(Math.random()*choices.size()));
        System.out.println(aPlace.get(choice)+"->"+bPlace.get(choice)+":"+price[choice]+"★");
        return choice;
    }
}
