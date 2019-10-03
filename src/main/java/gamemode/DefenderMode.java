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
 * Classe strategie concrete pour le mode de jeu defenseur
 * @author C.ORSINI
 *
 */
public class DefenderMode implements IPlayMode
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
	public DefenderMode()
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
		logger.info("Mode défenseur");
		logger.debug("Création de la combinaison aléatoire de longueur " + nbDigits);
		Combinaison combinaison = new Combinaison(nbDigits);
		logger.info("Combinaison = " + combinaison);

		if (developpeur)
		{
			System.out.println(combinaison);
		}

		// Consignes
		System.out.println("Vous devez deviner une combinaison à " + nbDigits + " chiffre(s) en " + maxTry + " tentative(s).");

		// jeu
		boolean win = false; // flag pour sortir de la boucle
		int nbTours = 1; // nombres de tours de jeu
		while (!win && nbTours <= maxTry)
		{
			System.out.printf("%35s %d : ", "Veuillez faire la proposition N°", nbTours);
			entree = new Scanner(System.in);
			String proposition = entree.nextLine();

			if (proposition.length() != combinaison.getNbDigits()) // verfification longueur entrée
			{ 
				System.out.println("Veuillez entrer une combianison à " + nbDigits + " chiffre(s)SVP !");
				continue;
			}
			logger.info("Essai n° " + nbTours + " combinaison proposée : " + proposition);
			nbTours++;

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
		}

		// resultat
		if (win)
		{
			nbTours--;
			System.out.println("BRAVO ! Vous avez gangé en " + nbTours + " tentative(s)");
		} 
		else
		{
			System.out.println("Dommage, vous n'avez pas trouvé la combinaison");
		}
	}
}
