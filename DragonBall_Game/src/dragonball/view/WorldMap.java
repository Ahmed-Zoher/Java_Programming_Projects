package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class WorldMap extends JPanel{
private	 JPanel pnl;
 private   JButton create;
private	 JButton upgrade;
private	 JButton Save;
private JButton Assign;
private JButton Start;
private JButton boss;
public JButton getBoss() {
	return boss;
}
public JButton getStart() {
	return Start;
}
public JButton getAssign() {
	return Assign;
}
private String type;
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
private JComboBox availableFighters;
private ArrayList<JButton>buttons;
private JLabel details;
private String[] available;
private	 JButton SwitchFighter;
private JButton Exit;
private JButton previous;
private JButton current;


	public JButton getPrevious() {
	return previous;
}
public JButton getCurrent() {
	return current;
}
	public JButton getExit() {
	return Exit;
}
	public JPanel getPnl() {
	return pnl;
}
public JButton getCreate() {
	return create;
}
public JButton getUpgrade() {
	return upgrade;
}
public JButton getSave() {
	return Save;
}
public JComboBox getAvailableFighters() {
	return availableFighters;
}
public ArrayList<JButton> getButtons() {
	return buttons;
}
public JLabel getDetails() {
	return details;
}
public String[] getAvailable() {
	return available;
}
public JButton getSwitchFighter() {
	return SwitchFighter;
}
	public WorldMap() throws IOException{
		/*super("DRAGON BALL");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension d=new Dimension(1000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,600);*/
		this.setLayout(new BorderLayout(  ));
		Dimension d=new Dimension(1000,1000);
		this.setPreferredSize(new Dimension(1000,600));
		
				pnl =new JPanel();
		
		pnl.setLayout(new GridLayout(0,10));
		
		add(pnl, BorderLayout.CENTER);
		//pnl.setBackground(Color.BLACK);
		
		buttons=new ArrayList<>(170);
		details=new JLabel(type);
		details.setSize(d);
		details.setBackground(Color.BLACK);
		 details.setFont(new Font("Castellar",Font.PLAIN,9));
			details.setForeground(Color.BLACK);
		add(details,BorderLayout.NORTH);
		
		for(int i=0;i<100;i++){
			JButton a=new JButton("Button "+i);
			a.setForeground(Color.white);
			a.setBackground(Color.WHITE);
			a.setText("");
			//a.setOpaque(false);
			//a.setContentAreaFilled(false);
			//a.setBorderPainted(false);
			
		
		buttons.add(a);
		
			pnl.add(a);
			if(i==99){
				a.setIcon(new ImageIcon(getClass().getResource("goku.png")));
				a.setBackground(Color.WHITE);
				a.setText(null);
				current=a;
				previous=a;
				Start=a;
			}
			if(i==0){
				a.setIcon(new ImageIcon(getClass().getResource("Boss.jpg")));
				a.setBackground(Color.WHITE);
				a.setText(null);
				boss=a;
				
			}
			
			 
			
				}
		
		
		
		
		
		    create=new JButton("Create");
		    create.setFont(new Font("Castellar",Font.BOLD,22));
			create.setForeground(Color.RED);
			create.setBackground(Color.white);
			
			Assign=new JButton("Assign Attacks");
			Assign.setFont(new Font("Castellar",Font.BOLD,22));
				Assign.setForeground(Color.RED);
				Assign.setBackground(Color.white);
			
			Save=new JButton("Save");
			Save.setFont(new Font("Castellar",Font.BOLD,22));
			Save.setForeground(Color.RED);
			Save.setBackground(Color.white);
			
			upgrade=new JButton("Upgrade");
			upgrade.setFont(new Font("Castellar",Font.BOLD,22));
			upgrade.setForeground(Color.RED);
			upgrade.setBackground(Color.white);
			
			Exit=new JButton("Exit");
			Exit.setFont(new Font("Castellar",Font.BOLD,22));
			Exit.setForeground(Color.RED);
			Exit.setBackground(Color.white);
			
			SwitchFighter=new JButton("Switch Fighter");
			SwitchFighter.setFont(new Font("Castellar",Font.BOLD,22));
			SwitchFighter.setForeground(Color.RED);
			SwitchFighter.setBackground(Color.white);
		
			JPanel b=new JPanel(new FlowLayout());
		 
		 b.setBackground(Color.black);
		 b.add(create);
		 b.add(upgrade);
		 b.add(SwitchFighter);
		 b.add(Assign);
		 b.add(Save);
		 b.add(Exit);
		 
		
		 add(b,BorderLayout.SOUTH);
		
		 
		setVisible(true);
		
		/*ImageIcon img = new ImageIcon("tv_icon.jpg");
		this.setIconImage(img.getImage());*/
}
	
	public void setDetails(JLabel Details) {
		details.setText("");
		this.details = Details;
		Dimension d=new Dimension(1000,1000);
		Details.setSize(d);
		Details.setBackground(Color.BLACK);
		 Details.setFont(new Font("Castellar",Font.BOLD,11));
			Details.setForeground(Color.BLUE);
			
		add(Details,BorderLayout.NORTH);
		
	}
	public void setCurrent(JButton current) {
		this.current = current;
	}
	public static void main(String[] args) throws IOException {
		
			WorldMap x=new WorldMap();
			
			JFrame f=new JFrame("DRAGON BALL");
			ImageIcon img = new ImageIcon("tv_icon.jpg");
			f.setIconImage(img.getImage());
			f.getContentPane().setLayout(new BorderLayout());
			f.setSize(1000,600);
		
		
			f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        f.add(x);

	         f.setVisible(true);

			
		
			// TODO Auto-generated catch block
			
	}
}
