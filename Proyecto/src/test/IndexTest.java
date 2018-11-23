package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;	

import org.junit.jupiter.api.Test;

import exception.CharacterNotChoosen;
import exception.FieldNotChoosen;
import exception.NicknameNotValid;
import modelo.Index;
import modelo.User;
import modelo.Character;
import modelo.Field;

class IndexTest {

	private Index index;
	
	
	private IndexTest() {
		
	}
	
	
	void escenario1() {
		index = new Index();
	}
	
	private void escenario2() throws NicknameNotValid {
		escenario1();
		index.registrerUser("sebb");
		index.registrerUser("Ana");
		index.registrerUser("Cristian");
	}
	
	private void escenario3() {
	
	}
	
	@Test
	void testIndex() {
		escenario1();
		assertNotNull(index.getHeadCharacter());
	}

	@Test
	void testRegistrerUserNotException() {
		escenario1();
		String newName = "Sebb";
		
		try {
			index.registrerUser(newName);
			assert(true);
		} catch (NicknameNotValid e) {
			fail("No esperaba excepcion");
		}
	}
	
	@Test
	void testRegistrerUserExcpetion() {
		escenario1();
		String newName = "Se";
		
		try {
			index.registrerUser(newName);
			fail("Se esperaba excepcion");
		} catch (NicknameNotValid e) {
			assert(true);
		}
	}

	@Test
	void testOrdenarUserName() {
//		try {
//			escenario2();
//		} catch (NicknameNotValid e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		assertEquals("Ana",index.ordenarUserName().get(0).getName());
//		
	}

	@Test
	void testWriteUsers() {
		fail("Not yet implemented");
	}

	@Test
	void testLoadCharacters() {
		fail("Not yet implemented");
	}

	@Test
	void testLoadFields() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveField() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveCharacters() {
		fail("Not yet implemented");
	}

	@Test
	void testCircularListCharacter() {
		escenario1();
		if(index.getCharacterChoose().getPrevious()!=null) {
			assert(true);
		}else {
			fail("No se esperaba esto");
		}
	}

	@Test
	void testCircularListField() {
		escenario1();
		if(index.getFieldChoose().getPrevious()!=null) {
			assert(true);
		}else {
			fail("No se esperaba esto");
		}
	}

	@Test
	void testShowNextCharacter() {
		escenario1();
		Character temp = index.getCharacterChoose().getNext();
		index.showNextCharacter();
		assertEquals(temp,index.getCharacterChoose());
	}

	@Test
	void testShowPreviousCharacter() {
		escenario1();
		Character temp = index.getCharacterChoose().getPrevious();
		index.showPreviousCharacter();
		assertEquals(temp,index.getCharacterChoose());
	}

	@Test
	void testShowNextField() {
		escenario1();
		Field temp = index.getFieldChoose().getNext();
		index.showNextField();
		assertEquals(temp,index.getFieldChoose());	
	}

	@Test
	void testShowPreviousField() {
		escenario1();
		Field temp = index.getFieldChoose().getPrevious();
		index.showPreviousField();
		assertEquals(temp,index.getFieldChoose());		
	}

	@Test
	void testElectedCharacterNotException() {
		escenario1();
		boolean elected = true;
		try {
			index.electedCharacter(elected);
			assert(true);
		} catch (CharacterNotChoosen e) {
			fail("No se esperaba excepcion");
		}
		
	}
	
	@Test
	void testElectedCharacterException() {
		escenario1();
		boolean elected = false;
		try {
			index.electedCharacter(elected);
			fail("Se esperaba excepcion");
		} catch (CharacterNotChoosen e) {
			assert(true);
		}		
	}

	@Test
	void testElectedFieldNotException() {
		escenario1();
		boolean elected = true;
		try {
			index.electedField(elected);
			assert(true);
		} catch (FieldNotChoosen e) {
			fail("No se esperaba excepcion");
		}				
	}
	
	@Test
	void testElectedFieldException() {
		escenario1();
		boolean elected = false;
		try {
			index.electedField(elected);
			fail("Se esperaba excepcion");
		} catch (FieldNotChoosen e) {
			assert(true);
		}						
	}

	@Test
	void testSerializarUsers() {
		fail("Not yet implemented");
	}

	@Test
	void testRecuperarUsers() {
		fail("Not yet implemented");
	}

}
