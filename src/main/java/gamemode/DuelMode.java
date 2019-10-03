package gamemode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.ini4j.Ini;

import combinaison.App;
import combinaison.Combinaison;

/**
 * Classe strategie concrete pour le mode de jeu duel
 * @author C.ORSINI
 *
 */
public class DuelMode implements IPlayMode
{
	private static final Logger logger = Logger.getLogger(App.class);
	private static Scanner entree = new Scanner(System.in); // pour lecture clavier
	
	// declaration des parametres de l'application
	private int nbDigits = 4;
	private int maxTry = 5;
	private boolean developpeur = false;
			
	/**
	 * Constructeur
	 */
	public DuelMode()
	{
		ConsoleAppender appender = (ConsoleAppender)  logger.getAppender("DailyRollingFile"); 
		logger.addAppender(appender);
		
		// lecture du fichier app.ini
		Ini ini = new Ini();
		try
		{
			ini.load(new FileReader("app.ini"));
			
			Ini.Section dev = ini.get("developpeur");
			developpeur = Integer.parseInt(dev.get("active"))==0?false:true;
			Ini.Section dflt = ini.get("config");
			nbDigits = Integer.parseInt(dflt.get("nb_digits"));
			maxTry = Integer.parseInt(dflt.get("max_try"));
		} 
		catch (FileNotFoundException e)
		{
			// utilisation de valeurs par defaut
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void play()
	{
		logger.info("Mode duel");
		logger.debug("Création de la combinaison aléatoire de départ de longueur " + nbDigits);
		
		Combinaison myCombinaison = new Combinaison(nbDigits); // combinaison de l'ordinateur
		Combinaison searchCombinaison = new Combinaison(nbDigits); // premiere proposoition de l'ordinateur
		
		logger.info("Combinaison de l'ordinateur = " + myCombinaison);
		logger.info("Proposition de l'ordinateur = " + searchCombinaison);

		if (developpeur)
		{
			System.out.println(myCombinaison);
		}

		// Consignes
		System.out.println("Nous allons jouer chacun à notre tour pour deviner une combinaison à " + nbDigits + " chiffre(s) en " + maxTry + " tentative(s).");
		System.out.println("Le premier qui arrive à deviner la combinaison de l'autre a gagné.");
		System.out.println("On commence, pensez à une combinaison à " + nbDigits + " chiffre(s).");
		
		// jeu
		boolean iWin = false; // flags pour sortir de la boucle
		boolean youWin = false;
		int nbTours = 1; // nombres de tours de jeu
		while (!iWin && !youWin && nbTours <= maxTry)
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
				System.out.println("Veuillez entrer exactement " + nbDigits + " valeur(s)SVP !");
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
