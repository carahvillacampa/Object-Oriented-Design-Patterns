package basics.objectclass;
// A static class
public class Main3 {
	private Main3() {}
	static public void main (String[] args) {
		//stdlib.Trace.graphvizShowSteps (true); stdlib.Trace.run ();
		Circle3[] list = new Circle3[3];
		for (int i = 0; i < list.length; i++)
			list[i] = new Circle3 (i*10);
		for (Circle3 c : list)
			System.out.println(c);
	}
}
// An object class
final class Circle3 extends Object {
	int radius;
	public Circle3(int radius) { this.radius = radius; }
	public String toString() { return "Circle(" + radius + ")"; }
}
