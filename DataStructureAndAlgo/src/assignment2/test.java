package assignment2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class test {

	public static void main(String[] args) throws IOException {
		PrintWriter writer = null;
		int[] a = new int[24];
		try {
			writer = new PrintWriter(new File(System.getProperty("user.dir")+"\\n24.txt").getAbsoluteFile());
			System.out.println(System.getProperty("user.dir"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//BufferedWriter bw = new BufferedWriter(writer);
		for(int i = 0;i<24;i++){
			int k = ((int) (Math.random()*(20000 - 1))) + 1;
			a[i]=k;
			//System.out.println(a[i]);
			writer.println(Integer.toString(a[i]));
		}
		writer.close();
	}

}
