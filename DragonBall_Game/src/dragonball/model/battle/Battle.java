package dragonball.model.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.player.Player;

public class Battle implements Serializable{
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener listener;

	public Battle(BattleOpponent me, BattleOpponent foe) {
		this.me = me;
		this.foe = foe;
		this.attacker = me;

		// set current values appropriately
		Fighter meFighter = (Fighter) me;
		meFighter.setHealthPoints(meFighter.getMaxHealthPoints());
		meFighter.setKi(0);
		meFighter.setStamina(meFighter.getMaxStamina());
		// reset a saiyan's transformation state in case it was transformed in a previous battle
		if (me instanceof Saiyan) {
			Saiyan meSaiyan = (Saiyan) me;
			meSaiyan.setTransformed(false);
		}

		Fighter foeFighter = (Fighter) foe;
		foeFighter.setHealthPoints(foeFighter.getMaxHealthPoints());
		foeFighter.setKi(0);
		foeFighter.setStamina(foeFighter.getMaxStamina());
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}

	public BattleOpponent getDefender() {
		return attacker == me ? foe : me;
	}

	public boolean isMeBlocking() {
		return meBlocking;
	}

	public boolean isFoeBlocking() {
		return foeBlocking;
	}

	public ArrayList<Attack> getAssignedAttacks() {
		Fighter attackerFighter = (Fighter) attacker;

		ArrayList<Attack> attacks = new ArrayList<>();
		// make sure to include the physical attack as well
		attacks.add(new PhysicalAttack());
		attacks.addAll(attackerFighter.getSuperAttacks());
		attacks.addAll(attackerFighter.getUltimateAttacks());
		return attacks;
	}
	public ArrayList<Attack> getAssignedAttacks(Fighter me) {
		

		ArrayList<Attack> attacks = new ArrayList<>();
		// make sure to include the physical attack as well
		attacks.add(new PhysicalAttack());
		attacks.addAll(me.getSuperAttacks());
		attacks.addAll(me.getUltimateAttacks());
		return attacks;
	}

	public void switchTurn() {
		attacker = getDefender();
	}

