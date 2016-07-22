package palindrome;

/**
 * 最长回文子串
 * Created by GGM on 2016/7/22.
 */
public class LongestPalindrome {

    /**
     * 最长回文字符串的动态规划实现
     * 定义boolean型的 p[i][j]，为 Si 到 Sj 是否为回文，true 说明 Si 到 Sj 是回文字符串
     * 则有，P[i,j] = (P[i + 1, j - 1] && Si ==Sj)
     * 初始条件p[i, i] = true, p[i,i+1] = S<sub>i</sub>==S<sub>i+1</sub>
     * 动态规划的思想是首先判断相邻的字符串是否是回文，然后继续判断连续的三个字符是否是回文，然后是四个，...，直到判断完整个字符串
     * 时间复杂度O(n<sup>2</sup>)，空间复杂度O(n<sup>2</sup>)
     * @param str 传入的字符串
     * @return 最长回文串，如果传入的字符串为null或者长度为0，则返回null
     */
    public static String longestPalindromeDP(String str){
        if (str == null || str.length() <= 0) return null;

        int len = str.length();
        int startIndex = 0;
        int maxLen = 1;

        boolean[][] p = new boolean[len][len];
        for (int i = 0; i < len; i++){
            for (int j = 0; j < len; j++){
                if (i == j) {
                    p[i][j] = true;
                    continue;
                }
                p[i][j] = false;
            }
        }
        for (int i = 0; i < len - 1; i++){
            //相邻的相同
            if (str.charAt(i) == str.charAt(i+1)){
                p[i][i+1] = true;
                startIndex = i;
                maxLen = 2;
            }
        }

        for (int i = 3; i <= len; i++){
            for (int j = 0; j < len - i + 1; j++){
                //当前判断回文长度为i，起始位置为j
                int currLast = j + i - 1;
                if (str.charAt(j) == str.charAt(currLast) && p[j+1][currLast-1]){
                    p[j][currLast] = true;
                    startIndex = j;
                    maxLen = i;
                }
            }
        }

        return str.substring(startIndex, startIndex+maxLen);
    }


    /**
     * 回文字符串的特点是以中心对称，从0开始依次遍历字符串，每次以选取的点为中心，向两边检测，判断是否符合回文字符串。
     * @param str 输入字符串
     * @return 最长回文串，如果传入的字符串为null或者长度为0，则返回null
     */
    public static String longestPalindromeCerter(String str){
        if (str == null || str.length() <= 0) return null;
        String longest = str.substring(0,1);
        for (int i = 0; i < str.length() - 1; i++){
            //获得以i为中心的回文字符串
            String s = getPalindromeCerter(str, i, i);

            if (s.length() > longest.length()){
                longest = s;
            }

            //获得以i和i+1为中心的回文字符串
            s = getPalindromeCerter(str, i, i + 1);

            if (s.length() > longest.length()){
                longest = s;
            }
        }
        return longest;
    }

    private static String getPalindromeCerter(String str, int i, int j) {
        while (i >= 0 && j < str.length() && str.charAt(i) == str.charAt(j)){
            i --;
            j ++;

        }
        return str.substring(i + 1, j);
    }

    /**
     * 通过添加辅助标识，来获得最长回文子串
     * @param str
     * @return
     */
    public static String longestPalindromeAddTag(String str){
        if (str == null || str.length() <= 0) return null;
        StringBuilder sb = addTag(str);
        int[] p = new int[sb.length()]; //以i为中心的，左右半边的回文子串长度（包括#）
        p[0] = p[sb.length() - 1] = 0;
        int center = 0; int r = 0;
        for (int i = 1; i < sb.length() - 1; i++){
            int i_mirror = center - ( i - center);
            int diff = r - i;
            if (i_mirror>= 0){
                if (p[i_mirror] < diff) p[i] = p[i_mirror];
                else {
                    center = i;
                    p[i] = diff;
                    int pre = i - p[i] - 1;  //往前
                    int after = i + p[i] + 1; //往后
                    while (pre >= 0 && after < sb.length() &&
                            sb.charAt(pre) == sb.charAt(after)){
                        p[i] ++;
                        pre --;  //往前
                        after ++; //往后
                    }
                    r = i + p[i];  //当前中心的右边缘
                }
            }else {
                center = i;
                p[i] = 0;
                int pre = i - p[i] - 1;  //往前
                int after = i + p[i] + 1; //往后
                while (pre >= 0 && after < sb.length() &&
                        sb.charAt(pre) == sb.charAt(after)){
                    p[i] ++;
                    pre --;  //往前
                    after ++; //往后
                }
                r = i + p[i];  //当前中心的右边缘
            }
        }

        int maxLen = 0;
        int index = 0;
        for (int i = 0; i < sb.length(); i++){
            if (p[i] > maxLen){
                maxLen = p[i];
                index = i;
            }
        }
        int start = (index >> 1) - (maxLen >> 1) ;
        int last = (index >> 1) + (maxLen >> 1);
        if ((index & 0x01) == 1) last++;
        return str.substring(start,last);
    }

    /**
     * 向字符串中插入#，如 ab，则返回 #a#b#
     * @param str
     * @return
     */
    private static StringBuilder addTag(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (int i = 0; i < str.length(); i++){
            sb.append(str.charAt(i));
            sb.append('#');
        }
        return sb;
    }

    /**
     * 添加多少字符串使字符串变为回文串
     * @param str
     * @return
     */
    public static int addTobePalindrome(String str){
        if (str == null || str.length() <= 0) return 0;

        int len = str.length();

        int[][] p = new int[len][len];
        for (int i = 0; i < len; i++){
            for (int j = 0; j < len; j++){
                p[i][j] = 0;
            }
        }

        for (int i = 2; i <= len; i++){
            for (int j = 0; j < len - i + 1; j++){
                int currLast = j + i - 1;
                if (str.charAt(j) == str.charAt(currLast)){
                    p[j][currLast] = p[j+1][currLast-1];
                }else {
                    p[j][currLast] = 1 + (p[j][currLast - 1] < p[j+1][currLast] ? p[j][currLast - 1] : p[j+1][currLast] );
                }
            }
        }

        return p[0][len-1];
    }

}
