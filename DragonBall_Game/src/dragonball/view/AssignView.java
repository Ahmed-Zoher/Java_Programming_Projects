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

public class AssignView extends JPanel{
	private  JComboBox oldsuperAttack;
	private JComboBox oldultimateattack;
	private  JComboBox newsuperAttack;
	private JComboBox newultimateattack;
	private JButton Sumbitsuper;
	private JButton Sumbitultimate;
	
	public AssignView(String [] a,String [] b,String[] c,String[]d){
		JPanel p=new JPanel();
		p.setPreferredSize(new Dimension(1400,1000));
	      
	    
			 Sumbitsuper=new JButton("Submit super attacks");
			Sumbitsuper.setFont(new Font("Castellar",Font.BOLD,14));
			Sumbitsuper.setForeground(Color.RED);
			Sumbitsuper.setBackground(Color.WHITE);
			Sumbitultimate=new JButton("Submit ultimate attacks");
			Sumbitultimate.setFont(new Font("Castellar",Font.BOLD,14));
			Sumbitultimate.setForeground(Color.RED);
			Sumbitultimate.setBackground(Color.WHITE);
			
			
newultimateattack = new JComboBox(d);
	      
	      	
			newultimateattack.setFont(new Font("Castellar",Font.BOLD,14));
			newultimateattack.setForeground(Color.RED);
			newultimateattack.setBackground(Color.WHITE);
oldultimateattack = new JComboBox(c);
	      
	      	
			oldultimateattack.setFont(new Font("Castellar",Font.BOLD,14));
			oldultimateattack.setForeground(Color.RED);
			oldultimateattack.setBackground(Color.WHITE);
			oldsuperAttack = new JComboBox(a);
	     // oldAttack.setToolTipText("choose the attack to be replace");
	      	
			oldsuperAttack.setFont(new Font("Castellar",Font.BOLD,14));
			oldsuperAttack.setForeground(Color.RED);
			oldsuperAttack.setBackground(Color.WHITE);
			
newsuperAttack = new JComboBox(b);
	      
	      	
			newsuperAttack.setFont(new Font("Castellar",Font.BOLD,14));
			newsuperAttack.setForeground(Color.RED);
			newsuperAttack.setBackground(Color.WHITE);
			
			
	     p.setLayout(new FlowLayout());	
	     p.add(oldsuperAttack);
	     p.add(newsuperAttack);
	     p.add(oldultimateattack);
	     p.add(newultimateattack);
	     p.add(Sumbitsuper);
	     p.add(Sumbitultimate);
	     p.setBackground(Color.BLACK);
	     add(p);
	     setVisible(true);
		
	}
	
	public JComboBox getOldsuperAttack() {
		return oldsuperAttack;
	}

	public JComboBox getOldultimateattack() {
		return oldultimateattack;
	}

	public JComboBox getNewsuperAttack() {
		return newsuperAttack;
	}

	public JComboBox getNewultimateattack() {
		return newultimateattack;
	}


	public JButton getSumbitsuper() {
		return Sumbitsuper;
	}

	public JButton getSumbitultimate() {
		return Sumbitultimate;
	}

	public static void main(String[] args) {
		String [] y={"7jd","de"};
		String []h={"kfg","jgf"};
		String [] t={"7jd","de"};
		String []z={"kfg","jgf"};
		AssignView x=new AssignView(y,h,t,z);
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
