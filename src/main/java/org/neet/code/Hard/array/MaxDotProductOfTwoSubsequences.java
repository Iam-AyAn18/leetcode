/*
 * 1458. Max Dot Product of Two Subsequences
 * Hard - Arrays
 *
 * Given two arrays nums1 and nums2.
 *
 * Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.
 *
 * A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the
 * characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of
 * [1,2,3,4,5] while [1,5,3] is not).
 *
 * Example 1:
 * Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
 * Output: 18
 * Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
 * Their dot product is (2*3 + (-2)*(-6)) = 18.
 *
 * Example 2:
 * Input: nums1 = [3,-2], nums2 = [2,-6,7]
 * Output: 21
 * Explanation: Take subsequence [3] from nums1 and subsequence [7] from nums2.
 * Their dot product is (3*7) = 21.
 *
 * Example 3:
 * Input: nums1 = [-1,-1], nums2 = [1,1]
 * Output: -1
 * Explanation: Take subsequence [-1] from nums1 and subsequence [1] from nums2.
 * Their dot product is -1.
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 500
 * -1000 <= nums1[i], nums2[i] <= 1000
 *
 * NOTE: This file is a LeetCode-style stub. The solution implementation is intentionally left as a TODO.
 */
package org.neet.code.Hard.array;

import java.util.Arrays;

public class MaxDotProductOfTwoSubsequences {

    /**
     * Returns the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.
     * TODO: implement an efficient solution (dynamic programming).
     *
     * @param nums1 first array
     * @param nums2 second array
     * @return maximum dot product
     */

    public int maxDotProduct(int[] nums1, int[] nums2) {
        // TODO: implement solution

        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n+1][m+1];

        for(var r :dp)
        {
            Arrays.fill(r, Integer.MIN_VALUE);
        }

        for(int i =1; i<=n; i++){
            for(int j=1; j<=m; j++){
                int best = nums1[i-1]*nums2[j-1];
                dp[i][j] = Math.max(dp[i-1][j],Math.max(dp[i][j-1], best+Math.max(0,dp[i-1][j-1])));
                System.out.print(nums1[i-1]+" "+nums2[j-1]+" "+dp[i][j]+"    ");
            }
            System.out.println();
        }
        return dp[n][m];
    }
    public int maxDotProduct2(int[] nums1, int[] nums2) {
        // TODO: implement solution

        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n][m];

        for(int i = 0; i<n; i++){
            for(int j=0; j<m; j++){
                int best=nums1[i]*nums2[j];
                if(i>0 && j>0)
                    best = Math.max(best, best+dp[i-1][j-1]);
                if(i>0)
                    best = Math.max(best, dp[i-1][j]);
                if(j>0)
                    best = Math.max(best, dp[i][j-1]);
                dp[i][j] = best;
                System.out.print(nums1[i]+" "+nums2[j]+" "+dp[i][j]+"    ");
            }
            System.out.println();
        }
        return dp[n-1][m-1];
    }

    public static void main(String[] args) {
        MaxDotProductOfTwoSubsequences sol = new MaxDotProductOfTwoSubsequences();

        int[] a1 = {2,1,-2,5};
        int[] b1 = {3,0,-6};
        int out1 = sol.maxDotProduct(a1, b1);
        System.out.println("Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6] -> Expected: 18, Got: " + out1);

        int[] a2 = {3,-2};
        int[] b2 = {2,-6,7};
        int out2 = sol.maxDotProduct(a2, b2);
        System.out.println("Input: nums1 = [3,-2], nums2 = [2,-6,7] -> Expected: 21, Got: " + out2);

        int[] a3 = {-1,-1};
        int[] b3 = {1,1};
        int out3 = sol.maxDotProduct(a3, b3);
        System.out.println("Input: nums1 = [-1,-1], nums2 = [1,1] -> Expected: -1, Got: " + out3);

        // Add more tests as desired. The implementation is intentionally left as a TODO.
    }
}

