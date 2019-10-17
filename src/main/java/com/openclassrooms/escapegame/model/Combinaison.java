/**
 * 
 */
package com.openclassrooms.escapegame.model;

import java.util.Random;

/**
 * Classe de gestion des combinaisons
 * 
 * @author C.ORSINI
 */
public class Combinaison
{
	/**
	 * Le nombre maxi de chiffre d'un code <BR />
	 * Par defaut {@value}
	 */
	public static final int MAX_DIGITS = 10; // TODO a supprimer
	
	// Nombre de chiffres du code
	protected int nbDigits;
	// Combinaison de nbDigits chiffres
	protected String combinaison;
	// Combinaison minimale pour la recherche initialise avec des 0
	protected String min;
	// Combinaison maximale pour la recherche initialise avec des 9
	protected String max;
	
	// ****************************************************** constructeurs
	/**
	 * Constructeur avec un nombre de chiffre voulu pour la combinaison <BR />
	 * L'appel de ce constructeur genere une combinaison aleatoire de 'nbDigits' chiffres
	 * 
	 * @param nbDigits int : nombre de chiffres de la combinaison > 0 et <= MAX_DIGITS
	 */
	public Combinaison(int nbDigits)
	{
		this.nbDigits = nbDigits;
		
		// controle le depassement de nbDigits
		if (nbDigits < 1 )
		{
			this.nbDigits = 1;
		}
		if (nbDigits > MAX_DIGITS)
		{
			this.nbDigits = MAX_DIGITS;
		}
		
		// Initialisation de min avec des 0 et max avec des 9
		initMinMax();
		
		// generation de la combinaison aleatoire
		generateCombinaison();
	}
	/**
	 * Constructeur par defaut
	 */
	public Combinaison()
	{
		this(MAX_DIGITS);
	}
	/**
	 * Constructeur avec une combinaison <BR />
	 * La combinaison sera reduite a MAX_DIGITS si elle est plus longue
	 * 
	 * @param combinaison String : la combinaison
	 */
	public Combinaison(String combinaison)
	{
		// verification de la taille maxi de la combinaison
		if (combinaison.length() > MAX_DIGITS)
		{
			this.combinaison = combinaison.substring(0, MAX_DIGITS);
		} 
		else
		{
			this.combinaison = combinaison;
		}
		nbDigits = this.combinaison.length();
		initMinMax();
	}
	
	// ********************************************************** getters
	@SuppressWarnings("javadoc")
	public int getNbDigits()
	{
		return nbDigits;
	}
	@SuppressWarnings("javadoc")
	public String getCombinaison()
	{
		return combinaison;
	}
	
	// ********************************************************* methodes
	/**
	 * Initialisation des combinaisons min (0) et max (9)
	 */
	private void initMinMax()
	{
		char[] newMin = new char[nbDigits];
		char[] newMax = new char[nbDigits];
		
		for (int i= 0; i < nbDigits; i++)
		{
			newMin[i] = '0';
			newMax[i] = '9';
		}
		min = new String(newMin);
		max = new String(newMax);
	}
	/**
	 * Generation aléatoire d'une combinaison de 'nbDigits' chiffres
	 */
	private void generateCombinaison()
	{
		String myCombinaison = "";
			
		for (int i= 0; i < nbDigits; i++)
		{
			float digit = new Random().nextFloat() * 10;
			myCombinaison += String.valueOf((int)digit);
		}
		combinaison = myCombinaison;
	}
	/**
	 * Compare a une autre combinaison et renvoi un code pour le resultat<BR />
	 * Pour chaque digit la methode renvoi + si le chiffre recherche et plus grand<BR />
	 * - s'il est plus petit ou = si c'est le meme
	 * 
	 * @param other Combinaison : l'autre combinaison
	 * @return String : un code sous forme "+-=" avec 1 symbole par digit
	 */
	public String compareTo(Combinaison other)
	{
		if (other.getNbDigits() != nbDigits)
		{
			return "! Erreur !";
		}
		
		String result = "";
		for (int i = 0; i < nbDigits; i++)
		{
			result += compareDigit(combinaison.charAt(i), other.getCombinaison().charAt(i));
		}
		return result;
	}
	/**
	 * Compare 2 chars et renvoi un resultat sous forme de code + - = <BR />
	 * Renvoi 	= si les 2 chars sont egaux<BR />
	 * 			+ si a > b<BR />
	 * 			- si a < b
	 * 
	 * @param a char : le premier char
	 * @param b char : le deuxieme char
	 * @return String : le resultat sous forme de code +, - ou  =
	 */
	private String compareDigit(char a, char b)
	{
		if (a > b)
		{
			return "+";
		}
		if (a < b)
		{
			return "-";
		}
		return "=";
	}
	/**
	 * Elabore une nouvelle combinaison en fonction dune chaine de + - ou =
	 * 
	 * @param code String : Chaine de symbole<BR />
	 * 			= si le chifre est bon<BR />
	 * 			+ si le chiffre a deviner est plus grand<BR />
	 * 			- si le chiffre a deviner est plus petit
	 * @return Combinaison : une nouvelle proposition de combinaison
	 */
	public Combinaison search(String code)
	{
		if (code.length() != nbDigits)
		{
			return this;
		}
		
		char[] newCombinaison = combinaison.toCharArray();
		char[] newMin = min.toCharArray();
		char[] newMax = max.toCharArray();
		
		for (int i=0; i<nbDigits; i++)
		{
			switch (code.charAt(i))
			{
			case '-':
				// (max - min) / 2 + min
				newMax[i] = (char)(Character.getNumericValue(newCombinaison[i]) - 1 + '0');
				newCombinaison[i] = (char)(mean(newMin[i], newMax[i]) + Character.getNumericValue(newMin[i]) + '0');
				break;
			case '+':
				// (max - min) / 2 + min
				newMin[i] = (char)(Character.getNumericValue(newCombinaison[i]) + 1 + '0');
				newCombinaison[i] = (char)(mean(newMin[i], newMax[i]) + Character.getNumericValue(newMin[i]) + '0');
				break;
			default:
				newMin[i] = newMax[i] = newCombinaison[i];
			}
		}
		combinaison = new String(newCombinaison);
		min = new String(newMin);
		max = new String(newMax);
		
		return this;
	}
	/**
	 * Verifie si la combinaison est trouve avec un code ne comprenant que des =
	 * 
	 * @param code String : Chaine de symbole<BR />
	 * 			= si le chifre est bon<BR />
	 * 			+ si le chiffre a deviner est plus grand<BR />
	 * 			- si le chiffre a deviner est plus petit
	 * @return boolean : true sie le code est de la bonne longueur et ne comprend que des =
	 */
	public boolean isFound(String code)
	{
		if (code.length() != nbDigits)
		{
			return false;
		}
		for (int i = 0, c = code.length(); i < c; i++)
		{
			if (code.charAt(i) != '=')
			{
				return false;
			}
		}
		return true;
	}
	@Override
	public String toString()
	{
		return combinaison;
	}
	@Override
	public boolean equals(Object other)
	{
		if (((Combinaison)other).getNbDigits() != nbDigits)
		{
			return false;
		}
		if (!combinaison.equals(((Combinaison)other).getCombinaison()))
		{
			return false;
		}
		return true;
	}
	/**
	 * Calcule le milieu de l'intervalle
	 * @param min char : valeur mini
	 * @param max char : valeur maxi
	 * @return int : milieur de l'intervalle
	 */
	private int mean(char min, char max)
	{
		return (Character.getNumericValue(max) - Character.getNumericValue(min)) / 2;
	}
}
