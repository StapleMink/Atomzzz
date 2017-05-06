import java.awt.CardLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel
{

	CardLayout cards;
	StartPanel sp;
	GamePanel gp;

	public MainPanel()
	{
		cards = new CardLayout();
		setLayout(cards);
		sp = new StartPanel(new MainPanel());
		gp = new GamePanel(new MainPanel());
		add(sp, "start");
		add(gp, "game");
	}

	public CardLayout getCards()
	{
		return cards;
	}

}
