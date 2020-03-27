package basics.inner.four;
public class Main {
	private Main() {}
	static public void main (final String[] args) {
		//stdlib.Trace.graphvizShowSteps (true); stdlib.Trace.run ();
		C mc1 = new C(42);
		C mc2 = new C(36);
		mc1.f();
		mc2.f();
	}
}

abstract class Print {
	int py;
	Print(int y) { py = y; }
	abstract void print();
}

class C {
	int cx;
	C(int x) { cx = x; }
	void f() {
		Print p = new Print(27) {
			public void print() {
				System.out.println(" cx=" + cx + " py=" + py);
			}
		};
		p.print();
	}
}
