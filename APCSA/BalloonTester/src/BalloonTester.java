public class BalloonTester {
	public static void main(String[] args) {
		Balloon balloon = new Balloon();
		balloon.inflate(5);
		System.out.println("volume: " + balloon.getVolume());
	}
}