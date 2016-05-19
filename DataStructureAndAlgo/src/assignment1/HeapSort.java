package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HeapSort {
	int count = 0;
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Heap Sort: \n Select any one of below options \n 1. n=32 Random \n 2. n=32 Sorted \n 3. n=32 Reverse Sorted \n 4. n= 2 Power 10 \n 5. n= 2 Power 15 \n 6. n= 2 Power 20\n Input:");
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
		HeapSort obj = new HeapSort();

		obj.buildHeap(arr, 1, arr.length);

		for(int i = arr.length;i>0;i--){
			arr = obj.deleteMax(arr,i);
		}

		long end= System.nanoTime();
		System.out.println("Please Check the Directory for Output File: "+System.getProperty("user.dir")+"\n File Name: Merge_Out_"+file);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(System.getProperty("user.dir")+"\\Heap_Out_"+file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
		for(int t: arr){
			writer.println(t);
		}		
		System.out.println("count: "+ obj.count);
		writer.println("count: "+ obj.count);
		//Time in milli Second
		System.out.println("Total Time: "+(end-start)/1000000+"ms");
		writer.println("Total Time: "+(end-start)/1000000+"ms");
		writer.close();
	}
	//Delete Root element and swap to n
	int[] deleteMax(int[] a,int n){
		int t = a[0];
		a[0] = a[n-1];
		a[n-1] =t;
		a = pushdown(a,1,n-1);
		return a;
	}
	//Build Heap
	void buildHeap(int[] a,int r,int n){
		//Base Case
		if((2*r)>n)return;
		//Recursive case
		buildHeap(a,(2*r),n);
		buildHeap(a,(2*r)+1,n);
		a=pushdown(a,r,n);
	}
	//Push down root element
	private int[] pushdown(int[] a,int r,int n){
		int s;
		int left = 2*r,right = (2*r)+1;
		//Base Case
		if(compare(n,left)){
			return a;
		}
		if(compareEqual(left,n) || greatEqual(a[left-1],a[right-1])){
			s = left;
		}else{
			s = right;
		}
		if(compare(a[r-1],a[s-1])){
			int t = a[r-1];
			a[r-1] = a[s-1];
			a[s-1] = t;
			//Recursive Case
			a = pushdown(a,s,n);
		}
		return a;
	}
	boolean compare(int x,int y){
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
	boolean compareEqual(int x,int y){
		count++;
		if(x==y){
			return true;
		}
		return false;
	}

}
