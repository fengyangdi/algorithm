package containerwithwater;

/**
 * 给定一个非负数的数组，代表一个容器。例如数组[0,1,0,2,1,0,1,3,2,1,2,1]，
 * 就是 以下图形中黑色的部分。如果用这个容器接水的话，请问可以接多少水？还以这个数组为例，可以接6 格水，就是以下图形中蓝色的部分。
 * ![题目图片](http://7xvq0h.com1.z0.glb.clouddn.com/%E6%B1%82%E6%95%B0%E7%BB%84%E7%BB%99%E5%AE%9A%E7%9A%84%E5%AE%B9%E5%99%A8%E7%9A%84%E8%93%84%E6%B0%B4%E9%87%8F.jpg)
 * Created by GGM on 2016/7/26.
 */
public class ContainerWithWater {
    public static int maxWater(int[] arr){
        if (arr == null || arr.length <= 2) return 0;
        int maxL = arr[0];
        int[] maxR = new int[arr.length];
        int max = 0;
        for (int i = arr.length - 1; i >=0; i--){
            if (arr[i] > max) maxR[i] = max = arr[i];
            else maxR[i] = max;
        }

        max = 0;  //用来计算总的水量

        for (int i = 1; i < arr.length - 1; i++){
            if (arr[i] > maxL) maxL = arr[i];
            max += Math.max(Math.min(maxL, maxR[i]) - arr[i], 0);
        }

        return max;
    }

    public static int maxWaterWithConstSpace(int[] arr){
        if (arr == null || arr.length <= 2) return 0;
        int maxL = arr[0];
        int maxR = arr[arr.length -1];
        int max = 0; //用来计算总的水量

        max = 0;

        int l = 1;
        int r = arr.length - 2;

        while (l <= r){
            if (maxL <= maxR){
                max += Math.max(0, maxL - arr[l]);
                if (arr[l++] > maxL) maxL = arr[l-1];
            }else {
                max += Math.max(0, maxR - arr[r]);
                if (arr[r--] > maxR) maxR = arr[r + 1];
            }
        }

        return max;
    }
}
