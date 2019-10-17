/**
 * 
 */
package com.openclassrooms.escapegame;

import com.openclassrooms.escapegame.controller.*;

/**
 * Classe principale du jeu EscapeGame <br />
 * C'est le point d'entrée de l'application
 * 
 * @author C.ORSINI
 */
public class Application
{
	@SuppressWarnings("javadoc")
	public Application()
	{
		// creation du controleur principal
		MainController mainController = new MainController();
		
		// execution 
		mainController.run();
	}
	@SuppressWarnings("javadoc")
	public static void main(String[] args)
	{
		new Application();
	}
}
