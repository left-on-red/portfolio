public class Student {
	private String name;
	private double totalScore;
	private int numberTook;
	
	public Student(String studentName) {
		name = studentName;
		totalScore = 0;
		numberTook = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public double getTotalScore() {
		return totalScore;
	}
	
	public double getAverageScore() {
		return totalScore / numberTook;
	}
	
	public void addQuiz(float score) {
		totalScore += score;
		numberTook += 1;
	}
}
