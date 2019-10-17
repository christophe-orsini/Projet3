package com.openclassrooms.escapegame.model;

import com.openclassrooms.escapegame.utils.*;

/**
 * Modèle du mode attaquant qui gère le jeu en collaboration avec Combinaison
 * 
 * @author C.ORSINI
 *
 */
public class ChallengerModel extends Model
{
	// ******************************************************* constructors
	/**
	 * Constructeur créant une combinaison à trouver
	 */
	public ChallengerModel()
	{
		AppLog.getLogger().info("Mode attaquant");
		AppLog.getLogger().debug("Création de la combinaison aléatoire de longueur " + AppConfig.getInstance().getNbDigits());
		changeCombinaison();
		AppLog.getLogger().info("Combinaison à trouver : " + _modelState.getSearched());
	}
	// ******************************************************* methods
	@Override
	public void manageEntry(String proposition)
	{
		Combinaison propose = new Combinaison(proposition); // Transforme la proposition String en Combinaison ...
		if (_modelState.getSearched().equals(propose)) // ... pour verifier l'egalite
		{
			_modelState.setYouWin(true);
		}
		_modelState.setResult(_modelState.getSearched().compareTo(propose)); // elabore la reponse
		notifyState();
	}
}
