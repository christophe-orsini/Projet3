package com.openclassrooms.escapegame.model;

import com.openclassrooms.escapegame.utils.*;

/**
 * Modèle du mode attaquant qui gère le jeu en collaboration avec Combinaison
 * @author C.ORSINI
 *
 */
public class ChallengerModel extends Model
{
	// ******************************************************* constructors
	/**
	 * Constructeur créant une proposition de combinaison
	 */
	public ChallengerModel()
	{
		AppLog.getLogger().info("Mode attaquant");
		AppLog.getLogger().debug("Création de la combinaison aléatoire de longueur " + AppConfig.getInstance().getNbDigits());
		changeCombinaison();
	}
	// ******************************************************* methods
	@Override
	public void manageEntry(String response)
	{
		// Verification de la reponse
		if (_modelState.getProposed().isFound(response))
		{
			_modelState.setIWin(true);
		} 
		_modelState.setProposed(_modelState.getProposed().search(response)); // elabore une nouvelle combinaison proposee
		notifyState();
	}
}
