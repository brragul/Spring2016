
public class DeepakNew {
	static int[] out;
	static int carry = 0;
	public static void main(String[] args) {
		int n = 8;
		int[] fin = new int[n+n];
		int cc = (n+n)-1;
		int tot = (n/4)*6;
		
		//
		int[] a = new int[]{1,1,1,1,1,1,1,1};
		int[] b = new int[]{1,1,1,1,1,1,1,1};
		int[] c = new int[]{0,0,0,0};
		int[] d = new int[]{0,0};
		out = new int[tot];
		
		for(int i=(n/2);i>0;i--){
			for(int j=(n/4);j>0;j--){
				int[] tempa = new int[4];
				int[] tempb = new int[2];
				for(int m=0;m<4;m++){
					tempa[m]=a[(4*(j-1))+m];
				}
				for(int m=0;m<2;m++){
					tempb[m]=b[(2*(i-1))+m];
				}
				if(j==(n/4)&&i==(n/2)){
						int[] arr;
						arr = nXn(tempa,tempb,c,d);
						for(int m=0;m<6;m++){
							out[6*(j-1)+m]=arr[m];
							
						}
						fin[cc]=arr[5];cc--;
						fin[cc]=arr[4];cc--;
					
				} if(j!=(n/4)&&i==(n/2)&&i!=1){
					int[] tempd = new int[2];
					for(int m=0;m<2;m++){
						tempd[m]=out[(6*(j))+m];
					}
					int[] arr;
					arr = nXn(tempa,tempb,c,tempd);
					for(int m=0;m<6;m++){
						out[6*(j-1)+m]=arr[m];
						
					}
					
					
				} if(j==(n/4)&&i!=(n/2)){
					int[] arr;
					d[0]=0;d[1]=0;
					c[0]=out[6*(j-2)+4];
					c[1]=out[6*(j-2)+5];
					c[2]=out[6*(j-1)+2];
					c[3]=out[6*(j-1)+3];
					arr = nXn(tempa,tempb,c,d);
					for(int m=0;m<6;m++){
						out[6*(j-1)+m]=arr[m];
						
					}
					fin[cc]=arr[5];cc--;
					fin[cc]=arr[4];cc--;
					if(i==1){
						fin[cc]=arr[3];cc--;
						fin[cc]=arr[2];cc--;
					}
				}
				 if(j!=(n/4)&&i!=(n/2)&&j==1){
					for(int m=0;m<2;m++){
						d[m]=out[(6*(j))+m];
					}
					for(int m=0;m<4;m++){
						c[m]=out[m];
					}
					int[] arr;
					arr = nXn(tempa,tempb,c,d);
					for(int m=0;m<6;m++){
						out[6*(j-1)+m]=arr[m];
						
					}
					if(i==1){
						for(int m=0;m<6;m++){
							fin[m]=out[m];
						}
					}
					
				}
				if(j!=(n/4)&&i!=(n/2)&&j!=1)
				{
					int[] arr;
					c[0]=out[6*(j-2)+4];
					c[1]=out[6*(j-2)+5];
					c[2]=out[6*(j-1)+2];
					c[3]=out[6*(j-1)+3];
					d[0]=out[6*(j)];
					d[1]=out[6*(j)+1];
					arr = nXn(tempa,tempb,c,d);
					for(int m=0;m<6;m++){
						out[6*(j-1)+m]=arr[m];
						
					}
					if(i==1){
						fin[cc]=arr[5];cc--;
						fin[cc]=arr[4];cc--;
						fin[cc]=arr[3];cc--;
						fin[cc]=arr[2];cc--;
					}
				}
			}
				
				
		}
		
		for(int i=0;i<fin.length;i++){
			System.out.print(fin[i]+"");
		}
		
	}
	
	
	static int[] nXn(int[] a,int[] b,int[] c,int[] d){
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
		//System.out.print(p[i]+" ");
		}
	//	System.out.println();
		//int[] arr = new int[]{};
		return p;
	
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
