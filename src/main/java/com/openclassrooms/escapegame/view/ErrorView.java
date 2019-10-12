package com.openclassrooms.escapegame.view;

/**
 * Vue affichant un message d'erreur
 * @author C.ORSINI
 *
 */
public class ErrorView
{
	private String _message;
	
	// *************************************************************** constructors
	public ErrorView(String message)
	{
		_message = message;
	}
	// ************************************************************** methods
	/**
	 * Affiche le message a l'ecran
	 */
	public void display()
	{
		System.out.println(_message);
	}
}
