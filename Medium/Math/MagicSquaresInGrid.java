/*
 * 840. Magic Squares In Grid
 * Medium
 *
 * Problem:
 * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9
 * such that each row, column, and both diagonals all have the same sum.
 *
 * Given a row x col grid of integers, return how many 3 x 3 magic square subgrids are there.
 *
 * Note: while a magic square can only contain numbers from 1 to 9, grid may contain numbers up to 15.
 *
 * Example 1:
 * Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
 * Output: 1
 *
 * Example 2:
 * Input: grid = [[8]]
 * Output: 0
 *
 * Constraints:
 * - row == grid.length
 * - col == grid[i].length
 * - 1 <= row, col <= 10
 * - 0 <= grid[i][j] <= 15
 */

package org.neet.code.slidingWindow;

public class MagicSquaresInGrid {


    public int numMagicSquaresInside(int[][] grid)  {
        if (grid == null || grid.length < 3 || grid[0].length < 3)
            return 0;

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;


        for (int i = 0; i <= m - 3; i++) {
            for (int j = 0; j <= n - 3; j++) {

                // Step 1: center must be 5
                if (grid[i + 1][j + 1] != 5) continue;

                boolean[] seen = new boolean[10];
                boolean valid = true;

                // Step 2: column sum + uniqueness
                for (int col = 0; col < 3 && valid; col++) {
                    int sum = 0;
                    for (int row = 0; row < 3; row++) {
                        int val = grid[i + row][j + col];
                        if (val < 1 || val > 9 || seen[val]) {
                            valid = false;
                            break;
                        }
                        seen[val] = true;
                        sum += val;
                    }
                    if (sum != 15) valid = false;
                }

                if (!valid) continue;

                // Step 3: row sums
                for (int row = 0; row < 3 && valid; row++) {
                    int sum = 0;
                    for (int col = 0; col < 3; col++) {
                        sum += grid[i + row][j + col];
                    }
                    if (sum != 15) valid = false;
                }

                if (!valid) continue;

                // Step 4: diagonals
                int d1 = grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2];
                int d2 = grid[i][j+2] + grid[i+1][j+1] + grid[i+2][j];

                if (d1 != 15 || d2 != 15) continue;

                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MagicSquaresInGrid solution = new MagicSquaresInGrid();

        // Test case 1: Contains one magic square
        int[][] grid1 = {
                {4, 3, 8, 4},
                {9, 5, 1, 9},
                {2, 7, 6, 2}
        };
        System.out.println("Test 1: " + solution.numMagicSquaresInside(grid1)); // Expected: 1

        // Test case 2: No magic square (grid too small)
        int[][] grid2 = {
                {4, 3},
                {9, 5}
        };
        System.out.println("Test 2: " + solution.numMagicSquaresInside(grid2)); // Expected: 0

        // Test case 3: Single cell
        int[][] grid3 = {{8}};
        System.out.println("Test 3: " + solution.numMagicSquaresInside(grid3)); // Expected: 0

        // Test case 4: 3x3 magic square
        int[][] grid4 = {
                {2, 7, 6},
                {9, 5, 1},
                {4, 3, 8}
        };
        System.out.println("Test 4: " + solution.numMagicSquaresInside(grid4)); // Expected: 1

        // Test case 5: Multiple magic squares
        int[][] grid5 = {
                {4, 3, 8, 4, 3, 8},
                {9, 5, 1, 9, 5, 1},
                {2, 7, 6, 2, 7, 6}
        };
        System.out.println("Test 5: " + solution.numMagicSquaresInside(grid5)); // Expected: 2

        // Test case 6: No magic square (center not 5)
        int[][] grid6 = {
                {1, 2, 3},
                {4, 6, 7},
                {8, 9, 5}
        };
        System.out.println("Test 6: " + solution.numMagicSquaresInside(grid6)); // Expected: 0

        // Test case 7: Values outside 1-9 range
        int[][] grid7 = {
                {10, 3, 8},
                {9, 5, 1},
                {2, 7, 6}
        };
        System.out.println("Test 7: " + solution.numMagicSquaresInside(grid7)); // Expected: 0

        // Test case 8: Duplicate values (all 5s)
        int[][] grid8 = {
                {5, 5, 5},
                {5, 5, 5},
                {5, 5, 5}
        };
        System.out.println("Test 8: " + solution.numMagicSquaresInside(grid8)); // Expected: 0

        // Test case 9: Large grid with one magic square embedded
        int[][] grid9 = {
                {1, 1, 1, 1, 1},
                {1, 4, 3, 8, 1},
                {1, 9, 5, 1, 1},
                {1, 2, 7, 6, 1},
                {1, 1, 1, 1, 1}
        };
        System.out.println("Test 9: " + solution.numMagicSquaresInside(grid9)); // Expected: 1

        // Test case 10: All zeros (values < 1)
        int[][] grid10 = {
                {0, 0, 0},
                {0, 5, 0},
                {0, 0, 0}
        };
        System.out.println("Test 10: " + solution.numMagicSquaresInside(grid10)); // Expected: 0

        // Test case 11: Empty rows
        int[][] grid11 = new int[0][0];
        try {
            System.out.println("Test 11: " + solution.numMagicSquaresInside(grid11));
        } catch (Exception e) {
            System.out.println("Test 11: Exception - " + e.getClass().getSimpleName()); // Expected: ArrayIndexOutOfBoundsException
        }

        // Test case 12: Negative values
        int[][] grid12 = {
                {-4, 3, 8},
                {9, 5, 1},
                {2, 7, 6}
        };
        System.out.println("Test 12: " + solution.numMagicSquaresInside(grid12)); // Expected: 0
    }
}
