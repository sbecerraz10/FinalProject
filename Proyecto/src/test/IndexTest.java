package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

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

/**
 * IndexTest class
 * @author Sebastian Becerra, Cristian Sierra, Juan Camilo Vargas
 * @version Nov-25-2018
 */


class IndexTest {

	//Attributes
	private Index index;

	/**
	 * Instance of index
	 */
	private void escenario1() {
		index = new Index();
	}
	/**
	 * Register three users 
	 * @throws NicknameNotValid: shouln't throw it
	 * @throws UserAlreadyExists : shouln't throw it 
	 */
	private void escenario2() throws NicknameNotValid, UserAlreadyExists {
		escenario1();
		index.registrerUser("Seeebb");
		index.registrerUser("Aaron");
		index.registrerUser("Wandaaa");
	}
	
	/**
	 * Set HeadCharacter null
	 */
	private void escenario3() {
		escenario1();
		index.setHeadCharacter(null);
	}
	
	
	/**
	 * Set HeadField null
	 */
	private void escenario4() {
		escenario1();
		index.setHeadField(null);
	}
	
	/**
	 * Set Users null
	 */
	private void escenario5() {
		escenario1();
		index.setUsers(null);
	}
	
	/**
	 * It clear existing users and add three new ones
	 */
	private void escenario6() {
		escenario1();
		index.getUsers().clear();
		User temp1 = new User("Camilo",0);
		User temp2 = new User("Sebb",0);
		User temp3 = new User("CrisD",0);		
		index.getUsers().add(temp1);
		index.getUsers().add(temp2);
		index.getUsers().add(temp3);
	}
	
	@Test
	/**
	 * Proves if one object in the constructor is created
	 */
	public void testIndex() {
		escenario1();
		assertNotNull(index.getHeadCharacter());
	}

