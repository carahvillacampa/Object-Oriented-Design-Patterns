package hw1;
import org.junit.Assert;
//import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import java.util.HashSet;

// TODO:  complete the tests
public class InventoryTEST extends TestCase {
  public InventoryTEST(String name) {
    super(name);
  }
  
  InventorySet test1= new InventorySet();
  
  VideoObj obj1= new VideoObj("TitleOne", 1990, "DirectorOne");
  VideoObj obj2= new VideoObj("TitleTwo", 1997, "DirectorTwo");
  
  Record rec1= new Record(obj1, 45, 1, 245);
  Record rec2= new Record(obj2, 65, 17, 145);
  
  @Test
  public void testSize() {
    assertTrue(test1.size() ==0);
    
    
  }

  @Test
  public void testAddNumOwned() {
    assertTrue(test1.size()==0);
    test1.addNumOwned(obj1, 25);
    assertEquals((test1.get(obj1).numOwned), 25);
  }

  @Test
  public void testCheckOutCheckIn() {
	  test1.addNumOwned(obj2, 10);
	  test1.checkOut(obj2);
	  assertEquals((test1.get(obj2).numOut), 1);
	  test1.checkIn(obj2);
	  assertEquals((test1.get(obj2).numOut), 0);
	  
    // TODO  
  }

  @Test
  public void testClear() {
	  test1.addNumOwned(obj2, 10);
	  assertTrue(test1.size()== 1);
	  test1.addNumOwned(obj1, 20);
	  assertTrue(test1.size()> 1);
	  test1.clear();
	  assertTrue(test1.size()==0);
	  
  }

  @Test
  public void testGet() {
	  test1.addNumOwned(obj2, 55);
	  assertTrue(test1.get(obj1)==test1.get(obj1));
	  assertNotSame(test1.get(obj1),test1.get(obj2));
  }

  @Test
  public void testToCollection() {
    // Be sure to test that changing records in the returned
    // collection does not change the original records in the
    // inventory.  ToCollection should return a COPY of the records,
    // not the records themselves.
	test1.addNumOwned(obj2, 10); 
	test1.addNumOwned(obj1, 25);
	assertTrue(test1.toCollection().size()==2);
	test1.clear();
	assertTrue(test1.toCollection().size()==0);
	  
	
  }
}
