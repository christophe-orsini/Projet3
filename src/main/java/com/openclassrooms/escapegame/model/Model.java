package com.openclassrooms.escapegame.model;

import java.util.Observable;

import com.openclassrooms.escapegame.utils.AppConfig;

/**
 * Classe abstraite de gestion des modèles
 * @author C.ORSINI
 *
 */
public abstract class Model extends Observable
{
	protected ModelState _modelState;
	
	// *************************************************************** constructors
	/**
	 * Constructeur par defaut créant un ModelState
	 */
	public Model()
	{
		_modelState = new ModelState();
	}
	
	// *************************************************************** methods
	/**
	 * Notifie aux observeurs l'état du modèle en poussant un ModelState
	 */
	public void notifyState() {
		setChanged();
		notifyObservers(new ModelState(_modelState.getSearched(), _modelState.getProposed(), _modelState.getResult(), _modelState.isIWin(), _modelState.isYouWin()));
	}
	/**
	 * Change la combinaison pour pouvoir rejouer et appelle {@link #notifyState()}
	 */
	public void changeCombinaison() {
		_modelState.setSearched(new Combinaison(AppConfig.getInstance().getNbDigits()));
		_modelState.setYouWin(false);
		_modelState.setProposed(new Combinaison(AppConfig.getInstance().getNbDigits()));
		_modelState.setIWin(false);
		notifyState();
	}
	/**
	 * Traite une entrée de la vue transmise par le controleur
	 * 
	 * @param entry String : l'entrée à traiter
	 */
	public abstract void manageEntry(String entry);
	
	// ***************************************************************** getters
	@SuppressWarnings("javadoc")
	public Combinaison getSearched() {
		return _modelState.getSearched();
	}
	@SuppressWarnings("javadoc")
	public Combinaison getProposed() {
		return _modelState.getProposed();
	}
	@SuppressWarnings("javadoc")
	public String getResult() {
		return _modelState.getResult();
	}
	@SuppressWarnings("javadoc")
	public boolean isIWin() {
		return _modelState.isIWin();
	}
	@SuppressWarnings("javadoc")
	public boolean isYouWin() {
		return _modelState.isYouWin();
	}
	// **************************************************************** setters
	@SuppressWarnings("javadoc")
	public void setSearched(Combinaison searched)
	{
		_modelState.setSearched(searched);
	}
	@SuppressWarnings("javadoc")
	public void setProposed(Combinaison proposed)
	{
		_modelState.setProposed(proposed);
	}
	@SuppressWarnings("javadoc")
	public void setResult(String result)
	{
		_modelState.setResult(result);
	}
	@SuppressWarnings("javadoc")
	public void setIWin(boolean iWin)
	{
		_modelState.setIWin(iWin);
	}
	@SuppressWarnings("javadoc")
	public void setYouWin(boolean youWin)
	{
		_modelState.setYouWin(youWin);
	}
}
