package packkonekta4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JokalariaTest {
	private Jokalaria j1;
	private Jokalaria j2;
	@Before
	public void setUp() throws Exception {
		j1= new Jokalaria ("Joanito",'G');
		j2= new Jokalaria ("Pedrito",'U');
	}
	

	@After
	public void tearDown() throws Exception {
		j1=null;
		j2=null;
	}

	@Test
	public void testJokalaria() {
		assertNotNull(j1);
		assertNotNull(j2);
	}

	@Test
	public void testGetIzena() {
		assertEquals("Joanito",j1.getIzena());
		assertNotEquals("Joanito",j2.getIzena());
	}

	@Test
	public void testGetKolorea() {
		assertEquals('G',j1.getKolorea());
		assertNotEquals('G',j2.getKolorea());
	}

	@Test
	public void testSetKomodinErabilgarria() {
		j1.setKomodinErabilgarria(1);
	}
	//eraikitzailea(k)

	//gainontzeko metodoak
	
}
