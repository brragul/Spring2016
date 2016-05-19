package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSorting {
	static int count = 0;
	static HashMap<String,List<Integer>> map = new HashMap<String,List<Integer>>();
	static int[] P;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		initializeMap();
		Scanner in = new Scanner(System.in);
		System.out.println("Please specify the input file (default = f.txt ):");
		String filename = in.nextLine();
		File file = new File(System.getProperty("user.dir") + "//" + filename);
		Scanner sc = null;
		//Check if the file exists in the folder
		if(file.exists()){
		sc = new Scanner(file);
		}else{
			System.out.println("File Not Found Default file: f.txt loaded");
			sc = new Scanner(new File(System.getProperty("user.dir") + "//f.txt"));
		}
		List<String> list = new ArrayList<String>();
		while(sc.hasNext()){
			list.add(sc.nextLine());
		}
		String[] sb = new String[list.size()];
		int j = 0;
		for(String s: list){
			sb[j]=s;
			j++;
		}
		for(int i =0;i<sb.length;i++){
			if(sb[i].length()>21){
				sb[i]=sb[i].substring(0, 21);
			}else{
				int a = 21 - sb[i].length();
				for(int m=0;m<a;m++){
					sb[i]=sb[i]+" ";
				}
			}
		}
		//Convert all names to uppercase
		for(int i=0;i<sb.length;i++){
			sb[i] = sb[i].toUpperCase();
		}
		//Initialize and setup 2-D array
		String[][] arr = new String[sb.length][21];
		for(int q=0;q<sb.length;q++){
			for(int w=0;w<21;w++){
				arr[q][w]=sb[q].substring(w, w+1);
			}
		}
		//Initialize Pointer
		P = new int[sb.length];
		for(int q=0;q<P.length;q++){
			P[q]=q;
		}
		//loop through each character and put it in Bucket
		for(int q=20;greatEqual(q, 0);q--){
			for(int w=0;compare(w, sb.length);w++){
				putInMap(arr[P[w]][q], P[w]);
			}
			managePointer();
			map.clear();//Clear map for next iteration
			initializeMap();
		}
		
		System.out.println("Please specify the output file (default = g.txt):");
		String output = in.nextLine();
		
		//Check the Validity of Output file name using Regular Expressions
		String pattern = ".txt$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(output);
		if(!m.find()){
			System.out.println("Invalid Input File Name. Default file : g.txt name choosed");
			output = "g.txt";
		}
		System.out.println("Please Check the Directory for Output File: "+System.getProperty("user.dir")+"\nFile Name: "+output);
		PrintWriter writer = null;
		try {
			//Output File
			writer = new PrintWriter(System.getProperty("user.dir")+"\\"+output);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
		for(int t: P){
			writer.println(sb[t]);
		}		
		writer.close();
		System.out.println("Total Names : "+sb.length);
		System.out.println("Loop Count : " + count);
	}
	
	//Add values to Hash map
	static void putInMap(String s,int k){
		List<Integer> li= map.get(s);
		li.add(k);
		map.put(s, li);
	}
	
	//Reorder the values in pointer
	static void managePointer(){
		int k = 0;		
		List<Integer> sp = map.get(" ");
		if(!sp.isEmpty()){
			for(Integer temp :sp){
				P[k] = temp;
				k++;
			}	
		}
		for(int i=65;i<=90;i++){
			String s =Character.toString((char)i);
			List<Integer> ls = map.get(s);
			if(!ls.isEmpty()){
				for(Integer temp :ls){
					P[k] = temp;
					k++;
				}
			}
		}
	
	}
	
	//Initialize Hash Map from A to Z and " "
	static void initializeMap(){
		for(int i=65;i<=90;i++){
			map.put(Character.toString((char)i), new ArrayList<Integer>());
		}
		//32 - Ascii for Space 
		map.put(Character.toString((char)32), new ArrayList<Integer>());
	}
	
	static boolean compare(int x,int y){
		count++;
		if(x<y){
			return true;
		}
		return false;
	}
	static boolean greatEqual(int x,int y){
		count++;
		if(x>=y){
			return true;
		}
		return false;
	}
	static boolean lessEqual(int x,int y){
		count++;
		if(x<=y){
			return true;
		}
		return false;
	}

}
