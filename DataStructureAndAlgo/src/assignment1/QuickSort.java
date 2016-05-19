package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class QuickSort {

	int count =0;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Quick Sort: \n Select any one of below options \n 1. n=32 Random \n 2. n=32 Sorted \n 3. n=32 Reverse Sorted \n 4. n= 2 Power 10 \n 5. n= 2 Power 15 \n 6. n= 2 Power 20\n Input:");
		Scanner in = new Scanner(System.in);
		int n = 0;
		int num = in.nextInt();
		String file = "";
		switch(num){

		case 1:
			file = "2pow5_rand.txt";
			n=32;
			break;
		case 2:
			file = "2pow5_sort.txt";
			n=32;
			break;
		case 3:
			file = "2pow5_rev_sort.txt";
			n=32;
			break;
		case 4:
			file = "2pow10.txt";
			n=1024;
			break;
		case 5:
			file = "2pow15.txt";
			n=32768;
			break;
		case 6:
			file = "2pow20.txt";
			n=1048576;
			break;
		default:
			file = "2pow5_rand.txt";
			n=32;
			break;
		}
		long start=System.nanoTime();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(System.getProperty("user.dir")+"\\"+file));
		} catch (FileNotFoundException e) {
			System.out.println("Please Keep Input Files here : "+System.getProperty("user.dir"));
			e.printStackTrace();
		}
		int[] arr = new int[n];
		int j=0;
		while(sc.hasNext()){
			arr[j]= Integer.parseInt(sc.next());
			j++;
		}
		QuickSort obj = new QuickSort();

		arr = obj.quickSort(arr, 0, arr.length-1);

		long end = System.nanoTime();
		System.out.println("Please Check the Directory for Output File: "+System.getProperty("user.dir")+"\n File Name: Merge_Out_"+file);
		PrintWriter writer = null;
		try {
			//Output File
			writer = new PrintWriter(System.getProperty("user.dir")+"\\Quick_Out_"+file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
		for(int t: arr){
			writer.println(t);
		}		
		System.out.println("count: "+ obj.count);
		writer.println("count: "+ obj.count);
		System.out.println("Total Time: "+(end-start)/1000000+"ms");
		writer.println("Total Time: "+(end-start)/1000000+"ms");
		writer.close();

	}
	int[] quickSort(int[] a,int left,int right){
		if(left>right) return a;//Base case
		int k = ((int) (Math.random()*(right - left))) + left;
		int pivot = a[k];
		int t=a[left];
		a[left]=a[k];
		a[k]=t;
		int l = left+1;
		int r = right;
		while(lessEqual(l, r)){
			while(lessEqual(l, r) && lessEqual(a[l], pivot))l++;
			while(lessEqual(l, r) && greatEqual(a[r], pivot))r--;
			if(compare(l, r)){
				int t1 = a[l];
				a[l] = a[r];
				a[r] = t1;
				l++;r--;

			}
		}
		int t2 = a[left];
		a[left] = a[r];
		a[r] = t2;
		//Recursive case
		a=quickSort(a,left,r-1);
		a=quickSort(a,r+1,right);
		return a;
	}

	private boolean compare(int x,int y){
		count++;
		if(x<y){
			return true;
		}
		return false;
	}
	boolean greatEqual(int x,int y){
		count++;
		if(x>=y){
			return true;
		}
		return false;
	}
	boolean lessEqual(int x,int y){
		count++;
		if(x<=y){
			return true;
		}
		return false;
	}
}
