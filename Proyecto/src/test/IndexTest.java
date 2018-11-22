package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.NicknameNotValid;
import modelo.Index;

class IndexTest {
	
	private Index index;
	
	public void setUpEscenario(){
		index = new Index();
	}
	
	@Test
	public void registrerUserTest() throws NicknameNotValid{
		boolean tst = false;
		setUpEscenario();
		index.registrerUser("Bartolomeo");
		assertTrue(tst);
		
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
