import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarreauTest {

	@Test
	void testConstructeur() {
		for(char i = 'a'; i < 'i'; ++i) {
			Carreau c = new Carreau(i);
			assertTrue(c.getLettre() == i);
			assertTrue(c.getHauteur() > 0 && c.getLargeur() > 0);
			Carreau c2 = new Carreau(Character.toUpperCase(i));
			assertTrue(Character.isUpperCase(c2.getLettre()));
		}
	}

	@Test
	void testNeutre() {
		Carreau ver = new Carreau(true);
		assertTrue(ver.getLettre() == 'x' && ver.getLargeur() == 1 && ver.getHauteur() == 3);
		Carreau hor = new Carreau(false);
		assertTrue(hor.getLettre() == 'x' && hor.getLargeur() == 3 && hor.getHauteur() == 1);
	}

	@Test
	void testEst() {
		Carreau b = new Carreau('a');
		Carreau r = new Carreau('A');
		Carreau t1 = new Carreau('a');
		Carreau t2 = new Carreau('b');
		Carreau t3 = new Carreau('H');

		for(int i = 0; i < 5; ++i) {
			switch(i) {
			case 0:
				assertTrue(b.est(Type.BLEU));
				assertFalse(r.est(Type.BLEU));
				break;
			case 1:
				assertTrue(r.est(Type.ROUGE));
				assertFalse(b.est(Type.ROUGE));
				break;
			case 2:
				assertTrue(t1.est(Type.T1));
				assertFalse(t3.est(Type.T1));
				break;
			case 3:
				assertTrue(t2.est(Type.T2));
				assertFalse(t1.est(Type.T2));
				break;
			case 4:
				assertTrue(t3.est(Type.T3));
				assertFalse(t1.est(Type.T3));
				assertFalse(t2.est(Type.T3));
				break;
			}
		}
	}
}
