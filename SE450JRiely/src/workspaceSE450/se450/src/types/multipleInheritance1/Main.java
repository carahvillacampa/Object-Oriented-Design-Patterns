package types.multipleInheritance1;

/* 
 * Uncommenting h or g below causes interface K to fail to compile
 * "The default method h() inherited from I2 conflicts with another method inherited from I1"
 * 
 * Uncommenting toString below causes I1 to fail to compile, with error:
 * "A default method cannot override a method from java.lang.Object"
 */
interface I1 { 
	public void f ();
	default public void g () { System.out.println ("I1.g"); }
	//public void h ();
	//default public String toString () { return "I1"; }
}
interface I2 {
	public void f ();
	//default public void g () { System.out.println ("I2.g"); }
	default public void h () { System.out.println ("I2.h"); }
}

interface K extends I1, I2 { }

class C implements K { 
	public void f () { System.out.println ("C.f"); } 
}
public class Main {
	public static void main (String[] args) {
		C x = new C ();
		x.f ();
		x.g ();
		x.h ();
	}
}
