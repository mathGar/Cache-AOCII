package Cache;

public class Set {

    private int bitVal;
    private String tag;

    public Set() {
        this.bitVal = 0;
        this.tag = null;
    }

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