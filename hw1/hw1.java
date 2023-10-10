package hw.hw1;


public class hw1{
	public static void main(String[] args) {
		class Shape{
			String name;
			public Shape(String name){
				this.name = name;
			}
			void print() {}
			void draw() {}
			double area() {
				return 0;
			}
			
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
		Triangle x = new Triangle("First triangle");
		x.setBase(5);
		x.setHeight(5);
		x.print();
		x.draw();
		Triangle y = new Triangle("Second triangle");
		y.setBase(4);
		y.setHeight(4);
		y.print();
		y.draw();
	}
}

