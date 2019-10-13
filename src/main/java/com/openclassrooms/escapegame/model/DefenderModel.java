package com.openclassrooms.escapegame.model;

import com.openclassrooms.escapegame.utils.*;

/**
 * Modèle du mode défenseur qui gère le jeu en collaboration avec Combinaison
 * @author C.ORSINI
 *
 */
public class DefenderModel extends Model
{
	// ******************************************************* constructors
	/**
	 * Constructeur créant une combinaison à trouver
	 */
	public DefenderModel()
	{
		AppLog.getLogger().info("Mode défenseur");
		AppLog.getLogger().debug("Création de la combinaison aléatoire de longueur " + AppConfig.getInstance().getNbDigits());
		_searched = new Combinaison(AppConfig.getInstance().getNbDigits());
		AppLog.getLogger().info("Combinaison à trouver : " + _searched);
		setChanged();
		notifyObservers(new ModelState(_searched, _proposed, _result, _win));
	}
	// ******************************************************* methods
	/**
	 * Vérifie si la combinaison proposée correspond à la combianaison à trouver
	 * @param proposition String : La combinaison proposée
	 */
	public void manageEntry(String proposition)
	{
		Combinaison propose = new Combinaison(proposition); // Transforme la proposition String en Combinaison ...
		if (_searched.equals(propose)) // ... pour verifier l'egalite
		{
			_win = true;
		}
		_result = _searched.compareTo(propose); // elabore la reponse
		setChanged();
		notifyObservers(new ModelState(_searched, _proposed, _result, _win));
	}
}
