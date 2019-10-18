package com.openclassrooms.escapegame.view;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Vue du menu principal de l'application
 * @author C.ORSINI
 *
 */
public class MainMenuView
{
	private static Scanner _entry = new Scanner(System.in);
	
	// ***************************************************************** methods
	/**
	 * Affiche le menu principal et demande un choix <br />
	 * Le parametre permet d'afficher ou de ne pas afficher l'option "Rejouer"
	 * 
	 * @param firstime boolean : true si c'est le premier appel sinon false
	 * @return int : le numero de l'option choisie
	 */
	public int display(boolean firstime)
	{
		// menu principal
		System.out.println("\nMenu principal");
		System.out.println("1 Jouer en mode Attaquant");
		System.out.println("2 Jouer en mode Défenseur");
		System.out.println("3 Jouer en mode Duel");
		if (!firstime) // false = le joueur a deja joue une partie
		{
			System.out.println("4 Rejouer la dernière partie");
		}
		System.out.println("0 Quitter le jeu");
		System.out.print("Faire un choix :");
		
		int choice = 0;
		try {
			choice = _entry.nextInt();
		} catch (InputMismatchException e) {}
			
		return choice;
	}
	/**
	 * Affiche une message à l'écran
	 * @param message
	 */
	public void displayMessage(String message)
	{
		System.out.println(message);
	}
}
