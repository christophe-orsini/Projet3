package com.openclassrooms.escapegame.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.openclassrooms.escapegame.utils.IConsole;

/**
 * Vue du menu principal de l'application
 * @author C.ORSINI
 *
 */
public class MainMenuView
{
	private static Scanner _entry = new Scanner(System.in);
	private IConsole _console;
	
	// ***************************************************************** constructors
	/**
	 * Constructeur prenant une cosole en argument
	 * @param console IConsole : La console pour les affuichages
	 */
	public MainMenuView(IConsole console)
	{
		_console = console;
	}
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
		_console.displayLine("\nMenu principal");
		_console.displayLine("1 Jouer en mode Attaquant");
		_console.displayLine("2 Jouer en mode Défenseur");
		_console.displayLine("3 Jouer en mode Duel");
		if (!firstime) // false = le joueur a deja joue une partie
		{
			_console.displayLine("4 Rejouer la dernière partie");
		}
		_console.displayLine("0 Quitter le jeu");
		_console.display("Faire un choix : ");
		
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
		_console.displayLine(message);
	}
}
