import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sun.javadoc.Doc;

import factory.shape3.Ellipse;
import factory.shape3.Rectangle;
import factory.shape3.Shape;

public class FinalEXAM {
	
	//Question 1
	class DocList implements Doc {
		String _s;
		int sizeOf;
		List<Doc> _l = new LinkedList();
		public int size() {
			return _l.size()+1;
			int count = 0;
			for (Doc d: _l) {
				int x = d.count();
				count += x;
			}
			return count + 1;
		}
		
		
	//Question 2
		class NonNegativeInteger {
			public NonNegativeInteger() {};
			public boolean equals (Object that) {};
			public void set(int v) throws IllegalArgumentException{};
			public int get() {};
		}
		
		/**
		 * if set has not been called, get should return 0
		if set has been called, get should return the value of the last set
				 if set is called with a negative number, the IllegalArgumentException is
				raised
		 */
		
		public void testNonNegativeInteger() {
			//invariant 1
			NonNegativeInteger int1= new NonNegativeInteger();
			NonNegativeInteger int2= new NonNegativeInteger();
			assertEquals(int1.get(), 0);
			int1.set(4);
			assertEquals(4,int1.get());
			try {
				int1.set(-1);
				assertThrows(IllegalArgumentException.class, ()->{int1.set(-1);});
				fail();
			}
			catch(IllegalArgumentException e ) {}
			
			int1.set(6);
			assertEquals(6,int1.get());
			assertFalse(int1.equals(int2));
			
			int2.set(6);
			assertTrue(int1.equals(int2));
			assertFalse(int1.equals(null));
			assertTrue(int1.equals(new NonNegativeInteger()));
			
			
		}
		
		//Question 3: implement the shape interface
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

		//implement shapes in shape facotry
		class ShapeFactory{
			public Shape newInstance (String selector) {
				if ("Rectangle".equals(selector)) return new Rectangle();
				if ("Circle".equals(selector))    return new Circle();
				if ("Translate".equals(selector)) return new Translate();
				if ("ComplexShape".equals(selector))   return new ComplexShape();
				throw new IllegalArgumentException();
			}
		}	
			
			
		}
	
	//QUESTION 4: implementation of shape and draw methods in complex shape
		private static ArrayList _shapes = new ArrayList();
		public void draw(Color c) {
		for(int i=0; i< _shapes.size() ;i ++){
				Shape got= (Shape) _shapes.get(i);
			  	got.draw(c);
			}
		}
		public static void addShape(Shape s) {
			_shapes.add(s);
		}
		
	//QUESTION 5: implementation of the Draw method in translate
		
		class Translate1 implements Shape{
			private int _x, _y;
			private Shape _s;
			public void setXY(int x, int y) { _x = x; _y = y; }
			public void setShape(Shape s) { _s = s; }
			public int getX() { return _x; }
			public int getY() { return _y; }
			public Shape getShape() { return _s; }
			public Object clone() { return null; }
			public boolean equals(Object o) { return false; }
			public void accept(ShapeVisitor v) { v.visitTranslate(_x,_y,_s); }
			
    			public void draw(Color c) {
    				BasicGLib.clear();
    				BasicGLib.moveCursor(_x*-1, _y*-1);
    				_s.draw(c);
    				BasicGLib.moveCursor(this.getX(), this.getY());
    				BasicGLib.clear();
				}
			
		}
		
	//6:One word answer for the design pattern
		/*
		In a multi-player game, each of the players take turns to make a move.
		Their moves are recorded on a central game board.
		All players can see all of the moves made by the other players.
	
		pattern: OBSERVER PATTERN
		*/
		
	//7:
		/*
		 pattern: TEMPLATE PATTERN
		 */
		
	//8:
		/*
		 
		 pattern: 
		 */
	public interface CurrentInterface{
		public void func();
	}
	public interface ZInterface {
		public void newFunction();
	}
	
	public class currentClass{
		public void func() {
			System.out.println("OLD function applied");
		}
	}
	public class currentZClass implements ZInterface{

		@Override
		public void newFunction() {
			// TODO Auto-generated method stub
			System.out.println("NEW function applied");
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
