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
        System.out.println("Want to choose cache's setting? (1-Y/0-N)"); //Modo defalt ou escolher caracteristicas
        aux = s.nextInt();

        if (aux == 1) {
            System.out.println("Choose cache's placement policy: \n0- Direct Mapping \n1- Associative Mapping \n2- Fully Associative Mapping/n");
            placement = s.nextInt();
            System.out.println("\n- The following data must be a multiple of two -\n");
            switch (placement) {
                case 0:
                    System.out.println("Enter the number of sets and block size(bytes)");
                    nSets = s.nextInt();
                    while (nSets % 2 != 0) {
                        System.out.println("Invalid data, enter a valid value");
                        nSets = s.nextInt();
                    }
                    bSize = s.nextInt();
                    while (bSize % 2 != 0) {
                        System.out.println("Invalid data, enter a valid value");
                        bSize = s.nextInt();
                    }
                    assoc = 1;
                    aux2 = "Direct Mapping";
                    break;
                case 1:
                    System.out.println("Enter the number of sets, block size(bytes) and associativity");
                    nSets = s.nextInt();
                    while (nSets % 2 != 0) {
                        System.out.println("Invalid data, enter a valid value");
                        nSets = s.nextInt();
                    }
                    bSize = s.nextInt();
                    while (bSize % 2 != 0) {
                        System.out.println("Invalid data, enter a valid value");
                        bSize = s.nextInt();
                    }
                    assoc = s.nextInt();
                    while (assoc % 2 != 0) {
                        System.out.println("Invalid data, enter a valid value");
                        assoc = s.nextInt();
                    }
                    aux2 = "Associative Mapping";
                    break;
                case 2:
                    System.out.println("Enter the block size(bytes) and associativity");
                    nSets = 1;
                    bSize = s.nextInt();
                    while (bSize % 2 != 0) {
                        System.out.println("Invalid data, enter a valid value");
                        bSize = s.nextInt();
                    }
                    assoc = s.nextInt();
                    while (assoc % 2 != 0) {
                        System.out.println("Invalid data, enter a valid value");
                        assoc = s.nextInt();
                    }
                    aux2 = "Fully Associative Mapping";
                    break;
            }
        } else {
            System.out.println("Started with the default configuration");
            nSets = 256;
            bSize = 4;
            assoc = 1;
            placement = 0;
        }

        cache = new Cache(nSets, bSize, assoc, placement, aux2);
        reader = new ReadFile(); //Le arquivo de dados e gera enderecos binarios
        for (int i = 0; i < reader.address.size(); i++) {
            cache.requisition(reader.address.get(i));
        }
        System.out.println("\n");
        System.out.println("Cache setting:");
        System.out.println(" - Number of sets: " + cache.getnSets());
        System.out.println(" - Block size: " + cache.getbSize() + " Bytes");
        System.out.println(" - Associativity: " + cache.getAssoc());
        System.out.println(" - Cache size: " + cache.getcSize() + " Bytes");
        System.out.println(" - Cache placement: " + cache.getsPlacement());
        System.out.println(" - Number of bits - Tag: " + cache.getNbTag());
        System.out.println(" - Number of bits - Index: " + cache.getNbIndex());
        System.out.println(" - Number of bits - Offset: " + cache.getNbOffset());
        System.out.println("");
        System.out.println("Statistcs:");
        System.out.println(" - Requisitions: " + (cache.getMisses() + cache.getHits()));
        System.out.println(" - Hits: " + cache.getHits());
        System.out.println(" - Misses: " + cache.getMisses());
        System.out.println("    - Capacity misses: " + cache.getCapMiss());
        System.out.println("    - Compulsory misses: " + cache.getCompMiss());
        System.out.println("    - Conflict misses: " + cache.getConfMiss());
        System.out.println(" - Miss Rate: " + (cache.getMisses() * 100) / (cache.getMisses() + cache.getHits()) + "%");
        System.out.println(" - Hit Rate: " + (cache.getHits() * 100) / (cache.getMisses() + cache.getHits()) + "%");
    }

}
