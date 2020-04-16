// LetterO.java

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class LetterO {
	private int xLeft;
	private int yTop;
	
	public LetterO(int x, int y) {
		xLeft = x;
		yTop = y;
	}
	
	public void draw(Graphics2D g2) {
		Ellipse2D circle1 = new Ellipse2D.Double(xLeft, yTop + 20, 160, 160);
		
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(40));
		
		g2.draw(circle1);
	}
}