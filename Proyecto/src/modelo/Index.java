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
import java.util.Comparator;

import exception.CharacterDoesNotExist;
import exception.CharacterNotChoosen;
import exception.FieldNotChoosen;
import exception.NicknameNotValid;
import exception.UserDoesNotExist;
import exception.UserAlreadyExists;

/**
 * Index Class
 * @author Sebastian Becerra Juan Camilo Vargas, Cristian Sierra
 * @version nov-23-2018
 */

public class Index implements Comparator<User>{
	
	
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
	 * @throws UserAlreadyExists 
	 */
	public void registrerUser(String nickname) throws NicknameNotValid, UserAlreadyExists {
		if(nickname.length()<4) {
			throw new NicknameNotValid();
		}
		for(int i = 0; i<users.size();i++) {
			if(users.get(i).getName().equalsIgnoreCase(nickname)) {
				throw new UserAlreadyExists();
			}
		}
		User add = new User(nickname,0);
		users.add(add);
		this.userChoose = add;
		
	}
	
	
	/**
	 * Method sortUserName
	 * It sorts the users by name using the bubble method.
	 */
	public void sortUserName() {	
		for (int i = 1; i < users.size(); i++) {
			for (int j = users.size() - 1; j >= i; j--) {
				if(users.get(j).compareTo(users.get(j-1))<0) {
					User aux = users.get(j);
					users.set(j,users.get(j-1));
					users.set(j-1,aux);
				}
			}
		}
	}
	
	
	/**
	 * Method sortUserScore
	 * It sorts the users by score using the selection method.
	 */
	public void sortUserScore() {
		for(int i= 0; i<users.size();i++) {
			int cual = i;
			User greater = users.get(i);
			for(int j=i+1;j<users.size();j++) {
				if(compare(greater, users.get(j))<0) {
					greater = users.get(j);
					cual = j;
				}
			}
			
			User temp = users.get(i);
			users.set(i, greater);
			users.set(cual, temp);
		}
	}
	
	
	/**
	 * Method sortUserName
	 * It sorts the users by the best game using the insertion method.
	 */
	public void sortUserBestGame() {	
		for (int i = 1; i < users.size(); i++) {
			for (int j = users.size() - 1; j >= i; j--) {
				if(users.get(j).getBestGame().compareTo(users.get(j-1).getBestGame())>0) {
					User aux = users.get(j);
					users.set(j,users.get(j-1));
					users.set(j-1,aux);
				}
			}
		}
	}
	
	
	/**
	 * Method sortUserFirstGame
	 * It sorts the users by their first game using the insertion method.
	 */
	public void sortUserFirstGame() {	
		for(int i = 1; i<users.size(); i++) {
			for(int j = i; (j > 0) && (users.get(j-1).getFirstGame().compareTo(users.get(j).getFirstGame())>0);j--) {
				User aux = users.get(j);
				users.set(j,users.get(j-1));
				users.set(j-1,aux);
			}
		}
	}
	
	
	/**
	 * Method sortUserLast
	 * It sorts the users by name using the insertion method.
	 */
	public void sortUserLast() {	
		for(int i = 1; i<users.size(); i++) {
			for(int j = i; (j > 0) && (users.get(j-1).getLastGame().compareTo(users.get(j).getLastGame())>0);j--) {
				User aux = users.get(j);
				users.set(j,users.get(j-1));
				users.set(j-1,aux);
			}
		}
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
	 * Method writeUsersS
	 * Returns a String with all users info.
	 * @return cadena- list of users with their names and score.
	 */
	public String writeUsersS() {
		sortUserScore();
		String cadena = "";
		for(int i = 0;i<users.size();i++) {
			cadena += users.get(i).getName()+"\t"+users.get(i).getScore()+",";
		}
		return cadena;
    }
	
	/**
	 * Method writeUsersB
	 * Returns a String with all users info.
	 * @return cadena- list of users with their names and score.
	 */
	public String writeUsersB() {
		sortUserLast();
		String cadena = "";
		for(int i = 0;i<users.size();i++) {
			cadena += users.get(i).getName()+"\t"+users.get(i).getBestGame()+",";
		}
		return cadena;
    }
	/**
     * Retorna una cadena de texto con los mejores juegos de los usuarios 
     * @return cadena la cadena de texto con los mejores juegos de los usuarios
     */
	public String writeUsersL() {
		sortUserLast();
		String cadena = "";
		for(int i = 0;i<users.size();i++) {
			cadena += users.get(i).getName()+"\t"+users.get(i).getLastGame()+",";
		}
		return cadena;
    }
	
	/**
     * Retorna una cadena de texto con el primer juego de los usuarios 
     * @return cadena la cadena de texto con primer juego de los usuarios
     */
	public String writeUsersF() {
		sortUserFirstGame();
		String cadena = "";
		for(int i = 0;i<users.size();i++) {
			cadena += users.get(i).getName()+"\t"+users.get(i).getFirstGame()+",";
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
		if(headField!=null) {
			if(headField.getPrevious()!=null) {
				noCirularField();
			}
		}
		if(this.headField==null) {
			this.headField = field;
		}else {
			if(actual==null) {
				field.setPrevious(previous);
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
		if(headCharacter!=null) {
			if(headCharacter.getPrevious()!=null) {
				noCircular();
			}
		}	
		if(this.headCharacter==null) {
			this.headCharacter = character;
		}else {
			if(actual==null) {
				character.setPrevious(previous);
				previous.setNext(character);
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
	 * Select the next Character
	 */
	public void showNextCharacter() {
		this.characterChoose = this.characterChoose.getNext();
	}
	/**
	 * Select the previous Character
	 */
	public void showPreviousCharacter() {
		this.characterChoose = this.characterChoose.getPrevious();
	}
	/**
	 * Select the next Field
	 */
	public void showNextField() {
		this.fieldChoose = this.fieldChoose.getNext();
	}
	/**
	 * Select the previous Field
	 */
	public void showPreviousField() {
		this.fieldChoose = this.fieldChoose.getPrevious();
	}
	/**
	 * Method electedCharacter
	 * @param elected: boolean that validate if the character has been chosen or not
	 * @throws CharacterNotChoosen throws if elected is false
	 */
	public void electedCharacter(boolean elected) throws CharacterNotChoosen {
		if(elected == false) {
			throw new CharacterNotChoosen();
		}
	}
	/**
	 * Method electedField
	 * @param elected:boolean that validate if the field has been chosen or not
	 * @throws FieldNotChoosen  throws it if elected is false
	 */
	public void electedField(boolean elected) throws FieldNotChoosen {
		if(elected == false) {
			throw new FieldNotChoosen();
		}
	}
	
	
	/**
	 * Method serializarUsers
	 * Serialize the collection of users
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void serializarUsers() throws FileNotFoundException, IOException  {
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
	
	/**
	 * Method getCharacterChoose
	 * @return characterChoose- actual character
	 */
	public Character getCharacterChoose() {
		return characterChoose;
	}

	/**
	 * Method setCharacterChoose
	 * @param characterChoose
	 */
	public void setCharacterChoose(Character characterChoose) {
		this.characterChoose = characterChoose;
	}

	
	/**
	 * Method getFieldChoose
	 * @return fieldChoose
	 */
	public Field getFieldChoose() {
		return fieldChoose;
	}

	/**
	 * setFieldChoose
	 * @param fieldChoose
	 */
	public void setFieldChoose(Field fieldChoose) {
		this.fieldChoose = fieldChoose;
	}
	/**
	 * Convert the circular list to a non circular list
	 */
	public void noCircular(){
		headCharacter.getPrevious().setNext(null);
		headCharacter.setPrevious(null);
	}
	
	
	/**
	 * Convert the circular list to a non circular list
	 */
	public void noCirularField() {
		headField.getPrevious().setNext(null);
		headField.setPrevious(null);
	}
	
	
	/**
	 * Method searchCharacter
	 * @param nombre: name of the character
	 * @return Character or null
	 * @throws CharacterDoesNotExist
	 */
	public Character searchCharacter(String nombre) throws CharacterDoesNotExist {
		
		Character ch = null;
		if(headCharacter != null) {
			if(headCharacter.getPrevious()!=null) {
				noCircular();
			}
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
	 * searchUser
	 * @param nombre: name of the user
	 * @return user or null
	 */
	public User searchUser(String nombre) throws UserDoesNotExist {
		sortUserName();
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
			throw new UserDoesNotExist();
		}
		
		return user;
	}
	
	
	/**
	 * searchUserScore
	 * @param points: points
	 * @return User
	 * @throws UserDoesNotExist : exception
	 */
	public User searchUserScore(int points) throws UserDoesNotExist {
		sortUserScore();
		User user = null;
		boolean encontro = false;
		int inicio = 0;
		int ultimo = users.size()-1;
		int centro;
		while(inicio<=ultimo && !encontro) {
			centro = (inicio+ultimo)/2;
			if(users.get(centro).getScore()==points) {
				user = users.get(centro);
				encontro = true;
			}else if(users.get(centro).getScore()>points) {
				inicio = centro+1;
			}else {
				ultimo = centro-1;
			}
		}
		if(user == null) {
			throw new UserDoesNotExist();
		}
		
		return user;
	}
	
	
	/**
	 * searchField
	 * @param name : name of the field
	 * @return Field or null
	 */
	public Field searchField(String name) {
		if(headField == null) {
			return null;
		}else{
			return headField.searchField(name);
		}
	}
	
	/**
	 * getUsers
	 * @return users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * getUserChoose
	 * @return userChoose
	 */
	public User getUserChoose() {
		return userChoose;
	}

	/**
	 * setUserChoose
	 * @param userChoose
	 */
	public void setUserChoose(User userChoose) {
		this.userChoose = userChoose;
	}


	/**
	 * setUsers
	 * @param users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	/**
	 * getHeadField
	 * @return headField
	 */
	public Field getHeadField() {
		return headField;
	}

	/**
	 * setHeadField
	 * @param headField
	 */
	public void setHeadField(Field headField) {
		this.headField = headField;
	}

	/**
	 * getHeadCharacter
	 * @return headCharacter
	 */
	public Character getHeadCharacter() {
		return headCharacter;
	}

	/**
	 * setHeadCharacter
	 * @param headCharacter
	 */
	public void setHeadCharacter(Character headCharacter) {
		this.headCharacter = headCharacter;
	}
	
	
	/**
	 * choosenUser
	 * @param data: user data
	 * @throws UserDoesNotExist 
	 */
	public void choosenUser(String data) throws UserDoesNotExist {
		String[] info = data.split("\t");
		String name = info[0];
		userChoose = searchUser(name);
		//System.out.println(userChoose.toString());
	}
	
	
	@Override
	public int compare(User user1, User user2) {
		if(user1.getScore() < user2.getScore()) {
			return -1;
		}else if(user1.getScore() > user2.getScore()){
			return 1;
		}else {
			return 0;
		}
	}
}


