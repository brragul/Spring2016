import java.math.BigDecimal;
import java.util.Arrays;

public class TimeSeries {
	static float[][] d;
	static float[][] gam;
	public static void main(String[] args) {
		float[] q = new float[]{(float) 0.2, (float) 0.3, (float) 0.2, (float) 0.4, (float) 0.1, (float) 0.5};
		float[] c = new float[]{(float) 0.3, (float) 0.4, (float) 0.3, (float) 0.1, (float) 0.2};
		d = new float[q.length][c.length];
		gam = new float[q.length][c.length];
		
		for(int i=0;i<q.length;i++){
			for(int j=0;j<c.length;j++){
				BigDecimal bd =new BigDecimal((q[i]-c[j])*(q[i]-c[j])).setScale(6, BigDecimal.ROUND_HALF_UP);
				d[i][j]=bd.floatValue();
				
			}
		}
		for(int i=0;i<d.length;i++){
			for(int j=0;j<d[0].length;j++){
				System.out.print(d[i][j]);
				//System.out.print(new BigDecimal(d[i][j]).setScale(6, BigDecimal.ROUND_HALF_UP));
				//System.out.print((Float.toString(d[i][j]).length()>6)?((d[i][j])+"").substring(0, 6):d[i][j]);
				System.out.print(" 	");
			}
			System.out.println();
		}
		for(int i=0;i<q.length;i++){
			for(int j=0;j<c.length;j++){
				BigDecimal bd =new BigDecimal(d[i][j]+calcMin(i,j)).setScale(6, BigDecimal.ROUND_HALF_UP);
				gam[i][j]=bd.floatValue();
				
			}
		}
		System.out.println("gamma");
		for(int i=0;i<d.length;i++){
			for(int j=0;j<d[0].length;j++){
				System.out.print(gam[i][j]);
				//System.out.print(new BigDecimal(gam[i][j]).setScale(6, BigDecimal.ROUND_HALF_UP));
				//System.out.print((Float.toString(d[i][j]).length()>6)?((d[i][j])+"").substring(0, 6):d[i][j]);
				System.out.print(" 	");
			}
			System.out.println();
		}
	}
	static float calcMin(int i,int j){
		if(j==0){
			if(i==0){
				return 0.0f;
			}else{
				return gam[i-1][j];
			}
		}else if(i==0){
			return gam[i][j-1];
		}else{
			float a=gam[i-1][j],b=gam[i][j-1],c=gam[i-1][j-1];
			float smallest;
			if(a<b && a<c){
			    smallest = a;
			}else if(b<c && b<a){
			    smallest = b;
			}else{
			    smallest = c;
			}
			return smallest;
		}
	}

}
