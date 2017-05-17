package com.SaleSystem;


public class QuickSort {


	public static void quickSort(int arr[], int low, int high) {
		int pivot;
		// Termination condition!
		if (high > low) {
			pivot = Partition(arr, low, high);
			quickSort(arr, low, pivot - 1);
			quickSort(arr, pivot + 1, high);
		}
	}

	private static int Partition(int[] arr, int low, int high) {
		int left, right, pivot_item = arr[low];
		left = low;
		right = high;
		while (left < right) {
			// Move left while item < pivot
			while (arr[left] <= pivot_item)
				left++;
			// Move right while item > pivot
			while (arr[right] > pivot_item)
				right--;
			if (left < right)
				swap(arr, left, right);
		}
		// right is final position for the pivot
		arr[low] = arr[right];
		arr[right] = pivot_item;
		return right;
	}

	private static void swap(int[] arr, int left, int right) {
		int temp = 0;
		temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}

	public static void main(String[] args) {
		int arr[] = { 2, 8, 80, 7, 1, 10, 20, 90, 40, 30, 3, 5, 6, 4, 9 };
		quickSort(arr, 0, 14);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println();
	}

}