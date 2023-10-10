package hw.hw1;


public class hw1{
	public static void main(String[] args) {
		abstract class Shape{
			private String name;
			public Shape(String name){
				this.name = name;
			}
			String getName() {
				return name;
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
			
			int getHeight() {
				return height;
			}
			int getBase() {
				return base;
			}
			
			@Override
			double area() {
				double x = Double.valueOf(base);
				double y = Double.valueOf(height);
				return x*y/2;
			}
			
			@Override
			void print() {
				System.out.println(getName() + "(" + base + ", " + height + ") : " + area() );
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
			private int radius;
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
				System.out.println(getName() + "(" + radius + ") : " + area());
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
			private int length;
			public Square (String name, int length) {
				super(name);
				this.length = length;
			}
			
			//testerfunc
			void setLength(int x) {
				this.length = x;
			}
			//
			
			int getLength() {
				return length;
			}
			
			@Override
			void print() {
				System.out.println(getName() + "(" + length + ") : " + area());
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
			private int width; 
			
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
				System.out.println(getName() + "(" + getLength() + ", " +  width + ") : "  + area());
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
				return Double.valueOf(width)*Double.valueOf(getLength());
			}
		}
		
		class Picture{
			class ListNode{
				private ListNode nextNode = null;
				private Shape currentShape = null;
				
				public ListNode() {
					
				}
				
				void setNext(Shape sh){
					ListNode nextNode = new ListNode();
					nextNode.setCurrent(sh);
					this.nextNode = nextNode;
				}
				void setCurrent(Shape sh) {
					this.currentShape = sh;
				}
				ListNode getNext() {
					return nextNode;
				}
				Shape getCurrent() {
					return currentShape;
				}
				
			}
			
			ListNode head; 
			public Picture () {
				this.head = null;
			}
			void add(Shape sh) {
				if(head == null) {
					head = new ListNode();
					head.setCurrent(sh);
				}
				
				else {
					ListNode iterator = head;
					while(iterator.getNext() != null) {
						iterator = iterator.getNext();
					}
					iterator.setNext(sh);
				}
			}
			void printAll() {
				ListNode iterator = head;
				while(iterator != null) {
					iterator.getCurrent().print();
					iterator = iterator.getNext();
				}
			}
			
			void drawAll() {
				ListNode iterator = head;
				while(iterator != null) {
					iterator.getCurrent().draw();
					iterator = iterator.getNext();
				}
			}
			double totalArea() {
				ListNode iterator = head;
				double sum = 0;
				while(iterator != null) {
					sum += iterator.getCurrent().area();
					iterator = iterator.getNext();
				}
				return sum; 
			}
		}
		
//		Rectangle a = new Rectangle("First Rectangle",5,4);
//		a.setLength(4);
//		a.setWidth(5);
//		a.print();
//		a.draw();
//		
//		Square k = new Square("First Square",5);
		
//		Picture tester = new Picture();
//		tester.add(a);
//		tester.add(k);
//		tester.printAll();
//		tester.drawAll();
//		System.out.println(tester.totalArea());
		
////		k.setLength(5);
//		k.print();
//		k.draw();
//		
//		Circle x = new Circle ("First Circle",5);
////		x.setRadius(5);
//		x.print();
//		x.draw();
////		
//		Circle y = new Circle ("Second Circle",4);
////		y.setRadius(4);
//		y.print();
////		
//		Triangle i = new Triangle("First triangle", 5,5);
////		i.setBase(5);
////		i.setHeight(5);
//		i.print();
//		i.draw();
//		Triangle j = new Triangle("Second triangle",4,4);
////		j.setBase(4);
////		j.setHeight(4);
//		j.print();
//		j.draw();
		
		int x = Integer.valueOf(args[0]);
		System.out.println(x);
		int y = Integer.valueOf(args[1]);
		System.out.println(y);
		
		
	}
}

