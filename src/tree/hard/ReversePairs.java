package tree.hard;

/**
 * Created by poorvank.b on 02/12/17.
 */
public class ReversePairs {

    private class Node {

        private Node left;
        private Node right;
        private int value;
        private int greaterCount;

    }

    private Node root;

    private Node addToBST(Integer x,Node root) {
        if(root==null) {
            root = new Node();
            root.value=x;
            root.greaterCount=1;
        } else if(root.value>x) {
            root.left = addToBST(x,root.left);
        } else {
            root.greaterCount++;
            root.right = addToBST(x,root.right);
        }
        return root;
    }

    private int searchCount(long x,Node root) {
        if(root==null) {
            return 0;
        }
        if(x==root.value) {
            return root.greaterCount;
        } else if(x<root.value) {
            return root.greaterCount+searchCount(x,root.left);
        } else {
            return searchCount(x,root.right);
        }
    }

    public int reversePairs(int[] nums) {

        if(nums==null || nums.length==0) {
            return 0;
        }

        int ans=0;
        root=null;

        for (int num : nums) {
            Long value = (num*2L+1);
            ans+=searchCount(value,root);
            root = addToBST(num,root);
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
        System.out.println(new ReversePairs().reversePairs(arr));
    }

}
