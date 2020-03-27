package shop.data;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import shop.command.Command;

/*
 * Implementation of Inventory interface.
 * @see Data
 */

final class InventorySet implements Inventory {
  // Chose to use Map of Record, rather than RecordObj, because of
  // Java's broken generic types.  The story is too sad to retell, but
  // involves the fact that Iterable<? extends Record> is not a valid
  // type, and that Iterator<RecordObj> is not a subtype of
  // Iterator<Record>.
  //
  // Seems like the best approach for Java generics is to use the
  // external representation internally and downcast when necessary.
  private final Map<Video,Record> _data;

  InventorySet() {
    _data = new HashMap<Video,Record>();
  }
  public int size() {
    return _data.values().size();
  }
  /*
   * if(_data.containsKey(v)) {
		return _data.get(v);
	}
	return null;
   */
  public Record get(Video v) {
	  if (v== null) { throw new IllegalArgumentException();}
	  if (_data.containsKey(v)) {
		  Record r = _data.get(v);
		  return r;
	  } else {
		  return null;
	  }
  }

  public Iterator<Record> iterator() {
    return Collections.unmodifiableCollection(_data.values()).iterator();
  }

  public Iterator<Record> iterator(Comparator<Record> comparator) { 
	List<Record> recordList= new ArrayList(_data.values());
	Collections.sort(recordList,comparator);
	return Collections.unmodifiableCollection(recordList).iterator();   
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
   * 
   * 
	}
   */
  void addNumOwned(Video video, int change) {
	  
	// Throw exception if null or change is 0
	    if (video == null || change == 0)
	    	throw new IllegalArgumentException("Invalid input");
	    // If video doesn't exist, create new record
	    if (!_data.containsKey(video) ) {
			Record newRecord = new RecordObj(video, 0, 0, 0);
			_data.put(video, newRecord);
	    }
	    // If video exists, change the number owned
	    if (_data.containsKey(video)) {
	    	//If the number of videos owned - the number of videos out + change is less than 0, throw exception
	    	if (_data.get(video).numOwned() - _data.get(video).numOut() + change < 0) {
	    		throw new IllegalArgumentException("Uh Oh Spaghettios");
	    	}   	
	    	Record record = new RecordObj(video, _data.get(video).numOwned() +
	    			change, _data.get(video).numOut(), _data.get(video).numRentals());
	    	_data.replace(video, record);
	    }
	    // If the number of videos owned drops below 0, remove the video from inventory
	    if (_data.get(video).numOwned() < 1) {
	    	_data.remove(video);
	    }
	  /*
	  
	    
	    if(video==null || change==0) {
	    	throw new IllegalArgumentException("Invalid entrie/s");
	    }
		// change is non negative, video isn't present && change > 0
		if(!_data.containsValue(_data.get(video))) {
			//update the numOwned to change
			Record newRec = new RecordObj(video, change, 0, 0); 
			//add new Record to the hashmap
			_data.putIfAbsent(video, newRec);
		}
		//update numOwned if video is already there
		else if(_data.containsValue(_data.get(video))) {
			//make record copy
			Record RecCopy = new RecordObj(video, _data.get(video).numOwned()+
					change, _data.get(video).numOut(),_data.get(video).numRentals());	
	
			// if numOwned is less than 1: remove it
			if(_data.get(video).numOwned() < 1 ) {
				_data.remove(video, _data.get(video));
			} 
			else {
				//replace old value w new value
				_data.replace(video, _data.get(video), RecCopy);
			}
		}
	  
	    
		*/
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   */
  void checkOut(Video video) {
	  if(!_data.containsKey(video) || _data.get(video).numOut() == _data.get(video).numOwned() ) {
			 throw new IllegalArgumentException("Map has no record of video or rentals are out");
	  }
	  
  	Record record = new RecordObj(video, _data.get(video).numOwned(), _data.get(video).numOut() + 1, _data.get(video).numRentals() + 1);
  	_data.replace(video, record);	  
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   */
  void checkIn(Video video) {
	  if(!_data.containsKey(video) || _data.get(video).numOut() < 0) {
			 throw new IllegalArgumentException();
	  }	

     if (_data.get(video).numOut() < 1 ) {
		   	throw new IllegalArgumentException("There are no videos checked out");
	 }
	  Record replacement = new RecordObj(video, _data.get(video).numOwned(), _data.get(video).numOut()- 1, _data.get(video).numRentals());
	  _data.replace(video, replacement);
  }
  
  /**
   * Remove all records from the inventory.
   */
  void clear() {
	 _data.clear();
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("Database:\n");
    for (Record r : _data.values()) {
      buffer.append("  ");
      buffer.append(r);
      buffer.append("\n");
    }
    return buffer.toString();
  }
  /**
   * Implementation of Record interface.
   *
   * <p>This is a utility class for Inventory.  Fields are mutable and
   * package-private.</p>
   *
   * <p><b>Class Invariant:</b> No two instances may reference the same Video.</p>
   *
   * @see Record
   */
  private static final class RecordObj implements Record {
    Video video; // the video
    int numOwned;   // copies owned
    int numOut;     // copies currently rented
    int numRentals; // total times video has been rented
    
    RecordObj(Video video, int numOwned, int numOut, int numRentals) {
      this.video = video;
      this.numOwned = numOwned;
      this.numOut = numOut;
      this.numRentals = numRentals;
    }
    public Video video() {
      return video;
    }
    public int numOwned() {
      return numOwned;
    }
    public int numOut() {
      return numOut;
    }
    public int numRentals() {
      return numRentals;
    }
    public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(video);
      buffer.append(" [");
      buffer.append(numOwned);
      buffer.append(",");
      buffer.append(numOut);
      buffer.append(",");
      buffer.append(numRentals);
      buffer.append("]");
      return buffer.toString();
    }
  }
}

