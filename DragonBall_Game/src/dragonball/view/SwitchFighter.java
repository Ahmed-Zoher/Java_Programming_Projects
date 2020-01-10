package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SwitchFighter extends JPanel{
	private  JComboBox fighters;
	private JButton Return;
     public JComboBox getFighters() {
		return fighters;
	}
	public JButton getReturn() {
		return Return;
	}
	
	public SwitchFighter(String []names) throws IOException{
	/* super("Switch Fighter");
	 ImageIcon img = new ImageIcon("tv_icon.jpg");
		this.setIconImage(img.getImage());
		JPanel p=new JPanel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);*/
		//ImagePanel p = new ImagePanel(getClass().getResource("11.gif"));
		JPanel p=new JPanel();
		p.setBackground(Color.black);
		p.setPreferredSize(new Dimension(1400,1000));
		
		fighters = new JComboBox(names);
        fighters.setFont(new Font("Castellar",Font.BOLD,14));
		fighters.setForeground(Color.RED);
		fighters.setBackground(Color.WHITE);
		
		Return=new JButton("Back");
		Return.setFont(new Font("Castellar",Font.BOLD,14));
		Return.setForeground(Color.RED);
		Return.setBackground(Color.WHITE);
		
		p.setLayout(new FlowLayout());
		p.add(fighters);
		p.add(Return);
		add(p);
		
		setVisible(true);
	}
	public static void main(String[] args) throws IOException {
		String[]a={"ahmed","tharwat","omar"};
		SwitchFighter x=new SwitchFighter(a);
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
