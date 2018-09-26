package sandbox;

import java.util.ArrayList;

public class JCFEx {
	
	public static void main (String[] args) {
		ArrayList<String> arr = new ArrayList<String>();
		  arr.add("a"); // Append
		  arr.add("b");
		  arr.add("c");
		  arr.remove(1); // Removes "b" at index 1
		  System.out.println(arr.get(1)); // What gets printed?
	}
}
