package com.dynamicBinding;

class Animal {
	void bark() {
		System.out.println("Animal barking");
	}
}

class Dog extends Animal {
	@Override
	void bark() {
		System.out.println("Dog barking");
	}
}

public class DynamicBinding {
	public static void main(String[] args) {
		Animal a = new Dog(); // Reference type: Animal, Object type: Dog
		a.bark(); // Which bark() is called?
	}
}
