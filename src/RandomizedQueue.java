import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
	
	 private Item[] a;         // array of items
	 private int N;            // number of elements on stack
	
	public RandomizedQueue()
	{
		// construct an empty randomized queue
		a = (Item[]) new Object[2];
	}
	
	public boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	// resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
	
	public void enqueue(Item item) 
	{
		if (item == null) { throw new NullPointerException("Adding null object"); }
		// add the item
		if (N == a.length) resize(2*a.length);    // double size of array if necessary
        a[N++] = item;   
	}
	
	public Item dequeue()  
	{
		// delete and return a random item
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		int index = StdRandom.uniform(N);
        Item item = a[index];
        a[index] = a[N-1];                              // to avoid loitering
        a[N-1] = null;
        N--;
        // shrink size of array if necessary
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
	}
	
	public Item sample()
	{
		// return (but do not delete) a random item
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		int index = StdRandom.uniform(N);
        Item item = a[index];

        return item;
	}
	
	@Override
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() { return new RandomIterator(); }
	
	private class RandomIterator implements Iterator<Item> {
		
		private int index = 0;
		private int[] choosenIndex;
		public RandomIterator() {
			choosenIndex = new int[N];
			for (int i = 0; i < N; i++) {
        		choosenIndex[i] = i;
        	}
        	StdRandom.shuffle(choosenIndex);
		}
        public boolean hasNext()  { return index != N;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
        	
        	if (!hasNext()) throw new NoSuchElementException();
        	Item randomToReturn = a[choosenIndex[index++]];
        	return randomToReturn;
        }
    }
	
	public static void main(String[] args) {
		
		/*
		
		RandomizedQueue<String> s = new RandomizedQueue<String>();
		
		s.enqueue("1");
		s.enqueue("2");
		s.enqueue("3");
		s.enqueue("4");
		s.enqueue("5");
		s.enqueue("6");
		s.enqueue("7");
		s.enqueue("8");
		s.enqueue("9");
		s.enqueue("10");
		s.enqueue("11");
		s.enqueue("12");
		s.enqueue("13");
		s.enqueue("14");
		s.enqueue("15");
		s.enqueue("16");
		s.enqueue("17");
		s.enqueue("18");
		s.enqueue("19");
		
     //   while (!StdIn.isEmpty()) {
    //        String item = StdIn.readString();
    //        if (!item.equals("-") && !item.equals("+")) s.enqueue(item);
     //       else if (!s.isEmpty() && item.equals("-")) {StdOut.print(s.dequeue() + " ");}
     //       else if (!s.isEmpty() && item.equals("+")) {StdOut.print(s.sample() + " ");}
     //   }
		
		
		Iterator<String> aiterator = s.iterator();
		int k = Integer.parseInt(args[0]);
		for (int i = 0; i < k; i++) {
			System.out.println(aiterator.next());
		}
		*/
	}

}
