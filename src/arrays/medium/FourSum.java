package arrays.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank.b on 15/12/18.
 */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<n-3;) {
            for(int j=i+1;j<n-2;) {
                int k=j+1;
                int l=n-1;
                while(k<l) {
                    int sum = nums[i]+nums[j]+nums[k]+nums[l];
                    if(sum==target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        result.add(list);
                        l--;
                        while(nums[l]==nums[l+1] && k<l) {
                            l--;
                        }
                        k++;
                        while(nums[k]==nums[k-1] && k<l) {
                            k++;
                        }
                    } else if(sum>target) {
                        l--;
                    } else {
                        k++;
                    }
                }
                j++;
                while(j<n-2 && nums[j]==nums[j-1]) {
                    j++;
                }
            }
            i++;
            while(i<n-1 && nums[i]==nums[i-1]) {
                i++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, -1, 0, -2, 2};
        System.out.println(new FourSum().fourSum(arr,0));
    }

}

/*

Generalised KSum:


class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums,4,target,0);
    }

    private List<List<Integer>> kSum(int[] nums,int k,int target,int index) {
        List<List<Integer>> result = new ArrayList<>();
        if(index >= nums.length) {
            return result;
        }
        if(k==2) {
            int l =index,r = nums.length-1;
            while(l<r) {
                int sum = nums[l]+nums[r];
                if(sum==target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[l]);
                    list.add(nums[r]);
                    result.add(list);
                    l++;
                    while(l<r && nums[l]==nums[l-1]) {
                        l++;
                    }
                    r--;
                    while(l<r && nums[r]==nums[r+1]) {
                        r--;
                    }
                } else if(sum>target) {
                    r--;
                } else {
                    l++;
                }
            }
        } else {
            for(int i=index;i<nums.length-k+1;) {
                List<List<Integer>> list = kSum(nums,k-1,target-nums[i],i+1);
                if(list!=null) {
                    for(List<Integer> l : list) {
                        l.add(0,nums[i]);
                    }
                    result.addAll(list);
                }
                i++;
                while(i<nums.length-1 && nums[i]==nums[i-1]) {
                    i++;
                }
            }
        }

        return result;

    }

}


implement the 3sum and 4sum by using the sorting approach: reduce them into 2sum at the end, you might already
got the feeling that, all ksum problem can be divided into two problems:

2sum Problem
Reduce K sum problem to K – 1 sum Problem
Therefore, the ideas is simple and straightforward. We could use recursive to solve this problem. Time complexity is O(N^(K-1)).

 */