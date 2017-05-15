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
	private final boolean[] errors;
	private final int lives;
	private final Scanner input;
	private final JLabel mainMsg;
	private final JTextArea[] errorMsgs;
	private final JLabel passMsg;
	private final boolean passed;
	private final MainPanel main;

	private JButton retry;
	private JButton next;
	private final JButton quit;

	private final int level;

	private final GamePanel game;

	public ErrorPanel(boolean[] errorsIn, int livesIn, boolean passedIn, MainPanel mainIn, int levelIn,
			GamePanel gameIn)
	{
		setLayout(null);

		game = gameIn;

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
		passMsg.setLocation(250, 25);
		passMsg.setFont(new Font("Times New Roman", Font.PLAIN, 45));

		mainMsg = new JLabel();
		mainMsg.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mainMsg.setHorizontalAlignment(SwingConstants.CENTER);
		mainMsg.setBackground(Color.BLACK);
		mainMsg.setForeground(Color.CYAN);
		mainMsg.setSize(200, 20);
		mainMsg.setLocation(300, 80);

		main = mainIn;
		level = levelIn;
		quit = new JButton("Quit");
		quit.addActionListener(this);
		quit.setSize(100, 50);
		quit.setLocation(50, 300);

		run();

	}

	public void run()
	{
		String mainText = "";
		String getText = "";
		String errText = "";

		if (passed)
		{
			passMsg.setText("You Passed");
			add(passMsg);
			next = new JButton("Next Level");
			next.setSize(100, 50);
			next.setLocation(650, 300);
			retry = null;
			next.addActionListener(this);
		}

		if (!passed)
		{
			passMsg.setText("You Failed");
			add(passMsg);
			retry = new JButton("Retry");
			retry.setSize(100, 50);
			retry.setLocation(650, 300);
			next = null;
			retry.addActionListener(this);
		}

		if (lives == 3)
		{
			while (input.hasNext() && !getText.equals("Msg0"))
			{
				getText = input.next();
			}

			mainText = input.nextLine();

			mainText = mainText.trim();
			mainMsg.setText(mainText);
			add(mainMsg);

		}

		else if (lives < 3)
		{
			while (input.hasNext() && !getText.equals("Msg1"))
			{
				getText = input.next();
			}

			mainText = input.nextLine();

			mainText = mainText.trim();
			mainMsg.setText(mainText);
			add(mainMsg);

			int locationCount = 0;

			for (int i = 0; i < 5; i++)
			{
				if (errors[i])
				{
					while (input.hasNext() && !getText.equals("Err" + Integer.toString(i)))
					{
						getText = input.next();
					}

					errText = input.nextLine();

					errorMsgs[i].setLocation(200, 110 + (70 * locationCount));
					errText = errText.trim();
					errorMsgs[i].setText(errText);
					add(errorMsgs[i]);
					locationCount++;
				}
			}
		}

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

		if (command.equals("Quit"))
		{
			main.getCards().show(main, "start");
		}

		if (command.equals("Next Level"))
		{
			main.setGame(level + 1);
			main.getCards().show(main, "game");
		}

		if (command.equals("Retry"))
		{
			main.setGame(level);
			main.getCards().show(main, "game");
		}

	}

}
