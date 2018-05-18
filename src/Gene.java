package src;

public class Gene {
    private StringBuilder gene;
    private int goodness = 0;


    public Gene(){
        makeGene();
        testGene();
//        System.out.println(goodness);
    }

    public Gene(String gene){
        this.gene = new StringBuilder(gene);
        testGene();
//        System.out.println(goodness);
    }

    void makeGene(){
        gene = new StringBuilder();
        for(int i=0;i<20;i++){
            if(Math.random()>0.5) gene.append(0);
            else gene.append(1);
        }
    }

    int getGoodness(){
        return goodness;
    }

    public int testGene(){
//        System.out.println(gene);
        for(int i=0;i<4;i++){
            String sub = (String) gene.subSequence(i*5,(i+1)*5);
//            System.out.println(sub);
            switch (sub){
                case "00000" : goodness+=1; break;
                case "00001" : goodness+=2; break;
                case "00010" : goodness+=3; break;
                case "00011" : goodness+=4; break;
                case "00100" : goodness+=5; break;
                case "00101" : goodness+=6; break;
                case "00110" : goodness+=7; break;
                case "00111" : goodness+=8; break;
                case "01000" : goodness+=4; break;
                case "01001" : goodness+=6; break;
                case "01010" : goodness+=7; break;
                case "01011" : goodness+=8; break;
                case "01100" : goodness+=9; break;
                case "01101" : goodness+=10; break;
                case "01110" : goodness+=12; break;
                case "01111" : goodness+=16; break;
                case "10000" : goodness+=4; break;
                case "10001" : goodness+=6; break;
                case "10010" : goodness+=7; break;
                case "10011" : goodness+=8; break;
                case "10100" : goodness+=9; break;
                case "10101" : goodness+=10; break;
                case "10110" : goodness+=12; break;
                case "10111" : goodness+=16; break;
                case "11000" : goodness+=2; break;
                case "11001" : goodness+=3; break;
                case "11010" : goodness+=4; break;
                case "11011" : goodness+=5; break;
                case "11100" : goodness+=8; break;
                case "11101" : goodness+=9; break;
                case "11110" : goodness+=12; break;
                case "11111" : goodness+=16; break;
            }
        }
        return goodness;
    }

    void modification(double Pm){
        if(Math.random()<Pm){
            int i =(int)(Math.random()*20);
            if(gene.charAt(i)==0) gene.setCharAt(i,'1');
            else gene.setCharAt(i,'0');
            testGene();
        }
    }

    public String toString(){
        return gene.toString()+":"+getGoodness();
    }
}
