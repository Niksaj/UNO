

public class Deck {
	
	/**
	 * The deck of cards
	 */
	Card[] deck = new Card[0];
	/**
	 * The number of cards in the deck
	 */
	int cards = 0;
	
	/**
	 * @return the deck of cards
	 */
	public Card[] getDeck() {
		return deck;
	}
	
	/**
	 * 
	 * @param deck
	 */
	public void setDeck(Card[] deck) {
		this.deck = deck;
	}
	
	/**
	 * 
	 * @param numCards
	 * @return A new hand of cards
	 */
	public Card[] createHand(int numCards){
		Card[] cards = new Card[numCards];

		return cards;
	}
	
	/**
	 * 
	 * @return a random card from the deck
	 */
	public Card dealCard(){
		
		int index = (int) (Math.random() * cards);
		Card card = deck[index];
		
		for(int i = index ; i < cards - 1 ; i ++){
			deck[i] = deck[i + 1];
		}
		deck[cards - 1] = null;
		
		cards--;
		
		return card;
	}

	/**
	 * Fills the deck with the correct number of cards.
	 * @param numDecks
	 */
	public void fillDeck(int numDecks){
		int currentPosition = 0;
		
		for(int k = 0 ; k < numDecks ; k ++){
			for(int i = 0 ; i < 4 ; i ++){
				deck[currentPosition] = new NumberCard(0, i);
				currentPosition++;
			}

			// Fill deck with number cards

			for(int i = 1 ; i <= 9 ; i ++){
				for(int j = 0 ; j < 4 ; j ++){
					for(int l = 0 ; l < 2 ; l ++){
						deck[currentPosition] = new NumberCard(i, j);
						currentPosition++;
					}
				}
			}

			// Fill deck with special cards

			for(int i = 0 ; i < 3 ; i ++){
				for(int j = 0 ; j < 4 ; j ++){
					for(int l = 0 ; l < 2 ; l ++){
						deck[currentPosition] = new SpecialCard(i, j);
						currentPosition++;
					}
				}
			}

			// Fill deck with wild cards

			for(int i = 0 ; i < 8; i ++){
				if(i%2 == 0){
					deck[currentPosition] = new WildCard(true);
				} else {
					deck[currentPosition] = new WildCard(false);
				}
				currentPosition++;
			}

		}
		cards = numDecks * 108;
	}

	/**
	 * 
	 * @param numDecks
	 */
	public Deck(int numDecks){
		Card[] newDeck = new Card[108 * numDecks];
		this.setDeck(newDeck);
		fillDeck(numDecks);
	}
}