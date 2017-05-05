import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Orbital extends JPanel implements MouseListener
{
	boolean[] occupied;
	Electron[] orbitalElectrons;
	int principleLevel;
	char subLevel;
	String name;
	int numE;

	public Orbital(String nameIn)
	{
		setLayout(null);
		occupied = new boolean[]
		{ false, false };
		orbitalElectrons = new Electron[]
		{ null, null };
		name = nameIn;
		principleLevel = Integer.parseInt(name.substring(0, 1));
		subLevel = name.charAt(1);
		numE = 0;
		setSize(80, 40);
	}

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

	@Override
	public void mouseReleased(MouseEvent e)
	{
		System.out.println("released in orbital");
	}

	public void checkPaulis() // called everytime user places an electron
	{ // checks if Pauli's exclusion principle has been violated
		if (occupied[0] && occupied[1]) // (same spin, principleLevel, sublevel)
		{
			if (orbitalElectrons[0].spin == orbitalElectrons[1].spin)
			{
				// errors[0] = true;
				// lives--;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

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
}