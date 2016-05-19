
public class EucDistance {

	public static void main(String[] args) {
		double[] x = new double[2];
		double[] y = new double[2];
		 x[0]=8;
		 y[0]=10;
		 x[1]=8;
		 y[1]=13;
		 double euc = Math.sqrt(Math.abs(Math.pow(x[0]-x[1],2))+Math.abs(Math.pow(y[0]-y[1],2)));
		 System.out.println(euc);
	}

}
