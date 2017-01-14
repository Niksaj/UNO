public class NumberCard extends Card {
	int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public NumberCard(int number, int colour) {
		this.setNumber(number);
		this.setColour(Card.Colour.values()[colour]);
	}
	
	public boolean isPlayAbleOn(Card topCard){
		if(topCard.getColour() == this.getColour()){
			return true;
		} else if (topCard.getClass() == NumberCard.class) {
			if(((NumberCard)topCard).getNumber() == this.getNumber()){
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		return this.getColour() + " " + this.getNumber();
	}
}
