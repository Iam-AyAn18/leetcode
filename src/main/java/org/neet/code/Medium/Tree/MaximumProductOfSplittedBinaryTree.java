/*
 * 1339. Maximum Product of Splitted Binary Tree
 * Medium
 *
 * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that
 * the product of the sums of the subtrees is maximized.
 *
 * Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it
 * modulo 10^9 + 7.
 *
 * Note that you need to maximize the answer before taking the mod and not after taking it.
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [2, 5 * 10^4].
 * - 1 <= Node.val <= 10^4
 *
 * Examples (for reference):
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove one edge to split into subtrees with sums 11 and 10, product = 110.
 *
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation: Remove one edge to split into subtrees with sums 15 and 6, product = 90.
 */
package org.neet.code.Medium.Tree;

import java.util.ArrayList;
import java.util.List;

public class MaximumProductOfSplittedBinaryTree {

    // Modulo constant as described in the problem
    private static final int MOD = 1_000_000_007;

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Compute max product by:
     * 1) postorder traversal to collect subtree sums (long)
     * 2) iterate sums to find max(s * (total - s))
     */
    public int maxProduct(TreeNode root) {

        List<Long> sums = new ArrayList<>();
        long total = dfsSum(root, sums);
        long maxProd = 0L;
        for (long s : sums) {
            long prod = s * (total - s);
            if (prod > maxProd) maxProd = prod;
        }
        return (int) (maxProd % MOD);
    }

    private long dfsSum(TreeNode root, List<Long> sums) {
        if (root == null) return 0L;
        long left = dfsSum(root.left, sums);
        long right = dfsSum(root.right, sums);
        long s = left + right + root.val;
        sums.add(s);
        return s;
    }

    public static void main(String[] args) {
        MaximumProductOfSplittedBinaryTree sol = new MaximumProductOfSplittedBinaryTree();

        // Test 1: Example construction for root = [1,2,3,4,5,6]
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n3 = new TreeNode(3, n6, null);
        TreeNode root1 = new TreeNode(1, n2, n3);
        int result1 = sol.maxProduct(root1);
        System.out.println("Test 1: expected=110, got=" + result1);

        // Test 2: Example 2: [1,null,2,3,4,null,null,5,6]
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t3 = new TreeNode(3, t5, t6);
        TreeNode t4 = new TreeNode(4);
        TreeNode t2 = new TreeNode(2, t3, t4);
        TreeNode root2 = new TreeNode(1, null, t2);
        int result2 = sol.maxProduct(root2);
        System.out.println("Test 2: expected=90, got=" + result2);

        // Test 3: Minimal two nodes [1,1]
        TreeNode a1 = new TreeNode(1);
        TreeNode root3 = new TreeNode(1, a1, null);
        int result3 = sol.maxProduct(root3);
        System.out.println("Test 3: expected=1, got=" + result3);

        // Test 4: Chain [1,null,2,null,3,null,4]
        TreeNode c4 = new TreeNode(4);
        TreeNode c3 = new TreeNode(3, null, c4);
        TreeNode c2 = new TreeNode(2, null, c3);
        TreeNode root4 = new TreeNode(1, null, c2);
        int result4 = sol.maxProduct(root4);
        System.out.println("Test 4: expected=24, got=" + result4);

        // Test 5: Small balanced all-equal [1,1,1]
        TreeNode b1 = new TreeNode(1);
        TreeNode b2 = new TreeNode(1);
        TreeNode root5 = new TreeNode(1, b1, b2);
        int result5 = sol.maxProduct(root5);
        System.out.println("Test 5: expected=2, got=" + result5);

        // Test 6: Overflow-check: construct left and right subtrees each summing to 50000
        // Left subtree: chain of five nodes with value 10000 -> sum 50000
        TreeNode L5 = new TreeNode(10000);
        TreeNode L4 = new TreeNode(10000, null, L5);
        TreeNode L3 = new TreeNode(10000, null, L4);
        TreeNode L2 = new TreeNode(10000, null, L3);
        TreeNode L1 = new TreeNode(10000, null, L2);
        // Right subtree: similar
        TreeNode R5 = new TreeNode(10000);
        TreeNode R4 = new TreeNode(10000, null, R5);
        TreeNode R3 = new TreeNode(10000, null, R4);
        TreeNode R2 = new TreeNode(10000, null, R3);
        TreeNode R1 = new TreeNode(10000, null, R2);
        // Root value 0 to keep total = 100000
        TreeNode root6 = new TreeNode(0, L1, R1);
        int result6 = sol.maxProduct(root6);
        // Expected product = 50000 * 50000 = 2500000000 -> modulo 1_000_000_007 = 499999986
        System.out.println("Test 6: expected=499999986, got=" + result6);
    }
}
