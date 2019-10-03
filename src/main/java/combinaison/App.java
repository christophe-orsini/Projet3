/**
 * 
 */
package combinaison;

import java.util.Scanner;
import gamemode.*;

/**
 * @author C.ORSINI
 * @version 0.0.0
 */
public class App
{
	private static Scanner entree = new Scanner(System.in);
	private static IPlayMode playMode; // mode de jeu a choisir dans le menu
	
	public static void main(String[] args)
	{
		// menu principal
		int choix = 0;
		while (choix != 4)
		{
			System.out.println("\nMenu principal");
			System.out.println("1 Jouer en mode Défenseur");
			System.out.println("2 Jouer en mode Attaquant");
			System.out.println("3 Jouer en mode Duel");
			System.out.println("4 Quitter le jeu");
			System.out.print("Faire un choix :");
			choix = entree.nextInt();
			switch (choix)
			{
			case 1:
				playMode = new DefenderMode();
				playMode.play();
				break;
			case 2:
				playMode = new ChallengerMode();
				playMode.play();
				break;
			case 3:
				playMode = new DuelMode();
				playMode.play();
				break;
			case 4:
				break;
			default:
				System.out.println("Choix invalide !");
			}
		}
	}
}
