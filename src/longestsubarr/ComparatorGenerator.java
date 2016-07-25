package longestsubarr;

import java.util.Comparator;

/**
 * Created by GGM on 2016/7/25.
 */
public class ComparatorGenerator {
    /**
     * 第一个数递增排序，第二个数递增排序
     * @return
     */
    public static Comparator<TwoTuples> orderComparator(){
        return new Comparator<TwoTuples>() {
            @Override
            public int compare(TwoTuples o1, TwoTuples o2) {
                if (o1.getA() == o2.getA()){
                    return o1.getB() > o2.getB() ? 1 : (o1.getB() == o2.getB() ? 0 : -1);
                } else {
                    return o1.getA() > o2.getA() ? 1 : -1;
                }
            }
        };
    }

    /**
     * 第一个数大递增排序，第二数递减排序
     * @return
     */
    public static Comparator<TwoTuples> reverseComparator(){
        return new Comparator<TwoTuples>() {
            @Override
            public int compare(TwoTuples o1, TwoTuples o2) {
                if (o1.getA() == o2.getA()){
                    return o1.getB() > o2.getB() ? -1 : (o1.getB() == o2.getB() ? 0 : 1);
                } else {
                    return o1.getA() > o2.getA() ? 1 : -1;
                }
            }
        };
    }
}
