package hw.hw1;


public class hw1{
	public static void main(String[] args) {
		abstract class Shape{
			String name;
			public Shape(String name){
				this.name = name;
			}
			abstract void print();
			abstract void draw(); 
			abstract double area();
				
			
		}
		class Triangle extends Shape{
			private int base;
			private int height;
			public Triangle(String name, int base, int height) {
				super(name);
				this.base = base;
				this.height = height;
			}
			
			//tester functions
			void setHeight(int height) {
				this.height = height;
			}
			void setBase(int base) {
				this.base = base;
			}
			//
			
			@Override
			double area() {
				double x = Double.valueOf(base);
				double y = Double.valueOf(height);
				return x*y/2;
			}
			
			@Override
			void print() {
				System.out.println(name + "(" + base + ", " + height + ") : " + area() );
			}
			
			@Override
			void draw() {
				System.out.println("   *   ");
				System.out.println("  * *  ");
				System.out.println(" *   * ");
				System.out.println("*******");
			}
			
		}
		class Circle extends Shape{
			int radius;
			Circle(String name, int radius ){
				super(name);
				this.radius = radius;
			}
			//tester functions
			void setRadius(int r) {
				this.radius = r;
			}
			//
			
			@Override
			double area() {
				double x = Double.valueOf(radius);
				x = Math.PI*x*x;
				return x  = Math.round(x*100.0)/100.0;
			}
			
			@Override
			void print() {
				System.out.println(name + "(" + radius + ") : " + area());
			}
			
			@Override
			void draw() {
				System.out.println("    ***   ");
				System.out.println("  *     *  ");
				System.out.println(" *       * ");
				System.out.println(" *       * ");
				System.out.println("  *     *  ");
				System.out.println("    ***   ");
			}
			
		}
		
		class Square extends Shape {
			int length;
			public Square (String name, int length) {
				super(name);
				this.length = length;
			}
			
			//testerfunc
			void setLength(int x) {
				this.length = x;
			}
			//
			
			@Override
			void print() {
				System.out.println(name + "(" + length + ") : " + area());
			}
			@Override
			void draw() {
				System.out.println("******");
				System.out.println("*    *");
				System.out.println("*    *");
				System.out.println("******");
				
			}
			@Override
			double area() {
				double x = Double.valueOf(length);
				return x*x ;
			}
			
		}
		
		class Rectangle extends Square {
			int width; 
			
			public Rectangle(String name, int length, int width) {
				super(name, length);
				this.width = width;
			}
			
			//tester
			void setWidth(int width){
				this.width= width;
			}
			//
			
			@Override
			void print() {
				System.out.println(name + "(" + length + ", " +  width + ") : "  + area());
			}
			@Override
			void draw() {
				System.out.println("***");
				System.out.println("* *");
				System.out.println("* *");
				System.out.println("* *");
				System.out.println("***");
			}
			@Override
			double area() {
				return Double.valueOf(width)*Double.valueOf(length);
			}
		}
		
		Rectangle a = new Rectangle("First Rectangle",5,4);
//		a.setLength(4);
//		a.setWidth(5);
		a.print();
		a.draw();
		
		Square k = new Square("First Square",5);
//		k.setLength(5);
		k.print();
		k.draw();
		
		Circle x = new Circle ("First Circle",5);
//		x.setRadius(5);
		x.print();
		x.draw();
//		
		Circle y = new Circle ("Second Circle",4);
//		y.setRadius(4);
		y.print();
//		
		Triangle i = new Triangle("First triangle", 5,5);
//		i.setBase(5);
//		i.setHeight(5);
		i.print();
		i.draw();
		Triangle j = new Triangle("Second triangle",4,4);
//		j.setBase(4);
//		j.setHeight(4);
		j.print();
		j.draw();
		
		
	}
}

