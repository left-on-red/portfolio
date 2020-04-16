import java.awt.Point;

public class PointTester {
	public static void main(String[] args) {
		Point pointOne = new Point(3, 4);
		Point pointTwo = new Point(-3, -4);
		
		System.out.println("starting point: (" + pointOne.getX() + ", " + pointOne.getY() + ")");
		System.out.println("ending point: (" + pointTwo.getX() + ", " + pointTwo.getY() + ")");
		
		double distance = Point.distance(pointOne.getX(), pointOne.getY(), pointTwo.getX(), pointTwo.getY());
		
		System.out.println("distance: " + distance);
	}
}
