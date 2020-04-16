// LetterComponent.java

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class LetterComponent extends JComponent {
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		LetterH Letter1 = new LetterH(100, 100);
		LetterE Letter2 = new LetterE(300, 100);
		LetterL Letter3 = new LetterL(480, 100);
		LetterL Letter4 = new LetterL(640, 100);
		LetterO Letter5 = new LetterO(800, 100);
		
		Letter1.draw(g2);
		Letter2.draw(g2);
		Letter3.draw(g2);
		Letter4.draw(g2);
		Letter5.draw(g2);
	}
}