package src;

import src.Gene;

import java.util.ArrayList;

public class Heredity {
    static final double Pc=0.7;
    static final double Pm = 0.0001;
    static final int N = 100;
    static final int Age = 100;

    static public String set(int stage){
        ArrayList<Gene> genes = new ArrayList<Gene>();
        int target = 30;
        int[] goodness = new int[N];
        double[] roulette = new double[N];
        int goodsum=0;
        int[] array = new int[Age];
        switch (stage){
            case 2 : target=11; break;
            case 3 : target=11; break;
            case 4 : target=20; break;
        }
        for(int i=0;i<N;i++){
            genes.add(new Gene());
            int good=genes.get(i).getGoodness();
            goodness[i]=good;
            goodsum+=good;
        }
        double avg = (double) goodsum/N;


        for(int age=0;age<Age-1;age++) {
            ArrayList<Gene> newGenes = new ArrayList<Gene>();
            double rouletteSum=0;
            for(int i=0;i<N;i++){
                goodness[i] = genes.get(i).getGoodness();
                if((goodness[i]-target)!=0){
                    int sum = ((goodness[i]-target))*((goodness[i]-target));
                    roulette[i] =  1.0/sum;
                }
                else roulette[i]=1;
                rouletteSum+=roulette[i];
            }
            for(int i=0;i<N/2;i++){
                Gene a=null,b=null;
                double temp=0;
                int tempIndex=-1;
                for(int j=0;j<2;j++){
                    double r = Math.random()*rouletteSum;
                    int index=0;
                    for(;;index++){
                        if((r-=roulette[index])<0) break;
                    }
                    if(tempIndex==-1) {
                        tempIndex=index;
                        temp =roulette[index];
                        roulette[index]=0;
                        rouletteSum-=temp;
                        a=genes.get(index);
                    }
                    else{
                        b=genes.get(index);
                        roulette[tempIndex]=temp;
                        rouletteSum+=temp;
                    }
                }
                ArrayList<Gene> news = crossover(a,b);
                for(int j=0;j<2;j++){
                    newGenes.add(news.remove(0));
                }
            }
            goodsum=0;
            for(int i=0;i<N;i++){
                goodness[i] = newGenes.get(i).getGoodness();
                goodsum+=goodness[i];
            }
            genes.clear();
            genes=newGenes;
            avg = (double) goodsum/N;
            if(age%10==0) System.out.print("|");
        }
        System.out.println();
        return genes.get(0).toString();
    }

    private static ArrayList<Gene> crossover(Gene g1,Gene g2) {
        ArrayList<Gene> geneAge = new ArrayList<Gene>();
        if(Math.random()<Pc) {
            StringBuilder newgene1 = new StringBuilder();
            StringBuilder newgene2 = new StringBuilder();
            StringBuilder gene1 = new StringBuilder(g1.toString());
            StringBuilder gene2 = new StringBuilder(g2.toString());
            geneAge.clear();
            for(int i=0;i<20;i++){
                if(Math.random()>0.5){
                    newgene1.append(gene1.toString().charAt(i));
                    newgene2.append(gene2.toString().charAt(i));
                }
                else{
                    newgene1.append(gene2.toString().charAt(i));
                    newgene2.append(gene1.toString().charAt(i));
                }
            }
            geneAge.add(new Gene(newgene1.toString()));
            geneAge.add(new Gene(newgene2.toString()));
        }
        else{
            geneAge.add(g1);
            geneAge.add(g2);
        }
        return geneAge;
    }

    public static void main(String[] args) {
        System.out.println(set(1));
    }

}
