/*

Given a list of sorted characters letters containing only lowercase letters, and given a target letter target,
find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

Examples:
Input:
letters = ["c", "f", "j"]
target = "a"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "c"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "d"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "g"
Output: "j"

Input:
letters = ["c", "f", "j"]
target = "j"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "k"
Output: "c"
Note:
letters has a length in range [2, 10000].
letters consists of lowercase letters, and contains at least 2 unique letters.
target is a lowercase letter.


 */
package binarysearch.easy;

/**
 * Created by poorvank.b on 16/12/17.
 */
public class SmallestCharacter {

    public char nextGreatestLetter(char[] letters, char target) {

        int n = letters.length;
        return letters[find(letters,target,0,n-1)];

    }

    private int find(char[] letters,char target,int start,int end) {

        if(letters[start]>target || letters[end]<=target) {
            return start;
        }

        int mid = start+(end-start)/2;

        if(mid>0 && letters[mid-1]<=target && letters[mid]>target) {
            return mid;
        } else if(letters[mid]<=target) {
            return find(letters,target,mid+1,end);
        } else {
            return find(letters,target,0,mid-1);
        }

    }

    public static void main(String[] args) {
        char[] arr = new char[]{'a','b','c','d','e'};
        System.out.println(new SmallestCharacter().nextGreatestLetter(arr,'a'));
    }

}
