import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
	
	// To run in terminal java -classpath .:stdlib.jar:algs4.jar Deque < testFile.txt

	private int N;          // size of the stack
    private Node first;     // top of stack
    private Node last; //bottom of stack
    
    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }
    
	public Deque()
	{
		first = null;
		last = null;
		N = 0;
		assert check();
	}
	
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return N;
    }
    
    public void addFirst(Item item)
    {
    	if (item == null) { throw new NullPointerException("Adding null object"); }
    	Node oldfirst = first;
    	first = new Node();
    	
    	first.item = item;
    	first.next = oldfirst;
        N++;
        
        if (N == 1) {
        	last = first;
        }
        assert check();
    }
    
    public void addLast(Item item)
    {
    	if (item == null) { throw new NullPointerException("Adding null object"); }
    	Node oldlast = last;
    	last = new Node();
    	
    	last.item = item;
    	last.next = null;
    	
    	N++;
    	
    	if (N == 1) {
    		first = last;
    	} else {
    		oldlast.next = last;
    	}
    	
    	assert check();
    }
    
    public Item removeFirst()  
    {
    	 if (isEmpty()) throw new NoSuchElementException("Stack underflow");
         Item item = first.item;        // save item to return
         
         
         if (N == 1) {
        	 first.next = null;
        	 last = null;
         }
         
         first = first.next;   // delete first node
         N--;
         assert check();
         return item;       
    }
    
    public Item removeLast() 
    {
    	if (isEmpty()) throw new NoSuchElementException("Stack underflow");
    	Item item = last.item;
    	
    	 for (Node x = first; x != null; x = x.next) {
             if (x.next == last) {
            	 x.next = null;
            	 last = x;
             }
         }
    	 
    	 if (N == 1) {
    		 first = null;
    		 last = null;
    	 }
    	
    	N--;
    	
    	assert check();
    	return item;	
    	
    }

	@Override
	public Iterator<Item> iterator()  { return new ListIterator();  }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
	
	   // check internal invariants
    private boolean check() {
        
    	if (N == 0) {
            if (first != null) return false;
            if (last != null) return false;
        }
        else if (N == 1) {
            if (first == null)      return false;
            if (last == null)      return false;
            if (first.next != null) return false;
        }
        else {
            if (first.next == null) return false;
            if (last.next != null) return false;
        }

        // check internal consistency of instance variable N
        int numberOfNodes = 0;
        for (Node x = first; x != null; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != N) return false;
		
        return true;
    } 
	
	public static void main(String[] args) {

		/*
		Deque<String> s = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-") && !item.equals("+") && !item.substring(0, 1).equals("a")) s.addFirst(item);
            else if(!item.equals("-") && !item.equals("+")) s.addLast(item);
            else if (!s.isEmpty() && item.equals("-")) {StdOut.print(s.removeFirst() + " ");}
            else if (!s.isEmpty() && item.equals("+")) {StdOut.print(s.removeLast() + " ");}
        }
        StdOut.println("(" + s.size() + " left on stack)");
        StdOut.println(s.iterator().next());
		*/
	}

}
