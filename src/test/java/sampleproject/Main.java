package sampleproject;

import sampleproject.subpackage.Foo;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		if (new Foo().isUserAGoat()) {
			System.out.println("user is a goat");
		} else {
			System.out.println("user is no goat");
		}
	}
}
