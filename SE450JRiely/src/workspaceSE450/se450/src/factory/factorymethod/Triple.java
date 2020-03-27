 package factory.factorymethod;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * triple e is a subclass of Iterable:
 * all the concrete implementation of triple E has to implement
 * the method Iterator
 * The factory method here: Iterator<E>
 * @author carahvillacampa
 *
 * @param <E>
 */
interface Triple<E> extends Iterable<E> { }
////////////////////////////////////////////////////////////////////////

/**
 * first concrete implementation of Triple<E>
 * @author carahvillacampa
 *
 * @param <E>
 */
class FieldTriple<E> implements Triple<E> {
	private E one; E two; E three;
	public FieldTriple(E one, E two, E three) {
		this.one = one; this.two = two; this.three = three;
	}
	public Iterator<E> iterator() { return new TheIterator(); }

	private class TheIterator implements Iterator<E> {
		private int i;
		public boolean hasNext() { return i < 3; }
		public E next() {
			i++;
			switch (i) {
			case 1: return one;
			case 2: return two;
			case 3: return three;
			}
			throw new java.util.NoSuchElementException();
		}
		public void remove() { throw new UnsupportedOperationException(); }
	}
}
/**
 * Arrays do not play nicely with generics, so use ArrayList
 * @author carahvillacampa
 * @param <E> implements an interface that extends the iterable interface
 */
class ArrayTriple<E> implements Triple<E> {
	private ArrayList<E> a = new ArrayList<E>();
	//constructor
	public ArrayTriple(E one, E two, E three) {
		a.add(0,one); a.add(1,two); a.add(2,three);
	}
	
	/**
	 * method that returns an iterator
	 */
	public Iterator<E> iterator() { return new TheIterator(); }
	
	/**
	 * Iterator means that you can use a for loop.
	 * checks if there is an item next in line
	 * @author carahvillacampa
	 *
	 */
	private class TheIterator implements Iterator<E> {
		private int i = -1;
		public boolean hasNext() { return i < 2; }
		public E next() {
			i++;
			if (i <= 2)
				return a.get(i);
			else
				throw new java.util.NoSuchElementException();
		}
		public void remove() { throw new UnsupportedOperationException(); }
	}
}
