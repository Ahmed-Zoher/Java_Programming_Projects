package dragonball.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class ImagePanel extends JComponent{
	private Image backgroundImage;
	public ImagePanel(URL filename) throws IOException {
		
		backgroundImage = new ImageIcon(filename).getImage();
		setSize(1400, 1000);
		setPreferredSize(new Dimension(1000, 600));
		setMinimumSize(new Dimension(1000, 600));
		setMaximumSize(new Dimension(1000, 600));
		
	  }

	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(backgroundImage, 0, 0, 1400,800,this);
	}
}

	

