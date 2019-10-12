package com.openclassrooms.escapegame.model;

import java.util.Observable;

import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.utils.AppLog;

/**
 * Modèle du mode dèfenseur qui gère le jeu en collaboration avec Combinaison
 * @author C.ORSINI
 *
 */
public class DefenderModel extends Observable
{
	private Combinaison _searchedCombinaison;
	private boolean _win;
	private String _reponse;
	
	// ******************************************************* constructors
	/**
	 * Constructeur créant une combinaison à trouver
	 */
	public DefenderModel()
	{
		AppLog.getLogger().info("Mode défenseur");
		AppLog.getLogger().debug("Création de la combinaison aléatoire de longueur " + AppConfig.getInstance().getNbDigits());
		_searchedCombinaison = new Combinaison(AppConfig.getInstance().getNbDigits());
		AppLog.getLogger().info("Combinaison à trouver : " + _searchedCombinaison);
	}
	// ******************************************************* getters/setters
	/**
	 * Retourne la combinaison sous forme de String
	 * @return String : la combinaison
	 */
	public String getSearchedCombinaison() {
		return _searchedCombinaison.toString();
	}
	@SuppressWarnings("javadoc")
	public boolean isWin()
	{
		return _win;
	}
	@SuppressWarnings("javadoc")
	public String getReponse()
	{
		return _reponse;
	}
	// ******************************************************* methods
	/**
	 * Vérifie si la combinaison proposée correspond à la combianaison à trouver
	 * @param proposition String : La combinaison proposée
	 */
	public void verify(String proposition)
	{
		Combinaison propose = new Combinaison(proposition); // Transforme la proposition String en Combinaison ...
		if (_searchedCombinaison.equals(propose)) // ... pour verifier l'egalite
		{
			_win = true;
		}
		_reponse = _searchedCombinaison.compareTo(propose); // elabore la reponse
		setChanged();
		notifyObservers();
	}
}
