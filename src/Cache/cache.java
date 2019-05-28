package Cache;

import static java.lang.Math.log;

public class cache {
    
    private int nSets;      //numero de conjuntos
    private int bSize;      //Tamanho do conjunto
    private int assoc;      //Associatividade
    private int cSize;      //Tamanho da cache
    private int cMapping;    //Tipo de cache
    private int nbTag;      //Numero de bits da tag
    private int nbIndex;    //Numero de bits do indice
    private int nbOffset;   //Numero de bits do offset
    protected final set[][] sets;   //Cache propriamente dita
    private int aux, aux2;
    

    public cache(int nSets, int bSize, int assoc) {
        this.nSets = nSets;
        this.bSize = bSize;
        this.assoc = assoc;
        nbOffset = (int)(log(this.bSize)/log(2));
        nbIndex = (int)(log(this.nSets)/log(2));
        nbTag = (int) 32 - nbOffset - nbIndex;
        sets = new set[nSets][assoc];
        for(aux = 0; aux < nSets; aux++)
        {
            for(aux2 = 0; aux2 < assoc; aux2++)
            {
                sets[aux][aux2] = new set();
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

    public int getcMapping() {
        return cMapping;
    }

    public void setcMaping(int mapping) {
        this.cMapping = mapping;
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