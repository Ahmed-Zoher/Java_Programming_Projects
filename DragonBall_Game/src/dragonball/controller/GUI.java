 package dragonball.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.*;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.*;
import dragonball.model.dragon.Dragon;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.model.game.GameState;
import dragonball.view.*;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.util.Timer;
import java.util.TimerTask;

public class GUI extends JFrame implements ActionListener, GameListener,KeyListener{
 private Game g;
 private UpgradeWindow upgradewindow;
 Timer t=new Timer();
 private StartWindow start;
private  ChooseFighter choose;
private WorldMap worldMap;
private DragonWindow dragonWindow;
private Create create;
private ArrayList<SuperAttack> sa;
private ArrayList<UltimateAttack>ua;
private SwitchFighter switchFighter;
private Battleview battleView;
private String fighter ;
private String fighterName;
private Fighter x;
private Battle b;
private Dragon d;
private Fighter Foe;
private AssignView assign;
private int tarxp=0;

 
 
 
 public GUI() throws IOException{
	 
	    super("DRAGON BALL");
		ImageIcon img = new ImageIcon("tv_icon.jpg");
		this.setIconImage(img.getImage());
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		g=new Game();
		g.setListener(this);
		
		 start=new StartWindow();
		 add(start);
		 start.getNewGame().addActionListener(this);
		 start.getLoadGame().addActionListener(this);
		
		 choose=new ChooseFighter();
	     choose.getFighters().addActionListener(this);
	     choose.getNamee().addActionListener(this);
	     choose.getFightername().addActionListener(this);
	     choose.getConfirm().addActionListener(this);
	     
	     worldMap=new WorldMap();
	     worldMap.getCreate().addActionListener(this);
	     worldMap.getUpgrade().addActionListener(this);
	     worldMap.getSave().addActionListener(this);
	     worldMap.getExit().addActionListener(this);
	     worldMap.getSwitchFighter().addActionListener(this);
	   worldMap.getAssign().addActionListener(this);
	     
	     for(int i=0;i<worldMap.getButtons().size();i++){
	    	 worldMap.getButtons().get(i).addKeyListener(this);
	     }
	     
	     battleView=new Battleview();
	     battleView.getUse().addActionListener(this);
	     battleView.getBlock().addActionListener(this);
	     battleView.getPhysicalattack().addActionListener(this);
	     battleView.getSuperattack().addActionListener(this);
	     battleView.getUltimateattack().addActionListener(this);
	     
	     dragonWindow=new DragonWindow();
	     dragonWindow.getAbilityPoints().addActionListener(this);
	     dragonWindow.getUltimateAttack().addActionListener(this);
	     dragonWindow.getSuperAttack().addActionListener(this);
	     dragonWindow.getSenzuBeans().addActionListener(this);
	     
	     
	     create=new Create();
	     create.getRace().addActionListener(this);
	     create.getReturn().addActionListener(this);
	     create.getFightername().addActionListener(this);
	     
	     upgradewindow=new UpgradeWindow();
	     upgradewindow.getBack().addActionListener(this);
	     upgradewindow.getMaxHealth().addActionListener(this);
	     upgradewindow.getMaxKi().addActionListener(this);
	     upgradewindow.getPhysicalDamage().addActionListener(this);
	     upgradewindow.getBlastDamage().addActionListener(this);
	     upgradewindow.getMaxStamina().addActionListener(this);
	     
	     
	     
	     
	    
	     
		 setVisible(true);
 }

@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource() instanceof JButton){
	
		JButton jb = (JButton) e.getSource();
	String x=jb.getText();
	switch(x){
	case "New Game":
		try {
			g=new Game();
			g.setListener(this);
			start.setVisible(false);
			this.add(choose);
			
			
			
			revalidate();
		} catch (MissingFieldException e1) {
			JOptionPane.showMessageDialog(new JFrame(),"Please ReInstall The Game", "Dialog",
			        JOptionPane.INFORMATION_MESSAGE);
		} catch (UnknownAttackTypeException e1) {
			JOptionPane.showMessageDialog(new JFrame(),"Please ReInstall The Game", "Dialog",
			        JOptionPane.INFORMATION_MESSAGE);
		}
		break;
	    case "Load Game":
	    	try {
				
				
				g.load("ahmed.ser");
				g.setListener(this);
				
			     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"    fighter: "+fighter+"     "+"Type :"+fighterName+"   Level :"+ g.getPlayer().getActiveFighter().getLevel()+"\n"+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"    Dragon ball: "+g.getPlayer().getDragonBalls()+  "    Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"   Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()+"   Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()));
			    
			     int f=g.getWorld().getPlayerRow();
			     int  h=g.getWorld().getPlayerColumn();
			     String t=""+f+h;
			    int u=Integer.parseInt(t);
			     worldMap.getCurrent().setIcon(null);
			     for(int i=0;i<100;i++){
			     if(i==u){
			     worldMap.getButtons().get(i).setIcon(new ImageIcon(getClass().getResource("goku.png")));
			     worldMap.setCurrent(worldMap.getButtons().get(i));
			     }
			     
			     }
			     
			   
			   worldMap.setVisible(true);
			   add(worldMap);
			   start.setVisible(false);
			   revalidate();
				
				
			} catch (ClassNotFoundException e2) {
				JOptionPane.showMessageDialog(new JFrame(),"No Previously Saved Game", "Dialog",
				        JOptionPane.ERROR_MESSAGE);
			} catch (IOException e2) {
				
				JOptionPane.showMessageDialog(new JFrame(),"No  Saved Game", "Dialog",
				        JOptionPane.ERROR_MESSAGE);
			}
		break;
	    case"Confirm": 
	    	g.getPlayer().setActiveFighter(new Saiyan("ahmed"));
	    	g.getPlayer().setName((choose.getNamee().getText()));
	    	 fighter=choose.getFightername().getText();
	    	 fighterName=(String)choose.getFighters().getSelectedItem();
	    	 switch(fighterName){
	    	 case"Saiyan":
	    		 Saiyan s=new Saiyan(fighter);
	    		 g.getPlayer().getFighters().add(s);
	    		 g.getPlayer().setActiveFighter(s);
	    		 break;
	    	 case"Frieza":
	    		 Frieza f =new Frieza(fighter); 
	    		 g.getPlayer().getFighters().add(f);
	    		 g.getPlayer().setActiveFighter(f);
	    		 break;
	    	 case"Majin":
	    		 Majin m=new Majin(fighter);
	    		 g.getPlayer().getFighters().add(m);
	    		 g.getPlayer().setActiveFighter(m);
	    		 break;
	    	 case"Namekian":
	    		 Namekian n=new Namekian(fighter);
	    		 g.getPlayer().getFighters().add(n);
	    		 g.getPlayer().setActiveFighter(n);
	    		 break;	 
	    	 case"Earthling":
	    		 Earthling ea=new Earthling(fighter);
	    		 g.getPlayer().getFighters().add(ea);
	    		 g.getPlayer().setActiveFighter(ea);
	    		 break;	}
	    	
	    
	    		 
	    	 
	    	 choose.setVisible(false);
		
	    this.add(worldMap);
	     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
	    	
	   // 	this.add(worldMap);
	    	 revalidate();
	    break;
	    
	    
	    case "Use":  	try {
		    b.use(g.getPlayer(), Collectible.SENZU_BEAN);
		    
			revalidate();
			battleView.getPhysicalattack().setEnabled(false);
			battleView.getBlock().setEnabled(false);
			battleView.getUse().setEnabled(false);
			battleView.getUltimateattack().setEnabled(false);
			battleView.getSuperattack().setEnabled(false);
			battleView.getMyTurn().setText("foe Turn");
			
			t.schedule(new TimerTask() {
				  @Override
				  public void run() {
					  
					  try {
						b.play();
						battleView.getPhysicalattack().setEnabled(true);
						battleView.getBlock().setEnabled(true);
						battleView.getUse().setEnabled(true);
						battleView.getUltimateattack().setEnabled(true);
						battleView.getSuperattack().setEnabled(true);
						battleView.getMyTurn().setText("your Turn");
					} catch (NotEnoughKiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    // Your database code here
				  }
				}, 000);
			


			} catch (NotEnoughSenzuBeansException e1) {
				JOptionPane.showMessageDialog(new JFrame(),"You do not have enough senzu beans ", "Dialog",
				        JOptionPane.ERROR_MESSAGE);
			}  break;
	    
			
		    case"Block":
		    	b.block();
		    	battleView.getPhysicalattack().setEnabled(false);
				battleView.getBlock().setEnabled(false);
				battleView.getUse().setEnabled(false);
				battleView.getUltimateattack().setEnabled(false);
				battleView.getSuperattack().setEnabled(false);
				battleView.getMyTurn().setText("foe Turn");
				
				t.schedule(new TimerTask() {
					  @Override
					  public void run() {
						  
						  try {
							b.play();
							battleView.getPhysicalattack().setEnabled(true);
							battleView.getBlock().setEnabled(true);
							battleView.getUse().setEnabled(true);
							battleView.getUltimateattack().setEnabled(true);
							battleView.getSuperattack().setEnabled(true);
							battleView.getMyTurn().setText("your Turn");
						} catch (NotEnoughKiException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    // Your database code here
					  }
					}, 800);
				


		    break;
		    case"Physical Attack":
		    	PhysicalAttack p=new PhysicalAttack();
		    	//music("PUNCH.wav");
			try {
				b.attack(p);
				battleView.getPhysicalattack().setEnabled(false);
				battleView.getBlock().setEnabled(false);
				battleView.getUse().setEnabled(false);
				battleView.getUltimateattack().setEnabled(false);
				battleView.getSuperattack().setEnabled(false);
				battleView.getMyTurn().setText("foe Turn");
				
				
				t.schedule(new TimerTask() {
					  @Override
					  public void run() {
						  
						  try {
							b.play();
							battleView.getPhysicalattack().setEnabled(true);
							battleView.getBlock().setEnabled(true);
							battleView.getUse().setEnabled(true);
							battleView.getUltimateattack().setEnabled(true);
							battleView.getSuperattack().setEnabled(true);
							battleView.getMyTurn().setText("your Turn");
						} catch (NotEnoughKiException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    // Your database code here
					  }
					}, 800);
				

			} catch (NotEnoughKiException e1) {
				JOptionPane.showMessageDialog(new JFrame(),"Your foe is an idiot ", "Dialog",
				        JOptionPane.ERROR_MESSAGE);
			}
			
			break;
	    
	    case "Senzu Beans":
	    	g.getPlayer().setSenzuBeans(g.getPlayer().getSenzuBeans()+d.getSenzuBeans());
	    	JOptionPane.showMessageDialog(new JFrame(),d.getSenzuBeans()+" Senzu Beans have been added to your Senzu Beans", "Dialog",
			        JOptionPane.INFORMATION_MESSAGE);
	    	dragonWindow.setVisible(false);
		     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
	    	worldMap.setVisible(true);
	    	
	    break;
	    
	    case("Ability Points"):
	    	g.getPlayer().getActiveFighter().setAbilityPoints(g.getPlayer().getActiveFighter().getAbilityPoints()+d.getAbilityPoints());
	    JOptionPane.showMessageDialog(new JFrame(),d.getAbilityPoints()+" Ability Points have been added to your fighter", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
	    dragonWindow.setVisible(false);
	     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
    	
	    worldMap.setVisible(true);
	    break;
	    
	    case "Super Attacks":
	    	g.getPlayer().getSuperAttacks().addAll(d.getSuperAttacks());
		    JOptionPane.showMessageDialog(new JFrame(),"New Super attacks have been added to your attacks", "Dialog",
			        JOptionPane.INFORMATION_MESSAGE);
		    dragonWindow.setVisible(false);
		     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
	    	
		    worldMap.setVisible(true);
		    break;
		     
	    case "Ultimate Attacks":
	    	g.getPlayer().getUltimateAttacks().addAll(d.getUltimateAttacks());
	    	 JOptionPane.showMessageDialog(new JFrame(),"New Ultimate attacks have been added to your attacks", "Dialog",
				        JOptionPane.INFORMATION_MESSAGE);
	    	    dragonWindow.setVisible(false);
	   	     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
		    	
		    	worldMap.setVisible(true);
	    	break;
	    	
	    case "Create":	
	    	worldMap.setVisible(false);
	    	add(create);
	    	break;
	    case "Back":
	    	String team=(String)switchFighter.getFighters().getSelectedItem();
	    	 String fj="";
	    	for(int i=0;i<g.getPlayer().getFighters().size();i++){
	    		if(g.getPlayer().getFighters().get(i).getName().equals(team)){
	    			g.getPlayer().setActiveFighter(g.getPlayer().getFighters().get(i));
	    			if(g.getPlayer().getFighters().get(i) instanceof Saiyan){
	    				fj="Saiyan";
	    			}else{
	    				if(g.getPlayer().getFighters().get(i) instanceof Frieza){
	    					fj="Frieza";
	    				}else{
	    					if(g.getPlayer().getFighters().get(i) instanceof Earthling){
	    						fj="Earthling";
	    					}else{
	    						if(g.getPlayer().getFighters().get(i) instanceof Majin){
	    							fj="Majin";
	    						}else{
	    							fj="Namekian";
	    						}
	    					}
	    				}
	    				
	    			}
	    		}
	    	}
	   
	    	  //	worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"\n"+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()));
		    	switchFighter.setVisible(false);
		   worldMap.setVisible(true);
		     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
			   
	    	
	    	break;
	    case "Exit":
	    	System.exit(0);break;
	    case "Return":
	    	String newf=create.getFightername().getText();
	    	String type1=(String)create.getRace().getSelectedItem();
	    	switch(type1){
	    	 case"Saiyan":
	    		 Saiyan s=new Saiyan(newf);
	    		 g.getPlayer().getFighters().add(s);
	    		 //g.getPlayer().setActiveFighter(s);
	    		 break;
	    	 case"Frieza":
	    		 Frieza f =new Frieza(newf); 
	    		 g.getPlayer().getFighters().add(f);
	    		 //g.getPlayer().setActiveFighter(f);
	    		 break;
	    	 case"Majin":
	    		 Majin m=new Majin(newf);
	    		 g.getPlayer().getFighters().add(m);
	    		 //g.getPlayer().setActiveFighter(m);
	    		 break;
	    	 case"Namekian":
	    		 Namekian n=new Namekian(newf);
	    		 g.getPlayer().getFighters().add(n);
	    		 //g.getPlayer().setActiveFighter(n);
	    		 break;	 
	    	 case"Earthling":
	    		 Earthling ea=new Earthling(newf);
	    		 g.getPlayer().getFighters().add(ea);
	    		// g.getPlayer().setActiveFighter(ea);
	    		 break;	}
	    	create.setVisible(false);
	    	worldMap.setVisible(true);
	    	break;
	    	
	    case "Save":
	    	try {
				g.save("ahmed.ser");
			} catch (IOException e2) {
				
				e2.printStackTrace();
			}
			
	    	break;
	    	
	    case "Switch Fighter":
	    	ArrayList<PlayableFighter>a=g.getPlayer().getFighters();
	    	String []b=new String[a.size()];
	    	for(int i=0;i<a.size();i++){
	    		b[i]=a.get(i).getName();
	    	}
		try {
			switchFighter=new SwitchFighter(b);
			switchFighter.getReturn().addActionListener(this);
			switchFighter.getFighters().addActionListener(this);
			worldMap.setVisible(false);
			add(switchFighter);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	    	break;
	    	
	   
	    	
	    case "Assign Attacks":
	    	ArrayList<SuperAttack>allsattacks=g.getPlayer().getSuperAttacks();
	    	ArrayList<UltimateAttack>alluattacks=g.getPlayer().getUltimateAttacks();
	    	ArrayList<SuperAttack>assigns=g.getPlayer().getActiveFighter().getSuperAttacks();
	    	ArrayList<UltimateAttack>assignu=g.getPlayer().getActiveFighter().getUltimateAttacks();
	    	String []alls=new String[allsattacks.size()];
	    	String []allu=new String[alluattacks.size()];
	    	String []asss=new String[assigns.size()+1];
	    	String []assu=new String[assignu.size()+1];
	    	for(int i=0;i<allsattacks.size();i++){
	    		alls[i]=allsattacks.get(i).getName();
	    	}
	    	for(int i=0;i<alluattacks.size();i++){
	    		allu[i]=alluattacks.get(i).getName();
	    	}
	    	asss[0]="none";
	    	
	    	for(int i=0;i<assigns.size();i++){
	    		
	    		asss[i+1]=assigns.get(i).getName();
	    	}
	    	assu[0]="none";
	    	for(int i=0;i<assignu.size();i++){
	    		assu[i+1]=assignu.get(i).getName();
	    	}
	    	
	    	    assign=new AssignView(alls,asss,allu,assu);
	    	    assign.getSumbitsuper().addActionListener(this);
	    	    assign.getSumbitultimate().addActionListener(this);
	    	    assign.getOldsuperAttack().addActionListener(this);
	    	    assign.getNewsuperAttack().addActionListener(this);
	    	    assign.getNewultimateattack().addActionListener(this);
	    	    assign.getOldultimateattack().addActionListener(this);
	    		worldMap.setVisible(false);
	    		add(assign);
	    
	    	break;
	    case"Submit super attacks":
	    	
	    	String tobereplacedsa=(String) assign.getNewsuperAttack().getSelectedItem();
	    	String tobeassignedsa=(String)assign.getOldsuperAttack().getSelectedItem();
	    	
	    	
	    for(int i=0;i<g.getPlayer().getSuperAttacks().size();i++){
	    	
	    	if(tobeassignedsa.equals(g.getPlayer().getSuperAttacks().get(i).getName())){
	    		
	    		if(tobereplacedsa.equals("none")){
	    			try {
						
	    				g.getPlayer().assignAttack(g.getPlayer().getActiveFighter(),g.getPlayer().getSuperAttacks().get(i) , null);
	    				battleView.getSuperattack().addItem(g.getPlayer().getSuperAttacks().get(i).getName());
					} catch (MaximumAttacksLearnedException e1) {
 						JOptionPane.showMessageDialog(new JFrame(),"You cannot add an attack", "Dialog",
 						        JOptionPane.ERROR_MESSAGE);
 					} catch (DuplicateAttackException e1) {
 						JOptionPane.showMessageDialog(new JFrame()," this attack already exists in your list", "Dialog",
 						        JOptionPane.INFORMATION_MESSAGE);
 					} catch (NotASaiyanException e1) {
 						JOptionPane.showMessageDialog(new JFrame()," you have to be a saiyan", "Dialog",
 						        JOptionPane.INFORMATION_MESSAGE);
 					}
	    		}else{
	    			
	    			for(int j=0;j<g.getPlayer().getActiveFighter().getSuperAttacks().size();j++){
	    				if(tobereplacedsa.equals(g.getPlayer().getActiveFighter().getSuperAttacks().get(j).getName())){
	    					try {
	    						
								g.getPlayer().assignAttack(g.getPlayer().getActiveFighter(),g.getPlayer().getSuperAttacks().get(i) , g.getPlayer().getActiveFighter().getSuperAttacks().get(j));
								
								battleView.getSuperattack().removeItem(tobereplacedsa);
								battleView.getSuperattack().addItem(g.getPlayer().getSuperAttacks().get(i).getName());
							} catch (MaximumAttacksLearnedException e1) {
		 						JOptionPane.showMessageDialog(new JFrame(),"You cannot add an attack", "Dialog",
		 						        JOptionPane.ERROR_MESSAGE);
		 					} catch (DuplicateAttackException e1) {
		 						JOptionPane.showMessageDialog(new JFrame()," this attack already exists in your list", "Dialog",
		 						        JOptionPane.INFORMATION_MESSAGE);
		 					} catch (NotASaiyanException e1) {
		 						JOptionPane.showMessageDialog(new JFrame()," you have to be a saiyan", "Dialog",
		 						        JOptionPane.INFORMATION_MESSAGE);
		 					}
	    				}
	    			}
	    		}
	    				
	    	}
	    } assign.setVisible(false);
	    	worldMap.setVisible(true);
	    		
	    	break;
	    case "Submit ultimate attacks":
String tobereplacedua=(String)assign.getNewultimateattack().getSelectedItem();
	    	
	    	String tobeassignedua=(String)assign.getOldultimateattack().getSelectedItem();
	    	
	    	 for(int i=0;i<g.getPlayer().getUltimateAttacks().size();i++){
	 	    	if(tobeassignedua.equals(g.getPlayer().getUltimateAttacks().get(i).getName())){
	 	    		if(tobereplacedua.equals("none")){
	 	    			try {
	 	    			
	 						g.getPlayer().assignAttack(g.getPlayer().getActiveFighter(),g.getPlayer().getUltimateAttacks().get(i) , null);
	 						battleView.getUltimateattack().addItem(g.getPlayer().getUltimateAttacks().get(i).getName());
	 					} catch (MaximumAttacksLearnedException e1) {
	 						JOptionPane.showMessageDialog(new JFrame(),"You cannot add an attack", "Dialog",
	 						        JOptionPane.ERROR_MESSAGE);
	 					} catch (DuplicateAttackException e1) {
	 						JOptionPane.showMessageDialog(new JFrame()," this attack already exists in your list", "Dialog",
	 						        JOptionPane.INFORMATION_MESSAGE);
	 					} catch (NotASaiyanException e1) {
	 						JOptionPane.showMessageDialog(new JFrame()," you have to be a saiyan", "Dialog",
	 						        JOptionPane.INFORMATION_MESSAGE);
	 					}
	 	    		}else{
	 	    			for(int j=0;j<g.getPlayer().getActiveFighter().getUltimateAttacks().size();j++){
	 	    				if(tobereplacedua.equals(g.getPlayer().getActiveFighter().getUltimateAttacks().get(j).getName())){
	 	    					try {
	 								
	 	    						g.getPlayer().assignAttack(g.getPlayer().getActiveFighter(),g.getPlayer().getUltimateAttacks().get(i) , g.getPlayer().getActiveFighter().getUltimateAttacks().get(j));
	 	    						battleView.getUltimateattack().addItem(g.getPlayer().getUltimateAttacks().get(i).getName());
									battleView.getUltimateattack().removeItem(tobereplacedua);
	 							} catch (MaximumAttacksLearnedException e1) {
	 		 						JOptionPane.showMessageDialog(new JFrame(),"You cannot add an attack", "Dialog",
	 		 						        JOptionPane.ERROR_MESSAGE);
	 		 					} catch (DuplicateAttackException e1) {
	 		 						JOptionPane.showMessageDialog(new JFrame()," this attack already exists in your list", "Dialog",
	 		 						        JOptionPane.INFORMATION_MESSAGE);
	 		 					} catch (NotASaiyanException e1) {
	 		 						JOptionPane.showMessageDialog(new JFrame()," you have to be a saiyan", "Dialog",
	 		 						        JOptionPane.INFORMATION_MESSAGE);
	 		 					}
	 	    				}
	 	    			}
	 	    		}
	 	    				
	 	    	}
	 	    }assign.setVisible(false);
	    	worldMap.setVisible(true);
	    	 break;
	    	   case "Upgrade":
	   	    	worldMap.setVisible(false);
	   	    	upgradewindow.setVisible(true);
	   	    	add(upgradewindow);
	   	    	revalidate();
	   	    	upgradewindow.getCurrentblastdamage().setText("Your current blast damage is "+g.getPlayer().getActiveFighter().getBlastDamage());
	   	    	upgradewindow.getCurrentHealth().setText("Your current max health points is "+g.getPlayer().getActiveFighter().getMaxHealthPoints());
	   	    	upgradewindow.getCurrentki().setText("Your current max ki is "+g.getPlayer().getActiveFighter().getMaxKi());
	   	    	upgradewindow.getCurrentphysicaldamage().setText("Your current physical damage is "+g.getPlayer().getActiveFighter().getPhysicalDamage());
	   	    	upgradewindow.getCurrentstamina().setText("Your current max stamina is "+g.getPlayer().getActiveFighter().getMaxStamina());
	   	    	break;

	   	    case "Increase Health Points":
	   	     	try{
	   	    		g.getPlayer().upgradeFighter(g.getPlayer().getActiveFighter(),'H');
	   		    	upgradewindow.getCurrentHealth().setText("Your current max health points is "+g.getPlayer().getActiveFighter().getMaxHealthPoints());
	   	    		}catch(NotEnoughAbilityPointsException e1){
	   	    		JOptionPane.showMessageDialog(new JFrame(),"You dont have enough ability points", "Dialog",
	       			        JOptionPane.INFORMATION_MESSAGE);
	   	    	}
	   	    	break;
	   	    case "Increase Physical Damage":
	   	      	try{
	   	    		g.getPlayer().upgradeFighter(g.getPlayer().getActiveFighter(),'P');
	   		    	upgradewindow.getCurrentphysicaldamage().setText("Your current physical damage is "+g.getPlayer().getActiveFighter().getPhysicalDamage());
	   	    	}catch(NotEnoughAbilityPointsException e1){
	   	    		JOptionPane.showMessageDialog(new JFrame(),"You dont have enough ability points", "Dialog",
	       			        JOptionPane.ERROR_MESSAGE);
	   	    	}
	   	    	break;
	   	    case "Increase Blast Damage":
	   	      	try{
	   	    		g.getPlayer().upgradeFighter(g.getPlayer().getActiveFighter(),'B');
	   		    	upgradewindow.getCurrentblastdamage().setText("Your current blast damage is "+g.getPlayer().getActiveFighter().getBlastDamage());
	   	    	}catch(NotEnoughAbilityPointsException e1){
	   	    		JOptionPane.showMessageDialog(new JFrame(),"You dont have enough ability points", "Dialog",
	       			        JOptionPane.ERROR_MESSAGE);
	   	    	}
	   	    	break;
	   	    case "Increase Max Ki":
	   	    	try{
	   	    		g.getPlayer().upgradeFighter(g.getPlayer().getActiveFighter(),'K');
	   		    	upgradewindow.getCurrentki().setText("Your current max ki is "+g.getPlayer().getActiveFighter().getMaxKi());
	   	    	}catch(NotEnoughAbilityPointsException e1){
	   	    		JOptionPane.showMessageDialog(new JFrame(),"You dont have enough ability points", "Dialog",
	       			        JOptionPane.ERROR_MESSAGE);
	   	    	}
	   	    	break;
	   	    case "Increase Max Stamina":
	   	    	try{
	   	    		g.getPlayer().upgradeFighter(g.getPlayer().getActiveFighter(),'S');
	   	    		upgradewindow.getCurrentstamina().setText("Your current max stamina is "+g.getPlayer().getActiveFighter().getMaxStamina());
	   	    	}catch(NotEnoughAbilityPointsException e1){
	   	    		JOptionPane.showMessageDialog(new JFrame(),"You dont have enough ability points", "Dialog",
	       			        JOptionPane.ERROR_MESSAGE);
	   	    	}
	   	    	
	   	    	break;
	   	    case "Back to game":
	   	    		upgradewindow.setVisible(false);
	   	    		worldMap.setVisible(true);
	   		     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));

	   	    		break;
	    	 }
	
	   
		}	
	else if(e.getSource() instanceof JComboBox){
			JComboBox com=(JComboBox)e.getSource();
			if(com.getName()!=null){
			switch(com.getName()){
			case"superattack":
				//music("attack.wav");
				String saname=(String)com.getSelectedItem();
				for(int i=0;i<sa.size();i++){
					if(sa.get(i).getName().equals(saname)){
						try {
							b.attack(sa.get(i));
							battleView.getPhysicalattack().setEnabled(false);
							battleView.getBlock().setEnabled(false);
							battleView.getUse().setEnabled(false);
							battleView.getUltimateattack().setEnabled(false);
							battleView.getSuperattack().setEnabled(false);
							battleView.getMyTurn().setText("foe Turn");
							
							t.schedule(new TimerTask() {
								  @Override
								  public void run() {
									  
									  try {
										b.play();
										battleView.getPhysicalattack().setEnabled(true);
										battleView.getBlock().setEnabled(true);
										battleView.getUse().setEnabled(true);
										battleView.getUltimateattack().setEnabled(true);
										battleView.getSuperattack().setEnabled(true);
										battleView.getMyTurn().setText("your Turn");
									} catch (NotEnoughKiException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								    // Your database code here
								  }
								}, 800);
							

							
						} catch (NotEnoughKiException e1) {
							JOptionPane.showMessageDialog(new JFrame(),"You do not have enough Ki ", "Dialog",
							        JOptionPane.ERROR_MESSAGE);
							
							
						}
						break;
					}
				}
				break;
			case"Ultimate attack":
			//	music("filmfight.wav");
		    String uaname=(String)com.getSelectedItem();
		    if(uaname.equals("Super Saiyan")){
		    	JOptionPane.showMessageDialog(new JFrame(),"You are now transformed", "Dialog",
				        JOptionPane.INFORMATION_MESSAGE);
		    }
			for(int i=0;i<ua.size();i++){
				if(ua.get(i).getName().equals(uaname)){
					try {
						b.attack(ua.get(i));
						battleView.getPhysicalattack().setEnabled(false);
						battleView.getBlock().setEnabled(false);
						battleView.getUse().setEnabled(false);
						battleView.getUltimateattack().setEnabled(false);
						battleView.getSuperattack().setEnabled(false);
						battleView.getMyTurn().setText("foe Turn");
						
						t.schedule(new TimerTask() {
							  @Override
							  public void run() {
								  
								  try {
									b.play();
									battleView.getPhysicalattack().setEnabled(true);
									battleView.getBlock().setEnabled(true);
									battleView.getUse().setEnabled(true);
									battleView.getUltimateattack().setEnabled(true);
									battleView.getSuperattack().setEnabled(true);
									battleView.getMyTurn().setText("your Turn");
								} catch (NotEnoughKiException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    // Your database code here
							  }
							}, 800);
						


					} catch (NotEnoughKiException e1) {
						JOptionPane.showMessageDialog(new JFrame(),"You do not have enough Ki ", "Dialog",
						        JOptionPane.ERROR_MESSAGE);
						
						
					}
					break;
				}
			}
			break;
			default:break;
			}}
			
		}
		
	    	
		}
	
		
		
	
		
	
	
	
	

public static void main(String[] args) throws IOException {
	
	GUI x=new GUI();
	
}

@Override
public void onDragonCalled(Dragon dragon) {
	
	JOptionPane.showMessageDialog(this,"Congratulations,You collect 7 Dragon Balls :).","Dialog",JOptionPane.INFORMATION_MESSAGE);
	worldMap.setVisible(false);
	add(dragonWindow);
	revalidate();

	d=dragon;
}

@Override
public void onCollectibleFound(Collectible collectible) {
	if(collectible.equals(Collectible.SENZU_BEAN) ){
		JOptionPane.showMessageDialog(new JFrame(),"Congatulations! you have collected a SENZU BEAN", "Wow!..",
		        JOptionPane.INFORMATION_MESSAGE);
		
	     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
    	
		
    	
		
	}else{
		JOptionPane.showMessageDialog(new JFrame(),"Congatulations! you have collected a DRAGON BALL", "Wow!..",
		        JOptionPane.INFORMATION_MESSAGE);
		
		
	     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
    	
	}
	
}

@Override
public void onBattleEvent(BattleEvent e) {
	switch(e.getType()){
	case STARTED:
		

		//music();
		JOptionPane.showMessageDialog(new JFrame(),"BeCareful! Entering a battle... ", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
		 
	 worldMap.setVisible(false);
	
	 
	sa=g.getPlayer().getActiveFighter().getSuperAttacks();
	ua=g.getPlayer().getActiveFighter().getUltimateAttacks();
/*	String[] chooseSA=new String[sa.size()+1];
	chooseSA[0]="Choose a super attack";
	String[] chooseUA=new String[ua.size()+1];
	chooseUA[0]="choose an ultimate attack";
	for(int i=0;i<sa.size();i++){
		System.out.println(sa.get(i).getName());
		chooseSA[i+1]=sa.get(i).getName();
		
	}
	
	for(int i=0;i<ua.size();i++){
		chooseUA[i+1]=ua.get(i).getName();
	}
	
	battleView.setSuperattack(new JComboBox(chooseSA));
	battleView.setUltimateattack(new JComboBox(chooseUA));*/
	 
	 battleView.getMehealth().setMaximum(g.getPlayer().getActiveFighter().getMaxHealthPoints());
	 battleView.getMehealth().setMinimum(0);
	 battleView.getMehealth().setValue(g.getPlayer().getActiveFighter().getMaxHealthPoints());
	 battleView.getMehealth().setString("Health:"+g.getPlayer().getActiveFighter().getMaxHealthPoints());
	 
	 
	 battleView.getMestamina().setMaximum(g.getPlayer().getActiveFighter().getMaxStamina());
	 battleView.getMestamina().setMinimum(0);
	 battleView.getMestamina().setValue(g.getPlayer().getActiveFighter().getMaxStamina());
	 battleView.getMestamina().setString("Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina());
	 
	 battleView.getMeKi().setMaximum(g.getPlayer().getActiveFighter().getMaxKi());
	 battleView.getMeKi().setMinimum(0);
	 battleView.getMeKi().setValue(0);
	 battleView.getMeKi().setString("KI:"+0);
	 
	 battleView.getMe().setText(g.getPlayer().getActiveFighter().getName()+"       "+g.getPlayer().getActiveFighter().getLevel());
	 
	 b=(Battle)e.getSource();
		PlayableFighter me=(PlayableFighter)b.getMe();
		 tarxp=me.getTargetXp();
	  Foe=(Fighter)b.getFoe();
	 
	 battleView.getFoehealth().setMaximum(Foe.getMaxHealthPoints());
	 battleView.getFoehealth().setMinimum(0);
	 battleView.getFoehealth().setValue(Foe.getMaxHealthPoints());
	 battleView.getFoehealth().setString("Health:"+Foe.getMaxHealthPoints());
	 
	 battleView.getFoestamina().setMaximum(Foe.getMaxStamina());
	 battleView.getFoestamina().setMinimum(0);
	 battleView.getFoestamina().setValue(Foe.getMaxStamina());
	 battleView.getFoestamina().setString("Stamina:"+Foe.getStamina());
	 
	 battleView.getFoeKi().setMaximum(Foe.getMaxKi());
	 battleView.getFoeKi().setMinimum(0);
	 battleView.getFoeKi().setValue(0);
	 battleView.getFoeKi().setString("Ki:"+0);
	 battleView.getFoe().setText(Foe.getName()+"       "+Foe.getLevel());
	 
	 battleView.setVisible(true);
	 add(battleView);
	 revalidate();
	 break;
	case USE:
		battleView.getMestamina().setMaximum(g.getPlayer().getActiveFighter().getMaxStamina());
		battleView.getMehealth().setMaximum(g.getPlayer().getActiveFighter().getMaxHealthPoints());
		break;
	case BLOCK:if(b.getAttacker().equals(b.getFoe())){
		JOptionPane.showMessageDialog(new JFrame(),"Foe is blocking ", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
		 
	}
	
		
	revalidate();
		break;
	case NEW_TURN:
		
			
			
			
			battleView.getMehealth().setValue(g.getPlayer().getActiveFighter().getHealthPoints());
			battleView.getMeKi().setValue(g.getPlayer().getActiveFighter().getKi());
			battleView.getMestamina().setValue(g.getPlayer().getActiveFighter().getStamina());
			battleView.getMehealth().setString("Health:"+g.getPlayer().getActiveFighter().getHealthPoints());
			battleView.getMeKi().setString("Ki"+g.getPlayer().getActiveFighter().getKi());
			battleView.getMestamina().setString("Stamina"+g.getPlayer().getActiveFighter().getStamina());

			battleView.getFoehealth().setValue(Foe.getHealthPoints());
			battleView.getFoeKi().setValue(Foe.getKi());
			battleView.getFoestamina().setValue(Foe.getStamina());
			battleView.getFoehealth().setString("Health:"+Foe.getHealthPoints());
			battleView.getFoeKi().setString("Ki"+Foe.getKi());
			battleView.getFoestamina().setString("Stamina"+Foe.getStamina());
			battleView.getBlock().setEnabled(true);
			battleView.getUse().setEnabled(true);
			battleView.getPhysicalattack().setEnabled(true);
		
			revalidate();
			revalidate();
			repaint();

		
			
		
		
	
	break;
	case ATTACK:
		
		if(b.getAttacker().equals(b.getFoe())){
			
			JOptionPane.showMessageDialog(new JFrame(),"you have been attacked with "+e.getAttack().getName()+"of a damage "+e.getAttack().getAppliedDamage(Foe), "Dialog",
			        JOptionPane.INFORMATION_MESSAGE);
			 
		}
		
		
	battleView.getMehealth().setValue(g.getPlayer().getActiveFighter().getHealthPoints());
		battleView.getMeKi().setValue(g.getPlayer().getActiveFighter().getKi());
		battleView.getMestamina().setValue(g.getPlayer().getActiveFighter().getStamina());
		battleView.getMehealth().setString("Health:"+g.getPlayer().getActiveFighter().getHealthPoints());
		battleView.getMeKi().setString("Ki"+g.getPlayer().getActiveFighter().getKi());
		battleView.getMestamina().setString("Stamina:"+g.getPlayer().getActiveFighter().getStamina());
		battleView.getFoehealth().setValue(100);
		battleView.getFoeKi().setValue(Foe.getKi());
		battleView.getFoestamina().setValue(Foe.getStamina());
		battleView.getFoehealth().setString("Health:"+Foe.getHealthPoints());
		battleView.getMeKi().setString("Ki"+Foe.getKi());
		battleView.getMestamina().setString("Stamina:"+Foe.getStamina());
		revalidate();
		break;
		case ENDED:
			NonPlayableFighter fh=(NonPlayableFighter)b.getFoe();
			PlayableFighter meh=(PlayableFighter)b.getMe();
			
			if(e.getWinner().equals(b.getMe())){
				if(fh.getLevel()*5>=tarxp){
				if(fh.isStrong()){
					String x="";
					String y="";
					for(int i=0;i<fh.getSuperAttacks().size();i++){
						x+=fh.getSuperAttacks().get(i).getName();
					}
					for(int i=0;i<fh.getUltimateAttacks().size();i++){
						y+=fh.getUltimateAttacks().get(i).getName();
					}
					JOptionPane.showMessageDialog(this,"Congtraz you have beaten the boss you now have all his super which are"+x+" and ultimate attacks which are"+y+" and a new map wil be unloacked and your new xp is"+meh.getXp()+"and your new level is"+meh.getLevel(), "Dialog",
					        JOptionPane.INFORMATION_MESSAGE);
					worldMap.getStart().setIcon(new ImageIcon(getClass().getResource("goku.png")));
					worldMap.setCurrent(worldMap.getStart());
					worldMap.getBoss().setIcon(new ImageIcon(getClass().getResource("Boss.jpg")));
					worldMap.setVisible(true);
					battleView.setVisible(false);
				     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
					
				}else{
					String x="";
					for(int i=0;i<fh.getSuperAttacks().size();i++){
						x+=fh.getSuperAttacks().get(i).getName();
					}
				JOptionPane.showMessageDialog(this,"Congtraz you have beaten your foe you now have"+x+"as superattacks and your new xp is"+meh.getXp()+"and your new level is"+meh.getLevel(), "Dialog",
				        JOptionPane.INFORMATION_MESSAGE);
			     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
				worldMap.setVisible(true);
				battleView.setVisible(false);
				
			}}else{
				if(fh.isStrong()){
					String x="";
					for(int i=0;i<fh.getSuperAttacks().size();i++){
						x+=fh.getSuperAttacks().get(i).getName();
					}
					String y="";
					for(int j=0;j<fh.getUltimateAttacks().size();j++){
						y+=fh.getUltimateAttacks().get(j).getName();
					}
					JOptionPane.showMessageDialog(this,"Congtraz you have beaten the boss you now have all his super which are"+x+" and ultimate attacks which are"+y+" and a new map wil be unloacked and your new xp is"+meh.getXp(), "Dialog",
					        JOptionPane.INFORMATION_MESSAGE);
					worldMap.getStart().setIcon(new ImageIcon(getClass().getResource("goku.png")));
					worldMap.setCurrent(worldMap.getStart());
					worldMap.getBoss().setIcon(new ImageIcon(getClass().getResource("Boss.jpg")));
					worldMap.setVisible(true);
					battleView.setVisible(false);
				     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
					
				}else{
					String x="";
					for(int i=0;i<fh.getSuperAttacks().size();i++){
						x+=fh.getSuperAttacks().get(i).getName();
					}
				JOptionPane.showMessageDialog(this,"Congtraz you have beaten your foe you now have"+x+"as superattacks and your new xp is"+meh.getXp(), "Dialog",
				        JOptionPane.INFORMATION_MESSAGE);
			     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
				worldMap.setVisible(true);
				battleView.setVisible(false);
				
			}}
				}else{JOptionPane.showMessageDialog(new JFrame(),"You lost your last saved game will loaded ", "Dialog",
			        JOptionPane.INFORMATION_MESSAGE);
			worldMap.setVisible(true);
			battleView.setVisible(false);
		try {
			
			g.load("ahmed.ser");
			g.setListener(this);
			
		     worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
		    
		     int f=g.getWorld().getPlayerRow();
		     int  h=g.getWorld().getPlayerColumn();
		     String t=""+f+h;
		    int u=Integer.parseInt(t);
		     worldMap.getCurrent().setIcon(null);
		     for(int i=0;i<100;i++){
		     if(i==u){
		     worldMap.getButtons().get(i).setIcon(new ImageIcon(getClass().getResource("goku.png")));
		     worldMap.setCurrent(worldMap.getButtons().get(i));
		     }
		     
		     }
		     
		   
		   worldMap.setVisible(true);
		   add(worldMap);
		   
		   revalidate();
			
			} catch (ClassNotFoundException | IOException e1) {
				
				
				if(fh.isStrong()){
					
					worldMap.getStart().setIcon(new ImageIcon(getClass().getResource("goku.png")));
					worldMap.setCurrent(worldMap.getStart());
					worldMap.getBoss().setIcon(new ImageIcon(getClass().getResource("Boss.jpg")));
				}else{
				
					worldMap.getCurrent().setIcon(null);
					worldMap.getStart().setIcon(new ImageIcon(getClass().getResource("goku.png")));
					worldMap.setCurrent(worldMap.getStart());
				}
				 worldMap.setDetails(new JLabel("Name :  "+g.getPlayer().getName()+"     Senzu Beans: "+g.getPlayer().getSenzuBeans()+"      Dragon ball: "+g.getPlayer().getDragonBalls()+"     Level :  "+ g.getPlayer().getActiveFighter().getLevel()+"      fighter: "+fighter+"     "+"Type :  "+fighterName+  "      Ability Points:"+g.getPlayer().getActiveFighter().getAbilityPoints()+"    Physical Damage:"+g.getPlayer().getActiveFighter().getPhysicalDamage()+"    Blast Damage:"+g.getPlayer().getActiveFighter().getBlastDamage()+"   Max Health Points:"+g.getPlayer().getActiveFighter().getMaxHealthPoints()+"  Max Stamina:"+g.getPlayer().getActiveFighter().getMaxStamina()+"   Max Ki:"+g.getPlayer().getActiveFighter().getMaxKi()));
				    
				

			}
				
			}
			
	
			
			
			break;
	}
	
}
@Override
public void keyPressed(KeyEvent e) {

	
	int code=e.getKeyCode();
	if(code==KeyEvent.VK_UP){
		//music("footsteps.wav");
		try{	
			g.getWorld().moveUp();
			
	worldMap.getStart().setIcon(null);
		worldMap.getCurrent().setIcon(null);
		int buttonIndex = worldMap.getButtons().indexOf(worldMap.getCurrent());
      //  JButton c=worldMap.getButtons().get(buttonIndex-10);
      
		worldMap.getButtons().get(buttonIndex-10).setIcon(new ImageIcon(getClass().getResource("goku.png")));
		  revalidate();
		  repaint();
		int x=buttonIndex-10;
		
        worldMap.setCurrent(worldMap.getButtons().get(x));
		
	if(g.getState().equals(GameState.BATTLE)){
		/*	JOptionPane.showMessageDialog(new JFrame(),"BeCareful! Entering a battle... ", "Dialog",
			        JOptionPane.INFORMATION_MESSAGE);*/
			 worldMap.setVisible(false);
		//	 add(battleView);
			 revalidate();
			}
		else if(g.getState()==GameState.DRAGON){
	JOptionPane.showMessageDialog(this,"Congratulations,You collect 7 Dragon Balls :).","Dialog",JOptionPane.INFORMATION_MESSAGE);
	worldMap.setVisible(false);
	add(dragonWindow);
	revalidate();
}
		}
		catch(MapIndexOutOfBoundsException ee){
			JOptionPane.showMessageDialog(new JFrame(),"You cant move out of the map :P ", "Dialog",
			        JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
		
		
		
	}
if(code==KeyEvent.VK_DOWN){
	//music("footsteps.wav");
	try{	
		g.getWorld().moveDown();
		worldMap.getStart().setIcon(null);
		worldMap.getCurrent().setIcon(null);
		int buttonIndex = worldMap.getButtons().indexOf(worldMap.getCurrent());
       // JButton c=worldMap.getButtons().get(buttonIndex+10);
     
		
		worldMap.getButtons().get(buttonIndex+10).setIcon(new ImageIcon(getClass().getResource("goku.png")));
		revalidate();
		  repaint();
	int	x=buttonIndex+10;
		
		worldMap.setCurrent(worldMap.getButtons().get(x));
		
		if(g.getState().equals(GameState.BATTLE)){
	/*	JOptionPane.showMessageDialog(new JFrame(),"BeCareful! Entering a battle... ", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);*/
		 worldMap.setVisible(false);
	//	 add(battleView);
		 revalidate();
		
			
		}
			else if(g.getState()==GameState.DRAGON){
				JOptionPane.showMessageDialog(this,"Congratulations,You collect 7 Dragon Balls :).","Dialog",JOptionPane.INFORMATION_MESSAGE);
				worldMap.setVisible(false);
				add(dragonWindow);
				revalidate();
			}
		}
		catch(MapIndexOutOfBoundsException ee){
			JOptionPane.showMessageDialog(new JFrame(),"You cant move out of the map :P ", "Dialog",
			        JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
		
		
		
	}
if(code==KeyEvent.VK_LEFT){
	//music("footsteps.wav");
	try{	
		g.getWorld().moveLeft();
		worldMap.getStart().setIcon(null);
	worldMap.getCurrent().setIcon(null);
	int buttonIndex = worldMap.getButtons().indexOf(worldMap.getCurrent());
   // JButton c=worldMap.getButtons().get(buttonIndex-1);
  
	
	worldMap.getButtons().get(buttonIndex-1).setIcon(new ImageIcon(getClass().getResource("goku.png")));
	  revalidate();
	  repaint();
	int x=buttonIndex-1;
	
    worldMap.setCurrent(worldMap.getButtons().get(x));
	
    	if(g.getState().equals(GameState.BATTLE)){
	/*JOptionPane.showMessageDialog(new JFrame(),"BeCareful! Entering a battle... ", "Dialog",
	        JOptionPane.INFORMATION_MESSAGE);*/
	 worldMap.setVisible(false);
	// add(battleView);
	 revalidate();
	
		
	}
    		else if(g.getState()==GameState.DRAGON){
		JOptionPane.showMessageDialog(this,"Congratulations,You collect 7 Dragon Balls :).","Dialog",JOptionPane.INFORMATION_MESSAGE);
		worldMap.setVisible(false);
		add(dragonWindow);
		revalidate();
	}
	}
	catch(MapIndexOutOfBoundsException ee){
		JOptionPane.showMessageDialog(new JFrame(),"You cant move out of the map :P ", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	
	
	
}
if(code==KeyEvent.VK_RIGHT){
	//music("footsteps.wav");
	try{	
		g.getWorld().moveRight();
		worldMap.getStart().setIcon(null);
	worldMap.getCurrent().setIcon(null);
	int buttonIndex = worldMap.getButtons().indexOf(worldMap.getCurrent());
   // JButton c=worldMap.getButtons().get(buttonIndex+1);
    worldMap.getButtons().get(buttonIndex+1).setIcon(new ImageIcon(getClass().getResource("goku.png")));
    revalidate();
    repaint();
	int x=buttonIndex+1;
    
    worldMap.setCurrent(worldMap.getButtons().get(x));
	
   	if(g.getState().equals(GameState.BATTLE)){
/*	JOptionPane.showMessageDialog(new JFrame(),"BeCareful! Entering a battle... ", "Dialog",
	        JOptionPane.INFORMATION_MESSAGE);*/
	 worldMap.setVisible(false);
	// add(battleView);
	 revalidate();
	
		
	}
   		else if(g.getState()==GameState.DRAGON){
	JOptionPane.showMessageDialog(this,"Congratulations,You collect 7 Dragon Balls :).","Dialog",JOptionPane.INFORMATION_MESSAGE);
	worldMap.setVisible(false);
	add(dragonWindow);
	revalidate();
}
	}
	catch(MapIndexOutOfBoundsException ee){
		JOptionPane.showMessageDialog(new JFrame(),"You cant move out of the map :P ", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	
	
	
}
	
}


public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
public static void music(String path) 
{       
    AudioPlayer MGP = AudioPlayer.player;
    AudioStream BGM;
    AudioData MD;

    ContinuousAudioDataStream loop = null;

    try
    {
        InputStream test = new FileInputStream(path);
        BGM = new AudioStream(test);
        AudioPlayer.player.start(BGM);
        //MD = BGM.getData();
        //loop = new ContinuousAudioDataStream(MD);

    }
    catch(FileNotFoundException e){
        System.out.print(e.toString());
    }
    catch(IOException error)
    {
        System.out.print(error.toString());
    }
    MGP.start(loop);
}


}



 
 
	

