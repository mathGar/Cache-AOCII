package Cache;

import java.util.Scanner;

public class Simulator {

    public static void main(String[] args) {
        
        int nSets, bSize, assoc, cSize, placement, aux;
        String aux2 = "Direct Mapping";
        Scanner s = new Scanner(System.in);
        Cache cache;
        ReadFile reader;
        
        nSets = 0;
        bSize = 0;
        assoc = 0;
        
        System.out.println("Welcome to Cache simulator");
        System.out.println("Want to choose cache's configuration? (1-Y/0-N)");
        aux = s.nextInt();
        
        if(aux == 1){
            System.out.println("Choose cache's placement policy: \n0- Direct Mapping \n1- Associative Mapping \n2- Fully Associative Mapping/n");
            placement = s.nextInt();
            switch (placement){
                case 0:
                    System.out.println("Enter with the number of sets and block size(bytes)");
                    nSets = s.nextInt();
                    bSize = s.nextInt();
                    assoc = 1;
                    aux2 = "Direct Mapping";
                break;
                case 1:
                    System.out.println("Enter with the number of sets, block size(bytes) and associativity");
                    nSets = s.nextInt();
                    bSize = s.nextInt();
                    assoc = s.nextInt();
                    aux2 = "Associative Mapping";
                break;
                case 2:
                    System.out.println("Enter with the block size(bytes) and associativity");
                    nSets = 1;
                    assoc = s.nextInt();
                    bSize = s.nextInt();
                    aux2 = "Fully Associative Mapping";
                break;
            }
            cSize = nSets * bSize * assoc;
            System.out.println("Cache configuration:");
            System.out.println(" - Size: " + cSize + " Bytes");
            System.out.println(" - Number of sets: " + nSets);
            System.out.println(" - Block size: " + bSize);
            System.out.println(" - Assosiativity: " + assoc);
            System.out.println(" - Placement policy: " + aux2);
        }
        else{
            System.out.println("Started with the default configuration:");
            nSets = 256;
            bSize = 4;
            assoc = 1;
            placement = 0;
            cSize = nSets * bSize * assoc;
            System.out.println(" - Size: " + cSize + " Bytes");
            System.out.println(" - Number of sets: " + nSets);
            System.out.println(" - Block size: " + bSize);
            System.out.println(" - Assosiativity: " + assoc);
            System.out.println(" - Placement policy: " + aux2);
        }
        
        cache = new Cache(nSets, bSize, assoc, placement, cSize);
        
        
        
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
