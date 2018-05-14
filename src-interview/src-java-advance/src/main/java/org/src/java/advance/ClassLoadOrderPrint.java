package org.src.java.advance;

public class ClassLoadOrderPrint {

	{
		System.out.println("Test");
	}
	
	static {
		System.out.println("static Test");
	}
	
	public static void main(String[] args) {
		System.out.println(new B());
		System.out.println(C.class);
	}
}

class C{
	{
		System.out.println("C");
	}
	
	static {
		System.out.println("static C");
	}
	
	public C(){
		System.out.println("new C");
	}
}

class A{
	{
		System.out.println("A");
	}
	
	static {
		System.out.println("static A");
	}
	
	public A(){
		System.out.println("new A");
	}
}

class B extends A{
	{
		System.out.println("B");
	}
	
	static {
		System.out.println("static B");
	}
	
	public B(){
		System.out.println("new B");
	}
}