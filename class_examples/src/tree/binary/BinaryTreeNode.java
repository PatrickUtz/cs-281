/* Patrick Utz
 * Jeremy Goldberg
 * Matt Stein
 * 
 * Classwork 6 
 * 11/05/2018
 */

package tree.binary;
  
  public class BinaryTreeNode {
      
      private String data;
      private BinaryTreeNode left, right;
      
      BinaryTreeNode (String s) {
          data = s;
          left = null;
          right = null;
      }
      
      public void add (String s, String child) {
          if (child.equals("L")) {
              left = new BinaryTreeNode(s);
          } else if (child.equals("R")) {
              right = new BinaryTreeNode(s);
          } else {
              throw new IllegalArgumentException();
          }
      }
      
      public BinaryTreeNode getChild (String child) {
          return (child.equals("L")) ? left : right;
      }
      
      public String getString () {
          return data;
      }
      
      public static void preorderPrint (BinaryTreeNode n) {
          if (n == null) {return;}
          System.out.println(n.data);
          preorderPrint(n.getChild("L"));
          preorderPrint(n.getChild("R"));
      }
      
      public void doubleTree () {
          doubleTree(this);
      }
      
      private void doubleTree (BinaryTreeNode n) {
          // Base case: node is null, return
          if (n == null) { return; }
          
          // Recursive cases: use postorder traversal
          // to start doubling at leaves
          doubleTree(n.left);
          doubleTree(n.right);
          
          // Finally, "visit" the current node by doubling
          // to its left child, and preserving its structure
          BinaryTreeNode oldLeft = n.left;
          n.left = new BinaryTreeNode(n.data);
          n.left.left = oldLeft;
      }
      
      public static boolean sameTree (BinaryTreeNode n1, BinaryTreeNode n2) {
          // Base case: either null, which will only return true if they both are
          if (n1 == null || n2 == null) {
              return n1 == n2;
          }
          
          // Return true only if the data agrees and both
          // subtrees are equivalent as well (preorder traversal)
          return n1.data.equals(n2.data) &&
                 sameTree(n1.left, n2.left) &&
                 sameTree(n1.right, n2.right);
      }
      
  }
