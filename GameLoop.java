import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLoop {

	public GameLoop(){
		//		boolean again = true;
		int winner = 0;
		//		while(again){
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to UNO!\nA program by Nicholas Jackson.\nHow many players would you like to play with?");
		int numPlayers = scan.nextInt();
		System.out.println("You have selected " + numPlayers + " players. How many decks would you like to use? (108 cards in a deck)");
		int numDecks = scan.nextInt();
		Deck deck = new Deck(numDecks);
		Hand[] players = this.dealHands(deck, numPlayers, 0);
		winner = this.playGame(players, deck);
		System.out.println("The winner is player " + (winner + 1) + ", congradulations!");
		//		System.out.println("Would you like to play again?");
		//			boolean input = false;
		//			String answer= "";
		//			while(!input){
		//				answer = scan.nextLine();
		//				if(!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")){
		//					System.out.println("Please enter a 'y' or a 'n'.");
		//				} else {
		//					input = true;
		//				}
		//			}
		//			if(answer.equals("n")){
		//				again = false;
		//				System.out.println("Alright, restarting!");
		//			} else {
		//				scan.close();
		//				System.out.println("Thanks for playing, goodbye!");
		//			}
		//		}
	}

	public Hand[] dealHands(Deck deck, int numPlayers, int computerPlayers){

		Hand[] players = new Hand[numPlayers];

		for(int i = 0 ; i < numPlayers ; i ++){
			players[i] = new Hand();
		}

		for(int i = 0 ; i < numPlayers - computerPlayers ; i ++){
			Card[] cards = new Card[]{
					deck.dealCard(),
					deck.dealCard(),
					deck.dealCard(),
					deck.dealCard(),
					deck.dealCard(),
					deck.dealCard(),
					deck.dealCard()
			};
			players[i] = new Hand(cards);
		}

		for(int i = numPlayers - computerPlayers - 1 ; i < computerPlayers ; i ++){
			Card[] cards = new Card[]{
					deck.dealCard(),
					deck.dealCard(),
					deck.dealCard(),
					deck.dealCard(),
					deck.dealCard(),
					deck.dealCard(),
					deck.dealCard()
			};
			System.out.println(cards);
			players[i] = new ComputerPlayer(cards);
		}
		return players;
	}

	public int nextPlayer(Hand[] players, int currentPlayer, int direction){

		if(currentPlayer + direction < 0){
			return players.length - 1;
		} else if(currentPlayer + direction >= players.length){
			return 0;
		} else {
			return currentPlayer + direction;
		}
	}

	public Hand pickUpCards(Hand hand, Deck deck, int number){
		for(int i = 0 ; i < number ; i ++){
			hand.addCard(deck.dealCard());
		}
		return hand;
	}

	public boolean playCard(Card topCard, Card playedCard){
		if(topCard.getColour() == playedCard.getColour()){
			return true;
		} else if(topCard.getClass() == SpecialCard.class && playedCard.getClass() == SpecialCard.class){
			if (((SpecialCard)topCard).getType() == ((SpecialCard)playedCard).getType()){
				return true;
			}
		} else if(topCard.getClass() == NumberCard.class && playedCard.getClass() == NumberCard.class){
			if (((NumberCard)topCard).getNumber() == ((NumberCard)playedCard).getNumber()){
				return true;
			}
		}
		return false;
	}

	public WildCard playWild(WildCard card, Scanner s){
		boolean y = false;
		int selected = 0;
		while(!y){
			System.out.println("Please enter: \n1 for BLUE \n2 for GREEN \n3 for RED \n4 for YELLOW");
			boolean isValid = false;
			while(!isValid){
				try{
					isValid = true;
					selected = s.nextInt();
				} catch(InputMismatchException e){
					isValid = false;
					System.out.println("It's gotta be number buddy, you just entered letters...");
					s.next();
				}
				if(selected < 1 || selected > 4){
					System.out.println("The number has to be either 1, 2 ,3 or 4.");
					isValid = false;
				}
			}
			switch (selected) {
			case 1 : card.setColour(Card.Colour.BLUE);
			y = true;
			break;
			case 2 : card.setColour(Card.Colour.GREEN);
			y = true;
			break;
			case 3 : card.setColour(Card.Colour.RED);
			y = true;
			break;
			case 4 : card.setColour(Card.Colour.YELLOW);
			y = true;
			break;
			default : System.out.println("Please enter a number between 1 and 4.");
			break;
			}
		}
		return card;
	}

	public int playGame(Hand[] players, Deck deck){

		Scanner scan = new Scanner(System.in);
		int winner = 0;
		int currentPlayer = 0;
		int direction = 1;
		boolean newCard = true;
		Card topCard = deck.dealCard();
		System.out.println("\nNow starting the game! The top card is: " + topCard + "\n");
		if (topCard.getClass() == WildCard.class){
			System.out.println("The top card is a wild! Player 1 can choose the colour.");
			System.out.println("Here are your cards:\n");
			System.out.println(players[currentPlayer]);
			topCard = this.playWild((WildCard)topCard, scan);
		}
		boolean gameWon = false;

		while(!gameWon){
			if(topCard.getClass() == SpecialCard.class && newCard == true){
				if((Card.Special)((SpecialCard)topCard).getType() == Card.Special.DRAW){
					players[currentPlayer] = this.pickUpCards(players[currentPlayer], deck, 2);
					System.out.println("Drawing two cards for player " + (currentPlayer + 1));
					currentPlayer = this.nextPlayer(players, currentPlayer, direction);
				} else if((Card.Special)((SpecialCard)topCard).getType() == Card.Special.REVERSE){
					if(direction == 1){
						direction = -1;
					} else {
						direction = 1;
					}
					currentPlayer = this.nextPlayer(players, currentPlayer, direction);
					System.out.println("Reversing direction!\n");
				} else if((Card.Special)((SpecialCard)topCard).getType() == Card.Special.SKIP){
					System.out.println("Skipping player " + (currentPlayer + 1) + ".\n");
					currentPlayer = this.nextPlayer(players, currentPlayer, direction);
				}
			} else if (topCard.getClass() == WildCard.class){
				if(((WildCard)topCard).isDrawFour()){
					System.out.println("Picking up four cards for player " + (currentPlayer + 1) + "\n");
					players[currentPlayer] = this.pickUpCards(players[currentPlayer], deck, 4);
					currentPlayer = this.nextPlayer(players, currentPlayer, direction);
				}
			}
			if(players[currentPlayer].getClass() != ComputerPlayer.class){
				System.out.println("\n\n\nPlayer " + (currentPlayer + 1) + "'s turn, here are your cards:\n");
				System.out.println(players[currentPlayer]);
				System.out.println("-----------------The top card is now " + topCard + ".-------------------");

				boolean accept = false;
				int selected = 0;

				while(!accept){
					System.out.println("\nEnter the number of the card you want to play ('1' for the first card, '2' for the second etc.)");
					System.out.println("Be sure to match up either the colour or the type of card. (Wilds can be played on anything!)");
					System.out.println("Enter 0 to pick up a card if you can't play.");
					boolean isValid = false;
					while(!isValid){
						try{
							isValid = true;
							selected = scan.nextInt() - 1;
						} catch(InputMismatchException e){
							isValid = false;
							System.out.println("It's gotta be number buddy, you just entered letters...");
							scan.next();
						}
					}
					// if the entered number is -1, the player wishes to draw a card.
					if (selected == -1){
						System.out.println("Drawing a shiny new card!");
						Card draw = deck.dealCard();
						System.out.println("You drew a " + draw);

						if(this.playCard(topCard, draw)){
							System.out.println("Looks like you can play the card! Enter 'y' if you would like to, or 'n' if not.");
							boolean input = false;
							String answer = "";
							while(!input){
								answer = scan.nextLine();
								if(!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")){
									System.out.println("Please enter a 'y' or a 'n'.");
								} else {
									input = true;
								}
							}
							if(answer.toLowerCase().equals("y")){
								System.out.println("Alright, playing card.");
								topCard = draw;
							} else {
								System.out.println("Adding card to your hand.");
								players[currentPlayer].addCard(draw);
							}
						} else {
							System.out.println("Looks like you can't play the card, adding it to your hand.");
							players[currentPlayer].addCard(draw);
							newCard = false;
						}
						accept = true;
						// else it will be treated as a valid input
					} else {
						if (selected >= 0 && selected < (players[currentPlayer].getHand()).length){
							accept = this.playCard(topCard, (players[currentPlayer].getHand())[selected]);
							if(accept == false && (players[currentPlayer].getHand())[selected].getClass() != WildCard.class) {
								System.out.println("Oops, looks like you can't play that card! Try again.");
							} else {
								topCard = (players[currentPlayer].getHand())[selected];
								players[currentPlayer].removeCard(selected);
								if(topCard.getClass() == WildCard.class){
									topCard = this.playWild((WildCard)topCard, scan);
									accept = true;
								}
								newCard = true;
							}
						} else  {
							System.out.println("That card doesn't exist buddy chum pal, try again.");
						}
					}
				}
			} else {
				int card = ((ComputerPlayer)players[currentPlayer]).getBestPlayableCard(topCard);
				topCard = (players[currentPlayer].getHand())[card];
				System.out.println("The computer played: " + topCard);
				players[currentPlayer].removeCard(card);
			}
			for(int i = 0 ; i < players.length ; i ++){
				if(players[i].getHand().length == 0){
					winner = i;
					gameWon = true;
				}
			}
			currentPlayer = this.nextPlayer(players, currentPlayer, direction);
		}
		scan.close();
		return winner;
	}

	public static void main(String[] args) {

		GameLoop loop = new GameLoop();
	}
}