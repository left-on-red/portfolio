package unitConversions;

import java.util.Scanner;

public class unitConversions {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("convert from: ");
		String from = scan.nextLine();
		System.out.println("convert to: ");
		String to = scan.nextLine();
		System.out.println("value: ");
		double value = scan.nextDouble();
		scan.close();
		
		
		String[] metric = { "l", "g", "m" };
		//String[] metricPrefix = { "m", "c", "d", "", "da", "h", "k" };
		String[] metricPrefix = { "k", "h", "da", "", "d", "c", "m" };
		
		String[][] imperial = { { "fl oz", "gal" },
								{ "oz", "lb" },
								{ "in", "ft", "mi" } };
		
		double[][] conversions = { { 0.0295735, 3.78541 },
									{ 28.3495, 453.592 },
									{ 0.0254, 0.3048, 1609.344 } };
		
		String metricUnit = to.substring(to.length() - 1);
		
		int[] position = {0, 0};
		
		boolean error = true;
		for (int x = 0; x < imperial.length; x++) {
			for (int y = 0; y < imperial[x].length; y++) {
				if (imperial[x][y].equals(from)) {
					if (metric[x].equals(metricUnit)) {
						error = false;
						position[0] = x;
						position[1] = y;
					}
				}
			}
		}
		
		if (!error) {
			
			String prefix = to.substring(0, 1);
			int center = 0;
			int index = 3;
			int offset = 0;
			boolean hasPrefix = !prefix.equals("");
			for (int c = 0; c < metricPrefix.length; c++) {
				if (metricPrefix[c].equals("")) {
					center = c;
				}
				
				else if (metricPrefix[c].equals(prefix)) {
					index = c;
				}
			}
			
			if (hasPrefix) {
				offset = index - center;
			}
			
			else {
				offset = 0;
			}
			
			//System.out.println(index - center);
			
			double out = (value * (conversions[position[0]][position[1]] * (Math.pow(10.0, offset))));
			System.out.println(value + from + " = " + out + to);
		}
		
		else {
			System.out.println("an error occured!");
		}
	}
}