	public void endTurn() {
		// reset block mode
		if (attacker == me && foeBlocking) {
			foeBlocking = false;
		} else if (attacker == foe && meBlocking) {
			meBlocking = false;
		}

		// if i'm dead
		if (((Fighter) me).getHealthPoints() == 0) {
			// tell everyone my opponent won
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, foe));
			// if my opponent is dead
		} else if (((Fighter) foe).getHealthPoints() == 0) {
			// tell everyone i won
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, me));
		} else {
			switchTurn();

			getAttacker().onDefenderTurn();
			getDefender().onAttackerTurn();

			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
		}
	}

	public void start() {
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.STARTED));
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
	}
	public Attack maxdamage(ArrayList<Attack>a){
		Attack b=a.get(0);
		for(int i=0;i<a.size();i++){
			if(b.getAppliedDamage(foe)<a.get(i).getAppliedDamage(foe)){
				b=a.get(i);
			}
		}
		return b;
	}
	public Attack maxdamage(ArrayList<Attack>a,Fighter f){
		Attack b=new PhysicalAttack();
		for(int i=0;i<a.size();i++){
			
			if(b.getAppliedDamage(foe)<a.get(i).getAppliedDamage(foe)){
				
				if (a.get(i) instanceof PhysicalAttack
						|| (a.get(i) instanceof SuperAttack && f.getKi() >= 1)
						|| (a.get(i) instanceof UltimateAttack && f.getKi() >= 3)) {
					b=a.get(i);
			}
				
		}
			if((a.get(i).getName().equals("Maximum Charge"))&&(f.getKi()>=1)&&(f.getKi()<3)/*beacuse if ki is  more than 3 why would u use mc and if you dont have ua */&&!(f.getUltimateAttacks().isEmpty())){
				b=a.get(i);
				break;
			}
			
		
	}
		return b;
		}
	public UltimateAttack uamax(ArrayList<UltimateAttack>a){//get best damage ua
		UltimateAttack x=a.get(0);
		for(int i=0;i<a.size();i++){
			if(x.getAppliedDamage(foe)<a.get(i).getAppliedDamage(foe)){
				x=a.get(i);
			}
		}
		return x;
	}

	

	// used to automate turn for opponent a.k.a. ai
	public void play() throws NotEnoughKiException {
		NonPlayableFighter f=(NonPlayableFighter)foe;
		Fighter f1=(Fighter)me;
		
				
		if((f.getHealthPoints()==0) ||(f1.getHealthPoints()==0)){
			//donothing
		}else{
			
			ArrayList<Attack> attacks = getAssignedAttacks();
			
			Attack idealAttack=maxdamage(attacks,f);
			
	if(f1.getHealthPoints()<=idealAttack.getAppliedDamage(foe)){
		
			attack(idealAttack);
			System.out.println("1");
		} 
			else if(maxdamage(getAssignedAttacks(f1),f1).getAppliedDamage(me)>=f.getHealthPoints()){
				if(f1.getLevel()>1){
				block();
				System.out.println(2);
				}else{
					attack(idealAttack);
					System.out.println(3);
				}
			}else{
				if((idealAttack.getName().equals("Maximum Charge"))){
					attack(idealAttack);
					System.out.println(4);
				}
				
				
				else	if((maxdamage(attacks) instanceof UltimateAttack)&&(maxdamage(attacks).getAppliedDamage(foe)>=f1.getHealthPoints())){//if ua can win you the fight{
						attack(new PhysicalAttack());
						System.out.println(5);
					}
					else{
						if(!(f.getUltimateAttacks().isEmpty())){//in case fighter has no ua
							UltimateAttack best=uamax(f.getUltimateAttacks());
							if(idealAttack instanceof SuperAttack){//because if ideal attack was an ultimate it would be the same as best
								if(((best.getAppliedDamage(foe))>=(1.2*idealAttack.getAppliedDamage(foe)))&&(0.7*idealAttack.getAppliedDamage(foe)<=new PhysicalAttack().getAppliedDamage(foe)))
								{
									System.out.println("hi");
									//conditon to make sure ua does 1.5 more damage than sa and sa and physical are not much different
									attack(new PhysicalAttack());//charging ki to preform Ua later
								}else{
									System.out.println(6);
									attack(idealAttack);
								}
							}else{
								System.out.println(7);
								attack(idealAttack);
							}
						}else{
						
				
							System.out.println(8);
				attack(idealAttack);
			}}
			
			
		}
	}}
	
		
	

	// perform an attack and end turn
	public void attack(Attack attack) throws NotEnoughKiException {
		attack.onUse(attacker, getDefender(),
				(attacker == me && foeBlocking) || (attacker == foe && meBlocking));

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ATTACK, attack));

		endTurn();
	}

	// perform a block and end turn
	public void block() {
		if (attacker == me) {
			meBlocking = true;
		} else if (attacker == foe) {
			foeBlocking = true;
		}

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.BLOCK));

		endTurn();
	}

	// use a collectible and end turn
	public void use(Player player, Collectible collectible) throws NotEnoughSenzuBeansException {
		switch (collectible) {
		case SENZU_BEAN:
			if (player.getSenzuBeans() > 0) {
				PlayableFighter activeFighter = player.getActiveFighter();
				activeFighter.setHealthPoints(activeFighter.getMaxHealthPoints());
				activeFighter.setStamina(activeFighter.getMaxStamina());

				player.setSenzuBeans(player.getSenzuBeans() - 1);

				notifyOnBattleEvent(new BattleEvent(this, BattleEventType.USE, collectible));
			} else {
				throw new NotEnoughSenzuBeansException();
			}
			break;
		default:
			break;
		}

		endTurn();
	}

	public void setListener(BattleListener listener) {
		this.listener = listener;
	}

	public void notifyOnBattleEvent(BattleEvent e) {
		if (listener != null) {
			listener.onBattleEvent(e);
		}
	}
}
