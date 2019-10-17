package com.openclassrooms.escapegame.model;

/**
 * Classe de l'état du modèle transmise en push par le modèle à ses observeurs
 * 
 * @author C.ORSINI
 *
 */
public class ModelState
{
	protected Combinaison _searched; // Combinaison rechechee
	protected Combinaison _proposed; // Combinaison proposee
	protected String _result; // Symboles du resultat + - =
	protected boolean _iWin; // flag de victoire de l'ordinateur
	protected boolean _youWin; // flag victoire du joueur
	
	// ********************************************************** constructors
	/**
	 * Constructeur par defaut
	 */
	public ModelState()	{}
	/**
	 * Constructeur de la classe
	 * 
	 * @param searched Combinaison : Combinaison recherchée par le joueur
	 * @param proposed Combinaison : Combinaison rechercée par l'ordinateur
	 * @param result String : Résultat d'une comparaison de combinaisons sous forme de symboles +, - ou =
	 * @param iWin boolean : true si l'ordinateur a gagné false sinon
	 * @param youWin boolean : true si le joueur a gagné false sinon
	 */
	public ModelState(Combinaison searched, Combinaison proposed, String result, boolean iWin, boolean youWin)
	{
		_searched = searched;
		_proposed = proposed;
		_result = result;
		_iWin = iWin;
		_youWin = youWin;
	}

	// ********************************************************** getters
	@SuppressWarnings("javadoc")
	public Combinaison getSearched() {
		return _searched;
	}
	@SuppressWarnings("javadoc")
	public Combinaison getProposed() {
		return _proposed;
	}
	@SuppressWarnings("javadoc")
	public String getResult() {
		return _result;
	}
	@SuppressWarnings("javadoc")
	public boolean isIWin() {
		return _iWin;
	}
	@SuppressWarnings("javadoc")
	public boolean isYouWin() {
		return _youWin;
	}
	// ************************************************************ setters
	@SuppressWarnings("javadoc")
	public void setSearched(Combinaison searched) {
		_searched = searched;
	}
	@SuppressWarnings("javadoc")
	public void setProposed(Combinaison proposed) {
		_proposed = proposed;
	}
	@SuppressWarnings("javadoc")
	public void setResult(String result) {
		_result = result;
	}
	@SuppressWarnings("javadoc")
	public void setIWin(boolean iWin) {
		_iWin = iWin;
	}
	@SuppressWarnings("javadoc")
	public void setYouWin(boolean youWin) {
		_youWin = youWin;
	}
}
