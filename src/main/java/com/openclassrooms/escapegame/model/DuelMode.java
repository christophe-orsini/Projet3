package com.openclassrooms.escapegame.model;

import java.util.Scanner;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;

import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.utils.AppLog;

/**
 * Classe strategie concrete pour le mode de jeu duel
 * @author C.ORSINI
 *
 */
public class DuelMode extends Model
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	
	public void play()
	{
		AppLog.getLogger().info("Mode duel");
		AppLog.getLogger().debug("Création de la combinaison aléatoire de départ de longueur " + AppConfig.getInstance().getNbDigits());
		
		Combinaison myCombinaison = new Combinaison(AppConfig.getInstance().getNbDigits()); // combinaison de l'ordinateur
		Combinaison searchCombinaison = new Combinaison(AppConfig.getInstance().getNbDigits()); // premiere proposoition de l'ordinateur
		
		if (AppConfig.getInstance().isDebug())
		{
			System.out.println(myCombinaison);
		}

		// Consignes
		System.out.println("Nous allons jouer chacun à notre tour pour deviner une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) en " +
				AppConfig.getInstance().getNbTries() + " tentative(s).");
		System.out.println("Le premier qui arrive à deviner la combinaison de l'autre a gagné.");
		System.out.println("On commence, pensez à une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s).");
		
		// jeu
		boolean iWin = false; // flags pour sortir de la boucle
		boolean youWin = false;
		int nbTours = 1; // nombres de tours de jeu
		while (!iWin && !youWin && nbTours <= AppConfig.getInstance().getNbTries())
		{
			// Propositions
			System.out.printf("Tour N°%d -\tMa proposition : %s \t\t Votre proposition : ", nbTours, searchCombinaison);
			_entry = new Scanner(System.in);
			String yourProposition = _entry.nextLine();
			
			if (yourProposition.length() != myCombinaison.getNbDigits() || !myCombinaison.checkDigits(yourProposition)) // verfification longueur entrée
			{ 
				System.out.println("Veuillez entrer exactement " + AppConfig.getInstance().getNbDigits() + " chiffre(s) SVP !");
				continue;
			}
			// Corrections
			System.out.printf("Tour N°%d -\t%14s : ", nbTours, "Corrections");
			_entry = new Scanner(System.in);
			String reponse = _entry.next();
			
			if (reponse.length() != searchCombinaison.getNbDigits() || !searchCombinaison.checkSymbols(reponse)) // verfification longueur entrée
			{ 
				System.out.println("Veuillez entrer exactement " + AppConfig.getInstance().getNbDigits() + " symbole(s) SVP !");
				continue;
			}
			
			AppLog.getLogger().info("Ma proposition N°" + nbTours + " : " + searchCombinaison + " -> Reponse : " + reponse);
			
			// Verifications
			Combinaison propose = new Combinaison(yourProposition);
			if (myCombinaison.equals(propose))
			{
				youWin = true;
			} 
			else
			{
				System.out.printf("%66s : %s\n", "Resultat", myCombinaison.compareTo(propose));
			}
			
			if (searchCombinaison.isFound(reponse))
			{
				iWin = true;
			} 
			else
			{
				searchCombinaison.search(reponse);
			}
			AppLog.getLogger().info("Essai N°" + nbTours + " votre combinaison proposée : " + yourProposition +" -> réponse : " + myCombinaison.compareTo(propose));
			nbTours++;
		}
		nbTours--; // nombre de tentatives
		
		// resultat
		if (youWin)
		{
			System.out.println("BRAVO ! Vous avez gangé en " + nbTours + " tentative(s). Ma combinaison était : " + myCombinaison);
			AppLog.getLogger().info("Joueur gagne en " + nbTours + " tentative(s)");
		} 
		else
		{
			System.out.println("Dommage, vous n'avez pas trouvé la combinaison. C'était : " + myCombinaison);
			AppLog.getLogger().info("Joueur perd aprés " + nbTours + " tentative(s)");
		}
		if (iWin)
		{
			System.out.println("YOUPI ! J'ai gangé en " + nbTours + " tentative(s). J'ai trouvé : " + searchCombinaison);
			AppLog.getLogger().info("Ordinateur gagne en " + nbTours + " tentative(s)");
		} 
		else
		{
			System.out.println("Dommage, je n'ai pas trouvé la combinaison.");
			AppLog.getLogger().info("Ordinateur perd aprés " + nbTours + " tentative(s)");
		}
	}
}
