package Cache;

import java.util.Scanner;

public class Simulator {

    public static void main(String[] args) {
        
        int nSets, bSize, assoc, placement, aux;
        Scanner s = new Scanner(System.in);
        Cache cache;
        
        System.out.println("Welcome to Cache simulator");
        System.out.println("Want to choose cache's mapping? (1-Y/0-N)");
        aux = s.nextInt();
        
        if(aux == 1){
            System.out.println("Choose cache's placement police: \n1- Direct Mapping \n2- Associative Mapping \n3- Fully Associative Mapping/n");
            placement = s.nextInt();
        }
        else{
            placement = 0;
        }
        
        System.out.println("Enter with the number of sets, block size(bytes) and associativity");
        nSets = s.nextInt();
        bSize = s.nextInt();
        assoc = s.nextInt();
        
        cache = new Cache(nSets, bSize, assoc, placement);
        
        /*System.out.println(cache.getnSets());
        System.out.println(cache.getbSize());
        System.out.println(cache.getAssoc());
        System.out.println(cache.getcSize());
        System.out.println(cache.getcPlacement());
        System.out.println(cache.getNbTag());
        System.out.println(cache.getNbIndex());
        System.out.println(cache.getNbOffset());*/
        
        /*Achar tag e indice para comparacao na Cache
        this.index = address%this.nSets;
        this.tag = (address >> (byte)nbOffset + nbIndex);  
        */
        
        
    }
    
}
