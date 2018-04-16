/*

Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

 */

package binarysearch.medium;

/**
 * Created by poorvank.b on 12/01/18.
 */
public class HIndex2 {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        int l = 0, r = citations.length;
        int n = citations.length;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(citations[mid] == n - mid) return n - mid;
            if(citations[mid] < n - mid) l = mid + 1;
            else r = mid;
        }
        return n - l;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,1,3,4,6,9};
        System.out.println(new HIndex2().hIndex(arr));
    }

}

/*

Just binary search, each time check citations[mid]
case 1: citations[mid] == len-mid, then it means there are citations[mid] papers that have at least citations[mid] citations.
case 2: citations[mid] > len-mid, then it means there are citations[mid] papers that have moret than citations[mid] citations,
 so we should continue searching in the left half
case 3: citations[mid] < len-mid, we should continue searching in the right side
After iteration, it is guaranteed that right+1 is the one we need to find (i.e. len-(right+1) papars have at least len-(righ+1) citations)

 */
