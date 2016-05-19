package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Select3 {
	int count = 0;
	boolean finPivot = false;

	public static void main(String[] args) throws FileNotFoundException {
		int n = 0;
		System.out.println("Select3 \nEnter 1. 10 000 2. 100 000 3. 1000 000 4. n<25");
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
		Select3 obj = new Select3();
		int res = obj.select3(arr, 0, arr.length, n / 2);
		// System.out.println(res);
		System.out.println("Alogrithm Select3: n:" + n + ", k:" + (n / 2) + ", a[k]:" + res + ", count:" + obj.count);
	}

	int findPivot(Integer[] a, int s, int l) {
		if (l == 0) {
			return a[0];
		}
		int add = l;
		int d = a.length / l;
		int rem = a.length % l;
		int mid = l / 2;
		int m = 0;
		for (int i = 0; compare(i, d); i++) {

			for (int k = (i * l) + 1; compare(k, (i * l) + add); k++) {
				int j = k;
				while (compare((i * l), j) && compare(a[j], a[j - 1])) {
					a = swap(a, j - 1, j);
					j--;
				}

			}

			if (finPivot == true) {
				return a[mid];
			}
			a = swap(a, m, (i * l) + mid);
			m++;
			if (i == (d - 1) && rem > 0) {
				add = rem;
				mid = rem / 2;
			}

		}
		finPivot = true;
		//Find pivot of baby medians
		return findPivot(a, 0, d);
	}

	Integer[] swap(Integer[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}

	int select3(Integer[] a, int s, int n, int k) {
		Integer[] tempa = a;
		int pivot = findPivot(tempa, s, 5);
		// System.out.println("pivot : "+pivot+" N : "+n);
		finPivot = false;

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
			int tk = select3(arr, 0, L.size(), k);
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
			int tk = select3(arr, 0, G.size(), k - (L.size() + E.size()));
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
