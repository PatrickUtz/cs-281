package linked_forneymonegerie;

import java.util.NoSuchElementException;

public class LinkedForneymonegerie implements LinkedForneymonegerieInterface {

    // Fields
    // -----------------------------------------------------------
    private ForneymonType head;
    private int size, typeSize, modCount;
    
    
    // Constructor
    // -----------------------------------------------------------
    LinkedForneymonegerie () {
        head = null;
        size = 0;
        typeSize = 0;
        modCount = 0;
    }
    
    
    // Methods
    // -----------------------------------------------------------
    public boolean empty () {
    	return size == 0;
    }
    
    public int size () {
        return size;
    }
    
    public int typeSize () {
    	return typeSize;
    }
    
    public boolean collect (String toAdd) {
        modCount++;
                
        if (empty()) {
        	head = new ForneymonType(toAdd, 1);
    		head.prev = null;
    		head.next = null;
            size++;
    		typeSize++;
    		return true;
        }
        
        size++;
        ForneymonType typeToAdd = getType(toAdd);
        if (checkNotNull(typeToAdd)) {
        	typeToAdd.count++;
        	return false;
        } else {
        	ForneymonType oldEnd = getEnd();
        	ForneymonType newEnd = new ForneymonType(toAdd, 1);
        	oldEnd.next = newEnd;
        	newEnd.prev = oldEnd;
        	typeSize++;
        	return true;
        }
    }
    
    public boolean release (String toRemove) {
    	modCount++;
    	ForneymonType typeToRelease = getType(toRemove);
    	if (checkNotNull(typeToRelease)) {
    		if (typeToRelease.count == 1) {
    			releaseType(toRemove);
    		} else {
    			typeToRelease.count--;
    			size--;
    		}
			return true;
    	}
    	return false;
    }
    
    public void releaseType (String toNuke) {
    	modCount++;
    	ForneymonType typeToNuke = getType(toNuke);
    	if (checkNotNull(typeToNuke)) {
    		typeSize--;
    		size = size - typeToNuke.count;
    		remove(typeToNuke);
    	}
    	return;
    }
    
    public int countType (String toCount) {
    	ForneymonType typeToCount = getType(toCount);
    	if (checkNotNull(typeToCount)) {
    		return typeToCount.count;
    	}
    	return 0;
    }
    
    public boolean contains (String toCheck) {
        if (checkNotNull(getType(toCheck))) {
        	return true;
        }
        return false;
    }
    
    public String rarestType () {
    	ForneymonType current = head;
    	String rarest = null;
        int rarestCount = current.count;
        while (checkNotNull(current)) {
        	if (current.count <= rarestCount) {
        		rarest = current.type;
        		rarestCount = current.count;
        	}
        	current = current.next;
        }
        return rarest;
    }
    
    public LinkedForneymonegerie clone () {
    	LinkedForneymonegerie clone = new LinkedForneymonegerie();
    	ForneymonType current = head;
    	ForneymonType cloneCurrent = null;
    	ForneymonType clonePrev = null;
    	while (checkNotNull(current)) {
    		if (clone.head == null) {
    			clone.head = new ForneymonType(head.type, head.count);
    			cloneCurrent = clone.head;
    		} else {
    			cloneCurrent.next = new ForneymonType(current.type, current.count);
        		cloneCurrent.next.prev = cloneCurrent; 
        		cloneCurrent = cloneCurrent.next;
    		}
    		current = current.next;
    	}
    	clone.modCount = modCount;
    	clone.size = size;
    	clone.typeSize = typeSize;
    	return clone;
    }
    
    public void trade (LinkedForneymonegerie other) {
    	ForneymonType tempHead = head;
    	int tempSize = size;
    	int tempTypeSize = typeSize;
    	int tempModCount = modCount;
    	
    	head = other.head;
    	size = other.size;
    	typeSize = other.typeSize;
    	modCount = other.modCount;
    	
    	other.head = tempHead;
    	other.size = tempSize;
    	other.typeSize = tempTypeSize;
    	other.modCount = tempModCount;
    	
    	modCount++;
    	other.modCount++;
    }
    
    @Override
    public String toString() {
    	String[] result = new String[typeSize];
    	ForneymonType current = head;
    	int i = 0;
    	while (checkNotNull(current)) {
    		result[i] = "\"" + current.type + "\": " + current.count;
    		current = current.next;
        	i++;
    	}
    	return "[ " + String.join(", ", result) + " ]";
    }
    
