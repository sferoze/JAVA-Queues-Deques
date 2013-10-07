import java.util.Iterator;


public class Subset {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RandomizedQueue<String> s = new RandomizedQueue<String>();
		
		while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            s.enqueue(item);
		}
		
		Iterator<String> aiterator = s.iterator();
		int k = Integer.parseInt(args[0]);
		for (int i = 0; i < k; i++) {
			System.out.println(aiterator.next());
		}
	}
}
