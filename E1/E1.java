class E1
{
	public static void main(String[] args)
	{
		//int a = Integer.parseInt(args[0]);
		//int b = Integer.parseInt(args[1]);
		//int c = Integer.parseInt(args[2]);
		System.out.println(calculate(14,52,15)); 
	}
	public static int calculate(int card1, int card2, int card3)
	{
		int suitA;
		int suitB;
		int suitC;
		
		int newCard1;
		int newCard2;
		int newCard3;
		
		// This block of code finds the suit for the first input
		if (card1 >= 1 && card1 <= 13) {
			suitA = 1;
		}
		else if (card1 >= 14 && card1 <= 26) {
			suitA = 2;
		}
		else if (card1 >= 27 && card1 <= 39) {
			suitA = 3;
		}
		else if (card1 >= 40 && card1 <= 52) {
			suitA = 4;
		}
		else {
			suitA = 0;
		}
		
		// This block of code finds the suit for the second input
		if (card2 >= 1 && card2 <= 13) {
			suitB = 1;
		}
		else if (card2 >= 14 && card2 <= 26) {
			suitB = 2;
		}
		else if (card2 >= 27 && card2 <= 39) {
			suitB = 3;
		}
		else if (card2 >=  40 && card2 <= 52) {
			suitB = 4;
		}
		else {
			suitB = 0;
		}
		
		// This block of code finds the suit for the third input
		if (card3 >= 1 && card3 <= 13) {
			suitC = 1;
		}
		else if (card3 >= 14 && card3 <= 26) {
			suitC = 2;
		}
		else if (card3 >= 27 && card3 <= 39) {
			suitC = 3;
		}
		else if (card3 >= 40 && card3 <= 52) {
			suitC = 4;
		}
		else {
			suitC = 0;
		}
		
		// This block of code finds the number of the card and reorders them from greatest to least
		if (card1 % 13 > card2 % 13 && card1 % 13 > card3 % 13 && card2 % 13 > card3 % 13) {
			newCard1 = card1;
			newCard2 = card2;
			newCard3 = card3;
		}
		else if (card1 % 13 > card2 % 13 && card1 % 13 > card3 % 13 && card3 % 13 > card2 % 13) {
			newCard1 = card1;
			newCard2 = card3;
			newCard3 = card2;
		}
		else if (card2 % 13 > card1 % 13 && card2 % 13 > card3 % 13 && card1 % 13 > card3 % 13) {
			newCard1 = card2;
			newCard2 = card1;
			newCard3 = card3;
		}
		else if (card2 % 13 > card1 % 13 && card2 % 13 > card3 % 13 && card3 % 13 > card1 % 13) {
			newCard1 = card2;
			newCard2 = card3;
			newCard3 = card1;
		}
		else if (card3 % 13 > card1 % 13 && card3 % 13 > card2 % 13 && card1 % 13 > card2 % 13) {
			newCard1 = card3;
			newCard2 = card1;
			newCard3 = card2;
		}
		else if (card3 % 13 > card1 % 13 && card3 % 13 > card2 % 13 && card2 % 13 > card1 % 13) {
			newCard1 = card3;
			newCard2 = card2;
			newCard3 = card1;
		}
		else {
			newCard1 = card1;
			newCard2 = card2;
			newCard3 = card3;
		}	
		
		// The next block of code checks if the cards have any pattern which would add to their score
		if (suitA == 0 || suitB == 0 || suitC == 0) {
			return 0;
		}
		else if (suitA == suitB && suitA == suitC) {
			if (newCard3 % 13 == 0 && newCard1 % 13 == 12 && newCard2 % 13 == 11) {
				return 10;
			}
			
			else if (newCard1 - 2 == newCard3 && newCard1 - 1 == newCard2) {
				return 10;
			}
			else {
				return 5;
			}
		}
		else if (suitA != suitB && suitA != suitC && suitB != suitC) {
			if (newCard1 % 13 == newCard2 % 13 && newCard1 % 13 == newCard3 % 13) {
				return 8;
			}
			else if (newCard1 % 13 - 2 == newCard3 % 13 && newCard1 % 13 - 1 == newCard2 % 13) {
				return 7;
			}
		}
		if (newCard1 % 13 - 2 == newCard3 % 13 && newCard1 % 13 - 1 == newCard2 % 13) {
			return 7;
		}
		else if (newCard1 % 13 == newCard2 % 13 || newCard1 % 13 == newCard3 % 13 || newCard2 % 13 == newCard3 % 13) {
			return 3;
		}
		if (newCard1 % 13 == 12 && newCard2 % 13 == 11 && newCard3 % 13 == 0) {
			return 7;
		}
		
		// If the cards are valid inputs and have nothing in common, 1 is returned
		return 1;
	}
}
