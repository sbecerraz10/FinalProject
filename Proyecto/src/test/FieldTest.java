package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Field;
import modelo.Gemma;

class FieldTest {
	
	
	private Field field;
	
	public void escenario1() {
		field = new Field("Camp","/images/camp.png");
	}
	

	@Test
	public void testField() {
		escenario1();
		assertNotNull(field);
	}

	@Test
	public void testGenerateTraps() {
		fail("Not yet implemented");
	}

	@Test
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
	public void testAddGemma() {
		int n = 10;
		field.addGemma(n);
		//int size = field.getRootGemma()
		//assertEquals();
	}

	@Test
	public void testShowList() {
		fail("Not yet implemented");
	}

}
