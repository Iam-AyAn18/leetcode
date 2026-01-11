package org.neet.code.Medium.Tree;

/*
 * 1161. Maximum Level Sum of a Binary Tree
 * Medium
 *
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 *
 * Example 1:
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 * Example 2:
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^5 <= Node.val <= 10^5
 *
 * NOTE: This file contains the problem statement and a method stub (no solution), per request.
 */

import java.util.ArrayDeque;
import java.util.Queue;

public class MaximumLevelSumOfBinaryTree {
    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Returns the smallest level x such that the sum of all the values of nodes at level x is maximal.
     * Implementation intentionally omitted.
     */
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int level = 1;
        int max = Integer.MIN_VALUE;
        int result = 1;
        while (!q.isEmpty()) {
            int sum = 0;
            int size = q.size();
            while (size > 0) {
                size--;
                TreeNode tmp = q.poll();
                sum += tmp.val;
                if (tmp.left != null) q.offer(tmp.left);
                if (tmp.right != null) q.offer(tmp.right);
            }
            if (max < sum) {
                max = sum;
                result = level;
            }
            level++;
        }
        return result;
    }

    public static void main(String[] args) {
        // Example 1: root = [1,7,0,7,-8,null,null]
        TreeNode root1 = new TreeNode(1,
                new TreeNode(7,
                        new TreeNode(7),
                        new TreeNode(-8)
                ),
                new TreeNode(0)
        );
        MaximumLevelSumOfBinaryTree sol = new MaximumLevelSumOfBinaryTree();
        System.out.println("Example1 (expected 2): " + sol.maxLevelSum(root1));

        // Example 2: root = [989,null,10250,98693,-89388,null,null,null,-32127]
        TreeNode root2 = new TreeNode(989,
                null,
                new TreeNode(10250,
                        new TreeNode(98693,
                                null,
                                new TreeNode(-32127)
                        ),
                        new TreeNode(-89388)
                )
        );
        System.out.println("Example2 (expected 2): " + sol.maxLevelSum(root2));
    }
}
