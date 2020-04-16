import java.lang.StringBuilder;

public class ReverseTester {
	public static void main(String[] args) {
		String text = "desserts";
		System.out.println("expected: stressed");
		StringBuilder finished = new StringBuilder(text);
		finished = finished.reverse();
		System.out.println("actual: " + finished);
	}
}