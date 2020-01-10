package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class ChooseFighter extends JPanel{
   private  JPanel content;
  private     JComboBox fighters;
private    JTextField Name;
private       JTextField fightername;
private JButton Confirm;
      
      public JButton getConfirm() {
	return Confirm;
}
	public JPanel getContent() {
	return content;
}
public JComboBox getFighters() {
	return fighters;
}



public JTextField getNamee() {
	return Name;
}
public JTextField getFightername() {
	return fightername;
}
	public ChooseFighter() throws IOException{
    	/*  super("DRAGON BALL");
    	  
  		
  		setDefaultCloseOperation(EXIT_ON_CLOSE);
  		setSize(1000,600);
  		setExtendedState(JFrame.MAXIMIZED_BOTH);*/
  		Dimension d=new Dimension(300,50);
  		String[] players={"Saiyan","Namekian","Earthling","Frieza","Majin"};
      	ImagePanel p = new ImagePanel(getClass().getResource("Start.gif"));
      	p.setPreferredSize(new Dimension(1400,1000));
      	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
      	Name = new JTextField("PLEASE ENTER YOUR NAME",20);
      	fightername =new JTextField("PLEASE ENTER FIGHTER NAME",20);
      	fightername.setPreferredSize(new Dimension(d));
      	fightername.setFont(new Font("Castellar",Font.BOLD,14));
		fightername.setForeground(Color.BLACK);
		fightername.setBackground(Color.WHITE);
      			
      	
      	fightername.setMaximumSize(d);
      	fightername.setAlignmentX(CENTER_ALIGNMENT);
      	Name.setPreferredSize(new Dimension(d));
      	
      	Name.setMaximumSize(d);
      	Name.setAlignmentX(CENTER_ALIGNMENT);
      	Name.setFont(new Font("Castellar",Font.BOLD,14));
		Name.setForeground(Color.BLACK);
		Name.setBackground(Color.WHITE);
      	
      	fighters = new JComboBox(players);
      	fighters.setSize(d);
      	fighters.setPreferredSize(d);
      	fighters.setMaximumSize(d);
      	fighters.setAlignmentX(CENTER_ALIGNMENT);
      	fighters.setFont(new Font("Castellar",Font.BOLD,14));
		fighters.setForeground(Color.WHITE);
		fighters.setBackground(Color.BLACK);
      	
		 Confirm=new JButton("Confirm");
		 Confirm.setFont(new Font("Castellar",Font.BOLD,32));
		 Confirm.setForeground(Color.RED);
	     Confirm.setBackground(Color.white);
	 	Confirm.setAlignmentX(CENTER_ALIGNMENT);
	//	Confirm.setAlignmentY(BOTTOM_ALIGNMENT);
		
      	p.add(Name);
      	p.add(fighters);
      	p.add(fightername);
      	p.add(Confirm);
      	
      	
      	add(p);
    //	ImageIcon img = new ImageIcon("tv_icon.jpg");
		//this.setIconImage(img.getImage());
  		/*
  		//ImagePanel p = new ImagePanel(getClass().getResource("start.jpg"));
  		JPanel p=new JPanel();
  		//p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
  		fighters=new JComboBox(players);
  		fighters.setSize(d);
  		 
  		p.add(fighters,BorderLayout.CENTER);
  		name=new JTextField("PLEASE ENTER THE NAME",20);
  		
  		
  		name.setSize(d);
  		p.add(name,BorderLayout.EAST);
  		
  		
  		
  		add(p);
  		*/
  		setVisible(true);
  		
      }
      public static void main(String[]args) throws IOException{
    	  ChooseFighter x=new ChooseFighter();
    	 
  		
  		JFrame f=new JFrame("DRAGON BALL");
  		ImageIcon img = new ImageIcon("tv_icon.jpg");
  		f.setIconImage(img.getImage());
  		f.getContentPane().setLayout(new BorderLayout());
  		f.setSize(1000,600);
  	
  	
  		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
          f.add(x);

           f.setVisible(true);
  		
      }
      

}
