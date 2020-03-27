package factory.shape3;
public class ShapeFactory {
	private ShapeFactory() {}
	/**
	 * method newInstance return new obj instances of the classes that implement the shape interface
	 * @param selector
	 * @return
	 */
	static public Shape newInstance(String selector) {
		if ("Ellipse".equals(selector))   return new Ellipse();
		if ("Circle".equals(selector))    return new Ellipse();
		if ("Rectangle".equals(selector)) return new Rectangle();
		if ("Square".equals(selector))    return new Rectangle();
		throw new IllegalArgumentException();
	}
}
