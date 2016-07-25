package longestsubarr;

/**
 * 二元组类，保存两个数
 * Created by GGM on 2016/7/25.
 */
public class TwoTuples {
    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public TwoTuples(int a, int b){
        this.a = a;
        this.b = b;
    }

    public boolean canBeUpper(TwoTuples twoTuples) {
        if (this.a > twoTuples.a && this.b > twoTuples.b) return true;
        return false;
    }
}
