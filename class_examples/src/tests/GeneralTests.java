package tests;

import java.util.LinkedList;

public class GeneralTests {
	
	public GeneralTests() {
		
	}
	
	public void leftRotation(int[] inty, int rots) {
		LinkedList<Integer> listRep = new LinkedList<Integer> ();
		for (int w : inty) {
        	listRep.add(w);
        }
	
		int first;
		for (int i = 0; i < rots; i++) {
			first = listRep.poll();
			listRep.add(first);
		}
		System.out.println(listRep);
	}
	
	static LinkedList<Integer> oddNumbers(int l, int r) {
		LinkedList<Integer> oddNum = new LinkedList<Integer> ();
		for (int i = l; i <= r; i++) {
			if (i%2 != 0) {
				oddNum.add(i);
			}
		}
		return oddNum;
	}
	
	static String mergeStrings(String a, String b) {
		String longStr;
		String shortStr;
		if (a.length() >= b.length()) {
        	longStr = a;
        	shortStr = b;
        } else {
        	longStr = b;
        	shortStr = a;
        }
		
		String mergeStr = "";
		String ending = "";
		if (longStr.length() != shortStr.length()) {
			ending = longStr.substring(shortStr.length(), longStr.length());
		}
		for (int i = 0; i < longStr.length(); i++) {
			if (i == shortStr.length()) {
				break;
			}
			mergeStr = mergeStr + Character.toString(a.charAt(i));
			mergeStr = mergeStr + Character.toString(b.charAt(i));
		}
		mergeStr = mergeStr + ending;
        return mergeStr;
    }
	
	static String betterMergeStrings(String a, String b) {
		String mergeStr = "";
		for (int i = 0; i < b.length(); i++) {
			if (i == a.length() ) {
				break;
			}
			mergeStr = mergeStr + Character.toString(a.charAt(i));
			mergeStr = mergeStr + Character.toString(b.charAt(i));
		}
		if (b.length() > a.length()) {
			mergeStr = mergeStr + b.substring(a.length(), b.length());
		}
		if (a.length() > b.length()) {
			mergeStr = mergeStr + a.substring(b.length(), a.length());
		}
        return mergeStr;
    }
	
	static int maxDifference(int[] a) {
		if (a.length == 1) { return -1; }
		int maxDiff = a[1] - a[0];
		for (int i = 1; i < a.length; i++) {
			for (int j = 0; j < i; j++) {
				if (a[i] - a[j] > maxDiff) {
					maxDiff = a[i] - a[j];
				}
			}
		}
		if (maxDiff <= 0) {
			return -1;
		}
		return maxDiff;
    }
	
	static int betterMaxDifference(int[] a) {
		if (a.length == 1) { return -1; }
		int maxDiff = a[1] - a[0];
		int minNum = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i-1] < minNum) { 
				minNum = a[i-1]; 
			}
			if ( (a[i] - minNum) > maxDiff) {
				maxDiff = a[i] - minNum;
			}
		}
		if (maxDiff <= 0) {
			return -1;
		}
		return maxDiff;
    }
	
	private String d;
	void start() {
		d = new String();
		this.takeDemo(d);
	}
	
	void takeDemo (String demo) {
		demo = null;
		demo = new String();
	}
	
	public static void main (String[] args) {
//		GeneralTests test = new GeneralTests();
//		int[] testInput = {1, 2, 3, 4, 5};
//		test.leftRotation(testInput, 2);
//		System.out.println(GeneralTests.betterMergeStrings("a", "begegegeg"));
//		int[] testIntArr = {7, 2, 3, 10, 2, 4, 8, 1};
//		System.out.println(GeneralTests.betterMaxDifference(testIntArr));
//		System.out.println(9%8);
//		char ch = 0;
//		int count = 0;
//		while (++ch <= 255) {
//			count++;
//		}
//		System.out.println(count);
//		test.start();
//		System.out.println(count);
		
		String strTest = "good";
		String[] strArray = new String[5];
		if (strArray[1] == null || !strArray[1].equals(strTest)) {
			System.out.println("False");
		}
		byte[] strByte = strTest.getBytes();
		int hashCode = 0;
		for (int i = 0; i < strByte.length; i++) {
			hashCode += Byte.toUnsignedInt(strByte[i]) * (i);
		}
		System.out.println(hashCode);
	}
}
