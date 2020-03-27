package types.multipleInheritance2;

/* 
 * The "diamond of death" is not a problem in java, since multiple inheritance
 * is only possible for interfaces, and interfaces use "virtual inheritance" and
 * does not allow interfaces to have fields (no multiple-inheritance of state)
 * 
 * See
 * http://stackoverflow.com/questions/137282/how-can-you-avoid-the-diamond-of-death-in-c-when-using-multiple-inheritance
 * http://docs.oracle.com/javase/tutorial/java/IandI/multipleinheritance.html
 */
interface I0 {
	public void f ();
}
interface I1 extends I0 { 
	default public void g () { System.out.println ("I1.g"); }
}
interface I2 extends I0 {
	default public void g () { System.out.println ("I2.g"); }
}

/* It is possible to inherit conflicting defaults, as long as you don't use them!
 * If you comment out the definition of g in C or K below, you will get an error!
 */
class C implements I1, I2 { 
	public void f () { System.out.println ("C.f"); } 
	public void g () { System.out.println ("C.g"); } 
}
interface K extends I1, I2 { 
	default public void g () { System.out.println ("I2.g"); }
}
public class Main {
	public static void main (String[] args) {
		C x = new C ();
		x.g ();
	}
}
