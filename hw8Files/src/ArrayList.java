import java.awt.Color;
import java.awt.Color;
import java.util.ArrayList;

class BasicGLib {
		/** draw a circle of color c with center at current cursor position
		* the radius of the circle is given by radius
		*/
		public static void drawCircle(Color c, int radius) {/*...*/}
		/** draw a rectangle of Color c
		* with lower left corner at current cursor position
		* the length of the rectangle along the x axis is given by xlength
		* the length of the rectangle along the y axis is given by ylength
		*/
		public static void drawRect(Color c, int xlength, int ylength) {/*...*/}
		/** move the cursor by coordinate (xcoord,ycoord) */
		public static void moveCursor(int xcoord, int ycoord) {/*...*/}
		/** clear the entire screen and set cursor position to (0,0) */
		public static void clear() {/*...*/}
}
	
	/*
		For example:
		BasicGLib.clear(); // initialize
		BasicGLib.drawCircle(Color.red, 3); // a red circle: radius 3, center (0,0)
		BasicGLib.drawRect(Color.blue, 3, 5); // a blue rectangle: (0,0),(3,0),(3,5),(0,5)
		BasicGLib.moveCursor(2, 2); // move cursor
		BasicGLib.drawCircle(Color.green, 3); // a green circle: radius 3, center (2,2)
		BasicGLib.drawRect(Color.pink, 3, 5); // a pink rectangle: (2,2),(5,2),(5,7),(2,7)
		BasicGLib.moveCursor(-2, -2); // move cursor back to (0,0)
	*/

interface Shape extends Cloneable {
	public void draw(Color c);
	public void accept(ShapeVisitor v);
	public Object clone();
}
interface ShapeVisitor {
	public void visitCircle(int r);
	public void visitRectangle(int x, int y);
	public void visitComplexShape(ArrayList shapes);
	public void visitTranslate(int x, int y, Shape s);
}
class Circle implements Shape {
	private int _r;
	public void setRadius(int r) { _r = r; }
	public int getRadius() { return _r; }
	public void draw(Color c) { BasicGLib.drawCircle(c, _r); }
	public Object clone() { return null; }
	public boolean equals(Object o) { return false; }
	public void accept(ShapeVisitor v) { v.visitCircle(_r); }
}
class Rectangle implements Shape {
	private int _x, _y;
	public void setXY(int x, int y) { _x = x; _y = y; }
	public int getX() { return _x; }
	public int getY() { return _y; }
	public void draw(Color c) { BasicGLib.drawRect(c, _x, _y); }
	public Object clone() { return null; }
	public boolean equals(Object o) { return false; }
	public void accept(ShapeVisitor v) { v.visitRectangle(_x,_y); }
}
class ComplexShape implements Shape {
	private ArrayList _shapes = new ArrayList();
	public void addShape(Shape s) { }
	public void draw(Color c) { }
	public Object clone() { return null; }
	public boolean equals(Object o) { return false; }
	public void accept(ShapeVisitor v) { v.visitComplexShape(_shapes); }
}
class Translate implements Shape{
	private int _x, _y;
	private Shape _s;
	public void setXY(int x, int y) { _x = x; _y = y; }
	public void setShape(Shape s) { _s = s; }
	public int getX() { return _x; }
	public int getY() { return _y; }
	public Shape getShape() { return _s; }
	public void draw(Color c) { }
	public Object clone() { return null; }
	public boolean equals(Object o) { return false; }
	public void accept(ShapeVisitor v) { v.visitTranslate(_x,_y,_s); }
}

/*
public class ArrayList { 
	public ArrayList() {
		int size() {};
		boolean isEmpty();
		public Object clone();
		public boolean equals(Object a);
		public Object get(int index);
		public boolean add(Object a);
		public void clear() {}
   } 
}

*/



