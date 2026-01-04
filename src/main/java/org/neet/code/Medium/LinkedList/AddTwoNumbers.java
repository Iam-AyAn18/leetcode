package org.neet.code.Medium.LinkedList;

/*
 * 2. Add Two Numbers
 * Medium
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
 * and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 *
 * NOTE: This file contains the problem statement and a method stub (no solution), per request.
 */

import java.util.List;

public class AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Add two numbers represented by linked lists and return the sum as a linked list.
     * Implementation intentionally omitted.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode result = dummy;
        int carry = 0;
        while(l1!= null || l2 != null || carry!=0)
        {
            int sum = carry;
            if(l1!=null)
            {
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2!=null)
            {
                sum += l2.val;
                l2 = l2.next;
            }
            result.next = new ListNode(sum%10);
            result = result.next;
            carry = sum/10;
        }

        return dummy.next;
    }

    // Helper to build a list from an array (digits in reverse order as in the problem)
    private static ListNode fromArray(int[] a) {
        if (a == null || a.length == 0) return null;
        ListNode head = new ListNode(a[0]);
        ListNode cur = head;
        for (int i = 1; i < a.length; i++) {
            cur.next = new ListNode(a[i]);
            cur = cur.next;
        }
        return head;
    }

    // Helper to format a list as [x,y,z]
    private static String toArrayString(ListNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode cur = node;
        boolean first = true;
        while (cur != null) {
            if (!first) sb.append(",");
            sb.append(cur.val);
            first = false;
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        // Example 1
        ListNode l1 = fromArray(new int[]{2,4,3});
        ListNode l2 = fromArray(new int[]{5,6,4});
        AddTwoNumbers sol = new AddTwoNumbers();
        System.out.println("Example1 (expected [7,0,8]): " + toArrayString(sol.addTwoNumbers(l1, l2)));

        // Example 2
        ListNode a1 = fromArray(new int[]{0});
        ListNode a2 = fromArray(new int[]{0});
        System.out.println("Example2 (expected [0]): " + toArrayString(sol.addTwoNumbers(a1, a2)));

        // Example 3
        ListNode b1 = fromArray(new int[]{9,9,9,9,9,9,9});
        ListNode b2 = fromArray(new int[]{9,9,9,9});
        System.out.println("Example3 (expected [8,9,9,9,0,0,0,1]): " + toArrayString(sol.addTwoNumbers(b1, b2)));
    }
}
