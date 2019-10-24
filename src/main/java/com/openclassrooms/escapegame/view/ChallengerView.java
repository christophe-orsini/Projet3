package com.openclassrooms.escapegame.view;

import com.openclassrooms.escapegame.controller.*;
import com.openclassrooms.escapegame.model.*;
import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.utils.IConsole;

/**
 * Vue console du mode attaquant qui affiche les infos et demande les entrées
 * 
 * @author C.ORSINI
 *
 */
public class ChallengerView extends View
{
	
	
	// ****************************************************** constructors
	/**
	 * Constructeur enregistrant la vue auprès du modèle
	 * 
	 * @param controller Controller : le controleur qui gère la vue
	 * @param model Model : Le modèle correspondant
	 * @param console IConsole : La console pour les affichages
	 */
	public ChallengerView(Controller controller, Model model, IConsole console)
	{
		super(controller, model, console);
	}
	
	// ******************************************************* methods
	@Override
	public void displayInstructions() {
		// consignes
		_console.displayLine("Vous devez deviner une combinaison à " + AppConfig.getInstance().getNbDigits() + " chiffre(s) en " + AppConfig.getInstance().getNbTries() + " tentative(s).");
		
		// mode developpement
		if (AppConfig.getInstance().isDebug())
		{
			_console.displayLine("Développement : solution = " + _modelState.getSearched());
		}
	}
	@Override
	public String queryEntry(EntryMode entryMode, int tryNumber)
	{
		String message = String.format("%35s %d : ", "Veuillez faire la proposition N°", tryNumber);
		_console.displayLine(message);
		
		String proposition = _console.scan();
		return proposition;
	}
	@Override
	public void displayResult()
	{
		String message = String.format("%37s : %s%n", "Resultat", _modelState.getResult());
		_console.displayLine(message);
	}
	@Override
	public void displayWin(int nbTries)
	{
		_console.displayLine("BRAVO ! Vous avez gangé en " + nbTries + " tentative(s). La réponse est : " + _modelState.getSearched());
	}
	@Override
	public void displayLost()
	{
		_console.displayLine("Dommage, vous n'avez pas trouvé la combinaison. La réponse était : " + _modelState.getSearched());
	}
}
