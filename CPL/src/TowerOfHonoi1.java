import java.util.Scanner;

public class TowerOfHonoi1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter initial configuration  : ");
		String in = sc.nextLine();
		System.out.println("Please enter output configuration  : ");
		String ou = sc.nextLine();
		int n = in.length();
		tower(in,ou,n);
	}
	static void tower(String in,String out,int n){
			if(in.substring(0, 1).equals(out.substring(0, 1))){//If the largest tower are same in initial and final
				tower(in.substring(1,in.length()),out.substring(1,in.length()),n-1);
			}else{
				move(in,out);
			}
		
	}
	static void move(String in,String out){
		if(in.length()==1){
			if(in.equals(out)){
				return;
			}
			System.out.println("Move "+in+" to "+out);
			return;
		}
		String s1= String.valueOf(in.charAt(0));
		String s2 = String.valueOf(out.charAt(0));
		if(s1.equals(s2)){
			move(in.substring(1,in.length()),out.substring(1, out.length()));
			return;
		}
		String s=getAlternatePole(s1, s2);//other tiles should move to s except the tile to be moved
		String ss = "";
		for(int i=1;i<in.length();i++){
			ss+=s;
		}
		move(in.substring(1,in.length()),ss);
		//Move Largest Tile
		System.out.println("Move "+s1+" to "+s2);
		//Move n-1 Largest Tiles
		move(ss,out.substring(1, out.length()));
		
	}
	static String getAlternatePole(String s1,String s2){
		if((s1.equals("A")&&s2.equals("B"))||(s1.equals("B")&&s2.equals("A"))){
			return "C";
		}else if((s1.equals("C")&&s2.equals("B"))||(s1.equals("B")&&s2.equals("C"))){
			return "A";
		}else{
			return "B";
		}
	}

}
