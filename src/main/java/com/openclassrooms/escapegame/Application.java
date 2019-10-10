/**
 * 
 */
package com.openclassrooms.escapegame;

import java.util.Scanner;
import com.openclassrooms.escapegame.model.ChallengerMode;
import com.openclassrooms.escapegame.model.DefenderMode;
import com.openclassrooms.escapegame.model.DuelMode;
import com.openclassrooms.escapegame.model.IPlayMode;

/**
 * @author C.ORSINI
 * @version 0.0.0
 */
public class Application
{
	private static Scanner _entry = new Scanner(System.in);
	private static IPlayMode _playMode; // mode de jeu a choisir dans le menu
	
	@SuppressWarnings("javadoc")
	public static void main(String[] args)
	{
		// menu principal
		int choix = -1;
		while (choix != 0)
		{
			System.out.println("\nMenu principal");
			System.out.println("1 Jouer en mode Défenseur");
			System.out.println("2 Jouer en mode Attaquant");
			System.out.println("3 Jouer en mode Duel");
			if (_playMode != null)
			{
				System.out.println("4 Rejouer la dernière partie");
			}
			System.out.println("0 Quitter le jeu");
			System.out.print("Faire un choix :");
			choix = _entry.nextInt();
			switch (choix)
			{
			case 1:
				_playMode = new DefenderMode();
				_playMode.play();
				break;
			case 2:
				_playMode = new ChallengerMode();
				_playMode.play();
				break;
			case 3:
				_playMode = new DuelMode();
				_playMode.play();
				break;
			case 4:
				if (_playMode != null)
				{
					_playMode.play();
				}
			case 0:
				break;
			default:
				System.out.println("Choix invalide !");
			}
		}
	}
}
