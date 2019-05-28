package Cache;

public class conjunto {
    
    private int bitVal, tag, dados;

    public conjunto(int bitVal, int tag, int dados) {
        this.bitVal = bitVal;
        this.tag = tag;
        this.dados = dados;
    }
    
    public int getBitVal() {
        return bitVal;
    }

    public void setBitVal(int bitVal) {
        this.bitVal = bitVal;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getDados() {
        return dados;
    }

    public void setDados(int dados) {
        this.dados = dados;
    }
    
}