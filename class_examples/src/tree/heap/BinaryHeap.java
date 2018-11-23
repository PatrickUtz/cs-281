/* Patrick Utz
 * Jeremy Goldberg
 * Matt Stein
 * 
 * Classwork 6 
 * 11/05/2018
 */

package tree.heap;
  
  import java.util.ArrayList;
  
  class BinaryHeap {
      
      // Integer = wrapper class for primitive
      // ints, since ArrayLists need to operate on
      // objects, not primitives
      ArrayList<Integer> heap;
      
      BinaryHeap () {
          heap = new ArrayList<Integer>();
      }
      
      BinaryHeap (BinaryHeap other) {
          heap = new ArrayList<Integer>(other.heap);
      }
  
      /*
       * Continues to bubble values up the tree until we
       * find a node that is greater than it
       */
      private void bubbleUp (int index) {
          // If we're at the root, then
          // we're done
          if (index == 0) {return;}
  
          // Get parent index and store it
          int parent = getParent(index);
  
          // If the parent's value is less than
          // the current one's...
          if (heap.get(parent) < heap.get(index)) {
              // ...then we swap them...
              Integer temp = heap.get(index);
              heap.set(index, heap.get(parent));
              heap.set(parent, temp);
  
              // ...and recurse on the new parent!
              bubbleUp(parent);
          }
  
      }
      
      public void insert (Integer toInsert) {
          // Add new element to next available
          // slot in the binary tree
          heap.add(toInsert);
  
          // Reheapify by bubbling newly inserted
          // value up to its proper spot!
          bubbleUp(heap.size() - 1);
      }
  
       // Traversal helpers
      public int getParent (int index) {
          return (index - 1) / 2;
      }
      
      public int getChild (int index, char child) {
          int result = (index * 2) + 1;
          if (child == 'R') {
              result++;
          }
          return result;
      }
      
      public void print () {
          for (int i = 0; i < heap.size(); i++) {
              System.out.println(heap.get(i));
          }
      }
      
      public ArrayList<Integer> getSortedElements () {
          BinaryHeap result = new BinaryHeap(this);
          int count = result.heap.size() - 1;
          while (count > 0) {
              result.swapAtIndexes(0, count);
              result.bubbleDown(0, count);
              count--;
          }
          return result.heap;
      }
      
      private void swapAtIndexes (int ind1, int ind2) {
          int temp = heap.get(ind1);
          heap.set(ind1, heap.get(ind2));
          heap.set(ind2, temp);
      }
      
      private void bubbleDown (int index, int maxIndex) {
          // Base case: at a leaf
          if (index >= heap.size() || index >= maxIndex) { return; }
          
          // Get child indexes
          int lInd = getChild(index, 'L'),
              rInd = getChild(index, 'R'),
              swappedIndex = 0;
          
          // Make sure the left child exists within the maxIndex
          if (lInd < heap.size() && lInd < maxIndex) {
              // If left exists, check to see if the right does
              if (rInd < heap.size() && rInd < maxIndex) {
                  // Then, swap with the max value of left or right child
                  swappedIndex = (heap.get(lInd) > heap.get(rInd)) ? lInd : rInd;
              } else {
                  // Otherwise, right does not exist, so swap with left
                  swappedIndex = lInd;
              }
              if (heap.get(index) < heap.get(swappedIndex)) {
                  swapAtIndexes(index, swappedIndex);
              } else {
                  return;
              }
          }
          // Note: if left does not exist, then right certainly won't
          // (complete tree property of heap)
          if (swappedIndex != 0) {
              bubbleDown(swappedIndex, maxIndex);
          }
      }
      
  }