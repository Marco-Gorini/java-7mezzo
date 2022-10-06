package com.soprasteria;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int[] deck = new int[40];
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
				if(deck[counterDeck] >= 1 && deck[counterDeck] <= 10) {
					if(deck[counterDeck] == 8 || deck[counterDeck] == 9 || deck[counterDeck] == 10) {
						userPoints += 0.5;
					}
					else {
						userPoints += (deck[counterDeck]%10);
					}
					System.out.println("Hai estratto il " + deck[counterDeck] + " di bastoni.I tuoi punti sono attualmente " + userPoints);
				}
				if(deck[counterDeck] >= 11 && deck[counterDeck] <= 20) {
					if(deck[counterDeck] == 18 || deck[counterDeck] == 19 || deck[counterDeck] == 20) {
						userPoints += 0.5;
					}
					else {
						userPoints += (deck[counterDeck]%10);
					}
					System.out.println("Hai estratto il " + (deck[counterDeck]-10) + " di coppe.I tuoi punti sono attualmente " + userPoints);
				}
				if(deck[counterDeck] >= 21 && deck[counterDeck] <= 30) {
					if(deck[counterDeck] == 28 || deck[counterDeck] == 29 || deck[counterDeck] == 30) {
						userPoints += 0.5;
					}
					else {
						userPoints += (deck[counterDeck]%10);
					}
					System.out.println("Hai estratto il " + (deck[counterDeck]-20) + " di denari.I tuoi punti sono attualmente " + userPoints);
				}
				if(deck[counterDeck] >= 31 && deck[counterDeck] <= 40) {
					if(deck[counterDeck] == 38 || deck[counterDeck] == 39 || deck[counterDeck] == 40) {
						userPoints += 0.5;
					}
					else {
						userPoints += (deck[counterDeck]%10);
					}
					System.out.println("Hai estratto il " + (deck[counterDeck]-30) + " di coppe.I tuoi punti sono attualmente " + userPoints);
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
			while((dealerPoints < userPoints) || (dealerPoints < 7.5) ) {
				if((deck[counterDeck] % 10 == 8)||(deck[counterDeck] % 10 == 9) ||(deck[counterDeck] % 10 == 0)) {
					dealerPoints += 0.5;
				}
				else {
					dealerPoints += (deck[counterDeck] % 10);
				}
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

