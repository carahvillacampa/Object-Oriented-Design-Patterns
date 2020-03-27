package hw1;

// TODO:  complete the methods
/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * @objecttype Immutable Data Class
 * @objectinvariant
 *   Title is non-null, no leading or final spaces, not empty string.
 * @objectinvariant
 *   Year is greater than 1800, less than 5000.
 * @objectinvariant
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable {
  private final String _title;
  /** @invariant greater than 1800, less than 5000 */
  private final int    _year;
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if any object invariant is violated.
   */
  VideoObj(String title, int year, String director) {
	  
	//if it satisfies the invariants, initialize and trim 
	  if(title == null || !isValidTitle(title)) {
		  throw new IllegalArgumentException("Invalid entry for title");
	  } 
	  else {
		  _title= title.trim();
	  }
	  
	  if(!(isValidYear(year))) {
		  throw new IllegalArgumentException("Invalid year: has to be >1800 and < 5000");
	  }
	  else {
		  _year= year;
	  }
	  
	  if(director == null || !isValidDirector(director)) {
		  throw new IllegalArgumentException("Invalid entry for director"); 
	  }
	  else {
		  _director= director.trim();   
	  }
	   
  }

  /**
   * Return the value of the attribute.
   */
  public String director() {
    return this._director;
  }

  /**
   * Return the value of the attribute.
   */
  public String title() {
    return this._title;
  }

  /**
   * Return the value of the attribute.
   */
  public int year() {
    return this._year;
  }
  
 
  public boolean isValidYear(int y) {
	if(y> 1800 && y< 5000) {
	    return true;
	}
	return false;
  }
  
  //invariant check helper
  public boolean isValidTitle(String t) {
	  if(!(t.isBlank()) && ! (t.isEmpty())) { 
		  return true;
	  }
	  return false;
  }
  
  //invariant check helper
  public boolean isValidDirector(String d) {
	 if(!(d.isBlank()) &&!(d.isEmpty())) {return true; }
	 return false;
	 
  }
  
  public boolean hasWhiteSpaceChar(String x) {
	  if (x== null) { return false;}
	  for(int i=0; i < x.length(); i++) {
		  if(Character.isWhitespace(x.charAt(i))) {
			  return true;
		  }
	  }
	  return false;
  }
  /**
   * Compare the attributes of this object with those of thatObject.
   * @param thatObject the Object to be compared.
   * @return deep equality test between this and thatObject.
   */
  public boolean equals(Object thatObject) {
	VideoObj o= (VideoObj) thatObject;
	if(thatObject== null) { return false;}
	if(!(thatObject instanceof VideoObj)) {return false;}
    return this._title.compareTo(o._title)==0 &&
    		Integer.compare(this._year, o._year)==0 && 
    		this._director.compareTo(o._director)==0 ;
	
  }

  /**
   * Return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  private int starter= 0;
  public int hashCode() {
	if(starter==0) {
		starter=17;
		starter= 37 * starter+ _title.hashCode(); //((_title== null) ? 0 : _title.hashCode())
		starter= 37 * starter+ Integer.valueOf(_year).hashCode();
		starter= 37 * starter+ _director.hashCode(); //((_director== null) ? 0 : _director.hashCode())
	}
    return starter;
  }

  /**
   * Compares the attributes of this object with those of thatObject, in
   * the following order: title, year, director.
   * @param thatObject the Object to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *  object is less than, equal to, or greater thatObject.
   * @throws ClassCastException if thatObject has an incompatible type.
   */
  public int compareTo(Object thatObject) {
	if(!(thatObject instanceof VideoObj)) { throw new ClassCastException();}
	//cast thatObject to VideoObj and use as ref
    VideoObj obj1= (VideoObj) thatObject; 
    
	if( this._title.compareTo(obj1._title)!=0) { return -1;}
	if( Integer.compare(this._year, obj1._year)!=0 ) { return -1;}
	if( this._director.compareTo(obj1._director)!=0) { return -1;}
	
	return 0;
    
  }

  /**
   * Return a string representation of the object in the following format:
   * <code>"title (year) : director"</code>.
   * 
   */
  public String toString() { 
	  return String.format("%s (%d): %s", _title, _year, _director);
  }
  
  
}
