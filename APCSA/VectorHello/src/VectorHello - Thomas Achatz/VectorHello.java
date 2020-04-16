// VectorHello.java

import javax.swing.JFrame;

public class VectorHello {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LetterComponent component = new LetterComponent();
		frame.add(component);
		
		frame.setVisible(true);
	}
}