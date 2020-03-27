package hw3;

import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;



public class fp {
	
/*
 * @param l Iterable<U > object
 * @param f Function interface, accepts value U 
 * @return V as specified by f
 * 
 */
	static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
		if ( l== null || f== null) {
			throw new IllegalArgumentException("Null arguments are invalid");
		}
		List<V> out = new ArrayList<>();
		for(U elem : l) {
			out.add(f.apply(elem));
		}

		return out;
	}
	/*
	 * @param e initial value of V
	 * @param l Iterable<U > object
	 * @param f Accepts U,V returns V
	 * @return  V as specified by biFunction
	 */
	static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V, U, V> f){
		if (e == null || l== null || f== null) {
			throw new IllegalArgumentException("Null arguments are invalid");
		}
		
		for(U elem: l) {
			e= f.apply(e,elem);
		}
		return e;
	}

	/*
	 * @param e initial value of V
	 * @param l Iterable<U > object
	 * @param f Accepts U,V returns V
	 * @return  V as specified by biFunction
	 * 
	 * removed function:
	 * while(l.iterator().hasNext()!= false) {
				e= f.apply(elem, e);
			}	
	 */
	static <U,V> V foldRight(V e, Iterable<U>l, BiFunction<U,V,V> f){
		if (e == null || l== null || f== null) {
			throw new IllegalArgumentException("Null arguments are invalid");
		}
		
		List<U> reverseList= new ArrayList<U>();
		for (U item: l) {
			reverseList.add(item);
		}
		Collections.reverse(reverseList);
		
		U max;
		for(U elem: reverseList) {
			e= f.apply(elem, e);	
		}
		return e;
	}


	
	/*
	 * @param l Iterable<U > object
	 * @param p predicate of the function
	 */
	static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p){
		if (l == null || p== null) {
			throw new IllegalArgumentException("Null arguments are invalid");
		}
		List<U> filterOut= new ArrayList<>();
		for(U salary: l) {
			if(p.test(salary)== true) {
				filterOut.add(salary);
			}
		}
		return filterOut;
	}

	/*
	 *  @param l Iterable<U > object
	 *  @param c Comparator<U> object
	 *  @return minimum value of the element in the list
	 */
	static <U> U minVal(Iterable<U> l, Comparator<U> c){
		return foldLeft(l.iterator().next(), l,(a,b) ->c.compare(a, b) < 0 ? a:b);
	}

	/*
	 * @ param l Iterable<U > object
	 * @return position of the smallest item in the list
	 */
	static <U extends Comparable<U>> int minPos(Iterable<U> l){ 
		//use lambda and pass it on to foldleft
		int res= fp.foldLeft(0, l, (x,y) -> {
			ArrayList<U> list= new ArrayList<>();
			l.forEach(list::add);
			if(list.get(x).compareTo(y) < 0) {
				return x;
			}
			else if( list.get(x).compareTo(y) > 0) {
				return list.indexOf(y);
			}
			else {
				return x;
			}
		});
		return res;
	}

	public static void main(String[] args) {
		 List<Person> people = new ArrayList<>();
			Person p1= new Person(1200,"SomeName");
			Person p2= new Person(1500,"Person");
			Person p3= new Person(1800,"Shirt Pants");
			Person p4= new Person(210000,"Bo Tie");
			Person p5= new Person(200500,"Sam Guy");
	        people.add(p1);
	        people.add(p2);
	        people.add(p3);
	        people.add(p4);
	        people.add(p5);
	         
			List<Integer> hashes = fp.map(people, new Function<Person,Integer>() {
				@Override
				public Integer apply(Person p) {
					return p.hashCode();
				}
			});
			
			//testing foldright
			List<String> someList = new ArrayList<>();
			
			someList.add(p1.name);
			someList.add(p2.name);
			someList.add(p3.name);
			
			BiFunction< String, String, String> biFunc= (num1, num2)-> (num1+num2);
			//System.out.println(fp.foldRight("", someList, (i,j)-> (i+j)));
			
			//testing minPos
			
			List<Integer> min= new ArrayList<>();
			min.add(100);
			min.add(10);
			min.add(80);
			min.add(30);
			min.add(190);
			
			int x= fp.minPos(min);
			System.out.println(fp.minVal(min, Integer:: compare));
	}
}

class Person{
	final int salary;
	final String name;
	
	Person(int salary, String name){
		this.salary = salary;
		this.name = name;
	}
	
	int getSalary() { return salary; }
	String name() { return name;}
	
}

