// LetterH.java

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class LetterL {
	private int xLeft;
	private int yTop;
	
	public LetterL(int x, int y) {
		xLeft = x;
		yTop = y;
	}
	
	public void draw(Graphics2D g2) {
		Rectangle rect1 = new Rectangle(xLeft, yTop, 40, 200);
		Rectangle rect2 = new Rectangle(xLeft, yTop + 160, 120, 40);
				
		g2.setColor(Color.RED);
		
		g2.fill(rect1);
		g2.fill(rect2);
		
		g2.draw(rect1);
		g2.draw(rect2);
	}
}