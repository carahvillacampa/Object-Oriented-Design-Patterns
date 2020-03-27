package hw2;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import hw2.MyLinked.Node;

public class AssertExp1TEST {
	
	//AssertExp1 obj1= new AssertExp1();
	AssertExp1 test1= new AssertExp1();

	@Test
	public void TestMinValue(){
		assertTrue(-7 == test1.minValue(new double[] { -7 }));
		assertTrue(-7 == test1.minValue(new double[] { 1, 2,-7, 9, 8, 23}));
		assertTrue(-13 == test1.minValue(new double[] { -13, 4, 14, 19, 8, 12 }));
		assertTrue(-9 == test1.minValue(new double[] { 11, 7, 9, 2, 34, 11, -9 }));
		
	}
	
	@Test
	public void TestMinPosition() {
		assertTrue(0 == test1.minPosition(new double[] { -7 }));
		assertTrue(2 == test1.minPosition(new double[] { 14,-4, -7, 56, 8, 31 }) );
		assertTrue(0 == test1.minPosition(new double[] { -13, -12, -4, 78, 18, 1}) );
		assertTrue(6 == test1.minPosition(new double[] { 1, 4, -17, 72, 38, 11, -89 }));
		
	}
	
	@Test
	public void TestNumUnique() {
		try {
			assertTrue(0 == test1.numUnique(new double[] { }));
			assertTrue(2==test1.numUnique(new double[] { 11 ,4}) ); 
		}
		catch(AssertionError e) {
			System.out.println("Assertion error");
		}
		try {
			assertTrue(8 == test1.numUnique(new double[] {11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 }));	
			assertTrue(1==test1.numUnique(new double[] { 11 }) ); 
			assertTrue(1 ==test1.numUnique(new double[] { 11, 11, 11, 11 }));
			assertTrue(8== test1.numUnique(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66,77, 88 }));
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Out of bounds error");
		}
		
		double[] test = new double[] {11,11,22,33,33,33,44};
	    int unqNum = hw2.AssertExp1.numUnique(test);
	    assertTrue(unqNum == 4);
		
	}
	
	
	@Test
	public void TestRemoveDuplicates() {
		double[] t1 = new double[] {55,66,77,88,88,88,88,88};
	    double[] nt1 = hw2.AssertExp1.removeDuplicates(t1);
	    
	    double[] t2 = new double[] {1,1,2,2,3,3,4,4,5,5,6,6};
	    double[] nt2 = hw2.AssertExp1.removeDuplicates(t2);
	    assertTrue(nt1.length==4);
	    assertTrue(nt1[0]==55);
	    
	    assertTrue(nt2.length==6);
	    assertTrue(nt2[1]== 2);
	    
	    
		 
	}
}
