package com.openclassrooms.escapegame.view;

/**
 * @author C.ORSINI
 * Interface du mode console
 */
public interface IConsole
{
	/**
	 * Affichage d'un message
	 * @param message String : le message
	 */
	public void display(String message);
	/**
	 * Affichage d'un message et retour à la ligne
	 * @param message String : le message
	 */
	public void displayLine(String message);
	/**
	 * Scanne l'entrée de la console
	 * @return String : La valeur entrée
	 */
	public String scan();
}
