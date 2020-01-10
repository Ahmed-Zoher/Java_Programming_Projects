package dragonball.view;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class Battleview extends JPanel {
private JProgressBar foehealth;
private JProgressBar mehealth;
private JProgressBar foestamina;
private JProgressBar mestamina;

public void setUltimateattack(JComboBox ultimateattack) {
	Ultimateattack = ultimateattack;
}
private JButton Physicalattack;
private JComboBox Ultimateattack;
private JComboBox Superattack;
private JButton block;
private JButton Use;

private JProgressBar meKi;
private JProgressBar foeKi;
private JPanel Controller;
private JLabel me;
private JLabel foe;
private JPanel Right;
private JPanel Left;
private JPanel Rightup;

private JLabel Rightdown;
private JPanel Leftup;
private JLabel Leftdown;
private JLabel MyTurn;

public JProgressBar getFoehealth() {
	return foehealth;
}
public JProgressBar getMehealth() {
	return mehealth;
}
public JProgressBar getFoestamina() {
	return foestamina;
}
public JProgressBar getMestamina() {
	return mestamina;
}
public JButton getPhysicalattack() {
	return Physicalattack;
}

public JButton getBlock() {
	return block;
}
public void setSuperattack(JComboBox superattack) {
	Superattack = superattack;
}
public JButton getUse() {
	return Use;
}
public JProgressBar getMeKi() {
	return meKi;
}
public JProgressBar getFoeKi() {
	return foeKi;
}
public JPanel getController() {
	return Controller;
}
public JLabel getMe() {
	return me;
}
public JLabel getFoe() {
	return foe;
}
public JPanel getRight() {
	return Right;
}
public JPanel getLeft() {
	return Left;
}
public JPanel getRightup() {
	return Rightup;
}
public JLabel getRightdown() {
	return Rightdown;
}
public JPanel getLeftup() {
	return Leftup;
}
public JLabel getLeftdown() {
	return Leftdown;
}
public JLabel getMyTurn() {
	return MyTurn;
}
public Battleview() throws IOException{
/*	super("DRAGON BALL");
	ImageIcon img = new ImageIcon("tv_icon.jpg");
	this.setIconImage(img.getImage());
	getContentPane().setLayout(new BorderLayout());
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(1000,600);
	setExtendedState(JFrame.MAXIMIZED_BOTH);*/

	
	
	//super("Dragon Ball battle");
	//setDefaultCloseOperation(EXIT_ON_CLOSE);
	//setSize(1375,750);
	Dimension d=new Dimension(100,10);
	setLayout(new BorderLayout());
	ImagePanel p = new ImagePanel(getClass().getResource("battle1.jpg"));
	add(p,BorderLayout.CENTER);
	p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
	block =new JButton("Block");
	block.setFont(new Font("Castellar",Font.BOLD,13));
	block.setForeground(Color.GREEN);
	block.setBackground(Color.BLACK);
	block.setPreferredSize(d);
	
	Use=new JButton("Use");
	Use.setFont(new Font("Castellar",Font.BOLD,13));
	Use.setForeground(Color.GREEN);
	Use.setBackground(Color.BLACK);
	Use.setPreferredSize(d);
	Use.setToolTipText("use senzu beans");
	
	Physicalattack=new JButton("Physical Attack");
	Physicalattack.setFont(new Font("Castellar",Font.BOLD,13));
	Physicalattack.setForeground(Color.RED);
	Physicalattack.setBackground(Color.BLACK);
	Physicalattack.setPreferredSize(new Dimension(200,30));
	String[] chooseSA={"choose a super attack"};
	Superattack=new JComboBox(chooseSA);
	Superattack.setName("superattack");
	Superattack.setFont(new Font("Castellar",Font.BOLD,13));
	Superattack.setForeground(Color.RED);
	Superattack.setBackground(Color.BLACK);
	
	String[] chooseUA={"choose an ultimate attack"};
	
	Ultimateattack=new JComboBox(chooseUA);
	Ultimateattack.setName("Ultimate attack");
	Ultimateattack.setFont(new Font("Castellar",Font.BOLD,13));
	Ultimateattack.setForeground(Color.RED);
	Ultimateattack.setBackground(Color.BLACK);

	
	Controller =new JPanel();
	Controller.setLayout(new BoxLayout(Controller,BoxLayout.X_AXIS));
	Physicalattack.setAlignmentY(CENTER_ALIGNMENT);
	Ultimateattack.setAlignmentY(CENTER_ALIGNMENT);
	Superattack.setAlignmentY(CENTER_ALIGNMENT);
	Use.setAlignmentY(CENTER_ALIGNMENT);
	block.setAlignmentY(CENTER_ALIGNMENT); 
	
	Controller.add(Physicalattack);
	Controller.add(Superattack);
	Controller.add(Ultimateattack);
	Controller.add(block);
	Controller.add(Use);
	
	add(Controller,BorderLayout.SOUTH);
	me=new JLabel("Name:Youssef"+" "+"Level :x");
	me.setFont(new Font("Castellar",Font.BOLD,12));
	me.setForeground(Color.BLACK);
	
	foe=new JLabel("Name:Slim"+" "+"Level: xxxx");
	foe.setFont(new Font("Castellar",Font.BOLD,12));
	foe.setForeground(Color.BLACK);
	//foe.setBackground(Color.black);
	mehealth=new JProgressBar();
	mehealth.setStringPainted(true);
	mehealth.setForeground(Color.GREEN);
	mehealth.setString("Health:");
	
	foehealth=new JProgressBar();
	foehealth.setStringPainted(true);
	foehealth.setForeground(Color.GREEN);
	foehealth.setString("Health:");
	
	mestamina=new JProgressBar();
	mestamina.setStringPainted(true);
	mestamina.setForeground(Color.YELLOW);
	mestamina.setString("stamina:");
	
	foestamina=new JProgressBar();
	foestamina.setStringPainted(true);
	foestamina.setForeground(Color.YELLOW);
	foestamina.setString("stamina:");
	
	foeKi=new JProgressBar();
	foeKi.setStringPainted(true);
	foeKi.setForeground(Color.ORANGE);
	foeKi.setString("Ki:");
	
	meKi=new JProgressBar();
	meKi.setStringPainted(true);
	meKi.setForeground(Color.ORANGE);
	meKi.setString("Ki:");
	
	Rightup=new JPanel();
	Rightup.setLayout(new BoxLayout(Rightup,BoxLayout.Y_AXIS));
	Rightup.add(me);
	Rightup.add(mehealth);
	
	Rightup.add(meKi);
	Rightup.add(mestamina);
	
	Dimension h=new Dimension(100,100);
	
	MyTurn =new JLabel("Your Turn");
	MyTurn.setFont(new Font("Castellar",Font.BOLD,22));
	MyTurn.setForeground(Color.BLUE);
    MyTurn.setBackground(new Color(0,0,0,0));
	
	Rightup.add(MyTurn);
	Rightup.setPreferredSize(h);
	Rightup.setBackground(new Color(0,0,0,0));
	
	//Leftup.setBackground(new Color(0,0,0,0));
	//ImageIcon f=new ImageIcon("f.jpg");
	
	
	Rightdown=new JLabel();

	//
	//Rightdown.setIcon(f);
	Leftup=new JPanel();
	
	
	Leftup.setLayout(new BoxLayout(Leftup,BoxLayout.Y_AXIS));
	Leftup.add(foe);
	Leftup.add(foehealth);
    Leftup.add(foeKi);
	Leftup.add(foestamina);
	Leftup.setBackground(new Color(0,0,0,0));
	
	Leftdown=new JLabel();
	
	Right=new JPanel();
	Right.setLayout(new BorderLayout());
	Right.add(Rightup,BorderLayout.NORTH);
	Right.add(Rightdown, BorderLayout.SOUTH);
	
	Left=new JPanel();
	Left.setSize(100, 100);
	Left.setLayout(new BorderLayout());
	Left.add(Leftup,BorderLayout.NORTH);
	Left.add(Leftdown, BorderLayout.SOUTH);
	
	Right.setAlignmentX(LEFT_ALIGNMENT);
	
	Controller.setAlignmentX(CENTER_ALIGNMENT);
	Controller.setAlignmentY(BOTTOM_ALIGNMENT);
	
	Rightup.setAlignmentX(RIGHT_ALIGNMENT);
	Rightup.setAlignmentY(TOP_ALIGNMENT);
	
	Leftup.setAlignmentX(LEFT_ALIGNMENT);
	Leftup.setAlignmentY(TOP_ALIGNMENT);
	
	Right.setBackground(new Color(0,0,0,0));
	Left.setBackground(new Color(0,0,0,0));
	Right.setSize(100,100);
	
    p.add(Right);
    p.add(Left);

	add(p,BorderLayout.CENTER);
	
	setVisible(true);
	
}
public JComboBox getUltimateattack() {
	return Ultimateattack;
}
public JComboBox getSuperattack() {
	return Superattack;
}
public static void main(String[] args) throws IOException {
	Battleview x=new Battleview();
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
