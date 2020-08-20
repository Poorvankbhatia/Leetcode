package stack.medium;

import java.util.Stack;

public class CountSubMatrixWithAllOnes {

    public int numSubmat(int[][] mat) {

        int N = mat[0].length;

        int res = 0;

        int[] h = new int[N];
        for (int[] ints : mat) {
            for (int j = 0; j < N; j++) {
                h[j] = (ints[j] == 0 ? 0 : h[j] + 1);
            }
            res += util(h);
        }

        return res;
    }

    private int util(int[] A) {

        int[] width = new int[A.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < A.length; ++i) {

            while (!stack.isEmpty() && A[stack.peek()] >= A[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                int preIndex = stack.peek();
                width[i] = width[preIndex];
                width[i] += A[i] * (i - preIndex);
            } else {
                width[i] = A[i] * (i + 1);
            }

            stack.push(i);
        }

        int res = 0;
        for (int s : width) {
            res += s;
        }

        return res;
    }

}
