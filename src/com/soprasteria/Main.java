package com.soprasteria;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int[] deck = new int[40];
		String[] seeds = new String[] {"coppe","bastoni","denari","spade"};
		Random r = new Random();
		Scanner in = new Scanner(System.in);
		
		//Generate the shuffled deck
		
		for(int i = 0; i < 40; i++) {
			while(true) {
				boolean alreadyPresent = false;
				int numberToInsert = r.nextInt(40) + 1;
				for(int j = 0; j < 40; j++) {
					if(deck[j] == numberToInsert) {
						alreadyPresent = true;
						break;
					}
				}
				if(!alreadyPresent) {
					deck[i] = numberToInsert;
					break;
				}
			}
		}
		
		//Game logic
		
		float userPoints = 0;
		int counterDeck = 39;
		boolean lost = false;
		while(true) {
			System.out.println("Vuoi una carta? Scrivi 'si' oppure 'no'");
			String request = in.nextLine();
			if(request.equalsIgnoreCase("si")) {
				if((deck[counterDeck]%10 == 8)||(deck[counterDeck]%10 == 9)||(deck[counterDeck]%10 == 0)) {
					userPoints += 0.5;
				}
				else {
					userPoints += deck[counterDeck]%10;
				}
				if(deck[counterDeck]%10 == 0) {
					System.out.println("Hai estratto il 10 di " + seeds[deck[counterDeck]/10] + ".I tuoi punti sono " + userPoints + ".");
				}
				else {
					System.out.println("Hai estratto il " +  deck[counterDeck]%10 + " di " + seeds[deck[counterDeck]/10] + ".I tuoi punti sono " + userPoints + ".");

				}		
				counterDeck--;
				if(userPoints > 7.5) {
					lost = true;
					break;
				}
			}
			else {
				System.out.println("Ok, hai finito. I tuoi punti attuali sono " + userPoints);
				break;
			}
		}
		float dealerPoints = 0;
		if(!lost) {
			System.out.println("Adesso tocca al banco, cioè a me: ");
			while((dealerPoints < userPoints) && (dealerPoints < 7.5) ) {
				if((deck[counterDeck]%10 == 8)||(deck[counterDeck]%10 == 9)||(deck[counterDeck]%10 == 0)) {
					dealerPoints += 0.5;
				}
				else {
					dealerPoints += deck[counterDeck]%10;
				}
				if(deck[counterDeck]%10 == 0) {
					System.out.println("Ho estratto il 10 di " + seeds[deck[counterDeck]/10] + ".I miei punti sono " + dealerPoints + ".");
				}
				else {
					System.out.println("Ho estratto il " +  deck[counterDeck]%10 + " di " + seeds[deck[counterDeck]/10] + ".I miei punti sono " + dealerPoints + ".");

				}		
				counterDeck--;
			}
			if((dealerPoints > userPoints) && dealerPoints <= 7.5) {
				lost = true;
			}
		}
		if(!lost) {
			System.out.println("Complimenti! Hai vinto. Ho sforato.");
		}
		else {
			System.out.println("Ops! Stavolta hai perso. Ho fatto " + dealerPoints);
		}
	}
}

