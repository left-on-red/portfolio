public class HollePrinter {
	public static void main(String[] args) {
		String word = "Hello, World!";
		System.out.println("expected: Holle, Werld!");
		String finished = word.replace('e', '#').replace('o', 'e').replace('#', 'o');
		System.out.println("actual: " + finished);
	}
}