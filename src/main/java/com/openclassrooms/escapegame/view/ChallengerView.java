package com.openclassrooms.escapegame.view;

import java.util.Scanner;
import com.openclassrooms.escapegame.controller.*;
import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.utils.*;

/**
 * Vue console du mode attaquant qui affiche les infos et demande les entrées
 * @author C.ORSINI
 *
 */
public class ChallengerView extends View
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	
	// ****************************************************** constructors
	/**
	 * Constructeur enregistrant la vue auprès du modèle
	 * @param controller ChallengerController : le controleur qui gère la vue
	 * @param model ChallengerModel : Le modèle correspondant
	 */
	public ChallengerView(Controller controller, Model model)
	{
		super(controller, model);
	}
	
	// ******************************************************* methods
	/**
	 * Affiche les consignes à l'écran
	 */
	@Override
	public void displayInstructions() {
		// consignes
		System.out.println("Pensez à une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) et je vais la deviner en " + AppConfig.getInstance().getNbTries() + " tentative(s).");
		
	}
	/**
	 * Affiche une proposition et demande les corrections a apporter sous forme de symboles +, - ou =
	 * @param tryNumber int : le numero de la tentative en cours
	 * @return String : la reponse sous forme de symboles +, - ou =
	 */
	@Override
	public String queryEntry(int tryNumber)
	{
		System.out.printf("%46s%d : %s%n","Voici ma proposition N°", tryNumber, _modelState.getProposed());
		System.out.printf("%50s", "Veuillez m'indiquer mes erreurs avec + - = : ");
		_entry = new Scanner(System.in);
		String proposition = _entry.nextLine();
		return proposition;
	}
	/**
	 * Affiche le message de victoire
	 * @param nbTries int : nombre d'essais pour ganger
	 */
	@Override
	public void displayWin(int nbTries)
	{
		System.out.println("YOUPI ! J'ai gangé en " + nbTries + " tentative(s). La réponse est : " + _modelState.getProposed());
	}
	/**
	 * Affiche le message de defaite
	 */
	@Override
	public void displayLost()
	{
		System.out.println("Dommage, je n'ai pas trouvé la combinaison.");
	}
	
	@Override
	public void displayResult() {
		// rien a faire
	}
}