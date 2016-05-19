
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class AprioriAlgorithm {
	static HashMap<Integer, String> map = new HashMap<Integer, String>(); // Input
	static HashMap<String, Integer> frequentItems = new HashMap<String, Integer>(); //Set with Frequent items
	static HashMap<String, Integer> cmap = new HashMap<String, Integer>(); // Support of all sets
	static List<String> nonFrequentList = new ArrayList<String>(); // Non Frequent Set
	static List<String> uniqueElements = new ArrayList<String>();// Individual Elements
	static int count = 2;
	static boolean foundNFL = false;
	static GetConfidenceSet cf;
	public static float support = 0;
	public static float confidence = 0;

	@SuppressWarnings({ "rawtypes" })
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		/*System.out.println(
				"List of Databases:\n1.AMAZON\n2.IKEA\n3.WALMART\n4.FRUIT STORE\n5.APPLE STORE\nEnter Database Number (1-5)");
		int inp = sc.nextInt();
		String str = "";
		switch (inp) {
		case 1:
			str = "AMAZON";
			break;
		case 2:
			str = "IKEA";
			break;
		case 3:
			str = "WALMART";
			break;
		case 4:
			str = "FRUIT_STORE";
			break;
		case 5:
			str = "APPLE_STORE";
			break;
		default:
			System.out.println("Wrong Input AMAZON DB Loaded");
			str = "AMAZON";
			break;
		}
		System.out.println("\n" + str + " DB Selected\n");*/
		System.out.println("Enter Your Support(1-100) : ");
		support = sc.nextInt();
		System.out.print("Enter Your Confidence(1-100) : ");
		confidence = sc.nextInt();
		//DBConnect.connectToDB(str);
		loadMap();
		List<String> ind = new ArrayList<String>();// List of unique elements
		Iterator<?> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			String s = (String) pair.getValue();
			String[] li = s.split(",");
			for (String x : li) {
				if (!(ind.contains(x))) {
					ind.add(x);
				}
			}
		}
		String temp = "";
		System.out.print("\nIndividual Elements : ");
		for (String u : ind) {
			temp += u + ", ";
		}
		temp = temp.substring(0, temp.length() - 1);
		System.out.println(temp + "\n");
		uniqueElements = ind;
		calcuateSupport(map, ind);
		System.out.println("Rule X->Y [Support of X, Support of X->Y, Confidence of X->Y] ");
		List<String> tmpLst = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : frequentItems.entrySet()) {
			String[] tmparr = entry.getKey().split(",");
			if (tmparr.length < 2) {
				tmpLst.add(entry.getKey());
			}
		}
		// To remove sets with individual elements
		for (String x : tmpLst) {
			frequentItems.remove(x);
		}
		cf = new GetConfidenceSet(frequentItems);
		frequentItems = orderHash(frequentItems);
		cmap = orderHash(cmap);
		calculateConfidence();

	}
	
	static void loadMap(){
		map.put(1, "Milk,Bread,Salad,Fruit,Soup,Noodle");
		map.put(2, "Beef,Bread,Salad,Fruit,Soup,Noodle");
		map.put(3, "Milk,Rice,Fruit,Soup,Pork,Lamb");
		map.put(4, "Milk,Chicken,Fish,Fruit,Noodle");
		map.put(5, "Fish,Bread,Fruit,Shrimp,Soup");
		/*map.put(6, "dre,thr");
		map.put(7, "beh,chi,psy");*/
	}
	
	// Order elements in Hash Map to prevent NullPointException
	static HashMap<String, Integer> orderHash(HashMap<String, Integer> map) {
		HashMap<String, Integer> tempmap = new HashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			String[] s = entry.getKey().split(",");
			int c = entry.getValue();
			String s1 = s[0];
			Arrays.sort(s);
			for (int i = 1; i < s.length; i++) {
				s1 = s1.concat(",").concat(s[i]);
			}
			tempmap.put(s1, c);

		}

		return tempmap;
	}

	// Calculate Support for different combinations recursively
	protected static void calcuateSupport(HashMap<Integer, String> map, List<String> li) {
		List<String> tmpLst = new ArrayList<String>();
		for (String s : li) {
			String[] sarr = s.split(",");
			Arrays.sort(sarr);
			String tmpStr = "";
			for (String a : sarr) {
				tmpStr = tmpStr.concat(a).concat(",");
			}
			tmpStr = tmpStr.substring(0, tmpStr.length() - 1);
			tmpLst.add(tmpStr);
		}
		li = tmpLst;
		if (li.size() <= 1) {
			return;
		}
		// Initialize support list count to 0
		for (String x : li) {
			cmap.put(x, 0);
		}
		for (Entry<Integer, String> pair : map.entrySet()) {
			String map_String = (String) pair.getValue();
			String[] arr = map_String.split(",");
			for (String x : li) {
				String[] x_arr = x.split(",");
				Set<String> s1 = new HashSet<String>(Arrays.asList(arr));
				Set<String> s2 = new HashSet<String>(Arrays.asList(x_arr));
				s1.retainAll(s2);
				if (s1.equals(s2)) {
					int temp = cmap.get(x);
					temp++;
					cmap.put(x, temp);
				}
			}

		}
		for (Map.Entry<String, Integer> entry : cmap.entrySet()) {
			float s = ((float) entry.getValue() / (float) map.size()) * 100;
			if (s < support) {
				nonFrequentList.add(entry.getKey());
			}
		}
		// Remove Non Frequent Elements
		for (String s : nonFrequentList) {
			li.remove(s);
		}
		for (String temp : li) {
			if (cmap.get(temp) != null) {
				frequentItems.put(temp, cmap.get(temp));
			}
		}

		li = makeCombinationsForSupport(li);
		count++;

		calcuateSupport(map, li);// Recursive call

	}

	// Make Combinations for elements to calculate support
	static List<String> makeCombinationsForSupport(List<String> li) {
		Collections.sort(li);
		if (li.size() <= 1) {
			return li;
		}
		Set<String> combinedList = new HashSet<String>();
		for (int i = 0; i < li.size(); i++) {
			for (int j = i + 1; j < li.size(); j++) {
				List<String> l1 = Arrays.asList(li.get(i).split(","));
				List<String> l2 = Arrays.asList(li.get(j).split(","));
				Set<String> set = new HashSet<String>(l1);
				set.addAll(l2);
				List<String> sortList = new ArrayList<String>(set);
				if (!(sortList.size() > count)) {
					// If element set is non frequent do not add set to list
					for (String s1 : nonFrequentList) {
						Set<String> set1 = new HashSet<String>(Arrays.asList(s1.split(",")));
						Set<String> set2 = new HashSet<String>(sortList);
						set2.retainAll(set1);
						if (set1.equals(set2)) {
							foundNFL = true;
							break;
						}

					}
					if (!foundNFL) {
						StringBuffer sb = new StringBuffer();
						for (String s : sortList) {
							sb.append(s).append(",");
						}
						sb.setLength(sb.length() - 1);
						combinedList.add(sb.toString());

					} else {
						foundNFL = false;
					}
				}

			}
		}
		List<String> combinedList1 = new ArrayList<>(combinedList);
		return combinedList1;
	}

	// Calculate confidence using support values
	static void calculateConfidence() {
		for (String s : cf.getConfidence()) {
			String[] arr = s.split("->");
			String[] leftarr = arr[0].split(",");
			String[] rightarr = arr[1].split(",");

			List<String> temp = new ArrayList<String>();

			for (String ss : leftarr) {
				temp.add(ss);
			}
			List<String> leftList = new ArrayList<String>(temp);
			for (String ss : rightarr) {
				temp.add(ss);
			}
			Collections.sort(temp);
			Collections.sort(leftList);
			String up = "";
			for (String ss : temp) {
				up = up.concat(ss).concat(",");
			}
			up = up.substring(0, up.length() - 1);
			String down = "";
			for (String ss : leftList) {
				down = down.concat(ss).concat(",");
			}
			down = down.substring(0, down.length() - 1);
			try {
				float supX = ((float) AprioriAlgorithm.cmap.get(down) / (float) AprioriAlgorithm.map.size()) * 100;
				float conf = ((float) AprioriAlgorithm.cmap.get(up) / (float) AprioriAlgorithm.cmap.get(down)) * 100;
				float sup = ((float) AprioriAlgorithm.cmap.get(up) / (float) AprioriAlgorithm.map.size()) * 100;
				if (conf >= AprioriAlgorithm.confidence) {
					System.out.println(s + " [ " + supX + " , " + sup + " , " + conf + " ]");
				}
			} catch (NullPointerException n) {
				System.out.println("Catched on " + s);
			}

		}
	}

}
