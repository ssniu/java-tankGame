package com.tank5;

public class Demo23 {

	public static void main(String[] args) {
		Cat c1 = new Cat();
		c1.start();
		
		Dog d1 = new Dog();
		Thread t1 = new Thread(d1);
		t1.start();
		

	}

}

class Cat extends Thread{
	public void run(){
		System.out.println("11");
	}
}

 class Dog implements Runnable{

	@Override
	public void run() {
		System.out.println("22");
		
	}
	
}
