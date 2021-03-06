
/* Gaurav Datta
 * 5/1/17
 * Electron.java
 * This class creates an electron, a JPanel with a circle
 * and symbol for spin. It can be dragged and placed in orbitals,
 * which is the objective of the game.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Electron extends JPanel implements MouseMotionListener, MouseListener
{
	private boolean spin;
	private int compX;
	private int compY;
	private int pressX;
	private int pressY;
	private boolean selected;
	private String side;
	private int origX, origY;
	private boolean inOrbital;
	private GamePanel game;
	private int wherePlaced;
	private boolean lastMoved;

	// constructor: initialize fields
	public Electron(boolean spinIn, GamePanel gp)
	{
		setLayout(null);
		setSize(45, 45);
		spin = spinIn;
		inOrbital = false;
		selected = false;
		compX = getX();
		compY = getY();
		pressX = -1;
		pressY = -1;
		addMouseMotionListener(this);
		addMouseListener(this);
		side = "";
		game = gp;
		wherePlaced = -1;
		lastMoved = false;
	}

	/*
	 * The next several methods all return or set a field variable so that they
	 * may remain private;
	 */
	public void setOrigX(int origX)
	{
		this.origX = origX;
	}

	public int getOrigX()
	{
		return origX;
	}

	public void setOrigY(int origY)
	{
		this.origY = origY;
	}

	public int getOrigY()
	{
		return origY;
	}

	public boolean getSpin()
	{
		return spin;
	}

	public int getWherePlaced()
	{
		return wherePlaced;
	}

	public boolean getLastMoved()
	{
		return lastMoved;
	}

	public void setLastMoved(boolean lastMovedIn)
	{
		this.lastMoved = lastMovedIn;
	}

	// draw yellow circle to represent electron and arrow for spin

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillOval(0, 0, 45, 45);
		g.setColor(Color.BLACK);

		g.drawLine(23, 5, 23, 40);

		if (spin)
		{
			g.drawLine(23, 5, 35, 20);
		}
		else
		{
			g.drawLine(23, 40, 11, 25);
		}
	}

	// method for dragging electron, if not in orbital

	public void mouseDragged(MouseEvent e)
	{
		if (!inOrbital && !game.getDone())
		{
			selected = true;
			int changeX = (e.getX() - pressX);
			int changeY = (e.getY() - pressY);
			setLocation((compX + changeX), (compY + changeY));
			compX = getX();
			compY = getY();
			lastMoved = true;
		}

	}

	public void mouseMoved(MouseEvent e)
	{
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	// gets coordinates where mouse is pressed to know where to drag

	public void mousePressed(MouseEvent e)
	{
		pressX = e.getX();
		pressY = e.getY();

	}

	/*
	 * for after mouse drags electron figures out if in orbital, then places,
	 * then checks if errors were made
	 */

	public void mouseReleased(MouseEvent e)
	{
		if (selected && !inOrbital)
		{
			int index = 0;
			inOrbital = false;

			// checks each orbital to see if inside
			for (index = 0; (index < 23) && !inOrbital; index++)
			{
				inOrbital = isInOrbital(index);
			}

			// returns to original position if not in orbital
			if (!inOrbital)
			{
				setLocation(getOrigX(), getOrigY());
			}

			else
			{
				index--;
				wherePlaced = index; // field that can be accessed later
				// places electron in right or left side, tells orbital it has
				// occupant and instantiates an electron there with same spin

				if (side.equals("left"))
				{
					setLocation(game.getOrbitals()[index].getX() + 8, game.getOrbitals()[index].getY() + 8);
					game.getOrbitals()[index].getOccupied()[0] = true;
				}

				else if (side.equals("right"))
				{
					setLocation(game.getOrbitals()[index].getX() + 65, game.getOrbitals()[index].getY() + 8);
					game.getOrbitals()[index].getOccupied()[1] = true;
				}

				if (game.getOrbitals()[index].getOrbitalE()[0] == null)
				{
					game.getOrbitals()[index].getOrbitalE()[0] = new Electron(this.spin, game);
				}

				else
				{
					game.getOrbitals()[index].getOrbitalE()[1] = new Electron(this.spin, game);
				}

				game.getOrbitals()[index].incrementNumE();
				game.seteAdded(game.geteAdded() + 1);
				game.checkAfbau(wherePlaced);
				game.checkPauli(wherePlaced);
				game.checkHund(wherePlaced);
				game.repaint();
				game.checkPassed();
			}
			selected = false;
			// game.checkIfLost();
		}
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	/*
	 * returns true if e- is in orbital, also determines which side of orbital
	 * e- is in
	 */
	public boolean isInOrbital(int index)
	{
		// data on orbital in question
		int centerX = getX() + 23;
		int centerY = getY() + 23;
		int orbitalX = game.getOrbitals()[index].getX();
		int orbitalY = game.getOrbitals()[index].getY();
		int orbitalWidth = game.getOrbitals()[index].getWidth();
		int orbitalHeight = game.getOrbitals()[index].getHeight();

		if ((centerX >= orbitalX) && (centerX <= (orbitalX + orbitalWidth))) // checking
																				// to
																				// see
																				// if
																				// e-
																				// located
																				// in
																				// orbital
		{
			if ((centerY >= orbitalY) && (centerY <= (orbitalY + orbitalHeight)))
			{
				if (centerX < ((0.5 * orbitalWidth) + orbitalX))
				{
					if (!game.getOrbitals()[index].getOccupied()[0])
					{
						side = "left";
					}
					else
					{
						return false;
					}
				}
				else
				{
					if (!game.getOrbitals()[index].getOccupied()[1])
					{
						side = "right";
					}
					else
					{
						return false;
					}
				}

				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

}