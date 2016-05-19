package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Select1 {
	int count = 0;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int n = 0;
		System.out.println("Select 1: \nEnter 1. 10 000 2. 100 000 3. 1000 000 4. n<25");
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		String filename = "";
		switch (q) {
		case 1:
			n = 10000;
			filename = "10Pow4.txt";
			break;
		case 2:
			n = 100000;
			filename = "10Pow5.txt";
			break;
		case 3:
			n = 1000000;
			filename = "10Pow6.txt";
			break;
		case 4:
			n = 24;
			filename = "n24.txt";
			break;
		default:
			n = 10000;
			filename = "10Pow4.txt";
			break;
		}
		Scanner sc = new Scanner(new File(System.getProperty("user.dir") + "//" + filename));
		int[] arr = new int[n];
		int j = 0;
		while (sc.hasNext()) {
			arr[j] = Integer.parseInt(sc.next());
			j++;
		}
		Select1 obj = new Select1();
		arr = obj.quickSort(arr, 0, arr.length - 1);
		System.out.println(
				"Alogrithm Select1: n:" + n + ", k:" + (n / 2) + ", a[k]:" + arr[(n / 2) - 1] + ", count:" + obj.count);

	}

	int[] quickSort(int[] a, int left, int right) {
		if (left > right)
			return a;// Base case
		int k = ((int) (Math.random() * (right - left))) + left;
		int pivot = a[k];
		int t = a[left];
		a[left] = a[k];
		a[k] = t;
		int l = left + 1;
		int r = right;
		while (lessEqual(l, r)) {
			while (lessEqual(l, r) && lessEqual(a[l], pivot))
				l++;
			while (lessEqual(l, r) && greatEqual(a[r], pivot))
				r--;
			if (compare(l, r)) {
				int t1 = a[l];
				a[l] = a[r];
				a[r] = t1;
				l++;
				r--;

			}
		}
		int t2 = a[left];
		a[left] = a[r];
		a[r] = t2;
		// Recursive case
		a = quickSort(a, left, r - 1);
		a = quickSort(a, r + 1, right);
		return a;
	}

	private boolean compare(int x, int y) {
		count++;
		if (x < y) {
			return true;
		}
		return false;
	}

	boolean greatEqual(int x, int y) {
		count++;
		if (x >= y) {
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
