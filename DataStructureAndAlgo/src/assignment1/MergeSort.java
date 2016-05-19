package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MergeSort {

	private int count = 0;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Merge Sort: \n Select any one of below options \n 1. n=32 Random \n 2. n=32 Sorted \n 3. n=32 Reverse Sorted \n 4. n= 2 Power 10 \n 5. n= 2 Power 15 \n 6. n= 2 Power 20\n Input:");
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
		MergeSort obj = new MergeSort();

		arr=obj.divideAndConquer(arr,0,arr.length);

		long end= System.nanoTime();
		System.out.println("Please Check the Directory for Output File: "+System.getProperty("user.dir")+"\n File Name: Merge_Out_"+file);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(System.getProperty("user.dir")+"\\Merge_Out_"+file);
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


	private int[] divideAndConquer(int[] a,int s,int n){
		//Base case
		if(n==1){
			return a;
		}
		int mid = n/2;
		//Recursive Case
		a= divideAndConquer(a,s,mid);
		a = divideAndConquer(a,mid+s,mid);
		//Merge two arrays
		a = merge(a,n,s,mid+s);

		return a;
	}	
	//s1 - Start of first array s2 - start of second array
	private int[] merge(int[] a,int n,int s1,int s2){
		//Temporary array of n elements for merging
		int[] t = new int[n];
		int k=0;
		int i=s1,j=s2;
		while(compare(i,s2)&&compare(j,n+s1)){
			if(compare(a[j],a[i])){
				t[k]=a[j];
				j++;
				k++;
			}else{
				t[k] = a[i];
				i++;
				k++;
			}
		}
		while(compare(i,s2)){
			t[k] = a[i];
			i++;k++;
		}
		while(compare(j,(n+s1))){
			t[k]=a[j];
			j++;k++;
		}
		int b=0;
		//Move temporary array to main array
		for(int m=s1;m<(n+s1);m++){
			a[m]=t[b];
			b++;
		}

		return a;
	}
	private boolean compare(int x,int y){
		count++;
		if(x<y){
			return true;
		}
		return false;
	}

}
