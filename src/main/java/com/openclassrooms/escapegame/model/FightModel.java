package com.openclassrooms.escapegame.model;

import java.util.regex.Pattern;

import com.openclassrooms.escapegame.utils.*;

/**
 * Modèle du mode duel qui gère le jeu en collaboration avec Combinaison
 * 
 * @author C.ORSINI
 *
 */
public class FightModel extends Model
{
	// ******************************************************* constructors
	/**
	 * Constructeur créant une combinaison à trouver et une proposition
	 */
	public FightModel()
	{
		AppLog.getLogger().info("Mode duel");
		AppLog.getLogger().debug("Création de la combinaison aléatoire de longueur " + AppConfig.getInstance().getNbDigits());
		changeCombinaison();
		AppLog.getLogger().info("Combinaison à trouver : " + _modelState.getSearched());
	}
	// ******************************************************* methods
	@Override
	public void manageEntry(String proposition)
	{
		if (Pattern.matches("^[0-9]*$", proposition)) // si la proposition est une combinaison
		{
			Combinaison propose = new Combinaison(proposition); // Transforme la proposition String en Combinaison ...
			if (_modelState.getSearched().equals(propose)) // ... pour verifier l'egalite
			{
				_modelState.setYouWin(true);
			}
			_modelState.setResult(_modelState.getSearched().compareTo(propose)); // elabore la reponse
		}
		if (Pattern.matches("^(-|\\+|=)*$", proposition)) // si la proposition est une reponse sous forme de symboles + - =
		{
		// Verification de la reponse
				if (_modelState.getProposed().isFound(proposition))
				{
					_modelState.setIWin(true);
				} 
				_modelState.setProposed(_modelState.getProposed().search(proposition)); // elabore une nouvelle combinaison proposee
		}
		notifyState();
	}
}
