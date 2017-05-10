
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
	private final int eNeeded;
	private final boolean passed, selected;
	private final boolean[] errors;
	private Image nucleus;
	private final Image heart;
	private boolean done;

	public GamePanel(MainPanel mp)
	{
		setLayout(null); // set layout and size
		setSize(800, 450);

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
		level = 1;
		seteAdded(0);
		eNeeded = (int) ((Math.random() * 3) + 3); // Math.random() to generate
													// number of electrons
													// between 3 and 5
		// (Lithium and Boron)
		passed = false;
		selected = false;

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
				}
			}
		}

	}

	/*
	 * checks if puli’s exclusion principle has been violated (same spin in
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
				repaint();
			}
		}

		electrons[eLastMoved].setLastMoved(false); // set lastMoved to false for
													// next e- to be moved
	}

	/*
	 * checks if hund’s rule has been violated (electrons double up in orbitals
	 * when empty orbitals of same energy are available
	 */
	public void checkHund(int whereEPlaced)
	{
		boolean errorMade = false;
		// for loop to check all p sublevels b/c they have multiple orbitals
		for (int i = 2; i < 7; i += 4)
		{
			// if an e- was placed in one of the d orbitals
			if ((whereEPlaced == i) || (whereEPlaced == (i + 1)) || (whereEPlaced == (i + 2)))
			{
				// if placed in 1st orbital of p sublevel
				if (whereEPlaced == i)
				{
					// if 2 e- in this orbital and none in other of same
					// sublevel
					if ((orbitals[i].getNumE() == 2)
							&& ((orbitals[i + 1].getNumE() == 0) || (orbitals[i + 2].getNumE() == 0)))
					{
						errorMade = true;
					}

					// if this orbital and one other has 1 e-
					else if ((orbitals[i].getNumE() == 1) && ((orbitals[i + 1].getNumE() == 1)))
					{
						if (orbitals[i].getOrbitalE()[0].getSpin() != orbitals[i + 1].getOrbitalE()[0].getSpin())
						{
							errorMade = true;
						}
					}

					// if this orbital and one other has 1 e-
					else if ((orbitals[i].getNumE() == 1) && ((orbitals[i + 2].getNumE() == 1)))
					{
						// if different spins
						if (orbitals[i].getOrbitalE()[0].getSpin() != orbitals[i + 2].getOrbitalE()[0].getSpin())
						{
							errorMade = true;
						}
					}
				}

				// if placed in 2nd orbital of p sublevel
				else if (whereEPlaced == (i + 1))
				{
					// if 2 e- in this orbital and none in other of same
					// sublevel
					if ((orbitals[i + 1].getNumE() == 2)
							&& ((orbitals[i].getNumE() == 0) || (orbitals[i + 2].getNumE() == 0)))
					{
						errorMade = true;
					}

					// if this orbital and one other has 1 e-
					else if ((orbitals[i + 1].getNumE() == 1) && ((orbitals[i].getNumE() == 1)))
					{
						if (orbitals[i + 1].getOrbitalE()[0].getSpin() != orbitals[i].getOrbitalE()[0].getSpin())
						{
							errorMade = true;
						}
					}

					// if this orbital and one other has 1 e-
					else if ((orbitals[i + 1].getNumE() == 1) && ((orbitals[i + 2].getNumE() == 1)))
					{
						// if different spins
						if (orbitals[i + 1].getOrbitalE()[0].getSpin() != orbitals[i + 2].getOrbitalE()[0].getSpin())
						{
							errorMade = true;
						}
					}
				}

				// if placed in 3rd orbital of p sublevel
				else if (whereEPlaced == (i + 2))
				{
					System.out.println("wherePlaced = 4");
					// if 2 e- in this orbital and none in other of same
					// sublevel
					if ((orbitals[i + 2].getNumE() == 2)
							&& ((orbitals[i].getNumE() == 0) || (orbitals[i + 1].getNumE() == 0)))
					{
						errorMade = true;
					}

					// if this orbital and one other has 1 e-
					else if ((orbitals[i + 2].getNumE() == 1) && ((orbitals[i].getNumE() == 1)))
					{
						if (orbitals[i + 2].getOrbitalE()[0].getSpin() != orbitals[i].getOrbitalE()[0].getSpin())
						{
							errorMade = true;
						}
					}

					// if this orbital and one other has 1 e-
					else if ((orbitals[i + 2].getNumE() == 1) && ((orbitals[i + 1].getNumE() == 1)))
					{
						// if different spins
						if (orbitals[i + 2].getOrbitalE()[0].getSpin() != orbitals[i + 1].getOrbitalE()[0].getSpin())
						{
							errorMade = true;
						}
					}

				}
			}
		}

		if (errorMade)
		{
			lives--;
			errors[2] = true;
			repaint();
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

	public void checkIfLost()
	{
		if (done || (lives == 0))
		{
			done = true;
			new ErrorPanel(errors, lives);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		done = true;
		checkIfLost();
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

		// draw hearts for lives
		for (int i = 0; i < lives; i++)
		{
			g.drawImage(heart, 750, 0 + (55 * i), 800, 50 + (55 * i), 0, 0, heart.getWidth(this), heart.getHeight(this),
					this);
		}
	}
}