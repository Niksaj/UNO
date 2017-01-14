
public class SpecialCard extends Card {

	Card.Special type;
	
	public Card.Special getType() {
		return type;
	}
	public void setType(Card.Special type) {
		this.type = type;
	}
	
	public boolean isPlayAbleOn(Card topCard){
		if(topCard.getColour() == this.getColour()){
			return true;
		} else if (topCard.getClass() == NumberCard.class) {
			if(((SpecialCard)topCard).getType() == this.getType()){
				return true;
			}
		}
		return false;
	}
	
	public SpecialCard(int type, int colour){
		this.setColour(Card.Colour.values()[colour]);
		this.setType(Card.Special.values()[type]);
	}
	
	public String toString(){
		return this.getColour() + " " + this.getType();
	}
}