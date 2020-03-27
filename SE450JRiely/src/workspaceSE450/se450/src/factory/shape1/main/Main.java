package factory.shape1.main;
import factory.shape1.Shape;
import factory.shape1.Square;
import factory.shape1.Circle;


/**
 * client code is the one creating new instances of the class
 * @author carahvillacampa
 *
 */
public class Main {
	public static void main (String[] args) {
		Shape[] a = new Shape[2];
		a[0] = new Circle();
		a[1] = new Square();
	}
}
