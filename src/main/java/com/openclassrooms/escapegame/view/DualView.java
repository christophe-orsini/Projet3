package com.openclassrooms.escapegame.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import com.openclassrooms.escapegame.controller.*;
import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.utils.*;

/**
 * Vue console du mode défenseur qui affiche les infos et demande les entrées
 * @author C.ORSINI
 *
 */
public class DualView extends View
{
	private static Scanner _entry = new Scanner(System.in); // pour lecture clavier
	
	private boolean _challenger;
	
	// ****************************************************** constructors
	/**
	 * Constructeur enregistrant la vue auprès du modèle
	 * @param controller DualController : le controleur qui gère la vue
	 * @param model DualModel : Le modèle correspondant
	 */
	public DualView(Controller controller, Model model)
	{
		super(controller, model);
	}
	
	// ******************************************************* methods
	/**
	 * Affiche les consignes à l'écran
	 */
	public void displayInstructions() {
		// consignes
		System.out.println("Nous allons jouer chacun à notre tour pour deviner une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) en " +
				AppConfig.getInstance().getNbTries() + " tentative(s).");
		System.out.println("Le premier qui arrive à deviner la combinaison de l'autre a gagné.");
		System.out.println("Avant de commmencer, pensez à une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s).");
		System.out.println("D'abord vous corrigez ma proposition et ensuite vous essayez de deviner ma combinaison. Allons-y !");
		
		// mode developpement
		if (AppConfig.getInstance().isDebug())
		{
			System.out.println("Développement : solution = " + _modelState.getSearched());
		}
	}
	/**
	 * Demande la proposition
	 * @param tryNumber
	 * @return
	 */
	@Override
	public String queryEntry(int tryNumber)
	{
		if (_challenger)
		{
			System.out.printf("%65s %d : ", "Veuillez faire votre proposition N°", tryNumber);
			_entry = new Scanner(System.in);
			String proposition = _entry.nextLine();
			_challenger = !_challenger;
			return proposition;
		}
		else
		{
			System.out.printf("%46s%d : %s%n","Voici ma proposition N°", tryNumber, _modelState.getProposed());
			System.out.printf("%50s", "Veuillez m'indiquer mes erreurs avec + - = : ");
			_entry = new Scanner(System.in);
			String proposition = _entry.nextLine();
			_challenger = !_challenger;
			return proposition;
		}
	}
	@Override
	public void displayResult()
	{
		System.out.printf("%67s : %s%n", "Resultat", _modelState.getResult());
	}
	@Override
	public void displayWin(int nbTries)
	{
		if (_modelState.isYouWin())
		{
			System.out.println("BRAVO ! Vous avez gangé en " + nbTries + " tentative(s). La réponse est : " + _modelState.getSearched());
		}
		if (_modelState.isIWin())
		{
			System.out.println("YOUPI ! J'ai gangé en " + nbTries + " tentative(s). La réponse est : " + _modelState.getProposed());
		}
	}
	@Override
	public void displayLost()
	{
		if (!_modelState.isYouWin())
		{
			System.out.println("Dommage, vous n'avez pas trouvé la combinaison. La réponse était : " + _modelState.getSearched());
		}
		if (!_modelState.isIWin())
		{
			System.out.println("Dommage, je n'ai pas trouvé la combinaison.");
		}
	}
}
