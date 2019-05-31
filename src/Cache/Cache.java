package Cache;

import static java.lang.Math.log;
import java.util.Random;

public class Cache {

    private int nSets;      //Numero de conjuntos
    private int bSize;      //Tamanho do bloco
    private int assoc;      //Associatividade
    private int cSize;      //Tamanho da Cache
    private int cPlacement; //Tipo de mapeamento
    private int nbTag;      //Numero de bits da tag
    private int nbIndex;    //Numero de bits do indice
    private int nbOffset;   //Numero de bits do offset
    private int hits, misses, compMiss, confMiss, capMiss;
    private String auxTag, sPlacement;
    private int auxIndex;
    protected final Set[][] sets;   //Cache propriamente dita

    public Cache(int nSets, int bSize, int assoc, int placement, String sPlacement) {
        this.nSets = nSets;
        this.bSize = bSize;
        this.assoc = assoc;
        this.cPlacement = placement;
        this.sPlacement = sPlacement;
        this.nbOffset = (int) (log(this.bSize) / log(2));
        this.nbIndex = (int) (log(this.nSets) / log(2));
        this.nbTag = (int) 32 - nbOffset - nbIndex;
        this.cSize = nSets * (bSize * 8 + nbTag + 1) * assoc;
        this.hits = 0;
        this.misses = 0;
        this.compMiss = 0;
        this.confMiss = 0;
        this.capMiss = 0;
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
        boolean full = false;
        this.splittedInfo(address);

        for (aux = 0; aux < this.assoc; aux++) {
            if (this.sets[this.auxIndex][aux].getBitVal() == 0) {
                this.compMiss++; //Caso bit de validade seja 0 ocorre miss compulsorio 
                this.misses++;   //
                f = true;        //Flag de controle setada pra true pois a falta foi corrigida
                this.sets[this.auxIndex][aux].setBitVal(1);  //Escreve o dado na cache
                this.sets[this.auxIndex][aux].setTag(auxTag);
                return;
            } else if (this.sets[this.auxIndex][aux].getBitVal() == 1) {           //Caso o bit de validade seja 1 ocorrera o teste para
                if (this.auxTag.equals(this.sets[this.auxIndex][aux].getTag())) {  //ver se a tag se encontra na cache
                    this.hits++; //Caso a tag esteja na cache ocorre um hit
                    f = true;    //Flag de controle setada para true pois nao houve falta
                    return;
                } else {
                    f = false; //Flag de controle setada para false pois houve uma falta nao corrigida
                }
            }
        }

        if (!f) {
            Random r = new Random();

            if (this.cPlacement == 2) { //Caso o mapeamento seja totalmente associativo apenas ocorrera
                this.capMiss++;         //misses de capacidade e nao de conflito
                this.misses++;
            } else if (this.cPlacement == 0) {  //Caso mapeamento direto percorre a cache para ver se sera
                full = true;                    //miss por capacidade ou conflito
                for (int i = 0; i < this.nSets; i++) {
                    if (this.sets[i][0].getBitVal() == 0) {
                        full = false;
                    }
                }
                if (full) {
                    this.capMiss++;
                    this.misses++;
                } else {
                    this.confMiss++;
                    this.misses++;
                }
            } else {                                    //Caso mapeamento por conjunto associativo
                full = true;                            //percorre a cache para ver se sera miss por capacidade
                for (int i = 0; i < this.nSets; i++) {  //ou conflito
                    for (int j = 0; j < this.assoc; j++) {
                        if (this.sets[i][j].getBitVal() == 0) {
                            full = false;
                        }
                    }
                }
                if (full) {          //Testa se a memoria esta cheia para informar
                    this.capMiss++;  //misses de capacidade
                    this.misses++;
                } else {
                    this.confMiss++;  //Caso contrario, ocorre miss de conflito
                    this.misses++;
                }
                aux = r.nextInt(assoc);                      //Valor aleatorio para ser feita a substituicao
                this.sets[this.auxIndex][aux].setBitVal(1);  //Escreve o dado na posicao aleatoria dentro da via
                this.sets[this.auxIndex][aux].setTag(auxTag);
            }
        }
    }

    private void splittedInfo(String address) { //Separa bits de tag e indice

        if (this.cPlacement != 2) {
            this.auxTag = address.substring(0, nbTag);
            this.auxIndex = Integer.parseInt(address.substring(this.nbTag, this.nbTag + this.nbIndex), 2);
        } else if (this.cPlacement == 2) {
            this.auxTag = address.substring(0, nbTag);
            this.auxIndex = 0;
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

    public String getsPlacement() {
        return sPlacement;
    }

    public void setsPlacement(String sPlacement) {
        this.sPlacement = sPlacement;
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

    public String getAuxTag() {
        return auxTag;
    }

    public int getAuxIndex() {
        return auxIndex;
    }

}
