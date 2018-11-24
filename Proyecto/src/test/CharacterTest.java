package test;
import modelo.Character;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CharacterTest {

	private Character character;
	
	private void escenario1() {
		character = new Character(10,10,"Thor", "images/thor.png");
	}
	
	private void escenario2() {
		character = new Character(10,10,"Thor", "images/thor.png");
		Character next = new Character(10,10,"Hulk", "images/thor.png");
		character.setNext(next);
		next.setPrevious(character);
	}
	
	
	@Test
	void testCharacter() {
		escenario1();
		assertNotNull(character);
	}

	@Test
	void testMoveLeft() {
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
	void testMoveRight() {
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
	void testMoveUp() {
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
	void testMoveDown() {
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
	void testTakeTrue() {
		escenario1();
		int a = 356;
		int b = 253;
		assertTrue(character.take(a, b));
	}
	@Test
	void testTakeFalse() {
		escenario1();
		int a = 336;
		int b = 223;
		assertFalse(character.take(a, b));
	}

	@Test
	void testSearchCharacter() {
		escenario2();
		Character temp = character.searchCharacter("Hulk");
		assertEquals(character.getNext(),temp);
	}

}
