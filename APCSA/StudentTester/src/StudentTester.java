public class StudentTester {
	public static void main(String[] args) {
		Student jeff = new Student("Jeff");
		jeff.addQuiz(55);
		jeff.addQuiz(77);
		jeff.addQuiz(21);
		
		System.out.println(jeff.getName() + "\n----");
		System.out.println("total: " + jeff.getTotalScore());
		System.out.println("average: " + jeff.getAverageScore());
	}

}
