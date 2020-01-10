package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DragonWindow extends JPanel{
	private JButton senzuBeans;
	private JButton abilityPoints;
	private JButton superAttack;
 private JButton ultimateAttack;
private	 JLabel choose;
	public JButton getSenzuBeans() {
	return senzuBeans;
}
public JButton getAbilityPoints() {
	return abilityPoints;
}
public JButton getSuperAttack() {
	return superAttack;
}
public JButton getUltimateAttack() {
	return ultimateAttack;
}
public JLabel getChoose() {
	return choose;
}
	public DragonWindow() throws IOException{
	/*super("Dragon Ball");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setSize(1375,750);
		setSize(1000,600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);*/
		ImagePanel p = new ImagePanel(getClass().getResource("dragon2.jpg"));
	//	p.setPreferredSize(new Dimension(1400,1000));
		p.setPreferredSize(new Dimension(1400,1100));
		setSize(1350,700);
		senzuBeans=new JButton("Senzu Beans");
		senzuBeans.setFont(new Font("Castellar",Font.BOLD,12));
		senzuBeans.setForeground(Color.GREEN);
		senzuBeans.setBackground(Color.DARK_GRAY);
		senzuBeans.setBounds(20,575,200,100);
		
		abilityPoints=new JButton("Ability Points");
		abilityPoints.setFont(new Font("Castellar",Font.BOLD,12));
		abilityPoints.setForeground(Color.RED);
		abilityPoints.setBackground(Color.DARK_GRAY);
		abilityPoints.setBounds(400,575,200,100);
		
		superAttack=new JButton("Super Attacks");
		superAttack.setFont(new Font("Castellar",Font.BOLD,12));
		superAttack.setForeground(Color.WHITE);
		superAttack.setBackground(Color.DARK_GRAY);
		superAttack.setBounds(780,575,200,100);
		
		ultimateAttack=new JButton("Ultimate Attacks");
		ultimateAttack.setFont(new Font("Castellar",Font.BOLD,12));
		ultimateAttack.setForeground(Color.LIGHT_GRAY);
		ultimateAttack.setBackground(Color.DARK_GRAY);
		ultimateAttack.setBounds(1120,575,200,100);
		
		choose=new JLabel("Choose Your Wish ;)");
		choose.setForeground(Color.WHITE);
		choose.setFont(new Font("Serif", Font.PLAIN, 40));
		choose.setBounds(500,475,600, 100);
		
		//ImageIcon img = new ImageIcon("tv_icon.jpg");
		//this.setIconImage(img.getImage());
		
		p.add(choose);
		p.add(ultimateAttack);
		p.add(superAttack);
		p.add(abilityPoints);
		p.add(senzuBeans);
		p.setLayout(null);
		add(p);
		setVisible(true);
	}
	public static void main(String[] args) throws IOException {
		DragonWindow x=new DragonWindow();
		JFrame f=new JFrame("DRAGON BALL");
		ImageIcon img = new ImageIcon("tv_icon.jpg");
		f.setIconImage(img.getImage());
		f.getContentPane().setLayout(new BorderLayout());
		f.setSize(1350,700);
	
		
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.add(x);

         f.setVisible(true);
	}

}
