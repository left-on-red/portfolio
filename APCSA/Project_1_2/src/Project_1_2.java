public class Project_1_2 {
	public static void main(String[] args) {
		int total = 0;
		int[] numbers = {1,2,3,4,5,6,7,8,9,10};
		for (int i = 0; i < numbers.length; i++) {
			total += numbers[i];
		}
		
		System.out.println(total);
	}
}