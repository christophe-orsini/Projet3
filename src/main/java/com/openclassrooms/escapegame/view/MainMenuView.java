package com.openclassrooms.escapegame.view;

import java.util.Scanner;

/**
 * Vue du menu principal de l'application
 * @author C.ORSINI
 *
 */
public class MainMenuView
{
	private static Scanner _entry = new Scanner(System.in);
	
	/**
	 * Affiche le menu principal et demande un choix
	 * @param firstime boolean : true si c'est le premier appel sinon false
	 * @return le numero choisi
	 */
	public int display(boolean firstime)
	{
		// menu principal
		System.out.println("\nMenu principal");
		System.out.println("1 Jouer en mode Défenseur");
		System.out.println("2 Jouer en mode Attaquant");
		System.out.println("3 Jouer en mode Duel");
		if (!firstime) // false = le joueur a deja joue une partie
		{
			System.out.println("4 Rejouer la dernière partie");
		}
		System.out.println("0 Quitter le jeu");
		System.out.print("Faire un choix :");
		int choice = _entry.nextInt();
			
		return choice;
	}
}
