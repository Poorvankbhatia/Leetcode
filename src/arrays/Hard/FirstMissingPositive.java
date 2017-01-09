package arrays.Hard;
/**
 * Created by poorvank on 09/01/17.
 */
public class FirstMissingPositive {


    public int firstMissingPositive(int[] arr) {
        int n = arr.length;
        for (int k=0; k<n; k++) {
            while (arr[k] != k+1 && arr[k]-1 >= 0 && arr[k]-1 < n && arr[k] != arr[arr[k]-1]) {
                swap(arr, k, arr[k]-1);
            }
        }


        for (int i=0; i<n; i++) {
            if (arr[i] != i+1)    return i+1;
        }
        return n + 1;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

/*

This problem can solve by using a bucket-sort like algorithm.
Let's consider finding first missing positive and 0 first. The key fact is that the ith element should be i, so we have:
i==A[i]
A[i]==A[A[i]]

 */