package hw6Files.src.src.source.shop.main;
import org.junit.Assert;
import org.junit.Test;
import junit.framework.TestCase;
import hw6Files.src.src.source.shop.command.Command;
import hw6Files.src.src.source.shop.data.Data;
import hw6Files.src.src.source.shop.data.Record;
import hw6Files.src.src.source.shop.data.Video;
import hw6Files.src.src.source.shop.data.Inventory;
import java.util.Iterator;

// TODO:
// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
public class TEST1 extends TestCase {
  private Inventory _inventory = Data.newInventory();
  public TEST1(String name) {
    super(name);
  }
  private void check(Video v, int numOwned, int numOut, int numRentals) {
    Record r = _inventory.get(v);
    assertEquals(numOwned, r.numOwned());
    assertEquals(numOut, r.numOut());
    assertEquals(numRentals, r.numRentals());
  }
   
  @Test
  public void test1() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    Command inCmd= Data.newInCmd(_inventory, v1);
    inCmd.run();
    assertEquals(0, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    check(v1,10,0,0);
    Video v2= Data.newVideo("title2", 2000, "Director2");
	assertEquals(1, _inventory.size());
	assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
	assertEquals(2, _inventory.size());
	assertTrue(Data.newAddCmd(_inventory, v2, -5).run());
	assertEquals(1, _inventory.size());
	Command outCmd= Data.newOutCmd(_inventory, v2);
	outCmd.run();
	Command clrCmd= Data.newClearCmd(_inventory);
	clrCmd.run();
	assertEquals(0, _inventory.size());
	assertTrue(Data.newAddCmd(_inventory, v1, 3).run());
    assertEquals(1, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
    assertEquals(2, _inventory.size());
  } 
}
