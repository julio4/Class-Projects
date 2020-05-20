import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Mur.java Définit le mur sur lequel les carreaux sont posés
 * 
 * @author Jules Doumèche, Gwénolé Martin
 */
public class Mur {

	private static final int LARGEUR = 5;
	private static final char LIBRE = ' ';
	private ArrayList<char[]> grille;
	private int nx;
	private int ny;

	/*
	 * Constructeur: Mur et initialisation sans carreau neutre (pour les tests)
	 * 
	 * @return le Mur initialisé
	 * @see Mur(boolean pNeutre) pour pouvoir poser le carreau neutre à l'initialisation du Mur
	 */
	public Mur() {
		this.grille = new ArrayList<>();
	}

	/*
	 * Constructeur: Mur et initialisation sans carreau neutre(pour les tests)
	 * 
	 * @param pNeutre : si vrai le carreau neutre est posé
	 * @return le Mur initialisé
	 * @see Mur() pour pouvoir initialiser le Mur sans paramètre d'entrée si aucun carreau neutre
	 */
	public Mur(boolean pNeutre) {
		this();
		if(pNeutre) {
			this.poserNeutre();
		}
	}

	/*
	 * Pose le carreau neutre à l'une des 4 positions possibles aléatoirement
	 * 
	 * @see Mur(boolean pNeutre) pour pouvoir poser le carreau neutre à l'initialisation du Mur
	 */
	public void poserNeutre() {
		SecureRandom r = new SecureRandom();
		boolean x = r.nextBoolean();
		boolean y = r.nextBoolean();
		Carreau neutre = new Carreau(x);
		this.nx = neutre.getLargeur();
		this.ny = neutre.getHauteur();
		if(y) {
			poser(neutre, grille.size() + 1, LARGEUR + 1 - neutre.getLargeur());
		}
		else {
			poser(neutre, grille.size() + 1, 1);
		}
	}

	/**
	 * Pose le Carreau dans la grille aux coordonnées entrées (qui ont été vérifiées au préalable)
	 * 
	 * @param c : le carreau à poser
	 * @param x : coordonnée x où placer le carreau (le plus à gauche)
	 * @param y : coordonnée y où placer le carreau (le plus en bas)
	 * @see poser(char lettre, int y, int x) pour pouvoir poser le Carreau en ne spécifiant que sa lettre et les coordonnées
	 * @see verifier(char lettre, int y, int x) pour vérifier la validité du placement du carreau aux coordonnées indiquées
	 */
	public void poser(Carreau c, int y, int x) {

		while(grille.size() - y < c.getHauteur()) {
			char[] ligne = new char[LARGEUR];
			Arrays.fill(ligne, LIBRE);
			grille.add(ligne);
		}

		for(int i = x; i < x + c.getLargeur(); ++i) {
			for(int j = y; j < y + c.getHauteur(); ++j) {
				grille.get(j - 1)[i - 1] = c.getLettre();
			}
		}
	}

	/**
	 * Pose le Carreau dans la grille aux coordonnées entrées (qui ont été vérifiées au préalable)
	 * 
	 * @param lettre : la lettre correspondant au carreau à poser
	 * @param x : coordonnée x où placer le carreau(le plus à gauche)
	 * @param y : coordonnée y où placer le carreau(le plus en bas)
	 * @see poser(Carreau c, int y, int x) pour pouvoir poser le Carreau en spécifiant le Carreau et les coordonnées
	 * @see verifier(char lettre, int y, int x) pour vérifier la validité du placement du carreau aux coordonnées indiquées
	 */
	public void poser(char lettre, int y, int x) {
		Carreau c = new Carreau(lettre);
		poser(c, y, x);
	}

