package src;

import java.io.IOException;
import java.util.ArrayList;

public class AI{

    public int doAI(String[] board, ArrayList<Integer> aPlace, ArrayList<Integer> bPlace, int stage){
        int maxprice=-999;
        int choice=0;
        int[] price = new int[aPlace.size()];
        ArrayList<Integer> choices = new ArrayList<Integer>();
        if(stage==1) choice=(int)(Math.random()*aPlace.size());  // Level 0 AI 무작위 선택
        if(stage>=2){ // Level 1 AI 상대말 잡기
            for(int i=0;i<aPlace.size();i++){
                int j = bPlace.get(i);
                for(int k=0;k<board.length;k++){
                    if(new Move().validateMove(k,j,'w',board)){
                        price[i]=-15;
                    }
                }
                switch (board[j]){
                    case "K" : price[i]=999999; break;
                    case "?" : price[i]=20; break;
                    case "P1" : price[i]=1; break;
                    case "P2" : price[i]=2; break;
                    case "P3" : price[i]=3; break;
                    case "P4" : price[i]=4; break;
                    case "P5" : price[i]=5; break;
                    case "P6" : price[i]=6; break;
                    case "P7" : price[i]=7; break;
                    case "P8" : price[i]=8; break;
                    case "R1" : price[i]=4; break;
                    case "R2" : price[i]=6; break;
                    case "R3" : price[i]=7; break;
                    case "R4" : price[i]=8; break;
                    case "R5" : price[i]=9; break;
                    case "R6" : price[i]=10; break;
                    case "R7" : price[i]=12; break;
                    case "R8" : price[i]=16; break;
                    case "B1" : price[i]=4; break;
                    case "B2" : price[i]=6; break;
                    case "B3" : price[i]=7; break;
                    case "B4" : price[i]=8; break;
                    case "B5" : price[i]=9; break;
                    case "B6" : price[i]=10; break;
                    case "B7" : price[i]=12; break;
                    case "B8" : price[i]=16; break;
                    case "N1" : price[i]=2; break;
                    case "N2" : price[i]=3; break;
                    case "N3" : price[i]=4; break;
                    case "N4" : price[i]=5; break;
                    case "N5" : price[i]=8; break;
                    case "N6" : price[i]=9; break;
                    case "N7" : price[i]=15; break;
                    case "N8" : price[i]=18; break;
                }// 상대말을 잡을 수 있을 경우 상대 말의 가치에 따라 가중치가 결정
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
//            System.out.println(aPlace.get(i)+"->"+bPlace.get(i)+":"+price[i]);
        }
        choice=choices.get((int)(Math.random()*choices.size()));
//        System.out.println(aPlace.get(choice)+"->"+bPlace.get(choice)+":"+price[choice]+"★");
        return choice;
    }
}
