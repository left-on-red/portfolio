
public class Project_1_4 {
	public static void main(String[] args) {
		double balance = 1000;
		double interest = 0.05;
		
		balance += (balance * interest);
		balance += (balance * interest);
		balance += (balance * interest);
		
		System.out.println(balance);
	}
}
