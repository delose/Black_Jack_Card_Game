package BlackJack_v1;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class PlayBlackJack extends Deck {

	private int playermoves = 0 ;
	private boolean playerbust = false;
	private boolean dealerbust = false;
	private int cardpoints = 0;
	private String[] dealerhand = new String[52];
	private String[] playerhand = new String[52];
	private Scanner input = new Scanner(System.in);
	private String[] acecards = {"Ace of Clubs","Ace of Spades","Ace of Hearts","Ace of Diamonds"};
	private String[] tenfacecards  = {"Jack of Clubs","Jack of Spades","Jack of Hearts","Jack of Diamonds",
						  "Queen of Clubs","Queen of Spades","Queen of Hearts","Queen of Diamonds",
						  "King of Clubs", "King of Spades","King of Hearts","King of Diamonds",
						  "Ten of Clubs","Ten of Spades","Ten of Hearts","Ten of Diamonds"};
	
	public Bet call = new Bet();
	
	public PlayBlackJack () {
		
		
		
	}
	
	public int getcardpoints() {
		return cardpoints;
	}

	public void setcardpoints(int cardcardpoints) {
		this.cardpoints = cardcardpoints;
	}

	public int getDealercards() {
		return dealerhand.length;
	}

	public int getPlayercards() {
		return playerhand.length;
	}
	
	public void startGame () {
		
		boolean answer = true;
		
		System.out.println("Welcome to BLACK JACK!");
		System.out.println(">> Press ENTER to play <<");
		input.nextLine();
		while (answer) {
			
			readyDeck();
			
			displayPlayerChip();
			
			call.placeBet();
			
			initialPhase();
			
			if (!checkInitialHand()) {

				playerPhase();
				
				if (playerbust == false) {
					dealerPhase();
				}
				
			}
			
			if (playerbust == false) {
				compareHands();
			}
			
			// bankrupt
			if (call.bankrupt() == true) {
				System.out.println("Sorry but you are already done for tonight!");
				break;
			}
			
			resetHands();

			answer = askPlayer();
			
		}		
		
		
	}
	
	public void initialPhase () {
		
		dealerhand[0] = DealCard();
		playerhand[0] = DealCard();
		dealerhand[1] = DealCard();
		playerhand[1] = DealCard();		
		
		displayInitialCards();
		
	}
	
	public boolean checkInitialHand() {
		
		boolean check = false;
		
		if ((Arrays.asList(acecards).contains(playerhand[0]) & Arrays.asList(tenfacecards).contains(playerhand[1])) ||
			(Arrays.asList(tenfacecards).contains(playerhand[0]) & Arrays.asList(acecards).contains(playerhand[1]))) {
			
			System.out.println("Black Jack!");
			
			call.blackJack();
			
			check = true;
			
		}
		
		return check;
		
	}
	
	public int checkAces (String[] deck) {
		
		int numberOfAces = 0;
		
		for (int i = 0; i < deck.length; i++) {
			
			if (Arrays.asList(acecards).contains(playerhand[i])) {
				
				numberOfAces++;
				
			}
			
		}
		
		return numberOfAces;
		
	}
	
	public void playerPhase () {
		
		System.out.println("==========================");
		
		playerTurn();
		
	}
	
	public void playerTurn() {
		
		boolean done = false;
		String answer = "";
		cardpoints = 0;
		
		while (done == false) {
			
			displayOption();
			
			answer = playerAnswer();
			
			cardpoints = computePlayerHand();
			
			if (cardpoints == 0) {
				System.out.println("Player busts!");
				playerbust = true;
				call.bust();
			}	
			System.out.println("response = " + answer);
			System.out.println("Check " + "cardpoints:" + cardpoints + " " + (cardpoints == 0) + " " + (answer.equals("s")) + " " + (answer.equals("s")));
			// check if player busts, stays, surrenders
			if (cardpoints == 0 | answer.equals("s") | answer.equals("r")) {
				
				playermoves++;
				done = true;
				break;
			}			
			
		}
		
	}
	
	public void dealerMove() {
		
		boolean done = false;
		
		cardpoints = computeDealerHand();
		
		while (done == false) {
			
			dealerhit();
			// this should come first; dealer should hit on first try if condition is met
			// hit until bust or cardpoints reach 17 to 21
			if (cardpoints == 0 || cardpoints >= 17) {
				
				done = true;
				
				if (cardpoints == 0) {
					System.out.println("Dealer busts!");
					dealerbust = true;
					call.playerwins();
				}
				
			}
			
		}
		
		//display
		
	}
	
	public void displayOption() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("What would you like to do?");
		System.out.print("hit(h), ");
		System.out.print("stay(s), ");
		if (playermoves == 0) {
			System.out.print("double(d), ");
		}
		System.out.print("surrender(r), ");
		if (playermoves == 0) {
			System.out.print("cashier(c)");
		}
		System.out.print("\n>> ");		
		
		/* has yet to be added
		if (checkSplit()) {
			//System.out.print("split(p), ");
		
		}
		
		//insurance (allowed if dealer gets an ace as first card)
		System.out.println("(i) Insurance/Side Bet");
		*/
		
	}
	
	public String playerAnswer() {
		
		String text = input.next();
		
		switch (text.toLowerCase()) {
			
		case "h" :
			playerhit();
			break;
		case "s" :
			stay();
			break;
		case "r" :
			call.surrender();
			break;
		case "d" :
			call.doubleDown();
			break;
		case "c" :
			call.cashier();
		/*case "p" :
			if (checkSplit()) {
				split();
			}
			break;
		case "i" :
			call.insurance();
			break;*/
		default:
			System.out.println("Keep your cool so just \"stay\"");
			stay();
		}	
		
		return text;
		
	}
	
	public boolean checkSplit () {
		
		boolean check = false;
		
		if (Arrays.asList(acecards).contains(playerhand[0])) {
			
			check = true;
		}
		
		return check;
		
	}
	
	public void dealerPhase () {
		
		System.out.println("==========================");
		
		dealerMove();
		
	}
	
	public boolean askPlayer() {
		
		System.out.println("Do you still want to play (y/n)?");
		String answer = input.next(); 
		boolean choice = true;
		
		switch (answer.toLowerCase()) {
		
		case "y":
			choice = true;
			break;
		case "n":
			System.out.println("Thanks for playing!");
			choice = false;
			break;
		default:
		
		}
		
		return choice;
		
	}
	
	public void displayPlayerChip () {
		
		System.out.println("Player chips : " + call.getPlayerchips());
		
	}
	
	public void displayInitialCards () {
		
		displayPlayerHand();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("===========VS.============");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Dealer's hand:");
		
		int i = 0;
		
		while (dealerhand[i] != null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			if (i == 1) {
				System.out.println("hidden");
			} else {
				System.out.println(dealerhand[i]);
			}
			
			i++;
			
		}
		
	}
	
	public void displayPlayerHand () {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("==========================");
		System.out.println("Player's hand:");
		
		int i = 0;
		
		while (playerhand[i] != null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(playerhand[i]);
			
			i++;
			
		}		
		
	}
	
	public void displayDealerHand () {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("==========================");
		System.out.println("Dealer's hand:");
		
		int i = 0;
		
		while (dealerhand[i] != null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(dealerhand[i]);
			
			i++;
			
		}
		
	}
	
	public void stay () {
		
		/*for (int i = 0;i < getArraySize(); i++) {
			
			if (dealerhand[i] == null) {
				dealerhand[i] = DealCard();
				break;
			}
		}*/
		
		displayPlayerHand();
		
		playermoves++;
		
	}
	
	public void playerhit () {
		
		for (int i = 0;i < getArraySize(); i++) {
			
			if (playerhand[i] == null) {
				playerhand[i] = DealCard();
				break;
			}			
		}
		
		displayPlayerHand();
		
		playermoves++;
		
	}
	
	public void dealerhit () {
		
		for (int i = 0;i < getArraySize(); i++) {
			
			if (dealerhand[i] == null) {
				dealerhand[i] = DealCard();
				break;
			}			
		}
		
		displayDealerHand();
		
	}
	
	public void split () {
		
		boolean ans = false;
		
		while (ans == false) {
			
			String message = JOptionPane.showInputDialog(null, "Current chips :" + call.getPlayerchips(), "Place bet for the other hand", JOptionPane.PLAIN_MESSAGE );
			
			call.setPlayerbet(Integer.parseInt(message));
			
			if (call.getPlayerbet() < call.getPlayerchips()) {
				
				ans = true;
				
			}			
			
		}		
		
	}
	
	public void compareHands () {
		
		if (computePlayerHand() > computeDealerHand() || (computePlayerHand() == 21 & computeDealerHand() < 21)) {
			call.playerwins();
		} else if (computePlayerHand() == computeDealerHand()) {
			call.pushTable();
		} else {
			call.playerlose();
		}
		
	}

	public void resetHands() {
		
		for (int i = 0; i < getDealercards(); i++) {
			dealerhand[i] = null;
		}
		
		for (int i = 0; i < getPlayercards(); i++) {
			playerhand[i] = null;
		}
		
		playermoves = 0;
		
	}
	
	public int computePlayerHand () {
		
		setcardpoints(0);
		cardpoints = getcardpoints();
		
		for (int i = 0; i < getPlayercards(); i++) {
			
			if (cardpoints > 21) {
				cardpoints = 0;
				break;
			}
			
			if (playerhand[i] != null) {
				cardpoints += cardValue(playerhand[i], playerhand);
			}
			
		}
		
		return cardpoints;
		
	}
	
	public int computeDealerHand () {
		
		setcardpoints(0);
		cardpoints = getcardpoints();
		
		for (int i = 0; i < getDealercards(); i++) {
			
			if (cardpoints > 21) {
				System.out.println("Dealer busts!");
				cardpoints = 0;
				break;
			}
			
			if (dealerhand[i] != null) {
				cardpoints += cardValue(dealerhand[i], dealerhand);
			}
			
		}
		
		return cardpoints;
		
	}
	
	public int cardValue (String text, String[] hand) {
		
		int value = 0;
		
		String[] temp = text.split(" ", 2);
		String firstword = null;
		
		if (text.contains(" ")) {
			
			firstword = temp[0];
			
		}
		
		switch (firstword) {
			
		case "Ace":
			//need to modify cardpoints
			if (cardpoints >= 11 && checkAces(hand) > 1) {	
				value -= 11;
			} else if (cardpoints >= 11) {
				value = 1;
			} else {
				value = 11;
			}
			
			break;
		case "Two":
			value = 2;
			break;
			
		case "Three":
			value = 3;
			break;
			
		case "Four":
			value = 4;
			break;
			
		case "Five":
			value = 5;
			break;
			
		case "Six":
			value = 6;
			break;
			
		case "Seven":
			value = 7;
			break;
			
		case "Eight":
			value = 8;
			break;
			
		case "Nine":
			value = 9;
			break;
			
		case "Ten":
			value = 10;
			break;
			
		case "Jack":
			value = 10;
			break;
			
		case "Queen":
			value = 10;
			break;

		case "King":
			value = 10;
			break;
			
		default:
			
		}
		
		return value;
		
	}
	
	public static void main(String[] args) {
		
		PlayBlackJack start = new PlayBlackJack();
		
		start.startGame();
		
		//start.surrender();
		
	}
	
	
}
