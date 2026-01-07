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

public class MedianOfTwoSortedArrays {

    /**
     * Returns the median of the two sorted arrays.
     *
     * TODO: implement this method with O(log(m+n)) time complexity.
     *
     * @param nums1 first sorted array
     * @param nums2 second sorted array
     * @return median value as double
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Not implemented yet.
        throw new UnsupportedOperationException("Not implemented");
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays sol = new MedianOfTwoSortedArrays();
        // Example usages (uncomment after implementation):
        // System.out.println(sol.findMedianSortedArrays(new int[]{1,3}, new int[]{2})); // 2.0
        // System.out.println(sol.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4})); // 2.5
    }
}

