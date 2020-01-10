package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UpgradeWindow extends JPanel {
	private JLabel currentHealth;
	private JLabel currentphysicaldamage;
	private JLabel currentblastdamage;
	private JLabel currentki;
	private JLabel currentstamina;
	private JButton maxHealth;
	private JButton physicalDamage;
	private JButton blastDamage;
	private JButton maxKi;
	private JButton maxStamina;
	private JButton back;
	
	public JLabel getCurrentHealth() {
		return currentHealth;
	}
	public JLabel getCurrentphysicaldamage() {
		return currentphysicaldamage;
	}
	public JLabel getCurrentblastdamage() {
		return currentblastdamage;
	}
	public JLabel getCurrentki() {
		return currentki;
	}
	public JLabel getCurrentstamina() {
		return currentstamina;
	}
	public JButton getMaxHealth() {
		return maxHealth;
	}
	public JButton getPhysicalDamage() {
		return physicalDamage;
	}
	public JButton getBlastDamage() {
		return blastDamage;
	}
	public JButton getMaxKi() {
		return maxKi;
	}
	public JButton getMaxStamina() {
		return maxStamina;
	}
	public JButton getBack() {
		return back;
	}
	public UpgradeWindow() throws IOException{
		Toolkit t= Toolkit.getDefaultToolkit();
		int x =(int) t.getScreenSize().getWidth();
		int y=(int) t.getScreenSize().getHeight();
		this.setSize(x,y);
		ImagePanel p = new ImagePanel(getClass().getResource("upgrade.jpg"));
	    p.setPreferredSize(new Dimension(1400,1100));
		
		maxHealth=new JButton("Increase Health Points");
		maxHealth.setFont(new Font("Castellar",Font.BOLD,12));
		maxHealth.setForeground(Color.GREEN);
		maxHealth.setBackground(Color.DARK_GRAY);
		maxHealth.setBounds(0,20,200,50);
		p.add(maxHealth);
		
		currentHealth=new JLabel("Your current max health points is ");
		currentHealth.setBounds(220,0,600,100);
		currentHealth.setForeground(Color.black);
		currentHealth.setFont(new Font("Serif", Font.PLAIN,15));
		p.add(currentHealth);
		
		physicalDamage=new JButton("Increase Physical Damage");
		physicalDamage.setFont(new Font("Castellar",Font.BOLD,12));
		physicalDamage.setForeground(Color.red);
		physicalDamage.setBackground(Color.DARK_GRAY);
		physicalDamage.setBounds(0,100,200,50);
		p.add(physicalDamage);
		
		currentphysicaldamage=new JLabel("Your current physical damage is ");
		currentphysicaldamage.setBounds(220,80,600,100);
		currentphysicaldamage.setForeground(Color.black);
		currentphysicaldamage.setFont(new Font("Serif", Font.PLAIN,15));
		p.add(currentphysicaldamage);
		
		blastDamage=new JButton("Increase Blast Damage");
		blastDamage.setFont(new Font("Castellar",Font.BOLD,12));
		blastDamage.setForeground(Color.red);
		blastDamage.setBackground(Color.DARK_GRAY);
		blastDamage.setBounds(0,175,200,50);
		p.add(blastDamage);
		
		currentblastdamage=new JLabel("Your current blast damage is ");
		currentblastdamage.setBounds(220,160,600,100);
		currentblastdamage.setForeground(Color.black);
		currentblastdamage.setFont(new Font("Serif", Font.PLAIN,15));
		p.add(currentblastdamage);
		
		maxKi=new JButton("Increase Max Ki");
		maxKi.setFont(new Font("Castellar",Font.BOLD,12));
		maxKi.setForeground(Color.LIGHT_GRAY);
		maxKi.setBackground(Color.DARK_GRAY);
		maxKi.setBounds(0,250,200,50);
		p.add(maxKi);
		
		currentki=new JLabel("Your current max ki is ");
		currentki.setBounds(220,230,600,100);
		currentki.setForeground(Color.black);
		currentki.setFont(new Font("Serif", Font.PLAIN,15));
		p.add(currentki);
		
		maxStamina=new JButton("Increase Max Stamina");
		maxStamina.setFont(new Font("Castellar",Font.BOLD,12));
		maxStamina.setForeground(Color.WHITE);
		maxStamina.setBackground(Color.DARK_GRAY);
		maxStamina.setBounds(0,325,200,50);
		p.add(maxStamina);
		
		currentstamina=new JLabel("Your current max staimna is ");
		currentstamina.setBounds(220,300,600,100);
		currentstamina.setForeground(Color.black);
		currentstamina.setFont(new Font("Serif", Font.PLAIN,15));
		p.add(currentstamina);
		
		back=new JButton("Back to game");
		back.setFont(new Font("Castellar",Font.BOLD,12));
		back.setForeground(Color.WHITE);
		back.setBackground(Color.DARK_GRAY);
		back.setBounds(575,600,200,50);
		p.add(back);
		add(p);
		
		this.setLayout(null);
		this.setVisible(true);
	}
	public static void main(String[] args) throws IOException {
	
		UpgradeWindow x =new UpgradeWindow();
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
