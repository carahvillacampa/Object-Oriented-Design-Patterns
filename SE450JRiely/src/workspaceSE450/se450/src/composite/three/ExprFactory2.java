package composite.three;

public class ExprFactory2 {
	private ExprFactory2() {}
	static public Expr newConst(int v) {
		return new Const(v);
	}
	static public Expr newPlus(Expr l, Expr r) {
		return new PlusOp(l, r);
	}
//	static public Expr newMinus(Expr l, Expr r) {
//		return new BinOp(l, new OpSub(), r);
//	}
//	static public Expr newMult(Expr l, Expr r) {
//		return new BinOp(l, new OpMul(), r);
//	}
//	static public Expr newQuot(Expr l, Expr r) {
//		return new BinOp(l, new OpDiv(), r);
//	}

	private static final class Const implements Expr {
		private final int v;
		public Const(int v) {
			this.v = v;
		}
		public int eval() {
			return v;
		}
		public String toString() {
			return Integer.toString(v);
		}
	}

	private static abstract class BinOp implements Expr {
		private final Expr l;
		private final Expr r;
		private final String opString;
		public BinOp(Expr l, String opString, Expr r) {
			if ((l == null) || (r == null)) {
				throw new IllegalArgumentException();
			}
			this.l = l;
			this.r = r;
			this.opString = opString;
		}
		protected abstract int run (int x, int y);
		// to be template method, there must a template method
		// template method = method of abstract class, that calls an abstract method
		public int eval() {
			return this.run(l.eval(), r.eval());
		}
		public String toString() {
			return l.toString() + " " + r.toString() + " " + opString;
		}
	}
	private static final class PlusOp extends BinOp {
		public PlusOp(Expr l, Expr r) { super (l, "+", r); }
		public int run(int x, int y) { return x+y; }
	}
}

