package binarysearch.medium;

/**
 * Created by poorvank.b on 12/10/16.
 */
public class Sqrt {

    public int mySqrt(int x) {

        if (0 == x) return 0;
        int left = 1, right = x, ans=0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid) { // instead of (mid*mid <= x)  use (mid <= x / mid)
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Sqrt().mySqrt(16765439));
    }

}