	/*
	 * Vérifie si le carreau correspondant à la lettre indiquée peut-être posé aux coordonnées spécifiées
	 * 
	 * @param lettre : la lettre correspondant au carreau à vérifier
	 * @param x : coordonnée x du carreau(le plus à gauche)
	 * @param y : coordonnée y du carreau(le plus en bas)
	 * @return "Valide" si valide, sinon description de l'erreur
	 * @see poser pour pouvoir poser le Carreau après vérification
	 * @see fonctions de règles : rDépassement, rVide, rSuperposé, rCloné et rIsolé
	 */
	public String verifier(char lettre, int y, int x) {
		Carreau c = new Carreau(lettre);

		if(rDépassement(c, x)) {
			return "Le carreau dépasse la zone";
		}

		if(rVide(c, y, x)) {
			return "Le carreau repose sur une case vide";
		}

		if(rSuperposé(c, y, x)) {
			return "Le carreau est superposé à un autre";
		}

		switch(rCloné(c, y, x)) {
		case 0:
			break;
		case 1:
			return "La base du carreau clone la face supérieure du carreau inférieur";
		case 2:
			return "La face droite du carreau clone la face gauche du carreau à droite";
		case 3:
			return "La face gauche du carreau clone la face droite du carreau à gauche";
		default:
			break;
		}

		if(rIsolé(c, y ,x)) {
			return "Le carreau ne touche aucun autre carreau";
		}

		return "valide";
	}

	/*
	 * Vérifie si le carreau correspondant à la lettre indiquée peut être posé aux coordonnées spécifiées
	 * (Pour les tests unitaires)
	 * 
	 * @param x : coordonnée x du carreau (le plus à gauche)
	 * @param y : coordonnée y du carreau (le plus en bas)
	 * @param lettre : la lettre correspondant au carreau à vérifier
	 * @return true si valide, false sinon
	 * @see poser pour pouvoir poser le Carreau après vérification
	 * @see fonctions de règles : rDépassement, rVide, rSuperposé, rCloné et rIsolé
	 */
	public boolean verifier(int y, int x, char lettre) {
		Carreau c = new Carreau(lettre);
		return !(rDépassement(c, x) || rVide(c, y, x) 
				|| rSuperposé(c, y, x) || rCloné(c, y, x) > 0 || rIsolé(c, y ,x));
	}

	/*
	 * Compte le nombre de lignes complètes
	 * 
	 * @return le nombre de lignes complètes
	 */
	public int niveauComplets() {
		int i = grille.size() - 1;
		while(i >= 0) {
			for(int j = 0; j < LARGEUR; ++j) {
				if(grille.get(i)[j] == LIBRE) {
					--i;
					break;
				}
				else if(j == LARGEUR - 1) {
					return i + 1;
				}
			}
		}
		return 0;
	}

	/*
	 * Retourne le mur ( uniquement pour les tests unitaires et débug )
	 * 
	 * @return la liste de liste correspondant au mur
	 */
	public ArrayList<char[]> getMur() {
		return this.grille;
	}

	/**
	 * Permet d'afficher le mur de bas en haut avec les numérotations
	 * 
	 * @return la chaîne de caractère correspondant à l'affichage du mur
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for(int i = grille.size(); i > 0; --i) {
			if(i < 10)
				sb.append(i + " ");
			else
				sb.append(i);
			for(int j = 0; j < LARGEUR; ++j) {
				sb.append(" " + grille.get(i - 1)[j]);
			}
			sb.append("\n");
		}
		sb.append("  ");
		for(int j = 1; j <= LARGEUR; ++j) {
			sb.append(" " + j);
		}
		sb.append("\n");
		return sb.toString();
	}

	/*
	 * Vérifie si le carreau dépasse les bordures à l'abscisse x
	 * 
	 * @param c : le carreau
	 * @param x : l'abscisse
	 * @return true si le carreau dépasse les bordures du mur, false sinon
	 */
	private boolean rDépassement(Carreau c, int x) {
		return (x + c.getLargeur() - 1 > LARGEUR || x < 1);
	}

