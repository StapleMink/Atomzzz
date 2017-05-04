import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;

public class PanelTester
{
	public static void main( String[] args )
	{
		PanelTester panelt = new PanelTester();
		panelt.runIt();
	}

	public void runIt()
	{
		JFrame frame = new JFrame("Atomzzz Alpha - Test Frame");
		frame.setSize( 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(0,0);
		frame.setResizable(false);
		StartPanel stpanel = new StartPanel();
		frame.getContentPane().add( stpanel );
		frame.setVisible(true);
	}
}

//<<<<<<<<<<<<<<<<<<<<<<<<<<BEGIN CODE FOR STARTPANEL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	class StartPanel extends JPanel
	{
		private TitlePanel titleP;//Instantiate Fields
		private MenuPanel menuP;


		public StartPanel()//Set BorderLayout
		{
			setLayout(new BorderLayout(10,10));//Give space between panels
			//setLayout(null);
			titleP = new TitlePanel();
			add(titleP,BorderLayout.NORTH);
			//titleP.setSize(125,50);
			//titleP.setLocation(0,0);
			menuP = new MenuPanel();
			add(menuP,BorderLayout.CENTER);
			//menuP.setSize(125,50);
			//menuP.setLocation(200,100);

		}

		public void paintComponent(Graphics g)//Set background colour
		{
			setBackground(Color.CYAN);
			super.paintComponent (g);// draw background
		}
//<<<<<<<<<<<<<<<<<<<<<<<<<<BEGIN CODE FOR MENUPANEL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		public class MenuPanel extends JPanel implements ActionListener
		{
			private JButton startButton;//create buttons in MenuPanel()
			private JButton helpButton;
			private Image menuImg;
			private String menuImgName;
			private ImagePanel1 imgP1;

			public MenuPanel()//Constructor for MenuPanel()
			{
				menuImg = null;
				menuImgName = "menuImg.PNG";
				//menuImgName = "/Users/danielbudziwojski/Documents/workspace/Game Final/beckenbauer.jpg";
				imgP1 = new ImagePanel1();
				
				setLayout(null);
				
				startButton = new JButton("Start");//Give buttons their names
				helpButton = new JButton("How To Play");
				startButton.addActionListener(this);//Add listeners
				helpButton.addActionListener(this);			
				getMyImage();
				
				add(startButton);//Add to panel
				startButton.setSize(125,50);
				startButton.setLocation(200,200);
				
				add(imgP1);
				imgP1.setSize(100,100);
				imgP1.setLocation(350,175);
				
				add(helpButton);
				helpButton.setSize(125,50);
				helpButton.setLocation(475,200);
			}

			public void actionPerformed(ActionEvent evt)//Get action from JButtons
			{
				String command = evt.getActionCommand();//get name of button pressed
				if(command.equals("Start"))//conditional to determine which button was clicked and how to react
				{
					System.out.println("Start Game");
				}
				if(command.equals("How To Play"))
				{
					System.out.println("How to Play");//Create Pop Up
					String header = "qwertyuiopasdfghjklzxcvbnm,";
					JFrame frame = new JFrame("How To Play");
					frame.setLocation(100,100);
					frame.setSize(300,125);
					frame.setLayout(new FlowLayout());
					JLabel headingLabel = new JLabel(header);
					frame.add(headingLabel);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);

				}
				titleP.repaint();//Repaint any changes made to the canvas
				repaint();
			}

			public void getMyImage()
			{
				System.out.println("Getting Image");
				try
				{
					menuImg = ImageIO.read(new File(menuImgName));
				}
				catch(IOException e)
				{
					System.err.println("\n\n" + menuImgName + " cannot be found\n\n");
					e.printStackTrace();
				}
			}

			public void paintComponent(Graphics g)	// paint component for MenuPanel
			{
				setBackground(Color.BLACK); //Set colour scheme
				System.out.println("Display Image");
				g.setColor(Color.RED);
				g.fillOval(100,100,50,50);
				super.paintComponent (g);	// draw background
			}

			class ImagePanel1 extends JPanel
			{
				public void paintComponent(Graphics g)	// paint component for TitlePanel
				{
					super.paintComponent (g);
					setBackground(Color.BLACK);
					System.out.println("Drawing Image");
					g.drawImage(menuImg,0,0,100,100,this);
				}
			}
		}
//<<<<<<<<<<<<<<<<<<<<<<<<<<BEGIN CODE FOR TITLEPANEL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		public class TitlePanel extends JPanel
		{
			private Image titleImg;
			private String titleImgName;
			private ImagePanel2 imgP2;			
			private JButton testButton;

			public TitlePanel()
			{
				//setLayout(new FlowLayout());
				setLayout(null);
				//testButton = new JButton("Test");
				//add(testButton);
				//testButton.setSize(50,50);
				//testButton.setLocation(50,50);
					//getMyImage2();	
				
				titleImg = null;
				titleImgName = "menuImg.PNG";
				imgP2 = new ImagePanel2();
				add(imgP2);
				imgP2.setSize(100,100);
				imgP2.setLocation(0,0);
			}

			public void paintComponent(Graphics g)	// paint component for TitlePanel
			{
				setBackground(Color.BLACK);
				super.paintComponent (g);	// draw background
			}

			public void getMyImage2()
			{
				System.out.println("Getting Image 2.0");
				try
				{
					titleImg = ImageIO.read(new File(titleImgName));
				}
				catch(IOException e)
				{
					System.err.println("\n\n" + titleImgName + " cannot be found\n\n");
					e.printStackTrace();
				}
			}

			class ImagePanel2 extends JPanel
			{
				public void paintComponent(Graphics g)	// paint component for TitlePanel
				{
					super.paintComponent (g);
					setBackground(Color.RED);
					System.out.println("Drawing Image");
					g.drawImage(titleImg,0,0,100,100,this);
				}
			}
		}
	}