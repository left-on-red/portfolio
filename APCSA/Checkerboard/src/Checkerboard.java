import java.awt.*;
import javax.swing.*;

public class Checkerboard {
	public static void main(String[] args) {
		JFrame checkerboard = new JFrame();
        checkerboard.setSize(400,400);
        checkerboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int rows = 8;
        int cols = 8;
        
        Container pane = checkerboard.getContentPane();
        pane.setLayout(new GridLayout(rows , cols));
     	
        Color checker;
           
        for (int x = 1; x <= (rows * cols); x++) {
        	int alt = 0;
        	alt = (x - 1) % cols;
        	alt += (x - 1) / cols;
           
        	if (alt % 2 == 0) {
        		checker = Color.white;
        	}
           
        	else {
        		checker = Color.black;
        	}
           
        	JPanel panel = new JPanel();
        	panel.setPreferredSize(new Dimension(400 / rows, 400 / cols));
        	panel.setBackground(checker);
        	pane.add(panel);
        }
        checkerboard.setVisible(true);
	}

}
