package factory.shape4;
public class ShapeFactory {
	private ShapeFactory() {}
	/**
	 * This factory contains a bunch of methods that return classes that implement the shape interface
	 * @param radius1
	 * @param radius2
	 * @return
	 */
	static public Shape newEllipse(int radius1, int radius2) {
		return new Ellipse(radius1, radius2);
	}
	static public Shape newCircle(int radius) {
		return new Ellipse(radius, radius);
	}
	static public Shape newRectangle(int height, int width) {
		return new Rectangle(height, width);
	}
	static public Shape newSquare(int height) {
		return new Rectangle(height, height);
	}
}
