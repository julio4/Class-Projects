/**
 * Type.java Définit les types utilisés pour gérer les cartes (et donc les carreaux)
 * 
 * @author Jules Doumèche, Gwénolé Martin
 */
public enum Type {
	ROUGE,
	BLEU,
	T1,
	T2,
	T3;

	/**
	 * Permet l'affichage des instructions des cartes selon leurs types
	 * 
	 * @return l'instruction correspondant au type
	 */
	@Override
	public String toString() {
		switch(this) {
		case ROUGE:
			return "Rouge";
		case BLEU:
			return "Bleu";
		case T1:
			return "Taille 1";
		case T2:
			return "Taille 2";
		case T3:
			return "Taille 3";
		default:
			return "null";
		}
	}
}
