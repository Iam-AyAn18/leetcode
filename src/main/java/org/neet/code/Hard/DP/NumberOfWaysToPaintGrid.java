package org.neet.code.Hard.DP;

/*
 * 1411. Number of Ways to Paint N × 3 Grid
 * Hard
 *
 * Problem:
 * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors: Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that share vertical or horizontal sides have the same color).
 *
 * Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 1
 * Output: 12
 * Explanation: There are 12 possible way to paint the grid as shown.
 *
 * Example 2:
 * Input: n = 5000
 * Output: 30228214
 *
 * Constraints:
 * n == grid.length
 * 1 <= n <= 5000
 */

public class NumberOfWaysToPaintGrid {
    static final int MOD = 1_000_000_007;

    public static int numOfWays(int n) {

        int c=1;
        int type1=6;
        int type2=6;
        while(c!=n){
            int new_type1 = (2 * type1 + 2 * type2) % MOD;
            int new_type2= (2 * type1 + 3 * type2) % MOD;
            type1 = new_type1;
            type2 = new_type2;
        c++;}
        return (int)((((long)type1 + type2) % MOD + MOD) % MOD);
    }

    public static void main(String[] args) {
        int n1 = 1;
        System.out.println("Test 1: " + numOfWays(n1)); // Output: 12

        int n2 = 5000;
        System.out.println("Test 2: " + numOfWays(n2)); // Output: 30228214
    }
}
