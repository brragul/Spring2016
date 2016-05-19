
public class Deepak {
	static int carry = 0;
	public static void main(String[] args) {
		//p=a*b+c+d
		int[] a = new int[]{1,1,1,1};
		int[] b = new int[]{1,1};
		int[] c = new int[]{1,1,1,1};
		int[] d = new int[]{1,1};
		int[][] ab = new int[2][4];
		int[] p = new int[6];
		
		for(int i =1;i>=0;i--){
			for(int j=3;j>=0;j--){
				ab[i][j]=a[j]*b[i];
			}
		}
		p[5]=ab[1][3];
		for(int i=4;i>1;i--){
			p[i]=lookUpTable(ab[0][i-1],ab[1][i-2],carry);
		}
		p[1]=lookUpTable(ab[0][0], 0, carry);
		if(carry==1){
			p[0]=1;
		}else{
			p[0]=0;
		}
		p=add(p,c);
		p=add(p,d);
		for(int i=0;i<6;i++)
		{
		System.out.print(p[i]+" ");
		}
	}
	static int[] add(int[] p,int[] ar){
		carry = 0;
		int l = ar.length;
		for(int i=5;i>=0;i--){
			if(l>0)
			{
				p[i]=lookUpTable(p[i],ar[--l],carry);
			}else{
				p[i]=lookUpTable(p[i],0,carry);
			}
		}
		return p;
	}
	
	static int lookUpTable(int a,int b,int c){
		if(a+b+c==3){
			carry = 1;
			return 1;
		}
		else if(a+b+c==2){
			carry =1;
			return 0;
		}else if(a+b+c==1){
			carry=0;
			return 1;
		}else{
			carry = 0;
			return 0;
		}
	}
}
