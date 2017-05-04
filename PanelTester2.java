import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelTester2
{
	public static void main( String[] args )
	{
		PanelTester2 panelt2 = new PanelTester2();
		panelt2.runIt();
	}

	public void runIt()
	{
		JFrame frame = new JFrame("Atomzzz Alpha - Test Frame 2");
		frame.setSize( 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(0,0);
		frame.setResizable(false);
		MatchPanel mtPanel = new MatchPanel();
		frame.getContentPane().add( mtPanel );
		frame.setVisible(true);
	}
	
	//<<<<<<<<<<<<<<<<<<<<<<<<<<BEGIN CODE FOR MatchPanel>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	class MatchPanel extends JPanel implements ActionListener
	{
		private JButton option1;
		private JButton option2;
		private JButton option3;
		private JButton option4;
		
		public MatchPanel()
		{
			setLayout(null);
			
			option1 = new JButton("A");//Give buttons their names
			option2 = new JButton("B");
			option3 = new JButton("C");//Give buttons their names
			option4 = new JButton("D");
			
			option1.addActionListener(this);//Add listeners
			option2.addActionListener(this);
			option3.addActionListener(this);	
			option4.addActionListener(this);	
			
			add(option1);
			option1.setSize(100,100);
			option1.setLocation(0,0);
			
			add(option2);
			option2.setSize(100,100);
			option2.setLocation(0,100);
			
			add(option3);
			option3.setSize(100,100);
			option3.setLocation(100,0);
			
			add(option4);
			option4.setSize(100,100);
			option4.setLocation(100,100);
		}
		
		public void paintComponent(Graphics g)//Set background colour
		{
			setBackground(Color.CYAN);
			super.paintComponent (g);// draw background
		}

		
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();//get name of button pressed
			if(command.equals("A"))//conditional to determine which button was clicked and how to react
			{
				System.out.println("Option A");
			}
			if(command.equals("B"))//conditional to determine which button was clicked and how to react
			{
				System.out.println("Option B");
			}
			if(command.equals("C"))//conditional to determine which button was clicked and how to react
			{
				System.out.println("Option C");
			}
			if(command.equals("D"))//conditional to determine which button was clicked and how to react
			{
				System.out.println("Option D");
			}
		}
	}		
}