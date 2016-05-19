import java.math.BigDecimal;
import java.util.Scanner;

public class Testing {
	public static void main(String[] args) throws ClassNotFoundException {
		double d = (0.4-0.3)*(0.4-0.3);
		BigDecimal db = new BigDecimal(d).setScale(6, BigDecimal.ROUND_HALF_UP);
		System.out.println(db);
	}
}
