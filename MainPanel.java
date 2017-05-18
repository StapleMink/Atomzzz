
/* Gaurav Datta
 * 5/5/17
 * MainPanel.java
 * This panel has the card layout that will enable
 * switching between game screens. It has an instance
 * of GamePanel and StartPanel, which are each passed
 * this instance of MainPanel
 */

import java.awt.CardLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel
{

	// fields
	private CardLayout cards;
	private StartPanel sp;
	private GamePanel gp;

	// initialize fields, giving this instance of MainPanel
	public MainPanel()
	{
		cards = new CardLayout();
		setLayout(cards);

		sp = new StartPanel(this);
		add(sp, "start");

		gp = new GamePanel(this, 1);
		add(gp, "game");

	}

	// returns card layout so it can be private
	public CardLayout getCards()
	{
		return cards;
	}

	// resets gp to new level after user elects to try again or advance
	public void setGame(int whichLevel)
	{
		gp = new GamePanel(this, whichLevel);
		add(gp, "game");
	}
}