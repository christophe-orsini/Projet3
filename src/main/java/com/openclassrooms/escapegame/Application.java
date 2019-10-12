/**
 * 
 */
package com.openclassrooms.escapegame;

import com.openclassrooms.escapegame.controller.*;

/**
 * Classe principale du jeu EscapeGame
 * @author C.ORSINI
 */
public class Application
{
	@SuppressWarnings("javadoc")
	public static void main(String[] args)
	{
		// creation du controleur principal
		MainController mainController = new MainController();
		
		// execution 
		mainController.run();
	}
}
