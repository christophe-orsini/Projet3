/**
 * 
 */
package com.openclassrooms.escapegame;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.openclassrooms.escapegame.controller.*;
import com.openclassrooms.escapegame.model.*;

/**
 * Classe principale du jeu EscapeGame <br />
 * C'est le point d'entrée de l'application
 * 
 * @author C.ORSINI
 */
public class Application
{
	private static Scanner _entry = new Scanner(System.in);
	
	// ***************************************************************** constructors
	@SuppressWarnings("javadoc")
	public Application()
	{
		run();
	}
	@SuppressWarnings("javadoc")
	public static void main(String[] args)
	{
		new Application();
	}
	/**
	 * Methode principale d'execution du jeu permettant de choisir un mode de jeu
	 */
	public void run()
	{
		Model model = null;
		Controller controller = null;
		boolean flag = true; // flag true pour afficher l'option rejouer
		
		// boucle principale du jeu
		int choice = -1;
		while (choice != 0)
		{
			choice = display(flag); // true pour le premier appel sans affichage de l'option rejouer
			switch (choice)
			{
			case 1:
				model = ApplicationFactory.getModel(GameMode.CHALLENGER); // choix 1 creation du modele attaquant...
				controller = ApplicationFactory.getController(GameMode.CHALLENGER, model); // ... et du controleur correspondant
				controller.run(); // execution de la methode principale du controleur
				break;
			case 2:
				model = ApplicationFactory.getModel(GameMode.DEFENDER);
				controller = ApplicationFactory.getController(GameMode.DEFENDER, model);
				controller.run();
				break;
			case 3:
				model = ApplicationFactory.getModel(GameMode.FIGHT);
				controller = ApplicationFactory.getController(GameMode.FIGHT, model);
				controller.run();
				break;
			case 4:
				if (controller != null) {
					model.changeCombinaison();
					controller.run();
				}
			case 0:
				break;
			default:
				System.out.println("Choix invalide !");
				continue;
			}
			flag = false; // pour les autres appels avec l'option rejouer
		}
	}
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
		System.out.print("Faire un choix : ");
		
		int choice = 0;
		try {
			choice = _entry.nextInt();
		} catch (InputMismatchException e) {}
			
		return choice;
	}
}
