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
	private Orbital[] orbitals;
	private Electron[] electrons;
	private JButton complete;
	private int lives, level, eAdded, eNeeded;
	private boolean passed, selected;
	private boolean[] errors;
	private Image nucleus;
	private Image heart;

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
		complete.setLocation(700, 200);
		add(complete);

		heart = Utilities.loadImage("heart.png"); // instatiate heart image
	}
	
	/*returns orbitals[] so other classes can access it
	 * but it remains private
	*/
	public Orbital[] getOrbitals() 
	{
		return orbitals;
	}
	

	/*checks if afbau principle has been violated
	* (e- in higher energy orbital when empty one is available)
	*/
	public void checkAfbau(int whereEPlaced)
	{

		int eLastMoved = -1; // index of electrons[] that was last moved

		for (int i = 0; i < 36; i++) // go through each electron[] index to find
						// last moved
		{
			if (electrons[i].getLastMoved())
			{
				eLastMoved = i;
			}
		}

		for (int i = whereEPlaced - 1; i >= 0; i--)
		{
			if (!orbitals[i].getOccupied()[0] || !orbitals[i].getOccupied()[1])
			{
				errors[1] = true;
				lives--;
				repaint();
				return;
			}
		}

	}
	
	/* checks if puli’s exclusion principle has been violated
	 * (same spin in orbital)
	 */	
	public void checkPauli(int whereEPlaced) 
	{
		int eLastMoved = -1; // index of electrons[] that was last moved

		for (int i = 0; i < 36; i++) // go through each electron[] index to find
										// last moved
		{
			if (electrons[i].getLastMoved())
			{
				eLastMoved = i;
			}
		}

		if ((orbitals[whereEPlaced].getOrbitalE()[0] != null) && (orbitals[whereEPlaced].getOrbitalE()[1] != null)) // if both e- not null				
		{
			if (orbitals[whereEPlaced].getOrbitalE()[0].getSpin() == orbitals[whereEPlaced].getOrbitalE()[1].getSpin()) // if they are same spin lose life and repaint
																									{
				errors[0] = true;
				lives--;
				repaint();
			}
		}

		electrons[eLastMoved].setLastMoved(false); // set lastMoved to false for next e- to be moved
	}

	/*checks if hund’s rule has been violated
	 *(electrons double up in orbitals when empty orbitals of same energy are available
	* (not finished yet)
	*/
	public void checkHund(int whereEPlaced)
	{
		int eLastMoved = -1; // index of electrons[] that was last moved

		for (int i = 0; i < 36; i++) // go through each electron[] index to find last moved
		{
			if (electrons[i].getLastMoved())
			{
				eLastMoved = i;
			}
		}

		if ((whereEPlaced == 2) || (whereEPlaced == 3) || (whereEPlaced == 4))
		{
			// if in sublevel d of principle energy level 2 one of the orbitals
			// has 2 e- when one has none
			if ((orbitals[2].getNumE() == 2) && ((orbitals[3].getNumE() == 0) || (orbitals[4].getNumE() == 0)))
			{
				lives--;
				repaint();
				errors[2] = true;
				return;
			}

			if ((orbitals[4].getNumE() == 2) && ((orbitals[2].getNumE() == 0) || (orbitals[3].getNumE() == 0)))
			{
				lives--;
				repaint();
				errors[2] = true;
				return;
			}

			if ((orbitals[3].getNumE() == 2) && ((orbitals[4].getNumE() == 0) || (orbitals[2].getNumE() == 0)))
			{
				lives--;
				repaint();
				errors[2] = true;
				return;
			}

			if (orbitals[2].getOrbitalE()[0] != null)
			{
				if (orbitals[3].getOrbitalE()[0] != null)
				{
					if (orbitals[4].getOrbitalE()[0] != null)
					{
						if ((orbitals[2].getOrbitalE()[0].getSpin() != orbitals[3].getOrbitalE()[0].getSpin())
								|| (orbitals[3].getOrbitalE()[0].getSpin() != orbitals[4].getOrbitalE()[0].getSpin())
								|| (orbitals[2].getOrbitalE()[0].getSpin() != orbitals[4].getOrbitalE()[0].getSpin()))
						{
							lives--;
							repaint();
							errors[2] = true;
							return;
						}
					}
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
	}

	public void paintComponent(Graphics g)
	{
		setBackground(Color.BLACK);
		super.paintComponent(g);
		

		//draw arcs for energy levels
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
		

		//draw hearts for lives
		for (int i = 0; i < lives; i++)
		{
			g.drawImage(heart, 750, 0 + (55 * i), 800, 50 + (55 * i), 0, 0, heart.getWidth(this), heart.getHeight(this),
					this);
		}
	}

	/**
	 * @return the eAdded
	 */
	public int geteAdded()
	{
		return eAdded;
	}

	/**
	 * @param eAdded the eAdded to set
	 */
	public void seteAdded(int eAdded)
	{
		this.eAdded = eAdded;
	}

}