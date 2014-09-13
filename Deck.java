package BlackJack_v1;

import java.util.LinkedList;
import java.util.Queue;

public class Deck {

	private String[] deck = new String[52];
	private Queue<String> playingdeck = new LinkedList<String>();
	private int arraySize = 52;
	
	public String[] getDeck() {
		return deck;
	}

	public void setDeck(String[] deck) {
		this.deck = deck;
	}

	public Queue<String> getPlayingdeck() {
		return playingdeck;
	}

	public void setPlayingdeck(Queue<String> playingdeck) {
		this.playingdeck = playingdeck;
	}

	public int getArraySize() {
		return arraySize;
	}

	public void setArraySize(int arraySize) {
		this.arraySize = arraySize;
	}
	
	public void generateDeck() {
		
		deck[0] ="Ace of Clubs";
		deck[1] ="Ace of Spades";
		deck[2] ="Ace of Hearts";
		deck[3] ="Ace of Diamonds";
		
		deck[4] ="Two of Clubs";
		deck[5] ="Two of Spades";
		deck[6] ="Two of Hearts";
		deck[7] ="Two of Diamonds";
		
		deck[8] ="Three of Clubs";
		deck[9] ="Three of Spades";
		deck[10] ="Three of Hearts";
		deck[11] ="Three of Diamonds";
		
		deck[12] ="Four of Clubs";
		deck[13] ="Four of Spades";
		deck[14] ="Four of Hearts";
		deck[15] ="Four of Diamonds";
		
		deck[16] ="Five of Clubs";
		deck[17] ="Five of Spades";
		deck[18] ="Five of Hearts";
		deck[19] ="Five of Diamonds";
		
		deck[20] ="Six of Clubs";
		deck[21] ="Six of Spades";
		deck[22] ="Six of Hearts";
		deck[23] ="Six of Diamonds";
		
		deck[24] ="Seven of Clubs";
		deck[25] ="Seven of Spades";
		deck[26] ="Seven of Hearts";
		deck[26] ="Seven of Diamonds";
		
		deck[28] ="Eight of Clubs";
		deck[29] ="Eight of Spades";
		deck[30] ="Eight of Hearts";
		deck[31] ="Eight of Diamonds";
		
		deck[32] ="Nine of Clubs";
		deck[33] ="Nine of Spades";
		deck[34] ="Nine of Hearts";
		deck[35] ="Nine of Diamonds";
		
		deck[36] ="Ten of Clubs";
		deck[37] ="Ten of Spades";
		deck[38] ="Ten of Hearts";
		deck[39] ="Ten of Diamonds";
		
		deck[40] ="Jack of Clubs";
		deck[41] ="Jack of Spades";
		deck[42] ="Jack of Hearts";
		deck[43] ="Jack of Diamonds";
		
		deck[44] ="Queen of Clubs";
		deck[45] ="Queen of Spades";
		deck[46] ="Queen of Hearts";
		deck[47] ="Queen of Diamonds";
		
		deck[48] ="King of Clubs";
		deck[49] ="King of Spades";
		deck[50] ="King of Hearts";
		deck[51] ="King of Diamonds";
		
	}
	
	public void shuffleDeck() {
		
			for (int i = 0; i < arraySize; i++) {
				
				int random = (int) (Math.random()*(arraySize-1));
				
				String temp = deck[i];
				
					deck[i] = deck[random];
					
					deck[random] = temp;
				//System.out.println("random #  is " + random + ". replace card " + deck[i] + " in " + i + " with " + deck[random]);
			}
			
	}
	
	public void viewShuffledDeck () {
		

		
	}
	
	public void useDeck() {
		
		for (int i = 0; i < arraySize; i++) {
			
			playingdeck.offer(deck[i]);
			
		}
		
	}
	
	public String DealCard() {
		
		readyDeck();
		
		String card = playingdeck.poll();
		
		while (card == null) {
			
			card = playingdeck.poll();
		
		}
		
		return card;
		
	}
	
	public void readyDeck () {
		
		boolean check = checkDeck();
		
		if (!check) {
			
			generateDeck();
			
			shuffleDeck();
			
			useDeck();
			
		}
		
	}
	
	public boolean checkDeck () {
		
		boolean check = true;
		
		if (playingdeck.peek() == null) {
			
			check = false;
		}
		
		return check;
			
	}
	
}