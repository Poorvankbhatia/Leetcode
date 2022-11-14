package binarysearch.hard;

public class GlobalMaximum {

    public int findMaximum(int[] arr, int n) {
        int length = arr.length;
        int lo = 1;
        int hi = 5;
        int ans = 0;
        while (lo<hi)  {
            int mid = lo + (hi - lo) / 2;
            if(!globalMaximaPossible(mid, arr,n)) {
                hi = mid-1;
            } else {
                ans = mid;
                lo = mid + 1;
            }
        }
        return ans;
    }

    private boolean globalMaximaPossible(int mid, int[] arr,int n) {
        int prev = arr[0];
        mid--;
        for (int i = 0; i < arr.length && mid>0; i++) {
            if(arr[i] -prev>=mid) {
                prev = arr[i];
                mid--;
            }
        }
        return mid==0;
    }

    public static void main(String[] args) {
        System.out.println(new GlobalMaximum().findMaximum(new int[]{1,2,3,4},3));
    }

}
