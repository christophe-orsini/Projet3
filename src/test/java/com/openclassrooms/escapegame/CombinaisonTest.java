package com.openclassrooms.escapegame;

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
	@Test
	public void testDigits()
	{
		Combinaison combinaison = new Combinaison(3);
		assertEquals("Test " + combinaison.getCombinaison() + " a 3 digits", 3, combinaison.getNbDigits());
		combinaison = new Combinaison("1234");
		assertEquals("Test 1234 a 4 digits", 4, combinaison.getNbDigits());
		combinaison = new Combinaison(new String(new char[Combinaison.MAX_DIGITS+1]).replace('\u0000', '1'));
		assertEquals("Combinaison max digit = "+ Combinaison.MAX_DIGITS, Combinaison.MAX_DIGITS, combinaison.getNbDigits());
		combinaison = new Combinaison();
		assertEquals("Test constructeur par defaut", Combinaison.MAX_DIGITS, combinaison.getNbDigits());
	}
	
	@Test
	public void testCombinaison()
	{
		Combinaison combinaison = new Combinaison("1234");
		assertEquals("Combinaison = 1234", "1234", combinaison.getCombinaison());
		assertEquals("Combinaison equals 1234", true, combinaison.equals(new Combinaison("1234")));
		assertEquals("Combinaison 1234 <> 2241 => -=-+", "-=-+", combinaison.compareTo(new Combinaison("2241")));
	}
	
	@Test
	public void testFindCombinaison()
	{
		Combinaison combinaison = new Combinaison("147");
		Combinaison proposition = new Combinaison("444");
		assertEquals("Recherche pour 444 -> -=+", combinaison, proposition.search("-=+"));
		combinaison = new Combinaison("048");
		proposition = new Combinaison("147");
		assertEquals("Recherche pour 147 -> -=+", combinaison, proposition.search("-=+"));
		assertTrue("Combinaison trouvée", combinaison.isFound("==="));
		assertFalse("Combinaison pas trouvée", combinaison.isFound("=-+"));
	}
}