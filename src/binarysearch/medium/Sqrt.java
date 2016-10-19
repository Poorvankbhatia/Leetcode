package binarysearch.medium;

/**
 * Created by poorvank.b on 12/10/16.
 */
public class Sqrt {

    public int mySqrt(int num) {

        if(num==1 || num==0) {
            return num;
        }

        int end,start = 1;
        int startSquare = 1;
        int prevStart = 1;

        while (startSquare<num && startSquare>0) {
            prevStart = start;

            if(startSquare<num) {
                start = start*2;
            }
            startSquare = start*start;
            if(startSquare==num) {
                return start;
            }
        }

        end = start;
        start = prevStart;

        int sqrt=1;
        while (end-1>start) {

            int mid = start + (end-start)/2;
            int midSquare = (mid*mid);
            if(midSquare==num) {
                sqrt = mid;
                break;
                // In case the number square causes stackoverflow
            } else if(midSquare>num || midSquare<0) {
                //Just to handle conditin in case of non perfect squares
                sqrt = mid-1;
                end = mid;
            } else {
                start = mid;
                sqrt = mid;
            }

        }


        return sqrt;

    }

    public static void main(String[] args) {
        System.out.println(new Sqrt().mySqrt(169));
    }

}
