package basics.inner.three;
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

interface Print { void print(); }

class C {
	int cx;
	C(int x) { cx = x; }
	void f() {
		Print p = new Print() {
			int py = 27;
			public void print() {
				System.out.println(" cx=" + C.this.cx + " py=" + this.py);
			}
		};
		p.print();
	}
}