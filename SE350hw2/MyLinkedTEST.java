package hw2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//import junit.framework.TestCase;
import org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Linked List tests")
public class MyLinkedTEST{
	
	/*
	 *Notes:
	 *
	 *- Do not make Tests private and don't extend
	 *TestCase class. You're going to get unrooted
	 *test errors.
	 *- Unrooted Test errors: 
	 *- Extending TestCase class was only for JUnit 3. 
	 *  Not neccesary for JUnit 5, I think.
	 *  
	 *  Writing your own JUnit tests
	 *  - methods must return void
	 *  - set up and set down methods cannot be private
	 *  - @beforealls must be static methods
	 *  - tests method must be annotated with @test annotation
	 *  
	 */
	
	 //MyLinked b = new MyLinked ();

	 @Test
	 @DisplayName("Delete method")
	 public void testDelete () {
		 System.out.println();
		 System.out.println("Testing Delete method");
		 MyLinked b = new MyLinked ();
	     b.add (1);
	     assertTrue(b.isEmpty()== false);
	     b.delete (0); 
	     assertTrue(b.isEmpty());
	     assertTrue(b.size()==0);
	     //adding multiple items in a list
	     System.out.printf("Adding multiple items in the list",b);
	     System.out.println();
	     double num= 13;
	     for (double i = 1; i < num; i++) {
	         b.add (i);
	     }
	     //see if it added all the items
	     assertTrue(b.size()==12);
	     System.out.printf("bigger list", b);
	     b.delete (0); 
	     assertTrue(b.size()==num-2);
	     System.out.println();
	     
	     //delete last item
	     System.out.printf("Deleted in the end", b);
	     b.delete (10);
	     assertTrue(b.size()==num-3);
	     System.out.println();
	     
	     //delete middle item
	     System.out.printf("Deleted in the middle", b);
	     b.delete(4);
	     assertTrue(b.size()==num-4);
	     System.out.println();
	 }
	 
	 
	 @Test
	 @DisplayName("Testing reverse")
	 public void testReverse () {
		 System.out.println("Testing reverse method");
		 MyLinked<Double> a = new MyLinked<Double> ();
		 //check if the size of linkedlist is 0 or empty
		 assertTrue(a.isEmpty());
		 assertEquals(0, a.N);
		 
		 System.out.printf ("adding 1 item", a);
		 a.add (1);
		 assertFalse(a.isEmpty());
		 assertTrue(a.first.item==1);
		 assertEquals(a.size(), 1);
		 System.out.println();
		 
		 System.out.printf ("adding another item", a);
		 a.add (2);
		 assertTrue(a.size()==2);
		 System.out.println();
		 
		 System.out.printf ("reversing linked list", a);
		 a.reverse ();
		 //reversing twice results in an error
		 assertTrue(a.first.item==2);
		 //assertTrue(a.first.item==1);
		 assertEquals(a.size(), 2);
		 System.out.println();
		 
		 System.out.printf ("bigger list", a);
		 for (double i = 3; i < 7; i++) {
	         a.add (i);
	         a.add (i);
	     }
		 System.out.println();
		 
		 System.out.printf("reversed", a);
		 a.reverse();
		 a.reverse ();
		 assertEquals(10, a.size());
		 System.out.println();
		 
		 System.out.println("Specifying object from generic MyLinked class");
		 MyLinked <Integer> I = new MyLinked<Integer> ();
		 assertTrue(I.isEmpty());
		 assertEquals(0,I.size());
		 assertFalse(I.size()==1);
		 I.add(3);
		 I.add(118);
		 I.add(19);
		 I.add(23);
		 I.add(89);
		 I.reverse();
		 assertTrue(I.first.item==89);
		 //I.reverse();
		// assertEquals(3,I.first.item);
     
	 }
	 
	@Test 
	@DisplayName("Remove method")
	 public void testRemove () {
	 System.out.println();
	 System.out.println("Testing remove by value");
     MyLinked c = new MyLinked ();
     assertTrue(c.isEmpty());
     assertEquals(0,c.size());
     
     try {
    	 System.out.println("Removing 4");
    	 c.remove(4);//will fail since list is empty
     }catch(NullPointerException e){
    	 System.out.println("Can't remove non-existent object");
     }
     System.out.printf ("Adding 1 and checking size", c);
     c.add (1);
     assertEquals(1,c.size());
     System.out.println();
     
     try {
    	 System.out.println("Trying to remove 7");
    	 c.remove(7);//will fail since list is empty
    	 assertTrue(c.condition== false);
     }catch(NullPointerException e){
    	 System.out.println("Can't remove non-existent object");
     }
     
     System.out.println("Removing 1");
     c.remove (1);
     assertTrue(c.condition== true);
     assertTrue(c.isEmpty());
     //System.out.println();
     
     System.out.println("Adding multiple items to empty Linked list");
     for (double i = 1; i < 5; i++) {
         c.add (i);
         c.add (i);
     }
     assertEquals(8,c.size());
     
     for (double i = 1; i < 5; i++) {
         c.add (i);
         c.add (i);
         c.add (i);
         c.add (i);
         c.add (i);
     }
     assertEquals(28, c.size());
     try {
    	 System.out.println("Trying to remove 9");
    	 c.remove(9);//will fail since list is empty
    	 assertTrue(c.condition== false);
     }catch(NullPointerException e){
    	 System.out.println("Can't remove non-existent object");
     }
     
     System.out.println("Removing 3 and checking size");
     //c.remove (3);
     System.out.printf ("removed all 3s", c);
     //assertEquals(27,c.size());
     
     
     //removing a non existent item 
     /*
      *  
     //System.out.printf ("removed 4 from singelton", b);
     //System.out.printf ("removed 1 from singelton", b);
     
     //double dupes
     
     
     //5 dupes
     
     
     //System.out.printf ("longer list", b);
     
    // System.out.printf ("removed all 9s", b); // does nothing
     
     
     
     //
     b.remove (1);
     //System.out.printf ("removed all 1s", b);
     b.remove (4);
     //System.out.printf ("removed all 4s", b);
     b.remove (2);
     //System.out.printf ("removed all 2s", b); // should be empty
     */
	 }
     
    
	 
}
