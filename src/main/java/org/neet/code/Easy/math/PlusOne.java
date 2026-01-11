package org.neet.code.Easy.math;/*
 * 66. Plus One
 * Easy
 *
 * Problem:
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer.
 * The digits are ordered from most significant to least significant in left-to-right order.
 * The large integer does not contain any leading 0's.
 *
 * Increment the large integer by one and return the resulting array of digits.
 *
 * Example 1:
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123. Incrementing by one gives 123 + 1 = 124. Thus, the result should be [1,2,4].
 *
 * Example 2:
 * Input: digits = [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321. Incrementing by one gives 4321 + 1 = 4322. Thus, the result should be [4,3,2,2].
 *
 * Example 3:
 * Input: digits = [9]
 * Output: [1,0]
 * Explanation: The array represents the integer 9. Incrementing by one gives 9 + 1 = 10. Thus, the result should be [1,0].
 */

public class PlusOne {
    public static int[] plusOne1(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    public static int[] plusOne(int[] digits){
        for(int i = digits.length-1; i>=0; i--)
        {
            if(digits[i]==9)
            {
                digits[i]=0;
            }
            else{
                digits[i]++;
                return digits;
            }
        }
        if(digits[0]==0)
        {
            int[] result = new int[digits.length+1];
            result[0]=1;
            return result;
        }
        else
            return digits;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3};
        int[] res1 = plusOne(test1);
        System.out.print("Test 1: ");
        for (int n : res1) System.out.print(n + " ");
        System.out.println();

        int[] test2 = {4, 3, 2, 1};
        int[] res2 = plusOne(test2);
        System.out.print("Test 2: ");
        for (int n : res2) System.out.print(n + " ");
        System.out.println();

        int[] test3 = {1,9};
        int[] res3 = plusOne(test3);
        int[] res3_1 = plusOne1(test3);
        System.out.print("Test 3: ");
        for (int n : res3) System.out.print(n + " ");
        System.out.println();
    }
}
