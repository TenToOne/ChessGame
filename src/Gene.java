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
        for(int i=0;i<16;i++){
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
            String sub = (String) gene.subSequence(i*4,(i+1)*4);
//            System.out.println(sub);
            switch (sub){
                case "0000" : goodness+=1; break;
                case "0001" : goodness+=3; break;
                case "0010" : goodness+=6; break;
                case "0011" : goodness+=8; break;
                case "0100" : goodness+=4; break;
                case "0101" : goodness+=8; break;
                case "0110" : goodness+=12; break;
                case "0111" : goodness+=16; break;
                case "1000" : goodness+=4; break;
                case "1001" : goodness+=8; break;
                case "1010" : goodness+=12; break;
                case "1011" : goodness+=16; break;
                case "1100" : goodness+=5; break;
                case "1101" : goodness+=9; break;
                case "1110" : goodness+=12; break;
                case "1111" : goodness+=16; break;
            }
        }
        return goodness;
    }

    void modification(double Pm){
        if(Math.random()<Pm){
            int i =(int)(Math.random()*16);
            if(gene.charAt(i)==0) gene.setCharAt(i,'1');
            else gene.setCharAt(i,'0');
            testGene();
        }
    }

    public String toString(){
        return gene.toString();
    }
}
