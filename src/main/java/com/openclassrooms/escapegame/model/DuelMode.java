package com.openclassrooms.escapegame.model;

import java.util.Scanner;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;

/**
 * Classe strategie concrete pour le mode de jeu duel
 * @author C.ORSINI
 *
 */
public class DuelMode implements IPlayMode
{
	private static final Logger logger = Logger.getLogger(DuelMode.class);
	private static Scanner entree = new Scanner(System.in); // pour lecture clavier
	
	/**
	 * Constructeur
	 */
	public DuelMode()
	{
		ConsoleAppender appender = (ConsoleAppender)  logger.getAppender("DailyRollingFile"); 
		logger.addAppender(appender);
	}
	
	public void play()
	{
		logger.info("Mode duel");
		logger.debug("Création de la combinaison aléatoire de départ de longueur " + AppConfig.getInstance().getNbDigits());
		
		Combinaison myCombinaison = new Combinaison(AppConfig.getInstance().getNbDigits()); // combinaison de l'ordinateur
		Combinaison searchCombinaison = new Combinaison(AppConfig.getInstance().getNbDigits()); // premiere proposoition de l'ordinateur
		
		logger.info("Combinaison de l'ordinateur = " + myCombinaison);
		logger.info("Proposition de l'ordinateur = " + searchCombinaison);

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
			entree = new Scanner(System.in);
			String yourProposition = entree.nextLine();
			
			// Corrections
			System.out.printf("Tour N°%d -\t%14s : ", nbTours, "Corrections");
			entree = new Scanner(System.in);
			String reponse = entree.next();
			
			if (yourProposition.length() != myCombinaison.getNbDigits() || reponse.length() != searchCombinaison.getNbDigits()) // verfification longueur entrée
			{ 
				System.out.println("Veuillez entrer exactement " + AppConfig.getInstance().getNbDigits() + " valeur(s)SVP !");
				continue;
			}
			
			logger.info("Essai n° " + nbTours + " combinaison proposée : " + yourProposition);
			logger.info("Essai n° " + nbTours + " réponse : " + reponse);
			
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
			
			nbTours++;
		}

		// resultat
		nbTours--;
		if (youWin)
		{
			System.out.println("BRAVO ! Vous avez gangé en " + nbTours + " tentative(s)");
		} 
		else
		{
			System.out.println("Dommage, vous n'avez pas trouvé la combinaison");
		}
		if (iWin)
		{
			System.out.println("YOUPI ! J'ai gangé en " + nbTours + " tentative(s).");
		} 
		else
		{
			System.out.println("Dommage, je n'ai pas trouvé la combinaison.");
		}
	}
}
