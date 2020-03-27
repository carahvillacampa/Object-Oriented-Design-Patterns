package types.casting;

interface Animal { default public String f () { return "Animal"; } }
interface Fish extends Animal { default public String f () { return "Fish"; } }
interface Bird extends Animal { default public String f () { return "Bird"; } }
class Cod implements Fish { public String f () { return "Cod"; } }
class Owl implements Bird { public String f () { return "Owl"; } }

public class Main {
	private Main() {}
	public static void main(String[] args) {
		Cod cod = new Cod ();
		Owl owl = new Owl ();
		
		/* Implicit upcasts always compile and run ok */
		Animal aCod = cod;
		Animal aOwl = owl;
		
		/* Explicit upcast  causes an unnecessary cast warning */
		//Animal aCod2 = (Animal) cod;

		/* Explicit downcasts always compile, but may cause runtime errors */
		//Fish f1 = aCod;        // implicit downcast: compiler error
		//Fish f2 = (Fish) aOwl; // explicit downcast: runtime error
		Fish f3 = (Fish) aCod;   // explicit downcast: runtime success

		/* Crosscasting to a class is disallowed by the compiler */
		//Cod f4 = (Cod) owl;          // crosscast: compiler error
		
		/* Crosscast can be replaced by upcast+downcast */
		//Cod f5 = (Cod) (Animal) owl; // upcast+downcast: runtime error

		/* Crosscasting to an interface is allowed by the compiler */
		//Fish f6 = (Fish) owl;        // crosscast: runtime error
		
		/* Casting changes the declared type, but not the actual type */
		System.out.println("Animal: " + aCod.f());
		System.out.println("Fish:   " + f3.f());
		System.out.println("Cod:    " + cod.f());
	}
}