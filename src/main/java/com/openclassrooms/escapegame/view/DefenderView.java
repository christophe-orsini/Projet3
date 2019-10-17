package com.openclassrooms.escapegame.view;

import java.util.Scanner;
import com.openclassrooms.escapegame.controller.*;
import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.utils.AppConfig;

/**
 * Vue console du mode défenseur qui affiche les infos et demande les entrées
 * @author C.ORSINI
 *
 */
public class DefenderView extends View
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	
	// ****************************************************** constructors
	/**
	 * Constructeur enregistrant la vue auprès du modèle
	 * @param controller DefenderController : le controleur qui gère la vue
	 * @param model DefenderModel : Le modèle correspondant
	 */
	public DefenderView(Controller controller, Model model)
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
		System.out.println("Vous devez deviner une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) en " + AppConfig.getInstance().getNbTries() + " tentative(s).");
		
		// mode developpement
		if (AppConfig.getInstance().isDebug())
		{
			System.out.println("Développement : solution = " + _modelState.getSearched());
		}
	}
	/**
	 * Demande la proposition
	 * @param tryNumber int : Numero de la tentative en cours
	 * @return String : L'entrée au clavier
	 */
	@Override
	public String queryEntry(int tryNumber)
	{
		System.out.printf("%35s %d : ", "Veuillez faire la proposition N°", tryNumber);
		_entry = new Scanner(System.in);
		String proposition = _entry.nextLine();
		return proposition;
	}
	/**
	 * Affiche la reponse de l'ordinateur
	 */
	@Override
	public void displayResult()
	{
		System.out.printf("%37s : %s%n", "Resultat", _modelState.getResult());
	}
	/**
	 * Affiche le message de victoire
	 * @param nbTries int : nombre d'essais pour ganger
	 */
	@Override
	public void displayWin(int nbTries)
	{
		System.out.println("BRAVO ! Vous avez gangé en " + nbTries + " tentative(s). La réponse est : " + _modelState.getSearched());
	}
	/**
	 * Affiche le message de defaite
	 */
	@Override
	public void displayLost()
	{
		System.out.println("Dommage, vous n'avez pas trouvé la combinaison. La réponse était : " + _modelState.getSearched());
	}
}