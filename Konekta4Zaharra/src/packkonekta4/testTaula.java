package packkonekta4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testTaula {
	Taula tau=Taula.getNireTaula();
    Gelaxka[][] taula;
	@Before
	public void setUp() throws Exception {
	}
	//atributuak

	@After
	public void tearDown() throws Exception {
		tau=null;
	}

	@Test
	public void testGetNireTaula() {
		 assertNotNull(tau);
	}

	@Test
	public void testBeteta() {
		assertFalse(tau.beteta());
	}

	@Test
	public void testZutabBeteta() {
		 assertFalse(tau.zutabBeteta(1));
	}

	@Test
	public void testFitxaKolorezAldatu() {
		 tau.fitxaKolorezAldatu(2,'G');
	}

	@Test
	public void testGetErrenkada() {
		tau.getErrenkada(2);
	}

	@Test
	public void testAlbokoakHutsik() {
		assertTrue(tau.albokoakHutsik(1,0));
	}

	@Test
	public void testAlbokoakKoloreBerdina() {
		assertFalse(tau.albokoakKoloreBerdina(1, 1));
	}

	@Test
	public void testAzkenekoGelaxka() {
		assertTrue(tau.azkenekoGelaxka(5,5));
		assertFalse(tau.azkenekoGelaxka(1,1));
	}

	@Test
	public void testInprimatuTaula() {
		   tau.inprimatuTaula();
	}

	//eraikitzailea(k)

	//gainontzeko metodoak
}
