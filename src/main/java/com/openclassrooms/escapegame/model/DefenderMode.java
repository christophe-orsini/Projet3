package com.openclassrooms.escapegame.model;

import java.util.Scanner;

import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.utils.AppLog;

/**
 * Classe strategie concrete pour le mode de jeu defenseur
 * @author C.ORSINI
 *
 */
public class DefenderMode implements IPlayMode
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	
	public void play()
	{
		AppLog.getLogger().info("Mode défenseur");
		AppLog.getLogger().debug("Création de la combinaison aléatoire de longueur " + AppConfig.getInstance().getNbDigits());
		Combinaison combinaison = new Combinaison(AppConfig.getInstance().getNbDigits());
		AppLog.getLogger().info("Combinaison à trouver : " + combinaison);

		if (AppConfig.getInstance().isDebug())
		{
			System.out.println(combinaison);
		}

		// Consignes
		System.out.println("Vous devez deviner une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) en " + AppConfig.getInstance().getNbTries() + " tentative(s).");

		// jeu
		boolean win = false; // flag pour sortir de la boucle
		int nbTours = 1; // nombres de tours de jeu
		while (!win && nbTours <= AppConfig.getInstance().getNbTries())
		{
			System.out.printf("%35s %d : ", "Veuillez faire la proposition N°", nbTours);
			_entry = new Scanner(System.in);
			String proposition = _entry.nextLine();

			if (proposition.length() != combinaison.getNbDigits()) // verfification longueur entrée
			{ 
				System.out.println("Veuillez entrer une combianison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s)SVP !");
				continue;
			}

			// Verification de la combinaison proposée
			Combinaison propose = new Combinaison(proposition);
			if (combinaison.equals(propose))
			{
				win = true;
			} 
			else
			{
				System.out.printf("%37s : %s%n", "Resultat", combinaison.compareTo(propose));
			}
			AppLog.getLogger().info("Essai N°" + nbTours + " combinaison proposée : " + proposition +" -> réponse : " + combinaison.compareTo(propose));
			nbTours++;
		}
		nbTours--; // nombre tentatives
		
		// resultat
		if (win)
		{
			System.out.println("BRAVO ! Vous avez gangé en " + nbTours + " tentative(s)");
			AppLog.getLogger().info("Gagné en " + nbTours + " tentative(s)");
		} 
		else
		{
			System.out.println("Dommage, vous n'avez pas trouvé la combinaison");
			AppLog.getLogger().info("Perdu aprés " + nbTours + " tentative(s)");
		}
	}
}
