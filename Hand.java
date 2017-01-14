
public class Hand {

	/**
	 * the collection of cards that is the hand
	 */
	Card[] hand;

	/**
	 * 
	 * @return this hand as card array
	 */
	public Card[] getHand() {
		return hand;
	}

	/**
	 * 
	 * @param hand
	 */
	public void setHand(Card[] hand) {
		this.hand = hand;
	}

	/**
	 * adds a card to the hand
	 * @param card
	 */
	public void addCard(Card card){
		Card[] newHand = new Card[hand.length + 1];
		for(int i = 0 ; i < hand.length ; i ++){
			newHand[i] = hand[i];
		}
		newHand[newHand.length - 1] = card;
		hand = newHand;
	}

	public void removeCard(int index){
		Card[] newHand = new Card[hand.length - 1];
		int j = 0;
		for(int i = 0 ; i < hand.length ; i ++){
			if(i != index){
				newHand[j] = hand[i];
				j++;
			}
		}
		this.setHand(newHand);
	}

	/**
	 * 
	 * @param cards
	 */
	public Hand(Card...cards){
		Card[] newHand = new Card[cards.length];
		for(int i = 0 ; i < cards.length ; i ++){
			newHand[i] = cards[i];
		}
		hand = newHand;
	}

	public String toString(){
		String str = "";
		int i = 1;
		for(Card card : hand){
			str += i + " " + card.toString() + "\n";
			i++;
		}
		return str;
	}
}
