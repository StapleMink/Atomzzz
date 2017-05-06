import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener
{
	private final Orbital[] orbitals;
	private final Electron[] electrons;
	private final JButton complete;
	protected int lives, level, eAdded, eNeeded;
	protected boolean passed, selected;
	protected boolean[] errors;
	private Image nucleus;

	public GamePanel(MainPanel mp)
	{
		setLayout(null);
		setSize(800, 450);
		orbitals = new Orbital[18]; // instantiate orbitals[]
		orbitals[0] = new Orbital("1s");
		orbitals[1] = new Orbital("2s");
		for (int i = 2; i < 5; i++)
		{
			orbitals[i] = new Orbital("2p");
		}
		orbitals[5] = new Orbital("3s");
		for (int i = 6; i < 9; i++)
		{
			orbitals[i] = new Orbital("3p");
		}
		orbitals[9] = new Orbital("4s");
		for (int i = 10; i < 15; i++)
		{
			orbitals[i] = new Orbital("3d");
		}
		for (int i = 15; i < 18; i++)
		{
			orbitals[i] = new Orbital("4p");
		}

		complete = new JButton("Complete");

		orbitals[0].setLocation(360, 50);
		orbitals[1].setLocation(175, 90);

		for (int i = 2; i < 4; i++)
		{
			orbitals[i].setLocation(96 + ((i - 1) * 176), 130);
		}
		orbitals[4].setLocation(545, 90);
		/*
		 * for (int i = 0; i < 4; i++) { orbitals[i + 1].setLocation(96 + (i *
		 * 176), 130); orbitals[i + 5].setLocation(96 + (i * 176), 200); }
		 */

		for (int i = 0; i < 9; i++)
		{
			orbitals[i + 9].setLocation(8 + (88 * i), 270);
		}

		electrons = new Electron[36];
		for (int i = 0; i < 18; i++)
		{
			electrons[i] = new Electron(true, orbitals);
			electrons[i].setLocation(13 + (43 * i), 330);
			electrons[i].origX = electrons[i].getX();
			electrons[i].origY = electrons[i].getY();
			add(electrons[i]);
		}

		for (int i = 0; i < 18; i++)
		{
			electrons[i + 18] = new Electron(false, orbitals);
			electrons[i + 18].setLocation(13 + (43 * i), 390);
			electrons[i + 18].origX = electrons[i + 18].getX();
			electrons[i + 18].origY = electrons[i + 18].getY();
			add(electrons[i + 18]);
		}

		for (int i = 0; i < 18; i++)
		{
			add(orbitals[i]);
		}

		lives = 0;
		level = 1;
		eAdded = 0;
		eNeeded = (int) ((Math.random() * 3) + 3); // Math.random() to generate
													// number of electrons
													// between 3 and 5
		// (Lithium and Boron)
		passed = false;
		selected = false;

		errors = new boolean[5];
		for (int i = 0; i < errors.length; i++)
		{
			errors[i] = false;
		}
	}

	public void checkAfbau() // called everytime an electron is placed
	{ // checks is user has violated the Afbau principle
		// (filled higher energy
		// orbital before lower one)
		System.out.println("checking");

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
	}

	@Override
	public void paintComponent(Graphics g)
	{
		setBackground(Color.BLACK);
		super.paintComponent(g);

		g.setColor(Color.CYAN);
		g.fillOval(30, -330, 760, 575);
		g.setColor(Color.BLACK);
		g.fillOval(35, -330, 750, 565);

		g.setColor(Color.CYAN);
		g.fillOval(100, -400, 600, 575);
		g.setColor(Color.BLACK);
		g.fillOval(105, -400, 590, 565);

		g.setColor(Color.CYAN);
		g.fillOval(170, -370, 460, 480);
		g.setColor(Color.BLACK);
		g.fillOval(175, -370, 450, 470);
	}

}