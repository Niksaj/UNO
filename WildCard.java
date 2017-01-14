
public class WildCard extends Card {
	boolean drawFour;

	public boolean isDrawFour() {
		return drawFour;
	}

	public void setDrawFour(boolean drawFour) {
		this.drawFour = drawFour;
	}

	public boolean isPlayAbleOn(Card topCard){
		return true;
	}

	public WildCard(boolean drawFour) {
		this.setDrawFour(drawFour);
	}

	public String toString(){
		String buildStr = "";
		if(this.getColour() != null){
			buildStr += this.getColour() + " ";
		}
		if(this.isDrawFour() == true){
			buildStr += "Wild Draw 4";
		} else {
			buildStr += "Wild";
		}
		return buildStr;
	}
}
