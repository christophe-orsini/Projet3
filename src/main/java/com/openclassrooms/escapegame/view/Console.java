package com.openclassrooms.escapegame.view;

import java.util.Scanner;

/**
 * Classe pour affichage à l'écran en mode console
 * @author C.ORSINI
 *
 */
public class Console implements IConsole
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	
	@Override
	public void display(String message)
	{
		System.out.print(message);
	}

	@Override
	public void displayLine(String message) {
		System.out.println(message);
	}
	@Override
	public String scan() {
		_entry = new Scanner(System.in);
		String entry = _entry.nextLine();
		return entry;
	}
}
