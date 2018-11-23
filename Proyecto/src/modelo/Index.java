package modelo;

import java.io.BufferedReader;	
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import exception.CharacterDoesNotExist;
import exception.CharacterNotChoosen;
import exception.FieldNotChoosen;
import exception.NicknameNotValid;
import exception.PlayerDoesNotExist;

/**
 * Index Class
 * @author Sebastian Becerra Juan Camilo Vargas, Cristian Sierra
 * @version nov-23-2018
 */

public class Index {
	
	
	//Atributos
	private ArrayList<User> users;
	
	private Field headField;
	
	private Character headCharacter;
	
	private Character characterChoose;
	
	private Field fieldChoose;
	
	private User userChoose;
	
	/**
	 *Index Constructor
	 */
	public Index() {
		File file = new File("files/Users.dat");
		if(file.exists() == false) {
			users = new ArrayList<User>();
		}else {
			users = recuperarUsers();
		}	
		loadCharacters();
		loadFields();
		circularListField();
		circularListCharacter();
		characterChoose = headCharacter;
		fieldChoose = headField;
	}
	
	
	/**
	 * Method register user 
	 * Register an user to the collection of users
	 * @param nickname , nickname of the user !=null
	 * @throws NicknameNotValid throws it in case that it contains less of 4 characters.
	 */
	public void registrerUser(String nickname) throws NicknameNotValid {
		if(nickname.length()<4) {
			throw new NicknameNotValid();
		}users.add(new User(nickname,0));		
	}
	
	
	/**
	 * Method ordenarUserName
	 * It sorts the users by name using the insertion method.
	 * @return users - Collection of users
	 */
	public ArrayList<User> ordenarUserName() {	
		int in;
		for (int i = 1 ; i < users.size(); i++) {
			User aux = users.get(i);
			in = i;             
			while (in > 0 && users.get(in - 1).compareTo(aux)>0) {
				users.set(in, users.get(in-1));
				--in;
			} 
			users.set(in, aux);
		}
		return users;
	}
	