	@Test
	/**
	 * Proves the register method given a valid user name 
	 */
	public void testRegistrerUserNotException() {
		escenario6();
		String newName = "Seebb";
		
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
	/**
	 * Proves the register method given a existent user name
	 */
	public void testRegistrerUserAlreadyExistException() {
		escenario6();
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
	/**
	 * Proves the register method given a invalid user name
	 */
	public void testRegistrerUserException() {
		escenario6();
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
	/**
	 * Proves that the method sort the user by name
	 */
	public void testOrdenarUserName() {
		try {
			escenario2();
		} catch (NicknameNotValid e) {
			fail("No se esperaba esto");
		} catch (UserAlreadyExists e) {
			fail("No se esperaba esto");			
		}
		index.ordenarUserName();
		assertEquals("Aaron",index.getUsers().get(0).getName());
		
	}

	@Test
	/**
	 * Proves that the method concatenate all the users
	 */
	public void testWriteUsers() {
		escenario6();
		String expected = "Camilo" + "\t" +"0" +","+ 
		"Sebb"+"\t"+"0"+","+
		"CrisD"+"\t"+"0"+","		
	;
		
		assertEquals(expected,index.writeUsers());
	
	}

	@Test
	/**
	 * It should load the characters, head character shouldn't be null 
	 */
	public void testLoadCharacters() {
		escenario3();
		index.loadCharacters();
		assertNotNull(index.getHeadCharacter());
		
	}

	@Test
	/**
	 * It should load the fields, head field shouldn't be null 
	 */
	public void testLoadFields() {
		escenario4();
		index.loadFields();
		assertNotNull(index.getHeadField());
	}


	@Test
	/**
	 * It should save the given character, at the list head 
	 */
	public void testSaveCharactersAtHead() {
		escenario1();
		Character character = new Character(0,0,"Zoo","/images/Zoo.png");
		index.saveCharacters(character, index.getHeadCharacter(),null);
		assertEquals(character,index.getHeadCharacter());
	}

	@Test
	/**
	 * It should save the given character, at the list middle 
	 */
	public void testSaveCharacterAtMiddle() {
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
	/**
	 * It should save the given character, at the list end 
	 */
	public void testSaveCharacterAtEnd() {
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
	/**
	 * It should convert the actual list onto a circular list
	 */
	public void testCircularListCharacter() {
		escenario1();
		if(index.getCharacterChoose().getPrevious()!=null) {
			assert(true);
		}else {
			fail("No se esperaba esto");
		}
	}

	@Test
	/**
	 * It should convert the actual list onto a circular list
	 */
	public void testCircularListField() {
		escenario1();
		if(index.getFieldChoose().getPrevious()!=null) {
			assert(true);
		}else {
			fail("No se esperaba esto");
		}
	}
	
	@Test
	/**
	 * It should test if the user choose attribute isn't null
	 */
	public void testChoosenUser() {
		try {
			escenario2();
		} catch (NicknameNotValid | UserAlreadyExists e1) {
			e1.printStackTrace();
		}
		String data = "Seeebb" + "\t" + "12";
		try {
			index.choosenUser(data);
		} catch (UserDoesNotExist e) {
			fail("No se esperaba esto");
		}
		assertNotNull(index.getUserChoose());

	}

	@Test
	/**
	 * It should verify that the method show effectively the next character 
	 */
	public void testShowNextCharacter() {
		escenario1();
		Character temp = index.getCharacterChoose().getNext();
		index.showNextCharacter();
		assertEquals(temp,index.getCharacterChoose());
	}

	@Test
	/**
	 * It should verify that the method show effectively the previous character 
	 */
	public void testShowPreviousCharacter() {
		escenario1();
		Character temp = index.getCharacterChoose().getPrevious();
		index.showPreviousCharacter();
		assertEquals(temp,index.getCharacterChoose());
	}

	@Test
	/**
	 * It should verify that the method show effectively the next field 
	 */
	public void testShowNextField() {
		escenario1();
		Field temp = index.getFieldChoose().getNext();
		index.showNextField();
		assertEquals(temp,index.getFieldChoose());	
	}

	@Test
	/**
	 * It should verify that the method show effectively the previous field 
	 */
	public void testShowPreviousField() {
		escenario1();
		Field temp = index.getFieldChoose().getPrevious();
		index.showPreviousField();
		assertEquals(temp,index.getFieldChoose());		
	}

	@Test
	/**
	 * The method verify if the character has been elected
	 * Given a true parameter it shouldn't throw exception  
	 */
	public void testElectedCharacterNotException() {
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
	/**
	 * The method verify if the character has been elected
	 * Given a false parameter it should throw exception  
	 */
	public void testElectedCharacterException() {
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
	/**
	 * It verify if the field has been elected
	 * Given a true parameter the method shouldn't throw exception  
	 */
	public void testElectedFieldNotException() {
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
	/**
	 * It verify if the field has been elected
	 * Given a false parameter the method should throw exception  
	 */
	public void testElectedFieldException() {
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
	/**
	 * It verify if the character to search is existent
	 * Given character as a parameter the method shouldn't throw exception
	 */
	public void testSearchCharacterExistent() {
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
	/**
	 * It verify if the character to search is existent
	 * Given character as a parameter the method should throw exception
	 */
	public void testSearchCharacterNotExistent() {
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
	/**
	 * It verify if the user to search is existent
	 * Given user as a parameter the method shouldn't throw exception
	 */
	public void testSearchUserExistent() {
		escenario6();
		String nickName = "Camilo";
		try {
			User user = index.searchUser(nickName);
			assertEquals(user.getName(),nickName);
		} catch (UserDoesNotExist e) {
			fail("No se esperaba excepcion");
		}
		
	}
	
	
	@Test
	/**
	 * It verify if the user to search is existent
	 * Given user as a parameter the method should throw exception
	 */
	public void testSearchUserNotExistent() {
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
	/**
	 * It validate if the given field has been saved at list head
	 */
	public void testSaveFieldAtHead() {
		escenario1();
		Field field = new Field("Zoo","/images/zoo.png");
		index.saveField(field,index.getHeadField(),null);
		assertEquals(field,index.getHeadField());
	}
	
	@Test
	/**
	 * It validate if the given field has been saved at list middle
	 */
	public void testSaveFieldAtMiddle() {
		escenario1();
		Field field = new Field("Loo","/images/loo.png");
		index.saveField(field,index.getHeadField(),null);
		if(index.searchField("Loo") != null) {
			assert(true);
		}else {
			fail("No esperaba esto");
		}
	}
	
	
	@Test
	/**
	 * It validate if the given field has been saved at list end
	 */
	public void testSaveFieldAtEnd() {
		escenario1();
		Field field = new Field("Aoo","/images/loo.png");
		index.saveField(field,index.getHeadField(),null);
		if(index.searchField("Aoo") != null) {
			assert(true);
		}else {
			fail("No esperaba esto");
		}
	}
	
	

	@Test
	/**
	 * It validate if the users have been serialized
	 */
	public void testSerializarUsers() {
		escenario1();
		try {
			index.serializarUsers();
			assert(true);
			//If the method does not throw exception
		} catch (IOException e) {
			fail("No esperaba esto");
		}
	}

	@Test
	/**
	 *It validate if the users have been loaded successfully 
	 */
	public void testRecuperarUsers() {
		escenario5();
		assertNotNull(index.recuperarUsers());
	}

}
