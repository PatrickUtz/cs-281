package tests;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays;

public class GoogleCodeChallenge {
	
	// Private fields
	
	public GoogleCodeChallenge () {
		
	}
	
	public String solution (String S, int K) {
		String str = "";
		S = S.toUpperCase();
		S = S.replaceAll("-", "");
		for (int i = (S.length() - K); i >= 0; i-=K) {
			str = "-" + S.substring(i, i + K) + str;
		}
		if (S.length()%K == 0) {
			return str.substring(1,str.length());
		} else {
			return S.substring(0,K-1) + str;
		}
    }
	
//	static long countTriplets(List<Long> arr, long r) {
//        HashMap<Long,Long> hashM = new ArrayList<Long,Long> ();
//        for (int i = 0; i < arr.size(); i++) {
//            if (hashM.containsKey(arr.get(i))) {
//                hashM.put(arr.get(i),hashM.get(arr.get(i))++);
//            }
//            hashM.put(arr.get(i),1);
//        }
//        for (int i = 0; i < arr.size(); i++) {
//            if (hashM.containsKey(arr.get(i))) {
//                hehe
//            }
//        }
//    }
	
	public int arraySol (int[][] arr) {
		int col = arr[0].length;
		int row = arr.length;
		int totalHG = ( (arr[0].length - 2) * (arr.length - 2) );
		int maxSum = 0;
		int tempSum = 0;
		int k = 0;
		int j = 0;
		for (int i = 0; i < totalHG; i++) {
			if ((i)%(col-2) == 0 && i != 0) {
				k++;
				j = 0;
			}
			tempSum =  arr[k][j] + arr[k][j+1] + arr[k][j+2] + arr[k+1][j+1] +
					arr[k+2][j] + arr[k+2][j+1] + arr[k+2][j+2];
			if (tempSum > maxSum) {
				maxSum = tempSum;
			}
			j++;
		}
		return maxSum;
	}
	
	public Character recurringChar (String str) {
      Hashtable<Character,Integer> hashT = new Hashtable<Character,Integer> ();
      for (int i = 0; i < str.length(); i++) {
          if (hashT.containsKey(str.charAt(i))) {
              return (str.charAt(i));
          }
          hashT.put(str.charAt(i),1);
      }
      throw null;
	}
	
	public String isValidString (String s) {
		Hashtable<Character,Integer> hashT = new Hashtable<Character,Integer> ();
        for (int i = 0; i < s.length(); i++) {
            if (hashT.containsKey(s.charAt(i))) {
                hashT.put(s.charAt(i), hashT.get(s.charAt(i)) + 1);
            } else {
            	hashT.put(s.charAt(i), 1);
            }
        }
        int min = hashT.get(s.charAt(0));
        int removeStatus = 0;
        for (int i = 0; i < s.length(); i++) {
            if (removeStatus > 1) {
            	return "NO";
            }
        	if (hashT.get(s.charAt(i)) > min) {
            	removeStatus++;
            	hashT.put(s.charAt(i), hashT.get(s.charAt(i)) - 1);
            }
        	if (hashT.get(s.charAt(i)) < min) {
        		if (s.charAt(i) - min > 1) {
                	return "NO";
                }
        		min--;
        		removeStatus++;
            }
        }
        return "YES";
    }
	
	static void countSwaps(int[] a) {
		int numSwaps = 0;
        for (int i = 0; i < a.length; i++) {
        	for (int j = 0; j < a.length-1; j++) {
        		if (a[j] > a[j+1]) {
        			int temp = a[j];
        			a[j] = a[j+1];
        			a[j+1] = temp;
        			numSwaps++;
        		}
        	}
        }
        System.out.println("Array is sorted in " + numSwaps + " swaps");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[a.length-1]);
    }
	
//	static Node lca(Node root,int v1,int v2)
//	{
//	    //Decide if you have to call recursively
//	    //Samller than both
//	    if(root.data < v1 && root.data < v2){
//	        return lca(root.right,v1,v2);
//	    }
//	    //Bigger than both
//	    if(root.data > v1 && root.data > v2){
//	        return lca(root.left,v1,v2);
//	    }
//
//	    //Else solution already found
//	    return root;
//	}
	
	static long substrCount(int n, String s) {
        for (int i = 0; i < s.length()-2; i++) {
        	if (s.charAt(i) == (s.charAt(i+2))) {
        		n++;
        	}
        }
        return n;
    }
	
	static void minimumBribes(int[] q) {
		int bribes = 0;
		for (int i = 0; i < q.length-1; i++) {
			if (q[i] > q[i+1]) {
				int base = q[i];
				int check = q[i+1];
				int index = i+1;
				while (check < base) {
					bribes++;
					index++;
					if (index >= q.length) {
						break;
					}
					check = q[index];
				}
			}
		}
		if (bribes > 2) {
			System.out.println("Too chaotic");
		} else {
			System.out.println(bribes);
		}
    }
	
	public int[] solutionA(int D, int[] A) {
		// Store length to be used throughout
		int length = A.length;
		
		// Create Node array with Nodes stored in proper order
		int[] nodeArray = new int[length];
		Hashtable<Integer,Integer> nodeHash = new Hashtable<Integer,Integer> ();
		for (int i = 0; i < length; i++) {
			nodeHash.put(A[i], i);
		}
		nodeArray[0] = nodeHash.get(-1);
		for (int i = 1; i < length; i++) {
			nodeArray[i] = nodeHash.get(nodeArray[i-1]);
		}
		
		// Create array with ancestors D away from respective nodes  
		int[] ancestorArr = new int[length];
		Hashtable<Integer,Integer> distance = new Hashtable<Integer,Integer> ();
		for (int i = 0; i < length; i++) {
			if (i < D) {
				distance.put(nodeArray[i], -1);
			} else {
				distance.put(nodeArray[i], nodeArray[i-D]);
			}
		}
		
		// Create final sorted array of ancestors D away from respective nodes
		Arrays.sort(nodeArray);
		for (int i = 0; i < length; i++) {
			ancestorArr[i] = distance.get(nodeArray[i]);
		}
		return ancestorArr;
    }
	
	public static void main (String[] args) {
		GoogleCodeChallenge test = new GoogleCodeChallenge();
		System.out.println(test.solution("2-4A0r7-4k", 3));
		
		int[][] arr = {{1,1,1,0,0,0}, {0,1,0,0,0,0}, {1,1,1,0,0,0}, {0,0,2,4,4,0},
				{0,0,0,2,0,0}, {0,0,1,2,4,0}};
		System.out.println(test.arraySol(arr));
		System.out.println(test.recurringChar("ABCDB"));
		System.out.println(test.isValidString("aabbc"));
		int[] a = {1,2,3,4,5};
		GoogleCodeChallenge.countSwaps(a);
		System.out.println(GoogleCodeChallenge.substrCount(7,"abcbaba"));
		int[] in = {2,1,5,3,4};
		GoogleCodeChallenge.minimumBribes(in);
		int[] tester = {-1,0,4,2,1};
		System.out.println(test.solutionA(3,tester));
	}
}