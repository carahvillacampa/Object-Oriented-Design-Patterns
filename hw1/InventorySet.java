package hw1;
import java.util.Map;
import java.util.Set;
import flyweight.Data;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

// TODO:  complete the methods
/**
 * An Inventory implemented using a <code>HashMap&lt;Video,Record&gt;</code>.
 * Keys are Videos; Values are Records.
 *
 * @objecttype Mutable Collection of Records
 * @objectinvariant
 *   Every key and value in the map is non-<code>null</code>.
 * @objectinvariant
 *   Each value <code>r</code> is stored under key <code>r.video</code>.
 */
final class InventorySet {
  /** @invariant <code>_data != null</code> */
  private final Map<VideoObj,Record> _data;

  InventorySet() {
    _data = new HashMap<VideoObj,Record>();
  }

  /**
   * Return the number of Records.
   */
  public int size() { 
    return _data.values().size();
  }

  /**
   * Return a copy of the record for a given Video.
   */
  public Record get(VideoObj v) {
	return _data.get(v);
  }

  /**
   * Return a copy of the records as a collection.
   * Neither the underlying collection, nor the actual 
   * records are returned.
   */
  public Collection toCollection() {
    // Recall that an ArrayList is a Collection.
	  
    ArrayList<Record> array1= new ArrayList<Record>();
    for (VideoObj vi : _data.keySet()) {
    	array1.add(_data.get(vi).copy());
    }

    return array1;
  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if video null or change is zero
   * @postcondition changes the record for the video
   * 
   * Keys: video
   * Values: records
   */
  public void addNumOwned(VideoObj video, int change) {
    if(video== null || change==0) { throw new IllegalArgumentException();}
    

    if(!_data.containsKey(video)) {
    	_data.putIfAbsent(video, new Record(video, 0, 0, 0));
    }
    _data.get(video).numOwned += change;
    if(_data.get(video).numOwned <= 0)
    	_data.remove(video);
   
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   * @postcondition changes the record for the video
   * decrease numOwned after this 
   */
  public void checkOut(VideoObj video) {
	 if(!_data.containsKey(video) || _data.get(video).numOut ++ >= _data.get(video).numOwned) {
		 throw new IllegalArgumentException();
	 }  
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   * @postcondition changes the record for the video
   */
  public void checkIn(VideoObj video) {
	 
	  if(!_data.containsKey(video) || _data.get(video).numOut -- == 0) {
			 throw new IllegalArgumentException();
	  }	
  }
  
  /**
   * Remove all records from the inventory.
   * @postcondition <code>size() == 0</code>
   */
  public void clear() {
	  _data.clear();
  }

  /**
   * Return the contents of the inventory as a string.
   * 
   */
  public String toString() {
	  StringBuffer buffer= new StringBuffer();
	  buffer.append("Keys and Values:\n");
	  for(Record z: _data.values()) {
		  buffer.append(" ");
		  buffer.append(z);
		  buffer.append("\n");
	  }
	  return buffer.toString();
  }
}
