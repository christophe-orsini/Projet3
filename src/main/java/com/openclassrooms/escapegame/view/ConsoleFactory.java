package com.openclassrooms.escapegame.view;

/**
 * @author C.ORSINI
 *
 */
public class ConsoleFactory
{
	/**
	 * Méthode statique de construction de Conole
	 * @param type String : Type de Console à construire (inop pour le moment)
	 * @return IConsole : La Console
	 */
	public static IConsole getConsole(String type)
	{
		return new Console();
	}
}
