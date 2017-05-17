import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

//<<<<<<<<<<<<<<<<<<<<<<<<<<BEGIN CODE FOR STARTPANEL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
public class StartPanel extends JPanel
{
	private final TitlePanel titleP;// Instantiate Fields
	private final MenuPanel menuP;
	private final MainPanel mp;

	public StartPanel(MainPanel main)// Set BorderLayout
	{
		mp = main;
		setLayout(null);
		titleP = new TitlePanel();
		add(titleP, BorderLayout.NORTH);
		titleP.setSize(1600, 200);
		titleP.setLocation(0, 0);
		menuP = new MenuPanel();
		add(menuP, BorderLayout.CENTER);
		menuP.setSize(1600, 700);
		menuP.setLocation(0, 200);
	}

	@Override
	public void paintComponent(Graphics g)// Set background colour
	{
		setBackground(Color.CYAN);
		super.paintComponent(g);// draw background
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<<BEGIN CODE FOR
	// MENUPANEL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public class MenuPanel extends JPanel implements ActionListener
	{
		private final JButton startButton;// create buttons in MenuPanel()
		private final JButton helpButton;
		private Image menuImg;
		private final String menuImgName;
		private final ImagePanel1 imgP1;

		public MenuPanel()// Constructor for MenuPanel()
		{
			menuImg = null;
			menuImgName = "menuImg.PNG";
			imgP1 = new ImagePanel1();

			setLayout(null);

			startButton = new JButton("Start");// Give buttons their names
			helpButton = new JButton("How To Play");
			startButton.addActionListener(this);// Add listeners
			helpButton.addActionListener(this);

			menuImg = Utilities.loadImage(menuImgName);

			add(startButton);// Add to panel
			startButton.setSize(125, 50);
			startButton.setLocation(525, 275);

			add(imgP1);
			imgP1.setSize(200, 200);
			imgP1.setLocation(700, 200);

			add(helpButton);
			helpButton.setSize(125, 50);
			helpButton.setLocation(950, 275);
		}

		@Override
		public void actionPerformed(ActionEvent evt)// Get action from JButtons
		{
			String command = evt.getActionCommand();// get name of button
													// pressed
			if (command.equals("Start"))// conditional to determine which button
										// was clicked and how to react
			{
				mp.getCards().next(mp);
			}
			if (command.equals("How To Play"))
			{
				// Create Pop Up
				HowToPlay htp = new HowToPlay();
				htp.run();
			}
			titleP.repaint();// Repaint any changes made to the canvas
			repaint();
		}

		@Override
		public void paintComponent(Graphics g) // paint component for MenuPanel
		{
			super.paintComponent(g); // draw background
			setBackground(Color.BLACK); // Set colour scheme
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, 1600, 10);
		}

		class ImagePanel1 extends JPanel
		{
			@Override
			public void paintComponent(Graphics g) // paint component for
													// TitlePanel
			{
				super.paintComponent(g);
				setBackground(Color.BLACK);
				g.drawImage(menuImg, 0, 0, 200, 200, this);
			}
		}
	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<<BEGIN CODE FOR
	// TITLEPANEL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public class TitlePanel extends JPanel
	{
		private Image titleImg;
		private final String titleImgName;
		private final ImagePanel2 imgP2;
		private JButton testButton;

		public TitlePanel()
		{
			setLayout(null);
			titleImg = null;
			titleImgName = "titleImg.png";
			titleImg = Utilities.loadImage(titleImgName);

			imgP2 = new ImagePanel2();
			add(imgP2);
			imgP2.setSize(1600, 200);
			imgP2.setLocation(0, 0);
		}

		@Override
		public void paintComponent(Graphics g) // paint component for TitlePanel
		{
			setBackground(Color.BLACK);
			super.paintComponent(g); // draw background
		}

		class ImagePanel2 extends JPanel
		{
			@Override
			public void paintComponent(Graphics g) // paint component for
													// TitlePanel
			{
				super.paintComponent(g);
				setBackground(Color.BLACK);
				g.drawImage(titleImg, 500, 0, 600, 200, this);
			}
		}
	}
}