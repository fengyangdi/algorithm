package longestsubarr;

import java.util.Arrays;

/**
 * 求最长子序列
 * Created by GGM on 2016/7/24.
 */
public class LongestSubString {

    public static int longestSubstring(int[] arr){
        if (arr == null || arr.length <= 0) return 0;
        int[] dp = new int[arr.length];
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < arr.length; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (arr[j] < arr[i]) {
                    dp[i] = dp[i] >= dp[j] + 1 ? dp[i] : dp[j] + 1;
                }
            }
            max = max >= dp[i] ? max : dp[i];
        }
        return max;
    }

    public static int longestSubstringFast(int[] arr){
        if (arr == null || arr.length <= 0) return 0;
        int[] h = new int[arr.length];
        h[0] = arr[0];
        int low = 0;
        int high = 0;
        int curr = 0;  //记录h的最后一个位置

        for (int i = 1; i < arr.length; i++){
            if (arr[i] > h[curr]) {
                h[++curr] = arr[i];  //比当前最后一个大，则直接插入在末尾
            }
            else{
                low = 0;
                high = curr;
                while (low <= high){
                    if (h[high] < arr[i]) {
                        h[high+1] = arr[i];
                        break;
                    }
                    if (h[low] > arr[i]){
                        h[low] = arr[i];
                        break;
                    }
                    int mid = (low + high) / 2;
                    if (arr[i] == h[mid]) break;
                    else if (arr[i] > h[mid]) low = mid + 1;
                    else high = mid;
                }
            }
        }

        return curr+1;
    }

    public static int longestOfTwoTuples(TwoTuples[] arr){
        if (arr == null || arr.length <= 0) return 0;
        int[] dp = new int[arr.length];
        Arrays.sort(arr,ComparatorGenerator.orderComparator());
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < arr.length; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (arr[i].canBeUpper(arr[j])) {  //如果当前i可以放在j上面
                    dp[i] = dp[i] >= dp[j] + 1 ? dp[i] : dp[j] + 1;
                }
            }
            max = max >= dp[i] ? max : dp[i];
        }
        return max;
    }

    public static int longestOfTwoTuplesFast(TwoTuples[] arr){
        if (arr == null || arr.length <= 0) return 0;
        int[] h = new int[arr.length];
        Arrays.sort(arr,ComparatorGenerator.reverseComparator());
        h[0] = arr[0].getB();
        int low = 0;
        int high = 0;
        int curr = 0;  //记录h的最后一个位置

        for (int i = 1; i < arr.length; i++){
            if (arr[i].getB() > h[curr]) {
                h[++curr] = arr[i].getB();  //比当前最后一个大，则直接插入在末尾
            }
            else{
                low = 0;
                high = curr;
                while (low <= high){
                    if (h[high] < arr[i].getB()) {
                        h[high+1] = arr[i].getB();
                        break;
                    }
                    if (h[low] > arr[i].getB()){
                        h[low] = arr[i].getB();
                        break;
                    }
                    int mid = (low + high) / 2;
                    if (arr[i].getB() == h[mid]) break;
                    else if (arr[i].getB() > h[mid]) low = mid + 1;
                    else high = mid;
                }
            }
        }

        return curr+1;
    }
}
