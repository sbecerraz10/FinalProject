package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Field;
import modelo.Gemma;

/**
 * FieldTest class
 * @author Sebastian Becerra, Cristian Sierra, Juan Camilo Vargas
 * @version Nov-25-2018
 */

public class FieldTest {
	
	/**
	 * Relationship with the class field
	 */
	private Field field;
	
	
	/**
	 * Create a new Field
	 */
	private void escenario1() {
		field = new Field("Camp","/images/camp.png");
	}
	
	/**
	 * It set empty the traps ArrayList 
	 */
	private void escenario2() {
		escenario1();
		field.getTraps().clear();
	}
	/**
	 * It add a number of gem to the field
	 */
	private void escenario3() {
		escenario1();
		field.addGemma(10);
		field.addGemma(3);
		field.addGemma(5);
	}

	@Test
	/**
	 * This method proves the Field Constructor
	 */
	public void testField() {
		escenario1();
		assertNotNull(field);
	}

	@Test
	/**
	 * This method proves if the attribute Trap isn't empty 
	 */
	public void testGenerateTraps() {
		escenario2();
		field.generateTraps();
		assertNotEquals(0,field.getTraps().size());
	}

	@Test
	/**
	 * This method proves the compareTo method
	 * It expect that the new field will be smaller than the existing field
	 */
	public void testCompareToSmaller() {
		escenario1();
		Field field2 = new Field("Albion","/images/camp.png");
		if(field.compareTo(field2) > 0) {
			assert(true);
		}else {
			fail("No esperaba esto");
		}
	}
	@Test
	/**
	 * This method proves the compareTo method
	 * It expect that the new field will be greater than the existing field
	 */
	public void testCompareToGreater() {
		escenario1();
		Field field2 = new Field("Wanda","/images/wanda.png");
		if(field.compareTo(field2) < 0) {
			assert(true);
		}else {
			fail("No esperaba esto");
		}
	}

	@Test
	/**
	 * This method proves that the gem is successfully added to the binary tree
	 */
	public void testAddGemma() {
		escenario1();
		int power = 10;
		field.addGemma(power);
		Gemma temp = field.searchGemma(power);
		if(temp != null && temp.getPower() == power) {
			assert(true);
		}else{
			fail("No esperaba esto");
		}
	}

	@Test
	/**
	 * It expect that the method in test return the ArrayList with the number of gem previously added
	 */
	public void testShowList() {
		escenario3();
		int expected = 3;
		assertEquals(expected,field.showList().size());
	}

}
