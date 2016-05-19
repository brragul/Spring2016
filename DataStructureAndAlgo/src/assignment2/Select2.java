package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Select2 {
	int count = 0;

	public static void main(String[] args) throws FileNotFoundException {
		int n = 0;
		System.out.println("Select2 \nEnter 1. 10 000 2. 100 000 3. 1000 000 4. n<25");
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
		Integer[] arr = new Integer[n];
		int j = 0;
		while (sc.hasNext()) {
			arr[j] = Integer.parseInt(sc.next());
			j++;
		}
		if (n < 25) {
			InsertionSort is = new InsertionSort();
			int f = is.findKth(arr, n / 2);
			System.out.println("Alogrithm Select2: n:" + n + ", k:" + (n / 2) + ", a[k]:" + f + ", count:" + is.count);
			return;
		}
		Select2 obj = new Select2();
		int f = obj.select2(arr, 0, arr.length, n / 2);
		System.out.println("Alogrithm Select2: n:" + n + ", k:" + (n / 2) + ", a[k]:" + f + ", count:" + obj.count);
		// System.out.println(f+" "+obj.count);

	}

	int select2(Integer[] a, int s, int n, int k) {
		int kth = ((int) (Math.random() * ((n + s) - s))) + s;

		int pivot = a[kth];
		// System.out.println("pivot : "+pivot+" N : "+n);

		ArrayList<Integer> L = new ArrayList<Integer>();
		ArrayList<Integer> E = new ArrayList<Integer>();
		ArrayList<Integer> G = new ArrayList<Integer>();

		for (int t : a) {
			if (compare(t, pivot)) {
				L.add(t);
			} else if (compareEqual(t, pivot)) {
				E.add(t);
			} else {
				G.add(t);
			}
		}

		if (lessEqual(k, L.size())) {// L.size is n1
			// Convert ArrayList to Array
			Integer[] arr = new Integer[L.size()];
			Iterator<Integer> iterator = L.iterator();
			for (int i = 0; i < arr.length; i++) {
				arr[i] = iterator.next().intValue();
			}
			int tk = select2(arr, 0, L.size(), k);
			return tk;
		} else if (lessEqual(k, (L.size() + E.size()))) {// E.size is n2
			// Convert ArrayList to Array
			Integer[] arr = new Integer[E.size()];
			Iterator<Integer> iterator = E.iterator();
			for (int i = 0; i < arr.length; i++) {
				arr[i] = iterator.next().intValue();
			}
			return arr[0];

		} else {// G.size is n3
			// Convert ArrayList to Array
			Integer[] arr = new Integer[G.size()];
			Iterator<Integer> iterator = G.iterator();
			for (int i = 0; i < arr.length; i++) {
				arr[i] = iterator.next().intValue();
			}
			int tk = select2(arr, 0, G.size(), k - (L.size() + E.size()));
			return tk;
		}
	}

	private boolean compare(int x, int y) {
		count++;
		if (x < y) {
			return true;
		}
		return false;
	}

	boolean compareEqual(int x, int y) {
		count++;
		if (x == y) {
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
