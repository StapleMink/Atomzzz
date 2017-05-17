/*Gaurav Datta
 * 5/1/17
 * Atomzzz.java
 * This is the file that runs first, creating a JFrame in which MenuPanel is added
 */

import javax.swing.JFrame;

public class Atomzzz
{
	private final JFrame frame;
	private final MainPanel mp;
	StartPanel sp;
	GamePanel gp;

	public static void main(String[] args)
	{
		new Atomzzz();
	}

	public Atomzzz() // This is the CONSTRUCTOR method
	{
		// The entry point into your program
		frame = new JFrame("");
		frame.setSize(1600, 900); // Set the size of the JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		mp = new MainPanel();
		frame.getContentPane().add(mp);
		frame.setVisible(true); // Make JFrame visible
	}
}