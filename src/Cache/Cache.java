package Cache;

import static java.lang.Math.log;
import java.util.Random;

public class Cache {

    private int nSets;      //numero de conjuntos
    private int bSize;      //Tamanho do bloco
    private int assoc;      //Associatividade
    private int cSize;      //Tamanho da Cache
    private int cPlacement; //Tipo de mapeamento
    private int nbTag;      //Numero de bits da tag
    private int nbIndex;    //Numero de bits do indice
    private int nbOffset;   //Numero de bits do offset
    private int hits, misses, compMiss, confMiss, capMiss, controlSize;
    private String auxTag;
    private int auxIndex;
    protected final Set[][] sets;   //Cache propriamente dita

    public Cache(int nSets, int bSize, int assoc, int placement) {
        this.nSets = nSets;
        this.bSize = bSize;
        this.assoc = assoc;
        this.cPlacement = placement;
        this.nbOffset = (int) (log(this.bSize) / log(2));
        this.nbIndex = (int) (log(this.nSets) / log(2));
        this.nbTag = (int) 32 - nbOffset - nbIndex;
        this.cSize = nSets * (bSize*8 + nbTag + 1) * assoc;
        this.hits = 0;
        this.misses = 0;
        this.compMiss = 0;
        this.confMiss = 0;
        this.capMiss = 0;
        this.controlSize = 0;
        this.sets = new Set[nSets][assoc];

        int aux, aux2;

        for (aux = 0; aux < nSets; aux++) {
            for (aux2 = 0; aux2 < assoc; aux2++) {
                sets[aux][aux2] = new Set();
            }
        }
    }

    public void requisition(String address) {
        int aux;
        boolean f = false;
        this.splittedInfo(address);

        for (aux = 0; aux < this.assoc; aux++) {
            if (this.sets[this.auxIndex][aux].getBitVal() == 0) {
                this.compMiss++;//miss compulsorio
                this.misses++;
                f = true;
                this.sets[this.auxIndex][aux].setBitVal(1);//escreve na cache
                this.sets[this.auxIndex][aux].setTag(auxTag);
                this.controlSize++;
                return;
            } else if (this.sets[this.auxIndex][aux].getBitVal() == 1) {
                if (this.auxTag.equals(this.sets[this.auxIndex][aux].getTag())) {
                    this.hits++;//hit
                    f = true;
                    return;
                } else {
                    f = false;
                }
            }
        }

        if (!f) {
            Random r = new Random();
            aux = r.nextInt(assoc);
            this.sets[this.auxIndex][aux].setBitVal(1);//escreve na cache
            this.sets[this.auxIndex][aux].setTag(auxTag);
            this.controlSize++;

            if (this.cPlacement == 2) {
                this.capMiss++;//miss capacidade
                this.misses++;
            } else {
                if (this.controlSize >= (this.assoc * this.nSets)) {
                    this.capMiss++;//miss capacidade
                    this.misses++;
                } else {
                    this.confMiss++;//miss conflito
                    this.misses++;
                }
            }
        }
    }

    private void splittedInfo(String address) { //Separa bits de tag e indice
        System.out.println("\n\n"+ this.nbTag + "\n" + this.nbIndex + "\n" + this.nbOffset +"\n\n");
        this.auxTag = address.substring(this.nbTag);
        if(this.cPlacement!=2)
            this.auxIndex = Integer.parseInt(address.substring(this.nbTag, this.nbTag + this.nbIndex), 2);
        else
            this.auxIndex = 0;
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

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getMisses() {
        return misses;
    }

    public void setMisses(int misses) {
        this.misses = misses;
    }

    public int getCompMiss() {
        return compMiss;
    }

    public void setCompMiss(int compMiss) {
        this.compMiss = compMiss;
    }

    public int getConfMiss() {
        return confMiss;
    }

    public void setConfMiss(int confMiss) {
        this.confMiss = confMiss;
    }

    public int getCapMiss() {
        return capMiss;
    }

    public void setCapMiss(int capMiss) {
        this.capMiss = capMiss;
    }

    
}
