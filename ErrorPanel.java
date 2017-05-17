/* Gaurav Datta
 * 5/12/17
 * ErrorPanel.java
 * This is the panel that shows the errors that the user made, displayed after they lose
 * all three lives or hit the complete button. It uses a text file to write the message and
 * allows the user to retry the level if they failed, move to the next level if they passed,
 * or return to the title screen
 */

//imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ErrorPanel extends JPanel implements ActionListener
{
	// from GamePanel
	private final boolean[] errors;
	private final int lives;
	private final boolean passed;
	private final int level;

	// Scanner to read files
	private final Scanner input;

	// JTextAreas and JLabels that will hold messages
	private final JLabel mainMsg;
	private final JTextArea[] errorMsgs;
	private final JLabel passMsg;

	// MainPanel to allow changing cards
	private final MainPanel main;

	// JButtons that allow the user to navigate
	private JButton retry;
	private JButton next;
	private final JButton quit;

	// constructor initializes fields
	public ErrorPanel(boolean[] errorsIn, int livesIn, boolean passedIn, MainPanel mainIn, int levelIn,
			GamePanel gameIn)
	{
		setLayout(null);

		errors = errorsIn;
		lives = livesIn;
		input = Utilities.loadText("Errors.txt");
		passed = passedIn;
		errorMsgs = new JTextArea[5];
		for (int i = 0; i < 5; i++)
		{
			errorMsgs[i] = new JTextArea();
			errorMsgs[i].setLineWrap(true);
			errorMsgs[i].setEditable(false);
			errorMsgs[i].setForeground(Color.CYAN);
			errorMsgs[i].setBackground(Color.BLACK);
			errorMsgs[i].setFont(new Font("Times New Roman", Font.PLAIN, 15));
			errorMsgs[i].setSize(450, 65);
		}

		passMsg = new JLabel();
		passMsg.setBackground(Color.BLACK);
		passMsg.setForeground(Color.CYAN);
		passMsg.setHorizontalAlignment(SwingConstants.CENTER);
		passMsg.setSize(300, 50);
		passMsg.setLocation(650, 25);
		passMsg.setFont(new Font("Times New Roman", Font.PLAIN, 45));

		mainMsg = new JLabel();
		mainMsg.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mainMsg.setHorizontalAlignment(SwingConstants.CENTER);
		mainMsg.setBackground(Color.BLACK);
		mainMsg.setForeground(Color.CYAN);
		mainMsg.setSize(200, 20);
		mainMsg.setLocation(700, 80);

		main = mainIn;
		level = levelIn;
		quit = new JButton("Quit");
		quit.addActionListener(this);
		quit.setSize(200, 100);
		quit.setLocation(50, 750);

		// if user passed level 5
		if ((level == 5) && passed)
		{
			passMsg.setText("Congratulations! You beat the game.");
			add(passMsg);
		}
		else
		{
			run();
		}

	}

	public void run()
	{

		// if user passed or failed level add appropriate buttons and messages
		if (passed)
		{
			passMsg.setText("You Passed");
			add(passMsg);
			next = new JButton("Next Level");
			next.setSize(200, 100);
			next.setLocation(1350, 750);
			retry = null;
			next.addActionListener(this);
		}

		if (!passed)
		{
			passMsg.setText("You Failed");
			add(passMsg);
			retry = new JButton("Retry");
			retry.setSize(200, 100);
			retry.setLocation(1350, 750);
			next = null;
			retry.addActionListener(this);
		}

		// if passed w/o errors appropriate message is displayed
		if (lives == 3)
		{

			mainMsg.setText("You didn’t make any errors.");
			add(mainMsg);

		}

		// else print appropriate message
		else if (lives < 3)
		{
			mainMsg.setText("Here are the errors you made:");
			add(mainMsg);

			// set text in JTextAreas if errors wre mae
			if (errors[0])
			{
				errorMsgs[0].setText(
						"You violated Pauli’s Exclusion Principle by placing two electrons with the same spin in one orbital."
								+ " Remember that electrons in the same orbital must have different spins.");
			}

			if (errors[1])
			{
				errorMsgs[1].setText(
						"You violated the Afbau Principle by placing an electron in a higher energy orbital when there was "
								+ "still space in a lower energy orbital. "
								+ "Remember to completely fill orbitals before moving to one of higher energy.");
			}

			if (errors[2])
			{
				errorMsgs[2].setText(
						"You violated Hund’s rule by not placing electrons into orbitals of the same energy so that they are "
								+ "alone with the same spin before pairing them up.");
			}

			if (errors[3])
			{
				errorMsgs[3].setText("You placed too many electrons around this atom. "
						+ "Remember that an atom has the same number of electrons as its atomic number.");
			}

			if (errors[4])
			{
				errorMsgs[4].setText("You placed too few electrons around this atom. "
						+ "Remember that an atom has the same number of electrons as its atomic number.");
			}

			// display error messages
			int locationCount = 0;
			for (int i = 0; i < 5; i++)
			{
				if (errors[i])
				{
					errorMsgs[i].setLocation(600, 110 + (70 * locationCount));
					add(errorMsgs[i]);
					locationCount++;
				}
			}

		}

		// add buttons appropriately
		if (next != null)

		{
			add(next);
		}
		else
		{
			add(retry);
		}

		add(quit);
	}

	// black background
	@Override
	public void paintComponent(Graphics g)
	{
		setBackground(Color.BLACK);
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();

		// return to title screen or exit if beat level 5
		if (command.equals("Quit"))
		{
			main.getCards().show(main, "start");
		}

		// change to next level
		if (command.equals("Next Level"))
		{
			main.setGame(level + 1);
			main.getCards().show(main, "game");
		}

		// same level
		if (command.equals("Retry"))
		{
			main.setGame(level);
			main.getCards().show(main, "game");
		}

	}

}