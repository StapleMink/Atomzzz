import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ErrorPanel extends JPanel implements ActionListener
{
	private final JFrame frame;
	private final boolean[] errors;
	private final int lives;
	Scanner input;

	public ErrorPanel(boolean[] errorsIn, int livesIn)
	{
		errors = errorsIn;
		lives = livesIn;
		frame = new JFrame();
		frame.setSize(800, 450);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(this);
		frame.setVisible(true);
		// input = Utilities.loadText("errors.txt");
	}

	@Override
	public void paintComponent(Graphics g)
	{

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

}
