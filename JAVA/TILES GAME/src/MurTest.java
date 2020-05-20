import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MurTest {

	private final static int LARGEUR = 5;

	@Test
	void testPoserNeutre() {
		Mur m = new Mur();
		m.poserNeutre();
		int i = 0;
		boolean trouvé = false;
		while(i < LARGEUR) {
			if(m.getMur().get(0)[i] == 'x') {
				trouvé = true;
				break;
			}
		}
		assertTrue(trouvé);
	}

	@Test
	void testPoser() {
		Mur m = new Mur();
		m.poser('c', 1, 1);
		assertTrue(m.getMur().get(0)[0] == 'c' && m.getMur().get(0)[1] == 'c');
		m.poser('c', 2, 1);
		assertTrue(m.getMur().get(1)[0] == 'c' && m.getMur().get(1)[1] == 'c');
	}

	@Test
	void testNiveauComplets() {
		Mur m = new Mur();
		char[] complet = new char[] {'a', 'a', 'a', 'a', 'a' };
		for(int i = 0; i < 10; ++i)  {
			m.getMur().add(complet);
		}
		System.out.println(m.niveauComplets());
		assertEquals(10, m.niveauComplets());
	}

	@Test
	void testVerifier() {
		Mur m = new Mur();

		assertFalse(m.verifier(1, 1, 'a')); // isolé

		m.poser('a', 1, 3);
		assertFalse(m.verifier(1, 2, 'a')); // clone gauche
		assertFalse(m.verifier(1, 4, 'a')); // clone droite
		assertFalse(m.verifier(2, 3, 'a')); // clone base
		assertFalse(m.verifier(2, 3, 'h')); // le carreau repose sur une case vide
		assertFalse(m.verifier(1, 2, 'h')); // superposé
		assertFalse(m.verifier(1, 4, 'h')); // dépassement zone
	}

}
