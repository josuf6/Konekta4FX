package packkonekta4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testKonekta_4 {
	Konekta4 lista=Konekta4.getNireKonekta4();
	Jokalaria jok;
	@Before
	public void setUp() throws Exception {
		jok=new Jokalaria("Paco",'u');
	}
	//atributuak

	@After
	public void tearDown() throws Exception {
		lista=null;
		jok=null;
	}

	@Test
	public void testGetNireKonekta4() {
		assertNotNull(lista);
	}

	@Test
	public void testJokoaHasieratu() {
		fail("Not yet implemented");
	}

	@Test
	public void testPartidaBatJolastu() {
		fail("Not yet implemented");
	}

}
