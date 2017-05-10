
/* Gaurav Datta
 * 5/1/17
 * Orbital.java
 * Panel that represents an orbital that electrons are placed in
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Orbital extends JPanel
{

	private final boolean[] occupied;
	private final Electron[] orbitalE;
	private final int principleLevel;
	private final char subLevel;
	private final String name;
	private int numE;

	// constructor: initialize fields
	public Orbital(String nameIn, GamePanel gp)
	{
		setLayout(null);
		occupied = new boolean[]
		{ false, false };
		orbitalE = new Electron[]
		{ null, null };
		name = nameIn;
		principleLevel = Integer.parseInt(name.substring(0, 1));
		subLevel = name.charAt(1);
		numE = 0;
		setSize(80, 40);
	}

	// these methods all return field variables so they can be private
	public boolean[] getOccupied()
	{
		return occupied;
	}

	public Electron[] getOrbitalE()
	{
		return orbitalE;
	}

	public int getPrincipleLevel()
	{
		return principleLevel;
	}

	public char getSubLevel()
	{
		return subLevel;
	}

	public int getNumE()
	{
		return numE;
	}

	public void incrementNumE()
	{
		numE++;
	}

	@Override
	public String getName()
	{
		return name;
	}

	// draw orbital in different color depending on name
	@Override
	public void paintComponent(Graphics g)
	{
		setBackground(Color.BLACK);
		super.paintComponent(g);
		if (subLevel == 's')
		{
			g.setColor(Color.GREEN);
			g.fillOval(0, 0, 80, 40);
		}
		if (subLevel == 'p')
		{
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, 80, 40);
		}
		if (subLevel == 'd')
		{
			g.setColor(Color.RED);
			g.fillOval(0, 0, 80, 40);
		}

		g.setColor(Color.BLACK);
		g.drawLine(40, 5, 40, 35);

	}

}