    public LinkedForneymonegerie.Iterator getIterator () {
        if (empty()) {
        	throw new IllegalStateException();
        } else {
        	return new Iterator(this);
        }
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static LinkedForneymonegerie diffMon (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
    	LinkedForneymonegerie result = y1.clone();
    	ForneymonType current = y2.head;
    	while(y2.checkNotNull(current)) {
    		result.releaseMany(current.type, current.count);
    		current = current.next;
    	}
    	return result;
    }
    
    public static boolean sameCollection (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
    	return diffMon(y1, y2).empty() && y1.size == y2.size && y1.typeSize == y2.typeSize;
    }
    
    // Private helper methods
    // -----------------------------------------------------------

    // TODO: Your helper methods here!
    
    private ForneymonType getType (String typeToFind) {
    	if (empty()) {
    		return null;
    	}
    	ForneymonType current = head;
    	while (current != null) {
    		if (current.type.equals(typeToFind)) {
    			return current;
    		}
    		current = current.next;
    	}
    	return null;
    }
    
    private boolean checkNotNull (ForneymonType typeToCheck) {
    	return typeToCheck != null; 
    }
    
    private void remove (ForneymonType toRemove) {
    	if (toRemove == head) {
    		if (head.next != null) {
    			head.next.prev = null;
    			head = head.next;
    			return;
    		} else {
    			head = null;
    		}
    	} else if (isEnd(toRemove)) {
    		toRemove.prev.next = null;
    	} else {
    		toRemove.prev.next = toRemove.next;
    		toRemove.next.prev = toRemove.prev;
    	}
    	return;
    }
    
    private void releaseMany (String toRemove, int countToRemove) {
    	ForneymonType typeToRelease = getType(toRemove);
    	if (checkNotNull(typeToRelease)) {
    		if (typeToRelease.count == countToRemove) {
    			releaseType(toRemove);
    		} else {
    			typeToRelease.count -= countToRemove;
    			size -= countToRemove;
    		}
    	}
    }
    
    private boolean isEnd (ForneymonType toCheck) {
    	if (toCheck.next == null) {
    		return true;
    	}
    	return false;
    }
    
    private ForneymonType getEnd () {
    	ForneymonType current = head;
    	while (!isEnd(current)) {
    		current = current.next;
    	}
    	return current;
    }
    
    
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedForneymonegerieIteratorInterface {
        LinkedForneymonegerie owner;
        ForneymonType current;
        int currentCountIndex;
        int itModCount;
        
        Iterator (LinkedForneymonegerie y) {
        	owner = y;
        	current = owner.head;
        	currentCountIndex = 1;
        	itModCount = owner.modCount;
        }
        
        public boolean hasNext () {
        	if (!isValid()) {
        		return false;
        	} else if ((current.count > currentCountIndex) || (current.next != null)) {
        		return true;
        	} else {
                return false;
        	}
        }
        
        public boolean hasPrev () {
        	if (!isValid()) {
        		return false;
        	} else if ((current.count > 1) || (current.prev != null)) {
        		return true;
        	} else {
                return false;
        	}
        }
        
        public boolean isValid () {
            return itModCount == owner.modCount;
        }
        
        public String getType () {
        	if (!isValid()) {
        		return null;
        	} else {
        		return current.type;
        	}
        }

        public void next () {
            if (!isValid()) {
            	throw new IllegalStateException();
            } else if (hasNext()) {
            	if (currentCountIndex < current.count) {
            		currentCountIndex++;
            	} else {
            		current = current.next;
            		currentCountIndex = 1;
            	}
            } else {
            	throw new NoSuchElementException();
            }
        }
        
        public void prev () {
        	if (!isValid()) {
            	throw new IllegalStateException();
            } else if (hasPrev()) {
            	if (currentCountIndex > 1) {
            		currentCountIndex--;
            	} else {
            		current = current.prev;
            		currentCountIndex = current.count;
            	}
            } else {
            	throw new NoSuchElementException();
            }
        }
        
        public void replaceAll (String toReplaceWith) {
            if (!isValid()) {
            	throw new IllegalStateException();
            } else {
            	itModCount++;
            	owner.modCount++;
            	current.type = toReplaceWith;
            }
        }
        
    }
    
    private class ForneymonType {
        ForneymonType next, prev;
        String type;
        int count;
        
        ForneymonType (String t, int c) {
            type = t;
            count = c;
        }
    }
}