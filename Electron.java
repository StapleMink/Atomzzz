
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Electron extends JPanel implements MouseMotionListener, MouseListener
{
	final boolean spin;
	private int compX;
	private int compY;
	private int pressX;
	private int pressY;
	private boolean selected;
	private String side;
	int origX, origY;
	private final Orbital[] eOrbitals;
	private boolean inOrbital;

	public Electron(boolean spinIn, Orbital[] orbitals)
	{
		setLayout(null);
		setSize(30, 30);
		spin = spinIn;
		inOrbital = false;
		selected = false;
		compX = getX();
		compY = getY();
		pressX = -1;
		pressY = -1;
		origX = getX();
		origY = getY();
		addMouseMotionListener(this);
		addMouseListener(this);
		eOrbitals = orbitals;
		side = "";
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillOval(0, 0, 30, 30);
		g.setColor(Color.BLACK);

		g.drawLine(15, 5, 15, 25);

		if (spin)
		{
			g.drawLine(15, 5, 22, 12);
		}
		else
		{
			g.drawLine(15, 25, 8, 18);
			/*
			 * g.drawLine(Utilities.scale(0.5, getWidth()),
			 * Utilities.scale(0.125, getHeight()), Utilities.scale(0.5,
			 * getWidth()), Utilities.scale(0.875, getHeight()));
			 * g.drawLine(Utilities.scale(0.5, getWidth()),
			 * Utilities.scale(0.125, getHeight()), Utilities.scale(0.75,
			 * getWidth()), Utilities.scale(0.25, getHeight()));
			 */
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (!inOrbital)
		{
			selected = true;
			int changeX = (e.getX() - pressX);
			int changeY = (e.getY() - pressY);
			setLocation((compX + changeX), (compY + changeY));
			compX = getX();
			compY = getY();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		pressX = e.getX();
		pressY = e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (selected || !inOrbital)
		{
			int index = 0;
			inOrbital = false;

			for (index = 0; (index < 18) && !inOrbital; index++)
			{
				inOrbital = isInOrbital(index);
			}

			if (!inOrbital)
			{
				setLocation(origX, origY);
			}

			else
			{
				index--;
				if (side.equals("left"))
				{
					setLocation(eOrbitals[index].getX() + 5, eOrbitals[index].getY() + 5);
					eOrbitals[index].occupied[0]= true;
				}

				else if (side.equals("right"))
				{
					setLocation(eOrbitals[index].getX() + 45, eOrbitals[index].getY() + 5);
					eOrbitals[index].occupied[1]= true;
				}
				
			}
			selected = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	public boolean isInOrbital(int index)
	{
		int centerX = getX() + 15;
		int centerY = getY() + 15;
		int orbitalX = eOrbitals[index].getX();
		int orbitalY = eOrbitals[index].getY();
		int orbitalWidth = eOrbitals[index].getWidth();
		int orbitalHeight = eOrbitals[index].getHeight();
		
		if ((centerX >= orbitalX) && (centerX <= (orbitalX + orbitalWidth)))
		{
			if ((centerY >= orbitalY) && (centerY <= (orbitalY + orbitalHeight)))
			{
				if (centerX < ((0.5 * orbitalWidth) + orbitalX))
				{
					if (!eOrbitals[index].occupied[0])
						side = "left";
					else return false;
				}
				else
				{
					if (!eOrbitals[index].occupied[1])
					side = "right";
					else return false;
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