// LetterE.java

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class LetterE {
	private int xLeft;
	private int yTop;
	
	public LetterE(int x, int y) {
		xLeft = x;
		yTop = y;
	}
	
	public void draw(Graphics2D g2) {
		Rectangle rect1 = new Rectangle(xLeft, yTop, 40, 200);
		Rectangle rect2 = new Rectangle(xLeft + 40, yTop, 100, 40);
		Rectangle rect3 = new Rectangle(xLeft + 40, yTop + 80, 80, 40);
		Rectangle rect4 = new Rectangle(xLeft + 40, yTop + 160, 100, 40);
				
		g2.setColor(Color.RED);
		
		g2.fill(rect1);
		g2.fill(rect2);
		g2.fill(rect3);
		g2.fill(rect4);
		
		g2.draw(rect1);
		g2.draw(rect2);
		g2.draw(rect3);
		g2.draw(rect4);
	}
}