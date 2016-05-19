import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetConfidenceSet {
	List<String> finConfidence = new ArrayList<String>();

	// Calculate all different permutations for a given set to calculate confidence
	public GetConfidenceSet(HashMap<String, Integer> supportMap) {
		for (Map.Entry<String, Integer> entry : supportMap.entrySet()) {
			calculateConfidence(entry.getKey());
		}
	}
	// Create all possible sets to calculate confidence
	protected void calculateConfidence(String s) {
		String[] arr = s.split(",");
		String[] bin = permute(arr.length);

		for (String b : bin) {
			String left = "", right = "";
			int i = 0;
			String[] sp = b.split("");
			for (String boo : sp) {
				if (boo.equals("0")) {
					left += arr[i].concat(",");
					i++;
				} else {
					right += arr[i].concat(",");
					i++;
				}
			}
			left = left.substring(0, left.length() - 1);
			right = right.substring(0, right.length() - 1);
			String fin = left.concat("->").concat(right);
			finConfidence.add(fin);
		}
	}

	// return confidence list
	public List<String> getConfidence() {
		return finConfidence;
	}

	// Find Permutations of each set
	String[] permute(int i) {
		int length = (int) Math.pow(2, i);
		length--;
		String temp = Integer.toBinaryString(length);
		length = temp.length();
		String[] arr = new String[(int) (Math.pow(2, i) - 2)];
		for (int j = 0; j < arr.length; j++) {

			arr[j] = Integer.toBinaryString(j + 1);
			int alen = length - arr[j].length();
			for (int k = 0; k < alen; k++) {
				arr[j] = "0".concat(arr[j]);
			}
		}
		return arr;
	}
}
