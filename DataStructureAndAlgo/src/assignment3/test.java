package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		String input = "ravi.txt";
		String pattern = ".txt$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(input);
		if(m.find()){
			System.out.println("success");
		}else{
			System.out.println("Failure");
		}
	}

}
