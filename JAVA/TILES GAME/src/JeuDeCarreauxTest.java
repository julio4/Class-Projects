import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JeuDeCarreauxTest {

	@Test
	void testConstructeur() {
		JeuDeCarreaux jdc = new JeuDeCarreaux();
		assertEquals(18, jdc.carreauxRestants());
	}

	@Test
	void testCréerListe() {
		JeuDeCarreaux jdc = new JeuDeCarreaux();

		JeuDeCarreaux b = jdc.créerListe(Type.BLEU);
		JeuDeCarreaux r = jdc.créerListe(Type.ROUGE);
		JeuDeCarreaux t1 = jdc.créerListe(Type.T1);
		JeuDeCarreaux t2 = jdc.créerListe(Type.T2);
		JeuDeCarreaux t3 = jdc.créerListe(Type.T3);

		assertEquals(9, b.carreauxRestants());
		assertEquals(9, r.carreauxRestants());
		assertEquals(10, t1.carreauxRestants());
		assertEquals(10, t2.carreauxRestants());
		assertEquals(10, t3.carreauxRestants());
	}

	@Test
	void testContient() {
		JeuDeCarreaux jdc = new JeuDeCarreaux();
		assertTrue(jdc.contient('a') && jdc.contient('i') 
				&& jdc.contient('A') && jdc.contient('I'));

		JeuDeCarreaux b = jdc.créerListe(Type.BLEU);
		JeuDeCarreaux r = jdc.créerListe(Type.ROUGE);

		assertTrue(b.contient('a') && b.contient('i')); 
		assertFalse(b.contient('A') && b.contient('I'));

		assertTrue(r.contient('A') && r.contient('I')); 
		assertFalse(r.contient('a') && r.contient('i'));
	}

	@Test
	void testEnlever() {
		JeuDeCarreaux jdc = new JeuDeCarreaux();
		assertTrue(jdc.contient('a'));
		jdc.enlever('a');
		assertFalse(jdc.contient('a'));
	}
}
