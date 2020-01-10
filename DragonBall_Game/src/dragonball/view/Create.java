package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Create extends JPanel {
	private  JComboBox race;
    private JTextField fightername;
    public JComboBox getRace() {
		return race;
	}
	public JTextField getFightername() {
		return fightername;
	}
	public JButton getReturn() {
		return Return;
	}
	private  JButton Return;
    
	public Create() {
		/* super("Create New Fighter");
   	  
	  		JPanel p=new JPanel();
	  		setDefaultCloseOperation(EXIT_ON_CLOSE);
	  		setSize(400,300);
	  	//	setExtendedState(JFrame.MAXIMIZED_BOTH);*/
		JPanel p=new JPanel();
		p.setPreferredSize(new Dimension(1400,1000));
	      	fightername =new JTextField("PLEASE ENTER FIGHTER NAME",20);
	     // 	fightername.setPreferredSize(new Dimension(d));
	      	
	      	fightername.setFont(new Font("Castellar",Font.BOLD,14));
			fightername.setForeground(Color.RED);
			fightername.setBackground(Color.WHITE);
			 Return=new JButton("Return");
			Return.setFont(new Font("Castellar",Font.BOLD,14));
			Return.setForeground(Color.RED);
			Return.setBackground(Color.WHITE);
			
			String[] players={"Saiyan","Namekian","Earthling","Frieza","Majin"};
			race = new JComboBox(players);
	      
	      	
	      	race.setFont(new Font("Castellar",Font.BOLD,14));
			race.setForeground(Color.RED);
			race.setBackground(Color.WHITE);
			
			
	     p.setLayout(new FlowLayout());	
	     p.add(fightername);
	     p.add(race);
	     p.add(Return);
	     p.setBackground(Color.BLACK);
	     add(p);
	     setVisible(true);
	   //  ImageIcon img = new ImageIcon("tv_icon.jpg");
		//	this.setIconImage(img.getImage());
	      
	      //	fightername.setMaximumSize(d);
	  		
	  		
	}
	public static void main(String[] args) {
		Create x=new Create();
		ImageIcon img = new ImageIcon("tv_icon.jpg");
		JFrame f=new JFrame("DRAGON BALL");
		f.setIconImage(img.getImage());
		f.getContentPane().setLayout(new BorderLayout());
		f.setSize(1000,600);
	
	
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.add(x);

         f.setVisible(true);
	}
}
