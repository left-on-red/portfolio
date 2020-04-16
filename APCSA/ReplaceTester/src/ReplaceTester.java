public class ReplaceTester {
	public static void main(String[] args) {
		String word = "Mississippi";
		System.out.println("expected: M!$$!$$!pp!");
		String finished = word.replace('i', '!').replace('s', '$');
		System.out.println("actual: " + finished);
	}

}