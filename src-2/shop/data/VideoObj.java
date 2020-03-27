package shop.data;

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
   */
  VideoObj(String title, int year, String director) {
	if (title == null || title.isBlank() || title.isEmpty())
			throw new IllegalArgumentException("Invalid title input/s");
	if (director == null || director.isEmpty() || director.isBlank())
			throw new IllegalArgumentException("Invalid director input/s");
	if (year <= 1800 || year >= 5000)
		throw new IllegalArgumentException("Invalid year input");
	
   _title = title;
   _director = director;
   _year = year;
	  
	  
	  /*
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
	  */
  }
  
  public boolean isValidYear(int y) {
	if(y> 1800 && y< 5000) {
	    return true;
	}
	return false;
  }
  
  //invariant check helper
  public boolean isValidTitle(String t) {
	  if(t!="" && t!=" ") { 
		  return true;
	  }
	  return false;
  }
  
  //invariant check helper
  public boolean isValidDirector(String d) {
	 if(!(d.isBlank()) &&!(d.isEmpty())) {return true; }
	 return false;
	 
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

  public boolean equals(VideoObj thatObject) {
	  if (thatObject == null) return false;
	  if (!(thatObject instanceof VideoObj)) return false;
	  if (thatObject.toString().trim() == this.toString().trim()) return true;
	  if (thatObject.hashCode() == this.hashCode()) return true;
	  
	  VideoObj v = (VideoObj) thatObject;
	  return (this._title.toString().trim().compareTo(v._title.toString().trim()) == 0) &&
			  (this._director.toString().trim().compareTo(v._director.toString().trim()) == 0) &&
			  (Integer.compare(this._year, v._year) == 0);
  }
  
  //private int starter=0;
  public int hashCode() {
	  int res= 17;
	  res = 37 * res + this._title.hashCode();
	  res = 37 * res + this._year;
	  res = 37 * res + this._director.hashCode();
	  return res;
  }

  @Override
  public int compareTo(Object thatObject) {
	  VideoObj v = (VideoObj) thatObject;
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
	  
      return 0; //All fields are equal
  }

  public String toString() {
	 return String.format("%s (%d) : %s", _title, _year, _director);
  }
  
  public static void main (String ars[]) {
	 
  }

}
