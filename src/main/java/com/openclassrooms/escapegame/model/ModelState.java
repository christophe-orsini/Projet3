package com.openclassrooms.escapegame.model;

/**
 * Classe de l'état du modèle transmise en push par le modèle
 * @author C.ORSINI
 *
 */
public class ModelState
{
	protected Combinaison _searched; // Combinaison rechechee
	protected Combinaison _proposed; // Combinaison proposee
	protected String _result; // Symboles du resultat + - =
	protected boolean _win; // flag de victoire
	
	public ModelState(Combinaison searched, Combinaison proposed, String result, boolean win)
	{
		_searched = searched;
		_proposed = proposed;
		_result = result;
		_win = win;
	}

	public Combinaison getSearched() {
		return _searched;
	}

	public Combinaison getProposed() {
		return _proposed;
	}

	public String getResult() {
		return _result;
	}

	public boolean isWin() {
		return _win;
	}
}
