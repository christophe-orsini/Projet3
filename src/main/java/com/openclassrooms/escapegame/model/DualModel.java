package com.openclassrooms.escapegame.model;

import java.util.Observable;

import com.openclassrooms.escapegame.utils.AppConfig;
import com.openclassrooms.escapegame.utils.AppLog;

/**
 * Modèle du mode défenseur qui gère le jeu en collaboration avec Combinaison
 * @author C.ORSINI
 *
 */
public class DualModel extends Observable
{
	private Combinaison _searchedCombinaison; // la combinaison recherchee
	private Combinaison _proposedCombinaison; // la proposition de l'ordinateur
	private boolean _iWin; // flag pour la victoire de l'ordinateur
	private boolean _youWin; // flag pour la victoire du joueur
	private String _response; // reponse de l'ordinateur sous forme de symboles + - =
	
	// ******************************************************* constructors
	/**
	 * Constructeur créant une combinaison à trouver
	 */
	public DualModel()
	{
		AppLog.getLogger().info("Mode duel");
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
		return _iWin;
	}
	@SuppressWarnings("javadoc")
	public String getResponse()
	{
		return _response;
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
			_iWin = true;
		}
		_response = _searchedCombinaison.compareTo(propose); // elabore la reponse
		setChanged();
		notifyObservers();
	}
}
