import java.util.Random;

public class DieSimulator {
	public static void main(String[] args) {
		Random randomNumber = new Random();
		int next = randomNumber.nextInt(6);
		System.out.println(next + 1);
	}
}