	/*
	 * Vérifie si le carreau ne repose pas entièrement sur des carreaux déjà posé aux positions x, y
	 * 
	 * @param c : le Carreau
	 * @param y : l'ordonnée
	 * @param x : l'abscisse
	 * @return true si le carreau repose sur au moins une case vide, false sinon
	 */
	private boolean rVide(Carreau c, int y, int x) {
		if( y > 1) {
			for(int i = x; i < x + c.getLargeur(); ++i) {
				if( this.grille.get(y - 2)[i - 1] == LIBRE) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Vérifie si le carreau est superposé à un autre carreau aux positions x, y
	 * 
	 * @param c : le carreau
	 * @param y : l'ordonnée
	 * @param x : l'abscisse
	 * @return true si le carreau se superpose à au moins une case pleine, false sinon
	 */
	private boolean rSuperposé(Carreau c, int y, int x) {
		ajouterLignes(c, y);

		for(int j = y; j < y + c.getHauteur(); ++j) {
			for(int i = x; i < x + c.getLargeur(); ++i) {
				if( this.grille.get(j - 1)[i - 1] != LIBRE) {
					retirerLignes(c, y);
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Vérifie si le carreau ne clone aucune des faces adjacentes
	 * 
	 * @param c : le carreau
	 * @param y : l'ordonnée
	 * @param x : l'abscisse
	 * @return 1 si la base du carreau est clonée, 2 si la face droite du carreau est clonée, 
	 * 3 si la face gauche du carreau est clonée, 0 si aucune face du carreau n'est clonée
	 */
	private int rCloné(Carreau c, int y, int x) {

		if( y > 1) {
			char cTest = this.grille.get(y - 2)[x - 1];
			if(cTest == this.grille.get(y - 2)[x + c.getLargeur() - 2]) {
				if(cTest == 'x' && nx == c.getLargeur()) {
					retirerLignes(c, y);
					return 1;
				}
				else {
					Carreau carInf = new Carreau(this.grille.get(y - 2)[x + c.getLargeur() - 2]);
					if(carInf.getLargeur() == c.getLargeur()) {
						retirerLignes(c, y);
						return 1;
					}
				}
			}
		}
		if( x - 1 + c.getLargeur() != LARGEUR ) {
			char cTest = this.grille.get(y - 1)[x - 1 + c.getLargeur()];
			if(cTest == this.grille.get(y + c.getHauteur() - 2)[x - 1 + c.getLargeur()]) {
				if(cTest == 'x' && ny == c.getHauteur()) {
					retirerLignes(c, y);
					return 2;
				}
				else {
					Carreau carInf = new Carreau(this.grille.get(y + c.getHauteur() - 2)[x - 1 + c.getLargeur()]);
					if( carInf.getHauteur() == c.getHauteur()) {
						retirerLignes(c, y);
						return 2;
					}
				}
			}
		}
		if ( x != 1 ) {
			char cTest = this.grille.get(y - 1)[x - 2];
			if(cTest == this.grille.get(y + c.getHauteur() - 2)[x - 2]) {
				if(cTest == 'x' && ny == c.getHauteur()) {
					retirerLignes(c, y);
					return 3;
				}
				else {
					Carreau carInf = new Carreau(this.grille.get(y + c.getHauteur() - 2)[x - 2]);
					if( carInf.getHauteur() == c.getHauteur()) {
						retirerLignes(c, y);
						return 3;
					}
				}
			}
		}
		return 0;
	}

	/*
	 * Permet d'ajouter les lignes supplémentaires nécessaires aux tests
	 * si le carreau dépasse la ligne la plus haute
	 * 
	 * @param c : le carreau à vérifier
	 * @param y : la ligne y où ajouter les lignes nécessaires
	 * @see retirerLignes pour retirer les lignes si le carreau n'est pas valide
	 */
	private void ajouterLignes(Carreau c, int y) {
		while(grille.size() - y < c.getHauteur()) {
			char[] ligne = new char[LARGEUR];
			Arrays.fill(ligne, LIBRE);
			grille.add(ligne);
		}
	}

	/*
	 * Permet de supprimer les lignes supplémentaires nécessaires aux tests après ajouterLignes()
	 * 
	 * @param c : le carreau à vérifier
	 * @param y : la ligne y pour supprimer les lignes ajoutées précedemment
	 * @see ajouterLignes pour ajouter les lignes
	 */
	private void retirerLignes(Carreau c, int y) {
		while(grille.size() - y < c.getHauteur()) {
			grille.remove(grille.size());
		}
	}

	/*
	 * Vérifie si le carreau ne touche aucun carreau adjacent aux positions x et y
	 * 
	 * @param c : le carreau
	 * @param y : l'ordonnée
	 * @param x : l'abscisse
	 * @return true si le carreau est isolé et ne touche aucun carreau, false sinon
	 */
	private boolean rIsolé(Carreau c, int y, int x) {
		return (y == 1 
				&& 
				(x == 1 || this.grille.get(y - 1)[x - 2] == LIBRE)
				&& 
				( x == LARGEUR || this.grille.get(y - 1)[x + c.getLargeur() - 1] == LIBRE));
	}
}
