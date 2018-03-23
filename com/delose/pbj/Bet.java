package com.delose.pbj;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;

public class Bet {
	
	private int playerchips = 1000;
	private int playerbet = 0; 
	
	public int getPlayerchips() {
		return playerchips;
	}

	public void setPlayerchips(int playerchips) {
		this.playerchips = playerchips;
	}

	public int getPlayerbet() {
		return playerbet;
	}

	public void setPlayerbet(int playerbet) {
		this.playerbet = playerbet;
	}
	
	public void blackJack () {
		
		setPlayerchips(playerchips + playerbet + playerbet*1/3);
		
	}
	
	public void placeBet () {
		
		boolean ans = false;
		
		while (!ans) {
			
			try {
				String message = JOptionPane.showInputDialog(null, "Current chips :" + playerchips, "Place your bet: ", JOptionPane.PLAIN_MESSAGE );
				
				playerbet = Integer.parseInt(message);
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (playerbet <= playerchips & playerbet > 0) {
				
				ans = true;
				
			}			
			
		}
		
		setPlayerbet(playerbet);
		//setPlayerchips(playerchips - playerbet);
		System.out.println("==========================");
		System.out.println("Chips : " + getPlayerchips());
		System.out.println("Bet   : " + getPlayerbet());
		
	}
	
	public void doubleDown() {
		
		// bet will be doubled and player will hit
		
		if (getPlayerchips() >= getPlayerbet()) {
		
			setPlayerbet(getPlayerbet()*2);
			
			System.out.println("Doubled bet to " + getPlayerbet() + "!");
			
		} else {
			
			System.out.println("Sorry, you are not allowed to bet more than your remaining chips");
			
		}
		
	}
	
	public void insurance() {
		
		setPlayerbet(getPlayerbet()/2);
		
		System.out.println("Halved bet to" + getPlayerbet() + "!");
		
	}
	
	public void cashier() {
		
		System.out.println("Thank you for playing!\nYour total winnings amount to: " + getPlayerchips());
		
		System.exit(1);
		
	}
	
	public void playerwins () {
		
		System.out.println("Player wins!");
		setPlayerchips(playerchips + playerbet);
		setPlayerbet(0);
		
	}
	
	
	public void playerlose () {
		
		System.out.println("Player loses!");
		setPlayerchips(playerchips - playerbet);
		setPlayerbet(0);
		
	}
	
	public void bust () {
		
		setPlayerchips(playerchips - playerbet);
		
	}
	
	public void pushTable() {
		
		System.out.println("Push!");
		setPlayerbet(0);
		
	}
	
	public void surrender () {
		
		System.out.println("You have surrendered.");
		
		playerlose();
		
	}
	
	public boolean bankrupt () {
		
		boolean broke = false;
		
		if (playerchips <= 0) {
			
			broke = true;

		}
		
		return broke;
		
	}
	
}
