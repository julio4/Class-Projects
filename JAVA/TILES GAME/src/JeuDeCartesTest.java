import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JeuDeCartesTest {

	@Test
	void testInit() {
		JeuDeCartes jdc = new JeuDeCartes();
		assertEquals(33, jdc.getCartesRestantes());
	}

	@Test
	void testTirer() {
		JeuDeCartes jdc = new JeuDeCartes();
		for(int i = 33; i > 0; --i) {
			assertEquals(i, jdc.getCartesRestantes());
			jdc.tirer();
		}
		assertEquals(null, jdc.tirer());
	}

}
