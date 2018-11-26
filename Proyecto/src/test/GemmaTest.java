package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.Gemma;

/**
 * 
 * @author Sebastian Becerra, Cristian Sierra, Juan Camilo Vargas
 * @version Nov-25-2018
 */

class GemmaTest {

	
	//Relationship
	private Gemma gemma;
	
	
	/**
	 * Create a new Gemma
	 */
	private void escenario1() {
		gemma = new Gemma(10);
	}

	/**
	 * Add 4 gem to the binary Tree
	 */
	private void escenario2() {
		escenario1();
		gemma.add(new Gemma(12));
		gemma.add(new Gemma(1));
		gemma.add(new Gemma(4));
		gemma.add(new Gemma(3));
	}
	
	@Test
	/**
	 * It should save the two gem in the binary tree
	 */
	public void testAddTwoGemma() {
		escenario1();
		gemma.add(new Gemma(9));
		gemma.add(new Gemma(12));
		assertEquals(3,gemma.getWeight());
	}

	@Test
	/**
	 * It should search the given gem
	 * Should be the same value of gem's power
	 */
	public void testSearchGemma() {
		escenario2();
		assertEquals(4,gemma.searchGemma(4).getPower());
	}

	@Test
	/**
	 * It should give the number of gem in the binary tree
	 */
	public void testGetWeight() {
		escenario2();
		int weight = 5;
		assertEquals(weight,gemma.getWeight());
	}

	@Test
	/**
	 * It should put the gems in the list successfully
	 */
	public void testShowInList() {
		escenario2();
		ArrayList<Gemma> gemitas = new ArrayList<Gemma>();
		gemma.showInList(gemitas);
		assertEquals(gemma.getWeight(),gemitas.size());
	}

}
