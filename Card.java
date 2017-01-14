
public class Card {
	/**
	 * Colour enum for tracking colour of cards
	 *
	 */
	public enum Colour{
		RED, BLUE, YELLOW, GREEN
	}
	/*
	 * Special enum for tracking the type of special card
	 */
	public enum Special{
		SKIP, DRAW, REVERSE
	}
	
	Card.Colour colour; 

	public Card.Colour getColour() {
		return colour;
	}

	public void setColour(Card.Colour colour) {
		this.colour = colour;
	}

	public boolean isPlayAble(Card topCard) {
		return false;
	}
}
