package bits;

/**
 * Created by poorvank on 18/12/16.
 */
public class HammingDistance {

    public int hammingDistance(int x, int y) {

        int xor = x^y;
        return countSetBits(xor);

    }

    private int countSetBits(int n) {
        int count = 0;
        while (n!=0) {
            count += n&1;
            n >>=1 ;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new HammingDistance().hammingDistance(5,3));
    }

}
