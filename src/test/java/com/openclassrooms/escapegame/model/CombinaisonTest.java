package com.openclassrooms.escapegame.model;

import static org.junit.Assert.*;
import org.junit.Test;
import com.openclassrooms.escapegame.model.Combinaison;

/**
 * 
 * @author C.ORSINI
 *
 */
public class CombinaisonTest
{
	/**
	 * Teste la construction de combinaison avec les différents constructeurs
	 */
	@Test
	public void constructorsTest()
	{
		Combinaison combinaison;
		
		// par defaut
		combinaison = new Combinaison();
		int expected = Combinaison.MAX_DIGITS;
		int actual = combinaison.getNbDigits();
		assertEquals("Test constructeur par defaut", expected, actual);
		
		// avec un int
		combinaison = new Combinaison(-1);
		expected = 1;
		actual = combinaison.getNbDigits();
		assertEquals("Test constructeur avec -1", expected, actual);
		combinaison = new Combinaison(0);
		expected = 1;
		actual = combinaison.getNbDigits();
		assertEquals("Test constructeur à 0 digit", expected, actual);
		combinaison = new Combinaison(3);
		expected = 3;
		actual = combinaison.getNbDigits();
		assertEquals("Test constructeur à 3 digits", expected, actual);
		combinaison = new Combinaison(Combinaison.MAX_DIGITS + 1);
		expected = Combinaison.MAX_DIGITS;
		actual = combinaison.getNbDigits();
		assertEquals("Test constructeur avec nombre de digits trop grand", expected, actual);
		
		// avec string
		combinaison = new Combinaison("1234");
		expected = 4;
		actual = combinaison.getNbDigits();
		assertEquals("Test constructeur 1234 a 4 digits", expected, actual);
		String expectedString = "1234";
		String actualString = combinaison.getCombinaison();
		assertEquals("Combinaison = 1234", expectedString, actualString);
		
		expected = Combinaison.MAX_DIGITS;
		expectedString = new String(new char[expected]).replace('\u0000', '1');
		combinaison = new Combinaison(expectedString + "1"); // combinaison trop longue de 1 digit
		actual = combinaison.getNbDigits();
		assertEquals("Combinaison max digit", expected, actual);
		actualString = combinaison.getCombinaison();
		assertEquals("Combinaison max digit = "+ expected, expected, actual);
		
		// avec symboles
		combinaison = new Combinaison("=+-=");
		expected = 4;
		actual = combinaison.getNbDigits();
		assertEquals("Test constructeur =+-=", expected, actual);
		expectedString = "=+-=";
		actualString = combinaison.getCombinaison();
		assertEquals("Combinaison =+-=", expectedString, actualString);
	}
	
	@SuppressWarnings("javadoc")
	@Test
	public void getNbDigitsTest()
	{
		// Default constructor
		Combinaison combinaison = new Combinaison();
		int expected = 10;
		int actual = combinaison.getNbDigits();
		assertEquals("Constructeur par défaut", expected, actual);
		
		// Int constructor
		combinaison = new Combinaison(5);
		expected = 5;
		actual = combinaison.getNbDigits();
		assertEquals("Constructeur avec int 5", expected, actual);
		
		// String constructor
		combinaison = new Combinaison("123");
		expected = 3;
		actual = combinaison.getNbDigits();
		assertEquals("Constructeur avec '123'", expected, actual);
	}
	
	@SuppressWarnings("javadoc")
	@Test
	public void compareToTest()
	{
		// egal
		Combinaison c1 = new Combinaison("123");
		Combinaison c2 = new Combinaison("123");
		String expected = "===";
		String actual = c1.compareTo(c2);
		assertEquals("CompareTo equal", expected, actual);
		
		// premier inférieur
		c1 = new Combinaison("123");
		c2 = new Combinaison("223");
		expected = "-==";
		actual = c1.compareTo(c2);
		assertEquals("CompareTo first less", expected, actual);
		
		// milieu superieur
		c1 = new Combinaison("123");
		c2 = new Combinaison("113");
		expected = "=+=";
		actual = c1.compareTo(c2);
		assertEquals("CompareTo first less", expected, actual);
				
		// erreur
		c1 = new Combinaison("123");
		c2 = new Combinaison("1234");
		expected = "! Erreur !";
		actual = c1.compareTo(c2);
		assertEquals("CompareTo Error", expected, actual);
	}
	
	@SuppressWarnings("javadoc")
	@Test
	public void equalsTest()
	{
		// egal
		Combinaison c1 = new Combinaison("1234");
		Combinaison c2 = new Combinaison("1234");
		boolean expected = true;
		boolean actual = c1.equals(c2);
		assertEquals("Equals", expected, actual);
		
		// pas le meme nbre de digits
		c1 = new Combinaison("123");
		c2 = new Combinaison("1234");
		expected = false;
		actual = c1.equals(c2);
		assertEquals("Equals one shorter than other", expected, actual);
				
		// pas egal
		c1 = new Combinaison("123");
		c2 = new Combinaison("124");
		expected = false;
		actual = c1.equals(c2);
		assertEquals("Equals not same combinaisons", expected, actual);
	}
	
	@SuppressWarnings("javadoc")
	@Test
	public void toStringTest()
	{
		Combinaison c = new Combinaison("123");
		String expected = "123";
		String actual = c.toString();
		assertEquals("toString 123", expected, actual);
	}
	
	@SuppressWarnings("javadoc")
	@Test
	public void isFounfTest()
	{
		// trouvé
		Combinaison c = new Combinaison("123");
		String actual = "===";
		assertTrue("Found", c.isFound(actual));
		
		// pas trouvé
		actual = "=-=";
		assertFalse("Not found", c.isFound(actual));
	}
	
	@SuppressWarnings("javadoc")
	@Test
	public void searchTest()
	{
		Combinaison proposition = new Combinaison("345");
		Combinaison expected = new Combinaison("147");
		String actual ="-=+";
		assertEquals("Recherche pour 345 -> -=+", expected, proposition.search(actual));
		
		proposition = new Combinaison("543");
		expected = new Combinaison("741");
		actual = "+=-";
		assertEquals("Recherche pour 543 -> +=-", expected, proposition.search(actual));
		
		proposition = new Combinaison("9");
		expected = new Combinaison("9");
		actual = "+";
		assertEquals("Recherche pour 0 -> -", expected, proposition.search(actual));
	}
}