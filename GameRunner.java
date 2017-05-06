import javax.swing.JFrame;

public class GameRunner
{
	private final JFrame frame;
	private final MainPanel mp;
	StartPanel sp;
	GamePanel gp;

	public static void main(String[] args)
	{
		new GameRunner();
	}

	public GameRunner() // This is the CONSTRUCTOR method
	{
		// The entry point into your program
		frame = new JFrame("Atomzzz Beta - Test Frame");
		frame.setSize(800, 450); // Set the size of the JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		mp = new MainPanel();
		frame.getContentPane().add(mp);
		frame.setVisible(true); // Make JFrame visible

		sp = new StartPanel(new MainPanel());
		gp = new GamePanel(new MainPanel());
		mp.add(sp, "start");
		mp.add(gp, "game");
	}
}