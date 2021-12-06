package sampleproject.subpackage;

public class Foo {

	private final int ohNo = 5;

	public boolean isUserAGoat() {
		new Nested().foo();
		System.out.println(this.ohNo);
		return false;
	}

	private class Nested {
		public void foo() {
			System.out.println("Foo!");
		}
	}
}
