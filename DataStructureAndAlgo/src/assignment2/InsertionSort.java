package assignment2;

public class InsertionSort {
	int count = 0;

	public int findKth(Integer[] arr, int k) {
		if (arr.length > 25) {
			return -1;
		}
		for (int i = 1; compare(i, arr.length); i++) {
			int j = i;
			while (compare(0, j) && compare(arr[j], arr[j - 1])) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
				j--;
			}
		}
		return arr[k - 1];
	}

	private boolean compare(int x, int y) {
		count++;
		if (x < y) {
			return true;
		}
		return false;
	}

	boolean lessEqual(int x, int y) {
		count++;
		if (x <= y) {
			return true;
		}
		return false;
	}

}
