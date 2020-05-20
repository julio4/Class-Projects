import java.util.ArrayList;

/**
 * JeuDeCarreaux.java Définit une liste de Carreau
 * 
 * @author Jules Doumèche, Gwénolé Martin
 */
public class JeuDeCarreaux {

	private static final int TMAX = 3;
	private ArrayList<Carreau> jdc;

	/*
	 * Constructeur: JeuDeCarreaux
	 * 
	 * @return le jeu de carreaux initialisé avec les 18 carreaux bleus et rouges
	 */
	public JeuDeCarreaux() {
		jdc = new ArrayList<>();
		for(char i = 'a'; i < ( 'a' + TMAX*TMAX ); ++i) {
			jdc.add(new Carreau(i));
			jdc.add(new Carreau(Character.toUpperCase(i)));
		}
	}

	/*
	 * Crée et initialise un jeu de carreaux contenant tous les carreaux restants du type indiqué par la carte
	 * 
	 * @param type : le type du jeu de carreaux à créer
	 * @return le jeu de carreaux créé
	 */
	public JeuDeCarreaux créerListe(Type type) {
		JeuDeCarreaux retour = new JeuDeCarreaux();
		retour.jdc = new ArrayList<>();

		for(int i = 0; i < this.jdc.size(); ++i) {
			if(this.jdc.get(i).est(type)) {
				retour.jdc.add(this.jdc.get(i));
			}
		}
		return retour;
	}

	/*
	 * Vérifie si tous les carreaux ont été posés
	 * 
	 * @return true s'il ne reste plus aucun carreau, false sinon
	 */
	public boolean estVide() {
		return this.jdc.isEmpty();
	}

	/*
	 * Vérifie si le jeu de carreau contient le carreau correspondant à la lettre indiquée
	 * 
	 * @param lettre : la lettre qui correspond au carreau à vérifier
	 * @return true si le jeu de carreaux contient le carreau de la lettre indiqué, false sinon
	 */
	public boolean contient(char lettre) {
		for(int i = 0; i < jdc.size(); ++i) {
			if(lettre == jdc.get(i).getLettre()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Enlève du jeu de carreaux le carreau correspondant à la lettre indiquée
	 * 
	 * @param lettre : la lettre qui correspond au carreau à enlever
	 * @return true si la suppression a été effectuée, false sinon
	 * @see contient(char lettre) pour vérifier la présence du carreau avant de l'enlever
	 */
	public boolean enlever(char lettre) {
		if(this.contient(lettre)) {
			for(int i = 0; i < jdc.size(); ++i) {
				if(lettre == jdc.get(i).getLettre()) {
					jdc.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Retourne le nombre de carreaux non posés
	 * 
	 * @return nombre de carreaux non posés
	 */
	public int carreauxRestants() {
		return jdc.size();
	}

	/*
	 * Obtient l'affichage des carreaux dans l'ordre alphabétique et des carreaux bleus à rouges
	 * 
	 * @return la chaîne avec les carreaux côte à côte sur une ligne
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		//Récupère y max
		int yMax = 0;
		for(int i = 0; i < jdc.size() - 1; ++i) {
			if(jdc.get(i).getHauteur() > yMax) {
				yMax = jdc.get(i).getHauteur();
			}
		}

		sb.append("\n");
		//Affiche les carreaux dans l'ordre
		for(int i = yMax; i > 0; --i) {
			for(int j = 0; j < jdc.size(); ++j) {
				for(int h = 0; h < jdc.get(j).getLargeur(); ++h) {
					if(jdc.get(j).getHauteur() < i) {
						sb.append("  ");
					}
					else {
						sb.append(" " + jdc.get(j).getLettre());
					}
				}
				sb.append("  ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
