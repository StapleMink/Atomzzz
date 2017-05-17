/* Daniel Budziwojski
 * HowToPlay.java
 * 4/27/17 Period 3
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowToPlay extends JFrame implements ActionListener
{
	JPanel cards;
    JButton button1, button2, button3, button4, button2B, button3B, button4B, button5B;
	
	public static void main( String[] args )
	{
		HowToPlay htp = new HowToPlay();
		htp.run();
	}

	public void run() //This is the CONSTRUCTOR method
    {
        //The entry point into your program
        JFrame frameA = new JFrame("How To Play");
        frameA.setSize(800, 450); //Set the size of the JFrame
        frameA.setLocation(400, 225);
        frameA.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameA.setResizable(false);

        button1 = new JButton("Next Page");
        button2 = new JButton("Next Page");
        button2B = new JButton("Previous Page");
        button3 = new JButton("Next Page");
        button3B = new JButton("Previous Page");
        button4 = new JButton("Next Page");
        button4B = new JButton("Previous Page");
        button5B = new JButton("Previous Page");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button2B.addActionListener(this);
        button3.addActionListener(this);   
        button3B.addActionListener(this);
        button4.addActionListener(this);   
        button4B.addActionListener(this);
        button5B.addActionListener(this);

        //Create the cards
        //HowToPlayPanel card1 = new HowToPlayPanel();

        JPanel card1 = new JPanel();
        HowToPlayPanel htp1 = new HowToPlayPanel();
        card1.setLayout(null);
        card1.add(htp1);     
        htp1.setSize(800,350);
        htp1.setLocation(0,0);
        card1.setBackground(Color.BLACK);
        card1.add(button1);
        button1.setSize(100,50);
        button1.setLocation(600, 350);
        
        JPanel card2 = new JPanel();
        HowToPlayPanel2 htp2 = new HowToPlayPanel2();
        card2.setLayout(null);
        card2.add(htp2);   
        card2.setBackground(Color.BLACK);
        card2.add(button2);
        htp2.setSize(800,350);
        htp2.setLocation(0,0);
        button2.setSize(100,50);
        button2.setLocation(600, 350);
        card2.add(button2B);
        button2B.setSize(100,50);
        button2B.setLocation(100, 350);
        
        JPanel card3 = new JPanel();
        HowToPlayPanel3 htp3 = new HowToPlayPanel3();
        card3.setLayout(null);
        card3.add(htp3);   
        card3.setBackground(Color.BLACK);
        card3.add(button3);
        htp3.setSize(800,350);
        htp3.setLocation(0,0);
        button3.setSize(100,50);
        button3.setLocation(600, 350);
        card3.add(button3B);
        button3B.setSize(100,50);
        button3B.setLocation(100, 350);
        
        JPanel card4 = new JPanel();
        HowToPlayPanel4 htp4 = new HowToPlayPanel4();
        card4.setLayout(null);
        card4.add(htp4);   
        card4.setBackground(Color.BLACK);
        card4.add(button4);
        htp4.setSize(800,350);
        htp4.setLocation(0,0);
        button4.setSize(100,50);
        button4.setLocation(600, 350);
        card4.add(button4B);
        button4B.setSize(100,50);
        button4B.setLocation(100, 350);
        
        JPanel card5 = new JPanel();
        HowToPlayPanel5 htp5 = new HowToPlayPanel5();
        card5.setLayout(null);
        card5.add(htp5);   
        card5.setBackground(Color.BLACK);
        htp5.setSize(800,350);
        htp5.setLocation(0,0);
        card5.add(button5B);
        button5B.setSize(100,50);
        button5B.setLocation(100, 350);
        
      //Create the panel that contains the "cards".

        cards = new JPanel(new CardLayout());

        cards.add(card1, "Card 1");
        cards.add(card2, "Card 2");
        cards.add(card3, "Card 3");
        cards.add(card4, "Card 4");
        cards.add(card5, "Card 5");

        frameA.getContentPane().add(cards); 
        frameA.setVisible(true); //Make JFrame visible
    }

	public void actionPerformed(ActionEvent evt)
	{
		String command = evt.getActionCommand();//get name of button pressed
		if(command.equals("Next Page"))//conditional to determine which button was clicked and how to react
		{
			//System.out.println("Slide 1");
			CardLayout cardLayout = (CardLayout) cards.getLayout();
			cardLayout.next(cards);
		}
		if(command.equals("Previous Page"))//conditional to determine which button was clicked and how to react
		{
			//System.out.println("Slide 1");
			CardLayout cardLayout = (CardLayout) cards.getLayout();
			cardLayout.previous(cards);
		}
	}
	
	class HowToPlayPanel extends JPanel
	{
		Image orbitalImg;
	
		public HowToPlayPanel()
		{
				orbitalImg = Utilities.loadImage("Orbital.png");
			
				System.out.println("How to Play Page 1");//Create Pop Up
				setLayout(null);

		}
		
		public void paintComponent(Graphics g)
		{
			setBackground(Color.BLACK);
			super.paintComponent(g);
			g.drawImage(orbitalImg, 180, 205, 400, 150,this);
			g.setColor(Color.CYAN);
			g.setFont(new Font("Times New Roman", Font.BOLD, 30));
			g.drawString("Welcome to Atomzzz!",250,50);
			g.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			g.drawString("The electron filling game!", 300, 80);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			g.drawString("----------------------------------------------------------------------------------------------------",0,-50);
			g.drawString("The oribital is located around the nucleus, Its job is to hold electrons. In this simulation green, ",110,120);
			g.drawString("ovals represent S orbitals which are the closest, blue ovals represent P orbitals which are the",110,140);
			g.drawString("furthest away, and red ovals which represent D orbitals which lie in the middle. There are no D",110,160); 
			g.drawString("orbitals in the 1st and 2nd energy level. Using your mouse, drag and drop the electron in the correct", 110,180);
			g.drawString("orbital!",110,200);
			g.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			g.drawString("Note:  Once an orbital is filled, its contents cannot be changed.",160,200);
		}
	}
	
	class HowToPlayPanel2 extends JPanel
	{
		Image electronImg;
	
		public HowToPlayPanel2()
		{
			electronImg = Utilities.loadImage("Electron.png");
			
				System.out.println("How to Play Page 2");//Create Pop Up
				setLayout(null);

		}
		
		public void paintComponent(Graphics g)
		{
			setBackground(Color.BLACK);
			super.paintComponent(g);
			g.drawImage(electronImg, 200, 200, 400, 150,this);
			g.setColor(Color.CYAN);
			g.setFont(new Font("Times New Roman", Font.BOLD, 30));
			g.drawString("How to Play",320,50);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			g.drawString("Page 2", 380, 80);
			g.drawString("----------------------------------------------------------------------------------------------------",0,-50);
			g.drawString("Electron are small negative charged particles located in the orbitals. They have spins, either UP",110,120);
			g.drawString("spins or DOWN spins. Electrons fill orbitals.",110,140);
			g.drawString("",110,160); 
			g.drawString("",110,180);
			g.drawString("",110,200);			
		}
	}
	
	class HowToPlayPanel3 extends JPanel
	{
		Image element;
	
		public HowToPlayPanel3()
		{
			element = Utilities.loadImage("Element.png");
			
				System.out.println("How to Play Page 3");//Create Pop Up
				setLayout(null);

		}
		
		public void paintComponent(Graphics g)
		{
			setBackground(Color.BLACK);
			super.paintComponent(g);
			g.drawImage(element, 300, 205, 250, 150,this);
			g.setColor(Color.CYAN);
			g.setFont(new Font("Times New Roman", Font.BOLD, 30));
			g.drawString("How to Play",320,50);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			g.drawString("Page 3", 380, 80);
			g.drawString("----------------------------------------------------------------------------------------------------",0,-50);
			g.drawString("Using the below rules fill in the orbitals for the coresponding element shown in the top left.",110,120);
			g.drawString("   -The Afbau Principle states that electrons fill the lower energy orbital (belonging to a lower",110,160);
			g.drawString("    principle energy level) prior to moving to higher one. Therefore, 1s will fill before 2s before 2p, ",110,180); 
			g.drawString("    and so on. However, there are exceptions. 3d fills after 4s, and 4d after 5s.",110,200);
			//g.drawString("   -Pauli's Exclusion Principle says that electrons cannot have all the same \"quantum numbers\",",110,200);
		}
	}
	
	class HowToPlayPanel4 extends JPanel
	{
		Image PauliImg;
	
		public HowToPlayPanel4()
		{
			PauliImg = Utilities.loadImage("Pauli.png");
			
				System.out.println("How to Play Page 4");//Create Pop Up
				setLayout(null);

		}
		
		public void paintComponent(Graphics g)
		{
			setBackground(Color.BLACK);
			super.paintComponent(g);
			g.drawImage(PauliImg, 205, 205, 400, 150,this);
			g.setColor(Color.CYAN);
			g.setFont(new Font("Times New Roman", Font.BOLD, 30));
			g.drawString("How to Play",320,50);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			g.drawString("Page 4", 380, 80);
			g.drawString("----------------------------------------------------------------------------------------------------",0,-50);
			g.drawString("   -Pauli's Exclusion Principle states that electrons cannot have all the same \"quantum numbers\",",110,120);
			g.drawString("    meaning that electrons in the same orbital must have different spins (Up and Down).",110,140);
			//g.drawString("",110,180); 
			//g.drawString("",110,200);
			//g.drawString("   -Pauli's Exclusion Principle says that electrons cannot have all the same \"quantum numbers\",",110,200);
		}
	}
	
	class HowToPlayPanel5 extends JPanel
	{
		Image HundsImg;
	
		public HowToPlayPanel5()
		{
			HundsImg = Utilities.loadImage("Hunds.png");
			
				System.out.println("How to Play Page 5");//Create Pop Up
				setLayout(null);

		}
		
		public void paintComponent(Graphics g)
		{
			setBackground(Color.BLACK);
			super.paintComponent(g);
			g.drawImage(HundsImg, 205, 205, 400, 150,this);
			g.setColor(Color.CYAN);
			g.setFont(new Font("Times New Roman", Font.BOLD, 30));
			g.drawString("How to Play",320,50);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			g.drawString("Page 5", 380, 80);
			g.drawString("----------------------------------------------------------------------------------------------------",0,-50);
			g.drawString("   -Lastly, Hund's Rule states that electrons prefer to be alone in orbitals of the same energy",110,120);
			g.drawString("    before \"doubling up\", and the ones that are alone have the same spin. This means that when ",110,140);
			g.drawString("    filling the 2p sublevel, you should put one electron in each 2p orbital, all with the same spin,",110,160); 
			g.drawString("    and then double them up after they are all filled.",110,180);
			g.setFont(new Font("Times New Roman", Font.BOLD, 15));
			g.drawString("You are now ready! Good Luck!",300,200);
		}
	}
}