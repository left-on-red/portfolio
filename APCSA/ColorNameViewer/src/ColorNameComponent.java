import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ColorNameComponent extends JComponent {
	private int xLeft;
	private int yTop;
	private Color color;
	
	public void ColorNameComponent(String colorStr, int x, int y) {
		// FINISH
		
		switch(colorStr) {
			case "black": color = Color.BLACK;
			case "blue": color = Color.BLUE;
			case "cyan": color = Color.CYAN;
			case "dark gray": color = Color.DARK_GRAY;
			case "gray": color = Color.GRAY;
			case "green": color = Color.GREEN;
			case "light gray": color = Color.LIGHT_GRAY;
			case "magenta": color = Color.MAGENTA;
			case "orange": color = Color.ORANGE;
			case "pink": color = Color.PINK;
			case "red": color = Color.RED;
			case "yellow": color = Color.YELLOW;
		}
	}
}