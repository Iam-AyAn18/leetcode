/*
 * 4. Median of Two Sorted Arrays
 * Hard
 *
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 * Constraints:
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * Examples (for reference):
 * Input: nums1 = [1,3], nums2 = [2] -> Output: 2.0
 * Input: nums1 = [1,2], nums2 = [3,4] -> Output: 2.5
 */
package org.neet.code.Hard.BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class MedianOfTwoSortedArrays {

    /**
     * Returns the median of the two sorted arrays.
     * <p>
     * TODO: implement this method with O(log(m+n)) time complexity.
     *
     * @param nums1 first sorted array
     * @param nums2 second sorted array
     * @return median value as double
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1 == null) nums1 = new int[0];
        if (nums2 == null) nums2 = new int[0];

        List<Integer> list = new ArrayList<>();
        int total = nums1.length + nums2.length;
        int k = total / 2;
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length && list.size() < k + 1) {
            if (nums1[i] < nums2[j]) list.add(nums1[i++]);
            else list.add(nums2[j++]);
        }
        while (list.size() < k + 1 && i < nums1.length) list.add(nums1[i++]);
        while (list.size() < k + 1 && j < nums2.length) list.add(nums2[j++]);

        if (total % 2 == 0) {
            return ((double) list.get(k - 1) + (double) list.get(k)) / 2.0;
        } else {
            return (double) list.get(k);
        }
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays sol = new MedianOfTwoSortedArrays();

        // Example 1
        int[] a1 = new int[]{1, 3};
        int[] b1 = new int[]{2};
        double r1 = sol.findMedianSortedArrays(a1, b1);
        System.out.println("Input: nums1=[1,3], nums2=[2] -> Expected: 2.0, Got: " + r1);

        // Example 2
        int[] a2 = new int[]{1, 2};
        int[] b2 = new int[]{3, 4};
        double r2 = sol.findMedianSortedArrays(a2, b2);
        System.out.println("Input: nums1=[1,2], nums2=[3,4] -> Expected: 2.5, Got: " + r2);

        // Additional small checks
        System.out.println("Input: nums1=[], nums2=[1] -> Expected: 1.0, Got: " + sol.findMedianSortedArrays(new int[]{}, new int[]{1}));
        System.out.println("Input: nums1=[2], nums2=[] -> Expected: 2.0, Got: " + sol.findMedianSortedArrays(new int[]{2}, new int[]{}));
    }
}
