package dragonball.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class StartWindow extends JPanel{
	//private JPanel content;
	private  JButton newGame;
	private JButton loadGame;
public JButton getNewGame() {
		return newGame;
	}
	public JButton getLoadGame() {
		return loadGame;
	}
	//	private Image image;
	public StartWindow() throws IOException{
		
	/*	super("DRAGON BALL");
		ImageIcon img = new ImageIcon("tv_icon.jpg");
		this.setIconImage(img.getImage());
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		*/
		
		
		ImagePanel p = new ImagePanel(getClass().getResource("Start.gif"));
		p.setPreferredSize(new Dimension(1400,1000));
		
		//p.setMinimumSize(new Dimension(1000,600));
	
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		
	
		newGame=new JButton("New Game");
		newGame.setBounds(800,800,150,150);
		newGame.setFont(new Font("Castellar",Font.BOLD,32));
		newGame.setForeground(Color.RED);
		newGame.setBackground(Color.BLACK);
		newGame.setAlignmentX(CENTER_ALIGNMENT);
		
		
		loadGame=new JButton("Load Game");
		loadGame.setAlignmentX(CENTER_ALIGNMENT);
		loadGame.setBounds(800,800,150,150);
		loadGame.setFont(new Font("Castellar",Font.BOLD,32));
		loadGame.setForeground(Color.RED);
		loadGame.setBackground(Color.BLACK);
		
		Dimension d = new Dimension(0, 200);
		p.add(Box.createRigidArea(d));
		p.add(newGame);
		p.add(Box.createRigidArea(new Dimension(0, 100)));
		p.add(loadGame);
		loadGame.setToolTipText("Press here only if you have a previously saved game");
		
		
	
		add(p);
	
		setVisible(true);
		
		
		
	}
	public static void main(String[] args) throws IOException {
		StartWindow x=new StartWindow();
		
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
