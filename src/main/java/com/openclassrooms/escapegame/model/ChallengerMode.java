package com.openclassrooms.escapegame.model;

import java.util.Scanner;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;

/**
 * Classe strategie concrete pour le mode de jeu attaquant
 * @author C.ORSINI
 *
 */
public class ChallengerMode implements IPlayMode
{
	private static final Logger logger = Logger.getLogger(ChallengerMode.class);
	private static Scanner entree = new Scanner(System.in); // pour lecture clavier

	// *********************************************************************** constructors
	/**
	 * Constructeur
	 */
	public ChallengerMode()
	{
		ConsoleAppender appender = (ConsoleAppender)  logger.getAppender("DailyRollingFile"); 
		logger.addAppender(appender);
	}
	
	// ************************************************************************* methods
	public void play()
	{
		logger.info("Mode attaquant");
		logger.debug("Création de la combinaison aléatoire de départ de longueur " + AppConfig.getInstance().getNbDigits());
		Combinaison combinaison = new Combinaison(AppConfig.getInstance().getNbDigits());
		logger.info("Combinaison = " + combinaison);

		// Consignes
		System.out.println("Pensez à une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) et je vais la deviner en " + AppConfig.getInstance().getNbTries() + " tentative(s).");
		
		// jeu
		boolean win = false; // flag pour sortir de la boucle
		int nbTours = 1; // nombres de tours de jeu
		while (!win && nbTours <= AppConfig.getInstance().getNbTries())
		{
			System.out.printf("%46s%d : %s%n","Voici ma proposition N°", nbTours, combinaison);
			System.out.printf("%50s", "Veuillez m'indiquer mes erreurs avec + - = : ");
			entree = new Scanner(System.in);
			String reponse = entree.nextLine();

			if (reponse.length() != combinaison.getNbDigits()) // verfification longueur entrée
			{ 
				System.out.println("Veuillez entrer exactement " + AppConfig.getInstance().getNbDigits() + " symboles + - ou = SVP !");
				continue;
			}
			logger.info("Essai n° " + nbTours + " réponse : " + reponse);
			nbTours++;

			// Verification de la combinaison proposée
			if (combinaison.isFound(reponse))
			{
				win = true;
			} 
			else
			{
				combinaison.search(reponse);
			}
		}

		// resultat
		if (win)
		{
			nbTours--;
			System.out.println("YOUPI ! J'ai gangé en " + nbTours + " tentative(s).");
		} 
		else
		{
			System.out.println("Dommage, je n'ai pas trouvé la combinaison.");
		}
	}
}
