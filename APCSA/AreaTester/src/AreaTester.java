import java.awt.Rectangle;

public class AreaTester {
	public static void main(String[] args) {
		Rectangle rect = new Rectangle(5, 10, 20, 30);
		System.out.println("expected: 600");
		double area = rect.getWidth() * rect.getHeight();
		System.out.println("actual: " + area);
	}

}