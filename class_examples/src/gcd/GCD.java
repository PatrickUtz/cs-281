package gcd;

public class GCD {
	
	/*
	 * Returns gcd of x and y
	 * Ex: gcd(10, 5) => 5
	 */
	public static int gcd(int x, int y) {
		// Consider x to be larger of x and y
		if (y < x) {
			int t = x; 
			x = y; 
			y = t;
		}
		
		// Handle simple cases:
		if (x % y == 0) {
			return y;
		}
		
		// Dumb case: try all the numbers to see which is 
		// the greatest denominator
		int currentGreatest = 1;
		for (int i = 0; i <= y; i++) {
			// Case: found a divisor
			if (x % i == 0 && y % i == 0) {
				currentGreatest = i;
			}
		}
		return currentGreatest;
	}
	
	public static void main (String[] args) {
		System.out.println(gcd(10, 5));
		System.out.println(gcd(21, 49));
	}

}
