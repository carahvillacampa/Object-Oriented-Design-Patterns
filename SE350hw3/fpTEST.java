package hw3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class fpTEST {
	
	
	@Test
	public void testMap() {
		List<Person> people= new ArrayList<>();
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
	    
	    List<Integer> hashes = fp.map(people, new Function<Person,Integer> () {
			//anonymous inner classes
			@Override
			public Integer apply(Person p) {
				return p.hashCode();
			}
		});
	    assertTrue(hashes.isEmpty() != true);
	    assertTrue(hashes.size()==5);
	    assertEquals(hashes.get(0), hashes.get(0));
	    assertEquals(hashes.size(), people.size()); //same number of hashcodes for each obj
	    assertFalse(hashes.get(0)==hashes.get(2));
	    assertFalse(hashes.get(1)==hashes.get(4));
	    
	}
	
	@Test
	public void testFoldLeft() {
		List<Integer> l1 = new ArrayList<>();
		Person p4= new Person(1800,"Shirt Pants");
		Person p5= new Person(210000,"Bo Tie");
		Person p6= new Person(200500,"Sam Guy");
		
		l1.add(p4.salary);
		l1.add(p5.salary);
		l1.add(p6.salary);
		
		List<Integer> l2= new ArrayList<>();
		Person p1= new Person(1200,"SomeName");
		Person p2= new Person(1500,"Person");
		Person p3= new Person(300,"boke hole");
		
		l2.add(p1.salary);
		l2.add(p2.salary);
		l2.add(p3.salary);
		
		BiFunction< Integer, Integer, Integer> biFunc= (num1, num2)-> (num1+num2);
		assertFalse(fp.foldLeft(0, l1, biFunc)==0);
		assertTrue(fp.foldLeft(0, l1, biFunc)== 412300);
		assertTrue(fp.foldLeft(0, l2, biFunc)== 3000);
		assertTrue(fp.foldLeft(0, l2, biFunc)== addTo(l2));
		assertTrue(fp.foldLeft(0, l1, biFunc)== addTo(l1));
	
	}
	public int addTo(List<Integer> l) {
		int sum=0;
		for(int x : l) {
			sum+=x;
		}
		return sum;
	}
	
	@Test
	public void testFoldRight() {
		List<String> l1Str = new ArrayList<>();
		Person p4= new Person(1800,"Ap");
		Person p5= new Person(210000,"p");
		Person p6= new Person(200500,"le");
		
		l1Str.add(p4.name);
		l1Str.add(p5.name);
		l1Str.add(p6.name);
		
		BiFunction<String, String, String> biStr= (str1, str2)-> (str1+str2);
		assertFalse(l1Str.isEmpty());
		String x= fp.foldRight(" ", l1Str, biStr);
		assertTrue(x!= null);
		assertTrue(biStr.apply(p4.name, p5.name) != addString(l1Str));
			
	}
	public String addString(List<String >l) {
		String retString= "";
		for(String x: l) {
			retString +=x;
		}
		return retString;
	}
	@Test
	public void testFilter() {
		Predicate<Integer> condition= (i)-> i >100000;
		
		Person p4= new Person(1800,"Ap");
		Person p5= new Person(210000,"p");
		Person p6= new Person(200500,"le");
		
		List<Integer> salary= new ArrayList<>();
		salary.add(p4.salary);//greater
		salary.add(p5.salary);//greater
		salary.add(p6.salary);
	
		
		Iterable<Integer> salaryOutput= fp.filter(salary, condition);
		for(int i: salaryOutput) {
			assertTrue(i > 100000);
		}
		
	}
	
	public List<Integer> listFilter(List<Integer> f) {
		List<Integer> filtered= new ArrayList<>();
		for(int x: f) {
			if (x> 100000){
				filtered.add(x);
			}
		}
		return filtered;
	}
	@Test
	public void testMinVal() {
		List<Integer> minVal= new ArrayList<>();
		minVal.add(12);
		minVal.add(1);
		minVal.add(13);
		minVal.add(20);
		minVal.add(30);
		assertTrue(minVal.isEmpty()== false);
		assertFalse(fp.minVal(minVal,Integer:: compare)== 30);
		assertTrue(fp.minVal(minVal, Integer:: compare)==1);
		
		List<Integer> minVal1= new ArrayList<>();
		minVal1.add(8);
		minVal1.add(0);
		minVal1.add(12);
		minVal1.add(5);
		minVal1.add(6);
		
		assertTrue(minVal1.isEmpty()== false);
		assertFalse(fp.minVal(minVal1,Integer:: compare)== 12);
		assertTrue(fp.minVal(minVal1, Integer:: compare)==0);
		
	}
	
	//int comparator
	
	@Test
	public void testMinPos() {
		
		List<String> min= new ArrayList<>();
		min.add("A");
		min.add("B");
		min.add("C");
		assertTrue(fp.minPos(min)!= 1);
		assertTrue(fp.minPos(min)==0);
		assertEquals(fp.minPos(min), 0);
		
		List<String> minString= new ArrayList<>();
		
		minString.add("why");
		minString.add("is");
		minString.add("this");
		minString.add("class");
		minString.add("difficult");
		
		int x= fp.minPos(minString);
		assertTrue(x== 3);
		assertTrue(minString != null);
		assertFalse(minString.isEmpty());
		assertEquals(fp.minPos(minString), 3);
		
		//assertfail(fp.minPos(null));
	}

}
