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

		sp = new StartPanel(this);
		add(sp, "start");

		gp = new GamePanel(this);
		add(gp, "game");
		/*
		 * if (makeStart && makeGame) // for the first time // constructor is
		 * called { sp = new StartPanel(new MainPanel(false, true)); add(sp,
		 * "start"); gp = new GamePanel(); add(gp, "game"); }
		 *
		 * if (!makeGame && makeStart) // for second time { sp = new
		 * StartPanel(new MainPanel(false, false)); add(sp, "start"); }
		 *
		 * if (makeGame && !makeStart) { gp = new GamePanel(); add(gp, "game");
		 * }
		 */
	}

	/*
	 * public MainPanel() { cards = new CardLayout(); setLayout(cards); sp = new
	 * StartPanel(new MainPanel(false, true)); add(sp, "start"); gp = new
	 * GamePanel(); add(gp, "game"); }
	 */

}
