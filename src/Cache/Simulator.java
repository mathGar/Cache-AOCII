package Cache;

import java.io.IOException;
import java.util.Scanner;

public class Simulator {

    public static void main(String[] args) throws IOException {
        
        int nSets, bSize, assoc, placement, aux;
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
                    bSize = s.nextInt();
                    assoc = s.nextInt();
                    aux2 = "Fully Associative Mapping";
                break;
            }
            System.out.println("Cache configuration:");
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
            System.out.println(" - Number of sets: " + nSets);
            System.out.println(" - Block size: " + bSize);
            System.out.println(" - Assosiativity: " + assoc);
            System.out.println(" - Placement policy: " + aux2);
        }
        
        cache = new Cache(nSets, bSize, assoc, placement);
        reader = new ReadFile();
        for(int i = 0; i < reader.address.size(); i++)
            cache.requisition(reader.address.get(i));
        System.out.println("\n\n");
        System.out.println("Number of sets: " + cache.getnSets());
        System.out.println("Block size: " + cache.getbSize());
        System.out.println("Associativity: " + cache.getAssoc());
        System.out.println("Cache size: " + cache.getcSize());
        System.out.println("Cache placement: " + cache.getcPlacement());
        System.out.println("Number of bits - Tag: " + cache.getNbTag());
        System.out.println("Number of bits - Index: " + cache.getNbIndex());
        System.out.println("Number of bits - Offset: " + cache.getNbOffset());
        System.out.println("Capacity misses: " + cache.getCapMiss());
        System.out.println("Conflict misses: " + cache.getConfMiss());
        System.out.println("Compulsory misses: " + cache.getCompMiss());
        System.out.println("Total misses: " + cache.getMisses());
        System.out.println("Hits: " + cache.getHits());
        
    }
    
}
