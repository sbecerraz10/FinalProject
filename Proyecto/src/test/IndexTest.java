package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;	

import org.junit.jupiter.api.Test;

import exception.CharacterDoesNotExist;
import exception.CharacterNotChoosen;
import exception.FieldNotChoosen;
import exception.NicknameNotValid;
import exception.UserDoesNotExist;
import exception.UserAlreadyExists;
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
	
	private void escenario2() throws NicknameNotValid, UserAlreadyExists {
		escenario1();
		index.registrerUser("sebb");
		index.registrerUser("Ana");
		index.registrerUser("Cristian");
	}
	
	private void escenario3() {
		escenario1();
		index.setHeadCharacter(null);
	}
	
	private void escenario4() {
		escenario1();
		index.setHeadField(null);
	}
	private void escenario5() {
		escenario1();
		index.setUsers(null);
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
		} catch (UserAlreadyExists e) {
			fail("No se esperaba excepcion");
		}
	}
	
	@Test
	void testRegistrerUserAlreadyExistException() {
		escenario1();
		String newName = "Camilo";
		
		try {
			index.registrerUser(newName);
			fail("Se esperaba una excepcion");
		} catch (NicknameNotValid e) {
			fail("No esperaba esta excepcion");
		} catch (UserAlreadyExists e) {
			assert(true);
		}
	}
	
	@Test
	void testRegistrerUserException() {
		escenario1();
		String newName = "Se";
		
		try {
			index.registrerUser(newName);
			fail("Se esperaba excepcion");
		} catch (NicknameNotValid e) {
			assert(true);
		} catch (UserAlreadyExists e) {
			fail("No se esperaba esta excepcion");
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
		fail("Not yet implemented");
	}

	@Test
	void testWriteUsers() {
		fail("Not yet implemented");
	}

	@Test
	void testLoadCharacters() {
		escenario3();
		index.loadCharacters();
		assertNotNull(index.getHeadCharacter());
		
	}

	@Test
	void testLoadFields() {
		escenario4();
		index.loadFields();
		assertNotNull(index.getHeadField());
	}


	@Test
	void testSaveCharactersAtHead() {
		escenario1();
		Character character = new Character(0,0,"Zoo","/images/Zoo.png");
		index.saveCharacters(character, index.getHeadCharacter(),null);
		assertEquals(character,index.getHeadCharacter());
	}

	@Test
	void testSaveCharacterAtMiddle() {
		escenario1();
		Character character = new Character(10, 10,"Loki","/images/loki.png");
		index.saveCharacters(character, index.getHeadCharacter(), null);
		try {
			assertEquals(character,index.searchCharacter("Loki"));
		} catch (CharacterDoesNotExist e) {
			fail("No se esperaba esto");
		}
	}
	
	
	@Test
	void testSaveCharacterAtEnd() {
		escenario1();
		Character character = new Character(10, 10,"Aaron","/images/Aaron.png");
		index.saveCharacters(character, index.getHeadCharacter(), null);
		try {
			assertEquals(character,index.searchCharacter("Aaron"));
		} catch (CharacterDoesNotExist e) {
			fail("No se esperaba esto");
		}
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
	void testChoosenUser() {
		escenario1();
		String data = "Sebb" + "\t" + "12";
		index.choosenUser(data);
		assertNotNull(index.getCharacterChoose());

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
	void testSearchCharacterExistent() {
		escenario1();
		String name = "Thor";
		try {
			Character character = index.searchCharacter(name);
			assertEquals(character.getNickname(),name);
		} catch (CharacterDoesNotExist e) {
			fail("No se esperaba excepcion");
		}
		
	}
	
	@Test
	void testSearchCharacterNotExistent() {
		escenario1();
		String name = "Tho";
		try {
			Character character = index.searchCharacter(name);
			fail("Se esperaba excepcion");
		} catch (CharacterDoesNotExist e) {
			assert(true);
		}
	}
	
	@Test
	void testSearchUserExistent() {
		escenario1();
		String nickName = "Camilo";
		try {
			User user = index.searchUser(nickName);
			assertEquals(user.getName(),nickName);
		} catch (UserDoesNotExist e) {
			fail("No se esperaba excepcion");
		}
		
	}
	
	
	@Test
	void testSearchUserNotExistent() {
		escenario1();
		String nickName = "C";
		try {
			User user = index.searchUser(nickName);
			fail("Se esperaba excepcion");
		} catch (UserDoesNotExist e) {
			assert(true);
		}
	}
	
	@Test
	void testSaveFieldAtHead() {
		escenario1();
		Field field = new Field("Zoo","/images/zoo.png");
		index.saveField(field,index.getHeadField(),null);
		assertEquals(field,index.getHeadField());
	}
	
	@Test
	void testSaveFieldAtMiddle() {
		escenario1();
		Field field = new Field("Loo","/images/loo.png");
		index.saveField(field,index.getHeadField(),null);
		//assertEquals(field,);
		fail("Not yet implemented");
	}
	@Test
	void testSaveFieldAtEnd() {
		escenario1();
		Field field = new Field("Loo","/images/loo.png");
		index.saveField(field,index.getHeadField(),null);
		//assertEquals(field,);
		fail("Not yet implemented");
	}
	
	

//	@Test
//	void testSerializarUsers() {
//		escenario1();
//		index.serializarUsers();
//		//If the method does not throw exception
//		assert(true);
//	}

	@Test
	void testRecuperarUsers() {
		escenario5();
		assertNotNull(index.recuperarUsers());
	}

}
