package hw6Files.src.src.source.shop.data;

import hw6Files.src.src.source.shop.data.VideoObj;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
	if (title == null || title.isBlank() || title.isEmpty()) {throw new IllegalArgumentException("title input is null, title is blank or empty");}
	else {_title = title;}
	
	if (director == null || director.isEmpty() || director.isBlank()) {throw new IllegalArgumentException("director input is null or is empty or is blank");}
	else {_director = director;}
	
	if (year <= 1800 || year >= 5000) {throw new IllegalArgumentException("Invalid year input");}
	else {_year = year;}

  }

  public String director() { 
    return _director;
  }

  public String title() {  
    return _title;
  }

  public int year() {
    return _year;
  }

  public boolean equals(Object thatObject) {
	  if (thatObject == null) return false;
	  if (!(thatObject instanceof VideoObj)) return false;
	  if (thatObject.toString().trim() == this.toString().trim()) return true;
	  if (thatObject.hashCode() == this.hashCode()) return true;
	  
	  VideoObj v = (VideoObj) thatObject;
	  return (this._title.toString().trim().compareTo(v._title.toString().trim()) == 0) &&
			  (this._director.toString().trim().compareTo(v._director.toString().trim()) == 0) &&
			  (Integer.compare(this._year, v._year) == 0);
  }

  public int hashCode() {
	  int res= 17;
	  res = 37 * res + this._title.hashCode();
	  res = 37 * res + this._year;
	  res = 37 * res + this._director.hashCode();
	  return res;
  }

  public int compareTo(Object thatObject) {
	  VideoObj v = (VideoObj) thatObject;
	  
	  //check invariants
	  if (thatObject == null && this != null)
		  throw new NullPointerException();
	  if (!(thatObject instanceof VideoObj))
		  throw new ClassCastException("Object is incompatible type");
	  
	  // Compare titles
	  int T = this._title.compareTo(v._title);
	  if (T != 0) return T;
	  
	  // Compare years
	  int Y = this._year - v._year;
	  if (Y != 0) return Y;
	  
	  // Compare directors
	  int D = this._director.compareTo(v._director);
	  if (D != 0) return D;
	  
	  //all fields are equal
      return 0; 
  }

  public String toString() {
	  String str= String.format("%s (%d) : %s", _title.trim(), _year, _director.trim());
	  return str;
  }
}
