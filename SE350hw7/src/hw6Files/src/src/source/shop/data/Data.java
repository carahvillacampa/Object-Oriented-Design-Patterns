package hw6Files.src.src.source.shop.data;

import java.util.HashMap;
import hw6Files.src.src.source.shop.command.RerunnableCommand;
import hw6Files.src.src.source.shop.command.UndoableCommand;

/**
 * A static class for accessing data objects.
 */
public class Data {
  private Data() {}
  /**
   * Returns a new Inventory.
   */
  static public final Inventory newInventory() {
    return new InventorySet();
  }
  /**
   * create a hashmap of the string and video object,
   * then use this everytime you add a new video
   */
  final public static HashMap<Video, Video> video_consing = new HashMap<Video, Video>();
  /**
   * Factory method for Video objects.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if Video invariant violated.
   */
  static public Video newVideo(String title, int year, String director) {
	  if (title == null || title.isBlank() || title.isEmpty())
			throw new IllegalArgumentException("Title must be non-null, have no leading or "
					+ "final spaces, and not be an empty string");
	if (director == null || director.isBlank() || director.isEmpty())
			throw new IllegalArgumentException("Director must be non-null, have no leading or "
					+ "final spaces, and not be an empty string");
	if (year <= 1800 || year >= 5000)
		throw new IllegalArgumentException("Year must be greater than 1800 or less than 5000");

    Video vid= new VideoObj(title.trim(), year, director.trim());
    if(!video_consing.containsKey(vid)) {
    	video_consing.put(vid, vid); 	
    }
    return vid;
    
  }

  /**
   * Returns a command to add or remove copies of a video from the inventory.
   * <p>The returned command has the following behavior:</p>
   * <ul>
   * <li>If a video record is not already present (and change is
   * positive), a record is created.</li>
   * <li>If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.</li>
   * <li>If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.</li>
   * </ul>
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if <code>inventory<code> not created by a call to <code>newInventory</code>.
   */
  static public UndoableCommand newAddCmd(Inventory inventory, Video video, int change) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdAdd((InventorySet) inventory, video, change);
  }

  /**
   * Returns a command to check out a video.
   * @param video the video to be checked out.
   */
  static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
	  if( !(inventory instanceof InventorySet)) 
		  throw new IllegalArgumentException();
    return new CmdOut((InventorySet)inventory, video );
  }
  /**
   * Returns a command to check in a video.
   * @param video the video to be checked in.
   */
  static public UndoableCommand newInCmd(Inventory inventory, Video video) {
	 if(!(inventory instanceof InventorySet))
		 throw new IllegalArgumentException();
     return new CmdIn((InventorySet)inventory, video);
  }
  
  /**
   * Returns a command to remove all records from the inventory.
   */
  static public UndoableCommand newClearCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdClear((InventorySet) inventory);
  }

  /**
   * Returns a command to undo that will undo the last successful UndoableCommand. 
   */
  static public RerunnableCommand newUndoCmd(Inventory inventory) {
    if(!(inventory instanceof InventorySet)) {
    	throw new IllegalArgumentException();
    }
    //(?) This is going to get the undo in CommandHistoryObj since it implements it
    //(?) I just need a reference and not a solid implementation??
    return ((InventorySet)inventory).getHistory().getUndo();
  }

  /**
   * Returns a command to redo that last successfully undone command. 
   */
  static public RerunnableCommand newRedoCmd(Inventory inventory) {
    if(!(inventory instanceof InventorySet)) {
    	throw new IllegalArgumentException();
    }
    return ((InventorySet)inventory).getHistory().getRedo();
  }
}  
