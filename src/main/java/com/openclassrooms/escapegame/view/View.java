package com.openclassrooms.escapegame.view;

/**
 * Classe abstarite du patterne MVC pour la vue
 * @author C.ORSINI
 *
 */
public abstract class View
{
	/**
	 * Affiche un ecran et demande un choix
	 * @param firstTime boolean : true si c'est le premier appel sinon false 
	 * @return le numero choisi
	 */
	public int show(boolean firstTime)
	{
		return -1;
	}; 

}
