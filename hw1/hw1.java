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
			
			
		}
		Triangle x = new Triangle("S");
	}
}

