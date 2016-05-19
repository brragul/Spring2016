import java.util.Arrays;

public class DecisionTree {

	public static void main(String[] args) {
		double tp =9;
		double tn = 5;
		double tpn = tp+tn;
		double tI = -((tp/tpn)*(Math.log(tp/tpn)/Math.log(2)))-((tn/tpn)*(Math.log(tn/tpn)/Math.log(2)));
		double[] p = new double[]{3,6};
		double[] n = new double[]{3,2};
		double[] pn = new double[p.length];
		double[] I = new double[p.length];
		for(int i=0;i<p.length;i++){
			pn[i]=p[i]+n[i];
		}
		for(int i=0;i<p.length;i++){
			I[i] = -((p[i]/pn[i])*(Math.log(p[i]/pn[i])/Math.log(2)))-((n[i]/pn[i])*(Math.log(n[i]/pn[i])/Math.log(2)));
			if(Double.isNaN(I[i])){
				I[i]=0;
			}
		}
		double en = 0.0;
		for(int i=0;i<p.length;i++){
			en+=((p[i]+n[i])/(tp+tn))*I[i];
		}
		double gain = tI-en;
		System.out.println("I(p,n): "+tI);
		System.out.println("I Array: "+Arrays.toString(I));
		System.out.println("Entropy: "+en);
		System.out.println("Gain: "+gain);
		
	}

}
