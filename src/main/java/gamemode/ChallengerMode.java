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
 * Classe strategie concrete pour le mode de jeu attaquant
 * @author C.ORSINI
 *
 */
public class ChallengerMode implements IPlayMode
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
	public ChallengerMode()
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
		logger.info("Mode attaquant");
		logger.debug("Création de la combinaison aléatoire de départ de longueur " + nbDigits);
		Combinaison combinaison = new Combinaison(nbDigits);
		logger.info("Combinaison = " + combinaison);

		// Consignes
		System.out.println("Pensez à une combinaison à " + nbDigits + " chiffre(s) et je vais la deviner en " + maxTry + " tentative(s).");
		
		// jeu
		boolean win = false; // flag pour sortir de la boucle
		int nbTours = 1; // nombres de tours de jeu
		while (!win && nbTours <= maxTry)
		{
			System.out.printf("%46s%d : %s%n","Voici ma proposition N°", nbTours, combinaison);
			System.out.printf("%50s", "Veuillez m'indiquer mes erreurs avec + - = : ");
			entree = new Scanner(System.in);
			String reponse = entree.nextLine();

			if (reponse.length() != combinaison.getNbDigits()) // verfification longueur entrée
			{ 
				System.out.println("Veuillez entrer exactement " + nbDigits + " symboles + - ou = SVP !");
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
