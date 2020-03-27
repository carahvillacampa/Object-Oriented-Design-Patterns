package basics.inner.five;
public class Main {
	private Main() {}
	static public void main (final String[] args) {
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
		Print p = () -> System.out.println(" cx=" + cx);
		p.print();
		
		G g = (int x) -> {
			cx = cx + x;
			cx = cx + x;
			cx = cx + x;
		};
		g.run(3);
	}
}

interface G { void run(int x); }
