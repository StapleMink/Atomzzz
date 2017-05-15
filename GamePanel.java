
/* Gaurav Datta
 * 5/1/17
 * GamePanel.java
 * This is the panel that contains the entire gameplay.
 * It displays the atom and the electrons to fill it with,
 * and does the error checking, the lives, and all the other
 * parts of gameplay.
 */

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
	private int lives;
	private final int level;
	private int eAdded;
	private int eNeeded;
	private boolean passed;
	private final boolean[] errors;
	private Image nucleus;
	private final Image heart;
	private final Image symbol;
	private boolean done;
	private boolean pressed;
	private final MainPanel main;

	public GamePanel(MainPanel mp, int levelIn)
	{
		setLayout(null); // set layout and size
		setSize(800, 450);

		main = mp;

		orbitals = new Orbital[18]; // instantiate orbitals[]
		orbitals[0] = new Orbital("1s", this);
		orbitals[1] = new Orbital("2s", this); // each orbital gets this
		// instance of GamePanel
		for (int i = 2; i < 5; i++)
		{
			orbitals[i] = new Orbital("2p", this);
		}

		orbitals[5] = new Orbital("3s", this);

		for (int i = 6; i < 9; i++)
		{
			orbitals[i] = new Orbital("3p", this);
		}

		orbitals[9] = new Orbital("4s", this);

		for (int i = 10; i < 15; i++)
		{
			orbitals[i] = new Orbital("3d", this);
		}

		for (int i = 15; i < 18; i++)
		{
			orbitals[i] = new Orbital("4p", this);
		}

		orbitals[0].setLocation(360, 50); // set location of orbitals
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

		electrons = new Electron[36]; // instantiate electrons[] w/ instance of
		// GamePanel and spin
		for (int i = 0; i < 18; i++) // then set location
		{
			electrons[i] = new Electron(true, this);
			electrons[i].setLocation(13 + (43 * i), 330);
			electrons[i].setOrigX(electrons[i].getX());
			electrons[i].setOrigY(electrons[i].getY());
			add(electrons[i]); // add
		}

		for (int i = 0; i < 18; i++)
		{
			electrons[i + 18] = new Electron(false, this);
			electrons[i + 18].setLocation(13 + (43 * i), 390);
			electrons[i + 18].setOrigX(electrons[i + 18].getX());
			electrons[i + 18].setOrigY(electrons[i + 18].getY());
			add(electrons[i + 18]); // add
		}

		for (int i = 0; i < 18; i++)
		{
			add(orbitals[i]); // add orbitals
		}

		lives = 3;
		level = levelIn;
		eAdded = 0;
		if (level == 1)
		{
			eNeeded = (int) ((Math.random() * 2) + 3);
		}

		if (level == 2)
		{
			eNeeded = 10;
		}

		if (level == 3)
		{
			eNeeded = (int) ((Math.random() * 4) + 6);
		}

		if (level == 4)
		{
			eNeeded = (int) ((Math.random() * 7) + 11);
		}

		if (level == 5)
		{
			double random = Math.random();
			if (random < 0.5)
			{
				eNeeded = (int) ((Math.random() * 3) + 26);
			}

			else
			{
				eNeeded = (int) ((Math.random() * 3) + 33);
			}
		}

		symbol = Utilities.loadImage("element" + Integer.toString(eNeeded) + ".jpg");

		passed = false;

		errors = new boolean[5]; // instantiate errors to all false
		for (int i = 0; i < errors.length; i++)
		{
			errors[i] = false;
		}

		complete = new JButton("Complete"); // instantiate button for completion
		complete.addActionListener(this);
		complete.setLocation(690, 200);
		complete.setSize(100, 30);
		add(complete);

		heart = Utilities.loadImage("heart.png"); // instantiate heart image

		done = false;
		pressed = false;
	}

	// these methods all return fields from this class so the can be private
	public Orbital[] getOrbitals()
	{
		return orbitals;
	}

	public int geteAdded()
	{
		return eAdded;
	}

	public void seteAdded(int eAdded)
	{
		this.eAdded = eAdded;
	}

	public boolean getDone()
	{
		return done;
	}

	/*
	 * checks if afbau principle has been violated (e- in higher energy orbital
	 * when empty one is available)
	 */
	public void checkAfbau(int whereEPlaced)
	{

		int eLastMoved = findLastMoved(); // index of electrons[] that was last
											// moved

		// starting from where e- was placed and going down
		for (int i = whereEPlaced - 1; i >= 0; i--)
		{
			// if the orbital has an empty space
			if (!orbitals[i].getOccupied()[0] || !orbitals[i].getOccupied()[1])
			{
				// if the orbital has a different name (principle energy and sub
				// level)
				if (!orbitals[i].getName().equals(orbitals[whereEPlaced].getName()))
				{
					// error
					errors[1] = true;
					lives--;
					repaint();
					return;
				}
			}
		}

	}

	/*
	 * checks if pauli's exclusion principle has been violated (same spin in
	 * orbital)
	 */
	public void checkPauli(int whereEPlaced)
	{
		int eLastMoved = findLastMoved(); // index of electrons[] that was last
											// moved
		// if both e- not null
		if ((orbitals[whereEPlaced].getOrbitalE()[0] != null) && (orbitals[whereEPlaced].getOrbitalE()[1] != null))
		{
			// if they are same spin
			if (orbitals[whereEPlaced].getOrbitalE()[0].getSpin() == orbitals[whereEPlaced].getOrbitalE()[1].getSpin())
			{
				errors[0] = true;
				lives--;
			}
		}

		electrons[eLastMoved].setLastMoved(false); // set lastMoved to false for
													// next e- to be moved
	}

	/*
	 * checks if hund's rule has been violated (electrons double up in orbitals
	 * when empty orbitals of same energy are available
	 */
	public void checkHund(int whereEPlaced)
	{
		// if action was in sublevel w/ multiple orbitals
		if ((orbitals[whereEPlaced].getSubLevel() == 'p') || (orbitals[whereEPlaced].getSubLevel() == 'd')
				|| (orbitals[whereEPlaced].getSubLevel() == 'f'))
		{

			// this new orbital will represent the orbital where the e- was
			// placed
			Orbital located = orbitals[whereEPlaced];
			// this new orbital will represent the other e- of that sublevel
			Orbital[] others = new Orbital[7];

			// to instantiate others[]
			int index = 0;
			for (int i = 0; i < 18; i++)
			{
				// making sure it's not the one that's where the e- was placed
				// and is correct level
				if ((orbitals[i].getName().equals(orbitals[whereEPlaced].getName())) && (i != whereEPlaced))
				{
					others[index] = orbitals[i];
					index++;
				}
			}

			// this is the number of other orbitals in that sublevel
			int numOthers = 0;

			while (others[numOthers] != null)
			{
				System.out.println(numOthers);
				numOthers++;
			}

			// goes through the others[] to find errors
			for (int i = 0; i < numOthers; i++)
			{
				// if the orbital where placed has 2e- and another has 0
				if ((located.getNumE() == 2) && (others[i].getNumE() == 0))
				{
					errors[2] = true;
					lives--;
					return;
				}
				// if both have one
				if ((located.getNumE() == 1) && (others[i].getNumE() == 1))
				{ // if different spins
					if (located.getOrbitalE()[0].getSpin() != others[i].getOrbitalE()[0].getSpin())
					{
						errors[2] = true;
						lives--;
						return;
					}
				}
			}
		}
	}

	public void checkNumE()
	{
		if (eNeeded != eAdded)
		{
			if (eAdded < eNeeded)
			{
				errors[4] = true;
				lives = 0;
				repaint();
				return;
			}

			if (eAdded > eNeeded)
			{
				errors[3] = true;
				lives = 0;
				repaint();
				return;
			}
		}
	}

	public void checkPassed()
	{
		if ((lives > 0) && done)
		{
			passed = true;
		}
		else if (lives <= 0)
		{
			passed = false;
			done = true;
		}
		if (pressed || (lives <= 0))
		{
			main.add(new ErrorPanel(errors, lives, passed, main, level, this), "errors");
			main.getCards().show(main, "errors");
		}
	}

	public int findLastMoved()
	{
		for (int i = 0; i < 36; i++) // go through each electron[] index to find
		// last moved
		{
			if (electrons[i].getLastMoved())
			{
				return i;
			}
		}

		return -1;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		pressed = true;
		done = true;
		checkNumE();
		checkPassed();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		setBackground(Color.BLACK);
		super.paintComponent(g);

		// draw arcs for energy levels
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

		g.drawImage(symbol, 0, 0, 50, 50, 0, 0, symbol.getWidth(this), symbol.getHeight(this), this);

		// draw hearts for lives
		for (int i = 0; i < lives; i++)
		{
			g.drawImage(heart, 750, 0 + (55 * i), 800, 50 + (55 * i), 0, 0, heart.getWidth(this), heart.getHeight(this),
					this);
		}
	}
}