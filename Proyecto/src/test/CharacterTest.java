package test;
import modelo.Character;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * CharacterTest Class
 * @author Sebastian Becerra, Cristian Sierra, Juan Camilo Vargas
 * @version Nov-25-2018
 */
class CharacterTest {

	
	//Relationship 
	private Character character;
	
	
	/**
	 * Create a new Character
	 */
	private void escenario1() {
		character = new Character(10,10,"Thor", "images/thor.png");
	}
	
	/**
	 *This method create two characters and create a connection between them
	 */
	private void escenario2() {
		character = new Character(10,10,"Thor", "images/thor.png");
		Character next = new Character(10,10,"Hulk", "images/thor.png");
		character.setNext(next);
		next.setPrevious(character);
	}
	
	
	@Test
	/**
	 * This method proves the constructor
	 */
	public void testCharacter() {
		escenario1();
		assertNotNull(character);
	}

	@Test
	/**
	 * This method proves that the character is able to move left
	 */
	public void testMoveLeft() {
		escenario1();
		character.setLeft(true);
		character.setRight(false);
		character.setUp(false);
		character.setDown(false);
		character.move();
		int x = 336;
		assertEquals(x,character.getPosx());
	}
	
	@Test
	/**
	 * This method proves that the character is able to move right
	 */
	public void testMoveRight() {
		escenario1();
		character.setRight(true);
		character.setLeft(false);
		character.setUp(false);
		character.setDown(false);
		character.move();
		int x = 356;
		assertEquals(x,character.getPosx());
	}
	
	@Test
	/**
	 * This method proves that the character is able to move up
	 */
	public void testMoveUp() {
		escenario1();
		character.setUp(true);
		character.setRight(false);
		character.setLeft(false);
		character.setDown(false);
		character.move();
		int y = 233;
		assertEquals(y,character.getPosy());
	}
	
	@Test
	/**
	 * This method proves that the character is able to move down
	 */
	public void testMoveDown() {
		escenario1();
		character.setDown(true);
		character.setUp(false);
		character.setRight(false);
		character.setLeft(false);
		character.move();
		int y = 253;
		assertEquals(y,character.getPosy());
	}

	@Test
	/**
	 * This method prove if that a given position is in the character area
	 * It expect true
	 */
	public void testTakeTrue() {
		escenario1();
		int a = 356;
		int b = 253;
		assertTrue(character.take(a, b));
	}
	@Test
	/**
	 * This method prove if that a given position is in the character area
	 * It expect false
	 */
	public void testTakeFalse() {
		escenario1();
		int a = 336;
		int b = 223;
		assertFalse(character.take(a, b));
	}

	@Test
	/**
	 * It expect that given a character name, the method in test could find it and return it
	 */
	public void testSearchCharacter() {
		escenario2();
		Character temp = character.searchCharacter("Hulk");
		assertEquals(character.getNext(),temp);
	}
	
	

}