	/**
	 * Method writeUsers
	 * Returns a String with all users info.
	 * @return cadena- list of users with their names and score.
	 */
	public String writeUsers() {
		String cadena = "";
		for(int i = 0;i<users.size();i++) {
			cadena += users.get(i).getName()+"\t"+users.get(i).getScore()+",";
		}
		return cadena;
    }
	/**
	 * Method loadCharacters
	 * Load the characters from a text file
	 * -post: list of characters change
	 */
	public void loadCharacters() {
		try {
			FileReader fr = new FileReader("files/Characters.txt");
			BufferedReader br = new BufferedReader(fr);
			String cadena = "";
			while((cadena= br.readLine())!= null) {
				String[] character = cadena.split(";");
				int healt = Integer.parseInt(character[0]);
				int power = Integer.parseInt(character[1]);
				String name = character[2];
				String image = character[3];
				Character ch = new Character(healt,power,name,image);
				saveCharacters(ch,this.headCharacter,null);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Method loadFields
	 * Load the fields from a text file
	 * -post: list of fields change
	 */
	public void loadFields() {
		try {
			FileReader fr = new FileReader("files/Fields.txt");
			BufferedReader br = new BufferedReader(fr);
			String cadena="";
			while((cadena=br.readLine())!=null) {
				String[] field = cadena.split(";");
				String nombre = field[0];
				String image = field[1];
				Field fd = new Field(nombre,image);
				saveField(fd,this.headField,null);
			}
			br.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method saveField
	 * It save a new field to the list
	 * -pre: field != null
	 * @param field: new field to save 
	 * @param actual: actual field of the list
	 * @param previous: previous field of the list
	 */
	public void saveField(Field field, Field actual, Field previous) {
		if(this.headField==null) {
			this.headField = field;
		}else {
			if(actual==null) {
				previous.setNext(field);
			}else {
				if(this.headField.compareTo(field) <= 0) {
					field.setNext(headField);
					headField.setPrevious(field);
					headField = field;
				}
				else if(actual.compareTo(field) <= 0) {
					if(previous!=null)previous.setNext(field);
					actual.setPrevious(field);
					field.setNext(actual);
					field.setPrevious(previous);
				}else {
					previous = actual;
					actual = actual.getNext();
					saveField(field,actual,previous);
				}
			}		
					
		}
	}
	
	
	/**
	 * Method saveCharacters
	 * It save a new Character to the list
	 * @param character: new character to save
	 * @param actual: actual character
	 * @param previous: previous character
	 */
	public void saveCharacters(Character character, Character actual, Character previous) {
		if(this.headCharacter==null) {
			this.headCharacter = character;
		}else {
			if(this.headCharacter.compareTo(character) <= 0) {
				character.setNext(headCharacter);
				headCharacter.setPrevious(character);
				headCharacter = character;
			}
			else if(actual.compareTo(character) <= 0) {
				if(previous!=null)previous.setNext(character);
				actual.setPrevious(character);
				character.setNext(actual);
				character.setPrevious(previous);
			}else {
				previous = actual;
				actual = actual.getNext();
				saveCharacters(character,actual,previous);
			}
		}
	}
	/**
	 * Method circularListCharacter
	 * Convert the list of characters into a circular list
	 */
	public void circularListCharacter() {
		Character actual = headCharacter;
		Character previous = null;
		while(actual!= null) {
			previous = actual;
			actual = actual.getNext();
		}
		previous.setNext(headCharacter);
		headCharacter.setPrevious(previous);
	}
	/**
	 * Method circularListField
	 * Convert the list of fields into a circular list
	 */
	public void circularListField() {
		Field actual = headField;
		Field previous = null;
		while(actual!= null) {
			previous = actual;
			actual = actual.getNext();
		}
		previous.setNext(headField);
		headField.setPrevious(previous);
	}
	/**
	 * Method showNextCharacter
	 * Receives a character and return the next 
	 * pre: actual!=null
	 * @param actual: character that belongs to the circular list of characters 
	 * @return: next character of actual character
	 */
	public Character showNextCharacter(Character actual) {
		return actual.getNext();
	}
	
	
	/**
	 * Method showNextField
	 * Receives a field and return the next 
	 * pre: actual!=null
	 * @param actual: field that belongs to the circular list of fields 
	 * @return: next field of actual field
	 */
	public Field showNextField(Field actual) {
		return actual.getNext();
	}
	/**
	 * Method showPreviousCharacter
	 * Receives a character and return the previous one 
	 * pre: actual!=null
	 * @param actual : character that belongs to the circular list of characters
	 * @return: previous character of the actual one
	 */
	public Character showPreviousCharacter(Character actual) {
		return actual.getPrevious();
	}
	
	
	/**
	 * Method showPreviousField
	 * Receives a field and return the previous one
	 * pre: actual!=null
	 * @param actual : field that belongs to the the circular list of fields
	 * @return : previous field of the actual one
	 */
	public Field showPreviosField(Field actual) {
		return actual.getPrevious();
	}
	
	public void showNextCharacter() {
		this.characterChoose = this.characterChoose.getNext();
	}
	
	public void showPreviousCharacter() {
		this.characterChoose = this.characterChoose.getPrevious();
	}
	
	public void showNextField() {
		this.fieldChoose = this.fieldChoose.getNext();
	}
	
	public void showPreviousField() {
		this.fieldChoose = this.fieldChoose.getPrevious();
	}
	/**
	 * Method electedCharacter
	 * @param elected: boolean that validate if the character has been chosen or not
	 * @throws CharacterNotChoosen: throws if elected is false
	 */
	public void electedCharacter(boolean elected) throws CharacterNotChoosen {
		if(elected == false) {
			throw new CharacterNotChoosen();
		}
	}
	/**
	 * Method electedField
	 * @param elected:boolean that validate if the field has been chosen or not
	 * @throws FieldNotChoosen: throws it if elected is false
	 */
	public void electedField(boolean elected) throws FieldNotChoosen {
		if(elected == false) {
			throw new FieldNotChoosen();
		}
	}
	
	
	/**
	 * Method serializarUsers
	 * Serialize the collection of users
	 */
	public void serializarUsers()  {
		try {
			File file = new File("files/Users.dat");
			if(file.exists()== false) {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(users);
				oos.close();	
			}else {
				FileWriter fl = new FileWriter(file);
				BufferedWriter bf = new BufferedWriter(fl);
				bf.write("");
				bf.close();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(users);
				oos.close();	
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method recuperarUsers
	 * Load the list of users
	 * @return the list of users
	 */
	public ArrayList<User> recuperarUsers() {
		ArrayList<User> clon = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("files/Users.dat"));
			clon = (ArrayList<User>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clon;
	}
	
//	public void chooseCharacter(String id) throws CharacterNotChoosen {
//		if(headCharacter != null) {
//			if(headCharacter.getImage().equals(id)) {
//				this.setCharacterChoose(headCharacter);
//			}else {
//				if(this.headCharacter.getNext()!=null)
//					this.setCharacterChoose(headCharacter.searchCharacter(id));
//			}
//		}else {
//			throw new CharacterNotChoosen();
//		}
//	}
//	
//	public void chooseField(String id) throws FieldNotChoosen {
//		if(headField != null) {
//			if(headField.getImage().equals(id)) {
//				this.setFieldChoose(headField);
//			}else {
//				if(this.headField.getNext()!=null)
//					this.setFieldChoose(headField.searchField(id));
//			}
//		}else {
//			throw new FieldNotChoosen();
//		}
//	}
	/**
	 * Method getCharacterChoose
	 * @return characterChoose- actual character
	 */
	public Character getCharacterChoose() {
		return characterChoose;
	}

	/**
	 * 
	 * @param characterChoose
	 */
	public void setCharacterChoose(Character characterChoose) {
		this.characterChoose = characterChoose;
	}

	
	/**
	 * 
	 * @return
	 */
	public Field getFieldChoose() {
		return fieldChoose;
	}

	/**
	 * 
	 * @param fieldChoose
	 */
	public void setFieldChoose(Field fieldChoose) {
		this.fieldChoose = fieldChoose;
	}
	
	public void noCircular(){
		headCharacter.getPrevious().setNext(null);
		headCharacter.setPrevious(null);
	}
	
	public Character searchCharacter(String nombre) throws CharacterDoesNotExist {
		noCircular();
		Character ch = null;
		if(headCharacter != null) {
			ch = headCharacter.searchCharacter(nombre);
		}else{
			ch = null;
		}
		circularListCharacter();
		if(ch == null) {
			throw new CharacterDoesNotExist();
		}
		return ch;
	}
	
	/**
	 * 
	 * @param criterio
	 * @return
	 */
	public User searchUser(String nombre) throws PlayerDoesNotExist {
		ordenarUserName();
		User user = null;
		boolean encontro = false;
		int inicio = 0;
		int ultimo = users.size()-1;
		int centro;
		while(inicio<=ultimo && !encontro) {
			centro = (inicio+ultimo)/2;
			if(users.get(centro).getName().compareToIgnoreCase(nombre)==0) {
				user = users.get(centro);
				encontro = true;
			}else if(users.get(centro).getName().compareToIgnoreCase(nombre)<0) {
				inicio = centro+1;
			}else {
				ultimo = centro-1;
			}
		}
		if(user == null) {
			throw new PlayerDoesNotExist();
		}
		
		return user;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	
	/**
	 * 
	 * @param users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	/**
	 * 
	 * @return headField
	 */
	public Field getHeadField() {
		return headField;
	}

	/**
	 * 
	 * @param headField
	 */
	public void setHeadField(Field headField) {
		this.headField = headField;
	}

	/**
	 * 
	 * @return
	 */
	public Character getHeadCharacter() {
		return headCharacter;
	}

	/**
	 * 
	 * @param headCharacter
	 */
	public void setHeadCharacter(Character headCharacter) {
		this.headCharacter = headCharacter;
	}
	
	
	/**
	 * 
	 * @param message
	 */
	public void choosenUser(String message) {
		userChoose = new User(message,0);		
	}
}


