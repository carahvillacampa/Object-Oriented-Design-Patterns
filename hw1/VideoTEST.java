package hw1;
import static org.junit.Assert.assertNotEquals;

//import static org.junit.Assert.assertNotEquals;
import org.junit.Assert;
import org.junit.Test;
import junit.framework.TestCase;

// TODO:  complete the tests
public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  @Test
  public void testConstructorAndAttributes() {
    String title1 = "XX";
    String director1 = "XY";
   
    String title2 = " XX ";
    String director2 = " XY ";
    int year = 2002;

    VideoObj v1 = new VideoObj(title1, year, director1);
    Assert.assertSame(title1, v1.title());
    Assert.assertEquals(year, v1.year());
    Assert.assertSame(director1, v1.director());

    VideoObj v2 = new VideoObj(title2, year, director2);
    Assert.assertEquals(title1, v2.title());
    Assert.assertEquals(director1, v2.director());
  }

  @Test
  public void testConstructorExceptionYear() {
    try {
      new VideoObj("X", 1800, "Y"); //catch
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("X", 5000, "Y"); //catch
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("X", 1801, "Y"); //pass
      new VideoObj("X", 4999, "Y");
    } catch (IllegalArgumentException e) {
      Assert.fail();
    }
  }
  
  @Test
  public void testConstructorExceptionTitle() {
    try {
      new VideoObj(null, 2002, "Y");
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj(" ", 2002, "Y");
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("", 2002, "Y");
      Assert.fail();
    } catch (IllegalArgumentException e) { }
    
    
  }

  @Test
  public void testConstructorExceptionDirector() {
	  try {
	      new VideoObj("N", 2002, null);
	      Assert.fail();   
	    } catch (IllegalArgumentException e) { }
	    try {
	      new VideoObj("N", 2002, "");
	      Assert.fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      new VideoObj("N", 2002, " ");
	      Assert.fail();
	    } catch (IllegalArgumentException e) { }
	  
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals
      (-875826552,
       new VideoObj("None", 2009, "Zebra").hashCode());
    Assert.assertEquals
      (-1391078111,
       new VideoObj("Blah", 1954, "Cante").hashCode());
  }

  @Test
  public void testEquals() { 
    VideoObj obj1= new VideoObj("SomeName", 1908 ,"SomeDirector");
    VideoObj obj2= new VideoObj("NoName", 1998, "NoDirectorName");
    VideoObj obj3= new VideoObj("SomeName", 1908 ,"SomeDirector");
    
    assertTrue(obj3.equals(obj1));
    assertFalse(obj3.equals(null));
    assertFalse(obj1.equals(null));
    assertEquals(obj2, obj2);
    assertEquals(obj3, obj3);
    assertEquals(obj1, obj3);
    assertNotEquals(obj2, obj1);
    assertNotEquals(obj1, obj2);
   
  }
  
  @Test
  public void testCompareTo() { 
	  VideoObj obj1= new VideoObj("SomeName", 1908 ,"SomeDirector");
	  VideoObj obj2= new VideoObj("SomeName", 1908 ,"SomeDirector");
	  VideoObj obj3= new VideoObj("NoName", 1998, "NoDirectorName");
	  
	  Assert.assertTrue(obj1.compareTo(obj3)==-1);
	  Assert.assertFalse(obj1.compareTo(obj3)==0);
	  
  }

  @Test
  public void testToString() { 
	VideoObj obj1= new VideoObj("SomeName", 1908 ,"SomeDirector");
	String x= obj1.toString();
	Assert.assertEquals(x, obj1.toString());
	
  }

}
