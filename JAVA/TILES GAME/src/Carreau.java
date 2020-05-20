/**
 * Carreau.java Définit un carreau, sa lettre et ses dimensions.
 * 
 * @author Jules Doumèche, Gwénolé Martin
 */
public class Carreau {

	private char lettre;
	private int hauteur;
	private int largeur;

	/*
	 * Constructeur: Carreau en fonction de sa lettre
	 * 
	 * @param l : la lettre correspondante
	 * @return Carreau
	 */
	public Carreau(char l){

		this.lettre = l;
		l = Character.toLowerCase(l);
		switch(l) {
		case 'a':
			this.largeur = 1;
			this.hauteur = 1;
			break;
		case 'b':
			this.largeur = 1;
			this.hauteur = 2;
			break;
		case 'c':
			this.largeur = 2;
			this.hauteur = 1;
			break;
		case 'd':
			this.largeur = 2;
			this.hauteur = 2;
			break;
		case 'e':
			this.largeur = 1;
			this.hauteur = 3;
			break;
		case 'f':
			this.largeur = 3;
			this.hauteur = 1;
			break;
		case 'g':
			this.largeur = 2;
			this.hauteur = 3;
			break;
		case 'h':
			this.largeur = 3;
			this.hauteur = 2;
			break;
		case 'i':
			this.largeur = 3;
			this.hauteur = 3;
			break;
		}
	}

	/*
	 * Constructeur : Carreau neutre en fonction de son orientation
	 * 
	 * @param estVert : orientation verticale ou horizontale
	 * @return Carreau neutre
	 */
	public Carreau(boolean estVert) {
		this.lettre = 'x';
		if(estVert) {
			this.hauteur = 3;
			this.largeur = 1;
		}
		else {
			this.hauteur = 1;
			this.largeur = 3;
		}
	}

	/*
	 * Permet de savoir si le carreau est du type indiqué
	 * 
	 * @param t : le type à tester
	 * @return true si le carreau est du type indiqué, false sinon
	 * @see Type.java
	 */
	public boolean est(Type t) {
		switch(t) {
		case BLEU:
			return Character.isLowerCase(this.lettre);
		case ROUGE:
			return Character.isUpperCase(this.lettre);
		case T1:
			return (this.largeur == 1 || this.hauteur == 1);
		case T2:
			return (this.largeur == 2 || this.hauteur == 2);
		case T3:
			return (this.largeur == 3 || this.hauteur == 3);
		}
		return false;
	}

	/*
	 * Renvoie la lettre du carreau
	 * 
	 * @return lettre du carreau
	 */
	public char getLettre() {
		return this.lettre;
	}

	/*
	 * Renvoie la hauteur du carreau
	 * 
	 * @return hauteur(y) du carreau
	 */
	public int getHauteur() {
		return this.hauteur;
	}

	/*
	 * Renvoie la largeur du carreau
	 * 
	 * @return largeur(x) du carreau
	 */
	public int getLargeur() {
		return this.largeur;
	}
}
