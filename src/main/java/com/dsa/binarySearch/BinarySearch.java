package com.dsa.BinarySearch;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch ob = new BinarySearch();
        int arr[] = { 2, 3, 4, 10, 40 };
        int n = arr.length;
        int x = 11;
        int result = ob.binarySearch(arr, x);
        if (result == -1)
            System.out.println(
                    "Element is not present in array");
        else
            System.out.println("Element is present at "
                    + "index " + result);
    }

    private int binarySearch(int[] arr, int x) {
        int start = 0;
        int end = arr.length - 1;
        while(start <= end) {
            int mid = start + (end -start) / 2;

            if(arr[mid] == x) {
                return mid; // Element found
            }

            if(arr[mid] < x) {
                start = mid + 1; // Search in the right half
            } else {
                end = mid - 1; // Search in the left half
            }
        }
        return -1 ; // Element not found
    }
}
