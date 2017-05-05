/* Gaurav Datta
 * 4/28/17
 * GameRunner.java
 * This is the class that has the JFrame and is run first, with other panels being added 
 *  to it later
 */


import javax.swing.JFrame;

public class GameRunner
{
	private final JFrame frame; // JFrame and panels that control gameplay
	private final GamePanel gp;

	public GameRunner() // constructor: instantiate fields
	{
		frame = new JFrame("");
		frame.setSize(800, 450);
		frame.setLocation(0, 0);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		gp = new GamePanel();
		frame.setContentPane(gp);
	}

	public static void main(String[] args) // main() call constructor
	{
		GameRunner game = new GameRunner();

	}

}
