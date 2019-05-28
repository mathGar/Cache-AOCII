package Cache;

import static java.lang.Math.log;

public class Cache {
    
    private int nSets;      //numero de conjuntos
    private int bSize;      //Tamanho do bloco
    private int assoc;      //Associatividade
    private int cSize;      //Tamanho da Cache
    private int cPlacement;   //Tipo de mapeamento
    private int nbTag;      //Numero de bits da tag
    private int nbIndex;    //Numero de bits do indice
    private int nbOffset;   //Numero de bits do offset
    private int hits, misses, compMiss, confMiss, capMiss;
    protected final Set[][] sets;   //Cache propriamente dita

    public Cache(int nSets, int bSize, int assoc, int placement) {
        this.nSets = nSets;
        this.bSize = bSize;
        this.assoc = assoc;
        this.cPlacement = placement;
        this.cSize = nSets * bSize * assoc;
        this.nbOffset = (int)(log(this.bSize)/log(2));
        this.nbIndex = (int)(log(this.nSets)/log(2));
        this.nbTag = (int) 32 - nbOffset - nbIndex;
        this.hits = 0;
        this.misses = 0;
        this.compMiss = 0;
        this.confMiss = 0;
        this.capMiss = 0;
        this.sets = new Set[nSets][assoc];
        
        int aux, aux2;
        
        for(aux = 0; aux < nSets; aux++)
        {
            for(aux2 = 0; aux2 < assoc; aux2++)
            {
                sets[aux][aux2] = new Set();
            }
        }
    }
    
    public int getnSets() {
        return nSets;
    }

    public void setnSets(int nSets) {
        this.nSets = nSets;
    }

    public int getbSize() {
        return bSize;
    }

    public void setbSize(int bSize) {
        this.bSize = bSize;
    }

    public int getAssoc() {
        return assoc;
    }

    public void setAssoc(int assoc) {
        this.assoc = assoc;
    }

    public int getcSize() {
        return cSize;
    }

    public void setcSize(int cSize) {
        this.cSize = cSize;
    }

    public int getcPlacement() {
        return cPlacement;
    }

    public void setcPlacement(int mapping) {
        this.cPlacement = mapping;
    }

    public int getNbTag() {
        return nbTag;
    }

    public void setNbTag(int nbTag) {
        this.nbTag = nbTag;
    }

    public int getNbIndex() {
        return nbIndex;
    }

    public void setNbIndex(int nbIndex) {
        this.nbIndex = nbIndex;
    }

    public int getNbOffset() {
        return nbOffset;
    }

    public void setNbOffset(int nbOffset) {
        this.nbOffset = nbOffset;
    }

}