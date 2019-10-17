package com.openclassrooms.escapegame.model;

import com.openclassrooms.escapegame.utils.*;

/**
 * Modèle du mode défenseur qui gère le jeu en collaboration avec Combinaison
 * 
 * @author C.ORSINI
 *
 */
public class DefenderModel extends Model
{
	// ******************************************************* constructors
	/**
	 * Constructeur créant une proposition de combinaison
	 */
	public DefenderModel()
	{
		AppLog.getLogger().info("Mode défenseur");
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
