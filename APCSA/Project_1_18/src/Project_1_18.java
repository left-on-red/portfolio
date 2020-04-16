import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Project_1_18 {
	public static void main(String[] args) throws Exception {
		URL imageLocation = new URL("https://media.istockphoto.com/photos/greeting-gesture-picture-id518117722?k=6&m=518117722&s=612x612&w=0&h=EJyuoeBiFzvJa-juxBPJIe05pqx6EB8jLjxx1d1K9GI=");
		JOptionPane.showMessageDialog(null, "Hello World", "Title", JOptionPane.PLAIN_MESSAGE, new ImageIcon(imageLocation));

	}

}
