package Cache;

public class Set {
    
    private int bitVal;
    private String tag;

    public Set(){
        this.bitVal = 0;
        this.tag = null;
    }
    
   /* public Set(int bitVal, int tag, int dados) {
        this.bitVal = bitVal;
        this.tag = tag;
        this.dados = dados;
    }*/
    
    public int getBitVal() {
        return bitVal;
    }

    public void setBitVal(int bitVal) {
        this.bitVal = bitVal;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    
}