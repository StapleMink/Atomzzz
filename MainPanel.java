
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

	private final CardLayout cards;
	private final StartPanel sp;
	private final GamePanel gp1;
	private final GamePanel gp2;
	private final GamePanel gp3;
	private final GamePanel gp4;
	private final GamePanel gp5;
	private GamePanel gp;

	public MainPanel()
	{
		cards = new CardLayout();
		setLayout(cards);

		sp = new StartPanel(this);
		add(sp, "start");

		gp1 = new GamePanel(this, 1);
		add(gp1, "game1");

		gp2 = new GamePanel(this, 2);
		add(gp2, "game2");

		gp3 = new GamePanel(this, 3);
		add(gp3, "game3");

		gp4 = new GamePanel(this, 4);
		add(gp4, "game4");

		gp5 = new GamePanel(this, 5);
		add(gp5, "game5");

		gp = new GamePanel(this, 1);
		add(gp, "game");

	}

	// returns card layout so it can be private
	public CardLayout getCards()
	{
		return cards;
	}

	public void setGame(int whichLevel)
	{
		gp = new GamePanel(this, whichLevel);
	}

}
