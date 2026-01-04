package org.neet.code.Medium.Matrix;/*
 * 1975. Maximum Matrix Sum
 * Medium
 *
 * You are given an n x n integer matrix. You can do the following operation any number of times:
 *
 * Choose any two adjacent elements of matrix and multiply each of them by -1.
 * Two elements are considered adjacent if and only if they share a border.
 *
 * Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements
 * using the operation mentioned above.
 *
 * Example 1:
 * Input: matrix = [[1,-1],[-1,1]]
 * Output: 4
 * Explanation: We can follow the following steps to reach sum equals 4:
 * - Multiply the 2 elements in the first row by -1.
 * - Multiply the 2 elements in the first column by -1.
 *
 * Example 2:
 * Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
 * Output: 16
 * Explanation: We can follow the following step to reach sum equals 16:
 * - Multiply the 2 last elements in the second row by -1.
 *
 * Constraints:
 * n == matrix.length == matrix[i].length
 * 2 <= n <= 250
 * -10^5 <= matrix[i][j] <= 10^5
 *
 * NOTE: This file intentionally contains only the problem statement and a method stub (no solution), per request.
 */

public class MaximumMatrixSum1975 {

    /**
     * Returns the maximum possible sum of all matrix elements after performing the allowed operation any number of times.
     *
     * NOTE: Implementation intentionally omitted.
     *
     * @param matrix input n x n integer matrix
     * @return maximum achievable sum (method not implemented)
     */
    public long maxMatrixSum(int[][] matrix) {
        long sumAbs = 0L;
        int countNeg =0;
        boolean hasZero =false;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int abs= Math.abs(matrix[i][j]);
                sumAbs += abs;
                if(matrix[i][j]==0){
                    hasZero=true;
                }
                if(matrix[i][j]<0){
                    countNeg++;
                }
                if(abs<min){
                    min=abs;
                }
            }
        }
        if(countNeg%2==0 || hasZero){
            return sumAbs;
        }
        else{
            Long m = sumAbs - 2L*min;
            return sumAbs-2L*min;
        }
    }

    public static void main(String[] args) {
        MaximumMatrixSum1975 sol = new MaximumMatrixSum1975();

        int[][] example1 = {{1, -1}, {-1, 1}};
        System.out.println("Example1 (expected 4): " + sol.maxMatrixSum(example1));

        int[][] example2 = {{1, 2, 3}, {-1, -2, -3}, {1, 2, 3}};
        System.out.println("Example2 (expected 16): " + sol.maxMatrixSum(example2));

        int[][] example3 = {
                {-10000, -10000, -10000},
                {-10000, -10000, -10000},
                {-10000, -10000, -10000}
        };
        System.out.println("Example3 (expected 70000): " + sol.maxMatrixSum(example3));
    }
}
