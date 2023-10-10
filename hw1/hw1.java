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
			public Triangle(String name) {
				super(name);
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
			Circle(String name){
				super(name);
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
		
//		class Square extends Shape {
//			int length;
//			public Square (String name) {
//				super(name);
//			}
//			
//			
//		}
		
		Circle x = new Circle ("First Circle");
		x.setRadius(5);
		x.print();
		x.draw();
		
		Circle y = new Circle ("Second Circle");
		y.setRadius(4);
		y.print();
		
		Triangle i = new Triangle("First triangle");
		i.setBase(5);
		i.setHeight(5);
		i.print();
		i.draw();
		Triangle j = new Triangle("Second triangle");
		j.setBase(4);
		j.setHeight(4);
		j.print();
		j.draw();
		
		
	}
}

