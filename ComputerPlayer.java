
public class ComputerPlayer extends Hand {

	public int getBestPlayableCard(Card topCard){

		Card[] playerHand = this.getHand();
		Card[] playAble = new Card[0];
		int j = 0;
		for(int i = 0 ; i < playerHand.length ; i ++){
			System.out.println(i);
			if(playerHand[i].isPlayAble(topCard)){
				Card[] newArray = new Card[playerHand.length + 1];
				for(int k = 0 ; k < playAble.length ; i ++){
					newArray[k] = playAble[k];
				}
				newArray[j+1] = playerHand[i];
				j++;
				playAble = newArray;
			}
		}

		boolean loop = true;
		while(loop){
			loop = false;
			for(int i = 0 ; i < playAble.length ; i ++){
				for(int k = i + 1 ; k < playAble.length ; k ++){
					if((playAble[i].getClass() == NumberCard.class && playAble[k].getClass() == NumberCard.class) ||
							(playAble[k].getClass() == SpecialCard.class && playAble[i].getClass() != SpecialCard.class)){
						if(((NumberCard)playAble[i]).getNumber() < ((NumberCard)playAble[k]).getNumber()){
							loop = true;
							Card card = playAble[i];
							playAble[i] = playAble[k];
							playAble[k] = card;
						}
					} else if (playAble[k].getClass() == SpecialCard.class && playAble[i].getClass() == SpecialCard.class){
						if(((SpecialCard)playAble[k]).getType() == Card.Special.DRAW){
							loop = true;
							Card card = playAble[i];
							playAble[i] = playAble[k];
							playAble[k] = card;
						} else if(((SpecialCard)playAble[k]).getType() == Card.Special.SKIP && 
								((SpecialCard)playAble[i]).getType() == Card.Special.REVERSE){
							loop = true;
							Card card = playAble[i];
							playAble[i] = playAble[k];
							playAble[k] = card;
						}
					}
				}
			}
		}
		
		Card playedCard = playAble[0];
		int i = 0;
		boolean found = false;
		while(!found && i < playerHand.length){
			if(playedCard.equals(playerHand[i])){
				found = true;
				return i;
			}
			i ++;
		}
		return -1;
	}
	
	public ComputerPlayer(Card...cards){
		super(cards);
	}
}
