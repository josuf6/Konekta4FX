package packkonekta4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGelaxka {
	private Gelaxka g1;
	private Gelaxka g2;
	@Before
	public void setUp() throws Exception {
		g1=new Gelaxka();
		g2=new Gelaxka();
	}
	//atributuak

	@After
	public void tearDown() throws Exception {
		g1=null;
		g2=null;
	}

	@Test
	public void testGelaxka() {
		assertNotNull(g1);
		assertNotNull(g2);
	}

	@Test
	public void testGelaxkaEgikaritu() {
		fail("Not yet implemented");
	}

	//eraikitzailea(k)

	//gainontzeko metodoak
}
