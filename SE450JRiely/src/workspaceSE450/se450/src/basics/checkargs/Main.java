package basics.checkargs;
public class Main {
	private Main() {}
	static public void main (final String[] args) {
		//stdlib.Trace.graphvizShowSteps (true); stdlib.Trace.run ();
		try {
			System.out.println(new Person("bob"));
			System.out.println(new Person(null));
		} catch (IllegalArgumentException e) {
			System.out.println("Error creating Person: " + e); 
		}
	}
}
final class Person {
	final private String name;
	public Person(String name) {
		if (name == null)
			throw new IllegalArgumentException("null name");
		this.name = name;
	}
	public String toString() { return "Person(" + name + ")"; };
}
